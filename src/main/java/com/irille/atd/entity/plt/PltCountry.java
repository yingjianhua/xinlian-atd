package com.irille.atd.entity.plt;

import java.util.Date;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;

import com.irille.atd.entity.lg.LgAccess.T;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class PltCountry extends BeanInt<PltCountry> implements IExtName{
	public static final Tb TB = new Tb(PltCountry.class, "国家管理").setAutoIncrement().addActIUDL().addActEnabled().addActOpt("hot","热门国家");
	public enum T implements IEnumFld {//@formatter:off
		PKEY(TB.crtIntPkey()),//主键
		NAME(SYS.MUILTI_LANGUAGE,"国家"),
		SHORT_NAME(SYS.STR__20,"国家简称"),
		ZONE(SYS.STR__10,"区号"),   
		CURRENCY(PltErate.fldOutKey("currency", "货币").setNull()),
		NATIONAL_FLAG(SYS.IMG__400_NULL,"国旗"),
		ENABLED(SYS.ENABLED),//启用
		HOT(SYS.NY,"热门国家"),
		ISDEFAULT(SYS.NY, "默认国家"),
		CREATED_BY(SYS.CREATED_BY),
		CREATED_TIME(SYS.CREATED_DATE_TIME),
		ROW_VERSION(SYS.ROW_VERSION),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		public static final Index IDX_SHORT_NAME = TB.addIndex("short_name", true, SHORT_NAME);
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		private Fld<?> _fld;

		private T(Class<?> clazz, String name, boolean... isnull) {
			_fld = IEnumFld.crtFld(this, clazz, name, isnull);
		}

		private T(IEnumFld fld, boolean... isnull) {
			_fld = IEnumFld.crtFld(this, fld, null, isnull);
		}

		private T(IEnumFld fld, String name, boolean... null1) {
			_fld = IEnumFld.crtFld(this, fld, name, null1);
		}

		private T(IEnumFld fld, String name, int strLen) {
			_fld = IEnumFld.crtFld(this, fld, name, strLen);
		}

		private T(Fld<?> fld) {
			_fld = IEnumFld.crtFld(this, fld);
		}

		public Fld<?> getFld() {
			return _fld;
		}
	}

	static { //在此可以加一些对FLD进行特殊设定的代码
		Stream.of(T.values()).forEach(f->TB.add(f.getFld()));
		TB.lockAllFlds();
	}
	public static Fld fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}
	
	@Override
	public String getExtName() {
		return getName();
	}
	//@formatter:on
	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 国家  JSONOBJECT
  private String _shortName;	// 国家简称  STR(20)
  private String _zone;	// 区号  STR(10)
  private Integer _currency;	// 货币 <表主键:PltErate>  INT<null>
  private String _nationalFlag;	// 国旗  STR(400)<null>
  private Byte _enabled;	// 启用标志 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private Byte _hot;	// 热门国家 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _isdefault;	// 默认国家 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _createdBy;	// 建档员 <表主键:SysUser>  INT
  private Date _createdTime;	// 建档时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PltCountry init(){
		super.init();
    _name=null;	// 国家  JSONOBJECT
    _shortName=null;	// 国家简称  STR(20)
    _zone=null;	// 区号  STR(10)
    _currency=null;	// 货币 <表主键:PltErate>  INT
    _nationalFlag=null;	// 国旗  STR(400)
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用标志 <OEnabled>  BYTE
    _hot=OYn.DEFAULT.getLine().getKey();	// 热门国家 <OYn>  BYTE
    _isdefault=OYn.DEFAULT.getLine().getKey();	// 默认国家 <OYn>  BYTE
    _createdBy=Idu.getUser().getPkey();	// 建档员 <表主键:SysUser>  INT
    _createdTime=Env.getTranBeginTime();	// 建档时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static PltCountry loadUniqueShort_name(boolean lockFlag,String shortName) {
    return (PltCountry)loadUnique(T.IDX_SHORT_NAME,lockFlag,shortName);
  }
  public static PltCountry chkUniqueShort_name(boolean lockFlag,String shortName) {
    return (PltCountry)chkUnique(T.IDX_SHORT_NAME,lockFlag,shortName);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public JSONObject gtName() throws JSONException {
    return getName()==null?new JSONObject():new JSONObject(getName());
  }
  public void stName(JSONObject name){
    setName(name==null?null:name.toString());
  }
  public String getName(FldLanguage.Language l) throws JSONException {
    return gtName().has(l.name())?gtName().getString(l.name()):"";
  }
  public void setName(String name, FldLanguage.Language l) throws JSONException {
    stName(gtName().put(l.name(), name));
  }
  public String getShortName(){
    return _shortName;
  }
  public void setShortName(String shortName){
    _shortName=shortName;
  }
  public String getZone(){
    return _zone;
  }
  public void setZone(String zone){
    _zone=zone;
  }
  public Integer getCurrency(){
    return _currency;
  }
  public void setCurrency(Integer currency){
    _currency=currency;
  }
  public PltErate gtCurrency(){
    if(getCurrency()==null)
      return null;
    return (PltErate)get(PltErate.class,getCurrency());
  }
  public void stCurrency(PltErate currency){
    if(currency==null)
      setCurrency(null);
    else
      setCurrency(currency.getPkey());
  }
  public String getNationalFlag(){
    return _nationalFlag;
  }
  public void setNationalFlag(String nationalFlag){
    _nationalFlag=nationalFlag;
  }
  public Byte getEnabled(){
    return _enabled;
  }
  public void setEnabled(Byte enabled){
    _enabled=enabled;
  }
  public Boolean gtEnabled(){
    return byteToBoolean(_enabled);
  }
  public void stEnabled(Boolean enabled){
    _enabled=booleanToByte(enabled);
  }
  public Byte getHot(){
    return _hot;
  }
  public void setHot(Byte hot){
    _hot=hot;
  }
  public Boolean gtHot(){
    return byteToBoolean(_hot);
  }
  public void stHot(Boolean hot){
    _hot=booleanToByte(hot);
  }
  public Byte getIsdefault(){
    return _isdefault;
  }
  public void setIsdefault(Byte isdefault){
    _isdefault=isdefault;
  }
  public Boolean gtIsdefault(){
    return byteToBoolean(_isdefault);
  }
  public void stIsdefault(Boolean isdefault){
    _isdefault=booleanToByte(isdefault);
  }
  public Integer getCreatedBy(){
    return _createdBy;
  }
  public void setCreatedBy(Integer createdBy){
    _createdBy=createdBy;
  }
  public SysUser gtCreatedBy(){
    if(getCreatedBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getCreatedBy());
  }
  public void stCreatedBy(SysUser createdBy){
    if(createdBy==null)
      setCreatedBy(null);
    else
      setCreatedBy(createdBy.getPkey());
  }
  public Date getCreatedTime(){
    return _createdTime;
  }
  public void setCreatedTime(Date createdTime){
    _createdTime=createdTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
