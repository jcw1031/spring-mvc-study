package com.woopaca.springmvc.basic.response;

import com.woopaca.springmvc.basic.HelloData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Controller
@RestController
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("OK");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "OK";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBoyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("woopaca");
        helloData.setAge(24);

        return ResponseEntity.ok().body(helloData);
    }

//    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/response-body-json-v2")
    public HelloData responseBoyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("woopaca");
        helloData.setAge(24);

        return helloData;
    }
}
