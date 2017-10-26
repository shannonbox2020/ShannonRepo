package archiveReports;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This class contains the code to archive old reports under Automation reports folder
 * @author Shiggins
 *
 */
public class ArchiveReports {
	
	private static Logger log = Logger.getLogger(ArchiveReports.class);
	
	String fileSeperator = System.getProperty("file.separator");	
	String sReportsLocation = System.getProperty("user.dir") + fileSeperator +"Automation Reports";
	String sLatestReportsLocation = System.getProperty("user.dir") + fileSeperator + "Automation Reports" + fileSeperator + "Latest Results";
	
	
	@Test
	public void archiveOldReports(){
		
		log.info("************************ Archiving Old Reports ********************");
		System.out.println("************************ Archiving Old Reports ********************");
		
		try{
			
			File latestResults = new File(sLatestReportsLocation);
	      	if(latestResults.exists())
	      	{
	      		Path p = Paths.get(sLatestReportsLocation);
			    BasicFileAttributes view;
				
				view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
			    String fCreationTime = view.creationTime().toString();
			    String istTime = convertTime(fCreationTime.split("\\.")[0].replace("T","-"));
	      		String oldFolder = sReportsLocation + fileSeperator + "Results_on_" + istTime.replace(":", "_at_");
	      		File oldResults = new File(oldFolder);
	      		if(!oldResults.exists())
	      		{
	      			//latestResults.renameTo(oldResults);
	      			FileUtils.moveDirectory(latestResults, oldResults);
	      		}
	      		else{
	      			String fModifiedTime = view.lastModifiedTime().toString();
				    String istTime2 = convertTime(fModifiedTime.split("\\.")[0].replace("T","-"));
		      		String oldFolder2 = sReportsLocation + fileSeperator + "Results_on_" + istTime2.replace(":", "_at_");
		      		File oldResults2 = new File(oldFolder2);
		      		//latestResults.renameTo(oldResults2);
		      		FileUtils.moveDirectory(latestResults, oldResults2);
	      		}
	      		//System.out.println("latest folder:"+sLatestReportsLocation);
	      		//System.out.println("old folder:"+oldFolder);
	      		
	      		/*//System.out.println("latest folder:"+sLatestReportsLocation);
	      		//System.out.println("old folder:"+oldFolder);
	      		if(latestResults.renameTo(oldResults)){
	      			System.out.println("renamed");
	      		}
	      		else{
	      			System.out.println("not renamed");
	      		}
	      		//Files.move(latestResults.toPath(), oldResults.toPath());*/
	      		//FileUtils.moveFile(latestResults, oldResults);
	      		//FileUtils.moveDirectory(latestResults, oldResults);
	      	}
			
		}
		catch(Exception e){
			Assert.fail("Exception occured while archiving old reports. Exception message: "+e.getMessage());
		}
	}
	
	 /**
	 * Purpose - To convert given time in "yyyy-MM-dd-HH:mm:ss" to IST time
	 * @returns date in String format 
	 * @throws Exception
	 */
	public static String convertTime(String origTime) 
	{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        TimeZone obj = TimeZone.getTimeZone("GMT");
        formatter.setTimeZone(obj);
        try 
        {
			Date date = formatter.parse(origTime);
			formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
			return formatter.format(date);
		} catch (ParseException e) {
			log.error("Cannot parse given date .." + origTime);
			log.info("returning current date and time .." + origTime);
		}
		return "";
    } 

}
