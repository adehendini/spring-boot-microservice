/*
 * Author : Ade Hendini
 * Email : adehendini@gmail.com
 * Date : 10/24/22, 5:17 AM
 */

package ade.hendini.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse<T> {

    private boolean status;

    private String message;

    private T payload;
}
