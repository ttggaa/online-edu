package com.education.module.resCourse;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.beans.ApiResult;
import com.education.domain.ResCourse;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.base.BaseController;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.module.resCourse.ResCourseServices;
import com.education.framework.util.PinyinConv;
import com.education.framework.util.calendar.CalendarUtil;

@Controller
@RequestMapping("resCourse")
public class ResCourseController extends BaseController{

	@Autowired
	private ResCourseServices services;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<ResCourse> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/resCourse/resCourseList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		return "/resCourse/resCourseEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(ResCourse resCourse, Model model,RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		resCourse.setCreateUser(sessionUser.getId());
		resCourse.setCreateTime(CalendarUtil.getCurrentDate());
		resCourse.setBusinessId(1);
		resCourse.setIndexno(0);
		services.save(resCourse);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/resCourse";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		ResCourse resCourse = services.findForObject(id);
		model.addAttribute("resCourse", resCourse);
		model.addAttribute("action", "update");
		return "/resCourse/resCourseEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(ResCourse resCourse, RedirectAttributes redirectAttributes) {
		resCourse.setBusinessId(1);
		resCourse.setIndexno(0);
		services.update(resCourse);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/resCourse";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/resCourse";
	}
	
	@RequestMapping(value = "addCourse", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult addCourse(@RequestParam(value="courseName",required=true) String courseName,
			@RequestParam(value="examSumTime",required=true) Integer examSumTime,
			@RequestParam(value="indexno",required=false) Integer indexno) {
		ApiResult apiResult = new ApiResult();
		ResCourse resCourse = new ResCourse();
		resCourse.setCourseName(courseName);
		resCourse.setCourseCode(PinyinConv.cn2py(courseName));
		resCourse.setExamSumTime(String.valueOf(examSumTime));
		resCourse.setIndexno(indexno);
		int cid = services.save(resCourse);
		resCourse.setId(cid);
		
		apiResult.setCode(1);
		apiResult.setObj(resCourse);
		return apiResult;
	}
	
}
