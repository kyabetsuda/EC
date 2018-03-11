package jp.TsudaJun.spring.EC.DAO;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.TsudaJun.spring.EC.model.User;

@Transactional
@Repository
public class UserDao{
	
	@PersistenceContext
	EntityManager manager;
	
	public List<User> getAllUsers(){
			List<User> ret = manager.createNativeQuery("select * from user", User.class).getResultList();
			return ret;
	}
	
	public User getUser(String user_id, String password) {
			
		User ret = null;
		try {
			ret = (User) manager.createNativeQuery("select * from user where userid = :id AND password = :password", User.class)
						.setParameter("id", user_id)
						.setParameter("password", password)
						.getSingleResult();	
		}catch (Exception e) {

		}
		
		return ret;
	}
	
	public User getUserById(String userid) {
		
		User user = null;
		try {
			user = (User) manager.createNativeQuery("select * from user where userid = :id", User.class)
				.setParameter("id", userid)
				.getSingleResult();
		}catch(Exception e) {
			
		}
		
		return user;
	}
	
	@Transactional
	public void persist(String userid, String username, String password) {
			User user = new User();
			user.setUserid(userid);
			user.setUsername(username);
			user.setPassword(password);
			manager.persist(user);		
	}
	
	@Transactional
	public void persist(User user) {
		manager.persist(user);
	}
	
	@Transactional
	public void merge(User user) {
		manager.merge(user);
	}
	
	@Transactional
	public void flush() {
		manager.flush();
	}
	
	@Transactional
	public void delete(String userid){
			User user = this.getUserById(userid);
			manager.remove(user);
	}

}
