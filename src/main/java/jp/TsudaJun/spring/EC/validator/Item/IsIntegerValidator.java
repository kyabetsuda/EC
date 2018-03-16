package jp.TsudaJun.spring.EC.validator.Item;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.TsudaJun.spring.EC.validation.Item.IsInteger;
import jp.TsudaJun.spring.EC.validation.User.AlreadyExists;

public class IsIntegerValidator implements ConstraintValidator<IsInteger, Integer>{

	
	public void initialize(IsInteger annotation) {
    }
	
	public boolean isValid(Integer arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		if(arg0 == 0)
			return false;
		
		return true;
	}
	
	

}
