package jndi.rmi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jndi.ConnectionPoolSTAXParser;
import jndi.LocalContext;
import jndi.LocalContextFactory;
import jndi.model.JndiParameters;
import jndi.rmi.XorClientSocketFactory;
import jndi.rmi.XorServerSocketFactory;

public class JndiServerImpl implements JndiServer {

	public JndiServerImpl() {
	}

	public static void main(String args[]) {

		// if (System.getSecurityManager() == null) {
		// System.setSecurityManager(new SecurityManager());
		// }

		byte pattern = (byte) 0xAC;
		try {
			/*
			 * Create remote object and export it to use custom socket
			 * factories.
			 */
			JndiServerImpl obj = new JndiServerImpl();
			RMIClientSocketFactory csf = new XorClientSocketFactory(pattern);
			RMIServerSocketFactory ssf = new XorServerSocketFactory(pattern);
			JndiServer stub = (JndiServer) UnicastRemoteObject.exportObject(
					obj, 0, csf, ssf);

			/*
			 * Create a registry and bind stub in registry.
			 */
			LocateRegistry.createRegistry(8086);
			Registry registry = LocateRegistry.getRegistry(8086);
			registry.rebind("JndiConnectionFactory", stub);
			System.out
					.println("JndiServer bound to registry: JndiConnectionFactory");

		} catch (Exception e) {
			System.out.println("JndiServerImpl exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private LocalContext ctx = null;

	private void setLocalContext() {
		try {
			List<JndiParameters> jndiList = ConnectionPoolSTAXParser
					.parseXML("connection-pools.xml");
			if (ctx == null)
				ctx = LocalContextFactory.createLocalContext();
			else
				ctx.close();
			for (int i = 0; i < jndiList.size(); i++) {
				// if (ctx == null)
				// ctx = LocalContextFactory.createLocalContext(jndiList
				// .get(i).getJndiDriverName());
				// else
				// ctx.close();
				ctx.addDataSource(jndiList.get(i).getJndiName(), jndiList
						.get(i).getJndiDBUrl(),
						jndiList.get(i).getJndiDBUser(), jndiList.get(i)
								.getJndiDBPass(), jndiList.get(i)
								.getJndiDriverName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataSource getDataSource(String jndiLookup)
			throws java.rmi.RemoteException, NamingException {
		this.setLocalContext();
		DataSource ds = (DataSource) new InitialContext().lookup(jndiLookup);
		return ds;
	}

	public DataSource getDataSource(String driverClassName, String jndiLookup)
			throws java.rmi.RemoteException, NamingException {
		this.setLocalContext();
		try {
			Class.forName(driverClassName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSource ds = (DataSource) new InitialContext().lookup(jndiLookup);
		return ds;
	}
}