package project.dto;

import java.util.List;

public class ListOrderDTO {
    private List<OrderDTO> list;

    public ListOrderDTO(List<OrderDTO> list) {
        this.list = list;
    }

    public List<OrderDTO> getList() {
        return list;
    }

    public void setList(List<OrderDTO> list) {
        this.list = list;
    }
}
