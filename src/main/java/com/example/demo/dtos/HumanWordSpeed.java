package com.example.demo.dtos;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="human_word_speed")
public class HumanWordSpeed {

    public HumanWordSpeed(){

    }
    public HumanWordSpeed(int speed){
        wordNumberMinute=speed;
    }
    public int getWordNumberMinute() {
        return wordNumberMinute;
    }

    public void setWordNumberMinute(int wordNumberMinute) {
        this.wordNumberMinute = wordNumberMinute;
    }

    @NotNull
    private int wordNumberMinute;

    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
