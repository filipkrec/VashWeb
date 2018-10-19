package apps;


public class QueueValues
{
    private String stringValue;
    private int intValue;
    private long longValue;
    public QueueValues(){
        stringValue = null;
        intValue = 0;
        longValue = 0;
    }
    public QueueValues(String stringValue, int intValue, long longValue)
    {
        this.stringValue = stringValue;
        this.intValue = intValue;
        this.longValue = longValue;
    }
    public void Values(String stringValue, int intValue, long longValue){
       this.stringValue = stringValue;
       this.intValue = intValue;
       this.longValue = longValue;
    }

    public String getString()
    {
        return stringValue;
    }
    public int getInt()
    {
        return intValue;
    }
    public long getLong() {return longValue;}
}