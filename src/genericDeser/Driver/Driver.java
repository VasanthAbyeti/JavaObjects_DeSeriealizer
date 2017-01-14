package genericDeser.Driver;

import java.lang.reflect.InvocationTargetException;

import genericDeser.fileOperations.FileProcessor;
import genericDeser.util.Logger;
import genericDeser.util.PopulateObjects;

public class Driver {
	private static int DEBUG_VALUE;

	
	
	public static void main(String args[])
	{
		PopulateObjects p = new PopulateObjects(new FileProcessor("input.txt"));
		try {
			p.deserObjects("input.txt");
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main1(String[] args) {
		
		// TODO Auto-generated method stub
		if(args.length != 3)
		{
			System.out.println("Please pass 3 arguments");
			System.exit(1);
		}

		//check for valid number of iterations
		if(!args[1].matches("-?\\d+(\\.\\d+)?"))
		{
			System.out.println("Please pass number of iterations as numeric");
			System.exit(1);
		}
		
		if(!args[2].matches("-?\\d+(\\.\\d+)?"))
		{
			System.out.println("Please pass Debug value argument as numeric");
			System.exit(1);
		}
		
		int NUM_ITERATIONS = Integer.parseInt(args[1]);
		
		//check for valid debug value
				DEBUG_VALUE = Integer.parseInt(args[2]);
				if((DEBUG_VALUE >4) || (DEBUG_VALUE<0 ))
				{
					System.out.println("Please pass Debug value between 0 and 4");
					System.exit(1);
				}
				
		Logger.setDebugValue(DEBUG_VALUE);	
		long startTime = System.currentTimeMillis();
		FileProcessor fpobj = new FileProcessor(args[0]);
		PopulateObjects ppobj = new PopulateObjects(fpobj);
		try {
			ppobj.reflection();
		} catch (SecurityException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long finishTime = System.currentTimeMillis();
		long runTime=(finishTime-startTime)/NUM_ITERATIONS;
	 	System.out.println("Total Time Value: "+runTime);

	}

}
