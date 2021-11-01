package in.natureom.plantcare.usermgmt.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NatureOmRoleRepo extends CrudRepository<NatureOmRole,Long>{
    NatureOmRole findByRoleName(String roleName);
    
}