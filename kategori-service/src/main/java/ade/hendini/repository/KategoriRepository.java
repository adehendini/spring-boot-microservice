package ade.hendini.repository;

import ade.hendini.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 11.51
 */
public interface KategoriRepository extends JpaRepository<Kategori, String>, JpaSpecificationExecutor<Kategori> {
}
