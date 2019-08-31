package jp.co.insightech.mock.jsp;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import junit.framework.Assert;

/**
 * PageContext の Simulator クラス
 * 
 * @author generator
 * @version $Id: PageContextSimulator.java,v 1.6 2008/05/05 01:42:01 nakajima Exp $
 */
public class PageContextSimulator extends PageContext {

	private final ServletContext servletContext;

	private final HttpServletRequest request;

	private final HttpServletResponse response;

	private final ServletConfig servletConfig;

	private final Hashtable<String, Object> attributes;

	private JspWriter writer;

	/**
	 * コンストラクタ
	 * 
	 * @param servletContext
	 * @param request
	 * @param response
	 * @param servletConfig
	 */
	public PageContextSimulator(ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response,
			ServletConfig servletConfig) {
		attributes = new Hashtable<String, Object>();
		this.servletContext = servletContext;
		this.request = request;
		this.response = response;
		this.servletConfig = servletConfig;

		this.writer = new JspWriterSimulator(false);
	}

	@Override
	public ServletRequest getRequest() {
		return request;
	}

	@Override
	public ServletResponse getResponse() {
		return response;
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public HttpSession getSession() {
		return request.getSession();
	}

	//
	// 属性
	//

	@Override
	public void setAttribute(String name, Object value) {
		Assert.assertNotNull("Attribute name must not be null", name);
		if (value != null) {
			attributes.put(name, value);
		} else {
			attributes.remove(name);
		}
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		Assert.assertNotNull("Attribute name must not be null", name);
		switch (scope) {
		case 1: // '\001'
			setAttribute(name, value);
			break;

		case 2: // '\002'
			request.setAttribute(name, value);
			break;

		case 3: // '\003'
			request.getSession().setAttribute(name, value);
			break;

		case 4: // '\004'
			servletContext.setAttribute(name, value);
			break;

		default:
			throw new IllegalArgumentException("Invalid scope: " + scope);
		}
	}

	@Override
	public Object getAttribute(String name) {
		Assert.assertNotNull("Attribute name must not be null", name);
		return attributes.get(name);
	}

	@Override
	public Object getAttribute(String name, int scope) {
		Assert.assertNotNull("Attribute name must not be null", name);
		switch (scope) {
		case 1: // '\001'
			return getAttribute(name);

		case 2: // '\002'
			return request.getAttribute(name);

		case 3: // '\003'
			HttpSession session = request.getSession(false);
			return session == null ? null : session.getAttribute(name);

		case 4: // '\004'
			return servletContext.getAttribute(name);
		}
		throw new IllegalArgumentException("Invalid scope: " + scope);
	}

	@Override
	public Object findAttribute(String name) {
		Object value = getAttribute(name);
		if (value == null) {
			value = getAttribute(name, 2);
			if (value == null) {
				value = getAttribute(name, 3);
				if (value == null) {
					value = getAttribute(name, 4);
				}
			}
		}
		return value;
	}

	@Override
	public void removeAttribute(String name) {
		Assert.assertNotNull("Attribute name must not be null", name);
		removeAttribute(name, 1);
		removeAttribute(name, 2);
		removeAttribute(name, 3);
		removeAttribute(name, 4);
	}

	@Override
	public void removeAttribute(String name, int scope) {
		Assert.assertNotNull("Attribute name must not be null", name);
		switch (scope) {
		case 1: // '\001'
			attributes.remove(name);
			break;

		case 2: // '\002'
			request.removeAttribute(name);
			break;

		case 3: // '\003'
			request.getSession().removeAttribute(name);
			break;

		case 4: // '\004'
			servletContext.removeAttribute(name);
			break;

		default:
			throw new IllegalArgumentException("Invalid scope: " + scope);
		}
	}

	@Override
	public int getAttributesScope(String name) {
		if (getAttribute(name) != null) {
			return 1;
		}
		if (getAttribute(name, 2) != null) {
			return 2;
		}
		if (getAttribute(name, 3) != null) {
			return 3;
		}
		return getAttribute(name, 4) == null ? 0 : 4;
	}

	/**
	 * 属性名の一覧を取得します
	 * 
	 * @return 属性名の一覧
	 */
	private Enumeration<String> getAttributeNames() {
		return attributes.keys();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Enumeration getAttributeNamesInScope(int scope) {
		switch (scope) {
		case 1: // '\001'
			return getAttributeNames();

		case 2: // '\002'
			return request.getAttributeNames();

		case 3: // '\003'
			HttpSession session = request.getSession(false);
			return session == null ? null : session.getAttributeNames();

		case 4: // '\004'
			return servletContext.getAttributeNames();
		}
		throw new IllegalArgumentException("Invalid scope: " + scope);
	}

	@Override
	public JspWriter getOut() {
		return this.writer;
	}

	//
	// 未実装
	//

	@Override
	public void forward(String url) throws ServletException, IOException {
		throw new UnsupportedOperationException("forward");
	}

	@Override
	public void include(String url) throws ServletException, IOException {
		throw new UnsupportedOperationException("include");
	}

	@Override
	public void include(String url, boolean flush) throws ServletException,
			IOException {
		throw new UnsupportedOperationException("include");
	}

	@Override
	public void handlePageException(Exception ex) throws ServletException,
			IOException {
		throw new UnsupportedOperationException("handlePageException");
	}

	@Override
	public void handlePageException(Throwable ex) throws ServletException,
			IOException {
		throw new UnsupportedOperationException("handlePageException");
	}

	@Override
	public Exception getException() {
		throw new UnsupportedOperationException("getException");
	}

	@Override
	public void initialize(Servlet servlet, ServletRequest request,
			ServletResponse response, String errorPageURL,
			boolean needsSession, int bufferSize, boolean autoFlush) {
		throw new UnsupportedOperationException("Use appropriate constructor");
	}

	@Override
	public void release() {
	}

	@Override
	public Object getPage() {
		throw new UnsupportedOperationException("getPage");
	}

	@Override
	@Deprecated
	public javax.servlet.jsp.el.ExpressionEvaluator getExpressionEvaluator() {
		return null;
	}

	@Override
	@Deprecated
	public javax.servlet.jsp.el.VariableResolver getVariableResolver() {
		return null;
	}
	
	@Override
	public ELContext getELContext() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
