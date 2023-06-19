package com.mAInd.springboot.web;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.transcribe.AmazonTranscribeClient;
import com.amazonaws.services.transcribe.model.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AmazonTranscribeApiController {

    private final AmazonTranscribeClient transcribeClient;
    private final AmazonS3 s3Client;

    @GetMapping("/transcribe2")
    public String transcribe2() throws IOException {
        //S3 버킷에서 음성파일 가져오기
        String bucketName = "maind-bucket";
        String objectKey = "interview.mp3";
        String jobName = "transcribe-job-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        //Media 객체 생성하여 S3 버킷에서 가져온 음성 파일 URI 설정
        Media media = new Media().withMediaFileUri("s3://" + bucketName + "/" + objectKey);

        Settings settings = new Settings().withShowSpeakerLabels(true);

        //Transcription 작업을 시작하기 위한 요청 생성
        StartTranscriptionJobRequest request = new StartTranscriptionJobRequest()
                .withTranscriptionJobName(jobName)
                .withLanguageCode(LanguageCode.KoKR)
                .withMedia(media)
                .withOutputBucketName(bucketName)
                .withSettings(settings.withMaxSpeakerLabels(2));

        //Transcription 작업 시작 요청 보내고 결과 받기
        StartTranscriptionJobResult result = transcribeClient.startTranscriptionJob(request);

        //Transcription 작업이 완료될 때까지 대기
        while (true) {
            GetTranscriptionJobRequest getTranscriptionJobRequest = new GetTranscriptionJobRequest()
                    .withTranscriptionJobName(jobName);
            GetTranscriptionJobResult getTranscriptionJobResult = transcribeClient.getTranscriptionJob(getTranscriptionJobRequest);
            String status = getTranscriptionJobResult.getTranscriptionJob().getTranscriptionJobStatus();

            if (status.equals(TranscriptionJobStatus.COMPLETED.toString())) {/*
                String transcriptFileUri = getTranscriptionJobResult.getTranscriptionJob().getTranscript().getTranscriptFileUri();

                // S3에서 JSON 파일 읽기
                S3Object s3Object = s3Client.getObject(bucketName, transcriptFileUri);
                S3ObjectInputStream inputStream = s3Object.getObjectContent();
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

                // 화자 분리된 결과를 처리하는 메서드 호출
                processSpeakerLabels(jsonContent.toString());

                break;
            }*/
            }
            return "Transcription completed.";
        }
    }
}


//
//    private void processSpeakerLabels(String jsonContent) {
//        try {
//            JSONObject jsonObject = new JSONObject(jsonContent);
//            JSONArray speakerLabels = jsonObject.getJSONObject("results").getJSONArray("speaker_labels");
//
//            // 화자 분리된 결과를 저장할 변수
//            Map<Integer, StringBuilder> speakerMap = new HashMap<>();
//            int currentSpeaker = -1;
//
//            // 화자 레이블을 반복하며 각 화자의 문장을 저장
//            for (int i = 0; i < speakerLabels.length(); i++) {
//                JSONObject label = speakerLabels.getJSONObject(i);
//                int start = label.getInt("start_time");
//                int end = label.getInt("end_time");
//                int speaker = label.getInt("speaker_label");
//
//                if (speaker != currentSpeaker) {
//                    currentSpeaker = speaker;
//                    speakerMap.put(speaker, new StringBuilder());
//                }
//
//                String sentence = label.getString("alternatives").split(" ")[0];
//                speakerMap.get(speaker).append(sentence).append(" ");
//            }
//
//            // 각 화자의 문장 출력
//            for (int speaker : speakerMap.keySet()) {
//                System.out.println("Speaker " + speaker + ": " + speakerMap.get(speaker).toString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//



