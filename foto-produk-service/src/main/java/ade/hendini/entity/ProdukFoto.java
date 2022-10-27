package ade.hendini.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "produk_foto")
public class ProdukFoto {
    @Id
    private String id;

    @Field(name = "produk_id")
    private String produkId;

    @Field(name = "urutan")
    private Integer urutan;

    @Field(name = "file_name")
    private String fileName;

    @Field(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Field(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;
}
