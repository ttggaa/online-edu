package com.education.framework.tag.data;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.education.framework.session.SessionHelper;

public class MenuTag extends BodyTagSupport{

	private String requestMapping;    
	
	@Override
    public int doStartTag() throws JspException {
		try {
			if(null != SessionHelper.getInstance().getUser()){
				if(SessionHelper.getInstance().isMenu(requestMapping)){
					return BodyTagSupport.EVAL_BODY_INCLUDE;
				}
			}
        } catch(Exception e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;

    }

	@Override
    public int doEndTag() throws JspException {
        return BodyTagSupport.EVAL_BODY_INCLUDE;
    }

	public String getRequestMapping() {
		return requestMapping;
	}

	public void setRequestMapping(String requestMapping) {
		this.requestMapping = requestMapping;
	}

	
}
