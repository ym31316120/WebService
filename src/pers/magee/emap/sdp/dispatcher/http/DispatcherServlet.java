package pers.magee.emap.sdp.dispatcher.http;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.magee.emap.core.Url;
import pers.magee.emap.core.util.ValidateUtil;

public class DispatcherServlet extends HttpServlet {

	/**
	 * 该Servlet用于处理所有的HTTP请求，并进行请求内容的路由分配处理
	 */
	private static final long serialVersionUID = 1L;
	private ProxyerHandler proHandler = new ProxyerHandler();
	private Url defaulturl = Url.get().addParameters("handler","invoker");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("this is doPost");
		String queryString = request.getQueryString();
		System.out.println("get QueryString =   "+queryString);
		byte [] responseByte = null;
		Url url = ValidateUtil.isNull(queryString)?this.defaulturl:Url.creatBy("?"+queryString);
		
		String ue = "heloege,werwekgere,werwe,wewegwge!";
		this.proHandler.handle(url, request, response);
		//这是第一种给response里传递返回消息的方式
		
//		responseByte = ue.getBytes();
//		
//		OutputStream out = response.getOutputStream();
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.setContentLength(responseByte.length);
//		response.setCharacterEncoding("UTF-8");
//
//		out.write(responseByte);
//		out.flush();
		//这是第二种给response里传递返回消息的方式
		//response.getWriter().write("hello,you success get message");
	}

}
