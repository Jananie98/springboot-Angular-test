package myposapp.demo.controller;

import com.jananie.myapp2.dto.CustomerDTO;
import com.jananie.myapp2.dto.request.CustomerUpdateDTO;
import com.jananie.myapp2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// defines that this is a controller. Rest controller annotation has the ability of handling requests from the outside also
@RestController
// the annotation request mapping make the dispatching process unique through the link
@RequestMapping("api/v1/customer")
// restricts the requests from the frontend
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    // @requestbody annotation convets the json object to (customerDTO) class type to bind data
    public String saveCustomer (@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return "saved";
    }

    @PutMapping("/update")
    public String updateCustomer (@RequestBody CustomerUpdateDTO customerUpdateDTO){
        customerService.updateCustomer(customerUpdateDTO);
        return "Updated";
    }

    @GetMapping(path = "/getbyid", params = "id")
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
      CustomerDTO customerDTO = customerService.getCustomerById(customerId);
      return customerDTO;
    }

    @GetMapping(path = "getallcustomers")
    public List<CustomerDTO> getAllCustomers(){
     List<CustomerDTO> allCustomers = customerService.getAllCustomers();
     return allCustomers;
    }

    @GetMapping(path = "getallcustomersbyactivestate" , params = "status")
    public List<CustomerDTO> getAllCustomersByActiveState(@RequestParam(value = "status") boolean activeState){
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActiveState(activeState);
        return allCustomers;
    }

    @DeleteMapping(path = "deletecustomer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") int customerId){
        String deleted = customerService.deleteCustomer(customerId);
        return deleted;
    }
}
