package project.dto;

import java.util.List;

public class ListProductDTO {
    private List<ProductDTO> productDTOList;

    public ListProductDTO(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }

    public ListProductDTO() {
    }

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }
}
