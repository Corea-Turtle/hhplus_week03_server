package kr.hhplus.be.server.domain.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private int productPrice;

    private int remainQuantity;

    @Builder
    public Product(String productName, int productPrice, int remainQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.remainQuantity = remainQuantity;
    }
}
