package com.tcm.apitransportsgitanos.application.dto;

import com.tcm.apitransportsgitanos.domain.*;


public class RouteDTO {
    private String idRoute;
    private WareHouseDTO origin;
    private WareHouseDTO destination;
    private TruckDTO truck;
    private double transportCost;
    private int duration;
    //
    private String idOg;
    private String idDest;
    private String idTruck;

    public RouteDTO(){

    }

    public RouteDTO (WareHouseDTO origin, WareHouseDTO destination, TruckDTO truckDTO){
        this.origin= origin;
        this.destination=destination;
        this.truck=truckDTO;
    }



    public RouteDTO(Route route){
        this.idRoute=route.getIdRoute();
        this.origin=new WareHouseDTO(route.getOrigin());
        this.destination=new WareHouseDTO(route.getDestination());
        this.truck=new TruckDTO(route.getTruck());
        this.duration=route.getDuration();
        this.transportCost=route.getTransportCost();

        this.idDest=route.getDestination().getIdWarehouse();
        this.idOg=route.getOrigin().getIdWarehouse();
        this.idTruck=route.getTruck().getIdTruck();
    }

    public RouteDTO(Route route, Truck truck){
        this.idRoute=route.getIdRoute();
        this.origin=new WareHouseDTO(route.getOrigin());
        this.destination=new WareHouseDTO(route.getDestination());
        this.truck=new TruckDTO(route.getTruck());
        this.duration=route.getDuration();
        this.transportCost=route.getTransportCost();
    }

    public double getTransportCost(){
        return transportCost;
    }

    public int getDuration(){return duration;}

    public String getIdRoute() {
        return idRoute;
    }

    public WareHouseDTO getOrigin() {
        return origin;
    }

    public WareHouseDTO getDestination() {
        return destination;
    }

    public TruckDTO getTruck(){ return truck; }
}
