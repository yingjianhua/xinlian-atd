package com.irille.atd.action.plt;

import java.io.IOException;

import org.json.JSONException;

import com.irille.atd.dao.plt.PltCountryDAO;
import com.irille.atd.entity.plt.PltCountry;
import com.irille.core.controller.BeanAction;
import com.irille.core.web.config.AppConfig;

public class PltCountryAction extends BeanAction<PltCountry, Integer>{
	
	private static final long serialVersionUID = 1L;
	
	public void list() throws JSONException, IOException {
		System.out.println(AppConfig.objectMapper.writeValueAsString(PltCountryDAO.listView()));
		write(PltCountryDAO.listView());
	}
}
