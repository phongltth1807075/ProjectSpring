package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        if (shoppingCart.getCartInformation().getPaymentType().equals(OrdersEntity.PaymentType.Cod)) {
            OrdersEntity createOrder = new OrdersEntity();
            createOrder.setAccountId(shoppingCart.getCartInformation().getAccountId());
            createOrder.setStatus(OrdersEntity.OrderStatus.Pending);
            createOrder.setPaymentType(OrdersEntity.PaymentType.Cod);
            createOrder.setShipAddress(shoppingCart.getCartInformation().getShipAddress());
            createOrder.setShipPhone(shoppingCart.getCartInformation().getShipPhone());
            createOrder.setAccountId(shoppingCart.getCartInformation().getAccountId());
            //total price order
            double totalPrice = 0.0;

            int size = shoppingCart.getList().size();

            for (int i = 0; i < shoppingCart.getList().size(); i++) {
                totalPrice += shoppingCart.getList().get(i).getQuantity() * shoppingCart.getList().get(i).getPrice();

            }
            createOrder.setTotalPrice(totalPrice);
            orderService.create(createOrder);
            for (int i = 0; i < size; i++) {
                OrderDetailEntity createOrderDetail = new OrderDetailEntity();
                createOrderDetail.setOrderId(createOrder.getId());
                createOrderDetail.setProductId(shoppingCart.getList().get(i).getProductId());
                createOrderDetail.setUnitPrice(shoppingCart.getList().get(i).getPrice());
                createOrderDetail.setQuantity(shoppingCart.getList().get(i).getQuantity());
                orderDetailService.create(createOrderDetail);
            }
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .addData(createOrder)
                    .build(),
                    HttpStatus.CREATED);
        }
        /// Thanh toan vnPay or InterNetBanking
        ///
        ///
        ///
//        else if (cartInformation.getPaymentType().equals("VnPay")) {
//            /// Thanh toan vnPay or InterNetBanking
//            ///
//            ///
//            ///
//        }
        else {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Object> detail(@PathVariable int id) {
        List<OrderDetailEntity> list = orderService.detail(id);
        if (list != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .addData(new OrderDetailDTO(list))
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
