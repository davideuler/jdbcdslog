package org.jdbcdslog;

import java.sql.Connection;
import java.sql.ResultSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConnectionPoolXADataSourceProxyTest extends TestCase {
	public ConnectionPoolXADataSourceProxyTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(ConnectionPoolXADataSourceProxyTest.class);
	}

	public void testApp() throws Exception {
		System.setProperty("org.jdbcdslog.ConnectionPoolXADataSourceProxy.targetDS", "org.hsqldb.jdbc.jdbcDataSource");
		ConnectionPoolXADataSourceProxy ds = new ConnectionPoolXADataSourceProxy();
		ds.setDatabase("jdbc:hsqldb:mem:mymemdb");
		ds.setUser("sa");
		Connection con = ds.getConnection();
		con.createStatement().execute("create table test (a integer)");
		con.createStatement().execute("insert into test values(1)");
		ResultSet rs = con.createStatement().executeQuery("select * from test");
		rs.close();
		con.close();
	}
}