package com.fullstack.batch3.common;

import java.io.File;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class DataUtils {

	static String environment = System.getProperty("environment") == null ? "staging"
			: System.getProperty("environment");

	static String testDataFile = environment.equals("staging") ? "TestData.xlsx" : "TestDataProd.xlsx";

	public static String getDataFromExcel(String sheetname, String feildName) {
		String result = "error retriving data...";
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(
					System.getProperty("user.dir") + File.separator + "resources" + File.separator + testDataFile);
			String strQuery = "Select * from " + sheetname + " " + "where ID='" + feildName + "'";
			Recordset recordset = connection.executeQuery(strQuery);

			while (recordset.next()) {
				result = recordset.getField("Value");
			}

			recordset.close();
			connection.close();
		} catch (FilloException e) {

			e.printStackTrace();
		}
		return result;
	}

}
