package cadelac.framework.gui.view.table;

import java.awt.event.MouseListener;
import java.util.function.Function;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import cadelac.framework.blade.core.Identified;
import cadelac.framework.blade.core.Provider;
import cadelac.framework.gui.view.table.column.TableGenericColumn;


public class TableGenericModel<K,E> 
		extends AbstractTableModel 
		implements Identified {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -313761349272263735L;
	public TableGenericModel(
			final String id_
			, final Provider<K,E> provider_
			, final Function<E,K> adapter_) {
		_adapter = adapter_;
		_id = id_;
		_columnManager = new ColumnManager<E>();
		_rowManager = new RowManager<K,E>(adapter_);
		_rowManager.setProvider(provider_);
		_rowManager.selectAllRows();
	}

	@Override
	public String getId() {
		return _id;
	}

	@Override
	public int getColumnCount() {
		return _columnManager.getColumnCount();
	}

	@Override
    public String getColumnName(int column_) {
		return _columnManager.getColumnTitle(column_);
	}
	
	@Override
	public Class<?> getColumnClass(int column_) {
		return _columnManager.getColumnClass(column_);
	}
	
	@Override
	public int getRowCount() {
		return _rowManager.getRowCount();
	}

	@Override
	public Object getValueAt(int rowIndex_, int columnIndex_) {
		final TableGenericColumn<?,E> column = _columnManager.getColumn(columnIndex_);
		final E element = _rowManager.getElementAtRow(rowIndex_);
		return (column != null && element != null) 
				? column.getValue(element) 
				: null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex_, int columnIndex_) {
		final TableGenericColumn<?,E> column = _columnManager.getColumn(columnIndex_);
		final E element = _rowManager.getElementAtRow(rowIndex_);
		if (column != null && element != null) {
			column.setValue(element, aValue);
			this.fireTableCellUpdated(rowIndex_, columnIndex_);
		}
    }
	
	@Override
    public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void setValue(Object aValue, K key_, int columnTag_) {
		if (_rowManager.isKeyExist(key_)) {
			int rowIndex = _rowManager.getRowFromKey(key_);
			int columnIndex = _columnManager.getColumnByTag(columnTag_).getColumnIndex();
			setValueAt(aValue, rowIndex, columnIndex);
		}
	}
	
    public void setTableHeader(JTableHeader tableHeader_) {
        _tableHeader = tableHeader_;
        if (_tableHeader != null && _tableHeaderMouseListener != null) {
            _tableHeader.addMouseListener(_tableHeaderMouseListener);
        }
    }	
	
	public void addColumn(final TableGenericColumn<?,E> column_) {
		_columnManager.addColumn(column_);
	}
	public void addRow(final E element_) {
		_rowManager.addRow(element_);
		int rowcount = _rowManager.getRowCount();
		this.fireTableRowsInserted(rowcount-1, rowcount-1);
	}
	
	public TableGenericColumn<?,E> getColumn(final int column_) {
		return _columnManager.getColumn(column_);
	}
	public E getElementAtRow(final int row_) {
		return _rowManager.getElementAtRow(row_);
	}
	
	public void selectAllRows() {
		_rowManager.selectAllRows();
	}
	
	public void updateRowWithKey(K key_) {
		int row = _rowManager.getRowFromKey(key_);
		this.fireTableRowsUpdated(row, row);
	}
	
	public void updateCellWithKey(K key_, int columnTag_) {
		if (_rowManager.isKeyExist(key_)) {
			this.fireTableCellUpdated(
					_rowManager.getRowFromKey(key_)
					, _columnManager.getColumnByTag(columnTag_).getColumnIndex());
		}
	}
	
    protected final ColumnManager<E> _columnManager;
    protected final RowManager<K,E> _rowManager;
    protected final Function<E,K> _adapter;
    protected /* not final */ MouseListener _tableHeaderMouseListener;
    
    private final String _id;
 	private JTableHeader _tableHeader;
}
