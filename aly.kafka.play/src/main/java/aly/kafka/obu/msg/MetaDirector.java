package aly.kafka.obu.msg;

public class MetaDirector
{
	public String getStoreURL(MsgMetadata meta)
	{
		return meta.storeID;
	}
	
	public String getStoreUser(MsgMetadata meta)
	{
		return null;
	}
	
	public String getStorePass(MsgMetadata meta)
	{
		return null;
	}
	
	public Class getTransformer(MsgMetadata meta)
	{
		return null;
	}
	
	public Class getLoader(MsgMetadata meta)
	{
		return null;
	}
}
