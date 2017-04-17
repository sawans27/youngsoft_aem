package org.youngsoft.bench.core.Impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youngsoft.bench.core.ExcelToJsonConverter;

@Component(name = "org.youngsoft.bench.core.ExcelToJsonConverter", immediate = true)
@Service(ExcelToJsonConverterImpl.class)
public class ExcelToJsonConverterImpl implements ExcelToJsonConverter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Reference
	private ResourceResolverFactory resourceFactory;
	
	//String queryStr = "SELECT parent.* FROM [cq:Page] AS parent INNER JOIN [nt:base] AS child ON ISCHILDNODE(child,parent) WHERE ISDESCENDANTNODE(parent, '/content') AND child.[cq:template] = '/libs/cq/personalization/templates/campaign'";

	String queryStr = "SELECT * FROM [dam:Asset] AS a WHERE ISDESCENDANTNODE([/content/en]) AND a.[jcr:content/metadata/type] = 'image'";
	
	@Override
	public String getJson() {
		try {
			FileInputStream inp = new FileInputStream("file");
			Workbook workbook = WorkbookFactory.create(inp);
			// Get the first Sheet.
			Sheet sheet = workbook.getSheetAt(0);

			// Start constructing JSON.
			JSONObject json = new JSONObject();

			// Iterate through the rows.
			JSONArray rows = new JSONArray();
			for (Iterator<Row> rowsIT = sheet.rowIterator(); rowsIT.hasNext();) {
				Row row = rowsIT.next();
				JSONObject jRow = new JSONObject();

				// Iterate through the cells.
				JSONArray cells = new JSONArray();
				for (Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext();) {
					Cell cell = cellsIT.next();
					cells.put(cell.getStringCellValue());
				}
				jRow.put("cell", cells);
				rows.put(jRow);
			}

			// Create the JSON.
			json.put("rows", rows);
			// Get the JSON text.
			return json.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get the JSON text.
		return "{}";
	}

	@Override
	public NodeIterator getTaggedPage(String tagPath) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		NodeIterator resultMap = null;
		// Mention the subServiceName you had used in the User Mapping
		paramMap.put(ResourceResolverFactory.SUBSERVICE, "ExcelToJsonConverter");
		ResourceResolver resourceResolver = null;
		logger.info("About to run Query");
		try {
			resourceResolver = resourceFactory.getServiceResourceResolver(paramMap);
			Session session = resourceResolver.adaptTo(Session.class);
			QueryManager queryManager = session.getWorkspace().getQueryManager();
			Query query = queryManager.createQuery(queryStr, Query.JCR_SQL2);
			logger.info("run Query"+query.getStatement());
			QueryResult result = query.execute();
			resultMap = result.getNodes();
			logger.info("Query Executed"+resultMap.getSize());
			return resultMap;
		} catch (Exception e) {

		}
		return null;
	}
}
