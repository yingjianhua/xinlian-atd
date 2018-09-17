package com.irille.atd.interceptor;

import com.irille.core.web.config.AppConfig;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ItpDbTransaction extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		String rtn = null;
		String path = actionInvocation.getProxy().getActionName();
		
		try {
			rtn = actionInvocation.invoke();
			String[] ps = path.split("\\_");
			if (ps[ps.length - 1].equals("list") == false) // 查询不处理事务提交
				AppConfig.db_connection_commit();
		} catch (Exception e) {
			AppConfig.db_connection_rollback();
			throw e;
		} finally {
			AppConfig.db_connection_commit();
			AppConfig.db_connection_close();
		}
		return rtn;
	}
}
