package com.education.module.paperExamination;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.education.domain.Paper;
import com.education.domain.PaperExamination;
import com.education.domain.Type;
import com.education.framework.application.ApplicationHelper;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;

@Service
public class PaperExaminationServices extends BaseServices implements IDao<PaperExamination>{

	@Override
	public List<PaperExamination> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pe.id,type_code,examination_content,examination_description,answer,default_point,difficulty,pe.create_time,pe.create_user,pe.update_time,pe.UPDATE_USER,paper_id,paper_name,pe.account_code,pe.audit_state,pe.examination_content_html,pe.qid ,option_a,option_b,option_c,option_d,option_e,option_f ");
		sql.append("FROM paper_examination pe ");
		sql.append("inner join paper p on p.id=paper_id ");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("examinationContent") && !"".equals((String)searchParams.get("examinationContent"))){
				sql.append(lp).append(" examination_content like ? ");
				argsList.add("%" + searchParams.get("examinationContent") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("paperId") && !"".equals((String)searchParams.get("paperId"))){
				sql.append(lp).append(" paper_id = ? ");
				argsList.add(searchParams.get("paperId"));
				lp = " and ";
			}
			if(null != searchParams.get("typeCode") && !"".equals((String)searchParams.get("typeCode"))){
				sql.append(lp).append(" type_code = ? ");
				argsList.add(searchParams.get("typeCode"));
				lp = " and ";
			}
		}
		sql.append(" order by paper_id,type_code asc");
		Object[] args = argsList.toArray();
		List<PaperExamination> list = dao.query(pageSQL(sql.toString(),page),args,new PaperExaminationRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<PaperExamination> find() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pe.id,type_code,examination_content,examination_description,answer,default_point,difficulty,pe.create_time,pe.create_user,pe.update_time,pe.UPDATE_USER,paper_id,paper_name,pe.account_code,pe.audit_state,pe.examination_content_html,pe.qid ,option_a,option_b,option_c,option_d,option_e,option_f ");
		sql.append("FROM paper_examination pe ");
		sql.append("inner join paper p on p.id=paper_id ");
		List<PaperExamination> list = dao.query(sql.toString(),new PaperExaminationRowmapper());
		return list;
	}

	public List<PaperExamination> findByPaper(int paperId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pe.id,type_code,examination_content,examination_description,answer,default_point,difficulty,pe.create_time,pe.create_user,pe.update_time,pe.UPDATE_USER,paper_id,paper_name,pe.account_code,pe.audit_state,pe.examination_content_html,pe.qid ,option_a,option_b,option_c,option_d,option_e,option_f ");
		sql.append("FROM paper_examination pe ");
		sql.append("inner join paper p on p.id=paper_id ");
		sql.append("where paper_id=? order by type_code");
		List<PaperExamination> list = dao.query(sql.toString(),new Object[]{paperId} ,new PaperExaminationRowmapper());
		return list;
	}
	
	@Override
	public int save(PaperExamination obj) {
		SysUser user = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into paper_examination ( "); 
		 sql.append("type_code,examination_content,examination_description "); 
		 sql.append(",answer,default_point,difficulty,create_time,create_user,paper_id,account_code,audit_state,dsh_type_code,examination_content_html "); 
		 sql.append(" ) values(?,?,?,?,?,?,now(),?,?,?,?,?,?) "); 
		 Object[] args = {obj.getTypeCode(),obj.getExaminationContent(),obj.getExaminationDescription(),obj.getAnswer() 
		 ,obj.getDefaultPoint(),obj.getDifficulty(),user.getId(),obj.getPaperId(),obj.getAccountCode(),obj.getAuditState(),obj.getDshTypeCode(),obj.getExaminationContentHtml() };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public PaperExamination findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pe.id,type_code,examination_content,examination_description,answer,default_point,difficulty,pe.create_time,pe.create_user,pe.update_time,pe.UPDATE_USER,paper_id,paper_name,pe.account_code,pe.audit_state,pe.examination_content_html,pe.qid ,option_a,option_b,option_c,option_d,option_e,option_f ");
		sql.append(" FROM paper_examination pe ");
		sql.append("inner join paper p on p.id=paper_id ");
		sql.append(" where pe.id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new PaperExaminationRowmapper());
	}

	@Override
	public void update(PaperExamination obj) {
		SysUser user = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update paper_examination "); 
		 sql.append("set  "); 
		 sql.append("type_code=?,examination_content=?,examination_description=?,answer=? "); 
		 sql.append(",default_point=?,difficulty=?,UPDATE_USER=?,update_time=now(),paper_id=?,account_code=?,audit_state=?,dsh_type_code=?,examination_content_html=? where id=?  "); 
		 Object[] args = {obj.getTypeCode(),obj.getExaminationContent(),obj.getExaminationDescription(),obj.getAnswer(),obj.getDefaultPoint() 
		 ,obj.getDifficulty(),user.getId(),obj.getPaperId(),obj.getAccountCode(),obj.getAuditState(),obj.getDshTypeCode(),obj.getExaminationContentHtml(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from paper_examination where id=?";
		dao.update(sql, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	public boolean updatePaper(int paperId){
		StringBuffer sql = new StringBuffer();
		sql.append("select pe.paper_id,count(1) cNum,sum(pe.default_point) pointSum from paper_examination pe ");
		sql.append("where pe.paper_id=?  group by pe.paper_id");
		Object[] args = {paperId};
		try{
			Map<String,Object> m = dao.queryForMap(sql.toString(), args);
			
			String quesCount = String.valueOf(m.get("cNum"));
			String pointSum =  String.valueOf(m.get("pointSum"));
			String sql2 = "update paper set ques_count=?,total_score=? where id=?";
			Object[] args2 = {quesCount,pointSum,paperId};
			
			return dao.update(sql2.toString(), args2)>0;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	private class PaperExaminationRowmapper implements RowMapper<PaperExamination> {
		@Override
		public PaperExamination mapRow(ResultSet rs, int arg1) throws SQLException {
			PaperExamination obj = new PaperExamination();
			obj.setAnswer(rs.getString("answer")); 
			obj.setDifficulty(rs.getString("difficulty")); 
			obj.setPaperId(rs.getInt("paper_id")); 
			obj.setExaminationDescription(rs.getString("examination_description")); 
			obj.setTypeCode(rs.getString("type_code")); 
			obj.setId(rs.getInt("id")); 
			obj.setDefaultPoint(rs.getString("default_point")); 
			obj.setExaminationContent(rs.getString("examination_content")); 
		    obj.setPaper(new Paper(rs.getInt("paper_id"),rs.getString("paper_name")));
		    obj.setAccountCode(rs.getString("account_code"));
		    obj.setQuesType(ApplicationHelper.getInstance().getQuesTypeMap().get(obj.getTypeCode()));
		    obj.setAuditState(rs.getString("audit_state"));
		    obj.setExaminationContentHtml(rs.getString("examination_content_html"));
		    obj.setQid(rs.getInt("qid"));
		    
		    obj.setOptionA(rs.getString("option_a"));
			obj.setOptionB(rs.getString("option_b"));
			obj.setOptionC(rs.getString("option_c"));
			obj.setOptionD(rs.getString("option_d"));
			obj.setOptionE(rs.getString("option_e"));
			obj.setOptionF(rs.getString("option_f"));
			return obj;
		}
	}

//	public AnswerRecord calQuestionRichProcess(AnswerRecord ar) {
//		if(null != ar.getPaper().getQuestionListByJf()){
//			String sql = "select examination_content_html from paper_examination where id=?";
//			for(Question ques : ar.getPaper().getQuestionListByJf()){
//				String ecHtml = dao.queryForObject(sql,new Object[]{ques.getQuestionId()},String.class);
//				if(null != ecHtml && !"".equals(ecHtml)){
//					ques.setExaminationContent(ecHtml);
//				}
//			}
//		}
//		return ar;
//	}
	public int checkQuesDup(String qid,String content) {
		if(null == content || "".equals(content)) return 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) FROM paper_examination where TRIM(REPLACE(examination_content,CHAR(13),'')) like REPLACE(?,CHAR(13),'') and id <> ?");
		return dao.queryForInt(sql.toString(),new String[]{"%" + content.trim() + "%",qid});
	}

	public List<PaperExamination> findQuestionListByNotPaper(String paperId,SearchParams searchParams) {
		StringBuffer sql = new StringBuffer();
		sql.append("select e.id,e.type_code,e.examination_content,t.typename ");
		sql.append("from tk_examination e ");
		sql.append("inner join type t on t.type_code=e.type_code ");
		sql.append("left join (select qid from paper_examination where paper_id=?) ss on ss.qid=e.id  ");
		sql.append("where ss.qid is null ");
		
		List<Object> argsList = new ArrayList<Object>();
		argsList.add(paperId);
		
		String lp = " and ";
		if(null != searchParams){
			if(null != searchParams.get("examinationContent") && !"".equals((String)searchParams.get("examinationContent"))){
				sql.append(lp).append(" examination_content like ? ");
				argsList.add("%" + searchParams.get("examinationContent") + "%");
			}
			if(null != searchParams.get("typeCode") && !"".equals((String)searchParams.get("typeCode"))){
				sql.append(lp).append(" e.type_code = ? ");
				argsList.add(searchParams.get("typeCode"));
			}
			if(null != searchParams.get("courseId") && !"".equals((String)searchParams.get("courseId"))){
				sql.append(lp).append(" course_id = ? ");
				argsList.add(searchParams.get("courseId"));
			}
			if(null != searchParams.get("batchInfo") && !"".equals((String)searchParams.get("batchInfo"))){
				sql.append(lp).append(" batch_info = ? ");
				argsList.add(searchParams.get("batchInfo"));
			}
			if(null != searchParams.get("sourceId") && !"".equals((String)searchParams.get("sourceId"))){
				sql.append(lp).append(" source_id = ? ");
				argsList.add(searchParams.get("sourceId"));
			}
		}
		sql.append("order by CREATE_TIME desc ");
		sql.append("limit 0,500");
		Object[] args = argsList.toArray();
		return dao.query(sql.toString(), args, new RowMapper<PaperExamination>(){
			@Override
			public PaperExamination mapRow(ResultSet rs, int arg1) throws SQLException {
				PaperExamination pe = new PaperExamination();
				pe.setId(rs.getInt("id"));
				pe.setTypeCode(rs.getString("type_code"));
				pe.setExaminationContent(rs.getString("examination_content"));
				Type type = new Type();
				type.setTypename(rs.getString("typename"));
				pe.setQuesType(type);
				return pe;
			}
		});
	}

	public void insertQues(String paperId, Integer[] quesIdChk) {
		StringBuffer buff = new StringBuffer();
		String lp = "";
		for(Integer id : quesIdChk){
			buff.append(lp).append(id);
			lp = ",";
		}
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		StringBuffer sql = new StringBuffer(); 
		sql.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sql.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html) "); 
		sql.append("SELECT ?,a.type_code,examination_content,EXAMINATION_DESCRIPTION,answer,default_point, ");
		sql.append("DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,examination_content_html ");	
		sql.append("FROM tk_examination a ");	
		sql.append("where id in(").append(buff.toString()).append(")"); 
		Object[] args = {paperId,sessionUser.getId()};
		dao.update(sql.toString(),args);
	}
}
