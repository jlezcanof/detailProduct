package com.inditex.detail.product.controller;

import com.inditex.detail.product.dto.SimilarProducts;
import com.inditex.detail.product.properties.RestProperties;
import com.inditex.detail.product.service.RestApiSimilarservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = RestProperties.DETAIL_PRODUCT_API_SERVICE)
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public final class DetailProductGetController extends AbstractController {

  private final RestApiSimilarservice restApiSimilarService;

  @Autowired
  public DetailProductGetController(final RestApiSimilarservice restApiSimilarService){
    this.restApiSimilarService = restApiSimilarService;
  }


  @GetMapping(value = "{productId}/similar", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SimilarProducts> find(@PathVariable(value = "productId") final String productId) {
    throwBadRequestIfParameterIsBlank(productId);
    throwBadRequestIfParameterHasWhitespaces(productId);

    SimilarProducts similarProducts = restApiSimilarService.obtainSimilarProducts(productId);

    return ResponseEntity.ok(similarProducts);
  }
}
