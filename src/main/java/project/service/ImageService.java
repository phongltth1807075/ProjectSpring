package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Category;
import project.model.Image;
import project.repository.ImageRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> getList() {
        return imageRepository.findAll();
    }

    public Image create(Image image) {
        image.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        image.setStatus(Image.imageStatus.Active);
        return imageRepository.save(image);
    }

    public List<Image> findImageByProductId(int id) {
        return imageRepository.findAllByProductId(id);
    }

    public boolean delete(Image image) {
        image.setStatus(Image.imageStatus.Deactive);
        image.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        imageRepository.save(image);
        return true;
    }

    public Image update(Image image) {
        image.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return imageRepository.save(image);
    }

    public boolean hard_erase(int id) {
        List<Image> list = imageRepository.findAllByProductId(id);
        for (int i = 0; i < list.size(); i++) {
            imageRepository.delete(list.get(i));
        }
        return true;
    }
}
