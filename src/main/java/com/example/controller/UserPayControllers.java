package com.example.controller;

import com.example.model.dto.Response.ResponsePage;
import com.example.model.dto.Response.ResponseWrapper;
import com.example.service.impl.UserPayServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@RestController
@CrossOrigin("*")
@RequestMapping("/user-pay")
@Tag(name = "UserPay")
public class UserPayControllers {

    @Autowired
    private UserPayServiceImpl userPayService;

    @GetMapping
    public ResponseEntity<ResponsePage> getAllUserByPrice(@RequestParam Long timeStart,
                                                          @RequestParam Long timeFinal,
                                                          @PageableDefault(size = 3, direction = Sort.Direction.ASC, sort = "name") Pageable page){
        ResponsePage result = userPayService.findAllUserByPriceWithDate(timeStart, timeFinal, page );
        return new ResponseEntity<>(result ,HttpStatus.OK);
    }

    @GetMapping("/use-hql")
    public ResponseEntity<ResponsePage> getAllUserByPriceWithHQL(@RequestParam Long timeStart,
                                                                 @RequestParam Long timeFinal,
                                                                 @PageableDefault(size = 3, direction = Sort.Direction.ASC, sort = "name") Pageable page){
        ResponsePage result = userPayService.findAllUserByPriceWithDateHQL(timeStart, timeFinal, page );
        return new ResponseEntity<>(result ,HttpStatus.OK);
    }
}
