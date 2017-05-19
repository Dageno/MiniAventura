package miniAventura.frontEnd.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import miniAventura.backEnd.clases.Drop;
import miniAventura.backEnd.clases.Gestion;
import miniAventura.backEnd.excepciones.ItemNoExistsException;


public class FrontPanel{

	JFrame frame;
	public static JFileChooser fileChooser;
	/**
	 * Bloque est�tico para iniciar el filechooser
	 */
	static {
		fileChooser = new JFileChooser();
		FileNameExtensionFilter extension = new FileNameExtensionFilter("Base de Datos Butcher", "db");
		fileChooser.setFileFilter(extension);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontPanel window = new FrontPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public FrontPanel() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setTitle("DataBase Butcher of Vlabiken");
		frame.setBounds(550, 200, 700, 440);
	
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (Gestion.getModificado()) {
					if (Gestion.deseaGuardar("Se han hecho modificaciones, Desea guardar los cambios ? (y/n)")) {
						if (Gestion.getFile() == null)
							try {
								Gestion.guardarComo(fileChooser.getSelectedFile(), Template.dataBase);
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(frame, "Se ha producido un error al guardar el archivo.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						else
							try {
								Gestion.guardar(Template.dataBase);
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(frame, "Se ha producido un error al guardar el archivo.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
					}
				}
			}
		});
		
		try {
			BufferedImage image = null;
			image = ImageIO.read(new File(getClass().getResource("/miniAventura/frontEnd/images/1.jpg").getFile()));	
			PanelImage backGround = new PanelImage(image);
			((JComponent) frame.getContentPane()).setBorder(backGround);
			frame.getContentPane().setLayout(null);
		} catch (IOException e) {
			
		}
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				try {
					Gestion.nuevo(guardarArchivo(), Template.dataBase);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(frame, "No se encuentra el archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Acceso cancelado por el usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		mnArchivo.add(mntmNuevo);
		
		JMenuItem mntmAbrirBaseDe = new JMenuItem("Abrir Base de Datos");
		mntmAbrirBaseDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Template.dataBase = Gestion.abrir(leerArchivo(), Template.dataBase);
					

				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(frame,
							"El fichero no contiene ning�n concesionario.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Acceso cancelado por el usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnArchivo.add(mntmAbrirBaseDe);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gestion.guardar(Template.dataBase);
					JOptionPane.showMessageDialog(frame, "Se ha guardado el archivo con �xito.");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Acceso cancelado por el usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Gestion.guardarComo(guardarArchivo(), Template.dataBase);
					
					JOptionPane.showMessageDialog(frame, "Se ha guardado el archivo con �xito.");
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(frame, "No se encuentra el archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Acceso cancelado por el usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnArchivo.add(mntmGuardarComo);
		 mnArchivo.add(new JSeparator());
		 
		 JMenuItem mntmSalir = new JMenuItem("Salir");
		 mntmSalir.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		frame.dispose();
		 	}
		 });
		 mnArchivo.add(mntmSalir);
		
		JMenu mnAdministracin = new JMenu("Administraci\u00F3n");
		menuBar.add(mnAdministracin);
		
		JMenuItem mntmAadirObjeto = new JMenuItem("A\u00F1adir objeto");
		mntmAadirObjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpBase up = new UpBase();
				up.setModal(true);
				up.setVisible(true);
			}
		});
		mnAdministracin.add(mntmAadirObjeto);
		
