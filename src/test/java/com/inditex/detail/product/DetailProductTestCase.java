package com.inditex.detail.product;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditex.detail.product.dto.ProductDetail;
import com.inditex.detail.product.dto.SimilarProducts;
import com.inditex.detail.product.service.RestApiSimilarservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DetailProductApplication.class)
@AutoConfigureMockMvc
public abstract class DetailProductTestCase {

  @Autowired
  public MockMvc mockMvc;

  public SimilarProducts mockSimilarProducts;

  @MockBean
  public RestApiSimilarservice restApiSimilarservice;

  @BeforeEach
  void setup(){
    mockSimilarProducts = new SimilarProducts();
    mockSimilarProducts.add(new ProductDetail("1000", "Coat", 89.99, true));
  }

  protected void assertResponse(
    String endpoint,
    Integer expectedStatusCode,
    String expectedResponseContains) throws Exception {

    mockMvc
      .perform(get(endpoint))
      .andExpect(status().is(expectedStatusCode))
      .andExpect(content().string(Matchers.containsString(expectedResponseContains)));
  }

  protected void assertBadRequest(
    String method,
    String endpoint,
    Integer expectedStatusCode
                                 ) throws Exception {
    mockMvc
      .perform(request(
        HttpMethod.valueOf(method), endpoint).contentType(APPLICATION_JSON))
      .andExpect(status().is(expectedStatusCode));
  }

}
