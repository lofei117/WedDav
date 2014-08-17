/*
 * JackRabbitWebDav.java
 * @include classes:JackRabbitWebDav;interfaces:JackRabbitWebDav
 * @version 1.0.0
 * @data 2014年7月24日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.lib.webdav;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConstants;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.jackrabbit.webdav.DavConstants;
import org.apache.jackrabbit.webdav.MultiStatus;
import org.apache.jackrabbit.webdav.MultiStatusResponse;
import org.apache.jackrabbit.webdav.client.methods.CopyMethod;
import org.apache.jackrabbit.webdav.client.methods.DavMethod;
import org.apache.jackrabbit.webdav.client.methods.DeleteMethod;
import org.apache.jackrabbit.webdav.client.methods.MkColMethod;
import org.apache.jackrabbit.webdav.client.methods.MoveMethod;
import org.apache.jackrabbit.webdav.client.methods.PropFindMethod;
import org.apache.jackrabbit.webdav.client.methods.PutMethod;
import org.apache.jackrabbit.webdav.property.DavProperty;
import org.apache.jackrabbit.webdav.property.DavPropertySet;
import org.w3c.dom.Element;

/**
 * @name      JackRabbitWebDav
 * @package   com.duotin.lib.webdav
 * @author    lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version   1.0.0
 * @history
 * lofei 2014年7月24日 下午5:34:02 
 */
public class JackRabbitWebDav implements WebDav {
	
	private static final String DEFAULT_USERNAME = "anomynous";
	private static final String DEFAULT_PASSWORD = "anomynous";
	 
	private HttpClient mHttpClient;
	
