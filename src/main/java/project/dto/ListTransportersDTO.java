package project.dto;

import java.util.List;

public class ListTransportersDTO {
    List<TransportersDTO> transportersDTOList;

    public ListTransportersDTO(List<TransportersDTO> transportersDTOList) {
        this.transportersDTOList = transportersDTOList;
    }

    public ListTransportersDTO() {
    }

    public List<TransportersDTO> getTransportersDTOList() {
        return transportersDTOList;
    }

    public void setTransportersDTOList(List<TransportersDTO> transportersDTOList) {
        this.transportersDTOList = transportersDTOList;
    }
}
