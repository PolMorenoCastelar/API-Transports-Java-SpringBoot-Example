package com.tcm.apitransportsgitanos.domain;


import com.tcm.apitransportsgitanos.application.RouteController;
import com.tcm.apitransportsgitanos.application.dto.RouteDTO;
import com.tcm.apitransportsgitanos.application.dto.TruckDTO;
import com.tcm.apitransportsgitanos.application.dto.WareHouseDTO;
import com.tcm.apitransportsgitanos.persistance.WareHouseRepository;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Entity
public class Route {

    @Id
    private String idRoute= UUID.randomUUID().toString();
    private double transportCost;

    @JoinColumn(name = "warehouse_origin")
    @ManyToOne
    private WareHouse origin;

    @JoinColumn(name = "warehouse_destination")
    @ManyToOne
    private WareHouse destination;

    @OneToOne
    @JoinColumn(name="truck_id")
    private Truck truck;

    @ManyToMany
    private static final List<WareHouse> warehouses= new ArrayList<>();


    private int duration;
    private static String stops [] = new String[5];



    public Route(){

    }


    public Route (RouteDTO route,List<WareHouse> allWarehouse)throws Exception{
        if(route.getDestination()==route.getOrigin()||route==null) throw new Exception();
        this.truck=new Truck(route.getTruck());

        this.origin=getWareHouseByName(route.getOrigin().getCityName(),allWarehouse);
        this.destination=getWareHouseByName(route.getDestination().getCityName(),allWarehouse);
        stops[0]=origin.getCityName();
        origin.backBestRoute(1, stops, destination.getCityName());
        stops=getStops();
        this.moveTruck(stops);
        this.duration=routeTime();
        this.transportCost=getRouteCost();
    }
    public WareHouse getWareHouseByName(String name,List<WareHouse> allWarehouses)throws Exception{
        for(WareHouse x:allWarehouses){
            if(x.getCityName().equals(name)){
                return x;
            }
        }
        throw new Exception("NOTFOUND");
    }




    public void moveTruck(String [] array){
        for(int i=0;i<array.length && array[i+1]!=null;i++){
            String origin=array[i];
            String destination=array[i+1];

            this.truck.moveTruck(WareHouse.distanceBetweenWareHouses(origin,destination));

        }
    }

    public int routeTime(){
        return (int)(origin.getKmBest()/Truck.TRUCK_VELOCITY) + (origin.getNumStopsBest()*WareHouse.WAITING_TIME);
    }

    public String[] getStops(){
        return origin.getStops();
    }

    public double getRouteCost(){
        return truck.getCostGas(origin.getKmBest())+(truck.getLoadUnloadPrice(getOrigin().getNumStopsBest()))+truck.getPriceDriver(duration);
    }

    public String getIdRoute() {
        return idRoute;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public WareHouse getOrigin() {
        return origin;
    }

    public WareHouse getDestination() {
        return destination;
    }

    public int getDuration() {
        return duration;
    }
    public Truck getTruck(){ return truck;}

}
