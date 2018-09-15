package com.irille.atd.domain.lg;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

import java.util.Date;

public class LgAccess extends BeanInt<LgAccess> {
	private static final long serialVersionUID = 3685013477292302359L;
	public static final Tb<?> TB = new Tb<>(LgAccess.class, "访问日志").setAutoIncrement();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TbBase.crtIntPkey()),
		REMOTEADDR(irille.core.sys.Sys.T.STR__500_NULL, "远程地址"),
		REQUESTURL(irille.core.sys.Sys.T.STR__500_NULL, "请求地址"),
		PARAMS(irille.core.sys.Sys.T.TEXT__200, "请求参数"),
		SUCCESS(Sys.T.YN, "是否成功"),
		REMARK(Sys.T.TEXT__200_NULL, "备注"),
		PROCESSTIME(irille.core.sys.Sys.T.LONG_PLUS_OR_ZERO, "处理用时"), 
		REQUESTTIME(irille.core.sys.Sys.T.DATE_TIME, "请求时间")
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// public static final Index IDX_ORDERID = TB.addIndex("orderid", true,
		// ORDERID);
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		private Fld<?> _fld;

		private T(Class<?> clazz, String name, boolean... isnull) {
			_fld = TB.addOutKey(clazz, this, name, isnull);
		}

		private T(IEnumFld fld, boolean... isnull) {
			this(fld, null, isnull);
		}

		private T(IEnumFld fld, String name, boolean... null1) {
			_fld = TB.add(fld, this, name, null1);
		}

		private T(IEnumFld fld, String name, int strLen) {
			_fld = TB.add(fld, this, name, strLen);
		}

		private T(Fld<?> fld) {
			_fld = TB.add(fld, this);
		}

		public Fld<?> getFld() {
			return _fld;
		}
	}

	static { // 在此可以加一些对FLD进行特殊设定的代码
		T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
	}

	public static Fld<?> fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld<?> fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name).setType(null);
	}

	// @formatter:on
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _remoteaddr;	// 远程地址  STR(500)<null>
  private String _requesturl;	// 请求地址  STR(500)<null>
  private String _params;	// 请求参数  TEXT(200)
  private Byte _success;	// 是否成功 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _remark;	// 备注  TEXT(200)<null>
  private Long _processtime;	// 处理用时  LONG
  private Date _requesttime;	// 请求时间  TIME

	@Override
  public LgAccess init(){
		super.init();
    _remoteaddr=null;	// 远程地址  STR(500)
    _requesturl=null;	// 请求地址  STR(500)
    _params=null;	// 请求参数  TEXT(200)
    _success=OYn.DEFAULT.getLine().getKey();	// 是否成功 <OYn>  BYTE
    _remark=null;	// 备注  TEXT(200)
    _processtime=(long)0;	// 处理用时  LONG
    _requesttime=Env.getTranBeginTime();	// 请求时间  TIME
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getRemoteaddr(){
    return _remoteaddr;
  }
  public void setRemoteaddr(String remoteaddr){
    _remoteaddr=remoteaddr;
  }
  public String getRequesturl(){
    return _requesturl;
  }
  public void setRequesturl(String requesturl){
    _requesturl=requesturl;
  }
  public String getParams(){
    return _params;
  }
  public void setParams(String params){
    _params=params;
  }
  public Byte getSuccess(){
    return _success;
  }
  public void setSuccess(Byte success){
    _success=success;
  }
  public Boolean gtSuccess(){
    return byteToBoolean(_success);
  }
  public void stSuccess(Boolean success){
    _success=booleanToByte(success);
  }
  public String getRemark(){
    return _remark;
  }
  public void setRemark(String remark){
    _remark=remark;
  }
  public Long getProcesstime(){
    return _processtime;
  }
  public void setProcesstime(Long processtime){
    _processtime=processtime;
  }
  public Date getRequesttime(){
    return _requesttime;
  }
  public void setRequesttime(Date requesttime){
    _requesttime=requesttime;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
