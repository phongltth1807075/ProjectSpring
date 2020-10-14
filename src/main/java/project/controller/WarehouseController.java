package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.WarehouseDTO;
import project.model.Category;
import project.model.Warehouse;
import project.model.rest.RESTResponse;
import project.service.WarehouseService;

import java.util.List;

@Controller
@RequestMapping(path = "/warehouse")
public class WarehouseController {


    @Autowired
    WarehouseService warehouseService;

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Object> create(@RequestBody Warehouse warehouse) {
//        Warehouse saveWarehouse = warehouseService.create(warehouse);
//        if (saveWarehouse != null) {
//            return new ResponseEntity<>(new RESTResponse.Success()
//                    .setStatus(HttpStatus.CREATED.value())
//                    .setMessage("Action Success")
//                    .addData(saveWarehouse)
//                    .build(),
//                    HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(new RESTResponse.SimpleError()
//                .setCode(HttpStatus.NOT_FOUND.value())
//                .setMessage("Not found")
//                .build(),
//                HttpStatus.NOT_FOUND);
//    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Object> getWarehouseByProductId(@PathVariable int id) {
        Warehouse warehouse = warehouseService.getById(id);
        if (warehouse != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .addData(new WarehouseDTO(warehouse))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }
}
