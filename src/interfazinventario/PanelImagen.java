package interfazinventario;



import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel que contiene el banner con la imagen.
 */
@SuppressWarnings("serial")
public class PanelImagen extends JPanel
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta de la imagen del banner.
     */
    public final static String RUTA_IMAGEN = "./data/super.jpg";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta de la imagen.
     */
    private JLabel labImagen;
    
    private ImageIcon imagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con el banner.
     */
    public PanelImagen( )
    {
    	imagen = new ImageIcon( RUTA_IMAGEN );
        labImagen = new JLabel( );
        labImagen.setIcon( imagen );
        add( labImagen );        
    }
}