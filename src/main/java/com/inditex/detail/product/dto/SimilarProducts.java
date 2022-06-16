package com.inditex.detail.product.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

public final class SimilarProducts implements Serializable {

  @Getter(AccessLevel.PUBLIC)
  private Set<ProductDetail> products = new HashSet<>();

  public void add(ProductDetail productDetail){
    products.add(productDetail);
  }

  public void remove(ProductDetail productDetail){
    products.remove(productDetail);
  }


}
