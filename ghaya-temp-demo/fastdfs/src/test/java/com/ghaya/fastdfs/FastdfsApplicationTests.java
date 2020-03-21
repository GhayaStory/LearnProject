package com.ghaya.fastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastdfsApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    public void contextLoads() throws FileNotFoundException {
        System.out.println(1);
        File file = new File("d://bug.jpg");
        String fileName = file.getName();
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        FileInputStream inputStream = new FileInputStream(file);

//        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), extName, null);
//        StorePath storePath = fastFileStorageClient.uploadFile("group2",inputStream,fileName.length(),extName);
        StorePath storePath = fastFileStorageClient.uploadFile("TcardsImg",inputStream,fileName.length(),extName);

        System.out.println(storePath.getGroup());
        System.out.println(storePath.getPath());
        System.out.println(storePath.getFullPath());
    }

}
