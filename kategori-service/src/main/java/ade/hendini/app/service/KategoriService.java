package ade.hendini.app.service;

import ade.hendini.app.model.criteria.KategoriCriteria;
import ade.hendini.app.model.mapper.KategoriMapper;
import ade.hendini.app.model.request.KategoriRequest;
import ade.hendini.app.model.response.KategoriResponse;
import ade.hendini.aws.KategoriS3;
import ade.hendini.aws.utils.ObjectS3Response;
import ade.hendini.entity.Kategori;
import ade.hendini.helpers.PaginationResponse;
import ade.hendini.repository.KategoriRepository;
import ade.hendini.utils.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 27/03/2022 12.24
 */

@Service
public class KategoriService {

    @Autowired
    private KategoriRepository repository;

    public PaginationResponse<List<KategoriResponse>> retrieve(KategoriCriteria criteria) {
        int page = criteria.getPage();
        int size = criteria.getSize();
        Pageable paging = PageRequest.of(page-1,size);
        Page<Kategori> listItems = repository.findAll(new Specification<Kategori>() {
            @Override
            public Predicate toPredicate(Root<Kategori> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(criteria.getCariNamaKategori()!=null){
                    predicates.add(criteriaBuilder.and(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("namaKategori")),"%"+criteria.getCariNamaKategori().toLowerCase()+"%")
                    ));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },paging);
        return this.index(listItems, page, size);
    }

    public KategoriResponse create(KategoriRequest req) {
        var entity = new Kategori();
        repository.save(KategoriMapper.request(req, entity));
        return KategoriMapper.response(entity);
    }

    public KategoriResponse update(String id, KategoriRequest req) {
        var entity = this.findModel(id);
        repository.save(KategoriMapper.request(req, entity));
        return KategoriMapper.response(entity);
    }

    public KategoriResponse getById(String id) {
        return KategoriMapper.response(this.findModel(id));
    }

    public List<KategoriResponse> getAll() {
        List<KategoriResponse> respList = new ArrayList<>();
        List<Kategori> kategoriList = repository.findAll();
        for (Kategori kategori:kategoriList){
            KategoriResponse resp = KategoriMapper.response(kategori);
            respList.add(resp);
        }
        return respList;
    }

    public boolean delete(String id) {
        try {
            repository.delete(this.findModel(id));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public KategoriResponse uploadIconFile(String kategoriId, MultipartFile file) throws Exception {
        var entity = this.findModel(kategoriId);
        if(entity.getIconFile()!=null) {
            KategoriS3.deleteFile(entity.getIconFile());
        }
        ObjectS3Response s3Response = KategoriS3.uploadFile(file);
        entity.setIconFile(s3Response.getKey());
        entity.setIconContentType(s3Response.getContentType());
        String base64Img = Base64.getEncoder().encodeToString(file.getBytes());
        entity.setBase64File(base64Img);
        repository.save(entity);
        return KategoriMapper.response(entity);
    }

    private PaginationResponse<List<KategoriResponse>> index(Page<Kategori> content, int page, int size){
        List<KategoriResponse> listItems = new ArrayList<>();
        for(var entity: content.getContent()){
            listItems.add(KategoriMapper.response(entity));
        }
        PaginationResponse<List<KategoriResponse>> response = new PaginationResponse<>();
        response.setPage(page);
        response.setSize(size);
        response.setTotal_page(content.getTotalPages());
        response.setTotal_items(content.getTotalElements());
        response.setItems(listItems);
        return response;
    }

    private Kategori findModel(String id){
        Kategori entity = KategoriMapper.findById(id);
        if(entity==null){
            throw new CustomNotFoundException("Kategori tidak ditemukan");
        }
        return entity;
    }
}
