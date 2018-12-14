package com.education.framework.tag.pageTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.education.framework.constants.ConstantsSession;
import com.education.framework.page.Page;

public class ListPageTag extends TagSupport{

	private int cpage;     //当前页
	private String url;    //翻页路径
	private int perItem;   //每页记录数
	private int totalItem; //总记录数
	private int pageViewMax = 10;
	private String formId=""; 
	@Override
    public int doStartTag() throws JspException {
		try {
            JspWriter out = this.pageContext.getOut();
            String content = getTagContent();
            out.println(content);
            
        } catch(Exception e) {

            throw new JspException(e.getMessage());

        }

        return SKIP_BODY;

    }

    private String getTagContent() {
    	HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
    	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    	
    	Page page = new Page(cpage,url,perItem,totalItem);
    	StringBuffer buff = new StringBuffer();
    	if(page.getTotal() > 1){
    		String prevCss = "";
    		String nextCss = "";
    		String prevUrl = page.getUrl() + ConstantsSession.SUFFIX + "?cpage=" + page.getPrePage();
    		String nextUrl = page.getUrl() + ConstantsSession.SUFFIX + "?cpage=" + page.getNextPage();
    		if(page.getCpage()==1){
	    		prevCss = "disabled";
	    		prevUrl = "";
	    	}
    		if(page.getCpage()==page.getTotal()){
    			nextCss = "disabled";
    			nextUrl = "";
	    	}
	    	buff.append("<div class=\"row-fluid\">");
	    	buff.append("	<div class=\"span6\">");
	    	buff.append("		<div class=\"dataTables_info\" id=\"sample_2_info\">");
	    	buff.append("			").append(page.getCurrPageRecordLocation()).append(" to ").append(page.getEndPage());
	    	buff.append(" of [共计").append(page.getTotalItem()).append("条记录  ~ ").append(page.getCpage()).append("/").append(page.getTotal()).append(" 页]");
	    	buff.append("		</div>");
	    	buff.append("	</div>");
	    	buff.append("	<div class=\"span6\">");
	    	buff.append("		<div class=\"dataTables_paginate paging_bootstrap pagination\">");
	    	buff.append("			<ul>");
	    	buff.append("				<li class=\"prev ").append(prevCss).append("\">");
	    	if(!"".equals(prevUrl)){
	    		buff.append("					<a href='javascript:void();' onclick=\"toPageSubmitForPages('").append(basePath).append(prevUrl).append("');\">← <span class=\"hidden-480\">Prev</span></a>");
	    	}else{
	    		buff.append("					<a href=\"javascript:void();\">← <span class=\"hidden-480\">Prev</span></a>");
	    	}
	    	buff.append("				</li>");
	    	String activeClass = "";
	    	StringBuffer href = new StringBuffer();
	    	int beginPage = 1; 
	    	int endPage = page.getTotal(); 
	    	
	    	if((page.getCpage() - 2) > 0) {
	    		beginPage = page.getCpage() - 2;
	    	}
	    	
	    	if((page.getCpage() + pageViewMax) < endPage){
	    		endPage = beginPage + pageViewMax;
	    	}
	    	
	    	for(int i=beginPage;i<=endPage;i++){
	    		href = new StringBuffer();
	    		if(i == page.getCpage()){
	    			href.append("				<a href=\"javascript:void();\">").append(i).append("</a>");
	    			activeClass = "active";
	    		}else{
	    			href.append("				<a href='javascript:void();' onclick=\"toPageSubmitForPages('").append(basePath).append(page.getUrl()).append(ConstantsSession.SUFFIX).append("?cpage=").append(i).append("');\">").append(i).append("</a>");
	    			activeClass = "";
	    		}
		    	buff.append("			<li class=\"").append(activeClass).append("\">");
		    	buff.append(href.toString());
		    	buff.append("			</li>");
	    	}
	    	buff.append("				<li class=\"next ").append(nextCss).append("\">");
	    	if(!"".equals(nextUrl)){
	    		buff.append("					<a href='javascript:void();' onclick=\"toPageSubmitForPages('").append(basePath).append(nextUrl).append("');\"><span class=\"hidden-480\">Next</span> → </a>");
	    	}else{
	    		buff.append("					<a href=\"javascript:void();\"><span class=\"hidden-480\">Next</span> → </a>");
	    	}
	    	buff.append("				</li>");
	    	buff.append("			</ul>");
	    	buff.append("		</div>");
	    	buff.append("	</div>");
	    	buff.append("</div>");
	    	buff.append("<script type='text/javascript'>");
	    	buff.append("function toPageSubmitForPages(url) {");
	    	if(null == formId || "".equals(formId)){
		    	buff.append("	document.forms[0].action=url;");
		    	buff.append("	document.forms[0].submit(); ");
	    	}else{
	    		buff.append("	document.forms['").append(formId).append("'].action=url;");
		    	buff.append("	document.forms['").append(formId).append("'].submit(); ");
	    	}
	    	buff.append(" }");
	    	buff.append("</script>");
    	}
		return buff.toString();
	}

	@Override
    public int doEndTag() throws JspException {

        return EVAL_PAGE;

    }

    @Override
    public void release() {
        super.release();
    }

	public int getCpage() {
		return cpage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPerItem() {
		return perItem;
	}

	public void setPerItem(int perItem) {
		this.perItem = perItem;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

}
