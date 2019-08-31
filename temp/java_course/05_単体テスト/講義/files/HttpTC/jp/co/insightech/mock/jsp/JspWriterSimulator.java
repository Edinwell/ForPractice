package jp.co.insightech.mock.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.jsp.JspWriter;

/**
 * JspWriter の Simulator クラス
 * 
 * @author generator
 * @version $Id: JspWriterSimulator.java,v 1.4 2008/05/05 01:42:01 nakajima Exp $
 */
public class JspWriterSimulator extends JspWriter {

	private PrintWriter writer;

	private StringWriter stringWriter;

	private boolean error;

	/**
	 * コンストラクタ
	 * 
	 * @param error
	 *            true の場合、書き込み時に IOException を発生させます
	 */
	public JspWriterSimulator(boolean error) {
		super(0, true);
		this.error = error;
		this.stringWriter = new StringWriter();
		this.writer = new PrintWriter(this.stringWriter);
	}

	@Override
	public void close() throws IOException {
		this.checkError();
		this.writer.close();
	}

	@Override
	public void flush() throws IOException {
		this.checkError();
		this.writer.flush();
	}

	@Override
	public void newLine() throws IOException {
		this.checkError();
		this.writer.println();
	}

	@Override
	public void print(boolean flag) throws IOException {
		this.checkError();
		this.writer.print(flag);
	}

	@Override
	public void print(char c) throws IOException {
		this.checkError();
		this.writer.print(c);
	}

	@Override
	public void print(int i) throws IOException {
		this.checkError();
		this.writer.print(i);
	}

	@Override
	public void print(long l) throws IOException {
		this.checkError();
		this.writer.print(l);
	}

	@Override
	public void print(float f) throws IOException {
		this.checkError();
		this.writer.print(f);
	}

	@Override
	public void print(double d) throws IOException {
		this.checkError();
		this.writer.print(d);
	}

	@Override
	public void print(char[] ac) throws IOException {
		this.checkError();
		this.writer.print(ac);
	}

	@Override
	public void print(String s) throws IOException {
		this.checkError();
		this.writer.print(s);
	}

	@Override
	public void print(Object obj) throws IOException {
		this.checkError();
		this.writer.print(obj);
	}

	@Override
	public void println() throws IOException {
		this.checkError();
		this.writer.println();
	}

	@Override
	public void println(boolean flag) throws IOException {
		this.checkError();
		this.writer.println(flag);
	}

	@Override
	public void println(char c) throws IOException {
		this.checkError();
		this.writer.println(c);
	}

	@Override
	public void println(int i) throws IOException {
		this.checkError();
		this.writer.println(i);
	}

	@Override
	public void println(long l) throws IOException {
		this.checkError();
		this.writer.println(l);
	}

	@Override
	public void println(float f) throws IOException {
		this.checkError();
		this.writer.println(f);
	}

	@Override
	public void println(double d) throws IOException {
		this.checkError();
		this.writer.println(d);
	}

	@Override
	public void println(char[] ac) throws IOException {
		this.checkError();
		this.writer.println(ac);
	}

	@Override
	public void println(String s) throws IOException {
		this.checkError();
		this.writer.println(s);
	}

	@Override
	public void println(Object obj) throws IOException {
		this.checkError();
		this.writer.println(obj);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		this.checkError();
		this.writer.write(cbuf, off, len);
	}

	/**
	 * Writerに出力された文字列を取得します
	 * 
	 * @retun Writerに出力された文字列
	 */
	@Override
	public String toString() {
		return this.stringWriter.toString();
	}

	//
	// 未実装
	//

	@Override
	public void clear() throws IOException {
		throw new UnsupportedOperationException("getException");
	}

	@Override
	public void clearBuffer() throws IOException {
		throw new UnsupportedOperationException("getException");
	}

	@Override
	public int getRemaining() {
		throw new UnsupportedOperationException("getException");
	}

	/**
	 * <pre>
	 * エラーフラグが立っている場合、
	 * IOException を発生させます
	 * </pre>
	 * 
	 * @throws IOException
	 */
	private void checkError() throws IOException {
		if (this.error) {
			throw new IOException();
		}
	}
}
