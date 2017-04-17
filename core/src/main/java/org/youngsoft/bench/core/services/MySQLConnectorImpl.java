package org.youngsoft.bench.core.services;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youngsoft.bench.core.MySQLConnector;

import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

@Component(metatype=false)
@Service(value=MySQLConnector.class)
public class MySQLConnectorImpl implements MySQLConnector{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Reference
	DataSourcePool dspService;
	
	public void connectMySQL(){
		try {
			DataSource ds = (DataSource) dspService.getDataSource("aemsightly");
			if(ds != null) {
				final Connection connection = ds.getConnection();
		          final Statement statement = connection.createStatement();
		          final ResultSet resultSet = statement.executeQuery("SELECT * from test"); 
		          int r=0;
		          while(resultSet.next()){
		             r=r+1;
		             logger.info("Result Counter : "+r);
		          } 
		          resultSet.close();
			}
		} catch (DataSourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
