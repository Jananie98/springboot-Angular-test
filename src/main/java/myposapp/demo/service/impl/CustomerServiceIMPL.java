package myposapp.demo.service.impl;

import com.jananie.myapp2.dto.CustomerDTO;
import com.jananie.myapp2.dto.request.CustomerUpdateDTO;
import com.jananie.myapp2.entity.Customer;
import com.jananie.myapp2.repo.CustomerRepo;
import com.jananie.myapp2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getContactNumber(),
                customerDTO.getCustomerNic(),
                customerDTO.isActive()
        );
        customerRepo.save(customer);
        return customerDTO.getCustomerName();
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
       if(customerRepo.existsById(customerUpdateDTO.getCustomerId())){
          Customer customer  = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
          customer.setCustomerName(customerUpdateDTO.getCustomerName());
          customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
          customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

          customerRepo.save(customer);
          return customerUpdateDTO.getCustomerName()+"Updated";
       }else{
           throw new RuntimeException("No data Found");
       }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)){
           Customer customer = customerRepo.getReferenceById(customerId);
           CustomerDTO customerDTO = new CustomerDTO(
                   customer.getCustomerId(),
                   customer.getCustomerName(),
                   customer.getCustomerAddress(),
                   customer.getCustomerSalary(),
                   customer.getContactNumber(),
                   customer.getCustomerNic(),
                   customer.isActive()

           );
           return customerDTO;

        }else{
            throw new RuntimeException("No customer");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for(Customer customer : getAllCustomers){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getCustomerNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "Deletion Successfully" + customerId;
        }else {
            throw new RuntimeException("No Customer Found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {
        // this is the code to be changed
        // there is no pre defined method foe this
        // change the method name findAllblabla()
        List<Customer> getAllCustomers = customerRepo.findAllByActiveEquals(activeState);
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        // this code segmant is identical for every scenario
        // adding filtered data to the customer DTO
        for(Customer customer : getAllCustomers){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getCustomerNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
