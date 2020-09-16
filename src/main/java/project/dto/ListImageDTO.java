package project.dto;

import java.util.List;

public class ListImageDTO {
    private List<ImageDTO> imageDTOS;

    public ListImageDTO(List<ImageDTO> imageDTOS) {
        this.imageDTOS = imageDTOS;
    }

    public ListImageDTO() {
    }

    public List<ImageDTO> getImageDTOS() {
        return imageDTOS;
    }

    public void setImageDTOS(List<ImageDTO> imageDTOS) {
        this.imageDTOS = imageDTOS;
    }
}
