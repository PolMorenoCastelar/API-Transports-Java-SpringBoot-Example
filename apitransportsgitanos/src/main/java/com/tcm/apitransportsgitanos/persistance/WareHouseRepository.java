package com.tcm.apitransportsgitanos.persistance;

import com.tcm.apitransportsgitanos.domain.WareHouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends CrudRepository<WareHouse,String> {
    Iterable<WareHouse> findAllByCityName(String  name);
}
