package com.refferal.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@Controller
@RequestMapping("/user")
public class UserAction {

	private static final Log LOGGER = LogFactory.getLog(UserAction.class);
	
	@Autowired
	private IndexAction indexAction;
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
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
			if (accessTokenObj.getAccessToken().equals("")) {
//              我们的网站被CSRF攻击了或者用户取消了授权
//              做一些数据统计工作
				LOGGER.error("登录回调没有获取到响应参数.QueryString:" + request.getQueryString() 
						+ ",qq_connect_state:" + request.getSession().getAttribute("qq_connect_state"));
			} else {
				String accessToken = accessTokenObj.getAccessToken();
                
                OpenID openIDObj =  new OpenID(accessToken);
                String openID = openIDObj.getUserOpenID();
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();

                request.getSession().setAttribute("nickname", userInfoBean.getNickname());
                request.getSession().setAttribute("avatar", userInfoBean.getAvatar().getAvatarURL30());
                
                // 微博中有公司信息等，可以考虑利用
			}
			
//			 request.getSession().setAttribute("nickname", "tiny");
//             request.getSession().setAttribute("avatar", "http://q.qlogo.cn/qqapp/101016468/E9FA4FA1E61C77871FBF3F7EAE589652/100");
			response.sendRedirect(request.getContextPath() + "/");
		} catch (QQConnectException e) {
			LOGGER.error("登录回调获取登录信息失败", e);
		} catch (IOException e) {
			LOGGER.error("登录回调跳转首页失败", e);
		}
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public JSONObject logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("nickname");
		request.getSession().removeAttribute("avatar");
		JSONObject json = new JSONObject();
		json.put("success", true);
		return json;
	}
	
}
