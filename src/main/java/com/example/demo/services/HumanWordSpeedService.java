package com.example.demo.services;

import com.example.demo.dtos.HumanWordSpeed;
import com.example.demo.repositories.HumanWordSpeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanWordSpeedService {

    @Autowired
    HumanWordSpeedRepository repository;

    public HumanWordSpeed update(int speed){
        List<HumanWordSpeed> hwsList=repository.findAll();
        HumanWordSpeed hw=null;
        if(hwsList.size()!=0) {
            hw = hwsList.get(0);
        }
        if(hw==null){
            hw=new HumanWordSpeed(speed);
        }else {
            hw.setWordNumberMinute(speed);
        }
        return repository.save(hw);
    }

    public HumanWordSpeed get(){
        List<HumanWordSpeed> hwsList=repository.findAll();
        HumanWordSpeed hw=null;
        if(hwsList.size()!=0) {
            hw = hwsList.get(0);
        }
        if(hw==null){
            hw=new HumanWordSpeed(20);
            return repository.save(hw);
        }else {
            return hw;
        }

    }
}
