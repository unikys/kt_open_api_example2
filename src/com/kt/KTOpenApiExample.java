package com.kt;

import com.kt.api.KTOpenApiManager;

public class KTOpenApiExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		KTOpenApiManager apiManager = new KTOpenApiManager("6a791ca4c001eecc00b51a55942b9f31", "245888223ba778d95ba4db65ac7d848c" , "hwi73338401" , "8401"); 
		
		new KTOpenApiGUI(apiManager);

	}

}
