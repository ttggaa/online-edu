package com.education.framework.base;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.education.framework.page.Page;


public class BaseServices {

	@Resource(name="jdbcTemplate")
	protected JdbcTemplate dao ;
	
	protected int pageCount=10;//每页显示数量
	
	protected String pageSQL(String selectSql,String orderBySql,Page page){
		return pageSQL(selectSql + " " + orderBySql, page);
	}
	
	protected String pageSQL(String selectSql,Page page){
		if(null == page) return selectSql;
		StringBuffer sql = new StringBuffer("select * from (");
		sql.append(selectSql);
		sql.append(") k limit ").append(page.getCurrPageRecordLocation()-1).append(",").append(page.getPerItem());
//		sql.append(") k limit ").append(page.getCurrPageRecordLocation()-1).append(",").append(page.getEndPage());
		return sql.toString();
	}
	
	protected int findRecordCount(String sql , Object[] args){
		return dao.queryForInt(sql,args);
	}
	
	protected int findRecordCountNew(String sql, Object[] args) {
		StringBuffer buffSQL = new StringBuffer();
		buffSQL.append("select count(1) from (");
		buffSQL.append(sql);
		buffSQL.append(") as bb");
		return dao.queryForInt(buffSQL.toString(),args);
	}
}
