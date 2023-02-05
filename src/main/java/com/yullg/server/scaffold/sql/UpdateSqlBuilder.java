package com.yullg.server.scaffold.sql;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UpdateSqlBuilder extends AbstractSqlBuilder<UpdateSqlBuilder> {

	private List<String> sets = new ArrayList<>();
	private Map<String, String> wheres = new LinkedHashMap<>();

	public UpdateSqlBuilder set(String set) {
		this.sets.add(set);
		return this;
	}

	public UpdateSqlBuilder where(String where) {
		this.wheres.put(where, "AND");
		return this;
	}

	public UpdateSqlBuilder where(String where, String separator) {
		this.wheres.put(where, separator);
		return this;
	}

	@Override
	protected String doBuild() {
		StringBuilder result = new StringBuilder();
		result.append("UPDATE " + infer_table);
		if (!sets.isEmpty()) {
			result.append(" SET " + join(sets));
		}
		if (!wheres.isEmpty()) {
			result.append(" WHERE " + join(wheres));
		}
		if (withSemicolon) {
			result.append(";");
		}
		return result.toString();
	}

	@Override
	protected UpdateSqlBuilder self() {
		return this;
	}

}