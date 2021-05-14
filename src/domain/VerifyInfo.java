package domain;

/**
 * @author yoo77
 *
 */
public class VerifyInfo {
	private String routeOriginFile;
	private String routeFileForVerify;
	private String routePublicKey;
	
	private static VerifyInfo verifyInfo = null;
    
	private VerifyInfo(){}

	public static VerifyInfo getInstance() {
		if(verifyInfo == null) {
			verifyInfo = new VerifyInfo();
		}
		return verifyInfo;
	}
	
	public String getRouteOriginFile() {
		return routeOriginFile;
	}
	public void setRouteOriginFile(String routeOriginFile) {
		this.routeOriginFile = routeOriginFile;
	}
	public String getRouteFileForVerify() {
		return routeFileForVerify;
	}
	public void setRouteFileForVerify(String routeFileForVerify) {
		this.routeFileForVerify = routeFileForVerify;
	}
	public String getRoutePublicKey() {
		return routePublicKey;
	}
	public void setRoutePublicKey(String routePublicKey) {
		this.routePublicKey = routePublicKey;
	}
}
