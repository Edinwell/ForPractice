package jp.co.insightech.mock.servlet;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import junit.framework.Assert;

/**
 * FilterConig �� Simulator �N���X
 * 
 * @author generator
 * @version $Id: FilterConfigSimulator.java,v 1.3 2008/05/05 00:10:11 nakajima Exp $
 */
public class FilterConfigSimulator implements FilterConfig {

	private final ServletContext servletContext;

	private final String filterName;

	private final Hashtable<String, String> initParameters;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param servletContext
	 *            ServletContext
	 * @param filterName
	 *            �t�B���^�[��
	 */
	public FilterConfigSimulator(ServletContext servletContext, String filterName) {
		this.initParameters = new Hashtable<String, String>();
		this.servletContext = servletContext;
		this.filterName = filterName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.FilterConfig#getFilterName()
	 */
	public String getFilterName() {
		return filterName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.FilterConfig#getServletContext()
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * �������p�����[�^��ǉ����܂�
	 * 
	 * @param name
	 *            �p�����[�^��
	 * @param value
	 *            �p�����[�^�̒l
	 */
	public void addInitParameter(String name, String value) {
		Assert.assertNotNull("Parameter name must not be null", name);
		this.initParameters.put(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.FilterConfig#getInitParameter(java.lang.String)
	 */
	public String getInitParameter(String name) {
		Assert.assertNotNull("Parameter name must not be null", name);
		return initParameters.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.FilterConfig#getInitParameterNames()
	 */
	public Enumeration<String> getInitParameterNames() {
		return initParameters.keys();
	}

}
