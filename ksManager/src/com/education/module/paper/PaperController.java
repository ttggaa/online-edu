package com.education.module.paper;

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

import com.education.domain.Paper;
import com.education.framework.base.BaseController;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.module.paper.bean.TPaper;
import com.education.module.paperExamination.PaperExaminationServices;
import com.education.module.quesSource.QuesSourceServices;
import com.education.module.resCourse.ResCourseServices;
import com.education.module.tkExamination.TkExaminationServices;

@Controller
@RequestMapping("paper")
public class PaperController extends BaseController{

	@Autowired
	private PaperServices services;
	@Autowired
	private ResCourseServices resCourseServices;
	@Autowired
	private PaperExaminationServices peServices;
	@Autowired
	private TkExaminationServices tkExaminationServices;
	@Autowired
	private QuesSourceServices quesSourceServices;
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<Paper> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/paper/paperList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		Paper paper = new Paper();
		paper.setKindCs("1");
		paper.setKindMobile("1");
		model.addAttribute("paper", paper);
		model.addAttribute("action","create");
		model.addAttribute("courseList",resCourseServices.find());
		return "/paper/paperEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(Paper paper, Model model,RedirectAttributes redirectAttributes) {
		services.save(paper);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paper";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		Paper paper = services.findForObject(id);
		model.addAttribute("paper", paper);
		model.addAttribute("action", "update");
		model.addAttribute("courseList",resCourseServices.find());
		return "/paper/paperEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Paper paper, RedirectAttributes redirectAttributes) {
		services.update(paper);
		peServices.updatePaper(paper.getId());
		
