package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.BlogDTO;
import project.dto.ListBlogDTO;
import project.model.Blog;
import project.model.rest.RESTResponse;
import project.service.BlogService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList() {
        List<BlogDTO> blogDTOList = new ArrayList<>();
        List<Blog> list = blogService.getList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getStatus().equals(Blog.BlogStatus.Active)) {
                    BlogDTO blogDTO = new BlogDTO(list.get(i));
                    blogDTOList.add(blogDTO);
                }
                return new ResponseEntity<>(new RESTResponse.Success()
                        .setStatus(HttpStatus.OK.value())
                        .setMessage("Simple Success")
                        .addData(new ListBlogDTO(blogDTOList))
                        .build(),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not Found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Blog blog) {
        Blog blog1 = blogService.create(blog);
        if (blog1 != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(blog1)
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
        Blog blog = blogService.detail(id);
        if (blog != null) {
            blogService.delete(blog);
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

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Object> detail(@PathVariable int id) {
        Blog blog = blogService.detail(id);
        if (blog != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(blog)
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
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Blog blog) {
        Blog blog1 = blogService.detail(id);
        if (blog1 != null) {
            Blog blog2 = new Blog();
            blog2.setStatus(blog.getStatus());
            blog2.setDescription(blog.getDescription());
            blog2.setImage(blog.getImage());
            blog2.setTitle(blog.getTitle());
            blogService.update(blog2);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(blogService.update(blog2))
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
