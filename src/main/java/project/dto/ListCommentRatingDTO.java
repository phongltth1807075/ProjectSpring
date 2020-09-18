package project.dto;

import java.util.List;

public class ListCommentRatingDTO {
    private List<CommentRatingDTO> commentRatingDTOList;

    public ListCommentRatingDTO(List<CommentRatingDTO> commentRatingDTOList) {
        this.commentRatingDTOList = commentRatingDTOList;
    }

    public ListCommentRatingDTO() {
    }

    public List<CommentRatingDTO> getCommentRatingDTOList() {
        return commentRatingDTOList;
    }

    public void setCommentRatingDTOList(List<CommentRatingDTO> commentRatingDTOList) {
        this.commentRatingDTOList = commentRatingDTOList;
    }
}
