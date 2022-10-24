package ade.hendini.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 06/04/2022 08.02
 */
@Data
public class KategoriResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("nama_kategori")
    private String namaKategori;

    @JsonProperty("icon_file")
    private String iconFile;
}
