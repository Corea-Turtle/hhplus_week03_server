package kr.hhplus.be.server.interfaces.dto.coupon.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.hhplus.be.server.domain.coupon.CouponType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Setter
@Getter
public class CouponCreateRequest {
    @Enumerated(EnumType.STRING)
    private CouponType type;
    private double valueOfType;
    private int remainQuantity;
    private SimpleDateFormat expiredDate = null;
    private SimpleDateFormat createDate;

}
