package kr.hhplus.be.server.interfaces.dto.product.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProductResponse {

    private Long productId;
    private String productName;
    private int productPrice;
    private int remainQuantity;


}
