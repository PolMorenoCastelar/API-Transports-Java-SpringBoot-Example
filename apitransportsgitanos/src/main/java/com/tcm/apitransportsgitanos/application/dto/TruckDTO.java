package com.tcm.apitransportsgitanos.application.dto;
import com.tcm.apitransportsgitanos.domain.*;

public class TruckDTO {

    private String idTruck;
    private double actualFuel;
    private double capacity;
    private double actualTemp;

    public TruckDTO(){

    }

    public TruckDTO(double capacity){
        this.capacity=capacity;
    }

    public TruckDTO(Truck truck){
        this.idTruck=truck.getIdTruck();
        this.capacity=truck.getCapacity();
        this.actualFuel=truck.getActualFuel();
        this.actualTemp=truck.getActualTemp();
    }

    public double getCapacity(){return capacity;}
    public String getIdTruck(){return idTruck;}
    public double getActualFuel(){return actualFuel;}
    public double getActualTemp(){return actualTemp;}
}
