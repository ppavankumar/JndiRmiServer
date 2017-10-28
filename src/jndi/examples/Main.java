package jndi.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jndi.ConnectionPoolSTAXParser;
import jndi.LocalContext;
import jndi.LocalContextFactory;
import jndi.examples.model.MySQLEmp99;
import jndi.model.JndiParameters;

public class Main {
	public static void main(String[] args) throws Exception {
		// LocalContext ctx =
		// LocalContextFactory.createLocalContext("org.hsqldb.jdbcDriver");
		// ctx.addDataSource("jdbc/ds1",
		// "jdbc:hsqldb:hsql://localhost:9001/zdb", "pavan", "123456");

		List<JndiParameters> jndiList = ConnectionPoolSTAXParser.parseXML("connection-pools.xml");
		LocalContext ctx = LocalContextFactory.createLocalContext();
		for (int i = 0; i < jndiList.size(); i++) {
			ctx.addDataSource(jndiList.get(i).getJndiName(), jndiList.get(i).getJndiDBUrl(),
					jndiList.get(i).getJndiDBUser(), jndiList.get(i).getJndiDBPass());
		}

		DataSource ds = (DataSource) new InitialContext().lookup("java:comp:/ds1");
		Connection conn = ds.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM EMP99");
		ResultSet rs = pstmt.executeQuery();
		List<MySQLEmp99> rowData = new ArrayList<MySQLEmp99>();
		while (rs.next()) {
			MySQLEmp99 emp99 = new MySQLEmp99();

			emp99.setId(rs.getString("id"));
			emp99.setName(rs.getString("name"));
			emp99.setSalary(rs.getString("salary"));
			emp99.setDesignation(rs.getString("designation"));

			rowData.add(emp99);
		}

		for (MySQLEmp99 mySQLEmp99 : rowData) {
			System.out.println(mySQLEmp99);
		}
	}
}
