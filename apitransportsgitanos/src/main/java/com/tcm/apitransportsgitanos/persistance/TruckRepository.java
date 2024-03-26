package com.tcm.apitransportsgitanos.persistance;

import com.tcm.apitransportsgitanos.domain.Truck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends CrudRepository<Truck,String> {
}
