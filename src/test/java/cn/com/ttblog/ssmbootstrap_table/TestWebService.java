package cn.com.ttblog.ssmbootstrap_table;

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
    public void test() throws Exception {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/ssm/cxf/userservice?wsdl");
        QName opName = new QName("http://ttblog.com/ssmsoap", "findById");
        Object[] results = client.invoke("findById", 1L);
        LOGGER.info("results:{}", Arrays.deepToString(results));
    }
}
