package jp.co.insightech.mock.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <pre>
 * �����𐳏�ɏI�����邾���̃t�B���^�[�N���X
 * 
 * ����ɑJ�ڂ������ǂ����́A���N�G�X�g�f�B�X�p�b�`���[��forwardUrl�� success ��
 * �ݒ肳��Ă��邱�ƂŊm�F���܂�
 * </pre>
 * 
 * @author generator
 * @version $Id: SuccessFilter.java,v 1.2 2008/05/05 00:10:11 nakajima Exp $
 */
public class SuccessFilter implements FilterChain {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.FilterChain#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse) throws IOException,
			ServletException {
		servletrequest.getRequestDispatcher("success").forward(servletrequest,
				servletresponse);
	}

}
