package in.natureom.plantcare.plantspace.dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantSpaceSpotRepo extends CrudRepository<PlantSpaceSpot,Long>{
    
    @Modifying
    @Transactional
    @Query(value = "delete from plant_space_spot where plant_space_id=?1",nativeQuery = true)
    public void deleteSpotsOfSpace(Long spaceId);   

    @Query(value = "select * from plant_space_spot where plant_space_id=?1" ,nativeQuery = true)
    public ArrayList<PlantSpaceSpot> getPlantSpotsOfSpace(Long spaceId);
}
