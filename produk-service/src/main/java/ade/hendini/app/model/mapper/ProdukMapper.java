package ade.hendini.app.model.mapper;

import ade.hendini.app.model.request.ProdukRequest;
import ade.hendini.app.model.response.ProdukResponse;
import ade.hendini.entity.Produk;
import ade.hendini.repository.ProdukRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Component
public class ProdukMapper {

    private static ProdukRepository repository;

    public ProdukMapper(ProdukRepository repository) {
        ProdukMapper.repository = repository;
    }

    public static ProdukResponse response(Produk produk){
        ProdukResponse res = new ProdukResponse();
        res.setId(produk.getId());
        res.setKategori(KategoriMapper.response(produk.getKategori()));
        res.setNamaProduk(produk.getNamaProduk());
        res.setHargaProduk(produk.getHargaProduk());
        res.setDeskripsiProduk(produk.getDeskripsiProduk());
        res.setStokProduk(produk.getStokProduk());
        res.setIsActive(produk.getIsActive());
        res.setKondisiProduk(produk.getKondisiProduk());
        res.setBeratProduk(produk.getBeratProduk());
        res.setCreatedAt(produk.getCreatedAt());
        res.setUpdatedAt(produk.getUpdatedAt());
        return res;
    }

    public static Produk request(ProdukRequest req, Produk produk){
        produk.setKategori(KategoriMapper.findById(req.getKategoriId()));
        produk.setNamaProduk(req.getNamaProduk());
        produk.setHargaProduk(req.getHargaProduk());
        produk.setDeskripsiProduk(req.getDeskripsiProduk());
        produk.setStokProduk(req.getStokProduk());
        produk.setKondisiProduk(req.getKondisiProduk().toString());
        produk.setBeratProduk(req.getBeratProduk());
        produk.setIsActive(req.getIsActive());
        return produk;
    }

    public static Produk findById(String id){
        Optional<Produk> produk = repository.findById(id);
        if(!produk.isPresent()){
            return null;
        }
        return produk.get();
    }
}
