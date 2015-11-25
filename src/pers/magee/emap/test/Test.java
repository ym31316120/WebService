package pers.magee.emap.test;

import java.net.MalformedURLException;

import pers.magee.emap.loader.MyURLClassLoader;

public class Test {

	public String getSliderImageList(String params) {
		String imageList = "{'reqs':[{'imgsrc':'http://172.20.182.96:8080/WebService/img/swiperslider_001.jpg','imgname':'swiperslider_001.jpg'},"+
				"{'imgsrc':'http://172.20.182.96:8080/WebService/img/swiperslider_002.jpg','imgname':'swiperslider_002.jpg'},"+
				"{'imgsrc':'http://172.20.182.96:8080/WebService/img/swiperslider_003.jpg','imgname':'swiperslider_003.jpg'},"+
				"{'imgsrc':'http://172.20.182.96:8080/WebService/img/swiperslider_004.jpg','imgname':'swiperslider_004.jpg'}]}";
		//\"http://172.20.182.96:8080/WebService/img/swiperslider_001.jpg\",\"http://172.20.182.96:8080/WebService/img/swiperslider_002.jpg\","
			//	+ "\"http://172.20.182.96:8080/WebService/img/swiperslider_003.jpg\",\"http://172.20.182.96:8080/WebService/img/swiperslider_004.jpg\"}";
		

		return imageList;
	}
	public String setJar(String params){
		String fileUrl = "file:D:/Development/testjar.jar";
		MyURLClassLoader m;
		try {
			m = new MyURLClassLoader(fileUrl);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

}
