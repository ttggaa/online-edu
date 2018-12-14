package com.education.framework.tag.data;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.education.framework.session.SessionHelper;

public class FuncTag extends BodyTagSupport{

	private String funcCode;    
	
	@Override
    public int doStartTag() throws JspException {
		try {
			if(null != SessionHelper.getInstance().getUser()){
				if(SessionHelper.getInstance().isFunc(funcCode)){
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
	
	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	
}
