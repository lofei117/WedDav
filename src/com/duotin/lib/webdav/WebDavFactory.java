/*
 * WebDavFactory.java
 * @include classes:WebDavFactory;interfaces:WebDavFactory
 * @version 1.0.0
 * @data 2014年7月24日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.lib.webdav;

/**
 * @name WebDavFactory
 * @package com.duotin.lib.webdav
 * @author lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version 1.0.0
 * @history lofei 2014年7月24日 下午5:38:22
 */
public class WebDavFactory {

	private WebDavFactory() {
	}
	
	public static WebDav withJackRabbitWebDav(){
		return new JackRabbitWebDav();
	}
	
	public static WebDav withJackRabbitWebDav(String username, String password){
		return new JackRabbitWebDav(username, password);
	}

}
