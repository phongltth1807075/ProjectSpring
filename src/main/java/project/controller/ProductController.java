package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.dto.ImageDTO;
import project.dto.ListImageDTO;
import project.dto.OrderDetailDTO;
import project.dto.ProductDTO;
import project.model.DataProducts;
import project.model.Image;
import project.model.Product;
import project.model.rest.RESTPagination;
import project.model.rest.RESTResponse;
import project.model.specification.ProductSpecification;
import project.model.specification.SearchCriteria;
import project.service.ImageService;
import project.service.ProductService;
import project.util.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "startPrice", required = false) Optional<Double> startPrice,
            @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(value = "category", required = false) Optional<Integer> category
    ) {
        Specification specification = Specification.where(null);
        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new ProductSpecification(new SearchCriteria("productName", "=", keyword)))
//                    .or(new ProductSpecification(new SearchCriteria("CategoryName", "=", keyword)))
                    .or(new ProductSpecification(new SearchCriteria("description", "=", keyword)));
        }
        if (startPrice.isPresent()) {
            specification = specification.and(new ProductSpecification(new SearchCriteria("productPrice", ">=", startPrice.get())));
        }
        if (endPrice.isPresent()) {
            specification = specification.and(new ProductSpecification(new SearchCriteria("productPrice", "<=", endPrice.get())));
        }
        if (category.isPresent()) {
            specification = specification.and(new ProductSpecification(new SearchCriteria("categoryId", "=", category.get())));
        }
        Page<Product> ProductPage = productService.getList(specification, page, limit);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(ProductPage.getContent().stream().map(x -> new ProductDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, ProductPage.getTotalPages(), ProductPage.getTotalElements()))
                .build(),
                HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/listImage/{id}")
    public ResponseEntity<Object> listImageByProductId(@PathVariable int id) {
        List<Image> imageList = imageService.findImageByProductId(id);
        List<ImageDTO> imageDTOArrayList = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setId(imageList.get(i).getId());
            imageDTO.setProductId(imageList.get(i).getProductId());
            imageDTO.setUrl(imageList.get(i).getUrl());
            imageDTO.setStatus(imageList.get(i).getStatus());
            imageDTO.setCreatedAt(DateTimeUtil.formatDateFromLong(imageList.get(i).getCreatedAt()));
            imageDTO.setUpdatedAt(DateTimeUtil.formatDateFromLong(imageList.get(i).getUpdatedAt()));
            imageDTO.setDeletedAt(DateTimeUtil.formatDateFromLong(imageList.get(i).getDeletedAt()));
            imageDTOArrayList.add(imageDTO);
        }
        return new ResponseEntity<>(new ListImageDTO(imageDTOArrayList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody DataProducts dataProducts) {
        Product saveProduct = productService.create(dataProducts.getProduct());
        for (int i = 0; i < dataProducts.getImageList().size(); i++) {
            Image save = new Image();
            save.setProductId(dataProducts.getProduct().getProductId());
            save.setUrl(dataProducts.getImageList().get(i).getUrl());
            imageService.create(save);
        }
        if (saveProduct != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .addData(saveProduct)
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
        Optional<Product> exitProduct = productService.getById(id);
        if (exitProduct.isPresent()) {
            if (exitProduct.get().getStatus() == Product.ProductStatus.Active) {
                productService.delete(exitProduct.get());
                return new ResponseEntity<>(new RESTResponse.Success()
                        .setStatus(HttpStatus.OK.value())
                        .setMessage("Simple Success")
                        .build(),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found or Deleted")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id) {
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            Product product1 = product.get();
            if (product1.getStatus() == Product.ProductStatus.Active) {
                return new ResponseEntity<>(new RESTResponse.Success()
                        .setStatus(HttpStatus.OK.value())
                        .setMessage("Success")
                        .addData(new ProductDTO(product1))
                        .build(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new RESTResponse.Success()
                        .setStatus(HttpStatus.OK.value())
                        .setMessage("Product Deleted or DeActive !")
                        .build(),
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody DataProducts dataProducts) {
        Optional<Product> productUpdate = productService.getById(id);
        if (productUpdate.isPresent()) {
            Product newProduct = productUpdate.get();
            newProduct.setProductName(dataProducts.getProduct().getProductName());
            newProduct.setDescription(dataProducts.getProduct().getDescription());
            newProduct.setImageProduct(dataProducts.getProduct().getImageProduct());
            newProduct.setProductPrice(dataProducts.getProduct().getProductPrice());
            newProduct.setStatus(dataProducts.getProduct().getStatus());
            newProduct.setCategoryId(dataProducts.getProduct().getCategoryId());
            Product productupdate = productService.update(newProduct);
            if (dataProducts.getImageList() != null) {
                imageService.hard_erase(id);
                for (int i = 0; i < dataProducts.getImageList().size(); i++) {
                    Image save = new Image();
                    save.setProductId(id);
                    save.setUrl(dataProducts.getImageList().get(i).getUrl());
                    imageService.create(save);
                }
            }
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(new ProductDTO(productupdate))
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
