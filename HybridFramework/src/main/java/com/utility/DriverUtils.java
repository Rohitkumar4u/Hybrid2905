package com.utility;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.BaseClass;

public class DriverUtils extends BaseClass{

	public static String getScreenshot(String name)  {

		TakesScreenshot ts=(TakesScreenshot)driver;  //TypeCast of TakesScreenshot as it is an Interface,we can't make its object
		File src=ts.getScreenshotAs(OutputType.FILE); //it returns a FILE which will get stored in src

		String path=System.getProperty("user.dir")+"/screenshot/"+name+".jpg";   //getting folder path
		File dest=new File(path);              //to be saved at this address

		try {
			FileUtils.copyFile(src, dest);        //it will copy n paste file from src->dest 
		}catch(Exception e) {
			e.printStackTrace();
		}

		return path;

	}
}
