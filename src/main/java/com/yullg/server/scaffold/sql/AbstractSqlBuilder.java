package com.yullg.server.scaffold.sql;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractSqlBuilder<T extends AbstractSqlBuilder<T>> {

	protected String table;
	protected Class<?> clazz;
	protected boolean withSemicolon = true;

	protected String infer_table;

	public T table(String table) {
		this.table = table;
		return self();
	}

	public T clazz(Class<?> clazz) {
		this.clazz = clazz;
		return self();
	}

	public T withSemicolon(boolean withSemicolon) {
		this.withSemicolon = withSemicolon;
		return self();
	}

	public String build() {
		infer();
		return doBuild();
	}

	protected void infer() {
		infer_table = table;
		if (infer_table == null && clazz != null) {
			Table annTable = clazz.getAnnotation(Table.class);
			if (annTable != null) {
				infer_table = annTable.value();
			}
		}
	}

	protected String join(Iterable<String> iterable) {
		StringBuilder result = new StringBuilder();
		Iterator<String> iterator = iterable.iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next());
			while (iterator.hasNext()) {
				result.append(", " + iterator.next());
			}
		}
		return result.toString();
	}

	protected String join(Map<String, String> map) {
		StringBuilder result = new StringBuilder();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		if (iterator.hasNext()) {
			result.append(iterator.next().getKey());
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				result.append(" " + entry.getValue() + " " + entry.getKey());
			}
		}
		return result.toString();
	}

	protected abstract String doBuild();

	protected abstract T self();

}