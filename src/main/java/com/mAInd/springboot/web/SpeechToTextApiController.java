package com.mAInd.springboot.web;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationTimedPollAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.cloud.speech.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
import org.threeten.bp.Duration;

@RequiredArgsConstructor
@RestController
public class SpeechToTextApiController {

    private final AwsS3Service awsS3Service;

    @GetMapping("/speech")
    public String speech() throws IOException {
        try (SpeechClient speechClient = SpeechClient.create()) {

            // The path to the audio file to transcribe
            String gcsUri = "gs://ds-maind-bucket/voice.wav";

            // Builds the sync recognize request
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setSampleRateHertz(48000)
                            .setLanguageCode("ko-KR")
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech.
                // Just use the first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());

                //speech-to-text 결과를 S3 bucket에 저장
                String fileName = "text.txt";
                String transcript = alternative.getTranscript();
                byte [] strBytes = transcript.getBytes();
                awsS3Service.uploadObject(strBytes, fileName);

                return transcript;
            }
        }
        return "error message";
    }

    @GetMapping("/speech/long")
    public String speechLong() throws Exception {
        String gcsUri = "gs://ds-maind-bucket/class_voice.wav";
        return asyncRecognizeGcs(gcsUri);
    }


    public static String asyncRecognizeGcs(String gcsUri) throws Exception {
        // Configure polling algorithm
        SpeechSettings.Builder speechSettings = SpeechSettings.newBuilder();
        TimedRetryAlgorithm timedRetryAlgorithm =
                OperationTimedPollAlgorithm.create(
                        RetrySettings.newBuilder()
                                .setInitialRetryDelay(Duration.ofMillis(500L))
                                .setRetryDelayMultiplier(1.5)
                                .setMaxRetryDelay(Duration.ofMillis(5000L))
                                .setInitialRpcTimeout(Duration.ZERO) // ignored
                                .setRpcTimeoutMultiplier(1.0) // ignored
                                .setMaxRpcTimeout(Duration.ZERO) // ignored
                                .setTotalTimeout(Duration.ofHours(24L)) // set polling timeout to 24 hours
                                .build());
        speechSettings.longRunningRecognizeOperationSettings().setPollingAlgorithm(timedRetryAlgorithm);

        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create(speechSettings.build())) {

            // Configure remote file request for FLAC
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("ko-KR")
                            .setSampleRateHertz(48000)
                            .setAudioChannelCount(1)
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speech.longRunningRecognizeAsync(config, audio);
            while (!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }
            //수정코드
            StringBuilder transcription = new StringBuilder();
            List<SpeechRecognitionResult> results = response.get().getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech.
                // Just use the first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                transcription.append(alternative.getTranscript());
                System.out.printf("Transcription: %s\n", alternative.getTranscript());
            }
            return transcription.toString();
        }
    }
}
