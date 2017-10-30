

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import jndi.rmi.server.JndiServer;

public class JndiClient {

	public static void main(String args[]) {

		// if (System.getSecurityManager() == null) {
		// System.setSecurityManager(new SecurityManager());
		// }

		try {
			Registry registry = LocateRegistry.getRegistry(8086);
			JndiServer obj = (JndiServer) registry
					.lookup("JndiConnectionFactory");

			DataSource ds = obj.getDataSource("java:comp:/ds1");
			Connection conn = ds.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("SELECT count(*) as count FROM EMP99");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Total Count: " + rs.getString("count"));
			}

		} catch (Exception e) {
			System.out.println("JndiClient exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}