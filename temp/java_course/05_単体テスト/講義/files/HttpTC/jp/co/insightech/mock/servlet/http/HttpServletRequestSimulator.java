package jp.co.insightech.mock.servlet.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.ReadListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import jp.co.insightech.testcase.SystemException;

/**
 * HttpServletRequest の Simulator クラス
 * 
 * @author generator
 * @version $Id: HttpServletRequestSimulator.java,v 1.6 2008/05/05 00:10:11
 *          nakajima Exp $
 */
public class HttpServletRequestSimulator implements HttpServletRequest {

	private static final String BOUNDARY_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final String BOUNDARY = "--ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private ByteArrayOutputStream out;

	private boolean flushed;

	private Hashtable<String, Object> attributes;

	private Hashtable<String, String[]> parameters;

	private Hashtable<String, String> headers;

	private List<Cookie> cookies;

	private HttpSession session;

	private ServletContext context;

	private String scheme;

	private String protocol;

	private String requestURI;

	private String requestURL;

	private String contextPath;

	private String servletPath;

	private String pathInfo;

	private String queryString;

	private String method;

	private String contentType;

	private Locale locale;

	private Principal principal;

	private String remoteAddr;

	private String localAddr;

	private String remoteHost;

	private String localName;

	private int remotePort;

	private int localPort;

	private String remoteUser;

	private String userRole;

	private String reqSessionId;

	private String authType;

	private String charEncoding;

	private String serverName;

	private int port;

	/**
	 * コンストラクタ
	 * 
	 * @param context
	 *            ServletContext
	 */
	public HttpServletRequestSimulator(ServletContext context) {
		this.protocol = "HTTP/1.1";
		this.contextPath = "";
		this.scheme = "http";
		this.attributes = new Hashtable<String, Object>();
		this.parameters = new Hashtable<String, String[]>();
		this.headers = new Hashtable<String, String>();
		this.cookies = new ArrayList<Cookie>();
		this.context = context;
	}

