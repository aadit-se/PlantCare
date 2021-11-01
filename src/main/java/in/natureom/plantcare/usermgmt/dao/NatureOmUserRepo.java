package in.natureom.plantcare.usermgmt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NatureOmUserRepo extends CrudRepository<NatureOmUser, Long>
{
	NatureOmUser findByUserId(Long userId);
}