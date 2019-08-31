package jp.co.insightech.testcase;

import java.io.File;

import jp.co.insightech.mock.servlet.ServletConfigSimulator;
import jp.co.insightech.mock.servlet.ServletContextSimulator;

/**
 * WEB�A�v���P�[�V�����̃e�X�g�P�[�X�̃w���p�[�N���X
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

	// ServletConfig �� ServletContext �̏������������s���܂�
	static {
		config = new ServletConfigSimulator();
		context = (ServletContextSimulator) config.getServletContext();

		initServletContext(CONTEXT_DIRECTORY_PATH);

		initOther();
	}

	/**
	 * <pre>
	 *      Spring �̐ݒ�̏��������s���܂�
	 *      ���[�U�R���e�L�X�g�̐ݒ���s���܂�
	 *      @TODO ���O�C���t�B���^�̎w����s���܂�
	 * </pre>
	 */
	private static void initOther() {
	}

	/**
	 * ServletContext�̏������������s���܂�
	 * 
	 * @param contextDirectoryPath
	 *            �R���e�L�X�g�f�B���N�g���̃p�X
	 */
	private static void initServletContext(String contextDirectoryPath) {
		String tempDir = System.getProperty("java.io.tmpdir");
		File tempDirFile = new File(tempDir);

		context.setAttribute("javax.servlet.context.tempdir", tempDirFile);
		context.setContextDirectory(new File(contextDirectoryPath));
	}
}
