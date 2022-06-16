package com.inditex.detail.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public final class ProductDetail implements Serializable {

  @JsonProperty(required = true)
  private String id;

  @JsonProperty(required = true)
  private String name;

  @JsonProperty(required = true)
  private Double price;

  @JsonProperty(required = true)
  private boolean availability;

}
