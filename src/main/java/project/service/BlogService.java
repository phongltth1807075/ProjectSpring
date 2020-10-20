package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Blog;
import project.repository.BlogRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    public List<Blog> getList() {
        return blogRepository.findAll();
    }

    public Blog create(Blog blog) {
        blog.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        blog.setStatus(Blog.BlogStatus.Active);
        return blogRepository.save(blog);
    }

    public Blog detail(int id) {
        Blog blog = blogRepository.findAllById(id);
        return blog;
    }

    public boolean delete(Blog blog) {
        blog.setStatus(Blog.BlogStatus.DeActive);
        blog.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        blogRepository.save(blog);
        return true;
    }

    public boolean update(Blog blog) {
        blog.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        blogRepository.save(blog);
        return true;
    }
}
