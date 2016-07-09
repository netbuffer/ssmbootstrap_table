package cn.com.ttblog.ssmbootstrap_table;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.util.ValidatorUtils;

public class TestValidator {
	public static boolean validateRequired(Object bean, Field field) {
		return !GenericValidator.isBlankOrNull(ValidatorUtils.getValueAsString(bean, field.getProperty()));
	}
}
