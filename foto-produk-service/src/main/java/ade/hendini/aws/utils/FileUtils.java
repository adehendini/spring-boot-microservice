/*
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 23/06/2021 18.32
 */

package ade.hendini.aws.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


public class FileUtils {

    public static File convertMultipartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(convertedFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return convertedFile;
    }

    public static String generateFileName(MultipartFile files) throws IOException {
        String extension = FilenameUtils.getExtension(files.getOriginalFilename());
        return new Date().getTime() + "-" + UUID.randomUUID().toString() + "." + extension;
    }



    public static FileReturn fileReturn(String bucketName, MultipartFile files) throws IOException {
        File file = FileUtils.convertMultipartToFile(files);
        String fileName = FileUtils.generateFileName(files);
        FileReturn fileReturn = new FileReturn(file,fileName,bucketName);
        return fileReturn;
    }
}
