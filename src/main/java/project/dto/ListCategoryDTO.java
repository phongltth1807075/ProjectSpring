package project.dto;

import java.util.List;

public class ListCategoryDTO {
    private List<CategoryDTO> list;

    public ListCategoryDTO(List<CategoryDTO> list) {
        this.list = list;
    }

    public List<CategoryDTO> getList() {
        return list;
    }

    public void setList(List<CategoryDTO> list) {
        this.list = list;
    }
}
