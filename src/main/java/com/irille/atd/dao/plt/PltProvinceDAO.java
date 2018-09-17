package com.irille.atd.dao.plt;

import java.util.List;
import java.util.stream.Collectors;

import com.irille.atd.entity.plt.PltProvince;
import com.irille.atd.entity.plt.PltProvince.T;
import com.irille.atd.view.plt.ProvinceView;
import com.irille.core.repository.Query;

public class PltProvinceDAO {
	
	/**
	 * 根据国家查询所有省份信息
	 * @param country 国家id
	 * @author yingjianhua
	 */
	public static List<ProvinceView> listByCountry(Integer country) {
		return Query
		.SELECT(PltProvince.class)
		.WHERE(country!=null&&country!=0, T.MAIN, "=?", country)
		.queryList()
		.stream()
		.map(bean->{
			return new ProvinceView() {{
				setId(bean.getPkey());
				setName(bean.getName());
				setShortName(bean.getShortName());
			}};
		})
		.collect(Collectors.toList());
	}
	
}
