package com.tcm.apitransportsgitanos.api;

import com.tcm.apitransportsgitanos.application.RouteController;
import com.tcm.apitransportsgitanos.application.dto.RouteDTO;
import com.tcm.apitransportsgitanos.application.dto.TruckDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteRestController {

    private RouteController routeController;

    public RouteRestController(RouteController routeController){
        this.routeController=routeController;
    }

    @PostMapping("/routes")
    public RouteDTO createRoute(@RequestBody RouteDTO routeDTO) throws Exception {
        return this.routeController.createRoute(routeDTO);
    }

    @GetMapping("/routes/{routeId}")
    public RouteDTO getRoute(@PathVariable String routeId)throws Exception {
        return routeController.getRoute(routeId);
    }

    @DeleteMapping("/routes")
    public void deleteAllRoutes(){
        routeController.deleteAllRoutes();
    }

    @GetMapping("/routes")
    public List<RouteDTO> getAllRoutes(){
        return routeController.getAllRoutes();
    }

    @DeleteMapping("/routes/{routeId}")
    public void deleteRouteById(@PathVariable String routeId){
        routeController.deleteRouteById(routeId);
    }


}
