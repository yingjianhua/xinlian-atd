package com.irille.atd.domain.lg;

import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Lg_Install  extends InstallBase {

	public static final Lg_Install INST = new Lg_Install();
	@Override
	public void initMenu(SysMenuDAO m) {
		m.proc(LgAccess.TB, 999,null, "plt");
	}

}