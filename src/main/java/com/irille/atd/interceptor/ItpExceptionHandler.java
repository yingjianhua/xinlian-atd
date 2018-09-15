package com.irille.atd.interceptor;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.irille.atd.config.AppConfig;
import com.irille.atd.dao.lg.LgAccessDAO;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import irille.action.BaseAction;
import irille.pub.Exp;

public class ItpExceptionHandler extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Long date1 = System.currentTimeMillis();
		boolean isSuccess = true;
		Exception e2 = null;
		try {
			return invocation.invoke();
		} catch (Exception e) {
			isSuccess = false;
			e2 = e;
			//如果是Exp类型的异常,都是通过LOG.err方式抛出来的,异常已经打印过了,不需要重复打印
			if(!(e instanceof Exp)) {
				e.printStackTrace();
			}
			ActionProxy proxy = invocation.getProxy();
			Method method = proxy.getAction().getClass().getMethod(proxy.getMethod());
			if(method.getReturnType().equals(void.class)){
				String msg = e instanceof Exp?((Exp)e).getLastMessage():e.getMessage();
				BaseAction.writeErr(msg);
			}
		} finally {
			Long date2 = System.currentTimeMillis();
			System.err.println("后台处理【"+getJumpUrl(ServletActionContext.getRequest())+"】共消耗【"+(date2-date1)+"】毫秒");
			try {
				LgAccessDAO.add(ServletActionContext.getRequest(), date2-date1, isSuccess, e2);
				AppConfig.db_connection_commit();
			} catch (Exception e) {
				AppConfig.db_connection_rollback();
				e.printStackTrace();
			} finally {
				AppConfig.db_connection_close();
			}
		}
		return null;
	}

	private String getJumpUrl(HttpServletRequest request) {
		String params = getParameters(request);
		if("".equals(params)) {
			return getServletUrl(request);
		} else {
			return getServletUrl(request)+"?"+params;
		}
	}
	private String getParameters(HttpServletRequest request) {
		String params = "";
		Enumeration<?> names = request.getParameterNames();
		int i=0;
		while(names.hasMoreElements()) {
			Object element  = names.nextElement();
			if(i!=0)
				params += "&";
			params += (element+"="+request.getParameter(element.toString()));
			i++;
		}
		return params;
	}
	private String getServletUrl(HttpServletRequest request) {
		return request.getRequestURI();
	}
}
