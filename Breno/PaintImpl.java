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

import java.awt.BasicStroke;            /* #TODO: Ver documentação do JAVA */
import java.awt.Color;                  /* #TODO: Ver documentação do JAVA */
import java.awt.Graphics;               /* #TODO: Ver documentação do JAVA */
import java.awt.Graphics2D;             /* #TODO: Ver documentação do JAVA */
import java.awt.Point;                  /* #TODO: Ver documentação do JAVA */
import java.awt.event.MouseAdapter;     /* #TODO: Ver documentação do JAVA */
import java.awt.event.MouseEvent;       /* #TODO: Ver documentação do JAVA */
import java.util.ArrayList;             /* #TODO: Ver documentação do JAVA */
import java.util.List;                  /* #TODO: Ver documentação do JAVA */
import javax.swing.JPanel;              /* #TODO: Ver documentação do JAVA */
import java.awt.Font;                   /* #TODO: Ver documentação do JAVA */
import java.awt.Image;                  /* #TODO: Ver documentação do JAVA */
import java.awt.Toolkit;                /* #TODO: Ver documentação do JAVA */
import javax.swing.ImageIcon;           /* #TODO: Ver documentação do JAVA */
import javax.swing.JButton;             /* #TODO: Ver documentação do JAVA */
import javax.swing.JPanel;              /* #TODO: Ver documentação do JAVA */
import javax.swing.JFrame;              /* #TODO: Ver documentação do JAVA */
import java.awt.event.ActionListener;   /* #TODO: Ver documentação do JAVA */
import java.awt.event.ActionEvent;      /* #TODO: Ver documentação do JAVA */
import javax.swing.*;                   /* #TODO: Ver documentação do JAVA */
import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.net.MalformedURLException; 
import java.rmi.NotBoundException; 
import java.rmi.RMISecurityManager;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class PaintImpl extends java.rmi.server.UnicastRemoteObject implements PaintInterface {

	BufferedImage off_Image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2d = off_Image.createGraphics();
	ArrayList<Quadro> quadros = new ArrayList<Quadro>();
	

	private class Quadro 
	{
		private HashTable <String, Usuario> usuarios;    /* Quantidade de Usuários no Quadro */
		public BufferedImagem imagemDoQuadro;
		public class Usuario 
		{
			Color corDoTraco = null;
			Point ultimoPonto = null;

			Usuario()
		}
	}

	public PaintImpl () throws java.rmi.RemoteException 
    {
        super ();
	}

    public int criarQuadro(String _nomeDoQuadro, String _nomeDoUsuario)
    {

    	return null; /* Provisório */
    }
    
	public int[] getArray (/* Futuramente será o nome do quadro */)
    {
    	g2d.setPaint (Color.white);
		g2d.fillRect ( 0, 0, off_Image.getWidth(), off_Image.getHeight() );
        g2d.setColor(Color.red);
        g2d.drawLine(0, 0, 100, 100);
        g2d.setColor(Color.blue);
        g2d.drawLine(0, 0, 200, 300);
        //g2d.setBackground(Color.white);
        g2d.dispose();

        ManipularImagem _manipula = new ManipularImagem(off_Image);
        System.out.println ("Chamou o getArray!");

        return _manipula.getImagem();
	}
    
    /*
    ** Classe:    ManipularImagem
    ** Descrição: Basicamente ela transforma um BufferedImage em um vetor de int
    **			  para que o mesmo possa ser enviado para o cliente e interpreta-
    **			  do corretamente pelo mesmo.
    */
    private class ManipularImagem implements Serializable
    {
        int [] _imagemArray;   /* Array que será retornado ou atribuido algum valor */
        
        ManipularImagem(BufferedImage imagem)
        {
            _imagemArray = imagem.getRGB(0, 0, 800, 600, _imagemArray, 0, 800);
        } /* Construtor Vazio */

        int [] getImagem()
        {
            return _imagemArray;
        }
    }
}