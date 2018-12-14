package com.education.framework.page;

public class Page {

	private int cpage=1;     //当前页
	private int total;     //页数合计
	private String url;       //翻页路径
	private int perItem = 10;   //每页记录数
	private int totalItem; //总记录数

	public Page(){
		
	}
	public Page(int cpage,String url,int perItem,int totalItem){
		this.cpage = cpage;
		this.url = url;
		this.perItem = perItem;
		this.totalItem = totalItem;
	}
	public Page(int cpage,int perItem){
		this.cpage = cpage;
		this.perItem = perItem;
	}
	
	public int getTotal() {
		total = totalItem / perItem;
		if(totalItem % perItem > 0){
			total ++;
		}
		return total;
	}
	
	public int getCurrPageRecordLocation(){
		if(cpage == 0) return 1;
		return (cpage -1) * perItem +1;
	}
	public int getNextPage(){
		if(cpage == getTotal()) return cpage;
		return cpage+1;
	}
	public int getPrePage(){
		if(cpage == 1) return 1;
		return cpage-1;
	}
	
	public int getEndPage(){
		if(cpage == 0) return 1;
		return cpage * perItem;
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

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
