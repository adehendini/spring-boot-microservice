package ade.hendini.app.model.request;

import ade.hendini.app.model.enums.KondisiProduk;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Data
public class ProdukRequest {

    @NotNull(message = "Kategori ID harus diisi")
    @JsonProperty("kategori_id")
    private String kategoriId;

    @NotNull(message = "Nama Produk harus diisi")
    @JsonProperty("nama_produk")
    private String namaProduk;

    @NotNull(message = "Harga Produk harus diisi")
    @JsonProperty("harga_produk")
    private Integer hargaProduk;

    @JsonProperty("deskripsi_produk")
    private String deskripsiProduk;

    @Min(value = 0, message = "Stok Produk diisi minimal 0")
    @JsonProperty("stok_produk")
    private Integer stokProduk;

    @NotNull(message = "Kondisi Produk harus diisi")
    @JsonProperty("kondisi_produk")
    private KondisiProduk kondisiProduk;

    @Min(value = 0, message = "Berat Produk diisi minimal 0")
    @JsonProperty("berat_produk")
    private Integer beratProduk;

    @NotNull(message = "Publish harus diisi")
    @JsonProperty("is_active")
    private Boolean isActive;
}
