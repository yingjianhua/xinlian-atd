package com.irille.atd.action.plt;

import java.io.IOException;

import org.json.JSONException;

import com.irille.atd.dao.plt.PltProvinceDAO;
import com.irille.atd.entity.plt.PltProvince;
import com.irille.core.controller.BeanAction;

public class PltProvinceAction extends BeanAction<PltProvince, Integer> {

	private static final long serialVersionUID = 1L;

	private Integer countryId;
	
	public void list() throws JSONException, IOException {
		write(PltProvinceDAO.listByCountry(countryId));
	}
	
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
}
