package com.tcm.apitransportsgitanos.api;

import com.tcm.apitransportsgitanos.application.TruckController;

import com.tcm.apitransportsgitanos.application.dto.TruckDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TruckRestController {

    private TruckController truckController;

    public TruckRestController(TruckController truckController) {
        this.truckController = truckController;
    }

    @PostMapping("/trucks")
    public TruckDTO createTruck(@RequestBody TruckDTO truckDTO) throws Exception{
        return this.truckController.createTruck(truckDTO);
    }

    @GetMapping("/trucks/{truckId}")
    public TruckDTO getTruck(@PathVariable String truckId){
        return truckController.getTruck(truckId);
    }

    @GetMapping("/trucks")
    public List<TruckDTO> getAllTrucks(){
        return truckController.getAllTrucks();
    }

    @DeleteMapping("/trucks")
    public void deleteAllTrucks(){
        truckController.deleteAllTrucks();

    }

    @DeleteMapping("/trucks/{truckId}")
    public void deleteTruckById(@PathVariable String truckId){
        truckController.deleteTruckById(truckId);
    }


}
