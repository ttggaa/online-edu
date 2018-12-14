package com.edufe.module.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.edufe.framework.helper.ApplicationHelper;
import com.edufe.module.entity.Exam;
import com.edufe.module.entity.ExamStu;
@Service
@Transactional
public class LoginServices {
	@Autowired
	protected JdbcTemplate jdbc;
	public ExamStu findExamStu(String idcard, String truename) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT s.id,idcard,identitycode,truename,exam_id,exam_siteid,exam_sitename,exam_roomid,exam_roomname,loginip,login_time,seatnum,photo");
		sql.append(", e.exam_name,e.exam_begintime,e.exam_endtime,e.pass_score, e.introduce ");
		sql.append("FROM exam_stu s ");
		sql.append("inner join exam e on s.exam_id=e.id and e.business_id=? ");
		sql.append("where s.idcard=? and s.truename=? and s.business_id=?");
		int businessId = ApplicationHelper.getInstance().getBusiness().getId();
		List<ExamStu> list = jdbc.query(sql.toString(),new Object[]{businessId,idcard, truename,businessId},new RowMapper<ExamStu>(){
			@Override
			public ExamStu mapRow(ResultSet rs, int arg1) throws SQLException {
				ExamStu obj = new ExamStu();
				obj.setExamId(rs.getInt("exam_id")); 
				obj.setExamRoomid(rs.getString("exam_roomid")); 
				obj.setExamRoomname(rs.getString("exam_roomname")); 
				obj.setExamSiteid(rs.getString("exam_siteid")); 
				obj.setExamSitename(rs.getString("exam_sitename")); 
				obj.setId(rs.getInt("id")); 
				obj.setIdcard(rs.getString("idcard")); 
				obj.setIdentitycode(rs.getString("identitycode")); 
				obj.setLoginTime(rs.getString("login_time")); 
				obj.setLoginip(rs.getString("loginip")); 
				obj.setSeatnum(rs.getString("seatnum")); 
				obj.setTruename(rs.getString("truename")); 
				obj.setPhoto(rs.getString("photo"));
				Exam exam = new Exam();
				exam.setId(obj.getExamId());
				exam.setExamName(rs.getString("exam_name"));
				exam.setExamBegintime(rs.getString("exam_begintime"));
				exam.setExamEndtime(rs.getString("exam_endtime"));
				exam.setPassScore(rs.getFloat("pass_score"));
				exam.setIntroduce(rs.getString("introduce"));
				obj.setExam(exam);
				return obj;
			}
		});
		
		if(null != list && list.size() > 0) return list.get(0);
		return null;
	}
	
}
