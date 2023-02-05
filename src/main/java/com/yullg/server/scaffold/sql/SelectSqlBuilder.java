package com.yullg.server.scaffold.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SelectSqlBuilder extends AbstractSqlBuilder<SelectSqlBuilder> {

	private List<String> columns = new ArrayList<>();
	private Map<String, String> wheres = new LinkedHashMap<>();
	private String group;
	private Map<String, String> havings = new LinkedHashMap<>();
	private String order;
	private String limit;
	private String offset;

	private List<String> infer_columns = new ArrayList<>();

	public SelectSqlBuilder column(String column) {
		this.columns.add(column);
		return this;
	}

	public SelectSqlBuilder where(String where) {
		this.wheres.put(where, "AND");
		return this;
	}

	public SelectSqlBuilder where(String where, String separator) {
		this.wheres.put(where, separator);
		return this;
	}

	public SelectSqlBuilder group(String group) {
		this.group = group;
		return this;
	}

	public SelectSqlBuilder having(String having) {
		this.havings.put(having, "AND");
		return this;
	}

	public SelectSqlBuilder having(String having, String separator) {
		this.havings.put(having, separator);
		return this;
	}

	public SelectSqlBuilder order(String order) {
		this.order = order;
		return this;
	}

	public SelectSqlBuilder limit(String limit) {
		this.limit = limit;
		return this;
	}

	public SelectSqlBuilder limit(Integer limit) {
		this.limit = Objects.toString(limit, null);
		return this;
	}

	public SelectSqlBuilder offset(String offset) {
		this.offset = offset;
		return this;
	}

	public SelectSqlBuilder offset(Integer offset) {
		this.offset = Objects.toString(offset, null);
		return this;
	}

	@Override
	protected void infer() {
		super.infer();
		infer_columns.clear();
		infer_columns.addAll(columns);
		if (infer_columns.isEmpty() && clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Column annColumn = field.getAnnotation(Column.class);
				if (annColumn != null) {
					String alias = annColumn.alias();
					if (alias.isEmpty()) {
						infer_columns.add(annColumn.value());
					} else {
						infer_columns.add(annColumn.value() + " AS " + alias);
					}
				}
			}
		}
	}

	@Override
	protected String doBuild() {
		StringBuilder result = new StringBuilder();
		result.append("SELECT ");
		if (infer_columns.isEmpty()) {
			result.append("*");
		} else {
			result.append(join(infer_columns));
		}
		result.append(" FROM " + infer_table);
		if (!wheres.isEmpty()) {
			result.append(" WHERE " + join(wheres));
		}
		if (group != null) {
			result.append(" GROUP BY " + group);
		}
		if (!havings.isEmpty()) {
			result.append(" HAVING " + join(havings));
		}
		if (order != null) {
			result.append(" ORDER BY " + order);
		}
		if (limit != null) {
			result.append(" LIMIT " + limit);
		}
		if (offset != null) {
			result.append(" OFFSET " + offset);
		}
		if (withSemicolon) {
			result.append(";");
		}
		return result.toString();
	}

	@Override
	protected SelectSqlBuilder self() {
		return this;
	}

}