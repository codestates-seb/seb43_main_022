package com.codea.Image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    public String uploadImage(String imageName, String image, String email) {
        imageName = imageName.substring(0, imageName.indexOf(".")) + "_" + image.substring(5, Math.min(image.length(), 20)) + imageName.substring(imageName.indexOf("."));
        String uploadUrl = "image/" + email + "/" + imageName;
        byte[] decodeImage = Base64.getMimeDecoder().decode(image.substring(image.indexOf(",") + 1));

        //파일 형식 구하기
        String ext = imageName.split("\\.")[1];
        String contentType = "";

        //content type을 지정해서 올려주지 않으면 자동으로 "application/octet-stream"으로 고정이 되서 링크 클릭시 웹에서 열리는게 아니라 자동 다운이 시작됨.
        switch (ext) {
            case "JPEG":
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "JPG":
            case "jpg":
                contentType = "image/jpg";
                break;
            case "PNG":
            case "png":
                contentType = "image/png";
                break;
        }

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);

            amazonS3.putObject(new PutObjectRequest(bucket, uploadUrl, new ByteArrayInputStream(decodeImage), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

        //object 정보 가져오기
//        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucket);
//        List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();
//
//        for (S3ObjectSummary object: objectSummaries) {
//            System.out.println("object = " + object.toString());
//        }
        String s3url = amazonS3.getUrl(bucket, uploadUrl).toString().replaceAll("%40","@");
        return s3url;
    }
}