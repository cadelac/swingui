package cadelac.framework.gui.view.table;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import cadelac.framework.blade.core.Provider;
import cadelac.framework.gui.view.table.column.TableGenericColumn;

public class RowManager<K,E> {

	private class RowMgr {
		public RowMgr(final List<E> elements_) {
			installRows(elements_);
		}
		public K getKeyAtRow(int row_) {
			return _keyMap.get(row_);
		}
		public int getRowFromKey(final K key_) {
			return _rowMap.get(key_);
		}
		public void addRow(final Integer row_, final K key_) {
			_keyMap.put(row_, key_);
			_rowMap.put(key_, row_);
		}
		public int getRowCount() {
			return _keyMap.size();
		}
		public void setRows(final List<E> store_) {
			installRows(store_);
		}
		private void installRows(final List<E> elements_) {
			_keyMap = new HashMap<Integer,K>();
			_rowMap = new HashMap<K,Integer>();
			int row = 0;
			for (E element : elements_) {
				addRow(row, _adapter.apply(element));
				row++;
			}
		}

		private Map<Integer,K> _keyMap; // map from row to key
		private Map<K,Integer> _rowMap; // map from key to row
	}
	
	
	public RowManager(final Function<E,K> adapter_) {
		_adapter = adapter_;
		_provider = null;
		_rowMgr = null;
	}
	
	public void setProvider(final Provider<K,E> provider_) {
		_provider = provider_;
	}
	public void addRow(final E element_) {
		final K key = _adapter.apply(element_);
		_provider.safeAdd(key, element_);
		_rowMgr.addRow(getRowCount(), key);
	}
	public E getElementAtRow(final int row_) {
		final K key = _rowMgr.getKeyAtRow(row_);
		if (key == null)
			return null;
		return _provider.getElement(key);
	}
	public int getRowFromKey(final K key_) {
		return _rowMgr.getRowFromKey(key_);
	}
	public List<E> getAllRows() {
		return _provider.getAllElements();
	}
	public void selectAllRows() {
		_rowMgr = createRowMgr(_provider.getAllElements());
	}
	public void selectFilteredRows(final Predicate<E> filter_) {
		_rowMgr = createRowMgr(_provider.getFilteredElements(filter_));
	}
	public int getRowCount() {
		if (_rowMgr==null)
			return 0;
		return _rowMgr.getRowCount();
	}
	public void setRows(final List<E> store_) {
		_rowMgr.setRows(store_);
	}
	public void sort(final TableGenericColumn<?,E> gcolumn_) {
		Comparator<E> comparator = gcolumn_.getComparator();
		if (comparator != null) {
			List<E> rows = getAllRows();
    			Collections.sort(rows, comparator);
    			setRows(rows);
		}
	}
	

	private RowMgr createRowMgr(final List<E> elements_) {
		return new RowMgr(elements_);
	}
	
	
	private final Function<E,K> _adapter;
	private /* must not be final */ Provider<K,E> _provider;
	private /* must not be final */ RowMgr _rowMgr;
}
