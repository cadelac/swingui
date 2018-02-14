package cadelac.framework.gui.view.table.column;

public abstract class IntegerColumn<V> extends TableGenericColumn<Integer,V> {
    public IntegerColumn(final String title_) {super(title_);}
    public IntegerColumn(final String title_, int columnIndex_) {super(title_, columnIndex_);}
    
    @Override 
    public Class<Integer> getColumnClass() {return Integer.class;}
}