package com.moxi.controller;

import com.moxi.model.NewsPersonnel;
import com.moxi.model.ResObject;
import com.moxi.service.NewsPersonnelService;
import com.moxi.util.Constant;
import com.moxi.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NewsPersonnelController {

	@Autowired
	private NewsPersonnelService newsPersonnelService;

	@RequestMapping("/admin/newsPersonnelManage_{pageCurrent}_{pageSize}_{pageCount}")
	public String newsPersonnelManage(NewsPersonnel newsPersonnel, @PathVariable Integer pageCurrent, @PathVariable Integer pageSize, @PathVariable Integer pageCount, Model model) {
		//判断
		if(pageSize == 0) pageSize = 10;
		if(pageCurrent == 0) pageCurrent = 1;
		int rows = newsPersonnelService.count(newsPersonnel);
		if(pageCount == 0) pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;

		//查询
		newsPersonnel.setStart((pageCurrent - 1)*pageSize);
		newsPersonnel.setEnd(pageSize);
		List<NewsPersonnel> list = newsPersonnelService.list(newsPersonnel);

		//输出
		model.addAttribute("list", list);
		String pageHTML = PageUtil.getPageContent("newsPersonnelManage_{pageCurrent}_{pageSize}_{pageCount}?name="+newsPersonnel.getName(), pageCurrent, pageSize, pageCount);
		model.addAttribute("pageHTML",pageHTML);
		model.addAttribute("newsPersonnel",newsPersonnel);
		return "news/newsPersonnelManage";
	}

	/**
	 * 文章分类新增、修改跳转
	 * @param model
	 * @param newsPersonnel
	 * @return
	 */
	@GetMapping("/admin/newsPersonnelEdit")
	public String newsPersonnelEditGet(Model model, NewsPersonnel newsPersonnel) {
		if(newsPersonnel.getId()!=0){
			NewsPersonnel newsPersonnelT = newsPersonnelService.findById(newsPersonnel);
			model.addAttribute("newsPersonnel",newsPersonnelT);
		}
		return "news/newsPersonnelEdit";
	}

	/**
	 * 文章分类新增、修改提交
	 * @param model
	 * @param newsPersonnel
	 * @param imageFile
	 * @param httpSession
	 * @return
	 */
	@PostMapping("/admin/newsPersonnelEdit")
	public String newsPersonnelEditPost(Model model, NewsPersonnel newsPersonnel, @RequestParam MultipartFile[] imageFile, HttpSession httpSession) {
		for (MultipartFile file : imageFile) {
			if (file.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = new Date();
				String strDate = sdf.format(date);
				String fileName = strDate + file.getOriginalFilename().substring(
								file.getOriginalFilename().indexOf("."),
								file.getOriginalFilename().length());
				String realPath = httpSession.getServletContext().getRealPath("/userfiles");
				System.out.println("realPath : "+realPath);
//				try {
////					FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath, fileName));
////					newsPersonnel.setImage("/userfiles/"+fileName);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
		}
		if(newsPersonnel.getId()!=0){
			newsPersonnelService.update(newsPersonnel);
		} else {
			newsPersonnelService.insert(newsPersonnel);
		}
		return "redirect:newsPersonnelManage_0_0_0";
	}
	
	@ResponseBody
	@PostMapping("/admin/newsPersonnelEditState")
	public ResObject<Object> newsPersonnelEditState(NewsPersonnel newsPersonnel) {
		newsPersonnelService.updateState(newsPersonnel);
		ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
		return object;
	}
	
}
