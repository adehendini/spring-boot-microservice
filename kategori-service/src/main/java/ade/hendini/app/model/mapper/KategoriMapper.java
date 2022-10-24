package ade.hendini.app.model.mapper;

import ade.hendini.app.model.request.KategoriRequest;
import ade.hendini.app.model.response.KategoriResponse;
import ade.hendini.aws.KategoriS3;
import ade.hendini.entity.Kategori;
import ade.hendini.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 12.03
 */

@Component
public class KategoriMapper {

    private static KategoriRepository repository;

    @Autowired
    public KategoriMapper(KategoriRepository repository) {
        KategoriMapper.repository = repository;
    }

    public static KategoriResponse response(Kategori kategori){
        KategoriResponse resp = new KategoriResponse();
        resp.setId(kategori.getId());
        if(kategori.getKategoriParent()!=null) {
            resp.setKategoriParentResponse(KategoriParentMapper.response(kategori.getKategoriParent()));
        }
        resp.setNamaKategori(kategori.getNamaKategori());
        resp.setBase64File(kategori.getBase64File());
        if(kategori.getIconFile()!=null) {
            String urlIcon = KategoriS3.getPresignedUrl(kategori.getIconFile());
            resp.setIconFile(urlIcon);
        }
        return resp;
    }

    public static Kategori request(KategoriRequest req, Kategori entity){
        if(req.getKategoriParentId()!=null){
            entity.setKategoriParent(findById(req.getKategoriParentId()));
        }
        entity.setNamaKategori(req.getNamaKategori());
        return entity;
    }

    public static Kategori findById(String id){
        Optional<Kategori> kategori = repository.findById(id);
        if(!kategori.isPresent()){
            return null;
        }
        return kategori.get();
    }

}
