package jp.TsudaJun.spring.EC.validator.UserAddress;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.TsudaJun.spring.EC.validation.UserAddress.IsNumber;

public class IsNumberValidator implements ConstraintValidator<IsNumber, String>{

	public void initialize(IsNumber annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
    		
    		try {
    			Integer.parseInt(value);
    		}catch(Exception e) {
    			return false;
    		}
    		return true;
    }
    
}
