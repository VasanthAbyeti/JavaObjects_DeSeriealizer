package genericDeser.util;

import genericDeser.fileOperations.FileProcessor;

public class First implements InputInterface {
	
	private int IntValue;
	private short ShortValue;
	private float FloatValue;
	private String StringValue;
	
	
	
	@Override
	public boolean equals(Object o)
	{
		First fIn = (First)o;
		
		if(StringValue!=null)
		{
		if(fIn.getStringValue().equals(this.StringValue) && fIn.getIntValue()==this.IntValue && fIn.getFloatValue()==this.getFloatValue() && fIn.getShortValue()==this.ShortValue)
		 return true;
		}
		else
		{
			if(fIn.getStringValue()==(this.StringValue) && fIn.getIntValue()==this.IntValue && fIn.getFloatValue()==this.getFloatValue() && fIn.getShortValue()==this.ShortValue)
				 return true;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
        int hash = 1;
        hash = hash * 17 + IntValue;
        hash = hash * 31 + StringValue.hashCode();
        hash = (int) (hash * 97 + FloatValue);
        hash = hash * 73 + ShortValue;
        return hash;
	}
	
	public void setIntValue(int iIn) {
		// TODO Auto-generated method stub
		IntValue=iIn;
	}

	public void setShortValue(short shIn) {
		// TODO Auto-generated method stub
		ShortValue=shIn;		
	}

	public void setFloatValue(float fIn) {
		// TODO Auto-generated method stub
		FloatValue=fIn;		
	}

	public void setStringValue(String sIn) {
		// TODO Auto-generated method stub
		StringValue=sIn;
	}

	public int getIntValue() {
		// TODO Auto-generated method stub
		return IntValue;
	}

	public short getShortValue() {
		// TODO Auto-generated method stub
		return ShortValue;
	}

	public float getFloatValue() {
		// TODO Auto-generated method stub
		return FloatValue;
	}

	public String getStringValue() {
		// TODO Auto-generated method stub
		return StringValue;
	}

}
