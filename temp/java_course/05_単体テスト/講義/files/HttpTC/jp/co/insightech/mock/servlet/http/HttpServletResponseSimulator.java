package jp.co.insightech.mock.servlet.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import jp.co.insightech.mock.servlet.ServletOutputStreamSimulator;
import junit.framework.AssertionFailedError;


/**
 * HttpServletResponse の Simulator クラス
 * 
 * @author generator
 * @version $Id: HttpServletResponseSimulator.java,v 1.5 2008/05/05 00:10:11 nakajima Exp $
 */
public class HttpServletResponseSimulator implements HttpServletResponse {

	private String encoding;

	private Locale locale;

	private int contentLength;

	private String contentType;

	private int status;

	private String message;

	private HashMap<String, String> headers;

	private HashMap<String, Cookie> cookies;

	private boolean isCommitted;

	private ServletOutputStreamSimulator out;

	private PrintWriter writer;

	/**
	 * コンストラクタ
	 */
	public HttpServletResponseSimulator() {
		this.reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#reset()
	 */
	public void reset() {
		this.encoding = null;
		this.locale = null;
		this.contentLength = 0;
		this.contentType = null;
		this.status = 200;
		this.message = null;
		this.headers = new HashMap<String, String>();
		this.cookies = new HashMap<String, Cookie>();
		this.isCommitted = false;

		this.out = new ServletOutputStreamSimulator();
		this.writer = new PrintWriter(new OutputStreamWriter(this.out));
	}

	public String getCharacterEncoding() {
		return this.encoding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#setCharacterEncoding(java.lang.String)
	 */
	public void setCharacterEncoding(String encoding) {
		this.encoding = encoding;
	}

	//
	// ヘッダー
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#containsHeader(java.lang.String)
	 */
	public boolean containsHeader(String name) {
		return headers.containsKey(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#addDateHeader(java.lang.String,
	 *      long)
	 */
	public void addDateHeader(String name, long date) {
		headers.put(name, (new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z"))
				.format(new Date(date)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#setDateHeader(java.lang.String,
	 *      long)
	 */
	public void setDateHeader(String name, long date) {
		addDateHeader(name, date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#addIntHeader(java.lang.String,
	 *      int)
	 */
	public void addIntHeader(String name, int value) {
		setIntHeader(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#setIntHeader(java.lang.String,
	 *      int)
	 */
	public void setIntHeader(String name, int value) {
		setHeader(name, String.valueOf(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#addHeader(java.lang.String,
	 *      java.lang.String)
	 */
	public void addHeader(String name, String value) {
		setHeader(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#setHeader(java.lang.String,
	 *      java.lang.String)
	 */
	public void setHeader(String name, String value) {
		if (name.equalsIgnoreCase("content-type")) {
			this.setContentType(value);
			return;
		}
		if (name.equalsIgnoreCase("content-length")) {
			this.setContentLength(Integer.parseInt(value));
			return;
		} else {
			headers.put(name, value);
			return;
		}
	}

	/**
	 * ヘッダーを取得します
	 * 
	 * @param name
	 *            ヘッダー名
	 * @return ヘッダーの値
	 */
	public String getHeader(String name) {
		if (headers.containsKey(name)) {
			return headers.get(name);
		} else {
			return null;
		}
	}

	/**
	 * ヘッダーを削除します
	 * 
	 * @param name
	 *            ヘッダー名
	 */
	public void removeHeader(String name) {
		if (headers.containsKey(name))
			headers.remove(name);
	}

	//
	// クッキー
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#addCookie(javax.servlet.http.Cookie)
	 */
	public void addCookie(Cookie cookie) {
		cookies.put(cookie.getName(), cookie);
	}

	/**
	 * クッキーを取得します
	 * 
	 * @param name
	 *            クッキー名
	 * @return クッキーの値
	 */
	public Cookie getCookie(String name) {
		return cookies.get(name);
	}

	//
	// エンコード
	// 

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#encodeRedirectUrl(java.lang.String)
	 */
	public String encodeRedirectUrl(String url) {
		return url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#encodeRedirectURL(java.lang.String)
	 */
	public String encodeRedirectURL(String url) {
		return url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#encodeUrl(java.lang.String)
	 */
	public String encodeUrl(String url) {
		return url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#encodeURL(java.lang.String)
	 */
	public String encodeURL(String url) {
		return url;
	}

	//
	// 未実装
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#flushBuffer()
	 */
	public void flushBuffer() throws IOException {
		this.writer.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#getBufferSize()
	 */
	public int getBufferSize() {
		throw new UnsupportedOperationException(
				"getBufferSize operation is not supported!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#setBufferSize(int)
	 */
	public void setBufferSize(int size) {
		throw new UnsupportedOperationException(
				"setBufferSize operation not supported.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#resetBuffer()
	 */
	public void resetBuffer() {
		throw new UnsupportedOperationException(
				"resetBuffer operation is not supported.");
	}

	//
	// コンテントレングス、コンテントタイプ
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#setContentLength(int)
	 */
	public void setContentLength(int len) {
		contentLength = len;
	}

	/**
	 * コンテントレングスを取得します
	 * 
	 * @return コンテントレングス
	 */
	public int getContentLength() {
		return contentLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#setContentType(java.lang.String)
	 */
	public void setContentType(String type) {
		contentType = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#getContentType()
	 */
	public String getContentType() {
		return contentType;
	}

	//
	// リダイレクト
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#sendRedirect(java.lang.String)
	 */
	public void sendRedirect(String location) throws IOException {
		reset();
		setStatus(302);
		setHeader("Location", location);
	}

	/**
	 * リダイレクトURLを取得します
	 * 
	 * @return リダイレクトURL
	 */
	public String getRedirectedUrl() {
		return this.getHeader("Location");
	}

	//
	// OutputStream、Writer
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#getOutputStream()
	 */
	public ServletOutputStream getOutputStream() throws IOException {
		return this.out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#getWriter()
	 */
	public PrintWriter getWriter() throws IOException {
		return this.writer;
	}

	/**
	 * Writerに出力されたデータを取得します
	 * 
	 * @return Writerに出力されたデータ
	 */
	public byte[] getWritedBytes() {
		return this.out.getWritedData();
	}

	/**
	 * Writerに出力された文字列を取得します
	 * 
	 * @return Writerに出力された文字列
	 */
	public String getWritedString() {
		if (this.encoding != null) {
			try {
				return new String(this.getWritedBytes(), this.encoding);
			} catch (UnsupportedEncodingException e) {
			}
		}

		return new String(this.getWritedBytes());
	}

	//
	// ロケール
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#getLocale()
	 */
	public Locale getLocale() {
		if (locale == null)
			return Locale.US;
		else
			return locale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#setLocale(java.util.Locale)
	 */
	public void setLocale(Locale loc) {
		locale = loc;
	}

	//
	// ステータスコード、メッセージ
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#setStatus(int)
	 */
	public void setStatus(int sc) {
		setStatus(sc, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#setStatus(int,
	 *      java.lang.String)
	 */
	public void setStatus(int sc, String sm) {
		status = sc;
		message = sm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#sendError(int)
	 */
	public void sendError(int sc) throws IOException {
		setStatus(sc);
		throw new AssertionFailedError("received error: " + sc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponse#sendError(int,
	 *      java.lang.String)
	 */
	public void sendError(int sc, String msg) throws IOException {
		setStatus(sc, msg);
		throw new AssertionFailedError("received error " + sc + " : " + msg);
	}

	/**
	 * ステータスコードを取得します
	 * 
	 * @return ステータスコード
	 */
	public int getStatusCode() {
		return status;
	}

	/**
	 * メッセージを取得します
	 * 
	 * @return メッセージ
	 */
	public String getMessage() {
		return message;
	}

	//
	// isCommitted
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponse#isCommitted()
	 */
	public boolean isCommitted() {
		return isCommitted;
	}

	/**
	 * コミットされているかどうかを設定します
	 * 
	 * @param isCommitted
	 *            コミットされているかどうか
	 */
	public void setIsCommitted(boolean isCommitted) {
		this.isCommitted = isCommitted;
	}

	@Override
	public void setContentLengthLong(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getHeaders(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
