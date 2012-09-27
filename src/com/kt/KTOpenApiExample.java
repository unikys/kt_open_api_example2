package com.kt;

import com.kt.api.KTOpenApiManager;

public class KTOpenApiExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KTOpenApiManager apiManager = new KTOpenApiManager("AuthKey", "SecretKey"); 
		new KTOpenApiGUI(apiManager);

	}

}
