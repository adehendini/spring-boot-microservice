package ade.hendini.app.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Data
public class ProdukFotoRequest {

    @NotNull(message = "Urutan harus diisi")
    @JsonProperty("urutan")
    private Integer urutan;

    @JsonProperty("foto_produk")
    private MultipartFile fotoProduk;

}
