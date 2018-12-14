package com.education.module.paper.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TPaper implements Serializable {
	private static final long serialVersionUID = 6655294736726535414L;//序列号
	private String pId;
	private Map<String,TQuestion> qmap; //考试题
	private List<TQuestionBoard> qboardList; //答题板
	private int qIndex; //当前题号
	private String score;
	
	public boolean getPriQuesIsExist(){
		return qIndex > 1;
	}
	public String getPriQues(){
		if(getPriQuesIsExist()){
			qIndex --;
		}
		return String.valueOf(qIndex);
	}
	
	public boolean getNextQuesIsExist(){
		return qIndex < qmap.size();
	}
	public String getNextQues(){
		if(getNextQuesIsExist()){
			qIndex ++;
		}
		return String.valueOf(qIndex);
	}
	public String getQuesCount(){
		return String.valueOf(qmap.size());
	}
	
	
	public float calScore(){
		float score = 0 ;
		for(Map.Entry<String,TQuestion> ques : qmap.entrySet()) {  
		    System.out.println("Key = " + ques.getKey() + ", Value = " + ques.getValue());  
		    if(ques.getValue().isRight()) score += Float.parseFloat(ques.getValue().getPoint());
		} 
		return score;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public int getqIndex() {
		return qIndex;
	}
	public void setqIndex(int qIndex) {
		this.qIndex = qIndex;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Map<String, TQuestion> getQmap() {
		return qmap;
	}
	public void setQmap(Map<String, TQuestion> qmap) {
		this.qmap = qmap;
	}
	public List<TQuestionBoard> getQboardList() {
		return qboardList;
	}
	public void setQboardList(List<TQuestionBoard> qboardList) {
		this.qboardList = qboardList;
	}
}
