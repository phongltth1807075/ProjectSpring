package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.dto.ListOderDetailDTO;
import project.dto.OrderDTO;
import project.dto.OrderDetailDTO;
import project.model.*;
import project.model.rest.RESTPagination;
import project.model.rest.RESTResponse;
import project.model.specification.AccountSpecification;
import project.model.specification.OrderSpecfication;
import project.model.specification.SearchCriteria;
import project.service.OrderDetailService;
import project.service.OrderService;
import project.service.WarehouseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping(path = "/order")
@Controller
public class OrderEntityController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    WarehouseService warehouseService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(value = "status", required = false) Optional<Integer> status
    ) {
        Specification specification = Specification.where(null);
        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new OrderSpecfication(new SearchCriteria("shopPhone", "=", keyword)))
                    .and(new OrderSpecfication(new SearchCriteria("shipAddress", "=", keyword)))
                    .or(new OrderSpecfication(new SearchCriteria("createdAt", "=", keyword)));
        }
        if (status.isPresent()) {
            specification = specification.and(new AccountSpecification(new SearchCriteria("status", "=", status.get())));
        }
        Page<OrdersEntity> OrderPage = orderService.getList(specification, page, limit);
        if (OrderPage != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Action success!")
                    .addData(OrderPage.getContent().stream().map(x -> new OrderDTO(x)).collect(Collectors.toList()))
                    .setPagination(new RESTPagination(page, limit, OrderPage.getTotalPages(), OrderPage.getTotalElements()))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)

    public ResponseEntity<Object> create(@RequestBody ShoppingCart shoppingCart) {
        List<Integer> id = new ArrayList<>();
        List<Double> total = new ArrayList<>();
        List<Double> w = new ArrayList<>();
        List<Warehouse> warehouseList = new ArrayList<>();
        List<Double> min = new ArrayList<>();
        for (int i = 0; i < shoppingCart.getList().size(); i++) {
            id.add(shoppingCart.getList().get(i).getProductId());
        }
        List<Integer> quantity = new ArrayList<>();
        for (int i = 0; i < shoppingCart.getList().size(); i++) {
            quantity.add(shoppingCart.getList().get(i).getQuantity());
        }
        for (int i = 0; i < id.size(); i++) {
            Warehouse warehouse = warehouseService.getById(id.get(i));
            total.add(warehouse.getTotalProduct());
            w.add(total.get(i) - quantity.get(i));
        }
        for (int i = 0; i < w.size(); i++) {
            if (w.get(i) < 0) {
                min.add(w.get(i));
            }
        }
        if (min.size() <= 0) {
            if (shoppingCart.getCartInformation().getPaymentType().equals(OrdersEntity.PaymentType.Cod)) {
                OrdersEntity createOrder = new OrdersEntity();
                createOrder.setAccountId(shoppingCart.getCartInformation().getAccountId());
                createOrder.setStatus(OrdersEntity.OrderStatus.Pending);
                createOrder.setPaymentType(OrdersEntity.PaymentType.Cod);
                createOrder.setShipAddress(shoppingCart.getCartInformation().getShipAddress());
                createOrder.setShipPhone(shoppingCart.getCartInformation().getShipPhone());
                createOrder.setAccountId(shoppingCart.getCartInformation().getAccountId());
                createOrder.setTransportersId(shoppingCart.getCartInformation().getTransportersId());
                //total price order
                double totalPrice = 0.0;

                int size = shoppingCart.getList().size();

                for (int j = 0; j < shoppingCart.getList().size(); j++) {
                    totalPrice += shoppingCart.getList().get(j).getQuantity() * shoppingCart.getList().get(j).getProductPrice();

                }
                createOrder.setTotalPrice(totalPrice);
                orderService.create(createOrder);
                for (int z = 0; z < size; z++) {
                    OrderDetailEntity createOrderDetail = new OrderDetailEntity();
                    createOrderDetail.setOrderId(createOrder.getId());
                    createOrderDetail.setProductId(shoppingCart.getList().get(z).getProductId());
                    createOrderDetail.setUnitPrice(shoppingCart.getList().get(z).getProductPrice());
                    createOrderDetail.setQuantity(shoppingCart.getList().get(z).getQuantity());
                    createOrderDetail.setProperty(shoppingCart.getList().get(z).getProperty());
                    orderDetailService.create(createOrderDetail);
                }
                for (int h = 0; h < shoppingCart.getList().size(); h++) {
                    warehouseList.add(warehouseService.getById(shoppingCart.getList().get(h).getProductId()));
                }
                for (int b = 0; b < warehouseList.size(); b++) {
                    Warehouse warehouse = warehouseList.get(b);
                    warehouse.setTotalProduct(w.get(b));
                    warehouseService.update(warehouse);
                }
                return new ResponseEntity<>(new RESTResponse.Success()
                        .setStatus(HttpStatus.CREATED.value())
                        .setMessage("Action Success")
                        .addData(createOrder)
                        .build(),
                        HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("There are products with excess quantity in stock")
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
    public ResponseEntity<Object> detail(@PathVariable int id) {
        List<OrderDetailEntity> list = orderService.detail(id);
        if (list != null) {
            int size = list.size();
            List<OrderDetailDTO> objectList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setId(list.get(i).getId());
                orderDetailDTO.setOrderId(list.get(i).getOrderId());
                orderDetailDTO.setProductPrice(list.get(i).getUnitPrice());
                orderDetailDTO.setProductId(list.get(i).getProductId());
                orderDetailDTO.setProductName(list.get(i).getProduct().getProductName());
                orderDetailDTO.setQuantity(list.get(i).getQuantity());
                orderDetailDTO.setProperty(list.get(i).getProperty());
                orderDetailDTO.setTotalPrice(list.get(i).getUnitPrice() * list.get(i).getQuantity());
                objectList.add(orderDetailDTO);
            }
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(new ListOderDetailDTO(objectList))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "confirmOrder/{id}")
    public ResponseEntity<Object> CondirmedOrder(@PathVariable int id) {
        Optional<OrdersEntity> optionalOrderEntity = orderService.getDetail(id);
        if (optionalOrderEntity.isPresent()) {
            OrdersEntity orderEntity = optionalOrderEntity.get();
            orderService.confirmOrder(orderEntity);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(orderService.confirmOrder(orderEntity))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "shipping/{id}")
    public ResponseEntity<Object> Shipping(@PathVariable int id) {
        Optional<OrdersEntity> orderEntityOptional = orderService.getDetail(id);
        if (orderEntityOptional.isPresent()) {
            OrdersEntity orderEntity = orderEntityOptional.get();
            orderService.shippingOrder(orderEntity);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(orderService.confirmOrder(orderEntity))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "done/{id}")
    public ResponseEntity<Object> Done(@PathVariable int id) {
        Optional<OrdersEntity> orderEntityOptional = orderService.getDetail(id);
        if (orderEntityOptional.isPresent()) {
            OrdersEntity orderEntity = orderEntityOptional.get();
            orderService.doneOrder(orderEntity);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(orderService.confirmOrder(orderEntity))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "paid/{id}")
    public ResponseEntity<Object> Paid(@PathVariable int id) {
        Optional<OrdersEntity> orderEntityOptional = orderService.getDetail(id);
        if (orderEntityOptional.isPresent()) {
            OrdersEntity orderEntity = orderEntityOptional.get();
            orderService.paidOrder(orderEntity);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(orderService.confirmOrder(orderEntity))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "cancel/{id}")
    public ResponseEntity<Object> Cancel(@PathVariable int id) {
        Optional<OrdersEntity> orderEntityOptional = orderService.getDetail(id);
        if (orderEntityOptional.isPresent()) {
            OrdersEntity orderEntity = orderEntityOptional.get();
            orderService.cancelOrder(orderEntity);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(orderService.confirmOrder(orderEntity))
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
