package ade.hendini.app.model.mapper;

import ade.hendini.app.model.request.ProdukFotoRequest;
import ade.hendini.app.model.response.ProdukFotoResponse;
import ade.hendini.aws.ProdukFotoS3;
import ade.hendini.entity.ProdukFoto;
import ade.hendini.repository.ProdukFotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Component
public class ProdukFotoMapper {

    private static ProdukFotoRepository repository;

    @Autowired
    public ProdukFotoMapper(ProdukFotoRepository repository) {
        ProdukFotoMapper.repository = repository;
    }

    public static ProdukFotoResponse response(ProdukFoto produkFoto){
        ProdukFotoResponse res = new ProdukFotoResponse();
        res.setId(produkFoto.getId());
        res.setProdukId(produkFoto.getProdukId());
        String fileName = ProdukFotoS3.getUrl(produkFoto.getFileName());
        res.setFileName(fileName);
        res.setCreatedAt(produkFoto.getCreatedAt());
        res.setUpdatedAt(produkFoto.getUpdatedAt());
        return res;
    }

    public static List<ProdukFotoResponse> responseList(List<ProdukFoto> produkFotoList){
        List<ProdukFotoResponse> respList = new ArrayList<>();
        for (ProdukFoto produkFoto: produkFotoList){
            respList.add(response(produkFoto));
        }
        return respList;
    }

    public static ProdukFoto request(ProdukFotoRequest req, ProdukFoto produkFoto){
        produkFoto.setUrutan(req.getUrutan());
        return produkFoto;
    }
}
