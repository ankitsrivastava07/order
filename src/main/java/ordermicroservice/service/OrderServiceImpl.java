package ordermicroservice.service;

import feign.RetryableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ordermicroservice.constant.ConstantResponse;
import ordermicroservice.conversion.DtoToEntityConversion;
import ordermicroservice.dao.OrderDao;
import ordermicroservice.dto.OrderRequest;
import ordermicroservice.dto.OrderResponseDto;
import ordermicroservice.email.Mail;
import ordermicroservice.email.service.EmailService;
import ordermicroservice.jwtutil.JwtTokenUtil;
import ordermicroservice.order_dao.entity.OrderDetailEntity;
import ordermicroservice.proxy.OrderProxy;
import ordermicroservice.proxy.OrderProxyResponse;
import ordermicroservice.response.server_down.OrderServiceDownResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DtoToEntityConversion dtoToEntityConversion;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private OrderProxy orderProxy;

    @Autowired private EmailService emailService;

    @Override
    @Transactional
    @CircuitBreaker(name="",fallbackMethod="saveOrderFallback")
    public OrderResponseDto saveOrder(String authencation,OrderRequest orderRequest) {

        OrderDetailEntity orderDetailEntity=dtoToEntityConversion.orderRequestToOderDetailEntity(orderRequest);
        orderDetailEntity= orderDao.saveOrder(orderDetailEntity);
        String newAccessToken="";
        OrderProxyResponse orderProxyResponse = orderProxy.getFirstNameAndEmail(orderRequest.getUserId());
        Mail mail=emailService.getMail(orderProxyResponse.getFirstName(),orderProxyResponse.getEmail(),"#"+orderDetailEntity.getRandomId());
        emailService.sendMail(mail);
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId("#"+orderDetailEntity.getRandomId());
        orderResponseDto.setOrderStatus("Success");
        orderResponseDto.setStatus(Boolean.TRUE);
        orderResponseDto.setMessage("Thank for your order with us. We have saint you a confirmation email");
        //orderResponseDto.setAccessToken(newAccessToken);
        return orderResponseDto;
    }

    public OrderResponseDto saveOrderFallback(String authencation, OrderRequest orderRequest, RetryableException exception) {
        OrderResponseDto orderServiceDownResponse = new OrderResponseDto();
        orderServiceDownResponse.setStatus(Boolean.FALSE);
        orderServiceDownResponse.setMessage(ConstantResponse.SERVER_DOWN);
        orderServiceDownResponse.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return orderServiceDownResponse;
    }

    @Override
    public List<OrderDetailEntity> getAllOrders(Long userId) {
        return orderDao.getAllOrders(userId);
    }

}
