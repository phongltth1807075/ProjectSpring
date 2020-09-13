package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.model.OrderDetailEntity;
import project.model.OrdersEntity;
import project.repository.OrderDetailRepository;
import project.repository.OrderRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    public Page<OrdersEntity> getList(Specification specification, int page, int limit) {
        return orderRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public OrdersEntity create(OrdersEntity orderEntity) {
        orderEntity.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        return orderRepository.save(orderEntity);
    }

    public Optional<OrdersEntity> getDetail(int Id) {
        Optional<OrdersEntity> optionalOrderEntity = orderRepository.findById(Id);
        return optionalOrderEntity;
    }

    public List<OrderDetailEntity> detail(int Id) {
        List<OrderDetailEntity> list = orderDetailRepository.findAllByOrderId(Id);
        return list;
    }

    public boolean delete(OrdersEntity orderEntity) {
        orderEntity.setStatus(OrdersEntity.OrderStatus.Deleted);
        orderEntity.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        orderRepository.save(orderEntity);
        return true;
    }

    public boolean confirmOrder(OrdersEntity orderEntity) {
        orderEntity.setStatus(OrdersEntity.OrderStatus.Condirmed);
        orderEntity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        orderRepository.save(orderEntity);
        return true;
    }
    public boolean shippingOrder(OrdersEntity orderEntity) {
        orderEntity.setStatus(OrdersEntity.OrderStatus.Shipping);
        orderEntity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        orderRepository.save(orderEntity);
        return true;
    }
    public boolean doneOrder(OrdersEntity orderEntity) {
        orderEntity.setStatus(OrdersEntity.OrderStatus.Done);
        orderEntity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        orderRepository.save(orderEntity);
        return true;
    }
    public boolean paidOrder(OrdersEntity orderEntity) {
        orderEntity.setStatus(OrdersEntity.OrderStatus.Paid);
        orderEntity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        orderRepository.save(orderEntity);
        return true;
    }
    public boolean cancelOrder(OrdersEntity orderEntity) {
        orderEntity.setStatus(OrdersEntity.OrderStatus.Cancel);
        orderEntity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        orderRepository.save(orderEntity);
        return true;
    }

    public OrdersEntity update(OrdersEntity orderEntity) {
        orderEntity.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return orderRepository.save(orderEntity);
    }

}
