package com.education.module.tkExamination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.chainsaw.Main;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.education.domain.ImportReturnMessage;
import com.education.domain.Paper;
import com.education.domain.TkExamination;
import com.education.domain.Type;
import com.education.domain.extend.QuesTypeAnalyse;
import com.education.domain.extend.QuesTypeCount;
import com.education.exception.ImpCheckExcelException;
import com.education.framework.application.ApplicationHelper;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.excelImp.ExcelImportTools;

@Service
public class TkExaminationServices extends BaseServices implements IDao<TkExamination>{

	@Override
	public List<TkExamination> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.id,a.type_code,d.typename,a.dsh_type_code,examination_content,EXAMINATION_DESCRIPTION,answer,DEFAULT_POINT,DIFFICULTY,a.course_id, b.course_name  ,BATCH_INFO,a.CREATE_TIME,a.CREATE_USER,a.UPDATE_TIME,a.UPDATE_USER,a.account_code,a.source_id,a.examination_content_html,a.audit_state,a.knowledge_point,option_a,option_b,option_c,option_d,option_e,option_f FROM tk_examination a ");
		sql.append("inner join res_course b on a.course_id=b.id ");
		sql.append("inner join type d on a.type_code=d.type_code ");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("examinationContent") && !"".equals((String)searchParams.get("examinationContent"))){
				sql.append(lp).append(" examination_content like ? ");
				argsList.add("%" + searchParams.get("examinationContent") + "%");
				lp = " and ";
			}
			
			if(null != searchParams.get("courseId") && !"".equals((String)searchParams.get("courseId"))){
				sql.append(lp).append(" a.course_id = ? ");
				argsList.add(searchParams.get("courseId"));
				lp = " and ";
			}
			if(null != searchParams.get("typeCode") && !"".equals((String)searchParams.get("typeCode"))){
				sql.append(lp).append(" a.type_code = ? ");
				argsList.add(searchParams.get("typeCode"));
				lp = " and ";
			}
			if(null != searchParams.get("batchInfo") && !"".equals((String)searchParams.get("batchInfo"))){
				sql.append(lp).append(" a.BATCH_INFO = ? ");
				argsList.add(searchParams.get("batchInfo"));
				lp = " and ";
			}
//			if(null != searchParams.get("sourceId") && !"".equals((String)searchParams.get("sourceId"))){
//				sql.append(lp).append(" a.source_id = ? ");
//				argsList.add(searchParams.get("sourceId"));
//				lp = " and ";
//			}
			if(null != searchParams.get("auditState") && !"".equals((String)searchParams.get("auditState"))){
				sql.append(lp).append(" a.audit_state = ? ");
				argsList.add(searchParams.get("auditState"));
				lp = " and ";
			}
		}
		sql.append(lp).append(" a.business_id = ? ");
		argsList.add(SessionHelper.getInstance().getUser().getBusinessId());
		Object[] args = argsList.toArray();
		
//		if(null != searchParams.get("sort") && !"".equals((String)searchParams.get("sort"))){
//			sql.append(" order by a." + searchParams.get("sort") + " DESC ");
//		}else{
//			sql.append(" order by a.UPDATE_TIME DESC ");
//		}
		sql.append(" order by a.UPDATE_TIME DESC ");
//		sql.append(" order by a.examination_content,a.UPDATE_TIME DESC ");
		List<TkExamination> list = null;
		if(null != page){
			list = dao.query(pageSQL(sql.toString(),page),args,new TkExaminationRowmapper());
			page.setTotalItem(findRecordCountNew(sql.toString(),args));
		}else{
			list = dao.query(sql.toString(),args,new TkExaminationRowmapper());
		}
		return list;
	}
	
