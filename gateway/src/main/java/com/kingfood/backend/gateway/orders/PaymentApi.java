package com.kingfood.backend.gateway.orders;
import com.kingfood.backend.domains.request.PaymentRequest;
import com.kingfood.backend.domains.response.BaseRestResponse;
import com.kingfood.backend.domains.response.PaymentResponse;
import com.kingfood.backend.order.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Payment-Resource"})
@SwaggerDefinition(tags = {
        @Tag(name = "Payment-API-Resource", description = "Write description here")
})
@RestController
@RequestMapping(value = "/api")
public class PaymentApi {

    private final PaymentService paymentService;

    @Autowired
    public PaymentApi(@Qualifier("momoPaymentService") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payments/momo", method = RequestMethod.POST)
    public ResponseEntity<?> payment(@RequestBody PaymentRequest request) {
        try {
            PaymentResponse response = paymentService.payment(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            BaseRestResponse response = BaseRestResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .success(false)
                    .message("An exception occurs when process payment.")
                    .buildResponse();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
