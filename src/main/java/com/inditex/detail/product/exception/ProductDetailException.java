package com.inditex.detail.product.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public final class ProductDetailException extends RuntimeException {
  private static final long serialVersionUID = 4251129815918670347L;
  private final HttpStatus status;
  private final String messageError;

  public ProductDetailException(HttpStatus status, String messageError){
    this.status = status;
    this.messageError = messageError;
  }
}
