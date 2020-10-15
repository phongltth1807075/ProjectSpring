package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Category;
import project.model.HotProducts;
import project.repository.HotProductRepository;

import java.util.Calendar;
import java.util.List;


@Service
public class HotProductService {

    @Autowired
    HotProductRepository hotProductRepository;

    public List<HotProducts> getList() {
        return hotProductRepository.findAll();
    }

    public HotProducts create(HotProducts hotProducts) {
        hotProducts.setStatus(HotProducts.HotProductStatus.Deactive);
        hotProducts.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        return hotProductRepository.save(hotProducts);
    }

    public boolean active(HotProducts hotProducts) {
        hotProducts.setStatus(HotProducts.HotProductStatus.Active);
        hotProducts.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        hotProductRepository.save(hotProducts);
        return true;
    }

    public boolean delete(HotProducts hotProducts) {
        hotProducts.setStatus(HotProducts.HotProductStatus.Deleted);
        hotProducts.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        hotProductRepository.save(hotProducts);
        return true;
    }


    public HotProducts detail(int id) {
        HotProducts hotProducts = hotProductRepository.findAllByHotProductId(id);
        return hotProducts;
    }


}
