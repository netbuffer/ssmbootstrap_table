package cn.com.ttblog.ssmbootstrap_table.directive;

import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloTag extends Directive{

	private static final Logger log=LoggerFactory.getLogger(HelloTag.class);
	
	@Override
	public String getName() {
		return "hello";
	}

	@Override
	public int getType() {
		return LINE;
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer,Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		SimpleNode sn = (SimpleNode) node.jjtGetChild(0);   
		writer.write((String)sn.value(context));
		return false;
	}
	
}
 