package com.education.module.examRank;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.domain.Exam;
import com.education.domain.ExamRankStu;
import com.education.domain.ExamRankStuCourse;
import com.education.domain.ResCourse;
import com.education.domain.extend.ExamRankBase;
import com.education.domain.extend.ExamRankCourse;
import com.education.framework.base.BaseServices;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.CommonTools;
import com.education.framework.util.DoubleUtils;
import com.education.framework.util.cache.CacheManager;
import com.education.framework.util.calendar.CalendarUtil;
import com.education.framework.util.exportExcel.ExcelTools;
import com.education.module.exam.ExamServices;
import com.education.module.resCourse.ResCourseServices;
import com.education.utils.JsonUtils;
import com.edufe.module.entity.ResCourseBean;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service
@Transactional
public class ExamRankServices extends BaseServices{
	@Autowired
	private ExamServices examServices;
	@Autowired
	private ResCourseServices resServices;
	@Resource(name="cacheManager")
	private CacheManager cache;
	
	public List<ExamRankStu> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT es.id,idcard,identitycode,truename,es.exam_id,exam_siteid,exam_sitename,exam_roomid,exam_roomname,loginip,login_time,seatnum,es.create_time,es.create_user,photo,test_flag ");
		sql.append(",ec.course_id,rc.course_name,ec.score,DATE_FORMAT(ec.submit_time,'%Y-%m-%d %H:%i:%s') submit_time,ec.submit_flag,ec.right_count,ec.wrong_count,ec.end_time, e.exam_name ");
		sql.append("FROM exam_stu es left join exam_course ec on ec.stu_id=es.id and ifnull(ec.end_time,'') <> '' ");
		sql.append("inner join exam e on e.id=es.exam_id ");
		sql.append("inner join res_course rc on rc.id=ec.course_id ");
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
			if(null != searchParams.get("export") && !"".equals((String)searchParams.get("export"))){
				sql.append(lp).append(" es.test_flag = '0' ");
				lp = " and ";
			}
		}else{
			return null;
		}
		sql.append(lp).append(" es.business_id = ? ");
		argsList.add(SessionHelper.getInstance().getUser().getBusinessId());
		Object[] args = argsList.toArray();
		final Map<Integer,ExamRankStuCourse> rankIndexMap = new HashMap<Integer,ExamRankStuCourse>();
		String execSql = "";
		if(null != page){
			execSql = pageSQL(sql.toString(),"order by ec.course_id, ec.submit_flag desc, ifnull(ec.score,0) desc",page);
		}else{
			sql.append(" order by ec.course_id,ec.submit_flag desc, ifnull(ec.score,0) desc");
			execSql = sql.toString();
		}
		List<ExamRankStu> list = dao.query(execSql,args,new RowMapper<ExamRankStu>(){
			@Override
			public ExamRankStu mapRow(ResultSet rs, int arg1) throws SQLException {
				ExamRankStu obj = new ExamRankStu();
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
				ExamRankStuCourse examCourse = new ExamRankStuCourse();
				examCourse.setCourseId(rs.getInt("course_id"));
				examCourse.setCourseName(rs.getString("course_name"));
				examCourse.setSubmitFlag(rs.getString("submit_flag"));
				examCourse.setEndTime(rs.getString("end_time"));
				if("1".equals(examCourse.getSubmitFlag())){
					examCourse.setSubmitTime(rs.getString("submit_time"));
					examCourse.setScore(rs.getString("score"));
					//排名
					if(!rankIndexMap.containsKey(examCourse.getCourseId())){
						examCourse.setRankIndex(1);
						rankIndexMap.put(examCourse.getCourseId(), examCourse);
					}else{
						ExamRankStuCourse erscTemp = rankIndexMap.get(examCourse.getCourseId());
						if(Double.parseDouble(erscTemp.getScore()) == Double.parseDouble(examCourse.getScore())){
							examCourse.setRankIndex(erscTemp.getRankIndex());
						}else{
							examCourse.setRankIndex(erscTemp.getRankIndex()+1);
							rankIndexMap.put(examCourse.getCourseId(), examCourse);
						}
					}
				}else{
					examCourse.setScore("未提交");
				}
				obj.setExamCourse(examCourse);
				return obj;
			}
			
		});
		if(null != page) page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}

	public boolean reexamine(Integer uid,Integer examId, Integer cid) {
		cache.removeExamPaper(uid, cid);
		dao.update("delete from paper_save where stu_id=? and course_id=?" , new Object[]{uid,cid});
		dao.update("update exam_course set score=0,submit_flag='0',end_time='',submit_time=null where stu_id=? and course_id=? and exam_id=?" , new Object[]{uid, cid, examId});
		//
		dao.update("update exam_stu set cost_flag='0' where id=?", new Object[]{uid});
		return true;
	}

	public boolean delayed(Integer uid, Integer examId, Integer cid, Integer minTime) {
		int n = dao.update("update exam_course set end_time=DATE_ADD(DATE_FORMAT(end_time,'%Y-%m-%d %H:%i:%s'),INTERVAL ? MINUTE) where stu_id=? and course_id=? and exam_id=? and ifnull(end_time,'') <> ''" , new Object[]{minTime,uid, cid, examId});
		return n > 0;
	}

	public ExamRankBase findExamRankBase(Integer eid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) sumCount, count(login_time) loginCount from exam_stu es ");
		sql.append("where es.exam_id=? and es.business_id=? and test_flag='0' group by es.exam_id");
		List<ExamRankBase> list = dao.query(sql.toString(), new Object[]{eid,SessionHelper.getInstance().getUser().getBusinessId()}, new RowMapper<ExamRankBase>(){
			@Override
			public ExamRankBase mapRow(ResultSet rs, int arg1) throws SQLException {
				ExamRankBase erb = new ExamRankBase();
				erb.setSumCount(rs.getInt("sumCount"));
				erb.setLoginCount(rs.getInt("loginCount"));
				erb.setNoLoginCount(erb.getSumCount() - erb.getLoginCount());
				return erb;
			}
		});
		if(list.size() > 0) return list.get(0);
		return null;
	}

	public List<ExamRankCourse> findExamRankCourseList(Integer eid, ExamRankBase baseBean) {
		List<ExamRankCourse> list = new ArrayList<ExamRankCourse>();
		Exam exam = examServices.findForObject(eid);
		List<ResCourseBean> selCourseArr = exam.getSelCourseArr();
		if(null != selCourseArr){
			for(ResCourseBean c: selCourseArr){
				ExamRankCourse ercBean = findExamRankCourse(eid, c, baseBean);
				list.add(ercBean);
			}
		}
		return list;
	}

	private ExamRankCourse findExamRankCourse(Integer eid, ResCourseBean c, ExamRankBase baseBean) {
		int loginCount = 0;
		if(null != baseBean) loginCount = baseBean.getLoginCount();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(ec.end_time) examingCount, count(if(ec.submit_flag='1',ec.submit_flag,null)) submitCount ");
		sql.append(", count(if(ec.score>=e.pass_score ,ec.score,null)) passCount,max(ec.score) maxScore,");
		sql.append("(select GROUP_CONCAT(t2.truename) truename from exam_course t1 ");
		sql.append("   inner join exam_stu t2 on t1.exam_id=t2.exam_id and t1.stu_id=t2.id ");
		sql.append(" where t1.score = max(ec.score) and t1.exam_id=? and t1.submit_flag='1' and t1.course_id=? group by t1.course_id ");
		sql.append(") maxScoreUserInfo ");
		sql.append("from exam_course ec inner join exam e on e.id=ec.exam_id ");
		sql.append("where ec.exam_id=? and ec.course_id=? group by ec.exam_id,ec.course_id");
		  
		List<ExamRankCourse> list = dao.query(sql.toString(), new Object[]{ eid,c.getId(), eid,c.getId()}, new RowMapper<ExamRankCourse>(){
			@Override
			public ExamRankCourse mapRow(ResultSet rs, int arg1) throws SQLException {
				ExamRankCourse erb = new ExamRankCourse();
				erb.setExamingCount(rs.getInt("examingCount"));
				erb.setSubmitCount(rs.getInt("submitCount"));
				int passCount = rs.getInt("passCount");
//				erb.setPassRate(rs.getString("passRate"));
				erb.setPassCount(passCount);
				erb.setMaxScore(rs.getString("maxScore"));
				erb.setMaxScoreUserInfo(rs.getString("maxScoreUserInfo"));
				return erb;
			}
		});
		ExamRankCourse erc = null ;
		if(list.size() > 0) {
			erc = list.get(0);
			erc.setCid(c.getId());
			if(erc.getPassCount() > 0){
				// 计算通过率
				String passRate = String.valueOf(DoubleUtils.div(String.valueOf(erc.getPassCount()), String.valueOf(loginCount), 2) * 100);
				erc.setPassRate(DoubleUtils.parsetStr(passRate,"##########0"));
			}else{
				erc.setPassRate("0");
			}
			ResCourse rc = resServices.findForObject(c.getId());
			erc.setCourseName(rc.getCourseName());
			return erc;
		}else{
			erc = new ExamRankCourse();
			erc.setCid(c.getId());
			ResCourse rc = resServices.findForObject(c.getId());
			erc.setCourseName(rc.getCourseName());
		}
		return erc;
	}

	public void export(Exam exam, SearchParams searchParams,String[] expColsCheckbox, HttpServletResponse response) throws Exception{
		if(null == expColsCheckbox) return ;
		searchParams.getMap().put("eid", exam.getId());
		searchParams.getMap().put("export", "excel");
		
		List<ExamRankStu> list = find(searchParams,null);
		ExamRankBase erb = findExamRankBase(exam.getId());
		List<ExamRankCourse> ercList = findExamRankCourseList(exam.getId(),erb);
		
		int expColCount = expColsCheckbox.length > 2 ? expColsCheckbox.length : 2;
		Map<String,String> expColMap = getExpColMap(expColsCheckbox);
		
		File file = null;
		try {
			String exportFileName = "考试成绩统计报表";
			file = new File(exportFileName + ".xls");
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			int sheetIndex = 0;
			for(ExamRankCourse erc: ercList){
				WritableSheet ws = wwb.createSheet(erc.getCourseName(), sheetIndex);
				String title = exam.getExamName() + "-统计报表";
				WritableCellFormat wcf_title = ExcelTools.getInstance().createCellFormat(Colour.GREY_40_PERCENT, Alignment.CENTRE,14);
				Label titleLabel = new Label(0, 0, title, wcf_title);
				ws.addCell(titleLabel);
				ws.mergeCells(0, 0, expColCount-1, 0);
				
				for(int n=0;n<expColCount;n++){
					ws.setColumnView(n, 20);
				}
				//1. 总体概况
				WritableCellFormat wcf_header_0 = ExcelTools.getInstance().createCellFormat(Colour.GREY_40_PERCENT, Alignment.CENTRE,11);
				ws.addCell(new Label(0, 1, "[总体概况]", wcf_header_0));
				ws.mergeCells(0, 1, 0, 3);
				
				WritableCellFormat wcf_header = ExcelTools.getInstance().createCellFormat(Colour.GREY_40_PERCENT, Alignment.LEFT,11);
				ws.addCell(new Label(1, 1, "考试科目：" + erc.getCourseName(), wcf_header));
				ws.mergeCells(1, 1, expColCount-1, 1);
				ws.mergeCells(1, 2, expColCount-1, 2);
				ws.mergeCells(1, 3, expColCount-1, 3);
				StringBuffer erbBuff = new StringBuffer();
				erbBuff.append("考生总数: ").append(erb.getSumCount()).append(" 人");
				erbBuff.append(", 登录人数： ").append(erb.getLoginCount()).append(" 人");
				erbBuff.append(", 未登录人数： ").append(erb.getNoLoginCount()).append(" 人");
				ws.addCell(new Label(1, 2, erbBuff.toString(), wcf_header));
				
				StringBuffer ercBuff = new StringBuffer();
				ercBuff.append("开考人数： ").append(erc.getExamingCount()).append(" 人");
				ercBuff.append(", 交卷人数：").append(erc.getSubmitCount()).append(" 人");
				ercBuff.append(", 通过率：").append(erc.getPassRate()).append("%");
				ws.addCell(new Label(1, 3, ercBuff.toString(), wcf_header));
				
				int iRow = 4;
				int iCol = 0;
				WritableCellFormat subCellFormatHeader = ExcelTools.getInstance().createCellFormat(Colour.GRAY_25, Alignment.CENTRE,11);
				if(expColMap.containsKey("rankIndex")){
					ws.addCell(new Label(iCol, iRow, "排名",subCellFormatHeader));
					iCol ++;
				}
				if(expColMap.containsKey("idcard")){
					ws.addCell(new Label(iCol, iRow, "准考证号",subCellFormatHeader));
					iCol ++;
				}
				if(expColMap.containsKey("truename")){
					ws.addCell(new Label(iCol, iRow, "姓名",subCellFormatHeader));
					iCol ++;
				}
				if(expColMap.containsKey("score")){
					ws.addCell(new Label(iCol, iRow, "分数",subCellFormatHeader));
					iCol ++;
				}
				if(expColMap.containsKey("submitTime")){
					ws.addCell(new Label(iCol, iRow, "提交时间",subCellFormatHeader));
					iCol ++;
				}
				if(expColMap.containsKey("ip")){
					ws.addCell(new Label(iCol, iRow, "IP地址",subCellFormatHeader));
					iCol ++;
				}
				iRow ++;
				WritableCellFormat subCellFormatBody = ExcelTools.getInstance().createCellFormat(Colour.WHITE, Alignment.CENTRE,10);
				for(ExamRankStu etemp : list){
					if(erc.getCid() == etemp.getExamCourse().getCourseId().intValue()){
						iCol = 0;
						if(expColMap.containsKey("rankIndex")){
							ws.addCell(new Label(iCol, iRow, CommonTools.null2String(String.valueOf(etemp.getExamCourse().getRankIndex())) ,subCellFormatBody));
							iCol ++;
						}
						if(expColMap.containsKey("idcard")){
							ws.addCell(new Label(iCol, iRow, CommonTools.null2String(String.valueOf(etemp.getIdcard())) ,subCellFormatBody));
							iCol ++;
						}
						if(expColMap.containsKey("truename")){
							ws.addCell(new Label(iCol, iRow, CommonTools.null2String(String.valueOf(etemp.getTruename())) ,subCellFormatBody));
							iCol ++;
						}
						if(expColMap.containsKey("score")){
							ws.addCell(new Label(iCol, iRow, CommonTools.null2String(String.valueOf(etemp.getExamCourse().getScore())) ,subCellFormatBody));
							iCol ++;
						}
						if(expColMap.containsKey("submitTime")){
							ws.addCell(new Label(iCol, iRow, CommonTools.null2String(String.valueOf(etemp.getExamCourse().getSubmitTime())) ,subCellFormatBody));
							iCol ++;
						}
						if(expColMap.containsKey("ip")){
							ws.addCell(new Label(iCol, iRow, CommonTools.null2String(String.valueOf(etemp.getLoginip())) ,subCellFormatBody));
							iCol ++;
						}
						iRow ++;
					}
				}
				
				sheetIndex ++ ;
			}
			wwb.write();
			wwb.close();
			ExcelTools.getInstance().fileUpload(exportFileName + ".xls", file, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> getExpColMap(String[] expColsCheckbox) {
		Map<String,String> expColMap = new HashMap<String,String>();
		for(String c : expColsCheckbox){
			expColMap.put(c, c);
		}
		return expColMap;
	}
}
