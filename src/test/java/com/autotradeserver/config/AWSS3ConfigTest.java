package com.autotradeserver.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class AWSS3ConfigTest {

    private AmazonS3 amazonS3;
    private AWSS3Config awss3Config;

    @Test
    void listBuckets(){
        amazonS3 = awss3Config.amazonS3();
        List<Bucket> buckets = amazonS3.listBuckets();
        for(Bucket bucket : buckets){
            System.out.println(bucket);
        }
    }
}
