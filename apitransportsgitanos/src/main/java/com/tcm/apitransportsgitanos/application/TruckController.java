package com.tcm.apitransportsgitanos.application;

import com.tcm.apitransportsgitanos.application.dto.TruckDTO;
import com.tcm.apitransportsgitanos.domain.Truck;
import com.tcm.apitransportsgitanos.persistance.TruckRepository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class TruckController {

    private TruckRepository truckRepository;

    public TruckController(TruckRepository repository){
        this.truckRepository=repository;
    }

    public TruckDTO createTruck(TruckDTO truckDTO) throws Exception{
        Truck truck = new Truck(truckDTO);
        this.truckRepository.save(truck);
        return new TruckDTO(truck);
    }

    public TruckDTO getTruck(String truckId){
        Truck truck = this.truckRepository.findById(truckId).get();
        return new TruckDTO(truck);
    }

    public List<TruckDTO> getAllTrucks(){
        List<Truck> allTrucks= new ArrayList<>();
        Iterator<Truck> iterator = this.truckRepository.findAll().iterator();
        iterator.forEachRemaining(allTrucks::add);
        return allTrucks.stream().map(x -> new TruckDTO(x)).toList();
    }
    
    public void deleteAllTrucks() {
        truckRepository.deleteAll();
    }

    public void deleteTruckById(String truckId){
        Truck t= truckRepository.findById(truckId).get();
        truckRepository.delete(t);
    }

}
