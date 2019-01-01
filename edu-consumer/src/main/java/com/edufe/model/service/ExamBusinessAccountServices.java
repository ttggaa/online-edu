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
		}else if("dec".equals(bean.getType()) && money > 0){
			//减少 账户金额
			String sql = "update business set account=account-? where id=?";
			jdbc.update(sql, new Object[]{money, bean.getBusinessId()});
			
			if(null != bean.getExamStuId()){
				if(bean.getExamStuId().intValue() > 0){
					String sql2 = "update exam_stu set cost_flag='1' where id=?";
					jdbc.update(sql2, new Object[]{bean.getExamStuId()});
				}
			}
		}
	}
}
