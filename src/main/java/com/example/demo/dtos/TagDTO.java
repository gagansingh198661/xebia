package com.example.demo.dtos;

public class TagDTO {
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getOccurance() {
        return occurance;
    }

    public void setOccurance(int occurance) {
        this.occurance = occurance;
    }

    private String tag;
    private int occurance;

    public  TagDTO(String tag,int freq){
        this.tag=tag;
        occurance=freq;
    }

    public TagDTO(){

    }
}
