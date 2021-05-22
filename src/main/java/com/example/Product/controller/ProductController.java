package com.example.Product.controller;


import com.example.Product.model.ProductRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/")
public class ProductController {

    private List<ProductRepo> productDatabase = createDatabase();

    private List<ProductRepo> createDatabase() {
        List<ProductRepo> products = new ArrayList<>();
        products.add(new ProductRepo(13, "kirvis", 100));
        products.add(new ProductRepo(14, "kastuvas", 60));
        products.add(new ProductRepo(15, "plaktukas", 40));
        products.add(new ProductRepo(16, "liniuote", 10));
        products.add(new ProductRepo(17, "kibiras", 6));
        return products;
    }

    @GetMapping("/produktas")  //GET
    public List<ProductRepo> gautiProduktus() {
        return productDatabase;
    }

    @PutMapping("/produktas") //PUT
    public List<ProductRepo>atnaujinkProduktuSarasa(@RequestBody ProductRepo updateProduct) {
        ProductRepo product = productDatabase.stream().filter(newProduct -> updateProduct.getId() == newProduct.getId()).findAny().get();
        productDatabase.remove(product);
        product.setId(updateProduct.getId());
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        productDatabase.add(product);
        return productDatabase;
    }

    @PostMapping("/produktas") //POST
    public ProductRepo pridetProdukta(@RequestBody ProductRepo produktas) {
        productDatabase.add(produktas);
        return produktas;
    }

    @DeleteMapping("/produktas/{id}")
    public void trinkProdukta(@PathVariable int id) {
        for (ProductRepo produktas : productDatabase) {
            if (produktas.getId() == id) {
                productDatabase.remove(produktas);
                System.out.println("Produktas " + produktas + " pasalintas");
            } else System.out.println("produktas nerastas");
        }
    }

}
