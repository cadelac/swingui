package cadelac.framework.gui.view.table;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import cadelac.framework.gui.view.table.column.TableGenericColumn;


/**
 * improved double cell renderer
 * @author cadelac
 *
 */
public class DblCellRenderer<V> 
		extends JLabel 
		implements TableCellRenderer {

	public static final Color GREEN = new Color(0, 102, 0);
	public static final Color RED = new Color(153, 0, 0);
	public static final Color WHITE = Color.WHITE;

	
	class ColorAttribute {
		public ColorAttribute(
				Color background_
				, Color foreground_) {
			_background = background_;
			_foreground = foreground_;
		}
		Color _background;
		Color _foreground;
	}
	
	private final ColorAttribute UPTICK = new ColorAttribute(GREEN, WHITE);
	private final ColorAttribute DOWNTICK = new ColorAttribute(RED, WHITE);

	public DblCellRenderer(final DecimalFormat decimalFormat_) {
		setHorizontalAlignment(SwingConstants.RIGHT);
		setOpaque(true);
		decimalFormat = decimalFormat_;
	}


	@Override
	public Component getTableCellRendererComponent(
			JTable table
			, Object value
			, boolean isSelected
			, boolean hasFocus
			, int row
			, int column) {
		
		final ColorAttribute NORMAL = 
				new ColorAttribute(
						table.getBackground()
						, table.getForeground());
		final ColorAttribute SELECTED = 
				new ColorAttribute(
						table.getSelectionBackground()
						, table.getSelectionForeground());

		final Double doubleValue = (Double)value;
		setText(decimalFormat.format(doubleValue));

		ColorAttribute setting = NORMAL;

		if (isSelected) {
			setting = SELECTED;
		}
		else {
			final TableModel tableModel = table.getModel();
			if (tableModel instanceof TableGenericModel) {
				TableGenericModel<?,?> tableGenericModel = 
						(TableGenericModel<?,?>) tableModel;

				@SuppressWarnings("unchecked")
				TableGenericColumn<Double,V> tableGenericColumn = 
					(TableGenericColumn<Double,V>) 
						tableGenericModel.getColumn(column);
				
				if (tableGenericColumn != null) {
					@SuppressWarnings("unchecked")
					V rowData = (V) tableGenericModel.getElementAtRow(row);
					if (rowData != null) {
						final Integer delta = tableGenericColumn.getDelta(rowData);
						if (delta != null) {
							setting = (delta>0.0) 
									? UPTICK
									: ((delta<0.0)
											? DOWNTICK
											:NORMAL);
						}
					}
				}
			}
		}
		
		setColors(setting);
		return this;
	}

	private void setColors(ColorAttribute setting_) {
		setBackground(setting_._background);
		setForeground(setting_._foreground);
	}
	
	private final DecimalFormat decimalFormat;
}