package ade.hendini.app.controller;

import ade.hendini.app.model.criteria.ProdukCriteria;
import ade.hendini.app.model.request.ProdukRequest;
import ade.hendini.app.model.response.ProdukResponse;
import ade.hendini.app.service.ProdukService;
import ade.hendini.helpers.PaginationResponse;
import ade.hendini.helpers.WebResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */

@Tag(name = "Manajemen Produk",description = "Manajemen Produk")
@RestController
@RequestMapping("/master/produk")
public class ProdukController {

    @Autowired
    private ProdukService service;

    @Operation(summary = "Mengambil data Produk")
    @GetMapping()
    public ResponseEntity<WebResponse<PaginationResponse>> retrieve(
            @Valid ProdukCriteria criteria
    ){
        WebResponse<PaginationResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.retrieve(criteria));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Menambah data Produk")
    @PostMapping()
    public ResponseEntity<WebResponse<ProdukResponse>> create(
            @Valid @RequestBody ProdukRequest body
    ){
        WebResponse<ProdukResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.create(body));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mengubah satu data Produk berdasarkan Id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<WebResponse<ProdukResponse>> update(
            @PathVariable("id") String id,
            @Valid @RequestBody ProdukRequest body
    ){
        WebResponse<ProdukResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.update(id, body));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "mengambil satu data Produk berdasarkan Id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<WebResponse<ProdukResponse>> getById(
            @PathVariable("id") String id
    ){
        WebResponse<ProdukResponse> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.getById(id));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Menghapus satu data Produk berdasarkan Id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<WebResponse<String>> delete(
            @PathVariable("id") String id
    ){
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
}
