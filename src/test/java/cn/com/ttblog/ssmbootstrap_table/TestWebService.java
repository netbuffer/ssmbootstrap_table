package cn.com.ttblog.ssmbootstrap_table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Arrays;

public class TestWebService {

    private static Logger LOGGER = LoggerFactory.getLogger(TestWebService.class);

    @Test
    public void testInvoke() throws Exception {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/ssm/cxf/userservice?wsdl");
        QName opName = new QName("http://ttblog.com/ssmsoap", "findById");
        Object[] results = client.invoke("findById", 2L);
        LOGGER.info("results:{},result[0]:{}", Arrays.deepToString(results), ToStringBuilder.reflectionToString(results[0], ToStringStyle.JSON_STYLE));

    }
}