	//
	// リクエストパラメータ
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
	 */
	public String getParameter(String s) {
		if (s == null) {
			return null;
		}

		Object param = parameters.get(s);

		if (null == param) {
			return null;
		}
		if (param.getClass().isArray()) {
			return ((String[]) param)[0];
		} else {
			return (String) param;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getParameterNames()
	 */
	public Enumeration<String> getParameterNames() {
		return parameters.keys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
	 */
	public String[] getParameterValues(String s) {
		if (s == null) {
			return null;
		}

		Object param = parameters.get(s);

		if (null == param) {
			return null;
		}

		if (param.getClass().isArray()) {
			return (String[]) param;
		} else {
			return (new String[] { (String) param });
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getParameterMap()
	 */
	public Map<String, String[]> getParameterMap() {
		return parameters;
	}

	/**
	 * パラメータを追加します
	 * 
	 * @param key
	 *            パラメータ名
	 * @param value
	 *            パラメータの値
	 */
	public void addParameter(String key, String value) {
		if (this.out != null) {
			this.addMultipartParameter(key, value);

		} else {
			if (key != null && value != null) {

				parameters.put(key, new String[] { value });
			}
		}
	}

	/**
	 * パラメータを追加します
	 * 
	 * @param name
	 *            パラメータ名
	 * @param values
	 *            パラメータの値（複数ある場合）
	 */
	public void addParameter(String name, String[] values) {
		if (this.out != null) {
			this.addMultipartParameter(name, values);

		} else {
			if (name != null && values != null) {
				parameters.put(name, values);
			}
		}
	}

	//
	// リクエスト属性
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String s) {
		return attributes.get(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	public Enumeration<String> getAttributeNames() {
		return attributes.keys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String s) {
		attributes.remove(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#setAttribute(java.lang.String,
	 * java.lang.Object)
	 */
	public void setAttribute(String name, Object o) {
		if (o == null) {
			attributes.remove(name);
		} else {
			attributes.put(name, o);
		}
	}

	//
	// セッション
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getSession()
	 */
	public HttpSession getSession() {
		return getSession(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
	 */
	public HttpSession getSession(boolean b) {
		if (session == null && b) {
			session = new HttpSessionSimulator(context);
		} else if (session != null && !((HttpSessionSimulator) session).isValid() && b) {
			session = new HttpSessionSimulator(context);
		}

		if (session != null && ((HttpSessionSimulator) session).isValid()) {
			return session;
		} else {
			return null;
		}
	}

	/**
	 * セッションを設定します
	 * 
	 * @param session
	 *            セッション
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	//
	// クッキー
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getCookies()
	 */
	public Cookie[] getCookies() {
		if (cookies.isEmpty()) {
			return null;
		} else {
			Cookie cookieArray[] = new Cookie[cookies.size()];
			return cookies.toArray(cookieArray);
		}
	}

	/**
	 * クッキーを追加します
	 * 
	 * @param cookie
	 *            クッキー
	 */
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}

	/**
	 * クッキーを設定します
	 * 
	 * @param cookies
	 *            クッキーの配列
	 */
	public void setCookies(Cookie[] cookies) {
		for (int i = 0; i < cookies.length; i++) {
			this.cookies.add(cookies[i]);
		}
	}

	//
	// ヘッダー
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
	 */
	public long getDateHeader(String name) {
		String dateHeader = this.getHeader(name);
		if (dateHeader == null) {
			return -1L;
		}
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
		try {
			return dateFormat.parse(dateHeader).getTime();

		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
	 */
	public Enumeration<String> getHeaderNames() {
		return headers.keys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
	 */
	public Enumeration<String> getHeaders(String s) {
		throw new UnsupportedOperationException("getHeaders operation is not supported!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
	 */
	public int getIntHeader(String s) {
		Object header;
		header = headers.get(s);

		Integer intHeader = (Integer) header;
		return intHeader.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
	 */
	public String getHeader(String s) {
		return headers.get(s);
	}

	/**
	 * ヘッダーを設定します
	 * 
	 * @param key
	 *            ヘッダー名
	 * @param value
	 *            ヘッダーの値
	 */
	public void setHeader(String key, String value) {
		headers.put(key, value);
	}

	/**
	 * 日付型ヘッダーを設定します
	 * 
	 * @param name
	 *            ヘッダー名
	 * @param millis
	 *            ヘッダーの値
	 */
	public void setDateHeader(String name, long millis) {
		String dateString = (new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z")).format(new Date(millis));
		this.setHeader(name, dateString);
	}

	//
	// リアルパス
	//

	/**
	 * @deprecated Method getRealPath is deprecated
	 */
	@Deprecated
	public String getRealPath(String path) {
		return context.getRealPath(path);
	}

	//
	// リクエストディスパッチャー
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
	 */
	public RequestDispatcher getRequestDispatcher(String url) {
		return context.getRequestDispatcher(url);
	}

	//
	// ロケール
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getLocales()
	 */
	public Enumeration<Locale> getLocales() {
		return Collections.enumeration(Collections.singleton(getLocale()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getLocale()
	 */
	public Locale getLocale() {
		if (locale == null)
			return Locale.US;
		else
			return locale;
	}

	/**
	 * ロケールを設定します
	 * 
	 * @param locale
	 *            ロケール
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	//
	// メソッド
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getMethod()
	 */
	public String getMethod() {
		if (method == null)
			return "POST";
		else
			return method;
	}

	/**
	 * メソッドを設定します
	 * 
	 * @param methodType
	 */
	public void setMethod(int methodType) {
		switch (methodType) {
		case 0: // '\0'
			method = "GET";
			break;

		case 2: // '\002'
			method = "PUT";
			break;

		case 1: // '\001'
			method = "POST";
			break;

		default:
			method = "POST";
			break;
		}
	}

	//
	// リクエストURL
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#isSecure()
	 */
	public boolean isSecure() {
		if (scheme == null) {
			return false;
		} else {
			return scheme.equalsIgnoreCase("HTTPS");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getRequestURL()
	 */
	public StringBuffer getRequestURL() {
		return new StringBuffer(requestURL);
	}

	/**
	 * リクエストURLを設定します
	 * 
	 * @param url
	 *            リクエストURL
	 */
	public void setRequestURL(String url) {
		int queryIndex = url.lastIndexOf('?');
		if (queryIndex < 0) {
			queryIndex = url.length();
		}

		this.requestURL = url.substring(0, queryIndex);

		if (queryIndex != url.length()) {
			this.setQueryString(url.substring(queryIndex + 1));
		}

		int schemeIndex = url.lastIndexOf("://");
		this.setScheme(url.substring(0, schemeIndex));

		this.setRequestURI(url.substring(url.indexOf('/', schemeIndex + 3), queryIndex));

		int portIndex = url.indexOf(':', schemeIndex + 2);
		if (portIndex > 0) {
			this.setServerName(url.substring(schemeIndex + 3, portIndex));
			this.setServerPort(Integer.parseInt(url.substring(portIndex + 1, url.indexOf('/', schemeIndex + 3))));
		} else {
			this.setServerName(url.substring(schemeIndex + 3, url.indexOf('/', schemeIndex + 3)));
			if (isSecure()) {
				this.setServerPort(443);
			} else {
				this.setServerPort(80);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getQueryString()
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * クエリーストリングを設定します
	 * 
	 * @param s
	 *            クエリーストリング
	 */
	public void setQueryString(String s) {
		queryString = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getScheme()
	 */
	public String getScheme() {
		return scheme;
	}

	/**
	 * スキーマを設定します
	 * 
	 * @param s
	 *            スキーマ
	 */
	private void setScheme(String s) {
		scheme = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getRequestURI()
	 */
	public String getRequestURI() {
		return requestURI;
	}

	/**
	 * リクエストURIを設定します
	 * 
	 * @param requestURI
	 *            リクエストURI
	 */
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getServerPort()
	 */
	public int getServerPort() {
		return port;
	}

	/**
	 * サーバーポートを設定します
	 * 
	 * @param port
	 *            サーバーポート
	 */
	private void setServerPort(int port) {
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getServerName()
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * サーバー名を設定します
	 * 
	 * @param s
	 *            サーバー名
	 */
	private void setServerName(String s) {
		serverName = s;
	}

	//
	// その他
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
	 */
	public boolean isUserInRole(String s) {
		return s.equals(userRole);
	}

	/**
	 * ロールを設定します
	 * 
	 * @param role
	 *            ロール
	 */
	public void setUserRole(String role) {
		userRole = role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	public String getCharacterEncoding() {
		return charEncoding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
	 */
	public void setCharacterEncoding(String s) {
		charEncoding = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getContentType()
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * コンテントタイプを設定します
	 * 
	 * @param s
	 *            コンテントタイプ
	 */
	public void setContentType(String s) {
		contentType = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getPathInfo()
	 */
	public String getPathInfo() {
		return pathInfo;
	}

	/**
	 * パス情報を設定します
	 * 
	 * @param s
	 *            パス情報
	 */
	public void setPathInfo(String s) {
		pathInfo = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getContextPath()
	 */
	public String getContextPath() {
		return contextPath;
	}

	/**
	 * コンテキストパスを設定します
	 * 
	 * @param s
	 *            コンテキストパス
	 */
	public void setContextPath(String s) {
		contextPath = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getServletPath()
	 */
	public String getServletPath() {
		return servletPath;
	}

	/**
	 * サーブレットパスを設定します
	 * 
	 * @param s
	 *            サーブレットパス
	 */
	public void setServletPath(String s) {
		servletPath = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getUserPrincipal()
	 */
	public Principal getUserPrincipal() {
		return principal;
	}

	/**
	 * UserPrincipal を設定します
	 * 
	 * @param principal
	 *            UserPrincipal
	 */
	public void setUserPrincipal(Principal principal) {
		this.principal = principal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getRemoteUser()
	 */
	public String getRemoteUser() {
		return remoteUser;
	}

	/**
	 * リモートユーザを設定します
	 * 
	 * @param remoteUser
	 *            リモートユーザ
	 */
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getRemoteAddr()
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}

	/**
	 * リモートアドレスを設定します
	 * 
	 * @param remoteAddr
	 *            リモートアドレス
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getRemoteHost()
	 */
	public String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * リモートホストを設定します
	 * 
	 * @param remoteHost
	 *            リモートホスト
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getRemotePort()
	 */
	public int getRemotePort() {
		return remotePort;
	}

	/**
	 * リモートポートを設定します
	 * 
	 * @param remotePort
	 *            リモートポート
	 */
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getLocalAddr()
	 */
	public String getLocalAddr() {
		return localAddr;
	}

	/**
	 * ローカルアドレスを設定します
	 * 
	 * @param localAddr
	 *            ローカルアドレス
	 */
	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getLocalName()
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * ローカルネームを設定します
	 * 
	 * @param localName
	 *            ローカルネーム
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getLocalPort()
	 */
	public int getLocalPort() {
		return localPort;
	}

	/**
	 * ローカルポートを設定します
	 * 
	 * @param localPort
	 *            ローカルポート
	 */
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getAuthType()
	 */
	public String getAuthType() {
		return authType;
	}

	/**
	 * 認証タイプを設定します
	 * 
	 * @param s
	 *            認証タイプ
	 */
	public void setAuthType(String s) {
		authType = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
	 */
	public String getRequestedSessionId() {
		return reqSessionId;
	}

	/**
	 * セッションIDを設定します
	 * 
	 * @param s
	 *            セッションID
	 */
	public void setRequestedSessionId(String s) {
		reqSessionId = s;
	}

	//
	// 未実装
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getPathTranslated()
	 */
	public String getPathTranslated() {
		throw new UnsupportedOperationException("getPathTranslated operation is not supported!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getReader()
	 */
	public BufferedReader getReader() throws IOException {
		throw new UnsupportedOperationException("getReader operation is not supported!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getProtocol()
	 */
	public String getProtocol() {
		return protocol;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return true;
	}

	/**
	 * @deprecated Method isRequestedSessionIdFromUrl is deprecated
	 */
	@Deprecated
	public boolean isRequestedSessionIdFromUrl() {
		return isRequestedSessionIdFromURL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
	 */
	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
	 */
	public boolean isRequestedSessionIdValid() {
		return true;
	}

	//
	// Multipart Request
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getInputStream()
	 */
	public ServletInputStream getInputStream() throws IOException {
		if (!flushed) {
			flush();
			flushed = true;
		}

		return new ServletInputStreamSimulator(new ByteArrayInputStream(out.toByteArray()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getContentLength()
	 */
	public int getContentLength() {
		return this.out.toByteArray().length;
	}

	/**
	 * Request を Multipart として設定します
	 */
	public void initMultipart() {
		this.setCharacterEncoding("MS932");

		this.setHeader("Content-type", "multipart/form-data; boundary=" + BOUNDARY_STR + "");
		this.setContentType("multipart/form-data; boundary=" + BOUNDARY_STR + "");

		this.out = new ByteArrayOutputStream();
		this.print(BOUNDARY);

	}

	private void println(String str) {
		try {
			this.out.write((str + "\r\n").getBytes());
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	private void print(String str) {
		try {
			this.out.write(str.getBytes());
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	private void flush() {
		this.println("--");
	}

	private class ServletInputStreamSimulator extends ServletInputStream {

		private InputStream in;

		private ServletInputStreamSimulator(InputStream in) {
			this.in = in;
		}

		@Override
		public int read() throws IOException {
			return in.read();
		}

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setReadListener(ReadListener arg0) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * MultipartRequest としてパラメータを追加します
	 * 
	 * @param name
	 *            パラメータ名
	 * @param value
	 *            パラメータの値
	 */
	public void addMultipartParameter(String name, String value) {
		this.println("");
		this.println("Content-disposition: form-data; name=\"" + name + "\"");
		this.println("");
		this.println(value);
		this.print(BOUNDARY);
	}

	/**
	 * MultipartRequest としてパラメータを追加します
	 * 
	 * @param name
	 *            パラメータ名
	 * @param values
	 *            パラメータの値（複数ある場合）
	 */
	public void addMultipartParameter(String name, String[] values) {
		for (int i = 0; i < values.length; i++) {
			addParameter(name, values[i]);
		}
	}

	@Override
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getContentLengthLong() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticate(HttpServletResponse arg0) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String changeSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Part getPart(String arg0) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void login(String arg0, String arg1) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout() throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> arg0) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

}
