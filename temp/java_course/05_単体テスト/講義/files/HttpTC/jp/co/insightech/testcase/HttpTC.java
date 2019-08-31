package jp.co.insightech.testcase;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import jp.co.dgic.testing.framework.DJUnitTestCase;
import jp.co.insightech.mock.jsp.PageContextSimulator;
import jp.co.insightech.mock.servlet.http.HttpServletRequestSimulator;
import jp.co.insightech.mock.servlet.http.HttpServletResponseSimulator;

/**
 * <pre>
 * HTTP�Ɉˑ������N���X���e�X�g���邽�߂̃e�X�g�P�[�X�̃x�[�X�N���X
 * </pre>
 * 
 * @author generator
 * @version $Id: HttpTC.java,v 1.5 2008/05/03 05:34:21 nakajima Exp $
 */
public abstract class HttpTC {

	/** HttpServletRequest */
	protected HttpServletRequestSimulator request;

	/** HttpServletResponse */
	protected HttpServletResponseSimulator response;

	/** HttpServletSession */
	protected HttpSession session;

	/**
	 * <pre>
	 *      HttpServletRequest ����� HttpServletResponse �𐶐����܂�
	 * </pre>
	 */
	public void setUp() throws Exception {
		this.request = new HttpServletRequestSimulator(HttpTCHelper.context);
		this.request.setContextPath("/MvcReserve");

		this.response = new HttpServletResponseSimulator();
		this.response.setCharacterEncoding("Shift_JIS");

		this.session = request.getSession();
	}

	/**
	 * PageContext �𐶐����Ď擾���܂�
	 * 
	 * @return PageContext
	 */
	public PageContext getPageContext() {
		return new PageContextSimulator(HttpTCHelper.context, this.request, this.response, HttpTCHelper.config);
	}

	public void assertForwradPath(String path) {
		assertEquals(path, HttpTCHelper.context.getRequestDispatcherSimulator().getForward());
	}
}
