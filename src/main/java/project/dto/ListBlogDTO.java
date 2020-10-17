package project.dto;

import java.util.List;

public class ListBlogDTO {
    private List<BlogDTO> list;

    public ListBlogDTO(List<BlogDTO> list) {
        this.list = list;
    }

    public ListBlogDTO() {
    }

    public List<BlogDTO> getList() {
        return list;
    }

    public void setList(List<BlogDTO> list) {
        this.list = list;
    }
}
