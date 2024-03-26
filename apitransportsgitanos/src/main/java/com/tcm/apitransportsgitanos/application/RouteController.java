package com.tcm.apitransportsgitanos.application;

import com.tcm.apitransportsgitanos.application.dto.RouteDTO;
import com.tcm.apitransportsgitanos.application.dto.TruckDTO;
import com.tcm.apitransportsgitanos.application.dto.WareHouseDTO;
import com.tcm.apitransportsgitanos.domain.Route;
import com.tcm.apitransportsgitanos.domain.Truck;
import com.tcm.apitransportsgitanos.domain.WareHouse;
import com.tcm.apitransportsgitanos.persistance.RouteRepository;
import com.tcm.apitransportsgitanos.persistance.TruckRepository;
import com.tcm.apitransportsgitanos.persistance.WareHouseRepository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class RouteController {

    private RouteRepository routeRepository;
    private WareHouseRepository wareHouseRepository;
    private TruckRepository truckRepository;

    public RouteController(RouteRepository routeRepository, WareHouseRepository wareHouseRepository,TruckRepository truckRepository){
        this.routeRepository=routeRepository;
        this.wareHouseRepository=wareHouseRepository;
        this.truckRepository=truckRepository;
    }

    public RouteDTO createRoute (RouteDTO routeDTO)throws Exception{
        Route route= new Route(routeDTO,getAllWareHouse());

        this.truckRepository.save(route.getTruck());
        this.routeRepository.save(route);
        return new RouteDTO(route);
    }
    public List<WareHouse> getAllWareHouse() {
        List<WareHouse> allWarehouse= new ArrayList<>();
        Iterator<WareHouse> iterator = this.wareHouseRepository.findAll().iterator();
        iterator.forEachRemaining(allWarehouse::add);
        return allWarehouse.stream().toList();
    }

    public RouteDTO getRoute(String routeId) throws Exception {
        Route route = routeRepository.findById(routeId).get();
        return new RouteDTO(route);
    }

    public List<RouteDTO> getAllRoutes(){
        List<Route> allRoutes= new ArrayList<>();
        Iterator<Route> iterator = routeRepository.findAll().iterator();
        iterator.forEachRemaining(allRoutes::add);
        return allRoutes.stream().map(x -> new RouteDTO(x)).toList();
    }

    public void deleteRouteById(String routeId) {
        Route route= routeRepository.findById(routeId).get();
        routeRepository.delete(route);
    }

    public void deleteAllRoutes() {
        routeRepository.deleteAll();
    }
}
