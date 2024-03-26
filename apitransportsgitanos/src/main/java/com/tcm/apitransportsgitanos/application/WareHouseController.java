package com.tcm.apitransportsgitanos.application;

import com.tcm.apitransportsgitanos.application.dto.TruckDTO;
import com.tcm.apitransportsgitanos.application.dto.WareHouseDTO;
import com.tcm.apitransportsgitanos.domain.Truck;
import com.tcm.apitransportsgitanos.domain.WareHouse;
import com.tcm.apitransportsgitanos.persistance.WareHouseRepository;
import com.tcm.apitransportsgitanos.api.WareHouseRestController;
import org.springframework.stereotype.Controller;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class WareHouseController {

    private WareHouseRepository wareHouseRepository;

    public WareHouseController(WareHouseRepository repository) throws Exception {
        this.wareHouseRepository=repository;
        setupWarehouse();
    }

    private void setupWarehouse() throws Exception{
        WareHouseDTO warehouse = new WareHouseDTO("Barcelona");
        this.wareHouseRepository.save(new WareHouse(warehouse));
        warehouse = new WareHouseDTO("Sevilla");
        this.wareHouseRepository.save(new WareHouse(warehouse));
        warehouse = new WareHouseDTO("Valencia");
        this.wareHouseRepository.save(new WareHouse(warehouse));
        warehouse = new WareHouseDTO("Madrid");
        this.wareHouseRepository.save(new WareHouse(warehouse));
        warehouse = new WareHouseDTO("Vigo");
        this.wareHouseRepository.save(new WareHouse(warehouse));
    }

    public WareHouseDTO getWareHouseById(String wareHouseId) throws Exception{
        WareHouse wareHouse = wareHouseRepository.findById(wareHouseId).get();
        return new WareHouseDTO(wareHouse);
    }


    public List<WareHouseDTO> getAllWareHouse() {
        List<WareHouse> allWarehouse= new ArrayList<>();
        Iterator<WareHouse> iterator = this.wareHouseRepository.findAll().iterator();
        iterator.forEachRemaining(allWarehouse::add);
        return allWarehouse.stream().map(x -> new WareHouseDTO(x)).toList();
    }
}
