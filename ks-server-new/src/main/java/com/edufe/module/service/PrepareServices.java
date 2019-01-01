package com.edufe.module.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edufe.module.entity.ExamCourse;
@Service
@Transactional
public class PrepareServices {

	@Autowired
	protected JdbcTemplate jdbc;

	public List<ExamCourse> findExamCourse(int examId, int stuId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ec.id,ec.stu_id,ec.course_id,ec.end_time,ec.score,ec.submit_time,ec.submit_flag,ec.exam_id, rc.course_name,rc.exam_sum_time ");
		sql.append("FROM exam_course ec inner join res_course rc on ec.course_id=rc.id ");
		sql.append("where ec.exam_id=? and ec.stu_id=?");
		List<ExamCourse> list = jdbc.query(sql.toString(), new Object[]{examId, stuId}, new RowMapper<ExamCourse>(){
			@Override
			public ExamCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
				ExamCourse c = new ExamCourse();
				c.setId(rs.getInt("id"));
				c.setStuId(rs.getInt("stu_id"));
				c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				c.setExamSumTime(rs.getString("exam_sum_time"));
				c.setEndTime(rs.getString("end_time"));
				c.setScore(rs.getString("score"));
//				c.setSubmitTime(rs.getString("submit_time"));
				c.setSubmitFlag(rs.getString("submit_flag"));
				c.setExamId(rs.getInt("exam_id"));
				return c;
			}
		});
		
		boolean ksBtnFlag = false;
		for(ExamCourse c : list){
			if(null == c.getSubmitFlag() || "".equals(c.getSubmitFlag()) || "0".equals(c.getSubmitFlag())){
				//本科可考试
				ksBtnFlag = true;
				c.setKsBtnFlag("1");
				break;
			}else{
				c.setKsBtnFlag("0");
			}
		}
		return list;
	}

	
}
