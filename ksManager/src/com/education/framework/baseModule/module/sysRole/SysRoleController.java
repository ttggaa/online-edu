package com.education.framework.baseModule.module.sysRole;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.framework.base.BaseController;
import com.education.framework.baseModule.domain.SysRole;
import com.education.framework.baseModule.domain.SysUser;
import com.education.framework.baseModule.module.sysMenu.SysMenuServices;
import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;
import com.education.framework.session.SessionHelper;

@Controller
@RequestMapping("framework/sysRole")
public class SysRoleController extends BaseController{

	@Autowired
	private SysRoleServices services;
	@Autowired
	private SysMenuServices menuServices;
	
	@RequestMapping(value = "")
	public String list(Model model, SearchParams searchParams,Page page,ServletRequest request){
		List<SysRole> list = services.find(searchParams,page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("searchParams", searchParams);
		return "/framework/sysRole/sysRoleList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("action","create");
		model.addAttribute("menuList", menuServices.findMenu(0));
		return "/framework/sysRole/sysRoleEdit";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(SysRole sysRole, Model model,RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysRole.setCreateUser(sessionUser.getId());
		sysRole.setUpdateUser(sessionUser.getId());
		services.save(sysRole);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_SAVE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysRole";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		SysRole sysRole = services.findForObject(id);
		model.addAttribute("sysRole", sysRole);
		model.addAttribute("action", "update");
		model.addAttribute("menuList", menuServices.findMenu(id));
		return "/framework/sysRole/sysRoleEdit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(SysRole sysRole, RedirectAttributes redirectAttributes) {
		SysUser sessionUser = SessionHelper.getInstance().getUser();
		sysRole.setUpdateUser(sessionUser.getId());
		services.update(sysRole);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_UPDATE_SUCCESS);
		redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
		return "redirect:/framework/sysRole";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		if(services.deleteCheck(id)){
			try{
				services.delete(id);
				redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
				redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-success");
			}catch(Exception ex){
				ex.printStackTrace();
				redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_FAIL);
				redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-error");
			}
		}else{
			redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_FAIL + ",已有用户与该角色关联，请先取消后重试！");
			redirectAttributes.addFlashAttribute(MESSAGE_STATE, "alert-error");
		}
		return "redirect:/framework/sysRole";
	}
	
	@RequestMapping(value = "deleteRoleMenu/{id}/{roleMenuId}")
	public String deleteRoleMenu(@PathVariable("id") Integer id,@PathVariable("roleMenuId") Integer roleMenuId,RedirectAttributes redirectAttributes) {
		services.deleteRoleMenu(id,roleMenuId);
		redirectAttributes.addFlashAttribute(MESSAGE, MESSAGE_DELETE_SUCCESS);
		return "redirect:/framework/sysRole/update/" + id;
	}
	
}
