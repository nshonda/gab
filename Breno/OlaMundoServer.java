import java.rmi.RMISecurityManager;
import java.rmi.Naming;
import javax.swing.JFrame;

public class OlaMundoServer extends JFrame {
	public OlaMundoServer () throws Exception {
		System.out.println ("Comecando a execucao...");
		System.setSecurityManager(new RMISecurityManager());

		/* instanciacao do objeto remoto */
		System.out.println ("Iniciando objeto remoto...");
		OlaMundoInterface ola = new OlaMundoImpl();

		/* localhost eh o endereco do servidor,
		 * OlaMundoService eh o nome que sera dado ao objeto */
		Naming.rebind ("rmi://localhost/OlaMundoService", ola);

		/* A partir daqui, o programa fica aguardando chamadas remotas*/
		System.out.println ("Objeto " + ola + " aguardando invocacoes remotas de metodos");
	}

	public static void main(String args[]) throws Exception{
		new OlaMundoServer ();
	}
}
