package cn.com.ttblog.ssmbootstrap_table;


import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResult;
import org.apache.commons.validator.ValidatorResults;
import org.xml.sax.SAXException;

import cn.com.ttblog.ssmbootstrap_table.model.User;

public class ValidateExample{

    public static void main(String[] args)
        throws ValidatorException, IOException, SAXException {
        InputStream in = null;
        ValidatorResources resources = null;
        try {
            in = ValidateExample.class.getResourceAsStream("validator-example.xml");
            resources = new ValidatorResources(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        User bean = new User();
        Validator validator = new Validator(resources, "user");
        validator.setParameter(Validator.BEAN_PARAM, bean);
        ValidatorResults results = null;
        results = validator.validate();
        printResults(bean, results, resources);

        bean.setAge(200);
        bean.setName("aaa");
        printResults(bean, results, resources);
        
        validator.setOnlyReturnErrors(true);
        results = validator.validate();
        printResults(bean, results, resources);
        
        validator.setOnlyReturnErrors(false);
        bean.setAge(19);
        results = validator.validate();
        printResults(bean, results, resources);
    }

    /**
     * Dumps out the Bean in question and the results of validating it.
     */
    public static void printResults(
        User bean,
        ValidatorResults results,
        ValidatorResources resources) {
            
        boolean success = true;

        // Start by getting the form for the current locale and Bean.
        Form form = resources.getForm(Locale.getDefault(), "user");

        System.out.println("\n\nValidating:");
        System.out.println(bean);

        // Iterate over each of the properties of the Bean which had messages.
        Iterator propertyNames = results.getPropertyNames().iterator();
        while (propertyNames.hasNext()) {
            String propertyName = (String) propertyNames.next();

            // Get the Field associated with that property in the Form
            Field field = form.getField(propertyName);

            // Get the result of validating the property.
            ValidatorResult result = results.getValidatorResult(propertyName);

            // Get all the actions run against the property, and iterate over their names.
            Map actionMap = result.getActionMap();
            Iterator keys = actionMap.keySet().iterator();
            while (keys.hasNext()) {
                String actName = (String) keys.next();

                // Get the Action for that name.
                ValidatorAction action = resources.getValidatorAction(actName);

                // If the result is valid, print PASSED, otherwise print FAILED
                System.out.println(
                    propertyName
                        + "["
                        + actName
                        + "] ("
                        + (result.isValid(actName) ? "PASSED" : "FAILED")
                        + ")");

                //If the result failed, format the Action's message against the formatted field name
                if (!result.isValid(actName)) {
                    success = false;
                }
            }
        }
        if (success) {
            System.out.println("FORM VALIDATION PASSED");
        } else {
            System.out.println("FORM VALIDATION FAILED");
        }

    }

}