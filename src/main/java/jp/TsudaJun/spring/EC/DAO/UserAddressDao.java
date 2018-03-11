package jp.TsudaJun.spring.EC.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import jp.TsudaJun.spring.EC.model.User;
import jp.TsudaJun.spring.EC.model.UserAddress;

@Repository
@Transactional
public class UserAddressDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public List<UserAddress> getAddressByUserid(String userid){
			List<UserAddress> ret = manager.createNativeQuery("select * from useraddress where userid = :userid", UserAddress.class)
					.setParameter("userid", userid)
					.getResultList();
			return ret;
	}
	
	@Transactional
	public void persist(UserAddress address) {
		manager.persist(address);
	}
	
	

}
