package jndi.model;

public class JndiParameters {
	private String jndiName, jndiDriverName, jndiDBUrl, jndiDBUser, jndiDBPass;

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public String getJndiDriverName() {
		return jndiDriverName;
	}

	public void setJndiDriverName(String jndiDriverName) {
		this.jndiDriverName = jndiDriverName;
	}

	public String getJndiDBUrl() {
		return jndiDBUrl;
	}

	public void setJndiDBUrl(String jndiDBUrl) {
		this.jndiDBUrl = jndiDBUrl;
	}

	public String getJndiDBUser() {
		return jndiDBUser;
	}

	public void setJndiDBUser(String jndiDBUser) {
		this.jndiDBUser = jndiDBUser;
	}

	public String getJndiDBPass() {
		return jndiDBPass;
	}

	public void setJndiDBPass(String jndiDBPass) {
		this.jndiDBPass = jndiDBPass;
	}

	@Override
	public String toString() {
		return "JNDI Name=" + this.jndiName + "\nDriver Name=" + this.jndiDriverName + "\nDB Url=" + this.jndiDBUrl
				+ "\nDB User=" + this.jndiDBUser + "\nDB Pass=" + this.jndiDBPass;
	}
}
