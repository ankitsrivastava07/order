package ordermicroservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="order-proxy",url="http://localhost:8081/")
public interface OrderProxy {
    @PostMapping("/users/get-name-and-email")
    OrderProxyResponse getFirstNameAndEmail(@RequestParam(name="userId") Long userId);
}
