package cn.com.ttblog.ssmbootstrap_table;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestWebService {

	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);

	@Test
	public void testWebService() throws IOException {
		// 服务的地址
		URL wsUrl = new URL("http://localhost:8080/ssmbootstrap_table/cxf/user?wsdl");

		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

		OutputStream os = conn.getOutputStream();

		// 请求体
		String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://webservice.ssmbootstrap_table.ttblog.com.cn/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
				+ "<soapenv:Body> <q0:getUser><arg0>1</arg0>  </q0:getUser> </soapenv:Body> </soapenv:Envelope>";

		os.write(soap.getBytes());

		InputStream is = conn.getInputStream();

		byte[] b = new byte[1024];
		int len = 0;
		String s = "";
		while ((len = is.read(b)) != -1) {
			String ss = new String(b, 0, len, "UTF-8");
			s += ss;
		}
		System.out.println("结果:"+s);

		is.close();
		os.close();
		conn.disconnect();
	}
}
