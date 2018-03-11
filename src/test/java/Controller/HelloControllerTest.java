package Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.Controller.HelloController;
import jp.TsudaJun.spring.EC.DAO.UserDao;
import jp.TsudaJun.spring.EC.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-config.xml"})
@WebAppConfiguration
public class HelloControllerTest {
	
	private MockMvc mvc;
	
	List<User> list = new ArrayList<User>();
    
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    
    @InjectMocks
    private HelloController helloController;

    @Mock
    private UserDao dao;
    
    @Before
    public void setup() {
            mvc = MockMvcBuilders.standaloneSetup(this.helloController).build();
            User user = new User();
            user.setUserid("j");
            user.setUsername("j");
            user.setPassword("j");
            list.add(user);
    }
	
	@Test
	public void showTest() throws Exception{
		when(dao.getAllUsers()).thenReturn(list);
		assertEquals(1, ((List<User>) helloController.show(new ModelAndView()).getModel().get("datalist")).size());
	}

}
