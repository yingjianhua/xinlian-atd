package com.irille.atd.action.plt;

import java.io.IOException;

import org.json.JSONException;

import com.irille.atd.dao.plt.PltCountryDAO;
import com.irille.atd.domain.plt.PltCountry;

import irille.action.BeanAction;

public class PltCountryAction extends BeanAction<PltCountry, Integer>{
	
	private static final long serialVersionUID = 1L;
	
	public void list() throws JSONException, IOException {
		write(PltCountryDAO.listView());
	}
}
