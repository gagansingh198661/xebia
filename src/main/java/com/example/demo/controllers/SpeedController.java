package com.example.demo.controllers;


import com.example.demo.dtos.HumanWordSpeed;
import com.example.demo.services.HumanWordSpeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeedController {

    @Autowired
    HumanWordSpeedService service;

    @PutMapping("/updatespeed/{speed}")
    public ResponseEntity<HumanWordSpeed> update(@PathVariable int speed){
        try{
            HumanWordSpeed hws=service.update(speed);
            return new ResponseEntity<>(hws, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("bad request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
