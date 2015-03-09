package aly.kafka.obu.msg;

public class MetaField
{
	public static MetaField create(String fldName, String valueAsStr, IHandler.fldTypesEnum eTypeHint)
	{
		return new MetaField(fldName, valueAsStr, eTypeHint);
	}
	
	private MetaField(String fldName, String valueAsStr, IHandler.fldTypesEnum eTypeHint)
	{
		this.fldName = fldName;
		this.valueAsStr = valueAsStr;
		this.eTypeHint = eTypeHint;
	}
	
	final String fldName;
	final String valueAsStr;
	final IHandler.fldTypesEnum eTypeHint;
	
	public String getFldName()
	{
		return fldName;
	}

	public String getValueAsStr()
	{
		return valueAsStr;
	}

	public IHandler.fldTypesEnum geteTypeHint()
	{
		return eTypeHint;
	}
}
