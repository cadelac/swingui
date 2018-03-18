package cadelac.framework.gui.view;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public interface HasSortableTableView {

	JTable getTable();
	void setTable(JTable table_);
	
	TableModel getTableModel();
	void setTableModel(TableModel tableModel_);
}
