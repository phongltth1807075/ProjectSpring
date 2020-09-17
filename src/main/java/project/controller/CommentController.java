package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.CommentDTO;
import project.dto.ListCommentDTO;
import project.model.Comment;
import project.model.rest.RESTResponse;
import project.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(path = "/comment")
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList() {
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(commentService.getList())
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Comment comment) {
        Comment saveComment = commentService.create(comment);
        if (saveComment != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .addData(saveComment)
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
        Comment exitComment = commentService.detail(id);
        if (exitComment != null) {
            commentService.delete(exitComment);
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

    @RequestMapping(method = RequestMethod.DELETE,path = "/activeComment/{id}")
    public ResponseEntity<Object> activeComment(@PathVariable int id) {
        Comment comment = commentService.detail(id);
        if (comment != null) {
            commentService.activeComment(comment);
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
        List<Comment> list = commentService.getByProductId(id);
        List<Comment> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus().equals(Comment.CommentStatus.Active)) {
                list1.add(list.get(i));
            }
        }
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            CommentDTO commentDTO = new CommentDTO(list1.get(i));
            commentDTOList.add(commentDTO);
        }
        if (list != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(new ListCommentDTO(commentDTOList))
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
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Comment comment) {
        Comment comment1 = commentService.detail(id);
        if (comment1 != null) {
            Comment newComment = comment1;
            newComment.setAccountId(comment.getAccountId());
            newComment.setComment(comment.getComment());
            newComment.setProductId(comment.getProductId());
            newComment.setStatus(comment.getStatus());
            commentService.update(newComment);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(commentService.update(newComment))
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
