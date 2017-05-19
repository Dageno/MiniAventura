package miniAventura.frontEnd.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import miniAventura.backEnd.excepciones.ItemNoExistsException;

public class rankingPrice extends MostrarBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			rankingPrice dialog = new rankingPrice();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws ItemNoExistsException
	 */
	public rankingPrice() throws ItemNoExistsException {
		
		setTitle("Ranking por precios");
		medidas();
		precio.setVisible(true);
		lblPrecioDelObjeto.setVisible(true);
		atras.setVisible(false);
		adelante.setVisible(false);
		btnAtrasRanking.setVisible(true);
		btnAdelanteRanking.setVisible(true);
		/**
		 * Inicializar ranking
		 */
		rankIterator = dataBase.ranking();
		objeto = rankIterator.next();
		
		/**
		 * Comprobamos botones
		 */
		if(rankIterator.hasNext())
			btnAdelanteRanking.setEnabled(true);
		else
			btnAdelanteRanking.setEnabled(false);
		
		/**
		 * Mostrando objeto 
		 */
		mostrarDatabaseRanking(objeto);
		btnAtrasRanking.setEnabled(false);
		
		
		
		
		
	}
}
