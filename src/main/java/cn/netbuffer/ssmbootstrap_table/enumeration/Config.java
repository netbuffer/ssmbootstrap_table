package cn.netbuffer.ssmbootstrap_table.enumeration;

public enum Config {

	KEY("test");
	private final String value;

	Config(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}