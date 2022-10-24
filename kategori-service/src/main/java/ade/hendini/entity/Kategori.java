package ade.hendini.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 11.50
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kategori")
public class Kategori {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "kategori_id", nullable = true, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Kategori kategoriParent;

    @Column(name = "nama_kategori",nullable = false)
    private String namaKategori;

    @Column(name = "base64_file", columnDefinition = "TEXT")
    private String base64File;

    @Column(name = "icon_file")
    private String iconFile;

    @Column(name = "icon_content_type")
    private String iconContentType;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
