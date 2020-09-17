package project.model;

public class CartInformation {
    private int accountId;
    private String shipAddress;
    private String shipPhone;
    private int transportersId;
    private OrdersEntity.PaymentType paymentType;

    public CartInformation(int accountId, String shipAddress, String shipPhone, int transportersId, OrdersEntity.PaymentType paymentType) {
        this.accountId = accountId;
        this.shipAddress = shipAddress;
        this.shipPhone = shipPhone;
        this.transportersId = transportersId;
        this.paymentType = paymentType;
    }

    public CartInformation() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public OrdersEntity.PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(OrdersEntity.PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public int getTransportersId() {
        return transportersId;
    }

    public void setTransportersId(int transportersId) {
        this.transportersId = transportersId;
    }
}
