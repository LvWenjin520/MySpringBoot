package org.lwj.MySpringBoot.mail.service;

import java.util.Set;


/**
 * 邮件发送的服务
 * @author user
 *
 */
public interface MailsService {
	
	/**
	 * 发送邮件
	 * @param userMailName
	 * @return
	 */
	boolean sendMail(Set<String> userMailName);
}
