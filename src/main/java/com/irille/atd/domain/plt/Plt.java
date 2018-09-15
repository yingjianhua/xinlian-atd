package com.irille.atd.domain.plt;

import irille.core.prv.PrvRoleAct;
import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.PackageBase;
import irille.pub.svr.StartInitServlet;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

public class Plt extends PackageBase {
	private static final Log LOG = new Log(Plt.class);
	public static final Plt INST = new Plt();
	public static TbBase TB = new TbBase<Tb>(Plt.class, "平台管理"); // 定义公共的Fld对象用

	private Plt() {
	}

	public static void main(String[] args) {
		StartInitServlet.initBeanLoad();
		PrvRoleAct.TB.getCode();
		Plt_ConfPackage.INST.install();
	}

	@Override
	public SysModule initModule() {
		return iuModule(Plt.TB, "plt-平台管理-100");
	}

	@Override
	public void initTbMsg() {
		addTb(2, PltErate.class);
		addTb(10, PltCountry.class);
	}

	@Override
	public void initTranData() {
	}

	public int getPackageId() {
		return Plt_ConfPackage.INST.getPackageId(getClass());
	}

	/**
	 * 初始化，在运行期间仅执行一次
	 */
	public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
		super.initOnlyOne();
	}

	public enum OOpt implements IEnumOpt {// @formatter:off
		ONE(0, "是"), TWO(1, "否");
		public static final String NAME = "网站默认货币";
		public static final OOpt DEFAULT = TWO; // 定义缺省值
		private EnumLine _line;

		private OOpt(int key, String name) {
			_line = new EnumLine(this, key, name);
		}

		public EnumLine getLine() {
			return _line;
		}
	} // @formatter:on

	public enum OOpt2 implements IEnumOpt {// @formatter:off
		ONE(0, "是"), TWO(1, "否");
		public static final String NAME = "后台默认货币";
		public static final OOpt2 DEFAULT = TWO; // 定义缺省值
		private EnumLine _line;

		private OOpt2(int key, String name) {
			_line = new EnumLine(this, key, name);
		}

		public EnumLine getLine() {
			return _line;
		}
	} // @formatter:on

	public enum WeightType implements IEnumOpt {// @formatter:off
		WEIGHT(0, "重量"), VOLUME(1, "体积"), QUANTITY(2, "件数");
		public static final String NAME = "重量计算方式选择";
		public static final WeightType DEFAULT = WEIGHT; // 定义缺省值
		private EnumLine _line;

		private WeightType(int key, String name) {
			_line = new EnumLine(this, key, name);
		}

		public EnumLine getLine() {
			// TODO Auto-generated method stub
			return _line;
		}
	}// @formatter:on

	public enum ErrMsgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索
											// @formatter:off
		uniqueErr("记录【{0}】已存在，不可操作！"), lowPriceErr("【{0}】不能低于【{1}】"), notNull(
				"【{0}】不能为空"), ;
		private String _msg;

		private ErrMsgs(String msg) {
			_msg = msg;
		}

		public String getMsg() {
			return _msg;
		}
	} // @formatter:on

}
