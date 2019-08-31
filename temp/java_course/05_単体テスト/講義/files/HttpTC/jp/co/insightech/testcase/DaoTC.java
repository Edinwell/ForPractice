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
 * DBUnitが使用する接続設定や、テーブルの初期設定などを実装する抽象クラス.
 * 
 * Daoのテストケースを作成する場合はこのクラスを継承します.
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
	 * セットアップ
	 */
	@Before
	public void setUp() throws Exception {
		dbTester.setDataSet(new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(getInitXml())));
		dbTester.onSetup();
	}

	/**
	 * ティアダウン
	 */
	@After
	public void tearDown() throws Exception {
		dbTester.onTearDown();
	}

	/**
	 * CLEAN_INSERT処理
	 * 
	 * @param xmlName
	 *            対象XML名
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
	 * 初期化XML名取得 Overrideし、初期化対象のXML名を記述すること
	 * 尚、getClass().getResourceAsStream("name")にて取得するので パスは必要無し ex) return
	 * "init_table.xml";
	 * 
	 * @return 初期状態を記述したXML
	 */
	protected String getInitXml() {
		return null;
	}

	/**
	 * テスト実行（データセット単位）
	 * 
	 * @param xmlName
	 *            期待するDB状態の記述されたXML
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
			// XMLに無いカラムは無視
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
