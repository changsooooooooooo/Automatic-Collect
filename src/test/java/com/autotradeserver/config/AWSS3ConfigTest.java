package com.autotradeserver.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class AWSS3ConfigTest {

    private AmazonS3 amazonS3;

    @Test
    void listBuckets(){
        amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
                .build();
        List<Bucket> buckets = amazonS3.listBuckets();
        String bucketName = buckets.get(0).getName();
        amazonS3.putObject(
          bucketName, "bucket-path", "file-string"
        );
    }
}
