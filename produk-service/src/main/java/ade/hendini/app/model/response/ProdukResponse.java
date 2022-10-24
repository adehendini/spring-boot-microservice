package ade.hendini.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Data
public class ProdukResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("kategori")
    private KategoriResponse kategori;

    @JsonProperty("nama_produk")
    private String namaProduk;

    @JsonProperty("harga_produk")
    private Integer hargaProduk;

    @JsonProperty("deskripsi_produk")
    private String deskripsiProduk;

    @JsonProperty("stok_produk")
    private Integer stokProduk;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("kondisi_produk")
    private String kondisiProduk;

    @JsonProperty("berat_produk")
    private Integer beratProduk;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
