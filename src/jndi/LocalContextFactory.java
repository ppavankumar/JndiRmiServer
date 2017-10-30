package jndi;

import javax.naming.spi.NamingManager;

public class LocalContextFactory {
	/**
	 * do not instantiate this class directly. Use the factory method.
	 */
	private LocalContextFactory() {
	}

	public static LocalContext createLocalContext() {
		try {
			LocalContext ctx = new LocalContext();
			NamingManager.setInitialContextFactoryBuilder(ctx);
			return ctx;
		} catch (Exception e) {
			System.out.println("Error Initializing Context: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static LocalContext createLocalContext(String databaseDriver) throws Exception {

		try {
			LocalContext ctx = new LocalContext();
			System.out.println(databaseDriver);
			Class.forName(databaseDriver);
			NamingManager.setInitialContextFactoryBuilder(ctx);
			return ctx;
		} catch (Exception e) {
			throw new Exception("Error Initializing Context: " + e.getMessage(), e);
		}
	}
}