/*
 * TestDav.java
 * @include classes:TestDav;interfaces:TestDav
 * @version 1.0.0
 * @data 2014年7月24日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.lib.webdav;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.commons.httpclient.util.URIUtil;

import com.duotin.lib.util.URLUtil;

/**
 * @name TestDav
 * @package com.duotin.lib.webdav
 * @author lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version 1.0.0
 * @history lofei 2014年7月24日 下午4:00:30
 */
public class TestDav {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "http://192.168.30.70:8080/";
		try {
			
			WebDav webDav = WebDavFactory.withJackRabbitWebDav();

			boolean result = webDav.mkCol(path + "test");
			
			System.out.println("mkcol test folder:" + result);
			
			result = webDav.isCollection(path + "test.bb");
			
			System.out.println("is folder:" + result);
			
			String chinesePath = path + "/我是中国人/";
			chinesePath = URLUtil.encode(chinesePath);
			result = webDav.isCollection(chinesePath);

			System.out.println("is folder:" + result);
			
			result = webDav.putMethod(URLUtil.encode(path + "test/我是大帅哥lena.png", URLUtil.UTF_8), new FileInputStream("/Users/lofei/Pictures/viewfile.png"));
			System.out.println("put image file:" + result);

			result = webDav.copyMethod(URLUtil.encode(path + "test/我是大帅哥lena.png", URLUtil.UTF_8),URLUtil.encode(path + "test/我是大帅哥lena2.png", URLUtil.UTF_8));
			System.out.println("copy image file:" + result);

			result = webDav.moveMethod(URLUtil.encode(path + "test/我是大帅哥lena.png", URLUtil.UTF_8),URLUtil.encode(path + "test/我是大帅哥lena.png", URLUtil.UTF_8), true);
			System.out.println("move image file:" + result);

			ArrayList<String> files = webDav.list(path+"test/test");
			for(String s : files){
				System.out.println(s);
			}
			
			System.out.println("exists:" + webDav.exists(path + "test2/"));
			System.out.println("exists:" + webDav.exists(path + "test2"));

			result = webDav.deleteMethod(
					path + "test2/");
			System.out.println("delete test folder:" + result);
			
			System.out.println("exists:" + webDav.exists(path + "test2/"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
