package ade.hendini.repository;

import ade.hendini.entity.ProdukFoto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
public interface ProdukFotoRepository extends MongoRepository<ProdukFoto, String> {

    List<ProdukFoto> findAllByProdukIdOrderByUrutanAsc(String produkId);

    long deleteAllByProdukId(String produkId);

}
