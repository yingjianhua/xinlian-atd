package com.irille.atd.dao.plt;

import java.util.List;
import java.util.stream.Collectors;

import com.irille.atd.entity.plt.PltErate;
import com.irille.atd.view.plt.CurrencyView;
import com.irille.core.repository.Query;

import irille.pub.Log;

public class PltErateDAO {
    public static final Log LOG = new Log(PltErateDAO.class);

    public static PltErate find(Integer pkey) {
        return Query.SELECT(PltErate.class, pkey);
    }

    /**
     * 所有启用的货币
     *
     * @return
     */
    public static List<CurrencyView> listView() {
        return Query
		.SELECT(PltErate.class)
		.queryList()
		.stream()
		.map(bean->new CurrencyView() {{
			setId(bean.getPkey());
			setImg(bean.getLogo());
			setShortName(bean.getCurName());
		}})
		.collect(Collectors.toList());
    }

}
