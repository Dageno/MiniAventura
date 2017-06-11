package miniAventura.frontEnd.gui;

import miniAventura.backEnd.excepciones.ItemNoExistsException;

public class AnnadirInventario extends MostrarBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the dialog.
	 * @throws ItemNoExistsException 
	 */
	public AnnadirInventario() throws ItemNoExistsException {
		super();
		setTitle("A�adiendo al inventario");
		annadir.setVisible(true);
	
	}

}