	public JackRabbitWebDav() {
		this(DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}
	
	public JackRabbitWebDav(String username, String password){
		mHttpClient = new HttpClient();
		Credentials creds = new UsernamePasswordCredentials(username,
				password);
		mHttpClient.getState().setCredentials(AuthScope.ANY, creds);
	}

	private boolean isSuccess(DavMethod method){
		if(method == null){
			return false;
		}
//		int resultCode = method.getStatusCode();
//		String resultText = method.getStatusText(); 
//		System.out.println(resultCode + "," + resultText);
//		return resultText.trim().equals(RESULT_OK);
		return method.succeeded();
	}
	
	@Override
	public boolean copyMethod(String srcUri, String destUri, boolean overwrite) {
		DavMethod method = null;
		try {
			method = new CopyMethod(srcUri, destUri, overwrite);
			mHttpClient.executeMethod(method);
			return isSuccess(method);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
		
		return false;
	}

	@Override
	public boolean copyMethod(String srcUri, String destUri) {
		return copyMethod(srcUri, destUri, false);
	}

	@Override
	public boolean moveMethod(String srcUri, String destUri, boolean overwrite) {
		DavMethod method = null;
		try {
			method = new MoveMethod(srcUri, destUri, overwrite);
			mHttpClient.executeMethod(method);
			return isSuccess(method);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
		return false;
	}

	@Override
	public boolean moveMethod(String srcUri, String destUri) {
		return moveMethod(srcUri, destUri, false);
	}

	@Override
	public boolean putMethod(String uri, String localFilePath) {
		return putMethod(uri, new File(localFilePath));
	}

	@Override
	public boolean putMethod(String uri, File file) {
		if(file == null || !file.exists() || file.isDirectory()){
			return false;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return putMethod(uri, fis, file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
//		PutMethod method = null;
//        try {
//        	method = new PutMethod(uri);
//        	RequestEntity requestEntity = new FileRequestEntity(file, "audio/mp3");
//            method.setRequestEntity(requestEntity);
//			mHttpClient.executeMethod(method);
//			return isSuccess(method);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(method != null){
//				method.releaseConnection();
//				method.abort();
//			}
//		}
		return false;
	}

	@Override
	@Deprecated
	public boolean putMethod(String uri, InputStream is) {
		if(is == null){
			return false;
		}
		PutMethod method = null;
        try {
        	method = new PutMethod(uri);
        	RequestEntity requestEntity = new InputStreamRequestEntity(is);
            method.setRequestEntity(requestEntity);
			mHttpClient.executeMethod(method);
			return isSuccess(method);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
		return false;
	}
	
	@Override
	public boolean putMethod(String uri, InputStream is, long contentLength) {
		if(is == null){
			return false;
		}
		PutMethod method = null;
        try {
        	method = new PutMethod(uri);
        	RequestEntity requestEntity = new InputStreamRequestEntity(is, contentLength);
            method.setRequestEntity(requestEntity);
			mHttpClient.executeMethod(method);
			return isSuccess(method);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
		return false;
	}


	@Override
	public boolean deleteMethod(String uri) {
		DavMethod method = null;
		try {
			method = new DeleteMethod(uri);
			mHttpClient.executeMethod(method);
			return isSuccess(method);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> list(String uri, boolean includeSelf) {
		String[] subPath = uri.split("/");
		String self = "/" + subPath[subPath.length-1] + "/";
		ArrayList<String> result = new ArrayList<String>();
		DavMethod method = null;
		try {
        	method = new PropFindMethod(uri, DavConstants.PROPFIND_ALL_PROP,
                    DavConstants.DEPTH_1);
			mHttpClient.executeMethod(method);
			MultiStatus multiStatus = method.getResponseBodyAsMultiStatus();
	        MultiStatusResponse [] responses = multiStatus.getResponses();
	        for (int i = 0; i < responses.length; i++) {
//	            System.out.println(responses[i].getHref());
	        	String href = responses[i].getHref(); 
	        	if(!includeSelf && (href.equals(self) || href.equals("/"))){
	        		
	        	} else {
	        		result.add(href);
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
        
		return result;
	}

	@Override
	public ArrayList<String> list(String uri) {
		return list(uri, false);
	}

	@Override
	public boolean exists(String uri) {
		DavMethod method = null;
		try {
        	method = new PropFindMethod(uri, DavConstants.PROPFIND_ALL_PROP,
                    DavConstants.DEPTH_0);
			mHttpClient.executeMethod(method);
			MultiStatus multiStatus = method.getResponseBodyAsMultiStatus();
	        MultiStatusResponse [] responses = multiStatus.getResponses();
	        return responses.length > 0;
		} catch (Exception e) {
			
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
        
		return false;
	}

	@Override
	public boolean mkCol(String uri) {
		DavMethod method = null;
		try {
			method = new MkColMethod(uri);
			mHttpClient.executeMethod(method);
			return isSuccess(method);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
		return false;
	}

	@Override
	public boolean isCollection(String uri) {
		DavMethod method = null;
		try {
        	method = new PropFindMethod(uri, DavConstants.PROPFIND_ALL_PROP,
                    DavConstants.DEPTH_0);
			mHttpClient.executeMethod(method);
			MultiStatus multiStatus = method.getResponseBodyAsMultiStatus();
	        MultiStatusResponse [] responses = multiStatus.getResponses();
	        if(responses.length > 0){
	        	DavPropertySet set = responses[0].getProperties(HttpStatus.SC_OK);
	        	DavProperty<?> result = set.get("iscollection");
	        	if(result != null){
	        		String value = result.getValue().toString();
	        		return value.equalsIgnoreCase("true");
	        	} else {
	        		result = set.get(DavConstants.PROPERTY_RESOURCETYPE);
	        		Element resourcetype = ((Element) result.getValue());
	        		String localName = resourcetype.getLocalName();
	        		if(localName != null && localName.equalsIgnoreCase("collection")){
	        			return true;
	        		}
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(method != null){
				method.releaseConnection();
				method.abort();
			}
		}
		return false;
	}

	@Override
	public InputStream getMethodData(String uri) {
		// TODO
		InputStream is = null;
		try {
			GetMethod method = new GetMethod(uri);
			mHttpClient.executeMethod(method);
			if(method.getStatusCode() == HttpStatus.SC_OK){
				return method.getResponseBodyAsStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}
	
}
