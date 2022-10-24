package ade.hendini.app.model.mapper;

import ade.hendini.app.model.response.KategoriParentResponse;
import ade.hendini.entity.Kategori;
import org.springframework.stereotype.Component;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 11.53
 */
@Component
public class KategoriParentMapper {

    public static KategoriParentResponse response(Kategori kategori){
        KategoriParentResponse resp = new KategoriParentResponse();
        resp.setId(kategori.getId());
        resp.setNamaKategori(kategori.getNamaKategori());
        return resp;
    }
}
