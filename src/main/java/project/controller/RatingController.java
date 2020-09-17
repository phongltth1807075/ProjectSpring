package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.ListRatingDTO;
import project.dto.RatingDTO;
import project.model.Rating;
import project.model.rest.RESTResponse;
import project.service.RatingService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList() {
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(ratingService.getList())
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Rating rating) {
        Rating saveRating = ratingService.create(rating);
        if (saveRating != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .addData(saveRating)
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
        Rating exitRating = ratingService.detail(id);
        if (exitRating != null) {
            ratingService.delete(exitRating);
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
    public ResponseEntity<Object> listCommentByProductId(@PathVariable int id) {
        List<Rating> list = ratingService.getById(id);
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            RatingDTO ratingDTO = new RatingDTO(list.get(i));
            ratingDTOList.add(ratingDTO);
        }
        if (list != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(new ListRatingDTO(ratingDTOList))
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
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Rating rating) {
        Rating rating1 = ratingService.detail(id);
        if (rating1 != null) {
            Rating newRating = rating1;
            newRating.setAccountId(rating.getAccountId());
            newRating.setValue(rating.getValue());
            newRating.setProductId(rating.getProductId());
            newRating.setStatus(rating.getStatus());
            ratingService.update(newRating);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(ratingService.update(newRating))
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
