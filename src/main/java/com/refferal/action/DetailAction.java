package com.refferal.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.refferal.common.ConfigProps;
import com.refferal.common.Const;
import com.refferal.entity.JobDescriptionDTO;
import com.refferal.mail.MailSendInfo;
import com.refferal.mail.MailService;
import com.refferal.service.FavoritesService;
import com.refferal.service.JobDescriptionService;

@Controller
public class DetailAction {

	private static final Log LOGGER = LogFactory.getLog(DetailAction.class);

	@Autowired
	private JobDescriptionService jobDescriptionService;

	@Autowired
	private FavoritesService favoritesService;
	
	@Autowired
	private MailService mailService;

	@RequestMapping("/detail/{id:\\d+}")
	public ModelAndView detail(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
		ModelAndView mv = new ModelAndView("job_detail");
		JobDescriptionDTO jd = jobDescriptionService.selectById(id);
		mv.addObject("jd", jd);
		
		boolean favorited = false;
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		if (userId != null && userId > 0) {
			int favoriteCount = favoritesService.getCount(userId, id);
			if (favoriteCount > 0) {
				favorited = true;
			}
		}
		mv.addObject("favorited", favorited);
		
		mv.addObject("bannerSelected", "job");
		return mv;
	}

	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upload(HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json = new JSONObject();

		MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = fileRequest.getFiles("qqfile");
		String localPath = null;
		for (MultipartFile file : files) {
			localPath = Const.FILE_PATH + System.currentTimeMillis() + File.separator
					+ file.getOriginalFilename();
			File localFile = new File(localPath);
			localFile.mkdirs();
			try {
				file.transferTo(localFile);
			} catch (IOException e) {
				LOGGER.error("上传简历文件失败！" + localFile, e);
				json.put("success", false);
				return json;
			}
		}

		json.put("success", true);
		json.put("path", localPath);
		return json;
	}

	@RequestMapping("/deliver")
	@ResponseBody
	public JSONObject deliver(HttpServletRequest request,
			HttpServletResponse response) {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String msg = request.getParameter("msg");
		String companyName = request.getParameter("companyName");
		String jobName = request.getParameter("jobName");
		String title = companyName + '-' + jobName;
		int id = ServletRequestUtils.getIntParameter(request, "jobId", 0);
		String path = request.getParameter("resumePath");

		JSONObject json = new JSONObject();

		// 发给搬砖君
		MailSendInfo mailInfo = newSendInfo();
		mailInfo.setToAddress("job@churenceo.com");
		mailInfo.setSubject("搬砖网-" + name + "-" + title);
		mailInfo.setContent("您好，这是我的简历，请查收。\r\n我的邮箱是：" + email
				+ "\r\n职位链接：http://www.churenceo.com/detail/" + id
				+ "\r\n" + msg);
		mailInfo.setAttachFileName(path.substring(path.lastIndexOf(File.separator) + 1));
		mailInfo.setAttachFilePath(path);
		mailService.sendTextMail(mailInfo);
		
		// 发给用户
		try {
			MailSendInfo info = newSendInfo();
			info.setToAddress(email);
			info.setSubject("【搬砖网】" + title + "投递通知");
			info.setContent(name
					+ "，你好!</br>你选择的" + title
					+ "职位投递成功，搬砖君评估通过之后，将会直接内部推荐到" + companyName
					+ "。</br>更多信息请关注我们的微信：churenceo。</br><img src=\"http://www.churenceo.com/img/weixin.jpg\">");
			// 这个类主要来发送邮件
			mailService.sendHtmlMail(info);// 发送文体格式
		} catch (Exception e) {
			LOGGER.error("发送回复邮件失败！", e);
		}
		
		return json;

	}

	private MailSendInfo newSendInfo() {
		MailSendInfo info = new MailSendInfo();
		info.setMailServerHost("smtp.qq.com");
		info.setMailServerPort("25");
		info.setValidate(true);
		info.setUserName("churenceo@foxmail.com");
		info.setPassword(ConfigProps.getProp("password"));// 您的邮箱密码
		info.setFromAddress("churenceo@foxmail.com");
		info.setFromNickName("搬砖君");
		return info;
	}
	
}
