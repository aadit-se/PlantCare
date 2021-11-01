package in.natureom.plantcare.plantcare.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantCareIntervalRepo extends CrudRepository<PlantCareInterval,Integer>{
    
}
