package Controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.security.Principal;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.UserDao;
import jp.TsudaJun.spring.EC.model.User;
import jp.TsudaJun.spring.EC.Controller.UserInfoController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-config.xml",
"file:src/main/webapp/WEB-INF/mvc-config-test.xml"})
@WebAppConfiguration
public class UserInfoControllerTest {
	
	private MockMvc mvc;
	
	User user;

    
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    
    @InjectMocks
    private UserInfoController userInfoController;

    @Mock
    private UserDao dao;
    
    @Before
    public void setup() {
            mvc = MockMvcBuilders.standaloneSetup(this.userInfoController).build();
            user = new User();
            user.setUserid("j");
            user.setUsername("j");
            user.setPassword("j");
            
    }

//    @Test
//	public void showTest() throws Exception{
//    		when(principal.getName()).thenReturn("j");
//		when(dao.getUserById("j")).thenReturn(user);
//		assertEquals("j", ((User) userInfoController.show(null, new ModelAndView()).getModel().get("user")).getUsername());
//	}

}
