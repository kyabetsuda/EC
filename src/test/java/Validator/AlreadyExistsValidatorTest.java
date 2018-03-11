package Validator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jp.TsudaJun.spring.EC.validator.User.AlreadyExistsValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-config.xml",
								"file:src/main/webapp/WEB-INF/mvc-config-test.xml"})
@WebAppConfiguration
public class AlreadyExistsValidatorTest {
	
	@Autowired
	AlreadyExistsValidator validator;

	@Test
	public void getUser_test() {
		assertEquals(false, validator.isValid("a", null));
	}

}
