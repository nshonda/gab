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


public interface PaintInterface extends java.rmi.Remote {
	public String getArray (String nome, String texto) 
		throws java.rmi.RemoteException;
}
