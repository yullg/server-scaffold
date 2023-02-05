package com.yullg.server.scaffold.sql;

import java.util.LinkedHashMap;
import java.util.Map;

public class DeleteSqlBuilder extends AbstractSqlBuilder<DeleteSqlBuilder> {

	private Map<String, String> wheres = new LinkedHashMap<>();

	public DeleteSqlBuilder where(String where) {
		this.wheres.put(where, "AND");
		return this;
	}

	public DeleteSqlBuilder where(String where, String separator) {
		this.wheres.put(where, separator);
		return this;
	}

	@Override
	protected String doBuild() {
		StringBuilder result = new StringBuilder();
		result.append("DELETE FROM " + infer_table);
		if (!wheres.isEmpty()) {
			result.append(" WHERE " + join(wheres));
		}
		if (withSemicolon) {
			result.append(";");
		}
		return result.toString();
	}

	@Override
	protected DeleteSqlBuilder self() {
		return this;
	}

}