/*
 * Author : Ade Hendini
 * Email : adehendini@gmail.com
 * Date : 10/24/22, 5:16 AM
 */

package ade.hendini.helpers;

import lombok.Data;

@Data
public class PaginationResponse<T> {
    private Integer page;

    private Integer size;

    private Integer total_page;

    private Long total_items;

    private T items;
}
