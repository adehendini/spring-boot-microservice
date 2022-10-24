package ade.hendini.app.model.mapper;

import ade.hendini.app.model.response.KategoriResponse;
import ade.hendini.aws.KategoriS3;
import ade.hendini.entity.Kategori;
import ade.hendini.repository.KategoriRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Component
public class KategoriMapper {

    private static KategoriRepository repository;

    public KategoriMapper(KategoriRepository repository) {
        KategoriMapper.repository = repository;
    }

    public static KategoriResponse response(Kategori kategori){
        KategoriResponse res = new KategoriResponse();
        res.setId(kategori.getId());
        res.setNamaKategori(kategori.getNamaKategori());
        if(StringUtils.isNotBlank(kategori.getIconFile())) {
            String iconFile = KategoriS3.getPresignedUrl(kategori.getIconFile());
            res.setIconFile(iconFile);
        }
        return res;
    }

    public static Kategori findById(String id){
        Optional<Kategori> kategori = repository.findById(id);
        if(!kategori.isPresent()){
            return null;
        }
        return kategori.get();
    }
}
