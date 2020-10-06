package project.dto;

import java.util.List;

public class ListHotProductDTO {
    List<HotProductDTO> list;

    public ListHotProductDTO(List<HotProductDTO> list) {
        this.list = list;
    }

    public ListHotProductDTO() {
    }

    public List<HotProductDTO> getList() {
        return list;
    }

    public void setList(List<HotProductDTO> list) {
        this.list = list;
    }
}
