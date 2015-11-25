package pers.magee.emap.loader;

import java.net.URLClassLoader;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 实现动态加载jar包，并找到jar中的具体class调用其方法
 * @author Administrator
 *
 */

public class MyURLClassLoader {
	public static URLClassLoader loader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
	public MyURLClassLoader(String str) throws MalformedURLException {  
		URL url = new URL(str);  
		loader = new  URLClassLoader( new URL[] { url },Thread.currentThread().getContextClassLoader());
		System.out.println("加载类完成");
//        super(new URL[] { url },Thread.currentThread().getContextClassLoader());  
    }  
      
    public   Object test(String str, String cls) {  
        try {  
//            URL url = new URL(str);  
//            MyURLClassLoader t = new MyURLClassLoader(url);  
            System.out.println("加载类完成");
            
//            Class<?> c = t.loadClass("com.magee.test.util.MyService");
//            System.out.println(c);
//            c.newInstance();
            Class c = Class.forName("com.magee.test.util.MyService",true,Thread.currentThread().getContextClassLoader());
            Method m = c.getDeclaredMethod("getnihao",new Class[] {String.class});
            Object object = null;
            Constructor con = c.getDeclaredConstructor();
			object = con.newInstance();
			m.invoke(object,"");
        } catch (Exception ex) {
        	System.out.println("加载类出错");
            ex.printStackTrace();  
        }  
        return "";  
    }  
}
