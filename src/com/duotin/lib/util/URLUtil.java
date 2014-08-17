/*
 * UrlUtil.java
 * @include classes:UrlUtil;interfaces:UrlUtil
 * @version 1.0.0
 * @data 2014年5月20日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.lib.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;

/**
 * 将URL中的中文编码转化为指定编码
 * 
 * @name UrlUtil
 * @package com.duotin.car.util
 * @author lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version 1.0.0
 * @history lofei 2014年5月20日 上午11:32:23
 */
public class URLUtil {

	public static final String UTF_8 = "UTF-8";
	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";
	
	/**
	 * @see #encode(String, String)
	 * @param uri
	 * @return
	 */
	public static String encode(String uri){
		return encode(uri, UTF_8);
	}

	/**
	 * 用指定字符集对uri中的中文进行编码
	 * @param uri 要编码的uri地址
	 * @param charset 指定的字符集，默认为UTF-8
	 * @return
	 */
	public static String encode(String uri, String charset) {
		try {
			return URIUtil.encodePath(uri, charset);
		} catch (URIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
//		if (StringUtils.isEmpty(uri)) {
//			return "";
//		}
//		if (StringUtils.isEmpty(charset)) {
//			charset = UTF_8;
//		}
//		String returnUri = "";
//		try {
//			for (int i = 0; i < uri.length(); i++) {
//				String c = uri.substring(i, i + 1);
//				if (c.matches("[\\u4e00-\\u9fbb]")) {
//					c = java.net.URLEncoder.encode(c, charset);
//				}
//				returnUri = returnUri + c;
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return "";
//		}
//		return returnUri;
	}

}
