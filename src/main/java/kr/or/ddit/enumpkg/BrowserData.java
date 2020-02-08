package kr.or.ddit.enumpkg;

public class BrowserData {
	private String name;
	private double version;
	
	public BrowserData(String name) {
		super();
		this.name = name;
	}
	public BrowserData(String name, double version) {
		super();
		this.name = name;
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public double getVersion() {
		return version;
	}
}
