package project.service;


import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Comment;
import project.repository.CommentRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public List<Comment> getListByAccountId(int id) {
        return commentRepository.findAllByAccountId(id);
    }

    public Comment create(Comment comment) {
        comment.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        comment.setStatus(Comment.CommentStatus.Deactive);
        return commentRepository.save(comment);
    }

    public List<Comment> getByProductId(int id) {
        List<Comment> list = commentRepository.findAllByProductId(id);
        return list;
    }

    public Comment detail(int id) {
        Comment comment = commentRepository.findById(id);
        return comment;
    }

    public boolean delete(Comment comment) {
        comment.setStatus(Comment.CommentStatus.Deleted);
        comment.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        commentRepository.save(comment);
        return true;
    }

    public boolean activeComment(Comment comment) {
        comment.setStatus(Comment.CommentStatus.Active);
        comment.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        commentRepository.save(comment);
        return true;
    }

    public Comment update(Comment comment) {
        comment.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return commentRepository.save(comment);
    }


}
