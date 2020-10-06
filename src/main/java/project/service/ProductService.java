package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.model.Product;
import project.repository.ProductRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> getList(Specification specification, int page, int limit) {
        return productRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public List<Product> getAllProduct(Specification specification, Sort createdAt) {
        List<Product> productList = productRepository.findAll(specification);
        return productList;
    }

    public Product create(Product product) {
        product.setStatus(Product.ProductStatus.Active);
        product.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        return productRepository.save(product);
    }

    public Optional<Product> getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product;
    }


    public boolean delete(Product product) {
        product.setStatus(Product.ProductStatus.Deactive);
        product.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        productRepository.save(product);
        return true;
    }

    public Product update(Product product) {
        product.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return productRepository.save(product);
    }

    public List<Product> productListByProductId(int id) {
        List<Product> list = productRepository.findAllByAccountId(id);
        return list;
    }

    public List<Product> getAllProductByCategoryId(int id) {
        List<Product> list = productRepository.findAllByCategoryId(id);
        return list;
    }

}