		//更新缓存
		//services.cachePaper(paper.getId());
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paper?map['businessId']=" + paper.getBusinessId();
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, SearchParams searchParams,RedirectAttributes redirectAttributes) {
		services.delete(id);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return renderRedirectString("paper",searchParams);
//		return "redirect:/paper";
	}
	
	@RequestMapping(value = "copy/{id}")
	public String copy(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("paper", services.findForObject(id));
		model.addAttribute("courseList",resCourseServices.find());
		return "/paper/paperCopy";
	}
	
	@RequestMapping(value = "pastePaper", method = RequestMethod.POST)
	public String pastePaper(Paper paper, RedirectAttributes redirectAttributes) {
		services.pastePaper(paper);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paper";
	}
	@RequestMapping(value = "submitTkExamination/{id}")
	public String submitTkExamination(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("paper", services.findForObject(id));
		model.addAttribute("quesSourceList",quesSourceServices.find());
		return "/paper/submitTkExamination";
	}
	@RequestMapping(value = "submitTkExaminationAll")
	public String submitTkExaminationAll(Model model) {
		model.addAttribute("quesSourceList",quesSourceServices.find());
		return "/paper/submitTkExaminationAll";
	}
	
	@RequestMapping(value = "submitTkExaminationSave", method = RequestMethod.POST)
	public String submitTkExaminationSave(Paper paper,String sourceId , RedirectAttributes redirectAttributes) {
		boolean r = tkExaminationServices.addPaperQuestions(paper,sourceId);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paper?map['businessId']=" + paper.getBusinessId() + "&map['courseId']=" + paper.getCourseId();
	}
	
	@RequestMapping(value = "submitTkExaminationSaveAll", method = RequestMethod.POST)
	public String submitTkExaminationSaveAll(RedirectAttributes redirectAttributes,String sourceId) {
		boolean r = tkExaminationServices.addPaperQuestionsAll(sourceId);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paper";
	}
	
	@RequestMapping(value = "updateState/{state}", method = RequestMethod.POST)
	public String updateState(@PathVariable("state") String state,Integer[] paperIds, SearchParams searchParams,ServletRequest request, RedirectAttributes redirectAttributes) {
		services.updateState(paperIds,state);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return renderRedirectString("paper",searchParams);
		//return "redirect:/paper";
	}
	@RequestMapping(value = "batchDelPaper", method = RequestMethod.POST)
	public String batchDelPaper(Integer[] paperIds, SearchParams searchParams,ServletRequest request, RedirectAttributes redirectAttributes) {
		services.batchDelPaper(paperIds);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return renderRedirectString("paper",searchParams);
	}
	
	@RequestMapping(value = "updateBusiness/{bId}", method = RequestMethod.POST)
	public String updateBusiness(@PathVariable("bId") String bId,Integer[] paperIds, SearchParams searchParams,ServletRequest request, RedirectAttributes redirectAttributes) {
		services.updateBusiness(paperIds,bId);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return renderRedirectString("paper",searchParams);
		//return "redirect:/paper";
	}
	@RequestMapping(value = "batchCopyBusiness/{bId}", method = RequestMethod.POST)
	public String batchCopyBusiness(@PathVariable("bId") String bId,Integer[] paperIds, SearchParams searchParams,ServletRequest request, RedirectAttributes redirectAttributes) {
		services.batchCopyBusiness(paperIds,bId);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return renderRedirectString("paper",searchParams);
	}
	
	@RequestMapping(value = "buildPaper", method = RequestMethod.GET)
	public String buildPaper(Model model,
			@RequestParam(value="businessId",required=false) String businessId,
			@RequestParam(value="courseId",required=false) String courseId,
			@RequestParam(value="levelId",required=false) String levelId) {
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("quesSourceList", quesSourceServices.find());
		model.addAttribute("businessId", businessId);
		model.addAttribute("courseId", courseId);
		model.addAttribute("levelId", levelId);
		return "/paper/buildPaper";
	}
	@RequestMapping(value = "buildPaperSave", method = RequestMethod.POST)
	public String buildPaperSave(Model model,Paper paper,
			@RequestParam(value="paperCount",required=true) String paperCount,
			@RequestParam(value="allowDuplicateByDanx",required=true) String allowDuplicateByDanx,
			@RequestParam(value="allowDuplicateByDuox",required=true) String allowDuplicateByDuox,
			@RequestParam(value="allowDuplicateByPand",required=true) String allowDuplicateByPand,
			@RequestParam(value="allowDuplicateByJf",required=true) String allowDuplicateByJf,
			@RequestParam(value="allowDuplicateByAl",required=true) String allowDuplicateByAl,
			@RequestParam(value="allowDuplicateSc",required=true) String allowDuplicateSc,
			@RequestParam(value="allowDuplicateByBdxxz",required=true) String allowDuplicateByBdxxz,
			@RequestParam(value="allowDuplicateByJd",required=true) String allowDuplicateByJd,
			@RequestParam(value="allowDuplicateByJf2",required=true) String allowDuplicateByJf2,
			@RequestParam(value="allowDuplicateByZh",required=true) String allowDuplicateByZh,
			RedirectAttributes redirectAttributes) {
		String r = "";
		try {
			r = services.buildPaperSave(paper,paperCount,allowDuplicateByDanx,allowDuplicateByDuox,allowDuplicateByPand,allowDuplicateByJf,allowDuplicateByAl,allowDuplicateSc,allowDuplicateByBdxxz,allowDuplicateByJd,allowDuplicateByJf2,allowDuplicateByZh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("".equals(r)) {
			redirectAttributes.addFlashAttribute("MESSAGE", "操作成功");
			return "redirect:/paper/buildPaper";
		}else{
			model.addAttribute("courseList",resCourseServices.find());
			model.addAttribute("quesSourceList", quesSourceServices.find());
			model.addAttribute("paper", paper);
			model.addAttribute("MESSAGE", "操作失败," + r);
			return "/paper/buildPaper";
		}
	}
	@RequestMapping(value = "findQuesCountAjax")
	@ResponseBody
	public String findQuesCountAjax(@RequestParam(value="cid",required=true) String cid,
			@RequestParam(value="quescode",required=true) String quescode,
			@RequestParam(value="diff",required=true) String diff,
			@RequestParam(value="quesSourceIds",required=true) String quesSourceIds){
		return services.findQuesCount(cid,quescode,diff,quesSourceIds);
	}
	
	@RequestMapping(value = "checkBuildNumPaperAjax")
	@ResponseBody
	public String checkBuildNumPaperAjax(Model model,Paper paper,
			@RequestParam(value="paperCount",required=true) String paperCount,
			@RequestParam(value="allowDuplicateByDanx",required=true) String allowDuplicateByDanx,
			@RequestParam(value="allowDuplicateByDuox",required=true) String allowDuplicateByDuox,
			@RequestParam(value="allowDuplicateByPand",required=true) String allowDuplicateByPand,
			@RequestParam(value="allowDuplicateByJf",required=true) String allowDuplicateByJf,
			@RequestParam(value="allowDuplicateByAl",required=true) String allowDuplicateByAl,
			@RequestParam(value="allowDuplicateSc",required=true) String allowDuplicateSc,
			@RequestParam(value="allowDuplicateByBdxxz",required=true) String allowDuplicateByBdxxz,
			@RequestParam(value="allowDuplicateByJd",required=true) String allowDuplicateByJd,
			@RequestParam(value="allowDuplicateByJf2",required=true) String allowDuplicateByJf2,
			@RequestParam(value="allowDuplicateByZh",required=true) String allowDuplicateByZh){
		
		StringBuffer sidArr = new StringBuffer();
		String lp = "";
		if(null != paper.getSourceIds()){
			for(String sid : paper.getSourceIds()){
				sidArr.append(lp).append(sid);
				lp = ",";
			}
		}
		String msg = "";
		try {
			msg = services.buildPaperCheck(paper,paperCount,allowDuplicateByDanx,allowDuplicateByDuox,allowDuplicateByPand,allowDuplicateByJf,allowDuplicateByAl,allowDuplicateSc,allowDuplicateByBdxxz,allowDuplicateByJd,allowDuplicateByJf2,allowDuplicateByZh,sidArr.toString());
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}
	
	@RequestMapping(value = "jsonView/{pid}", method = RequestMethod.POST)
	@ResponseBody
	public TPaper jsonView(@PathVariable("pid") String pid) {
		TPaper tPaper = services.findTPaper(pid);
		return tPaper;
	}
}
