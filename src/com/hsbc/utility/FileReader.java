package com.hsbc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileReader 
{
	public static String read(String pathToFile, String key) 
	{
		Properties testProps = FileReader.readFile(pathToFile);
		String s = testProps.getProperty(key);
		return s;
	}
	

	
	public static Properties readFile(String scenarioPath) 
	{
		File src = new File(scenarioPath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(src);
		} catch (FileNotFoundException fileError) {
			fileError.printStackTrace();
		}
		Properties pro = new Properties();
		try {
			pro.load(fis);
		} catch (IOException propError) {
			propError.printStackTrace();
		}
		return pro;
	}
}
