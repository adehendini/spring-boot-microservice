package ade.hendini.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 11.55
 */

@Data
public class KategoriResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("kategori_parent")
    private KategoriParentResponse kategoriParentResponse;

    @JsonProperty("nama_kategori")
    private String namaKategori;

    @JsonProperty("base64_file")
    private String base64File;

    @JsonProperty("icon_file")
    private String iconFile;
}
