package com.tcm.apitransportsgitanos.persistance;

import com.tcm.apitransportsgitanos.domain.Route;
import com.tcm.apitransportsgitanos.domain.WareHouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends CrudRepository<Route,String> {
}
