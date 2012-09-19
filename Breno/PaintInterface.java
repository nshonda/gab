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


import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.Serializable;


public interface PaintInterface extends java.rmi.Remote {

	/*
	** Método para criação de um quadro
	** Argumentos:
	**     0 - Nome do Quadro
	**     1 - Nome do Usuário
	*/
	public int criarQuadro(String _nomeDoQuadro, String _nomeDoUsuario)
		throws java.rmi.RemoteException;
	
	public void desenharReta(String _nomeDoQuadro, String _nomeDoUsuario, int _CliqueX, int _CliqueY, int ultimoCliqueX, int ultimoCliqueY)
		throws java.rmi.RemoteException;

	/*
	** Somente para fins de teste
	*/
	public int[] getImagem (String _nomeDoQuadro) 
		throws java.rmi.RemoteException;
}
