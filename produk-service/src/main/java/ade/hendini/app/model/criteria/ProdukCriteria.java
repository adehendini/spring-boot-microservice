package ade.hendini.app.model.criteria;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 13.44
 */
@Data
public class ProdukCriteria {
    @Min(value = 1)
    private int page = 1;

    @Min(value = 1)
    private int size = 20;

    private String cariNamaProduk;
}
