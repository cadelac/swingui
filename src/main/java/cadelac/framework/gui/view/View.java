package cadelac.framework.gui.view;

import cadelac.framework.blade.core.IdentifiedBase;

public abstract class View<T> 
		extends IdentifiedBase 
		implements HasOutlet<T> {
	
	public View(final String id_, final T outlet_) {
		super(id_);
		this._outlet = outlet_;
	}

	@Override
	public T getOutlet() {
		return _outlet;
	}
	
	protected T _outlet;
}
