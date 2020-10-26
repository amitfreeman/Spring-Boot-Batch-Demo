package com.javainuse.step;


import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope("step")
public class Writer implements ItemWriter<String> {
	/* File writer */
	/*private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");  
	private LocalDateTime now = LocalDateTime.now();*/ 
	//System.out.println(dtf.format(now));
	
	/*private String fileName="SampleFile_"+dtf.format(now).toString()+".txt";
	private File myFile = new File(fileName);*/
	//private  String fileName;
	
	@Value("#{jobParameters['DateTime']}")
	public void setFileName(final long DateTime){
		System.out.println("#AMIT In setFileName DateTime="+DateTime);
		Date currentDate = new Date(DateTime);
		LocalDateTime now;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"); 
		//this.fileName="SampleFile_"+dtf.format(currentDate)+".txt";
	}
	

	@Override
	public void write(List<? extends String> messages) throws Exception {
			
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");  
		 LocalDateTime now = LocalDateTime.now();
		//System.out.println(dtf.format(now));
		
		String fileName="SampleFile_"+dtf.format(now).toString()+".txt";
		File myFile = new File(fileName);
		
		for (String msg : messages) {
			System.out.println("Writing the data " + msg);
			
			System.out.println("Opening file"+fileName+"in append mode");
			
			FileWriter myWriter = new FileWriter(myFile, true);
			myWriter.write(msg);
			myWriter.write("\n");
			myWriter.close();

		}
	}

}