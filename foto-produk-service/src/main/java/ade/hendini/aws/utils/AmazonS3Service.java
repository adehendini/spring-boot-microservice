/*
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 23/06/2021 15.11
 */

package ade.hendini.aws.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


@Service
public class AmazonS3Service {

    @Autowired
    private AmazonS3 s3;

    public void checkBucket(String bucketName){
        if(!this.s3.doesBucketExistV2(bucketName)){
            this.s3.createBucket(bucketName);
        }
    }

    public ObjectS3Response uploadFile(String bucketName, MultipartFile files) throws Exception {
        FileReturn fileReturn = this.utils(bucketName,files);
        File file = fileReturn.getFile();
        String fileName = fileReturn.getFileName();
        s3.putObject(bucketName, fileName, file);
        S3Object obj = s3.getObject(bucketName,fileName);
        ObjectS3Response response = new ObjectS3Response();
        response.setKey(obj.getKey());
        response.setContentType(obj.getObjectContent().toString());
        return response;
    }

    public ObjectS3Response uploadPublicRead(String bucketName, MultipartFile files) throws Exception {
        FileReturn fileReturn = this.utils(bucketName,files);
        File file = fileReturn.getFile();
        String fileName = fileReturn.getFileName();
        s3.putObject(new PutObjectRequest(bucketName, fileName,file).withCannedAcl(CannedAccessControlList.PublicRead));
        S3Object obj = s3.getObject(bucketName,fileName);
        ObjectS3Response response = new ObjectS3Response();
        response.setKey(obj.getKey());
        response.setContentType(obj.getObjectContent().toString());
        return response;
    }

    public String getPresignedUrl(String bucketName, String key){
        if(key!=null) {
            if (!s3.doesObjectExist(bucketName, key)) {
                return null;
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DATE, 1);
                return s3.generatePresignedUrl(bucketName, key, calendar.getTime()).toString();
            }
        }else {
            return null;
        }
    }

    public String getUrl(String bucketName, String key){
        if(key!=null) {
            if (!s3.doesObjectExist(bucketName, key)) {
                return null;
            } else {
                return s3.getUrl(bucketName, key).toString();
            }
        }else {
            return null;
        }
    }


    public void deleteFile(String bucketName, String key) throws Exception {
        if(key!=null) {
            if (s3.doesObjectExist(bucketName, key)) {
                s3.deleteObject(bucketName, key);
            }
        }
    }

    public byte[] getFile(String bucketName, String fileName) throws Exception {
        S3Object obj = this.getObject(bucketName, fileName);
        S3ObjectInputStream stream = obj.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(stream);
            obj.close();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public S3Object getObject(String bucketName, String fileName){
        return s3.getObject(bucketName, fileName);
    }

    private FileReturn utils(String bucketName, MultipartFile files) throws IOException {
        return FileUtils.fileReturn(bucketName, files);
    }


}
