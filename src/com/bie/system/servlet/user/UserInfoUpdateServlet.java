package com.bie.system.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bie.po.UserInfo;
import com.bie.system.service.UserInfoInsertService;
import com.bie.system.service.impl.UserInfoInsertServiceImpl;
import com.my.web.servlet.RequestBeanUtils;

/**
 * 用户修改的方法
 * @author Administrator
 *
 */
//@WebServlet("/system/userinfo/update")
public class UserInfoUpdateServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//使用RequestBeanUtils完成数据的获取和封装，但是要求实体类属性和前台的name属性一致哦
		UserInfo user = RequestBeanUtils.requestToSimpleBean(request, UserInfo.class);
		//然后在servlet层调用service逻辑处理层
		UserInfoInsertService service=new UserInfoInsertServiceImpl();
		//先查询再修改
		UserInfo users = service.getUserId(user);
		
		request.setAttribute("user", users);
		request.getRequestDispatcher("/view/system/userinfo/userinfo_update.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//使用工具直接获取到前台传过来的数据
		UserInfo user=RequestBeanUtils.requestToSimpleBean(request, UserInfo.class);
		System.out.println(user);//测试到这里是否出现bug
		
		//然后在servlet层调用service逻辑处理层
		UserInfoInsertService service=new UserInfoInsertServiceImpl();
		
		//先查询再修改
		/*UserInfo users = service.getUserId(user);
        users.setUserAccount(user.getUserAccount());
        users.setUserPw(user.getUserPw());
        users.setUserNumber(user.getUserNumber());
        users.setUserName(user.getUserName());
        users.setUserAge(user.getUserAge());
        users.setUserSex(user.getUserSex());
        users.setUserMark(user.getUserMark());*/
		
        //查询过后进行修改,对user2进行修改哦，切记不要修改user哦
		//调用service逻辑处理层的插入方法,返回布尔类型
		boolean mark=service.updateUser(user);
		/*HttpSession session = request.getSession();
		session.setAttribute("user", user);*/
		//返回提示信息到页面
		if(mark){
			request.setAttribute("info", "用户信息修改成功！！！");
		}else{
			request.setAttribute("info", "用户信息修改失败！！！");
		}
		//转发到页面(重定向)user_info.jsp提示信息，成功或者失败
		request.getRequestDispatcher("/view/system/userinfo/user_info.jsp").forward(request, response);
	}
	
	
}
