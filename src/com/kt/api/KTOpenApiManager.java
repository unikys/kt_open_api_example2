package com.kt.api;

import java.util.HashMap;

import com.kt.openplatform.sdk.KTOpenApiHandler;
import com.kt.openplatform.sdk.KTOpenApiHandler2;

public class KTOpenApiManager {

	private String strAuthKey = "";
	private String strSecretKey = "";
	private boolean isLogin = false;

	KTOpenApiHandler2 apiHandler;
	
	public KTOpenApiManager(String authKey , String secretKey)
	{
		this.strAuthKey = authKey;
		this.strSecretKey = secretKey;
	}
	
	public boolean connect()
	{
		apiHandler = new KTOpenApiHandler2(this.strAuthKey, this.strSecretKey, false);
		return apiHandler.hasAccessToken();
	}
	
	public boolean login(String userName , String password)
	{
		HashMap<?, ?> result = null;
		if ( !this.apiHandler.hasAccessToken() ) {
			if (!userName.isEmpty() && !password.isEmpty() ) {
				result = this.apiHandler.getAccessTokenForXauth(userName, password);
				if (result.containsKey(KTOpenApiHandler.DEF_ACCESS_TOKEN)) {
					isLogin = true;
				}
			}
		}
		return isLogin;
	}
	
	public HashMap<?,?> apiCall(String api_id) {
		return this.apiCall(api_id , new HashMap<String , String>());
	}

	public HashMap<?,?> apiCall(String api_id, HashMap<String, ?> params) {
		
		if(this.apiHandler == null && this.connect() == false) {
			return null;
		} 
		
		HashMap<?,?> r = apiHandler.call(api_id, params, null);

		return r;
	}
}
