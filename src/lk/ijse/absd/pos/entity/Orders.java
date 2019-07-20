package lk.ijse.absd.pos.entity;

public class Orders {
    private String orderId;
    private String orderDate;
    private String cusId;

    public Orders() {
    }

    public Orders(String orderId, String orderDate, String cusId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cusId = cusId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomer() {
        return cusId;
    }

    public void setCustomer(String customer) {
        this.cusId = customer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", cusId=" + cusId +
                '}';
    }
}
