package persist;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import consola.Consola;
import modelo.Inventario;
import modelo.POS;

public class ArchivoMercado {
	public void guardar(POS pos) throws FileNotFoundException, IOException
	{
		ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("POS.data"));
		salida.writeObject(pos);
		salida.close();
	}
	
	public POS recuperar() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("POS.data"));
		POS pos =  (POS) entrada.readObject();
		entrada.close();
		return pos;
	}
	
	
	
	
}
