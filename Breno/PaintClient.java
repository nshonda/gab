import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
import java.rmi.RMISecurityManager;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFrame;
import classes.*;


public class PaintClient extends JFrame{
	public PaintClient()
    {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new LinePanel(), BorderLayout.CENTER); //Aqui adicionamos o frame no painel.
        add(new JLabel("Use o mouse para desenhar"), BorderLayout.NORTH);        
    }

	public static void main(String[] args) throws Exception { 

		System.setSecurityManager(new RMISecurityManager());

		/* procura pelo objeto remoto. 
		 * localhost eh a maquina onde esta o rmiregistry
		 * OlaMundoService eh o nome que foi dado ao objeto remoto */
		PaintInterface c;
		c = (PaintInterface) Naming.lookup ("rmi://localhost/PaintService");
		BufferedReader stdIn;
		stdIn = new BufferedReader(new InputStreamReader(System.in));
		String nome = "";
		while (nome.equals ("")){
			System.out.print ("Entre com seu nome: ");
			nome = stdIn.readLine();
		}
		while (true){
			System.out.print ("Mensagem: ");
			String msg = stdIn.readLine ();
			if (msg.equals ("") == false){
				/* chamada de metodo ao objeto remoto */
				if(c.getArray (nome, msg).equals("bulhufas") == true){
				new PaintClient().setVisible(true);
				}
			}
		}
	}
}
