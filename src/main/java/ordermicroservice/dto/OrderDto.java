package ordermicroservice.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long productId;
    private Long subCategoryId;
    private Double price;
    private Long discountId;
}
