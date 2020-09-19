/**
 * 
 */
package com.fullstack.batch3;

import com.fullstack.batch3.common.DataUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * @author chandan
 *
 */
public abstract class BaseAPITest {

	protected RequestSpecification commonSpec = new RequestSpecBuilder()
			.setBaseUri(DataUtils.getDataFromExcel("APIData", "ServerUrl")).setContentType("application/json").build()
			.log().all();
	
	protected <T> T getDatafromJsonPath(Response response, String jsonPath)

	{
		Jsonpath jpath=reponse.jsonPath ();
		
		return jpath.get(jsonPath);
	}
	
}
