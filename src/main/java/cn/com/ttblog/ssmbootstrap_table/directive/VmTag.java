package cn.com.ttblog.ssmbootstrap_table.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 自定义标签渲染指定vm文件 
 * @package cn.com.ttblog.ssmbootstrap_table.directive
 */
public class VmTag extends Directive{

	private static final Logger log=LoggerFactory.getLogger(VmTag.class);
	
	private static final VelocityEngine ve;
	static{
		ve= new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
	}
	@Override
	public String getName() {
		return "vm";
	}

	@Override
	public int getType() {
		return LINE;
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer,Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		SimpleNode title = (SimpleNode) node.jjtGetChild(0);
		SimpleNode vm = (SimpleNode) node.jjtGetChild(1);
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("title", title.value(context));
		param.put("vm", vm.value(context));
		long m1=System.currentTimeMillis();
		String tpl=renderTpl(param);
		long m2=System.currentTimeMillis();
		log.debug("渲染{}耗时:{}s,参数:{}",param.get("vm").toString(),(double)(m2-m1)/1000,param);
		writer.write(tpl);
		return false;
	}
	
	private String renderTpl(Map<String, Object> param){
		//"../../"找到项目根目录下"../../"+param.get("vm").toString()
		return VelocityEngineUtils.mergeTemplateIntoString(ve,"tag.vm", "utf-8", param);
	}
	
}
 