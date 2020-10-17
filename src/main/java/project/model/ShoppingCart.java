package project.model;

import java.util.List;

public class ShoppingCart {
    private List<Cart> list;
    private CartInformation cartInformation;
    private String email;

    public ShoppingCart(List<Cart> list, CartInformation cartInformation, String email) {
        this.list = list;
        this.cartInformation = cartInformation;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
