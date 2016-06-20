package cn.com.ttblog.ssmbootstrap_table.webservice;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

/**
 * 可以在这里处理头部认证信息
 * 
 * @author champ
 *
 */
public class LicenseHandler implements SOAPHandler<SOAPMessageContext> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			Boolean out = (Boolean) context
					.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			logger.debug("LicenseHandler:{}",out);
			if (!out) {
				SOAPMessage message = context.getMessage();
				logger.debug("SOAPMessage:{}",ToStringBuilder.reflectionToString(message));
				SOAPEnvelope enve = message.getSOAPPart().getEnvelope();
				SOAPHeader header = enve.getHeader();
				SOAPBody body = enve.getBody();
				Node bn = body.getChildNodes().item(0);
				String partname = bn.getLocalName();
				if ("getUser".equals(partname)) {
					if (header == null) {
						// 添加一个错误信息
						SOAPFault fault = body.addFault();
						fault.setFaultString("头部信息不能为空!");
						throw new SOAPFaultException(fault);
					}
					Iterator<SOAPHeaderElement> iterator = header
							.extractAllHeaderElements();
					if (!iterator.hasNext()) {
						// 添加一个错误信息
						SOAPFault fault = body.addFault();
						fault.setFaultString("头部信息不正确!");
						throw new SOAPFaultException(fault);
					}
					while (iterator.hasNext()) {
						SOAPHeaderElement ele = iterator.next();
						System.out.println(ele.getTextContent());
					}
				}
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {

	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
