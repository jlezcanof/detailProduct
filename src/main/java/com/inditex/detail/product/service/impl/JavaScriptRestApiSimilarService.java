package com.inditex.detail.product.service.impl;

import com.inditex.detail.product.dto.ProductDetail;
import com.inditex.detail.product.dto.SimilarProducts;
import com.inditex.detail.product.service.RestApiSimilarservice;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public final class JavaScriptRestApiSimilarService implements RestApiSimilarservice {


  @Override
  public SimilarProducts obtainSimilarProducts(String idProductDetail) {

    Set<String> listIdsProducts = new HashSet<>();
    listIdsProducts.add(idProductDetail);

    List<String> idsSimilar = obtainListSimilarProducts(idProductDetail);
    listIdsProducts.addAll(idsSimilar);

    SimilarProducts similarProducts = new SimilarProducts();

    listIdsProducts.forEach(idProduct -> {
      ProductDetail detail = this.obtainDetailProduct(idProduct);
      similarProducts.add(detail);
    });

    return similarProducts;
  }

  private List<String> obtainListSimilarProducts(String idProductDetail) {

    String urlSimilarProducts =
      String.format("http://localhost:3001/product/%s/similarids", idProductDetail);

    ResponseEntity<Object> responseEntity = this.invokeOtherApi(urlSimilarProducts, String[].class);

    if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
      log.error("error {}", responseEntity.getStatusCode());
      return new ArrayList<>();
    }
    String[] body = (String[]) responseEntity.getBody();

    List<String> listSimilarProducts = new ArrayList<>();

    listSimilarProducts.addAll(Arrays.asList(body));

    return listSimilarProducts;
  }

  private ResponseEntity<Object> invokeOtherApi(String url, Class aClass){
    WebClient client = WebClient.create(url);

    try{
      Mono<Object> data = client.get().retrieve().bodyToMono(aClass);
      return new ResponseEntity<>(data.block(), HttpStatus.OK);
    } catch (WebClientResponseException ex){
      log.error("An error has occurred while obtain data {}", ex.getMessage());
      return ResponseEntity.status(ex.getStatusCode()).build();
    }
  }

  private ProductDetail obtainDetailProduct(String idProductDetail) {

    log.info(String.format("idProductDetail: %s", idProductDetail));

    String urlDetailProduct = String.format("http://localhost:3001/product/%s", idProductDetail);
    log.info(String.format("url: %s", urlDetailProduct));

    ResponseEntity<Object> responseEntity = this.invokeOtherApi(urlDetailProduct, Object.class);

    if (!HttpStatus.OK.equals(responseEntity.getStatusCode())){
      return new ProductDetail();
    }
    LinkedHashMap<String, ? extends Serializable> responseEntityBody =
      (LinkedHashMap<String, ? extends Serializable>) responseEntity.getBody();

    return obtainDataProductDetail(responseEntityBody);

  }

  private ProductDetail obtainDataProductDetail(
    LinkedHashMap<String, ? extends Serializable> responseEntityBody) {

    String idProduct = (String)responseEntityBody.get("id");
    String nameProduct = (String)responseEntityBody.get("name");
    Double priceProduct = (Double)responseEntityBody.get("price");
    Boolean availabilityProduct = (Boolean)responseEntityBody.get("availability");

    return new ProductDetail(idProduct, nameProduct,priceProduct, availabilityProduct);
  }

}



