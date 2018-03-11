package jp.TsudaJun.spring.EC.validator.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.TsudaJun.spring.EC.DAO.UserDao;
import jp.TsudaJun.spring.EC.model.User;
import jp.TsudaJun.spring.EC.validation.User.AlreadyExists;

public class AlreadyExistsValidator implements ConstraintValidator<AlreadyExists, String>{

	@Autowired
	UserDao dao;
	
	public void initialize(AlreadyExists annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
    		
    		if(dao != null) {
	    		User user = dao.getUserById(value);
	    		if(user != null)
	    			return false;
    		}
    		return true;
    }

}
