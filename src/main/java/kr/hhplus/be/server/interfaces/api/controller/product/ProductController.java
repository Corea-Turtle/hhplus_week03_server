package kr.hhplus.be.server.interfaces.api.controller.product;

import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.product.ProductService;
import kr.hhplus.be.server.interfaces.dto.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/detail/{productId}")
    public Product getProductDetail(@RequestBody ProductResponse response){
        return productService.getProductDetail(response);
    }

}
