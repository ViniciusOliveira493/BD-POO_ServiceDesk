package dao;

import java.sql.Date;

public abstract class DAO {
	protected Date getNow() {
		Date date = new Date(new java.util.Date().getTime());
		return date;
	}
}
