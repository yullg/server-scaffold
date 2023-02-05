package com.yullg.server.scaffold.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertSqlBuilder extends AbstractSqlBuilder<InsertSqlBuilder> {

	private final List<String> columns = new ArrayList<>();
	private final List<String> values = new ArrayList<>();

	private final List<String> infer_columns = new ArrayList<>();
	private final List<String> infer_values = new ArrayList<>();

	public InsertSqlBuilder column(String column) {
		this.columns.add(column);
		return this;
	}

	public InsertSqlBuilder value(String value) {
		this.values.add(value);
		return this;
	}

	@Override
	protected void infer() {
		super.infer();
		infer_columns.clear();
		infer_values.clear();
		infer_columns.addAll(columns);
		infer_values.addAll(values);
		if (infer_columns.isEmpty() && infer_values.isEmpty() && clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Column annColumn = field.getAnnotation(Column.class);
				if (annColumn != null) {
					infer_columns.add(annColumn.value());
					infer_values.add(annColumn.placeholder());
				}
			}
		}
	}

	@Override
	protected String doBuild() {
		StringBuilder result = new StringBuilder();
		result.append("INSERT INTO " + infer_table);
		if (!infer_columns.isEmpty()) {
			result.append(" ( " + join(infer_columns) + " )");
		}
		result.append(" VALUES");
		if (!infer_values.isEmpty()) {
			result.append(" ( " + join(infer_values) + " )");
		} else if (!infer_columns.isEmpty()) {
			List<String> list = Collections.nCopies(infer_columns.size(), "?");
			result.append(" ( " + join(list) + " )");
		}
		if (withSemicolon) {
			result.append(";");
		}
		return result.toString();
	}

	@Override
	protected InsertSqlBuilder self() {
		return this;
	}

}