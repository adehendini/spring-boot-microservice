package ade.hendini.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 11.52
 */

@Data
public class KategoriParentResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("nama_kategori")
    private String namaKategori;
}
