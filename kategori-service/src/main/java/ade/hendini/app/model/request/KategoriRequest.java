package ade.hendini.app.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 12.02
 */

@Data
public class KategoriRequest {

    @JsonProperty("kategori_parent_id")
    private String kategoriParentId;

    @NotNull(message = "Nama Kategori harus diisi")
    @JsonProperty("nama_kategori")
    private String namaKategori;
}
