package com.kt;

import com.kt.api.KTOpenApiManager;

public class KTOpenApiExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KTOpenApiManager apiManager = new KTOpenApiManager("8dfad1945247e11e7f7d813647a3516b", "c65453cf50d82f0865ed747b2f3b76fe"); 
		new KTOpenApiGUI(apiManager);

	}

}
