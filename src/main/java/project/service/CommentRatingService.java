package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Category;
import project.model.CommentRating;
import project.repository.CommentRatingRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CommentRatingService {

    @Autowired
    CommentRatingRepository commentRatingRepository;

    public CommentRating create(CommentRating commentRating) {
        commentRating.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        commentRating.setStatus(CommentRating.CommentRatingStatus.Active);
        return commentRatingRepository.save(commentRating);
    }

    public List<CommentRating> getListByProductId(int id) {
        List<CommentRating> commentRatingList = commentRatingRepository.findAllByProductId(id);
        return commentRatingList;
    }

    public boolean delete(CommentRating commentRating) {
        commentRating.setStatus(CommentRating.CommentRatingStatus.Deleted);
        commentRating.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        commentRatingRepository.save(commentRating);
        return true;
    }

    public CommentRating getById(int id) {
        CommentRating commentRating = commentRatingRepository.findById(id);
        return commentRating;
    }

    public boolean activeComment(CommentRating commentRating) {
        commentRating.setStatus(CommentRating.CommentRatingStatus.Active);
        commentRating.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        commentRatingRepository.save(commentRating);
        return true;
    }

}
