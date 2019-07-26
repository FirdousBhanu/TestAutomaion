package com.fre.db.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadingInputData {

	public static FileReader configreader;
	public static Properties configprop;

	public ReadingInputData(){
		try {
			configreader = new FileReader((System.getProperty("user.dir")+File.separator+"config.properties"));
			configprop = new Properties();
			try {
				configprop.load(configreader);
				} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String readPropertyValue(String PropertyName) {
		     	return configprop.getProperty(PropertyName);
				
}	
}