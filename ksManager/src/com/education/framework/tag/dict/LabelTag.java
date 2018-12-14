package com.education.framework.tag.dict;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.education.framework.util.DictionaryUtil;

public class LabelTag extends TagSupport {
	
	private static final long serialVersionUID = -4671120945820897914L;
	
	private String code;
	private String field;
	
	@Override
    public int doStartTag() throws JspException {
		try {
            JspWriter out = this.pageContext.getOut();
            String content = DictionaryUtil.getLabel(code, field);
            out.println(content);
            
        } catch(Exception e) {

            throw new JspException(e.getMessage());

        }

        return SKIP_BODY;

    }
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}