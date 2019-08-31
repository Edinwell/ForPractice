package jp.co.insightech.mock.servlet;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * ServletOutputStream �� Simulator �N���X
 * 
 * @author generator
 * @version $Id: ServletOutputStreamSimulator.java,v 1.4 2008/05/05 00:10:11 nakajima Exp $
 */
public class ServletOutputStreamSimulator extends ServletOutputStream {

	private ByteArrayOutputStream out;

	/**
	 * �R���X�g���N�^
	 */
	public ServletOutputStreamSimulator() {
		this.out = new ByteArrayOutputStream();
	}

	@Override
	public void write(int b) {
		out.write(b);
	}

	/**
	 * �o�̓f�[�^���擾���܂�
	 * 
	 * @return �o�̓f�[�^
	 */
	public byte[] getWritedData() {
		return this.out.toByteArray();
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO Auto-generated method stub
		
	}

}
