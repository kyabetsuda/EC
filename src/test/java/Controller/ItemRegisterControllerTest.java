package Controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import jp.TsudaJun.spring.EC.Controller.ItemRegisterController;
import jp.TsudaJun.spring.EC.DAO.ItemAttributeDao;
import jp.TsudaJun.spring.EC.DAO.UserDao;
import jp.TsudaJun.spring.EC.model.Item;
import jp.TsudaJun.spring.EC.model.ItemAttribute;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-config.xml"})
@WebAppConfiguration
public class ItemRegisterControllerTest {
	
	private MockMvc mvc;
	
	ItemAttributeDao iaDao = mock(ItemAttributeDao.class);
	List<ItemAttribute> attributes = new ArrayList<ItemAttribute>();
	
    @Rule
    public final MockitoRule rule = MockitoJUnit.rule();
    
    @InjectMocks
    private ItemRegisterController itemRegisterController;
    
    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(this.itemRegisterController).build();
    }
    
    @Test
	public void showTest() throws Exception{
    		when(iaDao.getAllAttributes()).thenReturn(attributes);
		assertEquals("商品登録", (itemRegisterController.show(new Item(),new ModelAndView()).getModel().get("msg")));
	}

}
