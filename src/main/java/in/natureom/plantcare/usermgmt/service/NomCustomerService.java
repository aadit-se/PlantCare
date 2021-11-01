package in.natureom.plantcare.usermgmt.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.natureom.plantcare.plantspace.dao.PlantSpace;
import in.natureom.plantcare.plantspace.dao.PlantSpaceRepo;
import in.natureom.plantcare.plantspace.service.PlantSpaceService;
import in.natureom.plantcare.usermgmt.dao.NatureOmRole;
import in.natureom.plantcare.usermgmt.dao.NatureOmUser;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRepo;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRole;
import in.natureom.plantcare.usermgmt.dao.NatureOmUserRoleRepo;
import in.natureom.plantcare.usermgmt.dto.CustomerCustomerSpaceDto;
import in.natureom.plantcare.usermgmt.dto.CustomerInformationDto;
import in.natureom.plantcare.usermgmt.dto.CustomerListDto;
import in.natureom.plantcare.usermgmt.dto.CustomerSpaceDto;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NomCustomerService{

    
    @Autowired
    NomUserService userService;

   @Autowired
   NatureOmUserRoleRepo userRoleDao;

   @Autowired
   NatureOmUserRepo userDao;

   @Autowired
   PlantSpaceRepo spaceDao;

   @Autowired
   NomRoleService roleService;

   @Autowired
   PlantSpaceService spaceService;

    public  ArrayList<CustomerInformationDto> getCustomers()
    {
        log.info("inside getCustomers, list being fetched");
        try{    
            NatureOmRole role =roleService.getByRoleName("customer");
            ArrayList<NatureOmUserRole> userRoles= userRoleDao.findByRole(role);
            log.info("list was fetched");
            ArrayList<CustomerInformationDto> customerDtos= new ArrayList<>();
            for(int i=0; i<userRoles.size();i++)
            {
                customerDtos.add(CustomerModelTODto(userDao.findByUserId(userRoles.get(i).getUser_role_id())));  
            }
            return customerDtos;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }    
    }

    public  CustomerInformationDto getCustomer(Long customerId)
    {
        try{
            log.info("inside getCustomer");
            NatureOmUser user=userDao.findByUserId(customerId);
            if(!NatureOmUtil.isObjectValid(user))
            throw new Exception("no such object");
            return CustomerModelTODto(user);
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null; 
        }

    }


    public ArrayList<CustomerCustomerSpaceDto> getCustomerSpaces(Long customerId) {

        try {
            log.info("inside of getCustomerSpaces, fetching object");
            NatureOmUserRole customer = userRoleDao.findById(customerId).get();
            List<PlantSpace> customerSpaces = spaceService.getPlantSpaceByCustomerId(customer);
            ArrayList<CustomerCustomerSpaceDto> customerSpaceDtos = new ArrayList<>();
            ArrayList<CustomerSpaceDto> spaceDtos= new ArrayList<>();    
            CustomerCustomerSpaceDto customerSpaceDto = new CustomerCustomerSpaceDto();
            customerSpaceDto.setUserCustomerId(customer.getUser_role_id());
            customerSpaceDto.setCustomerName(customer.getUserName());
            customerSpaceDto.setCustomerImgUrl(null);
            if(!customerSpaces.isEmpty()) {
            	 customerSpaces.forEach((customerSpace) -> {
                     spaceDtos.add(CustomerSpaceModelToDto(customerSpace));
                 });
            }           
            customerSpaceDto.setPlantSpaces(spaceDtos);
            customerSpaceDtos.add(customerSpaceDto);
            log.info("object was fetched");
            return customerSpaceDtos;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public ArrayList<CustomerCustomerSpaceDto> getCustomersSpaces() 
    {
        log.info("inside of getCustomersSpaces");
        try{
        ArrayList<CustomerInformationDto> customers=getCustomers();
        if(!NatureOmUtil.isObjectValid(customers))
        throw new Exception("no such object");
        ArrayList<CustomerCustomerSpaceDto> customersSpaces= new ArrayList<>(); 
        for(int i=0;i<customers.size();i++)
        {
            ArrayList<CustomerCustomerSpaceDto> customerSpaces=getCustomerSpaces(customers.get(i).getCustomerId());
            customersSpaces.add(customerSpaces.get(0));
        }
        return customersSpaces;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }

    }

    public  ArrayList<CustomerListDto> getCustomerList()
    {
        try{
        log.info("in getCustomerList, fetching list");
        NatureOmRole role =roleService.getByRoleName("customer");
        ArrayList<NatureOmUserRole> userRoles= userRoleDao.findByRole(role);
        ArrayList<CustomerListDto> customerListDtos= new ArrayList<>();
        for(int i=0; i<userRoles.size();i++)
        {
        	customerListDtos.add(CustomerModelTOListDto(userDao.findByUserId(userRoles.get(i).getUser_role_id())));  
        }
        log.info("list was fetched");
        return customerListDtos;
       }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
}
    
    public CustomerListDto CustomerModelTOListDto(NatureOmUser user) {
        CustomerListDto customerNameDto = new CustomerListDto();
        customerNameDto.setCustomerId(user.getUserId());
        customerNameDto.setCustomerName(user.getUserName());
        log.debug("in NomUserService inside NomUserModelToDto converting Model  to Dto object");
        return customerNameDto;
}

    public CustomerSpaceDto CustomerSpaceModelToDto(PlantSpace space) {
        CustomerSpaceDto spaceDto= new CustomerSpaceDto();
        spaceDto.setPlantMasterId(space.getPlantSpacePlantmasterId().getUser_role_id());
        spaceDto.setPlantSpaceId(space.getPlantSpaceId());
        spaceDto.setPlantSpaceName(space.getPlantSpaceName());
        spaceDto.setPlantSpaceAddress(space.getPlantSpaceAddress());
        spaceDto.setPlantSpaceStatus(space.getPlantSpaceStatus());
        return spaceDto;
    }



    public CustomerInformationDto CustomerModelTODto(NatureOmUser user) {
        CustomerInformationDto customerDto = new CustomerInformationDto();
        customerDto.setCustomerId(user.getUserId());
        customerDto.setCustomerContact(user.getUserContact());
        customerDto.setCustomerHome(user.getUserHome());
        customerDto.setCustomerLocality(user.getUserLocality());
        customerDto.setCustomerLandMark(user.getUserLandMark());
        customerDto.setCustomerCity(user.getUserCity());
        customerDto.setCustomerState(user.getUserState());
        customerDto.setCustomerPin(user.getUserPin());
        customerDto.setCustomerEmail(user.getUserEmail());
        customerDto.setCustomerGender(user.getUserGender());
        customerDto.setCustomerName(user.getUserName());
        customerDto.setCustomerDob(user.getUserDob());
        log.debug("in NomUserService inside NomUserModelToDto converting Model  to Dto object");
        return customerDto;
    }

   
}