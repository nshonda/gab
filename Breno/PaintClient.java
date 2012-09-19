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


import java.rmi.Naming; 				/* #TODO: Ver documentação do JAVA */
import java.rmi.RemoteException; 		/* #TODO: Ver documentação do JAVA */
import java.net.MalformedURLException; 	/* #TODO: Ver documentação do JAVA */
import java.rmi.NotBoundException; 		/* #TODO: Ver documentação do JAVA */
import java.rmi.RMISecurityManager;		/* #TODO: Ver documentação do JAVA */
import java.net.*;						/* #TODO: Ver documentação do JAVA */
import java.io.*;						/* #TODO: Ver documentação do JAVA */
import java.util.*;						/* #TODO: Ver documentação do JAVA */
import java.awt.BorderLayout;			/* #TODO: Ver documentação do JAVA */
import javax.swing.JFrame;				/* #TODO: Ver documentação do JAVA */
import javax.swing.JLabel;				/* #TODO: Ver documentação do JAVA */
import java.awt.Toolkit;				/* #TODO: Ver documentação do JAVA */


/*
	Classe:
		PaintClient

	Descrição:
		Fornecer um GUI para o usuário final, para que ele possa
		criar ou desenhar linhas em um quadro branco já existente.
*/

public class PaintClient extends JFrame{
	
	/* Construtor da classe */
	public PaintClient() throws Exception
	{
        setTitle("Paint Distribuído - Versão 1.0");    													/* Define o Título da Janela que contêm o JFrame / JPanel 	*/
        setSize(643, 388);    																			/* Define o tamanho do JFrame 								*/
        setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (getWidth() / 2)),
                    ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (getHeight() / 2)));    /* Inicializa o frame no centro da Tela 					*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    												/* Atribui uma Função do Botão fechar Janela 				*/
        add(new LinePanel(), BorderLayout.CENTER);    													/* Instancia um Painel e o adiciona no JFrame 				*/
    }

    /* Onde a brincadeira começa :) */
	public static void main(String[] args) throws Exception {

		new PaintClient().setVisible(true);    /* Instancia o objeto chamando o construtor */
	}
}
