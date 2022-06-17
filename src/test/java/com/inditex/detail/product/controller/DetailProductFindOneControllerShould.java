package com.inditex.detail.product.controller;

import com.inditex.detail.product.ApplicationConfigurationTest;
import com.inditex.detail.product.DetailProductTestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {ApplicationConfigurationTest.class})
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DetailProductFindOneControllerShould extends DetailProductTestCase {

  @Test
  void obtain_all_similar_products_for_one_product() throws Exception {
    Mockito.when(this.restApiSimilarservice.obtainSimilarProducts(Mockito.any()))
      .thenReturn(this.mockSimilarProducts);

    String idProduct = "1";
    String url = String.format("/product/%s/similar", idProduct);
    String expectedResponse = "products";
    assertResponse(url, 200, expectedResponse);
  }

  @Test
  void error_when_product_id_is_empty() throws Exception{
    String idProduct = " ";
    String url = String.format("/product/%s/similar", idProduct);
    assertBadRequest("GET",  url,  400);
  }

  @Test
  void error_when_product_id_has_whitespaces() throws Exception{
    String idProduct = "    1      ";
    String url = String.format("/product/%s/similar", idProduct);
    assertBadRequest("GET",  url,  400);
  }

}
