package com.edufe.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edufe.framework.common.Util;
import com.edufe.model.entity.ExamAccountBean;
@Service
@Transactional
public class ExamBusinessAccountServices {
	@Autowired
	protected JdbcTemplate jdbc;

	public void processMsg(ExamAccountBean bean) {
		if(null == bean) return ;
		if(null == bean.getBusinessId()) return ;
		
		int money = Util.parseInt(bean.getMoney());
		if("add".equals(bean.getType()) && money > 0){
			//增加 账户金额
			String sql = "update business set account=account+? where id=?";
			jdbc.update(sql, new Object[]{money, bean.getBusinessId()});
		}else if("login_dec".equals(bean.getType()) && money > 0){
			//验证是否已扣过费
			String costFlag = jdbc.queryForObject("select cost_flag from exam_stu where id=?", new Object[]{bean.getExamStuId()} ,String.class);
			if(!"1".equals(costFlag)){
				//减少 账户金额
				String sql = "update business set account=account-? where id=?";
				jdbc.update(sql, new Object[]{money, bean.getBusinessId()});
				
				String sql2 = "update exam_stu set cost_flag='1',loginip=?,login_time=? where id=?";
				jdbc.update(sql2, new Object[]{bean.getLoginIP(), bean.getLoginTime(), bean.getExamStuId()});
				
				//记录日志
				String ins = "insert into exam_logincost_log(login_user_id,money,create_time) values(?,?,now())";
				jdbc.update(ins, new Object[]{bean.getExamStuId(),money});
			}
		}
	}
}
