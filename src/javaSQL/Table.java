package javaSQL;
import java.sql.*;

public class Table {
	private final String TABLE_NAME;
	private final String PK_COLUMN;
	private String[] columnNames;
	private String[] columnDataTypes;
	private Accessor access;
	
	public Table(Accessor a, String tableName) {
		TABLE_NAME = tableName;
		PK_COLUMN = ""; // externally get the PK column, if there's nothing for that, pass it in as param
		this.columnNames = new String[0]; // externally get the column names, if there's nothing for that, pass it in as param
		this.columnDataTypes = new String[0]; // externally get the column data types, if there's nothing for that, pass it in as param
		this.access = a;
	}
	public boolean insertRecord(String[] cols, String[] vals) {
		//cols will be like... ["id", "name"]
		//vals will be like... ["3", "Michael"]
		String col = ""; // format to a string
		String val = ""; // check for datatypes and make sure they are in correct data type, then format
		String sql = String.format("INSERT INTO %1$s (%2$s) VALUES (%3$s)", TABLE_NAME, col, val);
		return access.execute(sql);
	}
	public ResultSet selectRecords(String[] cols, String identifier) {
		//cols will be like... ["id", "name"]
		//identifier will be like "(id = 3) && ((name = Michael) || (date = 9999-12-04))"
		String col = ""; // format to a string
		String selection = ""; // check columnDataTypes and make sure they are in correct data types
		String sql = String.format("SELECT %1$s FROM %2$s WHERE %3$s", col, TABLE_NAME, selection);
		if (access.execute(sql)) {
			return new ResultSet();
		}
		//return empty ResultSet
	}
	public boolean modifyRecords(String[] sets, String identifier) {
		//sets will be like... ["id = 3", "name = Michael"]
		//identifier will be like... "(id = 3) && ((name = Michael) || (date = 9999-12-04))"
		String set = ""; // create a string with the appropriate setters
		String conditions = ""; // check identifier and make it the appropriate type by putting it in correct format
								// don't forget to use the IN keyword (it will save a lot of time)
		String sql = String.format("UPDATE %1$s SET %2$s WHERE %3$s", TABLE_NAME, set, conditions);
		return access.execute(sql);
	}
	public boolean deleteRecords(String identifier) {
		//identifier will be like... "(id = 3) && ((name = Michael) || (date = 9999-12-04))"
		String conditions = ""; // check identifier and make it the appropriate type by putting it in correct format
		String sql = String.format("DELETE FROM %1$s WHERE %2$s", TABLE_NAME, conditions);
		return access.execute(sql);
	}
}
