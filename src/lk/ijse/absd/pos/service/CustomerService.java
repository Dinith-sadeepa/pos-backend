package lk.ijse.absd.pos.service;

import lk.ijse.absd.pos.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    boolean createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String cusId);
}
