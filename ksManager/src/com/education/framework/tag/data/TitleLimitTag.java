package com.education.framework.tag.data;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.education.framework.util.HTMLSpirit;

public class TitleLimitTag extends TagSupport{

	private int maxCount;
	private String str;    
	
	@Override
    public int doStartTag() throws JspException {
		try {
            JspWriter out = this.pageContext.getOut();
            String content = "";
            str = HTMLSpirit.delHTMLTag(str);
            if(null != str) {
            	if(str.length() < maxCount) {
            		maxCount = str.length();
            		content = str.substring(0,maxCount);
            	}else{
            		content = str.substring(0,maxCount) + "...";
            	}
            	
            }
            out.println(content);
            
        } catch(Exception e) {

            throw new JspException(e.getMessage());

        }

        return SKIP_BODY;

    }

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
}
