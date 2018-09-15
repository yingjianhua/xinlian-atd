package com.irille.atd.domain.plt;

import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Plt_Install  extends InstallBase {

	public static final Plt_Install INST = new Plt_Install();
	@Override
	public void initMenu(SysMenuDAO m) {
		m.proc(PltErate.TB,20,null);
		m.proc(PltCountry.TB, 40, null);
	}
	
	@Override
	public void installAfter() {
	}
	
}

