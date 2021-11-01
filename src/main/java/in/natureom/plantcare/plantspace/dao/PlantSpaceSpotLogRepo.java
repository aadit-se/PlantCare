package in.natureom.plantcare.plantspace.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantSpaceSpotLogRepo extends CrudRepository<PlantSpaceSpotLog,Long>{
    
}
