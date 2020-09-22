package project.dto;

import java.util.List;

public class ListAccountDTO {
    private List<AccountDTO> accountDTOList;

    public ListAccountDTO(List<AccountDTO> accountDTOList) {
        this.accountDTOList = accountDTOList;
    }

    public List<AccountDTO> getAccountDTOList() {
        return accountDTOList;
    }

    public void setAccountDTOList(List<AccountDTO> accountDTOList) {
        this.accountDTOList = accountDTOList;
    }
}
