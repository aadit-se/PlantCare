package in.natureom.plantcare.plantspace.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.natureom.plantcare.usermgmt.dao.NatureOmUserRole;

@Repository
public interface PlantSpaceRepo extends CrudRepository<PlantSpace, Long> {

    public ArrayList<PlantSpace> findByPlantSpacePlantmasterId(NatureOmUserRole plantMaster);
    ArrayList<PlantSpace> findByPlantSpaceCustomerId(NatureOmUserRole customer);

    @Query(value = "select * from plant_space where space_customer_id=?1 or space_plantmaster_id=?1",nativeQuery = true)
    ArrayList<PlantSpace> nullUserValue(Long userId);

}
