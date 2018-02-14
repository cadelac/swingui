package cadelac.framework.gui.view;

import javax.swing.JTable;

import cadelac.framework.gui.view.table.TableGenericModel;

public interface HasTableView<K,E> {

	JTable getTable();
	void setTable(JTable table_);
	
	TableGenericModel<K,E> getTableModel();
	void setTableModel(TableGenericModel<K,E> tableModel_);
}
