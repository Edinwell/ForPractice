package jp.co.insightech.testcase;

import java.io.File;

import jp.co.insightech.mock.servlet.ServletConfigSimulator;
import jp.co.insightech.mock.servlet.ServletContextSimulator;

/**
 * WEBアプリケーションのテストケースのヘルパークラス
 * 
 * @author generator
 * @version $Id: HttpTCHelper.java,v 1.2 2008/05/01 10:14:03 nakajima Exp $
 */
public class HttpTCHelper {

	private static final String CONTEXT_DIRECTORY_PATH = ".";

	/** ServletContext */
	public static ServletContextSimulator context;

	/** ServletConfig */
	public static ServletConfigSimulator config;

	// ServletConfig と ServletContext の初期化処理を行います
	static {
		config = new ServletConfigSimulator();
		context = (ServletContextSimulator) config.getServletContext();

		initServletContext(CONTEXT_DIRECTORY_PATH);

		initOther();
	}

	/**
	 * <pre>
	 *      Spring の設定の初期化を行います
	 *      ユーザコンテキストの設定を行います
	 *      @TODO ログインフィルタの指定を行います
	 * </pre>
	 */
	private static void initOther() {
	}

	/**
	 * ServletContextの初期化処理を行います
	 * 
	 * @param contextDirectoryPath
	 *            コンテキストディレクトリのパス
	 */
	private static void initServletContext(String contextDirectoryPath) {
		String tempDir = System.getProperty("java.io.tmpdir");
		File tempDirFile = new File(tempDir);

		context.setAttribute("javax.servlet.context.tempdir", tempDirFile);
		context.setContextDirectory(new File(contextDirectoryPath));
	}
}
