/*
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 23/06/2021 21.01
 */

package ade.hendini.aws.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.File;


@AllArgsConstructor
@Data
@Builder
public class FileReturn {
    private File file;

    private String fileName;

    private String bucketName;
}
