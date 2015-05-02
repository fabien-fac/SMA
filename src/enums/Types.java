package enums;

public enum Types {
	AGENT("agent"),
	BOITE("boite"),
	NID("nid");
	
	private String type;
	
	private Types(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
