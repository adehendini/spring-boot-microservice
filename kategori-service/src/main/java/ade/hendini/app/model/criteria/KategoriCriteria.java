package ade.hendini.app.model.criteria;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 12.24
 */

@Data
public class KategoriCriteria {
    @Min(value = 1)
    private int page = 1;

    @Min(value = 1)
    private int size = 20;

    private String cariNamaKategori;
}
