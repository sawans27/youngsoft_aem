package org.youngsoft.bench.core;

import java.util.Iterator;

public interface ExcelToJsonConverter {

	public String getJson();
	public Iterator<?> getTaggedPage(String tagPath);
		
}
