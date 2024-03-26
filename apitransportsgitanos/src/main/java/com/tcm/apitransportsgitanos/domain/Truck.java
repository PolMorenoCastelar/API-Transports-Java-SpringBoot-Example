package com.tcm.apitransportsgitanos.domain;

import com.tcm.apitransportsgitanos.application.dto.TruckDTO;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Truck {

    @Id
    private String id= UUID.randomUUID().toString();
    public static final int DRIVER_PRICE_HOUR = 15;
    public static final double GAS_PRICE_LITER = 2;
    private static final double INITIAL_TEMP = -20;
    private static final double MAX_CAPACITY = 200;
    private static final double MIN_CAPACITY = MAX_CAPACITY * 0.3;//30%
    public static final double TRUCK_VELOCITY = 100;
    private static final double LIMIT_TEMP = 0;
    private static final double INCREASE_TEMP_HOUR = 3;
    public static final double MAX_FUEL = 150;
    public static final double LOAD_UNLOAD_PRICE=200;
    public static final double FUEL_CONSUMPTION_HOUR = 20;
    public static final double MAX_KM_FULL_CAPACITY = (MAX_FUEL / FUEL_CONSUMPTION_HOUR)*TRUCK_VELOCITY;
    private double actualTemp;
    private double capacity;

    private double actualFuel;
    private double totalKm;
    private double totalTime;
    private double totalGas;

    @OneToOne
    @JoinColumn(name="route_idRoute")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Route route;

    public Truck(){

    }


    public Truck(TruckDTO truckDTO) throws Exception{
        if(truckDTO==null) throw new Exception();

        checkCapacity(truckDTO);
        this.capacity= truckDTO.getCapacity();

        actualTemp = INITIAL_TEMP;
        actualFuel = MAX_FUEL;
    }

    public void moveTruck(int km){
        totalKm+=km;
        totalTime+=getTotalTime(km);
        totalGas+=getFuelConsumption(km);
    }

    public double getTotalTime(double km){
        return (km/Truck.TRUCK_VELOCITY) + (WareHouse.WAITING_TIME);
    }

    public void checkCapacity(TruckDTO truckDTO)throws Exception{
        if(truckDTO.getCapacity() < MIN_CAPACITY || truckDTO.getCapacity() > MAX_CAPACITY) throw new Exception("Wrong capacity");
    }

    public static boolean temperatureAvailabilityBetweenStops(int kmBetweenStops){
        double finalTime = kmBetweenStops / TRUCK_VELOCITY;
        double finalTemperature = INITIAL_TEMP + (finalTime * INCREASE_TEMP_HOUR);
        return !(finalTemperature > LIMIT_TEMP);
    }

    public String getIdTruck() {
        return id;
    }

    public double getActualTemp() {
        return actualTemp;
    }

    public void increaseTemp(double actualTemp)throws Exception{
        if(actualTemp+INCREASE_TEMP_HOUR>0)throw new Exception("El menjar es dolent ha superat els 0 graus");
        actualTemp=actualTemp + INCREASE_TEMP_HOUR;
    }

    public void setActualTemp(double actualTemp)throws Exception {
        this.actualTemp = actualTemp;
    }

    public String getId() {
        return id;
    }

    public double getActualFuel() {
        return actualFuel;
    }

    public void setActualFuel(double actualFuel) {
        this.actualFuel = actualFuel;
    }

    public double getCostGas(int km){
        return GAS_PRICE_LITER * getFuelConsumption(km);
    }

    public double getLoadUnloadPrice(int numStops){
        return (numStops*LOAD_UNLOAD_PRICE*2)+LOAD_UNLOAD_PRICE*2;
    }
    public double getFuelConsumption(int km){
       return  (km/TRUCK_VELOCITY)*FUEL_CONSUMPTION_HOUR;
    }

    public double getPriceDriver(int duration){
        return (duration*DRIVER_PRICE_HOUR);
    }
    public double getCapacity(){
        return capacity;
    }


}
