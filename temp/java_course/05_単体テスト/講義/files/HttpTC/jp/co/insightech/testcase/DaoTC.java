package jp.co.insightech.testcase;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.CompositeTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * <pre>
 * 
 * DBUnit���g�p����ڑ��ݒ��A�e�[�u���̏����ݒ�Ȃǂ��������钊�ۃN���X.
 * 
 * Dao�̃e�X�g�P�[�X���쐬����ꍇ�͂��̃N���X���p�����܂�.
 * 
 * </pre>
 */
public abstract class DaoTC {

	/** URL */
	private static final String URL = "jdbc:mysql://localhost/reserve?useUnicode=true&characterEncoding=MS932";

	/** Driver */
	private static final String DRIVER = "org.gjt.mm.mysql.Driver";

	/** User */
	private static final String USER = "root";

	/** Password */
	private static final String PASSWORD = "";

	private static IDatabaseTester dbTester;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbTester = new JdbcDatabaseTester(DRIVER, URL, USER, PASSWORD);
		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		dbTester.setTearDownOperation(DatabaseOperation.DELETE);
	}

	/**
	 * �Z�b�g�A�b�v
	 */
	@Before
	public void setUp() throws Exception {
		dbTester.setDataSet(new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(getInitXml())));
		dbTester.onSetup();
	}

	/**
	 * �e�B�A�_�E��
	 */
	@After
	public void tearDown() throws Exception {
		dbTester.onTearDown();
	}

	/**
	 * CLEAN_INSERT����
	 * 
	 * @param xmlName
	 *            �Ώ�XML��
	 * @throws Exception
	 */
	protected void cleanInsert(String xmlName) throws Exception {
		IDataSet xmlDataSet = new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(xmlName));

		IDatabaseConnection connection = dbTester.getConnection();
		DatabaseOperation.CLEAN_INSERT.execute(connection, xmlDataSet);

		if (!connection.getConnection().getAutoCommit()) {
			connection.getConnection().commit();
		}

		connection.close();
	}

	/**
	 * ������XML���擾 Override���A�������Ώۂ�XML�����L�q���邱��
	 * ���AgetClass().getResourceAsStream("name")�ɂĎ擾����̂� �p�X�͕K�v���� ex) return
	 * "init_table.xml";
	 * 
	 * @return ������Ԃ��L�q����XML
	 */
	protected String getInitXml() {
		return null;
	}

	/**
	 * �e�X�g���s�i�f�[�^�Z�b�g�P�ʁj
	 * 
	 * @param xmlName
	 *            ���҂���DB��Ԃ̋L�q���ꂽXML
	 * @throws Exception
	 */
	protected void assertDBDataSet(String xmlName) throws Exception {
		IDataSet xmlDataSet = new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(xmlName));

		IDatabaseConnection connection = dbTester.getConnection();
		IDataSet dbDataSet = connection.createDataSet();

		String[] tableNames = xmlDataSet.getTableNames();
		for (int i = 0; i < tableNames.length; i++) {
			ITable expectTable = xmlDataSet.getTable(tableNames[i]);
			ITable resultTable = dbDataSet.getTable(tableNames[i]);
			// XML�ɖ����J�����͖���
			ITable compositTable = new CompositeTable(expectTable.getTableMetaData(), resultTable);

			debug(expectTable, compositTable);

			Assertion.assertEquals(expectTable, compositTable);
		}

		connection.close();
	}

	private void debug(ITable expectTable, ITable compositTable) {

		System.out.println("------- expected -------");
		System.out.println(toString(expectTable));
		System.out.println("------- composit -------");
		System.out.println(toString(compositTable));

	}

	private String toString(ITable table) {
		StringBuffer buf = new StringBuffer();

		try {
			ITableMetaData data = table.getTableMetaData();
			Column[] columns = data.getColumns();
			String[] columnNames = new String[columns.length];
			for (int i = 0; i < columns.length; i++) {
				String name = columns[i].getColumnName();
				columnNames[i] = name;
			}

			for (int i = 0; i < table.getRowCount(); i++) {
				buf.append("[");
				buf.append(i);
				buf.append("]");
				for (int j = 0; j < columnNames.length; j++) {
					String columnName = columnNames[j];
					Object o = table.getValue(i, columnName);
					buf.append(columnName);
					buf.append("=");
					buf.append(o.toString());
					buf.append(",");
				}
				buf.deleteCharAt(buf.length() - 1);
				buf.append("\r\n");
			}

		} catch (Exception e) {

		}

		return buf.toString();
	}

}
