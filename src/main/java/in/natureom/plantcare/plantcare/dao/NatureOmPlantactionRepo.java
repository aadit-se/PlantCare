package in.natureom.plantcare.plantcare.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureOmPlantactionRepo extends CrudRepository<NatureOmPlantCareAction,Integer>{
    
}
