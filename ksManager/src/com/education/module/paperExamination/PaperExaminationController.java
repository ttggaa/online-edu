package com.education.module.paperExamination;

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

import com.education.domain.PaperExamination;
import com.education.framework.base.BaseController;
import com.education.framework.constants.DictionaryConstants;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.module.paper.PaperServices;
import com.education.module.paperExamination.PaperExaminationServices;
import com.education.module.quesSource.QuesSourceServices;
import com.education.module.resCourse.ResCourseServices;
import com.education.module.tkExamination.TkExaminationServices;
import com.education.module.type.TypeServices;
import com.education.framework.util.DictionaryUtil;

@Controller
@RequestMapping("paperExamination")
public class PaperExaminationController extends BaseController{

	@Autowired
	private PaperExaminationServices services;
	@Autowired
	private TkExaminationServices examinationServices;
	@Autowired
	private PaperServices paperServices;
	@Autowired
	private TypeServices typeServices;
	@Autowired
	private QuesSourceServices quesSourceServices;
	@Autowired
	private ResCourseServices resCourseServices;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		String paperId = request.getParameter("paperId");
		if(null != paperId && !"".equals(paperId)){
			searchParams.getMap().put("paperId", paperId);
		}
		String typeCode = request.getParameter("typeCode");
		if(null != typeCode && !"".equals(typeCode)){
			searchParams.getMap().put("typeCode", typeCode);
		}
		List<PaperExamination> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("typeList", typeServices.find());
		model.addAttribute("paperList", paperServices.find());
		model.addAttribute("quesTypeAnalyseList", paperServices.quesTypeAnalyse((String)searchParams.getMap().get("paperId")));
		model.addAttribute("searchParams", searchParams);
		return "/paperExamination/paperExaminationList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model,ServletRequest request) {
		PaperExamination paperExamination = new PaperExamination();
		String paperId = request.getParameter("paperId");
		String typeCode = request.getParameter("typeCode");
		String defaultPoint = request.getParameter("defaultPoint");
		if(null != paperId && !"".equals(paperId)) {
			paperExamination.setPaperId(Integer.parseInt(paperId));
			paperExamination.setDifficulty("1");
			paperExamination.setTypeCode(typeCode);
			paperExamination.setDefaultPoint(defaultPoint);
		}
		model.addAttribute("paperExamination", paperExamination);
		model.addAttribute("paperList", paperServices.find());
		if(paperExamination.getPaperId() > 0 ) model.addAttribute("quesTypeAnalyseList", paperServices.quesTypeAnalyse(paperExamination.getPaperId()+""));
		model.addAttribute("typeList", typeServices.find());
		model.addAttribute("difficultyList",DictionaryUtil.getSysDictionaryList(DictionaryConstants.difficulty));
		model.addAttribute("action","create");
		return "/paperExamination/paperExaminationEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(PaperExamination paperExamination, Model model,RedirectAttributes redirectAttributes) {
		if("sc".equals(paperExamination.getTypeCode())){
			String[] answerArray = paperExamination.getAnswer().split("#");
			paperExamination.setDshTypeCode(answerArray[0]);
		}
		services.save(paperExamination);
		services.updatePaper(paperExamination.getPaperId());
		redirectAttributes.addFlashAttribute("paperExamination", "SUCCESS");
		return "redirect:/paperExamination/create?paperId=" + paperExamination.getPaperId() + "&typeCode=" + paperExamination.getTypeCode() 
				+ "&defaultPoint=" + paperExamination.getDefaultPoint();
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		PaperExamination paperExamination = services.findForObject(id);
		model.addAttribute("paperExamination", paperExamination);
		model.addAttribute("paperList", paperServices.find());
		if(paperExamination.getPaperId() > 0 ) model.addAttribute("quesTypeAnalyseList", paperServices.quesTypeAnalyse(paperExamination.getPaperId()+""));
		model.addAttribute("typeList", typeServices.find());
		model.addAttribute("difficultyList",DictionaryUtil.getSysDictionaryList(DictionaryConstants.difficulty));
		model.addAttribute("action", "update");
		return "/paperExamination/paperExaminationEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(PaperExamination paperExamination,RedirectAttributes redirectAttributes) {
		if("sc".equals(paperExamination.getTypeCode())){
			String[] answerArray = paperExamination.getAnswer().split("#");
			paperExamination.setDshTypeCode(answerArray[0]);
		}
		services.update(paperExamination);
		services.updatePaper(paperExamination.getPaperId());
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paperExamination?paperId=" + paperExamination.getPaperId() + "&typeCode=" + paperExamination.getTypeCode();
	}
	
	@RequestMapping(value = "copy/{id}", method = RequestMethod.GET)
	public String copy(@PathVariable("id") Integer id, Model model) {
		PaperExamination paperExamination = services.findForObject(id);
		model.addAttribute("paperExamination", paperExamination);
		model.addAttribute("paperList", paperServices.find());
		if(paperExamination.getPaperId() > 0 ) model.addAttribute("quesTypeAnalyseList", paperServices.quesTypeAnalyse(paperExamination.getPaperId()+""));
		model.addAttribute("typeList", typeServices.find());
		model.addAttribute("difficultyList",DictionaryUtil.getSysDictionaryList(DictionaryConstants.difficulty));
		model.addAttribute("action", "update");
		return "/paperExamination/paperExaminationCopy";
	}
	
	@RequestMapping(value = "paste", method = RequestMethod.POST)
	public String paste(PaperExamination paperExamination,RedirectAttributes redirectAttributes) {
		services.save(paperExamination);
		services.updatePaper(paperExamination.getPaperId());
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paperExamination?paperId=" + paperExamination.getPaperId() + "&typeCode=" + paperExamination.getTypeCode();
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		PaperExamination paperExamination = services.findForObject(id);
		services.delete(id);
		services.updatePaper(paperExamination.getPaperId());
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paperExamination?paperId=" + paperExamination.getPaperId() + "&typeCode=" + paperExamination.getTypeCode();
	}
	
	@RequestMapping(value = "batchDelete")
	public String batchDelete(Integer[] ids,RedirectAttributes redirectAttributes) {
		PaperExamination paperExamination = new PaperExamination();
		for(int id : ids){
			paperExamination = services.findForObject(id);
			services.delete(id);
			services.updatePaper(paperExamination.getPaperId());
		}
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paperExamination?paperId=" + paperExamination.getPaperId() + "&typeCode=" + paperExamination.getTypeCode();
	}
	
	@RequestMapping(value = "buildAlAnswer")
	public String buildAlAnswer() {
		return "/paperExamination/buildAlAnswer";
	}
	
	@RequestMapping(value = "buildJfAnswer")
	public String buildJfAnswer() {
		return "/paperExamination/buildJfAnswer";
	}
	/**
	 * 重复试题 校验ajax 
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "checkQuesDup")
	@ResponseBody
	public String checkQuesDup(@RequestParam(value="qid",required=true) String qid,
			@RequestParam(value="content",required=true) String content) {
		int r = services.checkQuesDup(qid,content);
		return String.valueOf(r);
	}
	
	@RequestMapping(value = "selectQues")
	public String selectQues(@RequestParam(value="paperId",required=true) String paperId, Model model,SearchParams searchParams) {
		model.addAttribute("questionList", services.findQuestionListByNotPaper(paperId,searchParams));
		model.addAttribute("quesSourceList",quesSourceServices.find());
		model.addAttribute("typeList",typeServices.find());
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("batchList",examinationServices.findBatch());
		model.addAttribute("paperId",paperId);
		return "/paperExamination/selectQues";
	}
	@RequestMapping(value = "selectQuesSave")
	public String selectQuesSave(@RequestParam(value="paperId",required=true) String paperId, Integer[] quesIdChk,RedirectAttributes redirectAttributes) {
		services.insertQues(paperId,quesIdChk);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/paperExamination?paperId=" + paperId;
	}
}
