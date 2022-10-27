package ade.hendini.app.service;

import ade.hendini.app.model.mapper.ProdukFotoMapper;
import ade.hendini.app.model.response.ProdukFotoResponse;
import ade.hendini.aws.ProdukFotoS3;
import ade.hendini.aws.utils.ObjectS3Response;
import ade.hendini.entity.ProdukFoto;
import ade.hendini.repository.ProdukFotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Service
public class ProdukFotoService {

    @Autowired
    private ProdukFotoRepository repository;

    public List<ProdukFotoResponse> getFotoProduk(String produkId) {
        List<ProdukFoto> produkFotoList = repository.findAllByProdukIdOrderByUrutanAsc(produkId);
        return ProdukFotoMapper.responseList(produkFotoList);
    }

    public List<ProdukFotoResponse> uploadFotoProduk(String produkId, MultipartFile[] fileProdukList) throws Exception {
        repository.deleteAllByProdukId(produkId);
        int urutan = 1;
        for (MultipartFile fileProduk: fileProdukList){
            ObjectS3Response s3Response = ProdukFotoS3.uploadFile(fileProduk);
            ProdukFoto produkFoto = new ProdukFoto();
            produkFoto.setProdukId(produkId);
            produkFoto.setFileName(s3Response.getKey());
            produkFoto.setUrutan(urutan);
            repository.save(produkFoto);
            urutan++;
        }
        List<ProdukFoto> produkFotoList = repository.findAllByProdukIdOrderByUrutanAsc(produkId);
        return ProdukFotoMapper.responseList(produkFotoList);
    }
}
