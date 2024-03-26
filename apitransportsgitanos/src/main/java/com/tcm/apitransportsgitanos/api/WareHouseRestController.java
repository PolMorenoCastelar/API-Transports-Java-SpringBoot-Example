package com.tcm.apitransportsgitanos.api;

import com.tcm.apitransportsgitanos.application.*;
import com.tcm.apitransportsgitanos.application.dto.WareHouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController

public class WareHouseRestController {

    private WareHouseController wareHouseController;

    public WareHouseRestController(WareHouseController wareHouseController) {
        this.wareHouseController = wareHouseController;
    }




    @GetMapping("/warehouses/{warehouseId}")
    public WareHouseDTO getWarehouseById(@PathVariable String warehouseId) throws Exception {
        return wareHouseController.getWareHouseById(warehouseId);
    }

    @GetMapping("/warehouses")
    public List<WareHouseDTO> getAllWarehouse() throws Exception{
            return wareHouseController.getAllWareHouse();
    }

}
