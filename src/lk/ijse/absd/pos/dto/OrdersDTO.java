package lk.ijse.absd.pos.dto;

import java.util.List;

public class OrdersDTO {
    private String orderId;
    private String orderDate;
    private CustomerDTO customerDTO;
    private List<OrderDetailDTO> orderDetailsDTOS;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderId, String orderDate, CustomerDTO customerDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerDTO = customerDTO;
    }

    public OrdersDTO(String orderId, String orderDate, CustomerDTO customerDTO, List<OrderDetailDTO> orderDetailsDTOS) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerDTO = customerDTO;
        this.orderDetailsDTOS = orderDetailsDTOS;
    }

    public List<OrderDetailDTO> getOrderDetailsDTOS() {
        return orderDetailsDTOS;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerDTO=" + customerDTO +
                ", orderDetailsDTOS=" + orderDetailsDTOS +
                '}';
    }

    public void setOrderDetailsDTOS(List<OrderDetailDTO> orderDetailsDTOS) {
        this.orderDetailsDTOS = orderDetailsDTOS;
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

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

}
