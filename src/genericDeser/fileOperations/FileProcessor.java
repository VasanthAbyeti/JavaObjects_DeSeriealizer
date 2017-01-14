package genericDeser.fileOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import genericDeser.util.Logger;

public class FileProcessor {
	String finput;


	BufferedReader br = null;
	/**
	 * 
	 * @param input
	 */
	public FileProcessor(String input)
	{
		this.finput=input;
		//this.foutput=output;
		File inputFile = new File(finput);
		try {
			br = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		if(Logger.DebugLevel.CONSTRUCTOR != null)
		{
			if(Logger.getDebugValue()==Logger.DebugLevel.CONSTRUCTOR)
			System.out.println("FileProcessor Constructor is called");
		}
		
	}
	
	
	//@return String
	public synchronized String readLine()
	{
		  //open input file
		
		String inputLine = null;
				
		try {
			
			
			inputLine=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
  
	
		if(inputLine!=null)
			return inputLine;
			
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputLine;
	}

}
