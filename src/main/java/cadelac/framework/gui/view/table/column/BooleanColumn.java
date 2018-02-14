package cadelac.framework.gui.view.table.column;

public abstract class BooleanColumn<V> extends TableGenericColumn<Boolean,V> {
    public BooleanColumn(final String title_) {super(title_);}
    public BooleanColumn(final String title_, int columnIndex_) {super(title_, columnIndex_);}
    
    @Override 
    public Class<Boolean> getColumnClass() {return Boolean.class;}
}
