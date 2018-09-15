package com.irille.atd.dao.lg;

import java.util.Date;
import java.util.Enumeration;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.irille.atd.domain.lg.LgAccess;
import com.irille.atd.pub.bean.statistics.Table;

import irille.pub.svr.DbPool;

public class LgAccessDAO {

	public static void add(HttpServletRequest request, Long processTime, boolean isSuccess, Exception e) {
		String remark = null;
		if(e != null)
			remark = e.toString();
		add(request.getRemoteAddr(), request.getRequestURL().toString(), getParameters(request), processTime, isSuccess, remark);
	}

	public static void add(String remoteAddr, String requestUrl, String params, Long processTime, boolean isSuccess, String remark) {
		LgAccess bean = new LgAccess();
		bean.setRemoteaddr(remoteAddr);
		bean.setRequesturl(requestUrl);
		bean.setParams(params);
		bean.setProcesstime(processTime);
		bean.setRequesttime(new Date());
		bean.stSuccess(isSuccess);
		bean.setRemark(remark);
		bean.ins();
	}
	
	private static String getParameters(HttpServletRequest request) {
		StringBuilder params = new StringBuilder();
		Enumeration<?> names = request.getParameterNames();
		boolean first = true;
		while(names.hasMoreElements()) {
			Object element  = names.nextElement();
			if(!first)
				params.append("&");
			params.append(element);
			params.append("=");
			params.append(request.getParameter(element.toString()));
			if(first)
				first = false;
		}
		return params.toString();
	}
	
	public static void main(String[] args) {
		avgProcessTime();
//		countRequest();
		DbPool.getInstance().releaseAll();
	}
	
	enum Querys {
		avgProcessTime("select requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY processtime desc"),
		countRequest("select requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY count desc")
		;
		private String sql;
		private Querys(String sql) {
			this.sql = sql;
		}
		public String query() {
			return new Table(sql).toString();
		}
	}
	
	public static JSONArray listQuerys() {
		JSONArray list = new JSONArray();
		Stream.of(Querys.values()).forEach(query->{
			try {
				list.put(new JSONObject().put("name", query.name()));
			} catch (Exception e) {
			}	
		});
		return list;
	}
	
	public static String query(String query) {
		String result = "查询出错";
		try {
			result = Querys.valueOf(query).query();
		} catch (Exception e) {
		}
		return result;
	}
	/**
	 * 统计请求的平均用时
	 * @author yingjianhua
	 */
	public static void avgProcessTime() {
		String sql = "select requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY processtime desc";
		new Table(sql).print();
	}
	/**
	 * 统计请求次数
	 * @author yingjianhua
	 */
	public static void countRequest() {
		String sql = "select requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY count desc";
		new Table(sql).print();
	}
}
