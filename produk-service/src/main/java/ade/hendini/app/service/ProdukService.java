package ade.hendini.app.service;

import ade.hendini.app.model.criteria.ProdukCriteria;
import ade.hendini.app.model.mapper.ProdukMapper;
import ade.hendini.app.model.request.ProdukRequest;
import ade.hendini.app.model.response.ProdukResponse;
import ade.hendini.entity.Produk;
import ade.hendini.helpers.PaginationResponse;
import ade.hendini.repository.ProdukRepository;
import ade.hendini.utils.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Service
public class ProdukService {

    @Autowired
    private ProdukRepository repository;

    public PaginationResponse<List<ProdukResponse>> retrieve(ProdukCriteria criteria){
        int page = criteria.getPage();
        int size = criteria.getSize();
        Pageable paging = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        Page<Produk> listItems = repository.findAll(new Specification<Produk>() {
            @Override
            public Predicate toPredicate(Root<Produk> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(criteria.getCariNamaProduk()!=null){
                    predicates.add(criteriaBuilder.and(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("namaProduk")),"%"+criteria.getCariNamaProduk().toLowerCase()+"%")
                    ));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },paging);
        return this.index(listItems, page, size);
    }

    public ProdukResponse create(ProdukRequest req) {
        var entity = new Produk();
        repository.save(ProdukMapper.request(req, entity));
        return ProdukMapper.response(entity);
    }

    public ProdukResponse update(String id, ProdukRequest req) {
        var entity = this.findModel(id);
        repository.save(ProdukMapper.request(req, entity));
        return ProdukMapper.response(entity);
    }

    public ProdukResponse getById(String id) {
        var entity = this.findModel(id);
        return ProdukMapper.response(entity);
    }

    public boolean delete(String id) {
        var entity = this.findModel(id);
        try {
            repository.delete(entity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private PaginationResponse<List<ProdukResponse>> index(Page<Produk> content, int page, int size){
        List<ProdukResponse> listItems = new ArrayList<>();
        for(var entity: content.getContent()){
            listItems.add(ProdukMapper.response(entity));
        }
        PaginationResponse<List<ProdukResponse>> response = new PaginationResponse<>();
        response.setPage(page);
        response.setSize(size);
        response.setTotal_page(content.getTotalPages());
        response.setTotal_items(content.getTotalElements());
        response.setItems(listItems);
        return response;
    }

    private Produk findModel(String id){
        Produk entity = ProdukMapper.findById(id);
        if(entity==null){
            throw new CustomNotFoundException("Produk tidak ditemukan");
        }
        return entity;
    }
}
