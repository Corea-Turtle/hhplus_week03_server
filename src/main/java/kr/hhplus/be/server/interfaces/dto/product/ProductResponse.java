package kr.hhplus.be.server.interfaces.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ProductResponse {

    private Long productId;
    private String productName;
    private int productPrice;
    private int remainQuantity;

    @Builder
    public ProductResponse(Long productId) {

    }
}
