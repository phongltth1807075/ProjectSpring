package project.model.specification;

public class SearchCriteria {
    private String key; //tim kiem theo truong nao.
    private String operation; //tim kiem theo kieu gi: lon hon, nho hon, giong...
    private Object value; // gia tri can tim la gi.

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
