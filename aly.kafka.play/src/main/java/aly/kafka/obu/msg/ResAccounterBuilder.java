package aly.kafka.obu.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * VERTICA_PRICES_DET - means Vertica, table = PRICES_DET  (details, each JSON elem goes to separate field, 
 * Fields names are JSON paths.
 * 
 *
 */
public class ResAccounterBuilder
{
	enum STORES {UNDEF, VERTICA_PRICES_DET, VERTICA_OFFER_6, VERTICA_OFFER_JSON};
	
	static public ResAccounter createInCode()
	{
		ResAccounter accounter = new ResAccounter();
		
		Map<String,StoreCred> storeMap = new HashMap<>();
		Map<Integer,HandlerRecord> transfomerMap = new HashMap<>();
		Map<Integer,HandlerRecord> LoaderMap = new HashMap<>();
		
		StoreCred vertica_Store = StoreCred.createStoreCred(
				STORES.VERTICA_PRICES_DET.ordinal(), "vertica conn. string", "ayakubo", "Ledocol95");
		storeMap.put("VERTICA_PRICES_DET", vertica_Store);
		
		return accounter;
	}
}
