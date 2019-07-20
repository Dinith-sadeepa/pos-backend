package lk.ijse.absd.pos.service.impl;

import lk.ijse.absd.pos.dto.CustomerDTO;
import lk.ijse.absd.pos.entity.Customer;
import lk.ijse.absd.pos.repository.CustomerRepo;
import lk.ijse.absd.pos.repository.impl.CustomerRepoImpl;
import lk.ijse.absd.pos.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo customerRepo;

    public CustomerServiceImpl() {
        customerRepo = new CustomerRepoImpl();
    }

    @Override
    public boolean createCustomer(CustomerDTO customerDTO) {
        return customerRepo.createCustomer(new Customer(customerDTO.getId(),
                customerDTO.getName(), customerDTO.getAddress()));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerRepo.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (!allCustomers.isEmpty() || allCustomers != null) {
            allCustomers.forEach(customer -> {
                customerDTOS.add(new CustomerDTO(customer.getCusId(), customer.getCusName(), customer.getCusAddress()));
            });
        }
        return customerDTOS;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return customerRepo.updateCustomer(new Customer(customerDTO.getId(),
                customerDTO.getName(), customerDTO.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String cusId) {
        return customerRepo.deleteCustomer(cusId);
    }
}
