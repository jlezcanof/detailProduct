package com.inditex.detail.product.exception.model;

import lombok.Data;

@Data
public final class ProductDetailExceptionResponse {

  private String message;
  private String requestId;

  public ProductDetailExceptionResponse(final String requestId, final String message) {
    this.message = message;
    this.requestId = requestId;
  }

}
