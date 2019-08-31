package jp.co.insightech.mock.servlet;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * ServletConfig �� Simulator �N���X
 * 
 * @author generator
 * @version $Id: ServletConfigSimulator.java,v 1.3 2008/05/05 00:10:11 nakajima Exp $
 */
public class ServletConfigSimulator implements ServletConfig {

	private Hashtable<String, String> parameters;

	private ServletContext context;

	/**
	 * �R���X�g���N�^
	 */
	public ServletConfigSimulator() {
		parameters = new Hashtable<String, String>();
		context = new ServletContextSimulator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletConfig#getInitParameter(java.lang.String)
	 */
	public String getInitParameter(String name) {
		return parameters.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletConfig#getInitParameterNames()
	 */
	public Enumeration<String> getInitParameterNames() {
		return parameters.keys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletConfig#getServletContext()
	 */
	public ServletContext getServletContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletConfig#getServletName()
	 */
	public String getServletName() {
		return "ActionServlet";
	}

	/**
	 * �������p�����[�^��ݒ肵�܂�
	 * 
	 * @param key
	 *            �p�����[�^��
	 * @param value
	 *            �p�����[�^�̒l
	 */
	public void setInitParameter(String key, String value) {
		parameters.put(key, value);
	}
}
