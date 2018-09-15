package com.irille.atd.domain.plt;

import com.irille.atd.domain.lg.Lg;

import irille.core.sys.ConfPackage;

public class Plt_ConfPackage extends  ConfPackage{

	public static final Plt_ConfPackage INST = new Plt_ConfPackage();

	@Override
	public void initPacks() {
		_packsFlag = true;
		installSys();
		add(Plt.class, 1100);
		add(Lg.class, 1700);
	}
	
}
