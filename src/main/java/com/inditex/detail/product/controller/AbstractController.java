package com.inditex.detail.product.controller;

import com.inditex.detail.product.exception.ProductDetailException;
import org.springframework.http.HttpStatus;

public abstract class AbstractController {

  protected void throwBadRequestIfParameterIsBlank(String productId) {
    if (org.apache.commons.lang3.StringUtils.isBlank(productId)) {
      throw new ProductDetailException(HttpStatus.BAD_REQUEST, "Field productId is empty");
    }
  }

  protected void throwBadRequestIfParameterHasWhitespaces(String productId) {
    if (org.apache.commons.lang3.StringUtils.containsWhitespace(productId)) {
      throw new ProductDetailException(HttpStatus.BAD_REQUEST, "Field productId has whitespaces");
    }
  }




  private static String ltrim(String str) {
    return str.replaceAll("^\\s+", "EMPTY_STRING");
  }

  private static String rtrim(String str) {
    return str.replaceAll("\\s+$", "EMPTY_STRING");
  }
}
