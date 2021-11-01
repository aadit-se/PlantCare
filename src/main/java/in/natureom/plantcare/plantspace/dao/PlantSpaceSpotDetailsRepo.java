package in.natureom.plantcare.plantspace.dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantSpaceSpotDetailsRepo extends CrudRepository<PlantSpaceSpotDetails,Long>{
    
    @Query(value = "SELECT * FROM space_spot_details WHERE next_fertilize <=?1 OR next_pesticide <=?1 OR  next_trim <=?1 OR next_water <=?1",nativeQuery = true)
    public ArrayList<PlantSpaceSpotDetails> viewUpcomingDetails(String dt);    

    @Query(value = "SELECT * FROM space_spot_details WHERE details_space_id=?1",nativeQuery = true)
    public ArrayList<PlantSpaceSpotDetails> selectAllSpaceSpots(Long spaceId);

    @Modifying
    @Transactional
    @Query(value = "delete from space_spot_details where details_space_id=?1",nativeQuery = true)
    public void deleteDetailsOfSpace(Long spaceId);

    @Modifying
    @Transactional
    @Query(value = "delete from space_spot_details where details_spot_id=?1",nativeQuery = true)
    public void deleteDetailsOfSpot(Long spaceId);

    @Query(value ="select * from  space_spot_details where details_spot_id=?1" ,nativeQuery = true)
    public PlantSpaceSpotDetails getSpotDetails(Long spotId);

}
