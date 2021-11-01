package in.natureom.plantcare.usermgmt.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.natureom.plantcare.usermgmt.dto.CustomerCustomerSpaceDto;
import in.natureom.plantcare.usermgmt.dto.CustomerInformationDto;
import in.natureom.plantcare.usermgmt.dto.CustomerListDto;
import in.natureom.plantcare.usermgmt.service.NomCustomerService;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/nom-customer")
public class NatureOmCustomerController {

    @Autowired
    NomCustomerService customerService;

    @GetMapping(value = "/customers/")
    public ResponseEntity<ArrayList<CustomerInformationDto>> getCustomers()
    {
        log.info("inside getCustomers");
        ArrayList<CustomerInformationDto> customerDtos= customerService.getCustomers();
        return new ResponseEntity<>(customerDtos,NatureOmUtil.isObjectValid(customerDtos) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping(value = "/customersSpaces/")
    public ResponseEntity<ArrayList<CustomerCustomerSpaceDto>> customersSpaces()
    {
        log.info("inside customerSpaces");
        ArrayList<CustomerCustomerSpaceDto> customersSpaces = customerService.getCustomersSpaces();
        return new ResponseEntity<>(customersSpaces,  NatureOmUtil.isObjectValid(customersSpaces) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);         
    }

    // URL for mobile developer
    @GetMapping(value = "/AcustomerSpaces/{customerId}")
    public ResponseEntity<ArrayList<CustomerCustomerSpaceDto>> AgetCustomers(@PathVariable("customerId") Long customerId)
    {
        log.info("inside AgetCustomers");
        ArrayList<CustomerCustomerSpaceDto> customerSpaces = customerService.getCustomerSpaces(customerId);
        return new ResponseEntity<>(customerSpaces,  NatureOmUtil.isObjectValid(customerSpaces) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);         
    }

    //url for UI developer 
    @GetMapping(value = "/BcustomerSpaces/{customerId}")
    public ResponseEntity<ArrayList<CustomerCustomerSpaceDto>> BgetCustomers(@PathVariable("customerId") Long customerId)
    {
        log.info("inside BgetCustomers");
        ArrayList<CustomerCustomerSpaceDto> customerSpaces = customerService.getCustomerSpaces(customerId);
        return new ResponseEntity<>(customerSpaces,  NatureOmUtil.isObjectValid(customerSpaces) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping(value = "/{customerId}/")
    public ResponseEntity<CustomerInformationDto> getCustomer(@PathVariable("customerId") Long customerId)
    {
        log.info("inside getCustomer");
        CustomerInformationDto customer = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customer, NatureOmUtil.isObjectValid(customer) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);         
    }

    @GetMapping(value ="/customerList/")
    public ResponseEntity<ArrayList<CustomerListDto>> getCustomerList()
    { 
        log.info("inside getCustomerList");
        ArrayList<CustomerListDto> customerNamesDtos= customerService.getCustomerList();
        return new ResponseEntity<>(customerNamesDtos,NatureOmUtil.isObjectValid(customerNamesDtos) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

}
