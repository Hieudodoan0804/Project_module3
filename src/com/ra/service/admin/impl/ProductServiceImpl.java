package com.ra.service.admin.impl;

import com.ra.entity.Product;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.admin.ProductService;


public class ProductServiceImpl implements ProductService {
    private Repository<Product,String> productRepository = new RepositoryImpl<>();

    @Override
    public Product findId(String productId) {
        Product product = productRepository.findId(productId,Product.class);
        if (product.getProductId().equals(productId)){
            return product;
        }
        return null;
    }

    @Override
    public String findName(String productName) {
        Product p = productRepository.findName(productName, Product.class);
        return p.getProductName();
    }

}
