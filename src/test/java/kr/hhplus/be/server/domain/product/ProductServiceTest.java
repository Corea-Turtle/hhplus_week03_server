package kr.hhplus.be.server.domain.product;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.infrastructure.product.ProductJpaRepository;
import kr.hhplus.be.server.interfaces.dto.product.response.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    ProductJpaRepository productRepository;

    @Autowired
    ProductService productService;

    @DisplayName("상품의 아이디를 입력받아 해당 상품의 ID, 이름, 가격, 잔여수량을 보여준다.")
    @Test
    void getProductDetail() {

        //given - 상품과 상품response가 주어짐
        Product product = new Product("상품1", 1000, 10);
        productRepository.save(product);

        ProductResponse response = new ProductResponse();

        //when - 상품 조회
        Product productFound = productService.getProductDetail(response);

        //then
        assertEquals(product.getProductId(), productFound.getProductId());
        assertEquals(product.getProductName(), productFound.getProductName());
        assertEquals(product.getProductPrice(), productFound.getProductPrice());
        assertEquals(product.getRemainQuantity(), productFound.getRemainQuantity());
    }
}