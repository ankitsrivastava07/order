package ordermicroservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {

    private Long userId;
    private List<OrderDto> orderDto;
    private Double grandTotal;
    private String accessToken;
    private String orderType;
    private AddressDto addressDto;
}
