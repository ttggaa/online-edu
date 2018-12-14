package com.education.module.paper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.domain.Paper;
import com.education.domain.PaperExamination;
import com.education.domain.PracmainSub;
import com.education.domain.ResCourse;
import com.education.framework.application.ApplicationHelper;
import com.education.framework.base.BaseServices;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.dao.IDao;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.CommonTools;
import com.education.framework.util.cache.CacheManager;
import com.education.framework.util.calendar.CalendarUtil;
import com.education.module.paper.bean.QuesTypeAnalyse;
import com.education.module.paper.bean.TPaper;
import com.education.module.paper.bean.TQuestion;
import com.education.module.paper.bean.TQuestionBoard;
import com.education.module.paperExamination.PaperExaminationServices;

@Service
@Transactional
public class PaperServices extends BaseServices implements IDao<Paper>{

	//@Resource(name="cacheManager")
	private CacheManager cache;
	@Autowired
	private PaperExaminationServices paperExaminationServices;
	
	@Override
	public List<Paper> find(SearchParams searchParams, Page page) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id,p.paper_name,p.pracid,p.course_id,p.business_id,p.create_time,p.create_user,p.ques_count,p.total_score,p.use_count,p.pass_score, ");
		sql.append("rc.COURSE_NAME,rc.COURSE_CODE,p.state,p.kind_cs,p.kind_moblie,p.level_id FROM paper p ");
		sql.append(" inner join res_course rc on rc.id=course_id ");
		String lp = " where ";
		List<Object> argsList = new ArrayList<Object>();
		if(null != searchParams){
			if(null != searchParams.get("paperIdOrName") && !"".equals((String)searchParams.get("paperIdOrName"))){
				sql.append(lp).append(" (p.id = ? or ").append(" paper_name like ? )");
				argsList.add(searchParams.get("paperIdOrName"));
				argsList.add("%" + searchParams.get("paperIdOrName") + "%");
				lp = " and ";
			}
			if(null != searchParams.get("state") && !"".equals((String)searchParams.get("state"))){
				sql.append(lp).append(" p.state = ?");
				argsList.add(searchParams.get("state"));
				lp = " and ";
			}
			if(null != searchParams.get("courseId") && !"".equals((String)searchParams.get("courseId"))){
				sql.append(lp).append(" p.course_id = ?");
				argsList.add(searchParams.get("courseId"));
				lp = " and ";
			}
			if(null != searchParams.get("kindCs") && !"".equals((String)searchParams.get("kindCs"))){
				sql.append(lp).append(" p.kind_cs = ?");
				argsList.add(searchParams.get("kindCs"));
				lp = " and ";
			}
			if(null != searchParams.get("levelId") && !"".equals((String)searchParams.get("levelId"))){
				sql.append(lp).append(" p.level_id = ?");
				argsList.add(searchParams.get("levelId"));
				lp = " and ";
			}
			if(null != searchParams.get("kindMobile") && !"".equals((String)searchParams.get("kindMobile"))){
				sql.append(lp).append(" p.kind_moblie = ?");
				argsList.add(searchParams.get("kindMobile"));
				lp = " and ";
			}
		}
		sql.append(lp).append(" p.business_id = ? ");
		argsList.add(SessionHelper.getInstance().getUser().getBusinessId());
		Object[] args = argsList.toArray();
		List<Paper> list = dao.query(pageSQL(sql.toString(),"order by p.create_time desc,p.paper_name",page),args,new PaperRowmapper());
		page.setTotalItem(findRecordCountNew(sql.toString(),args));
		return list;
	}
	
	public List<Paper> find() {
		List<Object> argsList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id,p.paper_name,p.pracid,p.course_id,p.business_id,p.create_time,p.create_user,p.ques_count,p.total_score,p.use_count,p.pass_score, ");
		sql.append("rc.COURSE_NAME,rc.COURSE_CODE ,p.state,p.kind_cs,p.kind_moblie,p.level_id FROM paper p ");
		sql.append(" inner join res_course rc on rc.id=course_id ");
		sql.append(" order by p.create_time desc");
		Object[] args = argsList.toArray();
		List<Paper> list = dao.query(sql.toString(),args,new PaperRowmapper());
		return list;
	}
	
	/**
	 * 查询待缓存试卷，每次 固定条数
	 * @return
	 */
	public List<Paper> findByNoCache() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id,p.paper_name,p.pracid,p.course_id,p.business_id,p.create_time,p.create_user,p.ques_count,p.total_score,p.use_count,p.pass_score, ");
		sql.append("rc.COURSE_NAME,rc.COURSE_CODE ,p.state,p.kind_cs,p.kind_moblie,p.level_id FROM paper p ");
		sql.append(" inner join res_course rc on rc.id=course_id ");
		sql.append("where p.state='1' and p.cache_flag is null  ");
		sql.append(" order by p.create_time desc limit 0, 20");
		List<Paper> list = dao.query(sql.toString(),new PaperRowmapper());
		return list;
	}

	@Override
	public int save(Paper obj) {
		obj.setBusinessId(SessionHelper.getInstance().getUser().getBusinessId());
		SysUser user = SessionHelper.getInstance().getUser();
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("insert into paper ( "); 
		 sql.append("paper_name,pracid,course_id,business_id "); 
		 sql.append(",create_time,create_user,pass_score,state,kind_cs,kind_moblie,level_id, ques_count, total_score ");  
		 sql.append(" ) values(?,?,?,?,now(),?,?,?,?,?,?,?,?) "); 
		 Object[] args = {obj.getPaperName(),obj.getPracid(),obj.getCourseId(),obj.getBusinessId(),user.getId(),obj.getPassScore(),obj.getState()
		 ,obj.getKindCs(),obj.getKindMobile(),obj.getLevelId(),obj.getQuesCount(),obj.getTotalScore() };
		 
		 dao.update(sql.toString(), args);
		return dao.queryForInt("SELECT LAST_INSERT_ID()"); 
	}

	@Override
	public Paper findForObject(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id,p.paper_name,p.pracid,p.course_id,p.business_id,p.create_time,p.create_user,p.ques_count,p.total_score,p.use_count,p.pass_score, ");
		sql.append("rc.COURSE_NAME,rc.COURSE_CODE,p.state,p.kind_cs,p.kind_moblie,p.level_id FROM paper p ");
		sql.append(" inner join res_course rc on rc.id=course_id ");
		sql.append(" where p.id=? ");
		
		Object[] args = {id};
		return dao.queryForObject(sql.toString(),args,new PaperRowmapper());
	}

	@Override
	public void update(Paper obj) {
		 
		 StringBuffer sql = new StringBuffer(); 
		 sql.append("update paper "); 
		 sql.append("set  "); 
		 sql.append("paper_name=?,pracid=?,course_id=?,pass_score=? ,state=? ,kind_cs=?,kind_moblie=?,level_id=? where id=?  "); 
		 Object[] args = {obj.getPaperName(),obj.getPracid(),obj.getCourseId(),obj.getPassScore(),obj.getState(),
				 obj.getKindCs(),obj.getKindMobile(),obj.getLevelId(),obj.getId() };
		 
		 dao.update(sql.toString(), args);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from paper_examination where paper_id=?";
		dao.update(sql, new Object[]{id});
		
		String sql2 = "delete from paper where id=?";
		dao.update(sql2, new Object[]{id});
	}

	@Override
	public boolean findIsExist(String name) {
		return false;
	}
	
	private class PaperRowmapper implements RowMapper<Paper> {
		@Override
		public Paper mapRow(ResultSet rs, int arg1) throws SQLException {
			Paper obj = new Paper();
			obj.setId(rs.getInt("id")); 
			obj.setPracid(rs.getInt("pracid")); 
			obj.setCreateTime(rs.getString("create_time")); 
			obj.setBusinessId(rs.getInt("business_id")); 
			obj.setPaperName(rs.getString("paper_name")); 
			obj.setCreateUser(rs.getInt("create_user")); 
			obj.setCourseId(rs.getInt("course_id")); 
			obj.setQuesCount(rs.getInt("ques_count"));
			obj.setTotalScore(rs.getInt("total_score"));
			obj.setUseCount(rs.getInt("use_count"));
			obj.setPassScore(rs.getInt("pass_score"));
			obj.setState(rs.getString("state"));
			obj.setKindCs(rs.getString("kind_cs"));
			obj.setKindMobile(rs.getString("kind_moblie"));
			obj.setLevelId(rs.getString("level_id"));
			//rc
			ResCourse resCourse = new ResCourse();
			resCourse.setId(rs.getInt("course_id"));
			resCourse.setCourseName(rs.getString("COURSE_NAME"));
			resCourse.setCourseCode(rs.getString("COURSE_CODE"));
			obj.setResCourse(resCourse);
			return obj;
		}
	}

	public void pastePaper(Paper paper) {
		SysUser user = SessionHelper.getInstance().getUser();
		StringBuffer sql = new StringBuffer(); 
		sql.append("insert into paper ( "); 
		sql.append("paper_name,pracid,course_id,business_id "); 
		sql.append(",create_time,create_user,pass_score,state,kind_cs,kind_moblie,ques_count,total_score ");  
		sql.append(" ) "); 
		sql.append(" select ?,?,?,?,now(),?,?,state,kind_cs,kind_moblie,ques_count,total_score from paper where id=?"); 
		Object[] args = {paper.getPaperName(), paper.getPracid(),paper.getCourseId(),paper.getBusinessId(),
				user.getId(),paper.getPassScore(),paper.getId()};
		dao.update(sql.toString(), args);
		int id = dao.queryForInt("SELECT LAST_INSERT_ID()");
		
		
		StringBuffer sql2 = new StringBuffer(); 
		sql2.append("insert into paper_examination ( "); 
		sql2.append("type_code,examination_content,examination_description "); 
		sql2.append(",answer,default_point,difficulty,create_time,create_user,paper_id,account_code "); 
		sql2.append(" ) "); 
		sql2.append(" select type_code,examination_content,examination_description "); 
		sql2.append(",answer,default_point,difficulty,create_time,create_user,?,account_code ");
		sql2.append("from paper_examination where paper_id=?");
		Object[] args2 = {id,paper.getId()};
		dao.update(sql2.toString(), args2);
		
		//是否为剪切
		if(null != paper.getOpterType() && "cut".equals(paper.getOpterType())){
			String cutSql = "delete from paper_examination where paper_id=?";
			String cutSql2 = "delete from paper where id=?";
			Object[] cutArgs = {paper.getId()};
			dao.update(cutSql.toString(), cutArgs);
			dao.update(cutSql2.toString(), cutArgs);
		}
	}
	
	/**
	 * 根据试卷id，统计各题型题数及总分
	 */
	public List<QuesTypeAnalyse> quesTypeAnalyse(String paperId){
		StringBuffer sql = new StringBuffer();
		sql.append("select pe.type_code, t.typename, count(1) as cNum ,sum(pe.default_point) as defPoint ");
		sql.append("from paper_examination pe ");
		sql.append("inner join type t on t.type_code=pe.type_code ");
		sql.append("where paper_id=? ");
		sql.append("group by pe.type_code,t.typename");
		List<QuesTypeAnalyse> list = dao.query(sql.toString(), new Object[]{paperId}, new RowMapper<QuesTypeAnalyse>(){
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

	/**
	 * 批量更新试卷状态方法
	 * @param paperIds
	 * @param state
	 */
	public void updateState(Integer[] paperIds, String state) {
		if(null == paperIds) return ;
		StringBuffer sql = new StringBuffer();
		sql.append("update paper ");
		sql.append("set state=? ");
		sql.append("where id=?");
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		Object[] args = {};
		for(Integer paperId : paperIds){
			args = new Object[]{state,paperId};
			batchArgs.add(args);
		}
		dao.batchUpdate(sql.toString(), batchArgs);
	}
	/**
	 * 批量更新试卷运营商ID方法
	 * @param paperIds
	 * @param state
	 */
	public void updateBusiness(Integer[] paperIds, String bId) {
		if(null == paperIds) return ;
		StringBuffer sql = new StringBuffer();
		sql.append("update paper ");
		sql.append("set business_id=? ");
		sql.append("where id=?");
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		Object[] args = {};
		for(Integer paperId : paperIds){
			args = new Object[]{bId,paperId};
			batchArgs.add(args);
		}
		dao.batchUpdate(sql.toString(), batchArgs);
	}

	public void batchCopyBusiness(Integer[] paperIds, String bId) {
		StringBuffer sql = new StringBuffer(); 
		sql.append("insert into paper ( "); 
		sql.append("paper_name,pracid,course_id,business_id "); 
		sql.append(",create_time,create_user,pass_score,state,kind_cs,kind_moblie ");  
		sql.append(" ) "); 
		sql.append(" select paper_name,pracid,course_id,?,now(),?,pass_score,state,kind_cs,kind_moblie from paper where id=?"); 
		SysUser user = SessionHelper.getInstance().getUser();
		
		for(Integer pId : paperIds){
			Object[] args = {bId,user.getId(),pId};
			dao.update(sql.toString(), args);
			int id = dao.queryForInt("SELECT LAST_INSERT_ID()");
			
			
			StringBuffer sql2 = new StringBuffer(); 
			sql2.append("insert into paper_examination ( "); 
			sql2.append("type_code,examination_content,examination_description "); 
			sql2.append(",answer,default_point,difficulty,create_time,create_user,paper_id,account_code "); 
			sql2.append(" ) "); 
			sql2.append(" select type_code,examination_content,examination_description "); 
			sql2.append(",answer,default_point,difficulty,create_time,create_user,?,account_code ");
			sql2.append("from paper_examination where paper_id=?");
			Object[] args2 = {id,pId};
			dao.update(sql2.toString(), args2);
		}
	}

	public List<PaperExamination> findQuestionList(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select pe.paper_id,pe.id,pe.type_code,pe.examination_content ");
		sql.append("from paper_examination pe ");
		sql.append("left join tk_examination e on e.examination_content=pe.examination_content ");
		sql.append("where pe.paper_id=? and e.examination_content is null ");
		sql.append("order by pe.type_code");
		return dao.query(sql.toString(), new Object[]{id}, new RowMapper<PaperExamination>(){
			@Override
			public PaperExamination mapRow(ResultSet rs, int arg1) throws SQLException {
				PaperExamination pe = new PaperExamination();
				pe.setPaperId(rs.getInt("paper_id"));
				pe.setId(rs.getInt("id"));
				pe.setTypeCode(rs.getString("type_code"));
				pe.setExaminationContent(rs.getString("examination_content"));
				return pe;
			}
		});
	}
	public List<PaperExamination> findQuestionList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select pe.paper_id,pe.id,pe.type_code,pe.examination_content ");
		sql.append("from paper_examination pe ");
		sql.append("left join tk_examination e on e.examination_content=pe.examination_content ");
		sql.append("where e.examination_content is null ");
		sql.append("order by pe.type_code");
		return dao.query(sql.toString(), new RowMapper<PaperExamination>(){
			@Override
			public PaperExamination mapRow(ResultSet rs, int arg1) throws SQLException {
				PaperExamination pe = new PaperExamination();
				pe.setPaperId(rs.getInt("paper_id"));
				pe.setId(rs.getInt("id"));
				pe.setTypeCode(rs.getString("type_code"));
				pe.setExaminationContent(rs.getString("examination_content"));
				return pe;
			}
		});
	}
	
	public String findQuesCount(String cid, String quescode, String diff,String quesSourceIds) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from tk_examination ");
		sql.append("where course_id=? and type_code=? and DIFFICULTY=? ");
		if(!"sc".equals(quescode)){
			sql.append(" and source_id in(" + quesSourceIds + ")");
		}
		return String.valueOf(dao.queryForInt(sql.toString(), new Object[]{cid,quescode,diff}));
	}
	
	private String chkErrorRet(String typeCode,String diff, int lackCount,String paperCount){
		return "BuildPaper Error: TypeCode：" + typeCode + ", requirePaperCount="+paperCount+", Need to add question：" + lackCount;
	}
	
	public String buildPaperCheck(Paper paper,String paperCount,String allowDuplicateByDanx,String allowDuplicateByDuox,String allowDuplicateByPand,String allowDuplicateByJf,
			String allowDuplicateByAl,String allowDuplicateSc,String allowDuplicateByBdxxz,String allowDuplicateByJd,String allowDuplicateByJf2,String allowDuplicateByZh,String sourceIds) throws Exception {
		int pc = CommonTools.parseInt(paperCount);
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT count(1) FROM tk_examination a ");
		//sql.append("left join paper_examination ep on ep.qid=a.id "); //不允许重复
		//sql.append("left join paper p on p.id=ep.paper_id and p.business_id=").append(paper.getBusinessId()).append(" ");
		sql.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and a.source_id in(").append(sourceIds).append(") ");
		//不允许重复
		sql.append("  and a.id not in( ");
		sql.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id where p.business_id=").append(paper.getBusinessId());
		sql.append("  )   ");
		
		//实操题不区分 题集
		StringBuffer sqlSc = new StringBuffer(); 
		sqlSc.append("SELECT count(1) FROM tk_examination a ");
		sqlSc.append("where a.course_id=? and a.type_code=? and DIFFICULTY=? ");
		sqlSc.append("  and a.id not in( ");
		sqlSc.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id where p.business_id=").append(paper.getBusinessId());
		sqlSc.append("  )   ");
		sqlSc.append("group by a.dsh_type_code ");
		
		Map<String, PracmainSub> map = paper.getPracmain().getPracmainSubMap();
		for (Map.Entry<String, PracmainSub> entry : map.entrySet()) {
			PracmainSub ps = entry.getValue();
			String typeCode = entry.getKey();
			String sqlTemp = sql.toString();
			if("sc".equals(typeCode)) sqlTemp = sqlSc.toString();
			if("danx".equals(typeCode) && "0".equals(allowDuplicateByDanx)
					|| "duox".equals(typeCode) && "0".equals(allowDuplicateByDuox)
					|| "pand".equals(typeCode) && "0".equals(allowDuplicateByPand)
					|| "jf".equals(typeCode) && "0".equals(allowDuplicateByJf)
					|| "al".equals(typeCode) && "0".equals(allowDuplicateByAl)
					|| "sc".equals(typeCode) && "0".equals(allowDuplicateSc)
					|| "bdxxz".equals(typeCode) && "0".equals(allowDuplicateByBdxxz)
					|| "jd".equals(typeCode) && "0".equals(allowDuplicateByJd)
					|| "jf2".equals(typeCode) && "0".equals(allowDuplicateByJf2)
					|| "zh".equals(typeCode) && "0".equals(allowDuplicateByZh)){
				if(ps.getDifid1() > 0){
					Object[] args = {paper.getCourseId(),typeCode,"1"};
					int r = dao.queryForInt(sqlTemp, args);
					int lackCount = (ps.getDifid1() * pc) - r;
					if( lackCount > 0 ) {
						return chkErrorRet(typeCode,"1",lackCount,paperCount);
					}
				}
				if(ps.getDifid2() > 0){
					Object[] args = {paper.getCourseId(),typeCode,"2"};
					int r = dao.queryForInt(sqlTemp, args);
					int lackCount = (ps.getDifid2() * pc) - r;
					if( lackCount > 0 ) {
						return chkErrorRet(typeCode,"2",lackCount,paperCount);
					}
				}
				if(ps.getDifid3() > 0){
					Object[] args = {paper.getCourseId(),typeCode,"3"};
					int r = dao.queryForInt(sqlTemp, args);
					int lackCount = (ps.getDifid3() * pc) - r;
					if( lackCount > 0 ) {
						return chkErrorRet(typeCode,"3",lackCount,paperCount);
					}
				}
			}
		}
		return "";
	}
	
	public String buildPaperSave(Paper paper,String paperCount,String allowDuplicateByDanx,String allowDuplicateByDuox,String allowDuplicateByPand,String allowDuplicateByJf,
			String allowDuplicateByAl,String allowDuplicateSc,String allowDuplicateByBdxxz,String allowDuplicateByJd,String allowDuplicateByJf2,String allowDuplicateByZh) throws Exception {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		paper.setCreateTime(CalendarUtil.getCurrentDate());
		int pc = CommonTools.parseInt(paperCount);
		
		//题集
		StringBuffer sourceIds = new StringBuffer();
		String lp = "";
		if(null != paper.getSourceIds() && paper.getSourceIds().length > 0){
			for(String sid : paper.getSourceIds()){
				sourceIds.append(lp).append(sid);
				lp = ",";
			}
		}
		
		//生成前 总量验证
		String checkMsg = buildPaperCheck(paper,paperCount,allowDuplicateByDanx,allowDuplicateByDuox,allowDuplicateByPand,allowDuplicateByJf,allowDuplicateByAl,allowDuplicateSc,allowDuplicateByBdxxz,allowDuplicateByJd,allowDuplicateByJf2,allowDuplicateByZh,sourceIds.toString());
		if(!"".equals(checkMsg)){
			return checkMsg;
		}
		
		//单选SQL
		StringBuffer sqlByDanx = new StringBuffer(); 
		sqlByDanx.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByDanx.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByDanx.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByDanx.append("FROM tk_examination a ");	
//		if("0".equals(allowDuplicateByDanx)){
//			//不允许重复
//			sqlByDanx.append("left join paper_examination ep on ep.qid=a.id "); 
//			sqlByDanx.append("left join paper p on p.id=ep.paper_id and p.business_id=").append(paper.getBusinessId()).append(" ");
//		}
		sqlByDanx.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByDanx)){
			//不允许重复
			sqlByDanx.append("  and a.id not in( ");
			sqlByDanx.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByDanx.append("  )   ");
		}
		sqlByDanx.append("ORDER BY RAND() limit 0,? ");
		//多选SQL
		StringBuffer sqlByDuox = new StringBuffer(); 
		sqlByDuox.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByDuox.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByDuox.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByDuox.append("FROM tk_examination a ");		
