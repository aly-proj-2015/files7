package aly.kafka.obu.msg;

import java.util.ArrayList;
import java.util.List;

public class TransformerPriceFlat implements ITransormer
{
/**
 * The payload has a structure : 
 * 	<payload> := <header>,<body>
 *  <header> := <storID>,<transformerID>,<loaderID>
 *  <body := string,string,...  
 */
	@Override
	public List<MetaField> transform(String payload)
	{
		List meta = new ArrayList();
		
		String[] elems = payload.split(",");
//		int storeID = Integer.parseInt(elems[0]);
//		int transformerID = Integer.parseInt(elems[1]);
//		int loaderID = Integer.parseInt(elems[2]);
//		String innerMsg = elems[3];

		for(int pos=)
		
		
		return meta;
	}

}
