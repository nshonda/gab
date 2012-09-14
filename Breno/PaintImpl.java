public class PaintImpl
	extends java.rmi.server.UnicastRemoteObject
	implements PaintInterface {

	public PaintImpl ()
		throws java.rmi.RemoteException {
		super ();
	}

	public String getArray (String nome, String texto) {
		System.out.println (nome+": "+texto);
		return "bulhufas";
	}
}