//		if("0".equals(allowDuplicateByDuox)){
//			//不允许重复
//			sqlByDuox.append("left join paper_examination ep on ep.qid=a.id "); 
//			sqlByDuox.append("left join paper p on p.id=ep.paper_id and p.business_id=").append(paper.getBusinessId()).append(" ");
//		}
		sqlByDuox.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByDuox)){
			//不允许重复
			sqlByDuox.append("  and a.id not in( ");
			sqlByDuox.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByDuox.append("  )   ");
		}
		sqlByDuox.append("ORDER BY RAND() limit 0,? ");
		
		//判断SQL
		StringBuffer sqlByPand = new StringBuffer(); 
		sqlByPand.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByPand.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByPand.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByPand.append("FROM tk_examination a ");		
		sqlByPand.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByPand)){
			//不允许重复
			sqlByPand.append("  and a.id not in( ");
			sqlByPand.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByPand.append("  )   ");
		}
		sqlByPand.append("ORDER BY RAND() limit 0,? ");
		
		//计分SQL
		StringBuffer sqlByJf = new StringBuffer(); 
		sqlByJf.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByJf.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByJf.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByJf.append("FROM tk_examination a ");		
		sqlByJf.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByJf)){
			//不允许重复
			sqlByJf.append("  and a.id not in( ");
			sqlByJf.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByJf.append("  )   ");
		}
		sqlByJf.append("ORDER BY RAND() limit 0,? ");
		
		//案例SQL
		StringBuffer sqlByAl = new StringBuffer(); 
		sqlByAl.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByAl.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByAl.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByAl.append("FROM tk_examination a ");		
		sqlByAl.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByAl)){
			//不允许重复
			sqlByAl.append("  and a.id not in( ");
			sqlByAl.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByAl.append("  )   ");
		}
		sqlByAl.append("ORDER BY RAND() limit 0,? ");
		
		//实操题不区分 题集
		StringBuffer sqlSc = new StringBuffer(); 
		sqlSc.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlSc.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlSc.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlSc.append("FROM tk_examination a ");
		sqlSc.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? ");
		if("0".equals(allowDuplicateSc)){
			sqlSc.append("  and a.id not in( ");
			sqlSc.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlSc.append("  )   ");
		}
		sqlSc.append("group by a.dsh_type_code ");
		sqlSc.append("ORDER BY RAND() limit 0,? ");
		
		
		//不定向选择SQL ???
		StringBuffer sqlByBdxxz = new StringBuffer(); 
		sqlByBdxxz.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByBdxxz.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByBdxxz.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByBdxxz.append("FROM tk_examination a ");		
		sqlByBdxxz.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByBdxxz)){
			//不允许重复
			sqlByBdxxz.append("  and a.id not in( ");
			sqlByBdxxz.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByBdxxz.append("  )   ");
		}
		sqlByBdxxz.append("ORDER BY RAND() limit 0,? ");
		
		//简答SQL
		StringBuffer sqlByJd = new StringBuffer(); 
		sqlByJd.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByJd.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByJd.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByJd.append("FROM tk_examination a ");		
		sqlByJd.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByJd)){
			//不允许重复
			sqlByJd.append("  and a.id not in( ");
			sqlByJd.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByJd.append("  )   ");
		}
		sqlByJd.append("ORDER BY RAND() limit 0,? ");
		
		//计分(主观)SQL
		StringBuffer sqlByJf2 = new StringBuffer(); 
		sqlByJf2.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByJf2.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByJf2.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByJf2.append("FROM tk_examination a ");		
		sqlByJf2.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByJf2)){
			//不允许重复
			sqlByJf2.append("  and a.id not in( ");
			sqlByJf2.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByJf2.append("  )   ");
		}
		sqlByJf2.append("ORDER BY RAND() limit 0,? ");
		
		//综合SQL
		StringBuffer sqlByZh = new StringBuffer(); 
		sqlByZh.append("insert into paper_examination(paper_id,type_code,examination_content,examination_description "); 
		sqlByZh.append(",answer,default_point,difficulty,create_time,create_user,account_code,audit_state,dsh_type_code,qid,examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f)  "); 
		sqlByZh.append("SELECT ?,a.type_code,a.examination_content,a.EXAMINATION_DESCRIPTION,a.answer,?,a.DIFFICULTY,now(),?,a.account_code,0,a.dsh_type_code,a.id,a.examination_content_html,option_a,option_b,option_c,option_d,option_e,option_f ");
		sqlByZh.append("FROM tk_examination a ");		
		sqlByZh.append("where a.course_id=? and a.type_code=? and a.DIFFICULTY=? and source_id in(").append(sourceIds.toString()).append(") ");
		if("0".equals(allowDuplicateByZh)){
			//不允许重复
			sqlByZh.append("  and a.id not in( ");
			sqlByZh.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sqlByZh.append("  )   ");
		}
		sqlByZh.append("ORDER BY RAND() limit 0,? ");
		
		List<Object[]> batchDanxArgs = new ArrayList<Object[]>();
		List<Object[]> batchDuoxArgs = new ArrayList<Object[]>();
		List<Object[]> batchPandArgs = new ArrayList<Object[]>();
		List<Object[]> batchJfArgs = new ArrayList<Object[]>();
		List<Object[]> batchAlArgs = new ArrayList<Object[]>();
		List<Object[]> batchScArgs = new ArrayList<Object[]>();
		List<Object[]> batchBdxxzArgs = new ArrayList<Object[]>();
		List<Object[]> batchJdArgs = new ArrayList<Object[]>();
		List<Object[]> batchJf2Args = new ArrayList<Object[]>();
		List<Object[]> batchZhArgs = new ArrayList<Object[]>();
		
		String paperName = paper.getPaperName();
		for(int i=1;i<=pc;i++){
			paper.setPaperName(paperName + "_" + i);
			int pId = save(paper);
			//生成试卷明细
			Map<String, PracmainSub> map = paper.getPracmain().getPracmainSubMap();
			for (Map.Entry<String, PracmainSub> entry : map.entrySet()) {
				PracmainSub ps = entry.getValue();
				String typeCode = entry.getKey();
				if("sc".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchScArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchScArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchScArgs.add(args_3);
					}
				}else if("danx".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchDanxArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchDanxArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchDanxArgs.add(args_3);
					}
				}else if("duox".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchDuoxArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchDuoxArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchDuoxArgs.add(args_3);
					}
				}else if("pand".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchPandArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchPandArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchPandArgs.add(args_3);
					}
				}else if("jf".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchJfArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchJfArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchJfArgs.add(args_3);
					}
				}else if("al".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchAlArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchAlArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchAlArgs.add(args_3);
					}
				}else if("bdxxz".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchBdxxzArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchBdxxzArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchBdxxzArgs.add(args_3);
					}
				}else if("jd".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchJdArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchJdArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchJdArgs.add(args_3);
					}
				}else if("jf2".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchJf2Args.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchJf2Args.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchJf2Args.add(args_3);
					}
				}else if("zh".equals(typeCode)){
					if(ps.getDifid1() > 0){
						Object[] args_1 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"1",ps.getDifid1()};
						batchZhArgs.add(args_1);
					}
					if(ps.getDifid2() > 0){
						Object[] args_2 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"2",ps.getDifid2()};
						batchZhArgs.add(args_2);
					}
					if(ps.getDifid3() > 0){
						Object[] args_3 = {pId,ps.getTypeScore(),sessionUser.getId(),paper.getCourseId(),typeCode,"3",ps.getDifid3()};
						batchZhArgs.add(args_3);
					}
				}
			}
		}
		int[] retDanx = dao.batchUpdate(sqlByDanx.toString(), batchDanxArgs);
		int[] retDuox = dao.batchUpdate(sqlByDuox.toString(), batchDuoxArgs);
		int[] retPand = dao.batchUpdate(sqlByPand.toString(), batchPandArgs);
		int[] retJf = dao.batchUpdate(sqlByJf.toString(), batchJfArgs);
		int[] retAl = dao.batchUpdate(sqlByAl.toString(), batchAlArgs);
		int[] retSc = dao.batchUpdate(sqlSc.toString(), batchScArgs);
		int[] retBdxxz = dao.batchUpdate(sqlByBdxxz.toString(), batchBdxxzArgs);
		int[] retJd = dao.batchUpdate(sqlByJd.toString(), batchJdArgs);
		int[] retJf2 = dao.batchUpdate(sqlByJf2.toString(), batchJf2Args);
		int[] retZh = dao.batchUpdate(sqlByZh.toString(), batchZhArgs);
		for(int r : retDanx){
			if(r <= 0) throw new Exception();
		}
		for(int r : retDuox){
			if(r <= 0) throw new Exception();
		}
		for(int r : retPand){
			if(r <= 0) throw new Exception();
		}
		for(int r : retJf){
			if(r <= 0) throw new Exception();
		}
		for(int r : retAl){
			if(r <= 0) throw new Exception();
		}
		for(int r : retSc){
			if(r <= 0) throw new Exception();
		}
		for(int r : retBdxxz){
			if(r <= 0) throw new Exception();
		}
		for(int r : retJd){
			if(r <= 0) throw new Exception();
		}
		for(int r : retJf2){
			if(r <= 0) throw new Exception();
		}
		for(int r : retZh){
			if(r <= 0) throw new Exception();
		}
		return "";
	}

	public int checkBuildPaperCount(String courseId, String businessId, String sourceIds,String allowDuplicate,String typeCode,String diff){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(1) ");
		sql.append("FROM tk_examination a ");
		sql.append("where a.course_id=? and a.type_code=? and DIFFICULTY=? and source_id in(").append(sourceIds).append(") ");
		if(!"sc".equals(typeCode) && "0".equals(allowDuplicate)){
			//不允许重复
			sql.append("  and a.id not in( ");
			sql.append("    select ep.qid from paper p inner join paper_examination ep on ep.paper_id=p.id ");
			sql.append("  )   ");
		}
		
		Object[] args = {courseId,typeCode,diff};
		return dao.queryForInt(sql.toString(),args);
	}
	public void batchDelPaper(Integer[] paperIds) {
		if(null == paperIds) return ;
		String delPaperQuesSql = "delete from paper_examination where paper_id=?";
		StringBuffer sql = new StringBuffer();
		sql.append("delete from paper ");
		sql.append("where id=?");
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		Object[] args = {};
		for(Integer paperId : paperIds){
			args = new Object[]{paperId};
			batchArgs.add(args);
		}
		dao.batchUpdate(sql.toString(), batchArgs);
		dao.batchUpdate(delPaperQuesSql, batchArgs);
	}
	
	/**
	 * 批量更新试卷到缓存
	 * @return
	 */
	public boolean batchPaperCache(){
		List<Paper> pList = findByNoCache();
		for(Paper p : pList){
			boolean r = cachePaper(p.getId());
			if(!r){
				System.out.println("批量缓存试卷失败！" + p.getId());
				break;
			}
		}
		return true;
		
	}
	
	/**
	 * 缓存试卷
	 * @param pid
	 * @return
	 */
	public boolean cachePaper(int pid){
//		Paper paper = findForObject(pid);
//		com.education.domain.ks.Paper p = new com.education.domain.ks.Paper();
//		p.setpId(String.valueOf(pid));
//		p.setCid(String.valueOf(paper.getCourseId()));
//		p.setCname(paper.getResCourse().getCourseName());
//		List<com.education.domain.ks.QuestionBoard> qboardList = new ArrayList<com.education.domain.ks.QuestionBoard>();
//		Map<String,com.education.domain.ks.Question> qmap = new HashMap<String,com.education.domain.ks.Question>();
//		List<PaperExamination> pList = paperExaminationServices.findByPaper(pid);
//		
//		int i = 1;
//		for(PaperExamination pe : pList){
//			qboardList.add(new com.education.domain.ks.QuestionBoard(String.valueOf(i), pe.getTypeCode() ,String.valueOf(i),"0","0"));
//			
//			qmap.put(String.valueOf(i), new com.education.domain.ks.Question(String.valueOf(i), pe.getExaminationContent() , 
//					pe.getTypeCode(),getTypeName(pe.getTypeCode()),pe.getAnswer(), pe.getDefaultPoint()));
//			i++;
//		}
//		p.setQboardList(qboardList);
//		p.setQmap(qmap);
//		try {
//			cache.putPaper(p, String.valueOf(pid));
//			dao.update("update paper set cache_flag='1' , cache_time=now() where id=?" , new Object[]{pid});
//			System.out.println("cache update success.");
//			return true;
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (MemcachedException e) {
//			e.printStackTrace();
//		}
		return false;
	}
	
	private String getTypeName(String typeCode){
		return ApplicationHelper.getInstance().getQuesTypeMap().get(typeCode).getTypename();
	}

	public TPaper findTPaper(String pid) {
		TPaper tPaper = new TPaper();
		tPaper.setpId(pid);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select pe.id,pe.type_code,pe.examination_content ");
		sql.append("from paper_examination pe ");
		sql.append("where pe.paper_id=?  ");
		sql.append("order by pe.type_code");
		
		final Map<String,TQuestion> qmap = new HashMap<String,TQuestion>();
		List<TQuestionBoard> tQuestionList = dao.query(sql.toString(), new Object[]{pid}, new RowMapper<TQuestionBoard>(){
			@Override
			public TQuestionBoard mapRow(ResultSet rs, int arg1) throws SQLException {
				TQuestionBoard qb = new TQuestionBoard();
				qb.setqId(rs.getString("id"));
				qb.setqTypeCode(rs.getString("type_code"));
				
				//ques.setQcontent(rs.getString("examination_content"));
				return qb;
			}
		});
		
		tPaper.setQmap(qmap);
		return tPaper;
	}
}
