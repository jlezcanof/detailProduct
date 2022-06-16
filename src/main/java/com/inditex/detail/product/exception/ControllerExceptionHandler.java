package com.inditex.detail.product.exception;

import com.inditex.detail.product.exception.model.ProductDetailExceptionResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public final class ControllerExceptionHandler {

  @ExceptionHandler({ProductDetailException.class})
  public ResponseEntity<ProductDetailExceptionResponse> handleProductDetailException(HttpServletRequest req, ProductDetailException exception) {
    log.error("Se ha capturado una excepción com.inditex.detail.product.exception.ProductDetailException: " + exception.getMessage(), exception);

    return new ResponseEntity<>(new ProductDetailExceptionResponse(req.getRequestedSessionId(), exception.getMessage()),
      exception.getStatus());

  }
}
