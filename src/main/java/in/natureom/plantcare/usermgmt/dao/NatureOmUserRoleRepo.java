package in.natureom.plantcare.usermgmt.dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureOmUserRoleRepo extends CrudRepository<NatureOmUserRole, Long> {

	public ArrayList<NatureOmUserRole> findByRole(NatureOmRole role);

	public NatureOmUserRole findByUserName(String userName);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM natureom_user_role WHERE user_id=?1", nativeQuery = true)
	public void deleteByUserId(Long userId);

}
