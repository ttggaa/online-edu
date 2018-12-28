package com.education.module.tkExamination;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.domain.ImportReturnMessage;
import com.education.domain.TkExamination;
import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.constants.CommonConstants;
import com.education.framework.constants.DictionaryConstants;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;
import com.education.framework.util.DictionaryUtil;
import com.education.framework.util.calendar.CalendarUtil;
import com.education.framework.util.exportExcel.ExcelHeader;
import com.education.framework.util.exportExcel.ExcelTools;
import com.education.module.paper.PaperServices;
import com.education.module.quesSource.QuesSourceServices;
import com.education.module.resCourse.ResCourseServices;
import com.education.module.type.TypeServices;

@Controller
@RequestMapping("tkExamination")
public class TkExaminationController extends BaseController{

	@Autowired
	private TkExaminationServices services;
	@Autowired
	private TypeServices typeServices;
	@Autowired
	private ResCourseServices resCourseServices;
	@Autowired
	private QuesSourceServices quesSourceServices;
	@Autowired
	private PaperServices paperServices;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<TkExamination> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("batchList",services.findBatch());
		model.addAttribute("typeList",typeServices.find());
		model.addAttribute("quesTypeAnalyseList", services.quesTypeAnalyse(searchParams,page));
		model.addAttribute("quesSourceList",quesSourceServices.find());
		model.addAttribute("searchParams", searchParams);
		return "/tkExamination/tkExaminationList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(TkExamination tkExamination,Model model) {
		model.addAttribute("typeList",typeServices.find());
		model.addAttribute("courseList",resCourseServices.find());
//		model.addAttribute("difficultyList",DictionaryUtil.getSysDictionaryList(DictionaryConstants.difficulty));
//		model.addAttribute("quesSourceList",quesSourceServices.find());
		model.addAttribute("action","create");
		tkExamination.setAuditState("1");
		model.addAttribute("tkExamination", tkExamination);
		return "/tkExamination/tkExaminationEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(TkExamination tkExamination, Model model,RedirectAttributes redirectAttributes) {
		tkExamination.setCreateTime(CalendarUtil.getCurrentDate());
		if("sc".equals(tkExamination.getTypeCode())){
			String[] answerArray = tkExamination.getAnswer().split("#");
			tkExamination.setDshTypeCode(answerArray[0]);
		}
		services.save(tkExamination);
		services.updateQuesSumCountByCid(tkExamination.getCourseId());
		//更新缓存试卷
		paperServices.buildExamCachePaperByCourseId(tkExamination.getCourseId());
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/tkExamination/create?courseId=" + tkExamination.getCourseId() + "&typeCode=" 
			+ tkExamination.getTypeCode();
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		TkExamination tkExamination = services.findForObject(id);
		model.addAttribute("tkExamination", tkExamination);
		model.addAttribute("typeList",typeServices.find());
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("difficultyList",DictionaryUtil.getSysDictionaryList(DictionaryConstants.difficulty));
		model.addAttribute("quesSourceList",quesSourceServices.find());
		model.addAttribute("action", "update");
		return "/tkExamination/tkExaminationEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(TkExamination tkExamination, RedirectAttributes redirectAttributes) {
		if("sc".equals(tkExamination.getTypeCode())){
			String[] answerArray = tkExamination.getAnswer().split("#");
			tkExamination.setDshTypeCode(answerArray[0]);
		}
		services.update(tkExamination);
		services.updateQuesSumCountByCid(tkExamination.getCourseId());
		
