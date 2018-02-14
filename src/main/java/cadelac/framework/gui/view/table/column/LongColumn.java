package cadelac.framework.gui.view.table.column;

public abstract class LongColumn<V> extends TableGenericColumn<Long,V> {
    public LongColumn(final String title_) {super(title_);}
    public LongColumn(final String title_, int columnIndex_) {super(title_, columnIndex_);}
    
    @Override 
    public Class<Long> getColumnClass() {return Long.class;}
}