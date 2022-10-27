package ade.hendini.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Data
public class ProdukFotoResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("produk_id")
    private String produkId;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
