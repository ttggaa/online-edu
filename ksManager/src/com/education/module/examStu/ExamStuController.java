package com.education.module.examStu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.domain.Exam;
import com.education.domain.ExamStu;
import com.education.framework.base.BaseController;
import com.education.framework.baseModule.module.business.BusinessServices;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.exportExcel.ExcelHeader;
import com.education.framework.util.exportExcel.ExcelTools;
import com.education.module.exam.ExamServices;

@Controller
@RequestMapping("examStu")
public class ExamStuController extends BaseController{

	@Autowired
	private ExamStuServices services;
	@Autowired
	private ExamServices examServices;
	@Autowired
	private BusinessServices businessServices;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<ExamStu> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("business", businessServices.findObject(SessionHelper.getInstance().getUser().getBusinessId()));
		model.addAttribute("searchParams", searchParams);
		return "/examStu/examStuList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		model.addAttribute("examList",examServices.find());
		return "/examStu/examStuEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(ExamStu examStu, Model model,RedirectAttributes redirectAttributes) {
		services.save(examStu);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/examStu";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		ExamStu examStu = services.findForObject(id);
		model.addAttribute("examStu", examStu);
		model.addAttribute("examList",examServices.find());
		model.addAttribute("action", "update");
		return "/examStu/examStuEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(ExamStu examStu, RedirectAttributes redirectAttributes) {
		services.update(examStu);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/examStu";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/examStu";
	}
	
	@RequestMapping(value = "reexamine/{uid}/{examId}/{cid}")
	public String reexamine(@PathVariable(value="uid") Integer uid,@PathVariable(value="examId") Integer examId,@PathVariable(value="cid") Integer cid ,RedirectAttributes redirectAttributes) {
		boolean r = services.reexamine(uid,examId,cid);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/examStu";
	}
	
	@RequestMapping(value = "export")
	public String export(Model model, SearchParams searchParams,HttpServletResponse response){
		List<ExamStu> list = services.find(searchParams,null);
		List<ExcelHeader> ehList = new ArrayList<ExcelHeader>();
		ehList.add(new ExcelHeader("idcard","准考证号",15));
		ehList.add(new ExcelHeader("identitycode","身份证号",15));
		ehList.add(new ExcelHeader("truename","姓名",10));
		ehList.add(new ExcelHeader("examCourse.courseName","考试科目",20));
		ehList.add(new ExcelHeader("examCourse.score","成绩",10));
		ehList.add(new ExcelHeader("examCourse.submitTime","交卷时间",20));
		ehList.add(new ExcelHeader("examName","考试活动名称",50));
		try {
			ExcelTools.getInstance().exportExcel("data", ehList, list, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "imp", method = RequestMethod.GET)
	public String imp(Model model) {
		List<Exam> examList = examServices.find();
		model.addAttribute("examList",examList);
		return "/examStu/examStuImport";
	}
	@RequestMapping(value = "imp", method = RequestMethod.POST)
	public String imp(Exam exam,@RequestParam(value = "file", required = true) MultipartFile file,Model model,RedirectAttributes redirectAttributes) {
		List<String> r = null;
		try {
			r = services.impExcel(exam,file);
			if(r==null){
				model.addAttribute("MESSAGE","导入成功。");
				return "redirect:/examStu";
			}else{
				List<Exam> examList = examServices.find();
				model.addAttribute("examList",examList);
				model.addAttribute("MESSAGE","导入失败。");
				model.addAttribute("errorlist",r);
			}
		} catch (Exception e) {
			e.printStackTrace();
			List<Exam> examList = examServices.find();
			model.addAttribute("examList",examList);
			r = new ArrayList<String>();
			r.add("导入发生错误!");
			model.addAttribute("MESSAGE","导入失败。");
			model.addAttribute("errorlist",r);
		}
		return "/examStu/examStuImport";
	}
	
}
