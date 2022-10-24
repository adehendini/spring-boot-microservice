package ade.hendini.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 13.02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produk")
public class Produk {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "kategori_id", nullable = true, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Kategori kategori;

    @Column(name = "nama_produk",nullable = false)
    private String namaProduk;

    @Column(name = "harga_produk",nullable = false)
    private Integer hargaProduk;

    @Column(name = "deskripsi_produk")
    private String deskripsiProduk;

    @Column(name = "stok_produk",nullable = false)
    private Integer stokProduk;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive = true;

    @Column(name = "kondisi_produk")
    private String kondisiProduk;

    @Column(name = "berat_produk")
    private Integer beratProduk;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
