package com.edufe.module.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.edufe.module.entity.bean.ExamPracBean;
import com.edufe.module.entity.bean.ExaminationType;

/* 
 *  
 * Fri Sep 14 18:27:26 CST 2018 
 */

public class ExamCourse implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private Integer id;
	private Integer courseId;
	private String score;
	private Integer stuId;
//	private String submitTime;
	private String submitFlag;
	private String endTime;
	private Integer examId;
	//考试科目信息
	private String courseName;
	//考试时长
	private String examSumTime;
	
	private List<ExaminationType> examinationTypeList = null;
	private String ksBtnFlag="0";//科目考试按扭状态 （科目考试顺序进行，endTime || 考试截止时间未超出时 ，考试按扭可用  ， allowFlag = 1：考试按扭可用 ）
	
	private Map<String,ExamPracBean> pracMap;

	public String getKsBtnFlag() {
		return ksBtnFlag;
	}
	public void setKsBtnFlag(String ksBtnFlag) {
		this.ksBtnFlag = ksBtnFlag;
	}
	public Map<String, ExamPracBean> getPracMap() {
		return pracMap;
	}
	public void setPracMap(Map<String, ExamPracBean> pracMap) {
		this.pracMap = pracMap;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getSubmitFlag() {
		return submitFlag;
	}
	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getExamSumTime() {
		return examSumTime;
	}
	public void setExamSumTime(String examSumTime) {
		this.examSumTime = examSumTime;
	}
	public List<ExaminationType> getExaminationTypeList() {
		return examinationTypeList;
	}
	public void setExaminationTypeList(List<ExaminationType> examinationTypeList) {
		this.examinationTypeList = examinationTypeList;
	}
	
}