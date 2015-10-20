package pers.magee.emap.sdp.dispatcher.http;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import pers.magee.emap.core.Url;

public class ProxyerHandler {

	private static final long MAX_REQUEST_BYTE = 10485760L;

	public void handle(Url queryUrl, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		BufferedInputStream input = null;
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(2048);

		byte[] bufferRead = new byte[8192];

		input = new BufferedInputStream(request.getInputStream());

		int length = 0;
		while ((length = input.read(bufferRead)) != -1) {
			byteOutput.write(bufferRead, 0, length);
		}

		input.close();
		byteOutput.close();

		byte[] requestByteArray = byteOutput.toByteArray();

		String requestOriginal = null;
		requestOriginal = new String(requestByteArray, "UTF-8");
		System.out.println("the handler get String =" + requestOriginal);
		String message = "";
		if (requestOriginal != null && requestOriginal.length() > 0) {
			new JSONObject();
			JSONObject jsonobj =  JSONObject.fromObject(requestOriginal);
			System.out.println(jsonobj.get("params")+"---"+jsonobj.getString("content"));
			String params = jsonobj.getString("params");
			String intface = null;
			String method = null;
			int index = params.indexOf("&");
			Map<String, String> pamap = new HashMap();
			if (index >= 0) {
				String[] para = params.split("\\&");
				for (int i = 0; i < para.length; i++) {
					int j = para[i].indexOf("=");
					if (j >= 0) {
						pamap.put(para[i].substring(0, j), para[i].substring(j + 1));
					}
				}
			}

			intface = pamap.get("Interface") == null ? "" : pamap.get("Interface");
			method = pamap.get("Method") == null ? "" : pamap.get("Method");

			System.out.println("get interface = " + intface + "  ;method= " + method);

			if (intface != null && intface.length() > 0) {
				try {
					Object object;
					Class c = Class.forName(intface);
					Method m = c.getDeclaredMethod(method);
					Constructor con = c.getDeclaredConstructor();
					object = con.newInstance();
					message = (String) m.invoke(object);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		response.getWriter().write(message);

	}

}
