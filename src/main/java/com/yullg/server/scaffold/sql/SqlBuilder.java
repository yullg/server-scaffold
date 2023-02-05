package com.yullg.server.scaffold.sql;

public abstract class SqlBuilder {

	public static InsertSqlBuilder insert(String table) {
		return new InsertSqlBuilder().table(table);
	}

	public static InsertSqlBuilder insert(Class<?> clazz) {
		return new InsertSqlBuilder().clazz(clazz);
	}

	public static DeleteSqlBuilder delete(String table) {
		return new DeleteSqlBuilder().table(table);
	}

	public static DeleteSqlBuilder delete(Class<?> clazz) {
		return new DeleteSqlBuilder().clazz(clazz);
	}

	public static UpdateSqlBuilder update(String table) {
		return new UpdateSqlBuilder().table(table);
	}

	public static UpdateSqlBuilder update(Class<?> clazz) {
		return new UpdateSqlBuilder().clazz(clazz);
	}

	public static SelectSqlBuilder select(String table) {
		return new SelectSqlBuilder().table(table);
	}

	public static SelectSqlBuilder select(Class<?> clazz) {
		return new SelectSqlBuilder().clazz(clazz);
	}

}