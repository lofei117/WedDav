/*
 * WebDav.java
 * @include classes:WebDav;interfaces:WebDav
 * @version 1.0.0
 * @data 2014年7月24日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.lib.webdav;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * WebDav 抽象接口
 * @name      WebDav
 * @package   com.duotin.lib.webdav
 * @author    lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version   1.0.0
 * @history
 * lofei 2014年7月24日 下午3:58:22 
 */
public interface WebDav {
	
	/**
	 * 拷贝文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param srcUri 源路径，绝对路径
	 * @param destUri 目标路径，绝对路径
	 * @param overwrite 如果目标路径已存在，是否覆盖，true为覆盖
	 * @return 拷贝结果，成功返回<code>true</code>
	 */
	boolean copyMethod(String srcUri, String destUri, boolean overwrite);
	
	/**
	 * 拷贝文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param srcUri 源路径，绝对路径
	 * @param destUri 目标路径，绝对路径
	 * @return 拷贝结果，成功返回<code>true</code>，如果目标文件已存在，不进行覆盖操作
	 * @see #copyMethod(String, String, boolean)
	 */
	boolean copyMethod(String srcUri, String destUri);
	
	/**
	 * 移动文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param srcUri 源路径，绝对路径
	 * @param destUri 目标路径，绝对路径
	 * @param overwrite 如果目标路径已存在，是否覆盖，true为覆盖
	 * @return 拷贝结果，成功返回<code>true</code>
	 */
	boolean moveMethod(String srcUri, String destUri, boolean overwrite);
	
	/**
	 * 移动文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param srcUri 源路径，绝对路径
	 * @param destUri 目标路径，绝对路径
	 * @return 移动结果，成功返回<code>true</code>，如果目标文件已存在，不进行覆盖操作
	 * @see #moveMethod(String, String, boolean)
	 */
	boolean moveMethod(String srcUri, String destUri);
	
	/**
	 * 上传文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri 目标路径，绝对路径
	 * @param localFilePath 本地文件路径 
	 * @return 成功返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean putMethod(String uri, String localFilePath);
	
	/**
	 * 上传文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri 目标路径，绝对路径
	 * @param file 文件
	 * @return 成功返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean putMethod(String uri, File file);
	
	/**
	 * @deprecated 由于该方法会每次将is中的数据读出到buffer计算大小，故请不要使用该方法，请使用{@link #putMethod(String, InputStream, long)}
	 * 上传文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri 目标路径，绝对路径
	 * @param is 文件流
	 * @return 成功返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean putMethod(String uri, InputStream is);
	

	/**
	 * 上传文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri 目标路径，绝对路径
	 * @param is 文件流
	 * @param contentLength 文件长度
	 * @return 成功返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean putMethod(String uri, InputStream is, long contentLength);
	
	/**
	 * 删除指定uri的文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri 目标路径，绝对路径
	 * @return 成功返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean deleteMethod(String uri);
	
	/**
	 * 获取uri目录下的文件列表，结果集为相对路径，PropFindMethod
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri 目标路径，绝对路径
	 * @param includeSelf，是否包含uri自身路径
	 * @return 结果集的完整uri
	 */
	ArrayList<String> list(String uri, boolean includeSelf);
	
	/**
	 * 获取uri目录下的文件列表，不包括本身，PropFindMethod
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri 目标路径，绝对路径
	 * @return 结果集的完整uri
	 */
	ArrayList<String> list(String uri);
	
	/**
	 * 是否存在指定文件
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri
	 * @return
	 */
	boolean exists(String uri);
	
	/**
	 * 创建文件夹
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * </ul>
	 * @param uri 目标路径
	 * @return 成功返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean mkCol(String uri);
	
	/**
	 * 是否是文件夹
	 * @param uri 目标路径，绝对路径
	 * @return 是文件夹返回<code>true</code>，否则返回<code>false</code>
	 */
	boolean isCollection(String uri);
	
	/**
	 * 获取目标数据流<br>
	 * <b>注意：</b>
	 * <ul>
	 * <li>如果你的uri中包含UTF8字符，比如中文，你需要自己对它进行编码转换，例如 {@link org.apache.commons.httpclient.util.URIUtil#encodePath(String)}</li>
	 * <li>当你使用完数据流的时候，请务必把它关闭</li>
	 * </ul>
	 * @param uri 目标路径，绝对路径
	 * @return 如果目标不存在，或者无法获取返回null
	 */
	InputStream getMethodData(String uri);
}
