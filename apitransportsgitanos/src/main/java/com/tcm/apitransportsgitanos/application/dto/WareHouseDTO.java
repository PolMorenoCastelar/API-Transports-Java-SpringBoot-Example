package com.tcm.apitransportsgitanos.application.dto;

import com.tcm.apitransportsgitanos.domain.WareHouse;

public class WareHouseDTO {
    private String cityName;
    private String idWareHouse;

    public WareHouseDTO(){

    }

    public WareHouseDTO(String cityName){
        this.cityName = cityName;
    }

    public WareHouseDTO(WareHouse wareHouse){
        this.cityName = wareHouse.getCityName();
        this.idWareHouse = wareHouse.getIdWarehouse();

    }

    public String getCityName(){
        return cityName;
    }

    public String getIdWareHouse(){
        return idWareHouse;
    }



}
