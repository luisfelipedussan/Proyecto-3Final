package modelo;
/**
 * Esta excepción se lanza cuando se presenta un error al leer o escribir el archivo con la información del estado del modelo del mundo. <br>
 * El mensaje asociado con la excepción debe describir el problema que se presentó.
 */
public class PersistenciaException extends Exception
{
    /**
    * Construye la excepción con el mensaje que se pasa como parámetro y que describe la causa del problema
    * @param causa el mensaje que describe el problema. pCausa != null && pCausa != "".
    */
   public PersistenciaException( String causa )
   {
       super( causa );
   }
    
}
