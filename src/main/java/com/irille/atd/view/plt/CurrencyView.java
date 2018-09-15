package com.irille.atd.view.plt;

import com.irille.atd.domain.plt.PltErate;

public class CurrencyView {
	private Integer id;
	private String shortName;
	private String symbols;
	private String img;
	
	public static CurrencyView build(PltErate bean) {
		CurrencyView view = new CurrencyView();
		view.setId(bean.getPkey());
		view.setImg(bean.getLogo());
		view.setSymbols(bean.getSymbol());
		view.setShortName(bean.getCurName());
		return view;
	}
	
	public String getSymbols() {
		return symbols;
	}
	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
