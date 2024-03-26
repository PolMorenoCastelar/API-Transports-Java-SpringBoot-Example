package com.tcm.apitransportsgitanos.domain;

import com.tcm.apitransportsgitanos.api.WareHouseRestController;
import com.tcm.apitransportsgitanos.application.dto.WareHouseDTO;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@Entity
public class WareHouse {

    @Id
    private String idWarehouse = UUID.randomUUID().toString();

    public static final int WAITING_TIME=5;
    private String cityName;

    private static final HashMap <String, Integer> destinationTable =new HashMap <String, Integer>();

    @ManyToMany
    @JoinColumn(name="route_idRoute")
    private List<Route> route= new ArrayList<>();


    public WareHouse(){

    }

    public WareHouse(WareHouseDTO wareHouseDTO) throws Exception{
        if(wareHouseDTO == null) throw new Exception();

        this.cityName = wareHouseDTO.getCityName();
    }

    public static int distanceBetweenWareHouses(String actualStop, String nextStop){
        switch(actualStop){
            case "Barcelona": return destinationTable.get(nextStop);
            case "Vigo": return destinationTable.get(nextStop);
            case "Sevilla": return destinationTable.get(nextStop);
            case "Valencia": return destinationTable.get(nextStop);
            case "Madrid": return destinationTable.get(nextStop);
        }
        return 0;
    }


    public void createTable(String cityName){
        destinationTable.clear();
        switch(cityName) {
            case "Barcelona":
                initDestinationBarcelona();
                break;
            case "Vigo":
                initDestinationVigo();
                break;
            case "Sevilla":
                initDestinationSevilla();
                break;
            case "Valencia":
                initDestinationValencia();
                break;
            case "Madrid":
                initDestinationMadrid();
        }
    }

    public int getKmBest(){
        return kmBest;
    }

    public int getNumStopsBest(){
        return numStopsBest;
    }
    public String[] getStops(){return stops;}
    private boolean isAcceptable(String actualStop, String nextStop){
            return  (distanceBetweenWareHouses(actualStop, nextStop) <= Truck.MAX_KM_FULL_CAPACITY) && (Truck.temperatureAvailabilityBetweenStops(distanceBetweenWareHouses(actualStop, nextStop)));

    }

    private boolean isSolution(String lastStop,String destination){
        return(lastStop.equals(destination));

    }

    private boolean bestSolution(){
        if((numStopsActual <= numStopsBest)){
            return true;
        }
        else if ((kmActual <= kmBest)){
            return true;
        }
        return false;
    }

    private int kmBest = 1000000;
    private int numStopsBest =100;
    private int kmActual = 0;
    private int numStopsActual =0;
    private static String stops[]=new String[5];

    public void backBestRoute(int k, String[] solution, String destination){

        createTable(solution[k-1]);
        for(String i : new HashSet<>(destinationTable.keySet())){
            solution[k] = i;
            numStopsActual++;
            kmActual += distanceBetweenWareHouses(solution[k-1], solution[k]);
            if(!notExists(solution,i) && isAcceptable(solution[k-1],solution[k])){ //Acceptable
                if(isSolution(solution[k],destination)){//Is solution?
                    if(bestSolution()){
                        setBestSolution(solution);
                    }
                }else
                    backBestRoute(k+1,solution,destination);
            }//undo
            createTable(solution[k-1]);
            kmActual =kmActual- distanceBetweenWareHouses(solution[k-1], solution[k]);
            solution[k]=null;
            numStopsActual--;
        }
    }

    public void setBestSolution(String[] solution){
        kmBest = kmActual;
        numStopsBest= numStopsActual;
        stops=new String[5];
        cloneSolution(stops,solution);
    }

    public void cloneSolution(String[]stops, String[]solution){
        for(int n=0;n<solution.length;n++){
            if(solution[n]!=null) {
                stops[n] = solution[n].toString();
            }
        }
    }
    public boolean notExists(String [] solution,String cityName){
        int counter=0;
        for (String x :solution){
            if(x!=null) {
                if (x.compareTo(cityName) == 0) counter++;
            }
        }
        return counter>=2;
    }

    public void initDestinationBarcelona(){
        destinationTable.put("Vigo",1200);
        destinationTable.put("Sevilla",800);
        destinationTable.put("Valencia",400);
        destinationTable.put("Madrid",700);

    }

    public void initDestinationVigo(){
        destinationTable.put("Barcelona",1200);
        destinationTable.put("Sevilla",600);
        destinationTable.put("Valencia",800);
        destinationTable.put("Madrid",500);

    }

    public void initDestinationSevilla(){
        destinationTable.put("Barcelona",800);
        destinationTable.put("Vigo",600);
        destinationTable.put("Valencia",400);
        destinationTable.put("Madrid",500);
    }

    public void initDestinationValencia(){
        destinationTable.put("Barcelona",400);
        destinationTable.put("Vigo",800);
        destinationTable.put("Sevilla",400);
        destinationTable.put("Madrid",300);
    }

    public void initDestinationMadrid(){
        destinationTable.put("Barcelona",700);
        destinationTable.put("Vigo",500);
        destinationTable.put("Sevilla",500);
        destinationTable.put("Valencia",300);

    }

    public String getIdWarehouse(){return idWarehouse;}

    public String getCityName(){return cityName;}



}
