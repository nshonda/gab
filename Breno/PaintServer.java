/*	
	Instituição: Universidade Estadual de Londrina
	Disciplina: Sistemas Operacionais
	Professor: Fábio Sakuray
	Bimestre: 3º Bimestre
	Ano: 2012
	
	Autores:
		Breno Naodi Kusunoki
		Luiz Guilherme Castilho Martins
	
	Tema:
		Implementar um Paint Compartilhado
	
	Descrição:
			O trabalho consiste em desenvolver uma aplicação em Java
		onde diversos usuários poderão utilizar o mesmo quadro branco
		para desenhar linhas, sendo que cada usuário terá a sua linha
		com uma cor diferenciada dos demais.
			Casa usuário poderá utilizar um quadro branco já criado,
		ou criar um novo para que ele possa desenhar, assim disponibi-
		lizando o mesmo para os demais usuários desenharem.
*/


import java.rmi.RMISecurityManager;
import java.rmi.Naming;
import javax.swing.JFrame;

public class PaintServer extends JFrame {
	public PaintServer () throws Exception {
		System.out.println ("Iniciando o Servidor Paint...");
		System.setSecurityManager(new RMISecurityManager());

		System.out.println ("Instanciando objeto remoto...");    /* Efetuando a Instância do Objeto Remoto */
		PaintInterface _paint = new PaintImpl();

		/* localhost eh o endereco do servidor,
		 * PaintService eh o nome que sera dado ao objeto */
		Naming.rebind ("rmi://localhost/PaintService", _paint);

		/* A partir daqui, o programa fica aguardando chamadas remotas*/
		System.out.println ("Objeto " + _paint + " aguardando chamadas...");
	}

	public static void main(String args[]) throws Exception{
		new PaintServer();
	}
}
