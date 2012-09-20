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
import java.util.Hashtable;

public class PaintImpl extends java.rmi.server.UnicastRemoteObject implements PaintInterface {

	BufferedImage off_Image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2d = off_Image.createGraphics();
	Hashtable<String, Quadro> quadros = new Hashtable<String, Quadro>();
	
	private class Quadro 
	{
		Color[] cores;    								/* Vetores de Cores disponíveis para desenhar 						   								 */
		private Hashtable <String, Usuario> usuarios;	/* Quantidade de Usuários no Quadro 								   								 */
		public BufferedImage imagemDoQuadro;			/* Imagem do quadro, com as retas desenhadas de cada usuário do quadro 								 */
		public int ultimaCorAtribuida;					/* Última cor atribuída para um usuário, caso o número de usuários exceda 10, haverá cores repetidas */

		Quadro()
		{
			ultimaCorAtribuida = -1;
			setCores();  																/* Atribui ao vetor, as cores disponíveis para desenhar no quadro. */
			usuarios = new Hashtable();													/* Instancia a tabela Hash que armazenará os usuários do quadro.   */
			imagemDoQuadro = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);	/* Imagem que será compartilhada por todos os usuários do quadro.  */
			Graphics2D xpto = imagemDoQuadro.createGraphics();
			xpto.setPaint (Color.white);
			xpto.fillRect ( 0, 0, imagemDoQuadro.getWidth(), imagemDoQuadro.getHeight());
			xpto.dispose();
		}

		void setCores()
		{
			cores = new Color[10]; 
			cores[0] = Color.BLACK; cores[1] = Color.RED;    cores[2] = Color.BLUE;      cores[3] = Color.GREEN;      cores[4] = Color.ORANGE;
			cores[5] = Color.PINK;  cores[6] = Color.YELLOW; cores[7] = Color.DARK_GRAY; cores[8] = Color.LIGHT_GRAY; cores[9] = Color.CYAN;
		}
	}

	private class Usuario 
	{
		Color corDoTraco;	/* Cor das retas que serão desenhadas pelo usuário. */

		Usuario(Color corEscolhida)
		{
			corDoTraco = corEscolhida;		/* Atribui uma cor que esteja disponível 													*/
			//ultimoPonto.setLocation(x,y);	/* Guarda o último ponto desenllhado para dar continuidade no desenho no próximo clique. 	*/
		}
	}

	public void desenharReta(String _nomeDoQuadro, String _nomeDoUsuario, int _CliqueX, int _CliqueY, int ultimoCliqueX, int ultimoCliqueY)
	{
		Quadro quadroTemp = quadros.get(_nomeDoQuadro);
		Usuario usuarioTemp = quadroTemp.usuarios.get(_nomeDoUsuario);
		
		Graphics2D xpto = quadroTemp.imagemDoQuadro.createGraphics();
		xpto.setPaint (usuarioTemp.corDoTraco);
		xpto.drawLine(_CliqueX, _CliqueY, ultimoCliqueX, ultimoCliqueY);
		xpto.dispose();
	}

	public int criarQuadro(String _nomeDoQuadro, String _nomeDoUsuario)
    {
    	try
    	{
    		if(quadros.get(_nomeDoQuadro) == null)
    		{
    			Quadro quadroCriado = new Quadro(_nomeDoQuadro, _nomeDoUsuario);														  		/* Instancia um novo quadro 							*/
	    		quadroCriado.ultimaCorAtribuida = quadroCriado.ultimaCorAtribuida + 1; 					  	    /* Incrementa +1 na última cor atribuída à um usuário 	*/
	    		Usuario usuarioCriado = new Usuario(quadroCriado.cores[quadroCriado.ultimaCorAtribuida % 10]);	/* Instancia um novo cliente							*/
	    	
		    	quadroCriado.usuarios.put(_nomeDoUsuario, usuarioCriado);
		    	quadros.put(_nomeDoQuadro, quadroCriado);

    			return 1;
    		}
    		else
    		{
    			if(quadros.usuario.get(_nomeDoUsuario) == null)
    			{
    				Usuario usuarioCriado = new Usuario(quadroCriado.cores[quadroCriado.ultimaCorAtribuida % 10]);	/* Instancia um novo cliente							*/
    				return 1;	
    			}
    			
	    		

    		}
	    	
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }

	public PaintImpl () throws java.rmi.RemoteException 
    {
        super ();
	}
    
	public int[] getImagem (String _nomeDoQuadro)
    {
    	Quadro quadroTemp = quadros.get(_nomeDoQuadro);
        ManipularImagem _manipula = new ManipularImagem(quadroTemp.imagemDoQuadro);
        System.out.println ("Chamou o getImagem do quadro => " + _nomeDoQuadro);
        return _manipula.getImagem();

		// g2d.setPaint (Color.white);
		// g2d.fillRect ( 0, 0, off_Image.getWidth(), off_Image.getHeight() );
		// g2d.setColor(Color.red);
		// 
		// g2d.setColor(Color.blue);
		// g2d.drawLine(0, 0, 200, 300);
		// g2d.dispose();
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
        }

        int [] getImagem()
        {
            return _imagemArray;
        }
    }
}