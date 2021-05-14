package domain;

public class SignInfo {
	private String routeSaveKey;
	private String routePrivateKey;
	private String routeFileForSign;
	
	private static SignInfo signInfo = null;
    
	private SignInfo(){}

	public static SignInfo getInstance() {
		if(signInfo == null) {
			signInfo = new SignInfo();
		}
		return signInfo;
	}
	
	public String getRoutePrivateKey() {
		return routePrivateKey;
	}
	public void setRoutePrivateKey(String routePrivateKey) {
		this.routePrivateKey = routePrivateKey;
	}
	public String getRouteFileForSign() {
		return routeFileForSign;
	}
	public void setRouteFileForSign(String routeFileForSign) {
		this.routeFileForSign = routeFileForSign;
	}

	public String getRouteSaveKey() {
		return routeSaveKey;
	}

	public void setRouteSaveKey(String routeSaveKey) {
		this.routeSaveKey = routeSaveKey;
	}
	
}
