package cadelac.framework.gui.view.table.column;

public abstract class StringColumn<V> extends TableGenericColumn<String,V>  {
	public StringColumn(final String title_) {super(title_);}
	public StringColumn(final String title_, int columnIndex_) {super(title_, columnIndex_);}
	
	@Override 
	public Class<String> getColumnClass() {return String.class;}
}
