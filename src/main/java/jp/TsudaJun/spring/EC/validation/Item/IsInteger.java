package jp.TsudaJun.spring.EC.validation.Item;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import jp.TsudaJun.spring.EC.validation.User.AlreadyExists;
import jp.TsudaJun.spring.EC.validator.Item.IsIntegerValidator;
import jp.TsudaJun.spring.EC.validator.User.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsIntegerValidator.class}) // Validatorの実装クラスを指定
@ReportAsSingleViolation
public @interface IsInteger {
	
	String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface List {
        IsInteger[] value();
    }

}