//	public List<TkExamination> find(SearchParams searchParams) {
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT a.id,a.type_code,d.typename,a.dsh_type_code,examination_content,EXAMINATION_DESCRIPTION,answer,DEFAULT_POINT,DIFFICULTY,a.course_id, b.course_name  ,BATCH_INFO,a.CREATE_TIME,a.CREATE_USER,a.UPDATE_TIME,a.UPDATE_USER,a.account_code,a.source_id,a.examination_content_html,a.audit_state,a.knowledge_point,option_a,option_b,option_c,option_d,option_e,option_f FROM tk_examination a ");
//		sql.append("inner join res_course b on a.course_id=b.id ");
//		sql.append("left join res_chapter c on a.chapter_id=c.id ");
//		sql.append("inner join type d on a.type_code=d.type_code ");
//		sql.append("where LENGTH(examination_content) < 32000 ");
//		String lp = " and ";
//		List<Object> argsList = new ArrayList<Object>();
//		if(null != searchParams){
//			if(null != searchParams.get("examinationContent") && !"".equals((String)searchParams.get("examinationContent"))){
//				sql.append(lp).append(" examination_content like ? ");
//				argsList.add("%" + searchParams.get("examinationContent") + "%");
//				lp = " and ";
//			}
//			
//			if(null != searchParams.get("courseId") && !"".equals((String)searchParams.get("courseId"))){
//				sql.append(lp).append(" a.course_id = ? ");
//				argsList.add(searchParams.get("courseId"));
//				lp = " and ";
//			}
//			if(null != searchParams.get("typeCode") && !"".equals((String)searchParams.get("typeCode"))){
//				sql.append(lp).append(" a.type_code = ? ");
//				argsList.add(searchParams.get("typeCode"));
//				lp = " and ";
//			}
//			if(null != searchParams.get("batchInfo") && !"".equals((String)searchParams.get("batchInfo"))){
//				sql.append(lp).append(" a.BATCH_INFO = ? ");
//				argsList.add(searchParams.get("batchInfo"));
//				lp = " and ";
//			}
//			if(null != searchParams.get("sourceId") && !"".equals((String)searchParams.get("sourceId"))){
//				sql.append(lp).append(" a.source_id = ? ");
//				argsList.add(searchParams.get("sourceId"));
//				lp = " and ";
//			}
//			if(null != searchParams.get("auditState") && !"".equals((String)searchParams.get("auditState"))){
//				sql.append(lp).append(" a.audit_state = ? ");
//				argsList.add(searchParams.get("auditState"));
//				lp = " and ";
//			}
//		}
//		Object[] args = argsList.toArray();
//		
////		if(null != searchParams.get("sort") && !"".equals((String)searchParams.get("sort"))){
////			sql.append(" order by a." + searchParams.get("sort") + " DESC ");
////		}else{
////			sql.append(" order by a.UPDATE_TIME DESC ");
////		}
//		sql.append(" order by a.UPDATE_TIME DESC ");
////		sql.append(" order by a.examination_content,a.UPDATE_TIME DESC ");
//		List<TkExamination> list = dao.query(sql.toString(),args,new TkExaminationRowmapper());
//		return list;
//	}
	
	public List<TkExamination> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.id,a.type_code,d.typename,a.dsh_type_code,examination_content,EXAMINATION_DESCRIPTION,answer,DEFAULT_POINT,DIFFICULTY,a.course_id, b.course_name  ,BATCH_INFO,a.CREATE_TIME,a.CREATE_USER,a.UPDATE_TIME,a.UPDATE_USER,a.account_code,a.source_id,a.examination_content_html,a.audit_state,a.knowledge_point,option_a,option_b,option_c,option_d,option_e,option_f FROM tk_examination a ");
		sql.append("inner join res_course b on a.course_id=b.id ");
		sql.append("inner join type d on a.type_code=d.type_code ");
		
		List<TkExamination> list = dao.query(sql.toString(),new TkExaminationRowmapper());
		return list;
	}

	@Override
	public int save(TkExamination obj) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into tk_examination ( "); 
		 sql.append("type_code,dsh_type_code,examination_content,EXAMINATION_DESCRIPTION "); 
		 sql.append(",answer,DEFAULT_POINT,DIFFICULTY,course_id "); 
		 sql.append(",BATCH_INFO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,account_code,source_id,examination_content_html,audit_state,knowledge_point ");  
		 sql.append(",option_a,option_b,option_c,option_d,option_e,option_f,business_id ");  
		 sql.append(" ) values(?,?,?,?,?,?,?,?,?,now(),?,now(),?,?,?,?,?,? ,?,?,?,?,?,?,?) "); 
		 Object[] args = {obj.getTypeCode(),obj.getDshTypeCode(),obj.getExaminationContent(),obj.getExaminationDescription(),obj.getAnswer() 
		 ,obj.getDefaultPoint(),obj.getDifficulty(),obj.getCourseId(),obj.getBatchInfo()
		 ,sessionUser.getId(),sessionUser.getId(),obj.getAccountCode(),obj.getSourceId(),obj.getExaminationContentHtml(),obj.getAuditState(),obj.getKnowledgePoint() 
		 ,obj.getOptionA(),obj.getOptionB() ,obj.getOptionC() ,obj.getOptionD() ,obj.getOptionE(),obj.getOptionF(), sessionUser.getBusinessId()};
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}
	
	public int saveByBatch(TkExamination obj) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		 Map<String,String> courseMap = new HashMap<String,String>();
		String FGF = "#";
		String[] examinationContentArr = obj.getExaminationContent().split(FGF);
		String[] examinationDescriptionArr = obj.getExaminationDescription().split(FGF);
		String[] answerArr = obj.getAnswer().split(FGF);
		if(examinationContentArr.length == examinationDescriptionArr.length && examinationDescriptionArr.length == answerArr.length){
			for(int i=0;i<examinationContentArr.length;i++){
				if(!"".equals(examinationContentArr[i].trim())){
					 StringBuffer sql = new StringBuffer(); 
					 sql.append("insert into tk_examination ( "); 
					 sql.append("type_code,dsh_type_code,examination_content,EXAMINATION_DESCRIPTION "); 
					 sql.append(",answer,DEFAULT_POINT,DIFFICULTY,course_id "); 
					 sql.append(",BATCH_INFO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,account_code,source_id,examination_content_html,audit_state,knowledge_point,business_id ");  
					 sql.append(" ) values(?,?,?,?,?,?,?,?,?,now(),?,now(),?,?,?,?,?,?,?) "); 
					 Object[] args = {obj.getTypeCode(),obj.getDshTypeCode(),examinationContentArr[i],examinationDescriptionArr[i],answerArr[i] 
					 ,obj.getDefaultPoint(),obj.getDifficulty(),obj.getCourseId(),obj.getBatchInfo()
					 ,sessionUser.getId(),sessionUser.getId(),obj.getAccountCode(),obj.getSourceId(),obj.getExaminationContentHtml(),obj.getAuditState(),obj.getKnowledgePoint(),sessionUser.getBusinessId() };
					 
					 dao.update(sql.toString(), args);
					 
					
					 if (courseMap.get(String.valueOf(obj.getCourseId()))==null){
							courseMap.put(String.valueOf(obj.getCourseId()), "");
					 }
						
				}
			}
			 //耿鑫课程表题数
			if(!updateQuesSumCount(courseMap)){
				return 0;
			}
		}
		 
		return 0; 
	}

	@Override
	public TkExamination findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.id,a.type_code,d.typename,a.dsh_type_code,examination_content,EXAMINATION_DESCRIPTION,answer,DEFAULT_POINT,DIFFICULTY,a.course_id, b.course_name  ,BATCH_INFO,a.CREATE_TIME,a.CREATE_USER,a.UPDATE_TIME,a.UPDATE_USER,a.account_code,a.source_id,a.examination_content_html,a.audit_state,a.knowledge_point,option_a,option_b,option_c,option_d,option_e,option_f FROM tk_examination a ");
		sql.append("inner join res_course b on a.course_id=b.id ");
		sql.append("inner join type d on a.type_code=d.type_code ");
		sql.append(" where a.id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new TkExaminationRowmapper());
	}

	@Override
	public void update(TkExamination obj) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update tk_examination "); 
		 sql.append("set  "); 
		 sql.append("type_code=?,dsh_type_code=?,examination_content=?,EXAMINATION_DESCRIPTION=?,answer=? "); 
		 sql.append(",DEFAULT_POINT=?,DIFFICULTY=?,course_id=? "); 
		 sql.append(",UPDATE_TIME=now(),UPDATE_USER=?,account_code=?,source_id=?,examination_content_html=?,audit_state=?,knowledge_point=?,option_a=?,option_b=?,option_c=?,option_d=?,option_e=?,option_f=? where id=?  "); 
		 Object[] args = {obj.getTypeCode(),obj.getDshTypeCode(),obj.getExaminationContent(),obj.getExaminationDescription(),obj.getAnswer(),obj.getDefaultPoint() 
		 ,obj.getDifficulty(),obj.getCourseId(),sessionUser.getId(),obj.getAccountCode(),obj.getSourceId(),obj.getExaminationContentHtml(),obj.getAuditState(),obj.getKnowledgePoint()
		 ,obj.getOptionA(), obj.getOptionB(), obj.getOptionC(), obj.getOptionD(), obj.getOptionE(), obj.getOptionF() ,obj.getId() };
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		Map<String,String> courseMap = new HashMap<String,String>();
		String queryCourseID = " select course_id from tk_examination where id=? ";
		Object[] args = { id };
		int courseID = dao.queryForInt(queryCourseID,args);
		courseMap.put(String.valueOf(courseID), "");
		updateQuesSumCount(courseMap);
		 
		String sql = "delete from tk_examination where id=?";
		dao.update(sql, args);
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class TkExaminationRowmapper implements RowMapper<TkExamination> {
		@Override
		public TkExamination mapRow(ResultSet rs, int arg1) throws SQLException {
			TkExamination obj = new TkExamination();
			obj.setDefaultPoint(rs.getString("DEFAULT_POINT")); 
			obj.setDshTypeCode(rs.getString("dsh_type_code")); 
			obj.setCreateTime(rs.getString("CREATE_TIME")); 
			obj.setAnswer(rs.getString("answer")); 
			obj.setUpdateTime(rs.getString("UPDATE_TIME")); 
			obj.setTypeCode(rs.getString("type_code")); 
			obj.setId(rs.getInt("id")); 
			obj.setExaminationDescription(rs.getString("EXAMINATION_DESCRIPTION")); 
			obj.setBatchInfo(rs.getString("BATCH_INFO")); 
			obj.setExaminationContent(rs.getString("examination_content")); 
			obj.setDifficulty(rs.getString("DIFFICULTY")); 
			obj.setCourseId(rs.getInt("course_id")); 
			obj.setAccountCode(rs.getString("account_code"));
			obj.setSourceId(rs.getString("source_id"));
			obj.setExaminationContentHtml(rs.getString("examination_content_html"));
			obj.setAuditState(rs.getString("audit_state"));
			obj.setCourseName(rs.getString("course_name"));
			Type type = new Type();
			type.setTypename(rs.getString("typename"));
			obj.setType(type);
			obj.setKnowledgePoint(rs.getString("knowledge_point"));
			obj.setOptionA(rs.getString("option_a"));
			obj.setOptionB(rs.getString("option_b"));
			obj.setOptionC(rs.getString("option_c"));
			obj.setOptionD(rs.getString("option_d"));
			obj.setOptionE(rs.getString("option_e"));
			obj.setOptionF(rs.getString("option_f"));
			obj.calOptionNum();
			return obj;
		}
	}

	public boolean addPaperQuestions(Paper paper,String sourceId) {
		Map<String,String> courseMap = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer(); 
		sql.append("insert into tk_examination ( "); 
		sql.append("type_code,examination_content,EXAMINATION_DESCRIPTION "); 
		sql.append(",answer,DEFAULT_POINT,DIFFICULTY,course_id "); 
		sql.append(",BATCH_INFO,CREATE_TIME,CREATE_USER,UPDATE_TIME,account_code,dsh_type_code,source_id) ");  
		sql.append("select pe.type_code,pe.examination_content,pe.EXAMINATION_DESCRIPTION,pe.answer,pe.DEFAULT_POINT,pe.DIFFICULTY,?,?,now(),?,now(),pe.account_code,pe.dsh_type_code,? ");
		sql.append("from paper_examination pe ");
		sql.append("left join tk_examination e on e.examination_content=pe.examination_content ");
		sql.append("where pe.paper_id=? and e.examination_content is null ");
		sql.append("order by pe.type_code");
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		boolean rs = dao.update(sql.toString(), new Object[]{paper.getCourseId(),paper.getId(),sessionUser.getId(),sourceId,paper.getId()})>0 ;
		courseMap.put(String.valueOf(paper.getCourseId()), "");
		updateQuesSumCount(courseMap);
		return rs;
	}
	public boolean addPaperQuestionsAll(String sourceId) {
		Map<String,String> courseMap = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer(); 
		sql.append("insert into tk_examination ( "); 
		sql.append("type_code,examination_content,EXAMINATION_DESCRIPTION "); 
		sql.append(",answer,DEFAULT_POINT,DIFFICULTY,course_id "); 
		sql.append(",BATCH_INFO,CREATE_TIME,CREATE_USER,UPDATE_TIME,account_code,dsh_type_code,source_id) ");  
		sql.append("select pe.type_code,pe.examination_content,pe.EXAMINATION_DESCRIPTION,pe.answer,pe.DEFAULT_POINT,pe.DIFFICULTY,p.course_id,?,now(),?,now(),pe.account_code,pe.dsh_type_code,? ");
		sql.append("from paper_examination pe ");
		sql.append("inner join paper p on p.id=pe.paper_id ");
		sql.append("left join tk_examination e on e.examination_content=pe.examination_content ");
		sql.append("where e.examination_content is null ");
		sql.append("order by pe.type_code");
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		boolean rs = dao.update(sql.toString(), new Object[]{"batch",sessionUser.getId(),sourceId}) > 0;
		/*
		//找课程id
		StringBuffer sqlQueryCourseID =new StringBuffer(); 
		sqlQueryCourseID.append("");

		updateQuesSumCount(courseMap);
		*/
		return rs;
	}

	public List<QuesTypeAnalyse> quesTypeAnalyse(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.type_code, d.typename, count(1) as cNum ,sum(default_point) as defPoint ");
		sql.append(" FROM tk_examination a ");
		sql.append("inner join res_course b on a.course_id=b.id ");
		sql.append("inner join type d on a.type_code=d.type_code ");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("examinationContent") && !"".equals((String)searchParams.get("examinationContent"))){
				sql.append(lp).append(" examination_content like ? ");
				argsList.add("%" + searchParams.get("examinationContent") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("courseId") && !"".equals((String)searchParams.get("courseId"))){
				sql.append(lp).append(" a.course_id = ? ");
				argsList.add(searchParams.get("courseId"));
				lp = " and ";
			}
			if(null != searchParams.get("typeCode") && !"".equals((String)searchParams.get("typeCode"))){
				sql.append(lp).append(" a.type_code = ? ");
				argsList.add(searchParams.get("typeCode"));
				lp = " and ";
			}
			if(null != searchParams.get("batchInfo") && !"".equals((String)searchParams.get("batchInfo"))){
				sql.append(lp).append(" a.BATCH_INFO = ? ");
				argsList.add(searchParams.get("batchInfo"));
				lp = " and ";
			}
			if(null != searchParams.get("sourceId") && !"".equals((String)searchParams.get("sourceId"))){
				sql.append(lp).append(" a.source_id = ? ");
				argsList.add(searchParams.get("sourceId"));
				lp = " and ";
			}
		}
		Object[] args = argsList.toArray();
		sql.append("group by a.type_code,d.typename");
		List<QuesTypeAnalyse> list = dao.query(sql.toString(), args, new RowMapper<QuesTypeAnalyse>(){
			@Override
			public QuesTypeAnalyse mapRow(ResultSet rs, int arg1) throws SQLException {
				QuesTypeAnalyse obj = new QuesTypeAnalyse();
				obj.setQuesTypeId(rs.getString("type_code"));
				obj.setQuesTypeName(rs.getString("typename"));
				obj.setQuesCount(rs.getString("cNum"));
				obj.setQuesSumScore(rs.getString("defPoint"));
				return obj;
			}
		});
		return list;
	}

	public List<ImportReturnMessage> impExcel(TkExamination tkExamination, MultipartFile file) throws Exception {
		List<ImportReturnMessage> importReturnMessageList = new ArrayList<ImportReturnMessage>(); 
		if(null == file || file.getSize() == 0) {
			ImportReturnMessage importReturnMessage = new ImportReturnMessage();
			importReturnMessage.setMessageCode(1);
			importReturnMessage.setRetrunMessage("excel试题导入文件有误，请重选择该文件后再试!");
			importReturnMessageList.add(importReturnMessage);
			return importReturnMessageList;
		}
		Map<String,String> courseMap = new HashMap<String,String>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());
		if(hssfWorkbook.getNumberOfSheets() == 4) {
			ImportReturnMessage importReturnMessage = new ImportReturnMessage();
			importReturnMessage.setMessageCode(1);
			importReturnMessage.setRetrunMessage("导入模板sheet有误，请重新下载!");
			importReturnMessageList.add(importReturnMessage);
			return importReturnMessageList;
		}
			
		List<TkExamination> qlist = new ArrayList<TkExamination>();
		for(int i=0;i<=4;i++){
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);				
				String typeCode = getTypeCode(i);
				try{
					TkExamination ques = getExamination(typeCode, tkExamination.getCourseId(),hssfRow);
					if(null != ques){
						qlist.add(ques);
					}
				}catch(ImpCheckExcelException iceex){
					ImportReturnMessage importReturnMessage = new ImportReturnMessage();
					importReturnMessage.setRowNum(rowNum);
					importReturnMessage.setMessageCode(2);
					importReturnMessage.setTypeName(getTypeName(i));
					importReturnMessage.setRetrunMessage(iceex.getMessage());
					importReturnMessageList.add(importReturnMessage);
				}
			}
		}
		
		if(importReturnMessageList.size()>0){
			return importReturnMessageList ; 
		}
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		StringBuffer sql = new StringBuffer(); 
		sql.append("insert into tk_examination ( "); 
		sql.append("id,type_code,dsh_type_code,examination_content,EXAMINATION_DESCRIPTION "); 
		sql.append(",answer,DEFAULT_POINT,DIFFICULTY,course_id "); 
		sql.append(",BATCH_INFO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,account_code,knowledge_point ");  
		sql.append(",option_a,option_b,option_c, option_d, option_e, option_f, audit_state, source_id,business_id");  
		sql.append(" ) values(?,?,?,?,?,?,?,?,?,?,now(),?,now(),?,?,? ,?,?,?,?,?,?,'1',?,?) "); 
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for(TkExamination obj : qlist){
			Object[] args = {obj.getId(),obj.getTypeCode(),obj.getDshTypeCode(),obj.getExaminationContent(),obj.getExaminationDescription(),obj.getAnswer() 
				 ,obj.getDefaultPoint(),obj.getDifficulty(),obj.getCourseId(),obj.getBatchInfo()
				 ,sessionUser.getId(),sessionUser.getId(),obj.getAccountCode(), obj.getKnowledgePoint() 
				 ,obj.getOptionA(), obj.getOptionB() ,obj.getOptionC() ,obj.getOptionD() ,obj.getOptionE() ,obj.getOptionF(),tkExamination.getSourceId(),sessionUser.getBusinessId()};
			batchArgs.add(args);
			if (courseMap.get(String.valueOf(obj.getCourseId()))==null){
				courseMap.put(String.valueOf(obj.getCourseId()), "");
			}
		}
		int[] ret = dao.batchUpdate(sql.toString(), batchArgs);
		int rowinsert = 1 ;
		for(int r : ret){
			if(r <= 0) {
				ImportReturnMessage importReturnMessage = new ImportReturnMessage();
				importReturnMessage.setRowNum(rowinsert);
				importReturnMessage.setMessageCode(4);
				importReturnMessage.setRetrunMessage("数据导入发生错误。");
				importReturnMessageList.add(importReturnMessage);
			}
			rowinsert++;
				
		}
		//更新课程表题数
		if(!updateQuesSumCount(courseMap)){
			ImportReturnMessage importReturnMessage = new ImportReturnMessage();
			importReturnMessage.setMessageCode(5);
			importReturnMessage.setRetrunMessage("导入完成，但更新题数发生错误。");
			importReturnMessageList.add(importReturnMessage);
		}
		
		if(importReturnMessageList.size()>0){
			throw new Exception("导入发生错误。");

		}
		
		return null;
	}
	
	private TkExamination getExamination(String typeCode, Integer courseId, HSSFRow hssfRow) throws ImpCheckExcelException{
		String examinationContent = ExcelImportTools.getInstance().getValForString(hssfRow, 1);
		if(null == examinationContent || "".equals(examinationContent)) return null;
		TkExamination ques = new TkExamination();
		ques.setCourseId(courseId);
		ques.setTypeCode(typeCode);
		ques.setExaminationContent(examinationContent);
					
		if("danx".equals(ques.getTypeCode()) || "duox".equals(ques.getTypeCode())){
			String optionA = ExcelImportTools.getInstance().getValForString(hssfRow, 2);
			String optionB = ExcelImportTools.getInstance().getValForString(hssfRow, 3);
			String optionC = ExcelImportTools.getInstance().getValForString(hssfRow, 4);
			String optionD = ExcelImportTools.getInstance().getValForString(hssfRow, 5);
			String optionE = ExcelImportTools.getInstance().getValForString(hssfRow, 6);
			String optionF = ExcelImportTools.getInstance().getValForString(hssfRow, 7);
			String answer = ExcelImportTools.getInstance().getValForString(hssfRow, 8);
			String difficulty = ExcelImportTools.getInstance().getValForString(hssfRow, 9);
			String knowledgePoint = ExcelImportTools.getInstance().getValForString(hssfRow, 10);
			String examinationDescription = ExcelImportTools.getInstance().getValForString(hssfRow, 11);
			fillOptionArr(ques, optionA,optionB,optionC,optionD,optionE,optionF);
			ques.setAnswer(answer.toLowerCase());
			ques.setDifficulty(getDifficulty(difficulty));
			ques.setKnowledgePoint(knowledgePoint);
			ques.setExaminationDescription(examinationDescription);
		}else if("pand".equals(ques.getTypeCode())){
			String answer = ExcelImportTools.getInstance().getValForString(hssfRow, 2);
			String difficulty = ExcelImportTools.getInstance().getValForString(hssfRow, 3);
			String knowledgePoint = ExcelImportTools.getInstance().getValForString(hssfRow, 4);
			String examinationDescription = ExcelImportTools.getInstance().getValForString(hssfRow, 5);
			ques.setAnswer(answer.toLowerCase());
			ques.setDifficulty(getDifficulty(difficulty));
			ques.setKnowledgePoint(knowledgePoint);
			ques.setExaminationDescription(examinationDescription);
		}else if("tiank".equals(ques.getTypeCode())){
			String optionA = ExcelImportTools.getInstance().getValForString(hssfRow, 2);
			String optionB = ExcelImportTools.getInstance().getValForString(hssfRow, 3);
			String optionC = ExcelImportTools.getInstance().getValForString(hssfRow, 4);
			String optionD = ExcelImportTools.getInstance().getValForString(hssfRow, 5);
			String optionE = ExcelImportTools.getInstance().getValForString(hssfRow, 6);
			String optionF = ExcelImportTools.getInstance().getValForString(hssfRow, 7);
			String difficulty = ExcelImportTools.getInstance().getValForString(hssfRow, 8);
			String knowledgePoint = ExcelImportTools.getInstance().getValForString(hssfRow, 9);
			String examinationDescription = ExcelImportTools.getInstance().getValForString(hssfRow, 10);
			fillOptionArr(ques, optionA,optionB,optionC,optionD,optionE,optionF);
			ques.setDifficulty(getDifficulty(difficulty));
			ques.setKnowledgePoint(knowledgePoint);
			ques.setAnswer(addAnswerStr(ques));
			ques.setExaminationDescription(examinationDescription);
			
		}else if("jiand".equals(ques.getTypeCode())){
			String answer = ExcelImportTools.getInstance().getValForString(hssfRow, 2);
			String difficulty = ExcelImportTools.getInstance().getValForString(hssfRow, 3);
			String knowledgePoint = ExcelImportTools.getInstance().getValForString(hssfRow, 4);
			String examinationDescription = ExcelImportTools.getInstance().getValForString(hssfRow, 5);
			ques.setAnswer(answer.toLowerCase());
			ques.setDifficulty(getDifficulty(difficulty));
			ques.setKnowledgePoint(knowledgePoint);
			ques.setExaminationDescription(examinationDescription);
		}
		
		checkImpData(ques);
		return ques;
	}
	
	private String addAnswerStr(TkExamination ques){
	    StringBuffer answerTempBuff = new StringBuffer();
	    int index = 1;
	    if(null != ques.getOptionA() && !"".equals(ques.getOptionA())) {
	    	answerTempBuff.append(" (").append(index).append(") ").append(ques.getOptionA());
	    	index ++;
	    }
	    if(null != ques.getOptionB() && !"".equals(ques.getOptionB())) {
	    	answerTempBuff.append(" (").append(index).append(") ").append(ques.getOptionB());
	    	index ++;
	    }
	    if(null != ques.getOptionC() && !"".equals(ques.getOptionC())) {
	    	answerTempBuff.append(" (").append(index).append(") ").append(ques.getOptionC());
	    	index ++;
	    }
	    if(null != ques.getOptionD() && !"".equals(ques.getOptionD())) {
	    	answerTempBuff.append(" (").append(index).append(") ").append(ques.getOptionD());
	    	index ++;
	    }
	    if(null != ques.getOptionE() && !"".equals(ques.getOptionE())) {
	    	answerTempBuff.append(" (").append(index).append(") ").append(ques.getOptionE());
	    	index ++;
	    }
	    if(null != ques.getOptionF() && !"".equals(ques.getOptionF())) {
	    	answerTempBuff.append(" (").append(index).append(") ").append(ques.getOptionF());
	    	index ++;
	    }
	    return answerTempBuff.toString();
	}

	private void checkImpData(TkExamination ques) {
		if("danx".equals(ques.getTypeCode())){
			if("".equals(ques.getAnswer())){
				throw new ImpCheckExcelException("试题答案不能为空！");
			}
			
			if(!"a".equalsIgnoreCase(ques.getAnswer()) && !"b".equalsIgnoreCase(ques.getAnswer()) 
					&& !"c".equalsIgnoreCase(ques.getAnswer()) && !"d".equalsIgnoreCase(ques.getAnswer())
					&& !"e".equalsIgnoreCase(ques.getAnswer()) && !"f".equalsIgnoreCase(ques.getAnswer())){
				throw new ImpCheckExcelException("试题答案输入有误，请输入[A至F区间的字符]！");
			}
		}else if("duox".equals(ques.getTypeCode())){
			if("".equals(ques.getAnswer())){
				throw new ImpCheckExcelException("试题答案不能为空");
			}
			StringBuffer answerBuff = new StringBuffer();
			String lp = "";
			for(int i=0;i<ques.getAnswer().length();i++){
				String temp = ques.getAnswer().substring(i, i+1);
				if(!"a".equalsIgnoreCase(temp) && !"b".equalsIgnoreCase(temp) 
						&& !"c".equalsIgnoreCase(temp) && !"d".equalsIgnoreCase(temp)
						&& !"e".equalsIgnoreCase(temp) && !"f".equalsIgnoreCase(temp) && !";".equalsIgnoreCase(temp)){
					throw new ImpCheckExcelException("试题答案输入有误，请输入[A至F区间的字符]！");
				}
				
				if("a".equalsIgnoreCase(temp) || "b".equalsIgnoreCase(temp) 
						|| "c".equalsIgnoreCase(temp) || "d".equalsIgnoreCase(temp)
						|| "e".equalsIgnoreCase(temp) || "f".equalsIgnoreCase(temp)){
					answerBuff.append(lp).append(temp);
					lp = ";";
				}
			}
			
			ques.setAnswer(answerBuff.toString());
		}else if("pand".equals(ques.getTypeCode())){
			if("".equals(ques.getAnswer())){
				throw new ImpCheckExcelException("试题答案不能为空！");
			}
			
			if(!"正确".equalsIgnoreCase(ques.getAnswer()) && !"错误".equalsIgnoreCase(ques.getAnswer())){
				throw new ImpCheckExcelException("试题答案输入有误，请输入[正确 或 错误]！");
			}
		}
	}
	
	private void fillOptionArr(TkExamination ques, String optionA, String optionB, String optionC, String optionD, String optionE,String optionF) {
		List<String> optionList = new ArrayList<String>();
		if(optionA != null && !"".equals(optionA)){
			optionList.add(optionA);
		}
		if(optionB != null && !"".equals(optionB)){
			optionList.add(optionB);
		}
		if(optionC != null && !"".equals(optionC)){
			optionList.add(optionC);
		}
		if(optionD != null && !"".equals(optionD)){
			optionList.add(optionD);
		}
		if(optionE != null && !"".equals(optionE)){
			optionList.add(optionE);
		}
		if(optionF != null && !"".equals(optionF)){
			optionList.add(optionF);
		}
		
		if(optionList.size() == 0){
			if("tiank".equals(ques.getTypeCode())){
				throw new ImpCheckExcelException("试题答案至少要添写一项！");
			}else{
				throw new ImpCheckExcelException("试题选项至少要添写一项！");
			}
		}
		int index = 0;
		for(String option : optionList){
			if(index == 0){
				ques.setOptionA(option);
				index ++;
			}else if(index == 1){
				ques.setOptionB(option);
				index ++;
			}else if(index == 2){
				ques.setOptionC(option);
				index ++;
			}else if(index == 3){
				ques.setOptionD(option);
				index ++;
			}else if(index == 4){
				ques.setOptionE(option);
				index ++;
			}else if(index == 5){
				ques.setOptionF(option);
				index ++;
			}
		}
	}

	public boolean updateQuesSumCount(Map<String,String> courseMap){
		for (String key : courseMap.keySet()) { 
			StringBuffer sql = new StringBuffer(); 
			sql.append("update res_course re set re.ques_sum_count = ");
			sql.append(" (select count(1) FROM tk_examination tn where tn.course_id = re.id) ");
			sql.append("where re.id=?");
			int ret = dao.update(sql.toString() , new Object[]{key});
			if(ret==0){
				return false;
			}
		} 
		return true;
	}
	
	public boolean updateQuesSumCountByCid(int cid){
		StringBuffer sql = new StringBuffer(); 
		sql.append("update res_course re set re.ques_sum_count = ");
		sql.append(" (select count(1) FROM tk_examination tn where tn.course_id = re.id) ");
//		sql.append("where re.id=?");
		int ret = dao.update(sql.toString());
		return ret > 0;
	}

	private String getDifficulty(String diff) {
		String ret = "";
		if(null == diff) return ret;
		if("难".equals(diff)){
			ret = "3";
		}else if("中".equals(diff)){
			ret = "2";
		}if("易".equals(diff)){
			ret = "1";
		}
		return ret;
	}

	private String getTypeCode(int i) {
		String ret = "";
		if(i == 0){
			ret = "danx";
		}else if(i == 1){
			ret = "duox";
		}else if(i == 2){
			ret = "pand";
		}else if(i == 3){
			ret = "tiank";
		}else if(i == 4){
			ret = "jiand";
		}
		return ret;
	}
	
	private String getTypeName(int i) {
		String ret = "";
		if(i == 0){
			ret = "单选题";
		}else if(i == 1){
			ret = "多选题";
		}else if(i == 2){
			ret = "判断题";
		}else if(i == 3){
			ret = "填空题";
		}else if(i == 4){
			ret = "简答题";
		}
		return ret;
	}

	public List<String> findBatch() {
		String sql = "select distinct BATCH_INFO from tk_examination where BATCH_INFO is not null";
		return dao.query(sql,new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("BATCH_INFO");
			}
		});
	}

	public boolean batchDel(SearchParams searchParams) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete FROM tk_examination where business_id=? ");
		String lp = " and ";
		List<Object> argsList = new ArrayList<Object>();
		argsList.add(SessionHelper.getInstance().getUser().getBusinessId());
		if(null != searchParams){
			if(null != searchParams.get("examinationContent") && !"".equals((String)searchParams.get("examinationContent"))){
				sql.append(lp).append(" examination_content like ? ");
				argsList.add("%" + searchParams.get("examinationContent") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("courseId") && !"".equals((String)searchParams.get("courseId"))){
				sql.append(lp).append(" course_id = ? ");
				argsList.add(searchParams.get("courseId"));
				lp = " and ";
			}
			if(null != searchParams.get("typeCode") && !"".equals((String)searchParams.get("typeCode"))){
				sql.append(lp).append(" type_code = ? ");
				argsList.add(searchParams.get("typeCode"));
				lp = " and ";
			}
			if(null != searchParams.get("batchInfo") && !"".equals((String)searchParams.get("batchInfo"))){
				sql.append(lp).append(" BATCH_INFO = ? ");
				argsList.add(searchParams.get("batchInfo"));
				lp = " and ";
			}
		}
		return dao.update(sql.toString(), argsList.toArray()) > 0;
	}

	public int checkQuesDup(String cid,String qid,String content) {
		if(null == content || "".equals(content)) return 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) FROM tk_examination where course_id = ? and TRIM(REPLACE(examination_content,CHAR(13),'')) like REPLACE(?,CHAR(13),'') and id <> ?");
		System.out.println("%" + content.trim() + "%");
		
		return dao.queryForInt(sql.toString(),new String[]{cid,"%" + content.trim() + "%",qid});
	}

	public SortedMap<String,List<QuesTypeCount>> findQuesAnalysisMap() {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.course_id,t.type_code,count(1) as cnum from tk_examination t ");
		sql.append("inner join type t2 on t2.type_code=t.type_code ");
		sql.append("inner join ques_source qs on qs.id=t.source_id and qs.state=1 ");
		sql.append("group by t.course_id,t.type_code ");
		sql.append("order by t.course_id,t2.typesort ");
		final SortedMap<String,List<QuesTypeCount>> map = new TreeMap<String,List<QuesTypeCount>>();
		dao.query(sql.toString(), new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				String courseId = rs.getString("course_id");
				QuesTypeCount qt = new QuesTypeCount();
				qt.setTypeCode(rs.getString("type_code"));
				qt.setTypeCodeName(ApplicationHelper.getInstance().getQuesTypeMap().get(rs.getString("type_code")).getTypename());
				int cnum = rs.getInt("cnum");
				qt.setNum(cnum);
				//计算百分比
				DecimalFormat df = new DecimalFormat("######0.00");   
				double bfb = Double.valueOf(cnum) / 2000 * 100;
				qt.setPercentum(df.format(bfb));
				String style = "";
				if(bfb < 50) style = "number bounce easyPieChart";
				if(bfb >= 50 && bfb < 70) style = "number transactions easyPieChart";
				if(bfb >= 70 && bfb < 100) style = "number visits easyPieChart";
				qt.setStyle(style);
				if(map.containsKey(courseId)){
					List<QuesTypeCount> list = map.get(courseId);
					list.add(qt);
					map.put(courseId, list);
				}else{
					List<QuesTypeCount> list = new ArrayList<QuesTypeCount>();
					list.add(qt);
					map.put(courseId, list);
				}
				return null;
			}
		});
		return map;
	}
}
