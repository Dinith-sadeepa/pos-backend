package lk.ijse.absd.pos.repository;

import lk.ijse.absd.pos.entity.Customer;

import java.util.List;

public interface CustomerRepo {
    boolean createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(String cusId);
}
