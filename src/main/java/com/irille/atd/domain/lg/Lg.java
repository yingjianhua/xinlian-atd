package com.irille.atd.domain.lg;

import com.irille.atd.domain.plt.Plt_ConfPackage;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

public class Lg extends PackageBase {
	private static final Log LOG = new Log(Lg.class);
	public static final Lg INST = new Lg();
	public static TbBase TB = new TbBase<Tb>(Lg.class, "日志管理");

	private Lg() {
	}

	@Override
	public SysModule initModule() {
		return iuModule(Lg.TB, "lg-日志管理-700");
	}

	@Override
	public void initTbMsg() {
		addTb(1, LgAccess.class);
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
	
}
