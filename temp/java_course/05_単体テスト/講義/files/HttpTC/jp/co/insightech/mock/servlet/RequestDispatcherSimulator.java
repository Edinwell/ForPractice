package jp.co.insightech.mock.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * RequestDispatcher の Simulator クラス
 * 
 * @author generator
 * @version $Id: RequestDispatcherSimulator.java,v 1.2 2008/05/05 00:10:11 nakajima Exp $
 */
public class RequestDispatcherSimulator implements RequestDispatcher {

	private String urlpath;

	/**
	 * コンストラクタ
	 * 
	 * @param dispatchedResource
	 */
	public RequestDispatcherSimulator(String urlpath) {
		this.urlpath = urlpath;
	}

	/**
	 * ディスパッチ先のURLを取得します
	 * 
	 * @return ディスパッチ先のURL
	 */
	public String getForward() {
		return this.urlpath;
	}

	//
	// 未実装
	//

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.RequestDispatcher#forward(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	public void forward(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.RequestDispatcher#include(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	public void include(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
	}

}
