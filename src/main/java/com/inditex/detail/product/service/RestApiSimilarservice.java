package com.inditex.detail.product.service;

import com.inditex.detail.product.dto.SimilarProducts;

public interface RestApiSimilarservice {

  public SimilarProducts obtainSimilarProducts(String idProductDetail);
}
