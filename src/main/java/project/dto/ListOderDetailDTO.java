package project.dto;

import project.model.OrderDetailEntity;

import java.util.List;

public class ListOderDetailDTO {
    private List<OrderDetailDTO> orderDetailDTOList;

//    public ListOderDetailDTO(List<OrderDetailEntity> orderDetailEntities) {
//
//        for (int i = 0; i < orderDetailEntities.size(); i++) {
//            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//            orderDetailDTO.setId(orderDetailEntities.get(i).getId());
//            orderDetailDTO.setOrderId(orderDetailEntities.get(i).getOrderId());
//            orderDetailDTO.setProductId(orderDetailEntities.get(i).getProductId());
//            orderDetailDTO.setProductName(orderDetailEntities.get(i).getProduct().getProductName());
//            orderDetailDTO.setQuantity(orderDetailEntities.get(i).getQuantity());
//            orderDetailDTO.setProductPrice(orderDetailEntities.get(i).getUnitPrice());
//            orderDetailDTO.setTotalPrice(orderDetailEntities.get(i).getUnitPrice() * orderDetailEntities.get(i).getQuantity());
//        }
//    }


    public ListOderDetailDTO(List<OrderDetailDTO> orderDetailDTO) {
        this.orderDetailDTOList = orderDetailDTO;
    }

    public ListOderDetailDTO() {
    }

    public List<OrderDetailDTO> getOrderDetailDTOList() {
        return orderDetailDTOList;
    }

    public void setOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList) {
        this.orderDetailDTOList = orderDetailDTOList;
    }
}