		JMenuItem mntmEliminarObjeto = new JMenuItem("Eliminar objeto");
		mntmEliminarObjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Template.dataBase.isEmpty())
					JOptionPane.showMessageDialog(frame, "La base de datos est� vac�a", "Error",
							JOptionPane.ERROR_MESSAGE);
				else{
					DownBase dBase = new DownBase();
					dBase.setModal(true);
					dBase.setVisible(true);
				}
				
			}
		});
		mnAdministracin.add(mntmEliminarObjeto);
		
		JMenuItem mntmBuscarObjeto = new JMenuItem("Buscar Objeto");
		mntmBuscarObjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Template.dataBase.isEmpty())
					JOptionPane.showMessageDialog(frame, "La base de datos est� vac�a", "Error",
							JOptionPane.ERROR_MESSAGE);
				else{
					searchObject search = new searchObject();
					search.setModal(true);
					search.setVisible(true);
				}
			}
		});
		mnAdministracin.add(mntmBuscarObjeto);
		mnAdministracin.add(new JSeparator());
		
		JMenuItem mntmRankingPorPrecio = new JMenuItem("Ranking por precio");
		mntmRankingPorPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Template.dataBase.isEmpty())
					JOptionPane.showMessageDialog(frame, "La base de datos est� vac�a", "Error",
							JOptionPane.ERROR_MESSAGE);
				else{
					rankingPrice ranking;
					try {
						ranking = new rankingPrice();
						ranking.setModal(true);
						ranking.setVisible(true);
					} catch (ItemNoExistsException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		mnAdministracin.add(mntmRankingPorPrecio);
		
		JMenu mnInventario = new JMenu("Inventario");
		menuBar.add(mnInventario);
		
		JMenuItem mntmAadir = new JMenuItem("A\u00F1adir");
		mntmAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Template.dataBase.isEmpty())
					JOptionPane.showMessageDialog(frame, "La base de datos est� vac�a", "Error",
							JOptionPane.ERROR_MESSAGE);
				else{
					try {
						AnnadirInventario annadir = new AnnadirInventario();
						annadir.setModal(true);
						annadir.setVisible(true);
					} catch (ItemNoExistsException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		
		mnInventario.add(mntmAadir);
		
		JMenuItem mntmBorrar = new JMenuItem("Borrar");
		mntmBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Drop.inventario.isEmpty())
					JOptionPane.showMessageDialog(frame, "El inventario esta vac�o", "Error",
							JOptionPane.ERROR_MESSAGE);
				else{
					try {
						BorrarInventario borrar = new BorrarInventario();
						borrar.setModal(true);
						borrar.setVisible(true);
					} catch (ItemNoExistsException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		mnInventario.add(mntmBorrar);
		
		JMenuItem mntmMostrarInventario = new JMenuItem("Mostrar inventario");
		mntmMostrarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Drop.inventario.isEmpty())
					JOptionPane.showMessageDialog(frame, "El inventario esta vac�o", "Error",
							JOptionPane.ERROR_MESSAGE);
				else{
					try {
						MostrarInventario showInv = new MostrarInventario();
						showInv.setModal(true);
						showInv.setVisible(true);
					} catch (ItemNoExistsException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mnInventario.add(mntmMostrarInventario);
		
		
		JMenu mnBaseDeDatos = new JMenu("Base de Datos");
		menuBar.add(mnBaseDeDatos);
		
		JMenuItem mntmMostrarBaseDe = new JMenuItem("Mostrar base de datos");
		mntmMostrarBaseDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Template.dataBase.isEmpty())
					JOptionPane.showMessageDialog(frame, "La base de datos est� vac�a", "Error",
							JOptionPane.ERROR_MESSAGE);
				else{
					MostrarBase dataBase;
					try {
			
						dataBase = new MostrarBase();
						dataBase.setModal(true);
						dataBase.setVisible(true);
					} catch (ItemNoExistsException e1) {
						
						JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
			}
		});
		mnBaseDeDatos.add(mntmMostrarBaseDe);
		
		JMenuItem mntmMostrarPorClase = new JMenuItem("Mostrar por Clase");
		mntmMostrarPorClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Template.dataBase.isEmpty())
					JOptionPane.showMessageDialog(frame, "La base de datos est� vac�a", "Error",
							JOptionPane.ERROR_MESSAGE);
				else{
					MostrarPorClase mostrarClase;
					try {
			
						mostrarClase = new MostrarPorClase();
						mostrarClase.setModal(true);
						mostrarClase.setVisible(true);
					} catch (ItemNoExistsException e1) {
						
						JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mnBaseDeDatos.add(mntmMostrarPorClase);
		
//		JMenuItem mntmMostrarLogs = new JMenuItem("Mostrar logs");
//		mnBaseDeDatos.add(mntmMostrarLogs);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmManualDeUso = new JMenuItem("Manual de uso");
		mntmManualDeUso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Ayuda ayuda = Ayuda.getSingleton();
					ayuda.setVisible(true);
					
				
			}
		});
		mnAyuda.add(mntmManualDeUso);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe acercaDe = new AcercaDe();
				acercaDe.setModal(true);
				acercaDe.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);

		
	}
	
	public static File leerArchivo() {

		
		if (fileChooser.showOpenDialog(null) != JFileChooser.CANCEL_OPTION) {
			return fileChooser.getSelectedFile();

		} else {

			return null;
		}
	}
	
	public static File guardarArchivo() {

		if (fileChooser.showSaveDialog(null) != JFileChooser.CANCEL_OPTION) {
			return fileChooser.getSelectedFile();

		} else {

			return null;
		}
	}
}
