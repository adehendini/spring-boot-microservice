package ade.hendini.app.controller;

import ade.hendini.app.model.criteria.KategoriCriteria;
import ade.hendini.app.model.request.KategoriRequest;
import ade.hendini.app.model.response.KategoriResponse;
import ade.hendini.app.service.KategoriService;
import ade.hendini.helpers.PaginationResponse;
import ade.hendini.helpers.WebResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 12.32
 */

@Tag(name = "Kategori", description = "Manajemen Kategori")
@RestController
@RequestMapping("/master/kategori")
public class KategoriController {

    @Autowired
    private KategoriService service;

    @Operation(summary = "Mengambil data Kategori")
    @GetMapping
    public ResponseEntity<WebResponse<PaginationResponse>> retrieve(
            @Valid KategoriCriteria criteria
    ){
        WebResponse<PaginationResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.retrieve(criteria));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Menyimpan data Kategori")
    @PostMapping
    public ResponseEntity<WebResponse<KategoriResponse>> create(@Valid @RequestBody KategoriRequest body){
        WebResponse<KategoriResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.create(body));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mengubah data Kategori")
    @PutMapping(path = "/{id}")
    public ResponseEntity<WebResponse<KategoriResponse>> update(
            @PathVariable("id") String id,
            @Valid @RequestBody KategoriRequest body
    ){
        WebResponse<KategoriResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.update(id, body));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Melihat Detail Kategori")
    @GetMapping(path = "/{id}")
    public ResponseEntity<WebResponse<KategoriResponse>> findOneById(@PathVariable("id") String id){
        WebResponse<KategoriResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.getById(id));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Menghapus data Kategori")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<WebResponse<String>> delete(@PathVariable("id") String id){
        WebResponse<String> response = new WebResponse<>();
        if(service.delete(id)){
            response.setStatus(true);
            response.setPayload("Data Berhasil dihapus");
        }else{
            response.setStatus(false);
            response.setPayload("Data Gagal dihapus");
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Melihat semua data Kategori")
    @GetMapping(path = "/list")
    public ResponseEntity<WebResponse<List<KategoriResponse>>> getAll(){
        WebResponse<List<KategoriResponse>> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.getAll());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Upload Icon File Kategori")
    @PostMapping(path = "/upload",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<WebResponse<KategoriResponse>> uploadFileIconKategori(
            @RequestPart(value = "id") String kategoriId,
            @RequestPart(value = "file") MultipartFile file
    ) throws Exception {
        WebResponse<KategoriResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.uploadIconFile(kategoriId, file));
        return ResponseEntity.ok(response);
    }
}
