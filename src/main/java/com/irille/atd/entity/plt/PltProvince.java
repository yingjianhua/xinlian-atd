package com.irille.atd.entity.plt;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;

import com.irille.atd.entity.lg.LgAccess.T;

import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class PltProvince extends BeanInt<PltProvince> implements IExtName{
	public static final Tb TB = new Tb(PltProvince.class, "省份").setAutoIncrement().addActIUDL();
	public  enum T implements IEnumFld {//@formatter:off
		PKEY(TB.crtIntPkey()),//省份主键
		MAIN(PltCountry.fldOutKey()),//国家  
		NAME(SYS.MUILTI_LANGUAGE,"省份名称"),//省份名称
		SHORT_NAME(SYS.STR__10,"省份缩写"),//省份缩写
		ROW_VERSION(SYS.ROW_VERSION),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
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
	
	public String getExtName() {
		return getName();
	}

	//@formatter:on
	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _main;	// 国家管理 <表主键:PltCountry>  INT
  private String _name;	// 省份名称  JSONOBJECT
  private String _shortName;	// 省份缩写  STR(10)
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PltProvince init(){
		super.init();
    _main=null;	// 国家管理 <表主键:PltCountry>  INT
    _name=null;	// 省份名称  JSONOBJECT
    _shortName=null;	// 省份缩写  STR(10)
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getMain(){
    return _main;
  }
  public void setMain(Integer main){
    _main=main;
  }
  public PltCountry gtMain(){
    if(getMain()==null)
      return null;
    return (PltCountry)get(PltCountry.class,getMain());
  }
  public void stMain(PltCountry main){
    if(main==null)
      setMain(null);
    else
      setMain(main.getPkey());
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
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
