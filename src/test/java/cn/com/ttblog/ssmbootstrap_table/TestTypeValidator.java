package cn.com.ttblog.ssmbootstrap_table;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.util.ValidatorUtils;

public class TestTypeValidator {
	public static boolean validateInt(Object bean, Field field){
		return GenericValidator.isInt(ValidatorUtils.getValueAsString(bean, field.getProperty()));
	}
}
