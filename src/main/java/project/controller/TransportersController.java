package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.ListTransportersDTO;
import project.dto.TransportersDTO;
import project.model.Accounts;
import project.model.Category;
import project.model.Transporters;
import project.model.rest.RESTResponse;
import project.service.TransportersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/transporters")
public class TransportersController {

    @Autowired
    TransportersService transportersService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList() {
        List<Transporters> transportersList = transportersService.getList();
        List<TransportersDTO> transportersDTOList = new ArrayList<>();
        if (transportersList != null) {

            for (int i = 0; i < transportersList.size(); i++) {
                TransportersDTO transportersDTO = new TransportersDTO(transportersList.get(i));
                transportersDTOList.add(transportersDTO);
            }
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Action success!")
                    .addData(new ListTransportersDTO(transportersDTOList))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Transporters transporters) {
        Transporters saveTransporters = transportersService.create(transporters);
        if (saveTransporters != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .addData(saveTransporters)
                    .build(),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Optional<Transporters> exitTransporters = transportersService.getById(id);
        if (exitTransporters.isPresent()) {
            transportersService.delete(exitTransporters.get());
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id) {
        Optional<Transporters> transporters = transportersService.getById(id);
        if (transporters.isPresent()) {
            Transporters transporters1 = transporters.get();
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(transporters1)
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Category category) {
        Optional<Transporters> transportersUpdate = transportersService.getById(id);
        if (transportersUpdate.isPresent()) {
            Transporters newTransporters = transportersUpdate.get();
            newTransporters.setTransportersName(newTransporters.getTransportersName());
            newTransporters.setStatus(newTransporters.getStatus());
            newTransporters.setDescription(newTransporters.getDescription());
            transportersService.update(newTransporters);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(transportersService.update(newTransporters))
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
