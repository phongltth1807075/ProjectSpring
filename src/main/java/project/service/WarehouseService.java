package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Category;
import project.model.Warehouse;
import project.repository.WarehouseRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;


    public List<Warehouse> getList() {
        return warehouseRepository.findAll();
    }

    public Warehouse create(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse getById(int id) {
        Warehouse warehouse = warehouseRepository.findAllByProductId(id);
        return warehouse;

    }

    public Warehouse update(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }
}
