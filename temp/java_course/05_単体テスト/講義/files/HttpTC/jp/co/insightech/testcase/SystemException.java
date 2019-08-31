package jp.co.insightech.testcase;

/**
 * システムエラー
 * 
 * @author generator
 * @version $Id: SystemException.java,v 1.4 2008/04/13 23:55:03 okano Exp $
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 209613440807719797L;

	/**
	 * コンストラクタ
	 * 
	 * @param cause
	 *            原因となった例外
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message
	 *            エラーメッセージ
	 */
	public SystemException(String message) {
		super(message);
	}

}
