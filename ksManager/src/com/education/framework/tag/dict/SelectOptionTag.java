package com.education.framework.tag.dict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.education.framework.baseModule.domain.SysDictionary;
import com.education.framework.util.DictionaryUtil;

public class SelectOptionTag extends TagSupport {
	
	private static final long serialVersionUID = -4671120945820897914L;
	
	private String field;
	private String selCode;
	private String empty;
	private String missCode;
	private String emptyTxt;//
	
	@Override
    public int doStartTag() throws JspException {
		try {
			Map<String,String> missMap = getMissMap();
            JspWriter out = this.pageContext.getOut();
            List<SysDictionary> list = DictionaryUtil.getSysDictionaryList(field);
            StringBuffer content = new StringBuffer();
            if("true".equalsIgnoreCase(empty)){
            	content.append("<option value=''>" + (null == emptyTxt?"":emptyTxt) + "</option>");
            }
            Map<String,String> selMap = new HashMap<String,String>();
            if(null != selCode){
            	String[] selCodeArr = selCode.split(",");
            	if(selCodeArr.length > 0){
            		for(String s : selCodeArr){
            			selMap.put(s, s);
            		}
            	}
            }
            for(SysDictionary dict : list){
            	if(null != missMap){
            		if(missMap.containsKey(dict.getCode())){
            			continue;
            		}
            	}
            	content.append("<option ");
            	if(null != selCode && (selCode.equals(dict.getCode()) || selMap.containsKey(dict.getCode()))){
            		content.append(" selected='selected' ");
            	}
            	content.append(" value='").append(dict.getCode()).append("'>");
            	content.append(dict.getLabel());
            	content.append("</option>");
            }
            out.println(content);
            
        } catch(Exception e) {

            throw new JspException(e.getMessage());

        }

        return SKIP_BODY;

    }
	
	private Map<String, String> getMissMap() {
		if(null == missCode) return null;
		if("".equals(missCode)) return null;
		Map<String,String> map = new HashMap<String,String>();
		String[] arr = missCode.split(",");
		for(String s : arr){
			map.put(s, s);
		}
		return map;
	}

	public String getEmptyTxt() {
		return emptyTxt;
	}

	public void setEmptyTxt(String emptyTxt) {
		this.emptyTxt = emptyTxt;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSelCode() {
		return selCode;
	}

	public void setSelCode(String selCode) {
		this.selCode = selCode;
	}

	public String getEmpty() {
		return empty;
	}

	public void setEmpty(String empty) {
		this.empty = empty;
	}

	public String getMissCode() {
		return missCode;
	}

	public void setMissCode(String missCode) {
		this.missCode = missCode;
	}
}