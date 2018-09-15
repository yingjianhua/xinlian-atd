package com.irille.atd.dao.plt;



import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;

import com.irille.atd.domain.plt.PltCountry;
import com.irille.atd.pub.bean.Query;
import com.irille.atd.view.plt.CountryView;

import irille.pub.Log;

public class PltCountryDAO {
	
	private static final Log LOG = new Log(PltCountryDAO.class);

	public static PltCountry loadByName(String name) {
		return PltCountry.chkUniqueShort_name(false, name);
	}
	
	/**
	 * @return
	 * @throws JSONException 
	 * @author yingjianhua
	 */
	public static List<CountryView> listView() throws JSONException {
		return Query
		.SELECT(PltCountry.class)
		.queryList()
		.stream()
		.map(bean->new CountryView() {{
				setId(bean.getPkey());
				setName(bean.getName());
				setShortName(bean.getShortName());
				setFlag(bean.getNationalFlag());
			}}
		)
		.collect(Collectors.toList());
	}
}
