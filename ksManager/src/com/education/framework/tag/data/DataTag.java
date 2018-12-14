package com.education.framework.tag.data;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DataTag extends TagSupport {
	
	private static final long serialVersionUID = -4671120945820897914L;
	
	protected JdbcTemplate dao ;
	
	private String tableName;  //表名
	private String primaryValue;    //主键值
	private String field;    //字段名
	
	@Override
    public int doStartTag() throws JspException {
		try {
			if(null == primaryValue || "".equals(primaryValue)) {
				JspWriter out = this.pageContext.getOut();
	            out.println("");
			}
			ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
			dao = (JdbcTemplate)ac.getBean("jdbcTemplate");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select ").append(field).append(" from ").append(tableName).append(" where id=").append(primaryValue);
			
			String content = "";
			List<Map<String,Object>> list = dao.queryForList(sql.toString());
			if(null != list && list.size() > 0){
				Map<String,Object> m = list.get(0);
				content = (String)m.get(field);
			}
            JspWriter out = this.pageContext.getOut();
            out.println(content);
            
        } catch(Exception e) {
        	
        }
        return SKIP_BODY;

    }

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getPrimaryValue() {
		return primaryValue;
	}

	public void setPrimaryValue(String primaryValue) {
		this.primaryValue = primaryValue;
	}
	
}