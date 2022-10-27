package ade.hendini.app.controller;

import ade.hendini.app.model.response.ProdukFotoResponse;
import ade.hendini.app.service.ProdukFotoService;
import ade.hendini.helpers.WebResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 */
@Tag(name = "Manajemen Foto Produk",description = "Manajemen Foto Produk")
@RestController
public class FotoProdukController {

    @Autowired
    private ProdukFotoService service;

    @Operation(summary = "Mengambil Foto Produk")
    @GetMapping(path = "/produk/{produk_id}/foto")
    public ResponseEntity<WebResponse<List<ProdukFotoResponse>>> getFotoProduk(
            @PathVariable("produk_id") String produkId
    ){
        WebResponse<List<ProdukFotoResponse>> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.getFotoProduk(produkId));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Upload Foto Produk")
    @PostMapping(path = "/produk/{id}/foto",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<WebResponse<List<ProdukFotoResponse>>> uploadProdukFoto(
            @PathVariable("id") String produkId,
            @RequestParam("foto_produk") MultipartFile[] fotoProduk
    ) throws Exception {
        WebResponse<List<ProdukFotoResponse>> response = new WebResponse<>();
        response.setStatus(true);
        response.setPayload(service.uploadFotoProduk(produkId, fotoProduk));
        return ResponseEntity.ok(response);
    }
}
