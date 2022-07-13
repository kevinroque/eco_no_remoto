      /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin_Roque
 */
import java.net.*;
import javax.swing.JOptionPane;
public class ServerUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try
        {
            System.out.println("\nInciando el servidor\n");
            int Puerto = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el puerto en el que va a iniciar el server"));
            DatagramSocket Coneccion = new DatagramSocket(Puerto);
            String Cerrar = "cierre";
            
            while(true)
            {
                byte[]Cadenarecivida = new byte[1024];
                byte[] CadenaEnviar  = new  byte[1024];
                DatagramPacket PacketRecivido = new DatagramPacket(Cadenarecivida,Cadenarecivida.length);
                Coneccion.receive(PacketRecivido);
                
                String CadenaServer = new String(PacketRecivido.getData());
                CadenaEnviar = CadenaServer.getBytes();
                
               InetAddress Ip = PacketRecivido.getAddress();
               int puerto = PacketRecivido.getPort();
               
               System.out.println("La cadena enviada es: " + PacketRecivido.getAddress());
               
               DatagramPacket enviarpaqket = new DatagramPacket(CadenaEnviar,CadenaEnviar.length,
               Ip,puerto);
               String Senviada = new String(PacketRecivido.getData());
               Coneccion.send(enviarpaqket);
               
              
               if(CadenaServer.indexOf("cierre") <0 )
               {
                   System.out.println("Enviando "+Senviada.toString());
                   
                }
               else
               {
                   
                   System.out.println("Se detecto cadena vacia para cerrar la conection");
                    Coneccion.close();
                    System.out.println("El servidor a cerrado secion\n");
                    break;
               }
               
//               System.out.println("Hola server Abajo");
            }
        }
        catch(Exception e)
        {
            System.out.println("Ocurrio un error: "+e.getMessage());
        }
        System.out.println("Adios clientete");
    }
    
}
