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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.refferal.common.Const;
import com.refferal.entity.JobDescriptionDTO;
import com.refferal.mail.MailSendInfo;
import com.refferal.mail.MailService;
import com.refferal.service.JobDescriptionService;

@Controller
public class DetailAction {

	private static final Log LOGGER = LogFactory.getLog(DetailAction.class);
	
	@Autowired
	private JobDescriptionService jobDescriptionService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/detail/{id:\\d+}")
    public ModelAndView detail(@PathVariable(value="id") Integer id) {
		ModelAndView mv = new ModelAndView("job_detail");
		JobDescriptionDTO jd = jobDescriptionService.selectById(id);
		mv.addObject("jd", jd);
        return mv;
    }

	@RequestMapping("/upload")
	@ResponseBody
    public JSONObject upload(HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		
		MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest)request;
		List<MultipartFile> files = fileRequest.getFiles("qqfile");
		String localPath = null;
		for (MultipartFile file : files) {
			localPath = Const.FILE_PATH + System.currentTimeMillis() + "/" + file.getOriginalFilename();
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
    public JSONObject deliver(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String path = request.getParameter("resumePath");
		String title = request.getParameter("title");
		
		JSONObject json = new JSONObject();
		
		MailSendInfo mailInfo = new MailSendInfo();
		mailInfo.setMailServerHost("163.177.65.211");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("gotoali@foxmail.com");
		mailInfo.setPassword("zty0703");// 您的邮箱密码
		mailInfo.setFromAddress("gotoali@foxmail.com");
		mailInfo.setToAddress("churenceo@foxmail.com");
		mailInfo.setSubject("搬砖网-" + name + "-" + title);
		mailInfo.setContent("您好，这是我的简历，请查收。我的邮箱是：" + email);
		mailInfo.setAttachFileName(path.substring(path.lastIndexOf("/") + 1));
		mailInfo.setAttachFilePath(path);
		
		mailService.sendTextMail(mailInfo);
		
		return json;
		
	}
	
}
