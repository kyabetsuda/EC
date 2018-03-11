package DAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jp.TsudaJun.spring.EC.DAO.UserDao;
import jp.TsudaJun.spring.EC.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-config.xml"})
@WebAppConfiguration
public class UserDaoTest {
	
	UserDao dao = mock(UserDao.class);
	
	List<User> list = new ArrayList<User>();
	User user = new User();
	
	@Before
	public void setUp(){
		user.setUserid("j");
		user.setUsername("j");
		user.setPassword("j");
	}

	@Test
	public void getUser_test() {
		when(dao.getUserById("a")).thenReturn(user);
		assertEquals("j", user.getUserid());
	}

}
