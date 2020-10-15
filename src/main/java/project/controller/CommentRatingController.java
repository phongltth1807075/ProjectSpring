package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.CommentRatingDTO;
import project.dto.ListCommentRatingDTO;
import project.model.CommentRating;
import project.model.rest.RESTResponse;
import project.service.CommentRatingService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(path = "/commentRating")
@Controller
public class CommentRatingController {

    @Autowired
    CommentRatingService commentRatingService;

    @RequestMapping(method = RequestMethod.GET, path = "/getListByProductId/{id}")
    public ResponseEntity<Object> getListByProductId(@PathVariable int id) {
        List<CommentRating> commentRatingList = commentRatingService.getListByProductId(id);
        List<CommentRatingDTO> commentRatingDTOList = new ArrayList<>();
        for (int i = 0; i < commentRatingList.size(); i++) {
            if (commentRatingList.get(i).getStatus().equals(CommentRating.CommentRatingStatus.Active)) {
                CommentRatingDTO commentRatingDTO = new CommentRatingDTO(commentRatingList.get(i));
                commentRatingDTOList.add(commentRatingDTO);
            }
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(new ListCommentRatingDTO(commentRatingDTOList))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody CommentRating commentRating) {
        CommentRating commentRating1 = commentRatingService.create(commentRating);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Action Success")
                .addData(commentRating1)
                .build(),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getAllByProductId/{id}")
    public ResponseEntity<Object> getAllByProductId(@PathVariable int id) {
        List<CommentRating> commentRatingList = commentRatingService.getListByProductId(id);
        List<CommentRatingDTO> commentRatingDTOList = new ArrayList<>();
        for (int i = 0; i < commentRatingList.size(); i++) {
            if (commentRatingList.get(i).getStatus() != CommentRating.CommentRatingStatus.Deleted) {
                CommentRatingDTO commentRatingDTO = new CommentRatingDTO(commentRatingList.get(i));
                commentRatingDTOList.add(commentRatingDTO);
            }
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(new ListCommentRatingDTO(commentRatingDTOList))
                .build(),
                HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.DELETE, path = "/activeCommentRating/{id}")
//    public ResponseEntity<Object> activeCommentRating(@PathVariable int id) {
//        CommentRating commentRating = commentRatingService.getById(id);
//        if (commentRating != null) {
//            return new ResponseEntity<>(new RESTResponse.Success()
//                    .setStatus(HttpStatus.OK.value())
//                    .setMessage("Action success!")
//                    .addData(commentRatingService.activeComment(commentRating))
//                    .build(),
//                    HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new RESTResponse.SimpleError()
//                .setCode(HttpStatus.NOT_FOUND.value())
//                .setMessage("Not found")
//                .build(),
//                HttpStatus.NOT_FOUND);
//    }

//    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteCommentRating/{id}")
//    public ResponseEntity<Object> delete(@PathVariable int id) {
//        CommentRating commentRating = commentRatingService.getById(id);
//        if (commentRating != null) {
//            return new ResponseEntity<>(new RESTResponse.Success()
//                    .setStatus(HttpStatus.OK.value())
//                    .setMessage("Action success!")
//                    .addData(commentRatingService.delete(commentRating))
//                    .build(),
//                    HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new RESTResponse.SimpleError()
//                .setCode(HttpStatus.NOT_FOUND.value())
//                .setMessage("Not found")
//                .build(),
//                HttpStatus.NOT_FOUND);
//    }
}
