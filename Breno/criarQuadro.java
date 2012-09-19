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
import java.io.IOException;



public class criarQuadro extends JFrame
{
	List<Point> pointList = new ArrayList<Point>();

	public criarQuadro(String _nomeDoQuadro, String _nomeDoUsuario) throws Exception
	{
		setTitle("Paint Distribuído || Usuário: " + _nomeUsuario + " Quadro: " + _nomeQuadro);    /* Define o Título da Janela que contêm o JFrame / JPanel */
        setSize(800, 600);    /* Define o tamanho do JFrame */
        setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (getWidth() / 2)),
                    ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (getHeight() / 2)));    /* Inicializa o frame no centro da Tela */

        try
        {
        	add(new Quadro(_nomeDoQuadro, _nomeDoUsuario));    /* Instancia um novo Quadro */
        }
        catch (IOException e)
		{
		  // you handle the exception here
		  System.out.println("Got an IOException: " + e.getMessage());
		}
        // catch(Exception ex)
        // {
        // 	System.out.println("Entrou no Catch da classe criarQuadro");
        // }

    }
}