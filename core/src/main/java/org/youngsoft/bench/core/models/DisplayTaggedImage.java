package org.youngsoft.bench.core.models;

import java.util.Iterator;

import javax.jcr.NodeIterator;

import org.apache.felix.scr.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youngsoft.bench.core.ExcelToJsonConverter;
import org.youngsoft.bench.core.Impl.ExcelToJsonConverterImpl;

import com.adobe.cq.sightly.WCMUse;

public class DisplayTaggedImage extends WCMUse{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Reference
	private ExcelToJsonConverter jsonConverter;
	
	private String displayTaggedImage;
    
	public Iterator<?> getTaggedPage() {
		NodeIterator itr = (NodeIterator) jsonConverter.getTaggedPage("");
		return itr;
	}

	public String getDisplayTaggedImage() {
		return displayTaggedImage;
	}

	@Override
	public void activate() throws Exception {
		
		displayTaggedImage = (String) getProperties().get("tag");
		logger.info("ExcelToJsonConverter is NULL");
		if(jsonConverter == null){
			
			jsonConverter = getSlingScriptHelper().getService(ExcelToJsonConverterImpl.class);
			logger.info("ExcelToJsonConverter is "+jsonConverter.toString());
		}
	}

}
