package in.natureom.plantcare.usermgmt.service;

import in.natureom.plantcare.usermgmt.dao.*;
import in.natureom.plantcare.util.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class NomUserRoleService implements CommonConstants  {

    @Autowired
    NatureOmUserRoleRepo userRoleDao;

    @Autowired
    NatureOmRoleRepo roleDao;

    public NatureOmUserRole getUser(String userName) {
        try{
        
        log.info("in getUser, fetching object");
        NatureOmUserRole userRole = userRoleDao.findByUserName(userName);
        log.info("oject was fetched");
        log.debug("object=", userRole);
        return userRole;
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }

	public String addUserRole(NatureOmUser user, String roleName) {
		try {

			log.info("In addUserRole:" + user);

			NatureOmUserRole userRole = new NatureOmUserRole();
			userRole.setUser_role_id(user.getUserId()); // TODO this must be unique - avinash
			userRole.setUserName(user.getUserName());
			userRole.setUser(user);

			if (roleName.equals(ROLE_CUSTOMER)) {
				log.info("In ciustomer");
				NatureOmRole role = roleDao.findByRoleName(ROLE_CUSTOMER);
				userRole.setRole(role);
				userRole.setRoleName(role.getRoleName());
				log.info("obj set");
			}
			else if (roleName.equals(ROLE_PLANT_MASTER)) {
				log.info("In PlamtMaster");
				NatureOmRole role = roleDao.findByRoleName(ROLE_PLANT_MASTER);
				userRole.setRole(role);
				userRole.setRoleName(role.getRoleName());
				log.info("obj set");
			}
			else if (roleName.equals(ROLE_ADMIN)) {
				NatureOmRole role = roleDao.findByRoleName(ROLE_ADMIN);
				userRole.setRole(role);
				userRole.setRoleName(role.getRoleName());
			}

			userRoleDao.save(userRole);

			log.debug("object=", userRole);
			return Success;
		} catch (Exception e) {
			log.error(e.getMessage());
			return Failed;
		}

	}
    

    public String deleteUserWithUserId(Long userId) {
        try{
        log.info("In deleteUserWithUserId:"+userId);
        userRoleDao.deleteByUserId(userId);
        log.info("user was deleted");
        return Success;
    }
    catch(Exception e)
    {
        log.error(e.getMessage());
        return Failed;
    }
    }

    public void addCustomerRole(NatureOmUser user) {
    }
}
