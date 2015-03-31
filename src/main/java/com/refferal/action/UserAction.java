package com.refferal.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.Birthday;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.javabeans.weibo.Company;
import com.qq.connect.oauth.Oauth;
import com.refferal.entity.User;
import com.refferal.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction {

	private static final Log LOGGER = LogFactory.getLog(UserAction.class);
	
	@Autowired
	private IndexAction indexAction;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
        	String referer = request.getHeader("Referer");
        	request.getSession().setAttribute("referer", referer);
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
        	LOGGER.error("跳转QQ登录失败！", e);
        } catch (IOException e) {
			LOGGER.error("跳转QQ登录IO异常！", e);
		}
	}
	
	@RequestMapping("/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			String accessToken = accessTokenObj.getAccessToken();
			if (accessToken.equals("")) {
//              我们的网站被CSRF攻击了或者用户取消了授权
//              做一些数据统计工作
				LOGGER.error("登录回调没有获取到响应参数.QueryString:" + request.getQueryString() 
						+ ",qq_connect_state:" + request.getSession().getAttribute("qq_connect_state"));
			} else {
                OpenID openIDObj =  new OpenID(accessToken);
                String openID = openIDObj.getUserOpenID();
                
                String nickname = null;
                String avatar = null;
                String gender = null;
                Date birthDate = null;
                String email = null;
                int province = 0;
                int city = 0;
                int companyId = 0;
                String companyName = null;
                String departmentName = null;
                
                // QZONE信息
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean qzoneUserInfoBean = qzoneUserInfo.getUserInfo();
                
                if (qzoneUserInfoBean.getRet() == 0) {
	                nickname = qzoneUserInfoBean.getNickname();
	                avatar = qzoneUserInfoBean.getAvatar().getAvatarURL30();
	                gender = qzoneUserInfoBean.getGender();
	                
                }
                
                // 微博信息
                com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
                com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
                if (weiboUserInfoBean.getRet() == 0) {
                	if (StringUtils.isBlank(nickname)) {
                		nickname = weiboUserInfoBean.getNickName();
                	}
                	if (StringUtils.isBlank(avatar)) {
                		avatar = weiboUserInfoBean.getAvatar().getAvatarURL30();
                	}
                	if (StringUtils.isBlank(gender)) {
                		gender = weiboUserInfoBean.getSex();
                	}
                	
                	Birthday birthday = weiboUserInfoBean.getBirthday();
                	Calendar cal = Calendar.getInstance();
                	cal.set(birthday.getYear(), birthday.getMonth(), birthday.getDay(), 0, 0, 0);
                	birthDate = cal.getTime();
            		
                	email = weiboUserInfoBean.getEmail();
                	
                	String provinceStr = weiboUserInfoBean.getProvinceCode();
                	if (StringUtils.isNotBlank(provinceStr)) {
                		province = Integer.valueOf(provinceStr) * 10000;
                	}
                	String cityStr = weiboUserInfoBean.getCityCode();
                	if (StringUtils.isNotBlank(cityStr)) {
                		city = province + Integer.valueOf(cityStr) * 100;
                	}
                	
                    ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
                    if (!companies.isEmpty()) {
                    	int beginYear = 0;
                        for (Company company : companies) {
                        	String curBeginYear = company.getBeginYear();
                        	if (StringUtils.isNotBlank(curBeginYear) && Integer.valueOf(curBeginYear) > beginYear) {
                        		beginYear = Integer.valueOf(curBeginYear);
                        		
                        		String companyIdStr = company.getID();
                        		if (StringUtils.isNotBlank(companyIdStr)) {
                        			companyId = Integer.valueOf(companyIdStr);
                        		}
                        		
                        		companyName = company.getCompanyName();
                        		departmentName = company.getDepartmentName();
                        	}
                        }
                    }
                	
                }
                
                User user = userService.selectBy(openID);
                if (user == null) {
                	user = new User();
                	user.setOpenid(openID);
                	user.setNickname(nickname);
                	user.setAvatar(avatar);
                	user.setGender(gender);
                	user.setBirthday(birthDate);
                	user.setEmail(email);
                	user.setProvince(province);
                	user.setCity(city);
                	user.setCompanyId(companyId);
                	user.setCompanyName(companyName);
                	user.setDepartmentName(departmentName);
                	userService.insert(user);
                }
                
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("nickname", nickname);
                request.getSession().setAttribute("avatar", avatar);
			}
			
//			 request.getSession().setAttribute("nickname", "tiny1231");
//             request.getSession().setAttribute("avatar", "http://q.qlogo.cn/qqapp/101016468/E9FA4FA1E61C77871FBF3F7EAE589652/100");
			String referer = (String) request.getSession().getAttribute("referer");
			if (StringUtils.isBlank(referer)) {
				referer = request.getContextPath() + "/";
			}
			response.sendRedirect(referer);
		} catch (QQConnectException e) {
			LOGGER.error("登录回调获取登录信息失败", e);
		} catch (IOException e) {
			LOGGER.error("登录回调跳转首页失败", e);
		}
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public JSONObject logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("userId");
		request.getSession().removeAttribute("nickname");
		request.getSession().removeAttribute("avatar");
		JSONObject json = new JSONObject();
		json.put("success", true);
		return json;
	}
	
}
