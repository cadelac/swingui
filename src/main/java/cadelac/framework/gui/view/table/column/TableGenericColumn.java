package cadelac.framework.gui.view.table.column;

import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

public abstract class TableGenericColumn<C,V> {

	public TableGenericColumn(
			final String title_
			, int tag_
			, int columnIndex_
			, int width_) {
		_title = title_;
		_columnIndex = columnIndex_;
		_width = width_;
		_tag = tag_;
	}
	public TableGenericColumn(final String title_, int tag_) {
		this(title_, tag_, -1, -1);
	}
	public TableGenericColumn(final String title_) {
		this(title_, -1, -1, -1);        
	}

	public String getTitle() {
		return _title;
	}
	public int getTag() {
		return _tag;
	}

	public abstract Comparator<V> getComparator();

	public int getColumnIndex() {
		return _columnIndex;
	}
	public void setColumnIndex(int columnIndex_) {
		_columnIndex = columnIndex_;
	}

	public int getWidth() {
		return _width;
	}
	public void setWidth(int width_) {
		_width = width_;
	}

	public abstract Class<C> getColumnClass();

	public C getValue(final V model_) {
		return null;
	}
	
	public void setValue(
			final AbstractTableModel state_
			, final V model_
			, final C value_) {
	}

	public C getDelta(final V model_) {
		return null;
	}

	private final String  _title;
	private int _columnIndex;
	private int _width;
	private final int _tag;
}