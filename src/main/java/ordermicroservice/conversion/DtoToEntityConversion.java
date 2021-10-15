package ordermicroservice.conversion;
import freemarker.template.Configuration;
import ordermicroservice.address_dao.entity.AddressEntity;
import ordermicroservice.dto.OrderRequest;
import ordermicroservice.email.Mail;
import ordermicroservice.locality.dao.entity.CityEntity;
import ordermicroservice.locality.dao.entity.StateEntity;
import ordermicroservice.order_dao.entity.OrderDetailEntity;
import ordermicroservice.order_dao.entity.OrderEntity;
import ordermicroservice.order_dao.repository.OrderDetailRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.*;

@Component
public class DtoToEntityConversion {

    private ModelMapper mapper = new ModelMapper();

    @Autowired
   private OrderDetailRepository orderDetailRepository;

     @Autowired
    private Configuration fmConfiguration;

    public <D> D entityToDtoConversionCreateOrder(Object source,Class<D> destination){
         return mapper.map(source,destination);
    }

    public OrderDetailEntity orderRequestToOderDetailEntity(OrderRequest orderRequest){
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();

        StateEntity stateEntity = new StateEntity();
        stateEntity.setId(orderRequest.getAddressDto().getStateId());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(orderRequest.getAddressDto().getCityId());

        AddressEntity address= new AddressEntity();
        address.setCityEntity(cityEntity);
        address.setStreet1(orderRequest.getAddressDto().getAddressLine1());
        address.setFirstName(orderRequest.getAddressDto().getFirstName());
        address.setLastName(orderRequest.getAddressDto().getLastName());
        address.setLandMark(orderRequest.getAddressDto().getLandMark());

        List<OrderEntity> orders = new ArrayList<>();
         orderRequest.getOrderDto().stream().forEach(orderDto-> {
         OrderEntity entity = new OrderEntity();
         entity.setProductId(orderDto.getProductId());
         entity.setPrice(orderDto.getPrice());
         entity.setSubCategoryId(orderDto.getSubCategoryId());
         orders.add(entity);
        });

         String randomId=randomNumber();
        orderDetailEntity.setRandomId(randomId);
        orderDetailEntity.setOrderProducts(orders);
        orderDetailEntity.setOrderStatus("Processing");
        orderDetailEntity.setOrderType("Cash on delivery");
        orderDetailEntity.setGrandTotal(orderRequest.getGrandTotal());
        orderDetailEntity.setUserId(orderRequest.getUserId());
        orderDetailEntity.setAddress(address);
        return orderDetailEntity;
    }

    public String randomNumber() {
        String random = RandomStringUtils.random(20, false, true);
        StringBuilder sb = new StringBuilder(random);
        String sub1=random.substring(0,3);
        sb.insert(3,'-');
        sb.insert((sub1.length()+1+8),'-');
        String numberFormat=sb.toString();
        System.out.println(numberFormat);
        return numberFormat;
    }

}
