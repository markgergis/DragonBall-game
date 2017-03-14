package dragonball.model.cell;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Cell implements Serializable {

	public abstract String toString();

	private CellListener listener;

	public CellListener getListener() {
		return listener;
	}

	public void setListener(CellListener listener) {
		this.listener = listener;
	}

	public abstract void onStep();

}
