package genericDeser.util;

public class Second implements InputInterface{
	
	private int IntValue;
	private double DoubleValue;
	private boolean BooleanValue;
	
	
	@Override
	public boolean equals(Object o)
	{
		Second sIn = (Second)o;
		
		if(sIn.getDoubleValue()==this.DoubleValue && sIn.getBooleanValue()==this.BooleanValue && sIn.getIntValue()==this.IntValue)
		 return true;
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
        int hash = 1;
        hash = hash * 17 + IntValue;
        hash = (int) (hash * 97 + DoubleValue);
        hash = hash * 73 + (BooleanValue?1:0);
        return hash;
	}
	
	
	public int getIntValue() {
		return IntValue;
	}
	public void setIntValue(int intValue) {
		IntValue = intValue;
	}
	public double getDoubleValue() {
		return DoubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		DoubleValue = doubleValue;
	}
	public boolean getBooleanValue() {
		return BooleanValue;
	}
	public void setBooleanValue(boolean booleanValue) {
		BooleanValue = booleanValue;
	}
}
