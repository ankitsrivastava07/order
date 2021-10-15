package ordermicroservice.controller;

import ordermicroservice.dto.OrderRequest;
import ordermicroservice.dto.OrderResponseDto;
import ordermicroservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/save-order")
    public ResponseEntity<?> createOrder(@RequestHeader(value = "Authentication",required = false)String authentication,@RequestBody OrderRequest orderRequest){
        OrderResponseDto createOrderResponse= orderService.saveOrder(authentication,orderRequest);
        return new ResponseEntity<>(createOrderResponse, HttpStatus.valueOf(createOrderResponse.getHttpStatus()));
    }

    @GetMapping("get-all-records")
    public ResponseEntity<?> getAllRecords(@RequestParam("userId") Long userId){
        return new ResponseEntity<>(orderService.getAllOrders(userId),HttpStatus.OK);
    }
}
