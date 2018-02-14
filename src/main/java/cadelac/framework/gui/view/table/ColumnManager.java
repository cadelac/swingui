package cadelac.framework.gui.view.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cadelac.framework.gui.view.table.column.TableGenericColumn;

public class ColumnManager<E> {
	
	public ColumnManager() {
		_columns = new ArrayList<TableGenericColumn<?,E>>();
		_map = new HashMap<Integer,TableGenericColumn<?,E>>();
	}
	
	public TableGenericColumn<?,E> getColumn(final int index_) {
		return (index_<0 || getColumnCount()-1<index_) ? null : _columns.get(index_);
	}
	
	public TableGenericColumn<?,E> getColumnByTag(final int tag_) {
		return _map.get(tag_);
	}

	public int getColumnCount() {
		return _columns.size();
	}
	
	public String getColumnTitle(final int index_) {
		final TableGenericColumn<?,E> column = getColumn(index_);
		return (column==null) ? "" : column.getTitle();
	}
	
	public Class<?> getColumnClass(final int index_) {
		final TableGenericColumn<?,E> column = getColumn(index_);
		return (column==null) ? null : column.getColumnClass();
	}
	
	public void addColumn(final TableGenericColumn<?,E> column_) {
		column_.setColumnIndex(_columns.size());
		_columns.add(column_);
		final int tag = column_.getTag();
		if (tag!=-1)
			_map.put(tag, column_);
	}
	/**
	 * access columns by index
	 */
	private final List<TableGenericColumn<?,E>> _columns;
	/**
	 * access columns by tag
	 */
	private final Map<Integer,TableGenericColumn<?,E>> _map;
}
