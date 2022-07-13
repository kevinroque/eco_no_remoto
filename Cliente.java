/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin_Roque
 */
import javax.swing.*;
import java.net.*;
import java.io.*;
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("\tIniciando el  Cliente\n");
        
        
        BufferedReader EntradaUser = new BufferedReader(new InputStreamReader(System.in));
        String NameServer = JOptionPane.showInputDialog("Ingresa la direcion ip del server");
        String puerto = JOptionPane.showInputDialog("Ingresa el puerto que le diste");
        int Port = 0;
        if (puerto.isEmpty() || puerto.isBlank())
        {
            Port = 5001;
        }else {
            Port = Integer.parseInt(puerto);
        }
        String Message;
        try
        {
            InetAddress IpAd = InetAddress.getByName(NameServer);
            DatagramSocket ConeccionCliente = new DatagramSocket();
            String cierre = new String("cierre");
                
            while(true)
            {

                System.out.println("Ingresa el mensaje que desea eniarle al servidor");
                Message = EntradaUser.readLine();

                byte[]DatosEnviar = new byte[Message.length()];
                byte[]DataRecive = new byte[Message.length()];

                if(Message.equals(""))Message = "cierre";
                
                System.out.println("Cadena que se esta enviando: " + Message);
                DatosEnviar = Message.getBytes();
                DatagramPacket EnviarPakete = new DatagramPacket(DatosEnviar,DatosEnviar.length,IpAd,Port);
                
                
                try{
                    ConeccionCliente.send(EnviarPakete);
                }catch(ConnectException e){
                    System.out.println("Eror en el envio: " + e.getMessage());
                }
                

                DatagramPacket RecivirPakete = new DatagramPacket(DataRecive,DataRecive.length);
                ConeccionCliente.receive(RecivirPakete);

                String Respuesta = new String(RecivirPakete.getData());
                
                
                if(!Respuesta.isEmpty() && !Message.equals("cierre"))
                {
                    
                    System.out.println("Respuesta del servidor: "+Respuesta + "\n");
                }
                else
                {
                    
                    
                    System.out.println("Respuesta del servidor: "+Respuesta);
                    
                    System.out.println("Cerrando la conccecion\n");
                    ConeccionCliente.close();
                    break;
               
                }
                
                
               
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("Ocurrio un error al tratar de conectar: "+e.getMessage() + "\n");
        }
        
        
        
        
        System.out.println("Adios");
    }
    
}
