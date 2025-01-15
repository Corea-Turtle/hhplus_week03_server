package kr.hhplus.be.server.domain.product;

import kr.hhplus.be.server.infrastructure.product.ProductJpaRepository;
import kr.hhplus.be.server.interfaces.dto.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    ProductJpaRepository productRepository;

    //1. 상품 정보 ( ID, 이름, 가격, 잔여수량 ) 을 조회.
    //2. 조회시점의 상품별 잔여수량이 정확하면 좋습니다.
    public Product getProductDetail(ProductResponse response){
        Product product = productRepository.findById(response.getProductId())
                .orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다."));
        return product;
    }
}
