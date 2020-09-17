package project.dto;

import java.util.List;

public class ListCommentDTO {

    private List<CommentDTO> list;

    public ListCommentDTO(List<CommentDTO> list) {
        this.list = list;
    }

    public List<CommentDTO> getList() {
        return list;
    }

    public void setList(List<CommentDTO> list) {
        this.list = list;
    }
}
