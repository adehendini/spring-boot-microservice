/*
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 23/06/2021 16.04
 */

package ade.hendini.aws.utils;

import lombok.Data;


@Data
public class ObjectS3Response {
    private String key;

    private String contentType;
}
