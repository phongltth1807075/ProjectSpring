package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Category;
import project.model.Rating;
import project.repository.RatingRepository;

import java.util.Calendar;
import java.util.List;


@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> getList() {
        return ratingRepository.findAll();
    }

    public Rating create(Rating rating) {
        rating.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        rating.setStatus(Rating.RatingStatus.Active);
        return ratingRepository.save(rating);
    }

    public List<Rating> getById(int id) {
        List<Rating> list = ratingRepository.findAllByProductId(id);
        return list;
    }

    public Rating detail(int id) {
        Rating rating = ratingRepository.findById(id);
        return rating;
    }

    public boolean delete(Rating rating) {
        rating.setStatus(Rating.RatingStatus.Deactive);
        rating.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        ratingRepository.save(rating);
        return true;
    }

    public Rating update(Rating rating) {
        rating.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return ratingRepository.save(rating);
    }

}
