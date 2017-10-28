package jndi.rmi.server;

import javax.naming.NamingException;
import javax.sql.DataSource;

public interface JndiServer extends java.rmi.Remote {
	DataSource getDataSource(String jndiLookup) throws java.rmi.RemoteException, NamingException;
}