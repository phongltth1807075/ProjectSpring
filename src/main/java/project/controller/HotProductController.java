package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.HotProductDTO;
import project.dto.ListHotProductDTO;
import project.model.HotProducts;
import project.model.rest.RESTResponse;
import project.service.HotProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/hotProducts")
public class HotProductController {

    @Autowired
    HotProductService hotProductService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody HotProducts hotProducts) {
        HotProducts hotProducts1 = hotProductService.create(hotProducts);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Action Success")
                .addData(hotProducts1)
                .build(),
                HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getAllHotProduct() {
        List<HotProducts> hotProductsList = hotProductService.getList();
        List<HotProducts> hotProductsListNoDeleted = new ArrayList<>();
        List<HotProductDTO> hotProductDTOList = new ArrayList<>();
        if (hotProductsList != null) {
            for (int i = 0; i < hotProductsList.size(); i++) {
                if (hotProductsList.get(i).getStatus() != HotProducts.HotProductStatus.Deleted) {
                    hotProductsListNoDeleted.add(hotProductsList.get(i));
                }
            }
            for (int i = 0; i < hotProductsListNoDeleted.size(); i++) {
                HotProductDTO hotProductDTO = new HotProductDTO(hotProductsListNoDeleted.get(i));
                hotProductDTOList.add(hotProductDTO);
            }
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(new ListHotProductDTO(hotProductDTOList))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not Found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAllHotProductActive")
    public ResponseEntity<Object> getAllHotProductActive() {
        List<HotProducts> hotProductsList = hotProductService.getList();
        List<HotProducts> hotProductsListActive = new ArrayList<>();
        List<HotProductDTO> hotProductDTOList = new ArrayList<>();
        if (hotProductsList != null) {
            for (int i = 0; i < hotProductsList.size(); i++) {
                if (hotProductsList.get(i).getStatus().equals(HotProducts.HotProductStatus.Active)) {
                    hotProductsListActive.add(hotProductsList.get(i));
                }
            }
            for (int i = 0; i < hotProductsListActive.size(); i++) {
                HotProductDTO hotProductDTO = new HotProductDTO(hotProductsListActive.get(i));
                hotProductDTOList.add(hotProductDTO);
            }
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(new ListHotProductDTO(hotProductDTOList))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not Found")
                .build(),
                HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Object> activeHotProduct(@PathVariable int id) {
        HotProducts hotProducts = hotProductService.detail(id);
        if (hotProducts != null) {
            hotProductService.active(hotProducts);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not Found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        HotProducts hotProducts = hotProductService.detail(id);
        if (hotProducts != null) {
            hotProductService.delete(hotProducts);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not Found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

}
