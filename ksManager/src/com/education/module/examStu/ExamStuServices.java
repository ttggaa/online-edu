package com.education.module.examStu;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.education.domain.Exam;
import com.education.domain.ExamStu;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.CommonTools;
import com.education.framework.util.cache.CacheManager;
import com.education.framework.util.excelImp.ExcelImportTools;
import com.education.module.exam.ExamServices;
import com.education.module.resCourse.ResCourseServices;
import com.edufe.module.entity.ResCourseBean;

@Service
public class ExamStuServices extends BaseServices implements IDao<ExamStu>{
	@Autowired
	private ExamServices examServices;
	@Autowired
	private ResCourseServices resServices;
	@Resource(name="cacheManager")
	private CacheManager cache;
	
	@Override
	public List<ExamStu> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT es.id,idcard,identitycode,truename,es.exam_id,exam_siteid,exam_sitename,exam_roomid,exam_roomname ");
		sql.append(",loginip,login_time,seatnum,es.create_time,es.create_user,photo,test_flag ");
		sql.append("FROM exam_stu es ");
//		sql.append("inner join exam e on e.id=es.exam_id ");
//		sql.append("inner join exam_course ec on ec.stu_id=es.id and ec.exam_id=e.id ");
//		sql.append("inner join res_course rc on rc.id=ec.course_id ");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			sql.append(lp).append(" es.exam_id = ? ");
			argsList.add(searchParams.get("eid"));
			lp = " and ";
//			if(null != searchParams.get("examname") && !"".equals((String)searchParams.get("examname"))){
//				sql.append(lp).append(" e.exam_name like ? ");
//				argsList.add("%" + searchParams.get("examname") + "%");
//				lp = " and ";
//			}
			if(null != searchParams.get("truename") && !"".equals((String)searchParams.get("truename"))){
				sql.append(lp).append(" truename like ? ");
				argsList.add("%" + searchParams.get("truename") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("idcard") && !"".equals((String)searchParams.get("idcard"))){
				sql.append(lp).append(" idcard like ? ");
				argsList.add("%" + searchParams.get("idcard") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("pageItem") && !"".equals((String)searchParams.get("pageItem"))){
				if(null != page) page.setPerItem(CommonTools.parseInt((String)searchParams.get("pageItem")));
			}
		}else{
			return null;
		}
		sql.append(lp).append(" es.business_id = ? ");
		argsList.add(SessionHelper.getInstance().getUser().getBusinessId());
		Object[] args = argsList.toArray();
		List<ExamStu> list = dao.query(pageSQL(sql.toString(),"order by es.id",page),args,new RowMapper(){
			@Override
			public ExamStu mapRow(ResultSet rs, int arg1) throws SQLException {
				ExamStu obj = new ExamStu();
				obj.setCreateTime(rs.getString("create_time")); 
				obj.setCreateUser(rs.getInt("create_user")); 
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
				obj.setPhoto(rs.getString("photo")); 
				obj.setSeatnum(rs.getString("seatnum")); 
				obj.setTruename(rs.getString("truename")); 
				obj.setTestFlag(rs.getString("test_flag"));
				return obj;
			}
			
		});
		if(null != page) page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<ExamStu> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,idcard,identitycode,truename,exam_id,exam_siteid,exam_sitename,exam_roomid,exam_roomname,loginip,login_time,seatnum,create_time,create_user,photo,test_flag FROM exam_stu");
		
		List<ExamStu> list = dao.query(sql.toString(),new ExamStuRowmapper());
		return list;
	}

