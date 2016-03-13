package cn.com.ttblog.ssmbootstrap_table.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cn.com.ttblog.ssmbootstrap_table.model.User;
/**
 * spring mvc 数据较验方式
 * @author netbuffer
 *
 */
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	/**
	 * 校验数据
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", null,
				"name is empty.");
		User user = (User) target;
		if (null == user.getName() || "".equals(user.getName())) {
			errors.rejectValue("name", null, "name is empty.");
		}
		if(null==user.getAge()||"".equals(user.getAge())){
			errors.rejectValue("age", null, "age is empty.");
		}
	}
}
