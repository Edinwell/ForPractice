package jp.co.insightech.testcase;

/**
 * �V�X�e���G���[
 * 
 * @author generator
 * @version $Id: SystemException.java,v 1.4 2008/04/13 23:55:03 okano Exp $
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 209613440807719797L;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param cause
	 *            �����ƂȂ�����O
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param message
	 *            �G���[���b�Z�[�W
	 */
	public SystemException(String message) {
		super(message);
	}

}