	@Override
	public int save(ExamStu obj) {
		 SysUser user = SessionHelper.getInstance().getUser();
		 if(null == obj.getTestFlag() || "".equals(obj.getTestFlag())) obj.setTestFlag("0");
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into exam_stu ( "); 
		 sql.append("idcard,identitycode,truename,exam_id,exam_siteid "); 
		 sql.append(",exam_sitename,exam_roomid,exam_roomname,loginip,login_time "); 
		 sql.append(",seatnum,create_time,create_user,photo,business_id,test_flag ");  
		 sql.append(" ) values(?,?,?,?,?,  ?,?,?,?,?,  ?,now(),?,?,?,?) "); 
		 Object[] args = {obj.getIdcard(),obj.getIdentitycode(),obj.getTruename(),obj.getExamId(),obj.getExamSiteid() 
		 ,obj.getExamSitename(),obj.getExamRoomid(),obj.getExamRoomname(),obj.getLoginip(),obj.getLoginTime()
		 ,obj.getSeatnum() ,user.getId(),obj.getPhoto(), SessionHelper.getInstance().getUser().getBusinessId(),obj.getTestFlag() };
		 
		dao.update(sql.toString(), args);
		int stuId = dao.queryForInt("SELECT LAST_INSERT_ID()"); 
//		Exam exam = examServices.findForObject(obj.getExamId());
//		if(null != exam.getSelCourseArr()){
//			for(ResCourseBean c : exam.getSelCourseArr()){
//				//新增考生课程关系数据
//				if(resServices.findIsExistById(c.getId())){
//					String insSql = "insert into exam_course(stu_id,course_id,score,submit_flag,exam_id,create_time) values(?,?,?,?,?,now())";
//					dao.update(insSql, new Object[]{stuId, c.getId(),0,"0", obj.getExamId()});
//				}
//			}
//		}
		return stuId;
	}

	@Override
	public ExamStu findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,idcard,identitycode,truename,exam_id,exam_siteid,exam_sitename,exam_roomid,exam_roomname,loginip,login_time,seatnum,create_time,create_user,photo,test_flag FROM exam_stu ");
		sql.append(" where id=? and business_id=?");
		
		Object[] args = {id, SessionHelper.getInstance().getUser().getBusinessId()};
		return dao.queryForObject(sql.toString(),args,new ExamStuRowmapper());
	}

	@Override
	public void update(ExamStu obj) {
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update exam_stu "); 
		 sql.append("set  "); 
		 sql.append("idcard=?,identitycode=?,truename=?,exam_id=?,exam_siteid=? "); 
		 sql.append(",exam_sitename=?,exam_roomid=?,exam_roomname=?,loginip=?,login_time=? "); 
		 sql.append(",seatnum=?,photo=? where id=? and business_id=?"); 
		 Object[] args = {obj.getIdcard(),obj.getIdentitycode(),obj.getTruename(),obj.getExamId(),obj.getExamSiteid(),obj.getExamSitename() 
		 ,obj.getExamRoomid(),obj.getExamRoomname(),obj.getLoginip(),obj.getLoginTime(),obj.getSeatnum(),obj.getPhoto()
		 ,obj.getId(),SessionHelper.getInstance().getUser().getBusinessId()};
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		dao.update("delete from exam_course where exam_id=?", new Object[]{id});
		String sql = "delete from exam_stu where id=? and business_id=?";
		dao.update(sql, new Object[]{id,SessionHelper.getInstance().getUser().getBusinessId()});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class ExamStuRowmapper implements RowMapper<ExamStu> {
		@Override
		public ExamStu mapRow(ResultSet rs, int arg1) throws SQLException {
			ExamStu obj = new ExamStu();
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setCreateUser(rs.getInt("create_user")); 
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
			obj.setPhoto(rs.getString("photo")); 
			obj.setSeatnum(rs.getString("seatnum")); 
			obj.setTruename(rs.getString("truename")); 
			obj.setTestFlag(rs.getString("test_flag"));
			return obj;
		}
	}

	public List<String> impExcel(Exam exam, MultipartFile file) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());
		List<ExamStu> slist = new ArrayList<ExamStu>();
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		List<String> errorList = new ArrayList<String>();
		Integer examId = exam.getId();
		Map<String, Integer> loginNameMap = new HashMap<String, Integer>();
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			ExamStu student= new ExamStu();
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			String idcard = ExcelImportTools.getInstance().getValForString(hssfRow, 0);
			String truename = ExcelImportTools.getInstance().getValForString(hssfRow, 1);
//			String identitycode = ExcelImportTools.getInstance().getValForString(hssfRow, 2);
//			String photo = ExcelImportTools.getInstance().getValForString(hssfRow, 3);
			truename = truename.replaceAll(" ", ""); 
			if (!"".equals(idcard) && idcard != null  ) {
				student.setIdcard(idcard);
			}else {
				errorList.add( "第"+rowNum+"行“准考证号”不可为空");
			}
			
