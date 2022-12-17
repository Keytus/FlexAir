package com.controller;

import com.model.Message;
import com.model.entity.Promocode;
import com.service.PromocodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/promocode")
public class PromocodeController {
    private PromocodeService promocodeService;

    public PromocodeController(PromocodeService promocodeService) {
        this.promocodeService = promocodeService;
    }

    @GetMapping("/promocodes")
    public List<Promocode> getCustomers(){
        return promocodeService.getPromocodes();
    }
    @GetMapping("/is_exist")
    public ResponseEntity<Message> isPromocodeExistByValue(@RequestParam String value){
        if (promocodeService.isExistPromocodeByValue(value)){
            return new ResponseEntity<>(new Message("success", null), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Message("fail", null),HttpStatus.NOT_FOUND);
        }
    }
}
