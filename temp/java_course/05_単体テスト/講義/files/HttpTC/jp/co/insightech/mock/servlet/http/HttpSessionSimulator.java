package jp.co.insightech.mock.servlet.http;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * HttpSession の Simulator クラス
 * 
 * @author generator
 * @version $Id: HttpSessionSimulator.java,v 1.4 2008/05/05 00:10:11 nakajima Exp $
 */
public class HttpSessionSimulator implements HttpSession {

	private Hashtable<String, Object> values;

	private boolean valid;

	private ServletContext context;

	/**
	 * コンストラクタ
	 * 
	 * @param context
	 *            ServletContext
	 */
	public HttpSessionSimulator(ServletContext context) {
		valid = true;
		this.context = context;
		values = new Hashtable<String, Object>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String s) throws IllegalStateException {
		checkValid();
		return values.get(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getAttributeNames()
	 */
	public Enumeration<String> getAttributeNames() throws IllegalStateException {
		checkValid();
		return values.keys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getValue(java.lang.String)
	 */
	public Object getValue(String s) throws IllegalStateException {
		checkValid();
		return values.get(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getValueNames()
	 */
	public String[] getValueNames() throws IllegalStateException {
		checkValid();
		return (String[]) values.keySet().toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#invalidate()
	 */
	public void invalidate() throws IllegalStateException {
		checkValid();
		valid = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#putValue(java.lang.String,
	 *      java.lang.Object)
	 */
	public void putValue(String s, Object obj) throws IllegalStateException {
		checkValid();
		values.put(s, obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String s) throws IllegalStateException {
		checkValid();
		values.remove(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#removeValue(java.lang.String)
	 */
	public void removeValue(String s) throws IllegalStateException {
		checkValid();
		values.remove(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#setAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	public void setAttribute(String s, Object obj) throws IllegalStateException {
		checkValid();
		if (obj == null) {
			removeValue(s);
		} else {
			values.put(s, obj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getServletContext()
	 */
	public ServletContext getServletContext() {
		return context;
	}

	/**
	 * このセッションが invalidate されていないことをチェックします
	 * 
	 * @throws IllegalStateException
	 *             このセッションが invalidate されている場合
	 */
	private void checkValid() throws IllegalStateException {
		if (!valid) {
			throw new IllegalStateException("session has been invalidated!");
		} else {
			return;
		}
	}

	/**
	 * invalidate されているかを取得します
	 * 
	 * @return invalidate されているか
	 */
	public boolean isValid() {
		return valid;
	}

	//
	// 未実装
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getCreationTime()
	 */
	public long getCreationTime() throws IllegalStateException {
		checkValid();
		return -1L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getId()
	 */
	public String getId() {
		return "-9999";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getLastAccessedTime()
	 */
	public long getLastAccessedTime() {
		return -1L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#getMaxInactiveInterval()
	 */
	public int getMaxInactiveInterval() throws IllegalStateException {
		checkValid();
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#isNew()
	 */
	public boolean isNew() throws IllegalStateException {
		checkValid();
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSession#setMaxInactiveInterval(int)
	 */
	public void setMaxInactiveInterval(int j) {
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public javax.servlet.http.HttpSessionContext getSessionContext() {
		return null;
	}

}
