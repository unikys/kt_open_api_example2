package com.kt.api;

import java.util.HashMap;

import com.kt.openplatform.sdk.KTOpenApiHandler;
import com.kt.openplatform.sdk.KTOpenApiHandler2;
import com.kt.openplatform.sdk.util.ConfigProvider;

public class KTOpenApiManager {

	private String strAuthKey = "";
	private String strSecretKey = "";
	private String strUserName = "";
	private String strPassword = "";
	private boolean isLogin = false;

	KTOpenApiHandler2 apiHandler;
	
	public KTOpenApiManager(String authKey , String secretKey , String userName , String password)
	{
		this.strAuthKey = authKey;
		this.strSecretKey = secretKey;
		this.strUserName = userName;
		this.strPassword = password;
	}
	
	public boolean connect()
	{
		apiHandler = new KTOpenApiHandler2(this.strAuthKey, this.strSecretKey, false);
		System.out.println(ConfigProvider.get("sdk.version"));
		apiHandler.initialize(ConfigProvider.get("sdk.version"));

		this.login(this.strUserName, this.strPassword);
		
		return apiHandler != null;
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
