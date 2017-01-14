package genericDeser.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import genericDeser.fileOperations.FileProcessor;

public class PopulateObjects {
	static FileProcessor fpobj;
	String input="";
	String firstClass = "genericDeser.util.First";	
	String secondClass = "fqn:genericDeser.util.Second";
	First firstobj;
	Second secondobj;
	
	
	HashMap<Integer,First> FirstMap;
	HashMap<Integer,Second> SecondMap;
	
	
	public PopulateObjects(FileProcessor fpobjIn)
	{
		fpobj=fpobjIn;
		
		FirstMap = new HashMap<Integer,First>();
		SecondMap = new HashMap<Integer,Second>();
	}
	
	
	public void findAndPlaceFirst(int HashCode, First fIn)
	{
		First fT = FirstMap.get(HashCode);
		if(fT!=null)
		{
			if(!fT.equals(fIn))
			{
				findAndPlaceFirst(HashCode+fT.hashCode(),fIn);
			}
		}
		else
		{
			FirstMap.put(HashCode,fIn);
		}
	}
	
	public void findAndPlaceSecond(int HashCode, Second sIn)
	{
		Second sT = SecondMap.get(HashCode);
		if(sT!=null)
		{
			if(!sT.equals(sIn))
			{
				findAndPlaceSecond(HashCode+sT.hashCode(),sIn);
			}
		}
		else
		{
			SecondMap.put(HashCode,sIn);
		}
	}
		
	public void deserObjects(String InputFile) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
		
		
		FileProcessor proc = new FileProcessor(InputFile);
		
		String stmt = proc.readLine();
		String st[];
		while(stmt!=null)
		{

			if(stmt.subSequence(0, 3).equals("fqn"))
			{
				if(stmt.endsWith("First"))
				{
					First F = null;
					 String clsName = "genericDeser.util.First";
					 Class cls = Class.forName(clsName); 
					 Class[] signature = new Class[1];  
					 Object obj = cls.newInstance();
					 F=(First)obj;
					stmt=proc.readLine();
					while(stmt!=null && !(stmt.subSequence(0, 3).equals("fqn")))
					{
						st=stmt.split(",");
						String type = (st[0].trim()).substring(5);
						String variable = (st[1].trim()).substring(4);
						String value = (st[2].trim()).substring(6);
						

						 
						 if(type.equals("int"))
						 signature[0] = Integer.TYPE;
						 else if(type.equals("float"))
						 signature[0] = Float.TYPE; 
						 else if(type.equals("short"))
						 signature[0] = Short.TYPE; 
						 else if(type.equals("String"))
						 signature[0] = String.class;
						 
						 
						 String methdName = "set" + variable; 
						 Method meth = cls.getMethod(methdName, signature); 
						  
						 Object[] params = new Object[1]; 
						 
						 if(type.equals("int"))
							 params[0] = new Integer(Integer.parseInt(value));
						 else if(type.equals("float"))
							 params[0] = new Float(Float.parseFloat(value));
						 else if(type.equals("short"))
							 params[0] = new Short(Short.parseShort(value)); 
						 else if(type.equals("String"))
							 params[0] = value;
						 
						  Object result = meth.invoke(obj, params); 
						  
						  stmt=proc.readLine();
					}
					findAndPlaceFirst(F.hashCode(),F);
				}
				else
				{
					Second S = null;
					stmt=proc.readLine();
					 String clsName = "genericDeser.util.Second";
					 Class cls = Class.forName(clsName); 
					 Class[] signature = new Class[1];  
					 Object obj = cls.newInstance();
					 S=(Second)obj;
					while(stmt!=null && !(stmt.subSequence(0, 3).equals("fqn")))
					{
						st=stmt.split(",");
						String type = (st[0].trim()).substring(5);
						String variable = (st[1].trim()).substring(4);
						String value = (st[2].trim()).substring(6);
						

						 
						 if(type.equals("int"))
						 signature[0] = Integer.TYPE;
						 else if(type.equals("boolean"))
						 signature[0] = Boolean.TYPE; 
						 else if(type.equals("double"))
						 signature[0] = Double.TYPE;
						 
						 
						 String methdName = "set" + variable; 
						 Method meth = cls.getMethod(methdName, signature); 
						  
						 Object[] params = new Object[1]; 
						 
						 if(type.equals("int"))
							 params[0] = new Integer(Integer.parseInt(value));
						 else if(type.equals("double"))
							 params[0] = new Double(Double.parseDouble(value));
						 else if(type.equals("boolean"))
							 params[0] = new Boolean(Boolean.parseBoolean(value)); 
						 
						  Object result = meth.invoke(obj, params); 
						  stmt=proc.readLine();
					}
					findAndPlaceSecond(S.hashCode(),S);
				}
			}
		}
		
		System.out.println(FirstMap.size());
		System.out.println(SecondMap.size());
	}
	
	public void reflection() 
	{
		String splitstr[];
		input=fpobj.readLine();
		while(input != null)
		{
			splitstr=input.split(":");
			input=splitstr[1];
			if(input !=null)
			{
				if(input.equals(firstClass) || input.equals(secondClass))
				{
					try {
						process(input);
					} catch (SecurityException | IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			input=fpobj.readLine();
		}
		
		
	}// reflection method ends
	
	
	public static void process(String clsName) 
	{
				
	    Class cls = null;
	    String line=fpobj.readLine();
		try {
			cls = Class.forName(clsName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    Class[] signature = new Class[6];  
	    while(line !=null)
	    {
	    String lineArray[];
	    lineArray=line.split("=");
	    line=lineArray[1];
	    lineArray=null;
	    lineArray=line.split(",");
	    line=lineArray[0];
	    System.out.println(line);
	    
	    
	    
	     
	    
	   
	    signature[0] = Integer.TYPE;			// generalize
	    String methodName = "set" + "IntValue"; 	        // generalize
	    Method meth = null;
		try {
			meth = cls.getMethod(methodName, signature);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    Object[] params = new Object[1]; 
	    params[0] = new Integer(17);			// generalize
	    try {
			Object result = meth.invoke(obj, params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	    
	    line=fpobj.readLine();
	    }//line null check ends

	}
	
	

}
