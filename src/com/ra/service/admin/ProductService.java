package com.ra.service.admin;

import com.ra.entity.Product;

public interface ProductService {
    Product findId(String productId);
    String findName(String productName);
}
