package cadelac.framework.gui.view.table.column;

public abstract class DoubleColumn<V> extends TableGenericColumn<Double,V> {
	public DoubleColumn(final String title_) {super(title_);}
    public DoubleColumn(final String title_, int columnIndex_) {super(title_, columnIndex_);}
    @Override
    public Class<Double> getColumnClass() {return Double.class;}
}
