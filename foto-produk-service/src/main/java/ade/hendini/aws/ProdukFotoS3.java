/*
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 23/06/2021 16.50
 */

package ade.hendini.aws;

import ade.hendini.aws.utils.AmazonS3Service;
import ade.hendini.aws.utils.ObjectS3Response;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Component
public class ProdukFotoS3 {

    private static AmazonS3Service amazonS3Service;

    private static final String folderName = "produk";

    @Autowired
    public ProdukFotoS3(AmazonS3Service amazonS3Service) {
        ProdukFotoS3.amazonS3Service = amazonS3Service;
        ProdukFotoS3.amazonS3Service.checkBucket(ProdukFotoS3.folderName);
    }

    public static ObjectS3Response uploadFile(MultipartFile files) throws Exception {
        String bucketName = ProdukFotoS3.folderName;
        return amazonS3Service.uploadFile(bucketName, files);
    }

    public static ObjectS3Response uploadPublicRead(MultipartFile files) throws Exception {
        String bucketName = ProdukFotoS3.folderName;
        return amazonS3Service.uploadPublicRead(bucketName, files);
    }

    public static String getUrl(String key){
        String bucketName = ProdukFotoS3.folderName;
        return amazonS3Service.getUrl(bucketName, key);
    }

    public static String getPresignedUrl(String key){
        String bucketName = ProdukFotoS3.folderName;
        return amazonS3Service.getPresignedUrl(bucketName, key);
    }

    public static void deleteFile(String key) throws Exception {
        String bucketName = ProdukFotoS3.folderName;
        amazonS3Service.deleteFile(bucketName, key);
    }

    public static S3Object getObject(String fileName){
        String bucketName = ProdukFotoS3.folderName;
        return amazonS3Service.getObject(bucketName, fileName);
    }

    public static byte[] getFile(String fileName) throws Exception{
        S3Object obj = ProdukFotoS3.getObject(fileName);
        S3ObjectInputStream stream = obj.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(stream);
            obj.close();
            return content;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
