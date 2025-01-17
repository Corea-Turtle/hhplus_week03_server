package kr.hhplus.be.server.interfaces.dto.product.response;

import lombok.Builder;
import lombok.Getter;


@Getter
public class ProductResponse {

    private Long productId;
    private String productName;
    private int productPrice;
    private int remainQuantity;


}
