package com.irille.atd.pub.bean.query;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.svr.DbPool;

public abstract class AbstractQuery {
	
	public static final Log LOG = new Log(AbstractQuery.class);
	
	protected static final Config config = new Config();
	public static final void config(boolean debug) {
		config.debug = debug;
	}
	static class Config {
		protected boolean debug = false;
	}
	protected abstract Serializable[] getParams();
	protected abstract String getSql();
	protected Boolean needDebug() {
		return config.debug;
	};
	/**
	 * @return
	 * @author yingjianhua
	 */
	protected Map<String, Object> queryMap() {
		return query(rs->{
			try {
				return ResultMapper.asMap(rs);
			} catch (SQLException e) {
				throw LOG.err(e, "executeCountRecord", "取数据库记录时出错【{0}】!", getSql());
			}
		});
	}
	
	/**
	 * @return
	 * @author yingjianhua
	 */
	protected List<Map<String, Object>> queryMaps() {
		return query(rs->{
			try {
				return ResultMapper.asMaps(rs);
			} catch (SQLException e) {
				throw LOG.err(e, "executeCountRecord", "取数据库记录时出错【{0}】!", getSql());
			}
		});
	}
	/**
	 * 
	 * @author yingjianhua
	 */
	protected <T extends Object> T queryObject(Class<T> resultClass) {
		return query(rs->{
			try {
				return ResultMapper.asObject(rs, resultClass);
			} catch (SQLException e) {
				throw LOG.err(e, "queryVector", "取数据库记录时出错【{0}】!", getSql());
			}
		});
	}
	/**
	 * 
	 * @author yingjianhua
	 */
	protected <T extends Object> List<T> queryObjects(Class<T> resultClass) {
		return query(rs->{
			try {
				return ResultMapper.asObjects(rs, resultClass);
			} catch (SQLException e) {
				throw LOG.err(e, "queryVector", "取数据库记录时出错【{0}】!", getSql());
			}
		});
	}
	
	/**
	 * 统计记录数
	 * 
	 * @return
	 * @author yingjianhua
	 */
	protected Integer countRecord() {
		String sql = getSql();
		int s = sql.indexOf(" LIMIT");
		if(s!=-1)
			sql = sql.substring(0, s);
		sql = sql.replaceFirst("(select|SELECT)\\s+.*\\s+(FROM|from)", "SELECT COUNT(1) FROM");
		return query(rs->{
			try {
				if (!rs.next())
					throw LOG.err("oneRowNotFound", "单一记录没找到【{0}】!", getSql());
				return rs.getInt(1);
			} catch (SQLException e) {
				throw LOG.err(e, "executeCountRecord", "取数据库记录时出错【{0}】!", getSql());
			}
		}, needDebug(), sql, getParams());
	}
	
	/**
	 * 根据字段名将数据注入bean
	 * @author yingjianhua
	 */
	protected <T extends Bean<?, ?>> List<T> queryBeans(Class<T> beanClass) {
		return query(rs->{
			try {
				return ResultMapper.asBeanList(rs, beanClass);
			} catch (SQLException e) {
				throw LOG.err(e, "queryVector", "取数据库记录时出错【{0}】!", getSql());
			}
		});
	}
	
	/**
	 * 根据字段名将数据注入bean
	 * @author yingjianhua
	 */
	protected <T extends Bean<?, ?>> T queryBean(Class<T> beanClass) {
		return query(rs->{
			try {
				return ResultMapper.asBean(rs, beanClass);
			} catch (SQLException e) {
				throw LOG.err(e, "queryVector", "取数据库记录时出错【{0}】!", getSql());
			}
		});
	}
	/**
	 * 执行sql语句
	 * @author yingjianhua
	 */
	protected int executeUpdate() {
		return executeUpdate(stmt->{
			try {
				return stmt.executeUpdate();
			} catch (SQLException e) {
				throw LOG.err("executeUpdate", "执行【{0}】出错", getSql());
			}
		});
	}

	protected static <R> R query(Function<ResultSet, R> f, boolean debug, String sql, Serializable... params) {
		if(debug) printSql(sql, params);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbPool.getInstance().getConn().prepareStatement(sql);
			stmt.setFetchSize(BeanBase.FETCH_SIZE);
			BeanBase.toPreparedStatementData(stmt, 1, params);
			rs = stmt.executeQuery();
			return f.apply(rs);
		} catch (Exception e) {
			throw LOG.err(e, "executeCountRecord", "取数据库记录时出错【{0}】!", sql);
		} finally {
			DbPool.close(stmt, rs);
		}
	}
	
	protected <R> R query(Function<ResultSet, R> f) {
		if(needDebug()) printSql(getSql(), getParams());
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbPool.getInstance().getConn().prepareStatement(getSql());
			stmt.setFetchSize(BeanBase.FETCH_SIZE);
			BeanBase.toPreparedStatementData(stmt, 1, getParams());
			rs = stmt.executeQuery();
			return f.apply(rs);
		} catch (Exception e) {
			throw LOG.err(e, "executeCountRecord", "取数据库记录时出错【{0}】!", getSql());
		} finally {
			DbPool.close(stmt, rs);
		}
	}
	
	protected int executeUpdate(Function<PreparedStatement, Integer> f) {
		if(needDebug()) printSql(getSql(), getParams());
		PreparedStatement stmt = null;
		try {
			stmt = DbPool.getInstance().getConn().prepareStatement(getSql());
			BeanBase.toPreparedStatementData(stmt, 1, getParams());
			return f.apply(stmt);
		} catch (Exception e) {
			throw LOG.err("executeUpdate", "执行【{0}】出错", getSql());
		} finally {
			DbPool.close(stmt);
		}
	}
	
	private static void printSql(String sql, Serializable... params) {
		Optional<StackTraceElement> o = Stream.of(new Throwable().getStackTrace()).limit(10).filter(st->st.getClassName().endsWith("DAO")||st.getClassName().contains("DAO$")).findFirst();
		if(o.isPresent()) {
			System.out.println("[sql:"+sql+"|"+params(params)+"] [stackTrace: "+o.get().toString()+"]");
		} else {
			Optional<StackTraceElement> o2 = Stream.of(new Throwable().getStackTrace()).limit(10).filter(st->st.getClassName().endsWith("Action")||st.getClassName().contains("Action$")).findFirst();
			if(o2.isPresent())
				System.err.println("数据库查询没有写在DAO里面,有问题..."+o2.get().toString());
			System.err.println("数据库查询没有写在DAO里面,有问题... 未知位置!!!");
			System.out.println("[sql:"+sql+"|"+params(params)+"]");
		}
	}
	
	private static String params(Serializable... a) {
		if (a == null)
            return "params:null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "params:[]";

        StringBuilder b = new StringBuilder();
        b.append("params:");
        for (int i = 0; ; i++) {
        	if(a[i] instanceof String)
        		b.append("\"").append(a[i]).append("\"");
        	else
        		b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.toString();
            b.append(", ");
        }
	}
}
