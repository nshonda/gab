import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
import java.rmi.RMISecurityManager;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;

import teste.*;


public class OlaMundoClient {
	public static void main(String[] args) throws Exception { 
		System.setSecurityManager(new RMISecurityManager());

		/* procura pelo objeto remoto. 
		 * localhost eh a maquina onde esta o rmiregistry
		 * OlaMundoService eh o nome que foi dado ao objeto remoto */
		OlaMundoInterface c;
		c = (OlaMundoInterface) Naming.lookup ("rmi://localhost/OlaMundoService");
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
				if(c.ola (nome, msg).equals("bulhufas") == true){
				new DemoFrame().setVisible(true);
				}
			}
		}
	}
}
