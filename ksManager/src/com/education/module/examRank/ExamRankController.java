package com.education.module.examRank;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.domain.Exam;
import com.education.domain.ExamRankStu;
import com.education.domain.extend.ExamRankBase;
import com.education.domain.extend.ExamRankCourse;
import com.education.framework.base.BaseController;
import com.education.framework.baseModule.module.business.BusinessServices;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.module.exam.ExamServices;

@Controller
@RequestMapping("examRank")
public class ExamRankController extends BaseController{

	@Autowired
	private ExamRankServices services;
	@Autowired
	private BusinessServices businessServices;
	@Autowired
	private ExamServices examServices;
	
	@RequestMapping(value = "{eid}")
	public String list(@PathVariable("eid") Integer eid, Model model, SearchParams searchParams,Page page,ServletRequest request){
		searchParams.getMap().put("eid", eid);
		List<ExamRankStu> list = services.find(searchParams,page);
		ExamRankBase erb = services.findExamRankBase(eid);
		List<ExamRankCourse> ercList = services.findExamRankCourseList(eid,erb);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("business", businessServices.findObject(SessionHelper.getInstance().getUser().getBusinessId()));
		model.addAttribute("searchParams", searchParams);
		model.addAttribute("erb", erb);
		model.addAttribute("ercList", ercList);
		return "/examRank/examRank";
	}
	
	@RequestMapping(value = "reexamine/{uid}/{examId}/{cid}")
	public String reexamine(@PathVariable(value="uid") Integer uid,@PathVariable(value="examId") Integer examId,@PathVariable(value="cid") Integer cid ,RedirectAttributes redirectAttributes) {
		boolean r = services.reexamine(uid,examId,cid);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/examRank/" + examId.intValue();
	}
	
	@RequestMapping(value = "delayed/{uid}/{examId}/{cid}/{minTime}")
	public String delayed(@PathVariable(value="uid") Integer uid,@PathVariable(value="examId") Integer examId,@PathVariable(value="cid") Integer cid ,@PathVariable(value="minTime") Integer minTime ,RedirectAttributes redirectAttributes) {
		boolean r = services.delayed(uid,examId,cid,minTime);
		if(r){
			redirectAttributes.addFlashAttribute(MESSAGE, "延时处理成功");
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		}else{
			redirectAttributes.addFlashAttribute(MESSAGE, "延时处理失败");
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-fail");
		}
		return "redirect:/examRank/" + examId.intValue();
	}
	
	@RequestMapping(value = "export/{examId}")
	public String export(@PathVariable(value="examId") Integer examId,String[] expColsCheckbox, Model model, SearchParams searchParams,HttpServletResponse response){
		Exam exam = examServices.findForObject(examId);
		try {
			services.export(exam, searchParams,expColsCheckbox, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		List<ExamRankStu> list = services.find(searchParams,null);
//		List<ExcelHeader> ehList = new ArrayList<ExcelHeader>();
//		ehList.add(new ExcelHeader("idcard","准考证号",15));
//		ehList.add(new ExcelHeader("truename","姓名",10));
//		ehList.add(new ExcelHeader("examCourse.courseName","考试科目",20));
//		ehList.add(new ExcelHeader("examCourse.score","成绩",10));
//		ehList.add(new ExcelHeader("examCourse.submitTime","交卷时间",20));
//		ehList.add(new ExcelHeader("examName","考试活动名称",50));
//		try {
//			ExcelTools.getInstance().exportExcel("data", ehList, list, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}
	
}
