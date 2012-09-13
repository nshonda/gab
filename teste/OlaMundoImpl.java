public class OlaMundoImpl
	extends java.rmi.server.UnicastRemoteObject
	implements OlaMundoInterface {

	public OlaMundoImpl ()
		throws java.rmi.RemoteException {
		super ();
	}

	public String ola (String nome, String texto) {
		System.out.println (nome+": "+texto);
		return "bulhufas";
	}
}
