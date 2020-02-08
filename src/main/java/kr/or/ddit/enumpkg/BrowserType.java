package kr.or.ddit.enumpkg;

public enum BrowserType {
	FIREFOX(new BrowserData("파이어폭스")), 
	CHROME(new BrowserData("크롬", 3.2)), 
	TRIDENT(new BrowserData("익스플로러")), 
	OTHERS(new BrowserData("기타"));
	
	private BrowserType(BrowserData data) {
		this.data = data;
	}
	private BrowserData data;
	public BrowserData getData() {
		return data;
	}
	
	public static BrowserType findBrowser(String userAgent) {
		BrowserType result = OTHERS;
		BrowserType[] types = values();
		for(BrowserType type : types) {
			if(userAgent.toUpperCase().contains(type.name())) {
				result = type;
				break;
			}
		}
		return result;
	}
}
















