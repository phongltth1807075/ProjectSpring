package project.model;

import java.util.List;

public class ShoppingCart {
    private List<Cart> list;
    private CartInformation cartInformation;

    public ShoppingCart(List<Cart> list, CartInformation cartInformation) {
        this.list = list;
        this.cartInformation = cartInformation;
    }

    public ShoppingCart() {
    }

    public List<Cart> getList() {
        return list;
    }

    public void setList(List<Cart> list) {
        this.list = list;
    }

    public CartInformation getCartInformation() {
        return cartInformation;
    }

    public void setCartInformation(CartInformation cartInformation) {
        this.cartInformation = cartInformation;
    }
}
