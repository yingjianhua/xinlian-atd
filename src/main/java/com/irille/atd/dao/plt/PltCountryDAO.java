package com.irille.atd.dao.plt;



import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.irille.atd.entity.plt.PltCountry;
import com.irille.atd.entity.plt.PltCountry.T;
import com.irille.atd.view.plt.CountryView;
import com.irille.core.repository.Query;
import com.irille.core.web.config.AppConfig;

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
	public static List<PltCountry> list() {
		return Query.SELECT(PltCountry.class).queryList();
	}
	
	public static void main(String[] args) throws JsonProcessingException, JSONException {
		Query
		.SELECT(T.NAME)
		.FROM(PltCountry.class)
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
		System.out.println(list().get(0).getName());
		System.out.println(list().get(0).getShortName());
		System.out.println(AppConfig.objectMapper.writeValueAsString(list()));
		AppConfig.dbpool_release();
	}
}
