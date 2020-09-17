package project.dto;

import java.util.List;

public class ListRatingDTO {

    List<RatingDTO> list;

    public ListRatingDTO() {
    }

    public ListRatingDTO(List<RatingDTO> list) {
        this.list = list;
    }

    public List<RatingDTO> getList() {
        return list;
    }

    public void setList(List<RatingDTO> list) {
        this.list = list;
    }
}
