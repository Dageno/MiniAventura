package miniAventura.backEnd.clases;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class Fichero {
	static File comprobarExtend(File archivo) {
		String path = archivo.getPath();
		if (!path.endsWith(".db"))
			return new File(path + ".db");
		return archivo;
	}

	static void escribir(File archivo, Drop drop) throws FileNotFoundException, IOException {
		archivo = comprobarExtend(archivo);
		if (archivo.exists())
			if (!Gestion.deseaGuardar("El archivo ya existe, desea sobreescribirlo ? (y/n)"))
				return;
		try (ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(archivo)))) {
			ous.writeObject(drop);
			Gestion.setModificado(false);
		}
	}

	public static Drop leer(File archivo, Drop drop) throws IOException, ClassNotFoundException{
		archivo = comprobarExtend(archivo);
		if(Gestion.modificado)
			Gestion.nuevo(drop);
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(archivo)))) {
			return (Drop) ois.readObject();

		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "El fichero elegido no es correcto.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return drop;
		

	}

	public static void guardar(File archivo, Drop drop) throws FileNotFoundException, IOException {

		try (ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(archivo)))) {
			ous.writeObject(drop);
			Gestion.setModificado(false);
		}

	}
	
	
}
