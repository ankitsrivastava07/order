package ordermicroservice.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String addressLine1;
    private String landMark;
    private Long cityId;
    private String firstName;
    private String lastName;
    private Long stateId;
}