		//更新缓存试卷
		paperServices.buildExamCachePaperByCourseId(tkExamination.getCourseId());
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/tkExamination?map['courseId']=" + tkExamination.getCourseId() + "&map['typeCode']=" 
			+ tkExamination.getTypeCode() + "&map['sourceId']=" + tkExamination.getSourceId();
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		TkExamination tkExamination = services.findForObject(id);
		services.delete(id);
		//更新缓存试卷
		paperServices.buildExamCachePaperByCourseId(tkExamination.getCourseId());
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/tkExamination?map['courseId']=" + tkExamination.getCourseId() + "&map['typeCode']=" 
		+ tkExamination.getTypeCode() + "&map['sourceId']=" + tkExamination.getSourceId();
	}
	/**
	 * 批量录入
	 * @param tkExamination
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "createByBatch", method = RequestMethod.GET)
	public String createFormByBatch(TkExamination tkExamination,Model model) {
		model.addAttribute("typeList",typeServices.find());
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("difficultyList",DictionaryUtil.getSysDictionaryList(DictionaryConstants.difficulty));
		model.addAttribute("quesSourceList",quesSourceServices.find());
		model.addAttribute("action","create");
		tkExamination.setAuditState("1");
		model.addAttribute("tkExamination", tkExamination);
		return "/tkExamination/tkExaminationEditByBatch";
	}
	
	/**
	 * 批量录入保存 (支持单题型)
	 * @param tkExamination
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveByBatch", method = RequestMethod.POST)
	public String saveByBatch(TkExamination tkExamination, Model model,RedirectAttributes redirectAttributes) {
		tkExamination.setCreateTime(CalendarUtil.getCurrentDate());
		if("sc".equals(tkExamination.getTypeCode())){
			String[] answerArray = tkExamination.getAnswer().split("#");
			tkExamination.setDshTypeCode(answerArray[0]);
		}
		services.saveByBatch(tkExamination);
		redirectAttributes.addFlashAttribute("MESSAGE", "SUCCESS");
		return "redirect:/tkExamination/createByBatch?courseId=" + tkExamination.getCourseId() + "&typeCode=" 
			+ tkExamination.getTypeCode()+"&accountCode=" + tkExamination.getAccountCode()
			+ "&defaultPoint=" + tkExamination.getDefaultPoint() + "&difficulty="+tkExamination.getDifficulty()+"&sourceId=" + tkExamination.getSourceId();
	}
	
	@RequestMapping(value = "examinationImport", method = RequestMethod.GET)
	public String examinationImport(Model model) {
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("quesSourceList",quesSourceServices.find());
		return "/tkExamination/examinationImport";
	}
	@RequestMapping(value = "imp", method = RequestMethod.POST)
	public String imp(TkExamination tkExamination,@RequestParam(value = "file", required = true) MultipartFile file,Model model,RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		tkExamination.setCreateUser(sessionUser.getId());
		tkExamination.setUpdateUser(sessionUser.getId());
		List<ImportReturnMessage> r = null;
		try {
			r = services.impExcel(tkExamination,file);
			if(r==null){
				model.addAttribute("MESSAGE","导入成功。");
				//更新缓存试卷
				paperServices.buildExamCachePaperByCourseId(tkExamination.getCourseId());
				return "redirect:/tkExamination";
			}else{
				model.addAttribute("MESSAGE","导入失败。");
				model.addAttribute("errorlist",r);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ImportReturnMessage importReturnMessage = new ImportReturnMessage();
			importReturnMessage.setMessageCode(5);
			importReturnMessage.setRetrunMessage("导入发生错误!");
			r.add(importReturnMessage);
			model.addAttribute("MESSAGE","导入失败。");
			model.addAttribute("errorlist",r);
		}
		model.addAttribute("courseList",resCourseServices.find());
		model.addAttribute("quesSourceList",quesSourceServices.find());
		return "/tkExamination/examinationImport";
	}
	
	@RequestMapping(value = "examinationExport")
	public String examinationExport(Model model, SearchParams searchParams,HttpServletResponse response){
		List<TkExamination> list = services.find(searchParams,null);
		List<ExcelHeader> ehList = new ArrayList<ExcelHeader>();
		ehList.add(new ExcelHeader("examinationContent","题干",80));
		ehList.add(new ExcelHeader("optionA","选项A",10));
		ehList.add(new ExcelHeader("optionB","选项B",10));
		ehList.add(new ExcelHeader("optionC","选项C",10));
		ehList.add(new ExcelHeader("optionD","选项D",10));
		ehList.add(new ExcelHeader("optionE","选项E",10));
		ehList.add(new ExcelHeader("optionF","选项F",10));
		ehList.add(new ExcelHeader("answer","答案",15));
		ehList.add(new ExcelHeader("type.typename","题型",15));
		ehList.add(new ExcelHeader("difficulty","难易度",15));
		ehList.add(new ExcelHeader("defaultPoint","分值",15));
		ehList.add(new ExcelHeader("id","ID",15,CommonConstants.INT_TYPE));
		ehList.add(new ExcelHeader("examinationDescription","解析",15));
		ehList.add(new ExcelHeader("courseName","课程",15));
		try {
			ExcelTools.getInstance().exportExcel("Question", ehList, list, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "batchDel")
	public String batchDel(Model model, SearchParams searchParams,RedirectAttributes redirectAttributes){
		if(services.batchDel(searchParams)){
			redirectAttributes.addFlashAttribute("MESSAGE", "删除成功");
		}else{
			redirectAttributes.addFlashAttribute("MESSAGE", "删除失败");
		}
		return "redirect:/tkExamination";
	}
	
	/**
	 * 重复试题 校验ajax 
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "checkQuesDup")
	@ResponseBody
	public String checkQuesDup(@RequestParam(value="cid",required=true) String cid,
			@RequestParam(value="qid",required=true) String qid,
			@RequestParam(value="content",required=true) String content) {
		int r = services.checkQuesDup(cid,qid,content);
		return String.valueOf(r);
	}

}