			if (!"".equals(truename) && truename != null  ) {
				student.setTruename(truename);
			}else {
				errorList.add( "第"+rowNum+"行“姓名”不可为空");
			}
			//当前考试活动中是否存在
			boolean exist = findIdCardExist(idcard);
			if(exist){
				errorList.add("第"+rowNum+"行“准考证号码在系统中存在，请更换其它号码重新导入！");
			}
//			student.setIdentitycode(identitycode);
//			student.setPhoto(photo);
			slist.add(student);
		}
		if (errorList.size()!=0) {
			return errorList;
		}
		batchBuildExamStu(slist,examId);
		return null;
	}

	
	private void batchBuildExamStu(List<ExamStu> slist , Integer examId) {
		 Exam exam = examServices.findForObject(examId);
		 SysUser user = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into exam_stu ( "); 
		 sql.append("idcard,identitycode,truename,exam_id,photo,create_time,create_user,business_id,test_flag ");  
		 sql.append(" ) values(?,?,?,?,?,now(),?,?,'0') "); //导入人员均为正式考生
		 for(ExamStu es : slist){
			 
			 dao.update(sql.toString(), new Object[]{es.getIdcard(),es.getIdentitycode(),es.getTruename(), examId, es.getPhoto(), user.getId(),exam.getBusinessId()});
			 //插入考试科目关系表数据
//			 int stuId = dao.queryForInt("SELECT LAST_INSERT_ID()"); 
//			 if(null != exam.getSelCourseArr()){
//				for(ResCourseBean c : exam.getSelCourseArr()){
//					//新增考生课程关系数据
//					String insSql = "insert into exam_course(stu_id,course_id,score,submit_flag,exam_id,create_time) values(?,?,?,?,?,now())";
//					dao.update(insSql, new Object[]{stuId, c.getId(),0,"0", examId});
//				}
//			 }
		 }
	}

	private boolean findIdCardExist(String idcard) {
		String sql = "select count(1) from exam_stu where idcard=? and business_id=?";
		return dao.queryForObject(sql, new Object[]{idcard, SessionHelper.getInstance().getUser().getBusinessId()}, Integer.class)>0;
	}

	public boolean reexamine(Integer uid,Integer examId, Integer cid) {
		cache.removeExamPaper(uid, cid);
		dao.update("update exam_course set score=0,submit_flag='0',end_time='',submit_time=null where stu_id=? and course_id=? and exam_id=?" , new Object[]{uid, cid, examId});
		return true;
	}

	public String checkExist(ExamStu examStu) {
		String sql = "select count(1) from exam_stu where business_id=? and idcard=?";
		int n = dao.queryForObject(sql, new Object[]{SessionHelper.getInstance().getUser().getBusinessId(), examStu.getIdcard()}, Integer.class);
		if(n > 0){
			String examName = dao.queryForObject("select exam_name from exam_stu es inner join exam e on e.id=es.exam_id where es.business_id=? and es.idcard=? limit 1",
					new Object[]{SessionHelper.getInstance().getUser().getBusinessId(), examStu.getIdcard()}, String.class);
			return "准考证号在 [" + examName + "] 考试中已经存在，不能重复创建！";
		}
		return "";
	}
	
	public String checkExistUpdate(ExamStu examStu) {
		String sql = "select count(1) from exam_stu where business_id=? and idcard=? and id<>?";
		int n = dao.queryForObject(sql, new Object[]{SessionHelper.getInstance().getUser().getBusinessId(), examStu.getIdcard(),examStu.getId()}, Integer.class);
		if(n > 0){
			String examName = dao.queryForObject("select exam_name from exam_stu es inner join exam e on e.id=es.exam_id where es.business_id=? and es.idcard=? limit 1",
					new Object[]{SessionHelper.getInstance().getUser().getBusinessId(), examStu.getIdcard()}, String.class);
			return "准考证号在 [" + examName + "] 考试中已经存在，不能重复创建！";
		}
		return "";
	}

}
