package ade.hendini.repository;

import ade.hendini.entity.Produk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 14.13
 */
public interface ProdukRepository extends JpaRepository<Produk, String>, JpaSpecificationExecutor<Produk> {

}
