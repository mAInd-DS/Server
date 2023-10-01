package com.mAInd.springboot.web;
import com.amazonaws.protocol.json.JsonContent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.transcribe.AmazonTranscribeClient;
import com.amazonaws.services.transcribe.model.*;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationTimedPollAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.cloud.speech.v1.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.threeten.bp.Duration;



@RequiredArgsConstructor
@RestController
public class SpeechToTextApiController {

    private final AwsS3Service awsS3Service;
    private final AmazonTranscribeClient transcribeClient;
    private final AmazonS3 s3Client;

    @GetMapping("/transcribe")
    public String transcribe() throws IOException {
        //S3 버킷에서 음성파일 가져오기
        String bucketName = "maind-bucket";
        String objectKey = "voice.wav";
        String jobName = "transcribe-job-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        //Media 객체 생성하여 S3 버킷에서 가져온 음성 파일 URI 설정
        Media media = new Media().withMediaFileUri("s3://" + bucketName + "/" + objectKey);

        //Transcription 작업을 시작하기 위한 요청 생성
        StartTranscriptionJobRequest request = new StartTranscriptionJobRequest()
                .withTranscriptionJobName(jobName)
                .withLanguageCode(LanguageCode.KoKR)
                .withMedia(media)
                .withOutputBucketName(bucketName);

        //Transcription 작업 시작 요청 보내고 결과 받기
        StartTranscriptionJobResult result = transcribeClient.startTranscriptionJob(request);

        //Transcription 작업이 완료될 때까지 대기
        while (true) {
            GetTranscriptionJobRequest getTranscriptionJobRequest = new GetTranscriptionJobRequest()
                    .withTranscriptionJobName(jobName);
            GetTranscriptionJobResult getTranscriptionJobResult = transcribeClient.getTranscriptionJob(getTranscriptionJobRequest);
            String status = getTranscriptionJobResult.getTranscriptionJob().getTranscriptionJobStatus();

            if (status.equals(TranscriptionJobStatus.COMPLETED.toString())) {
                /*
                String transcriptFileUri = getTranscriptionJobResult.getTranscriptionJob().getTranscript().getTranscriptFileUri();

                // S3에서 JSON 파일 읽기
                InputStream inputStream = s3Client.getObject(bucketName, transcriptFileUri).getObjectContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder jsonContent = new StringBuilder();
                String line;

                // 한 줄씩 읽어와 StringBuilder에 추가
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line).append('\n');
                }

                // JSON 내용 출력
                System.out.println("JSON Content:\n" + jsonContent.toString());
                reader.close();
                inputStream.close();

                break;
                */
                return "Transcription completed.";
            }
        }

    }


    /*
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
    */

}
