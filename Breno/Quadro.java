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
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Quadro extends JPanel 
{
    List<Point> pointList = new ArrayList<Point>();
    BufferedImage _imgRetornado = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
    PaintInterface _interface;
    
    public Quadro(String _nomeDoQuadro, String _nomeDoUsuario) throws Exception
    {
        setLayout(null);            /* Definindo nenhum layout para o Painel        */
        setSize(800, 600);          /* Definindo o tamanho do Painel                */
        setVisible(true);           /* Deixando o Painel visível                    */
        setBackground(Color.white); /* Definindo o plano de fundo com a cor branca  */

        /* 
        ** Instancia o objeto que cuida de toda parte de segurança sem essa instância
        ** a classe do RMI não conseguirá efetuar o download dos headers das classes
        ** remotas, assim não podendo efetuar nenhuma chamada de um método remoto
        */
        System.setSecurityManager(new RMISecurityManager());

        /*
        ** A classe Naming fornece métodos para persistência ou obtenção de referências
        ** para objetos remotos em um registro de um objeto remoto. Passando como parâ-
        ** metro o host onde os métodos remotos estão implementados.
        **
        ** _interface recebe uma referencia de um objeto do tipo Remote ( é um Stub )
        ** e é efetuado um Cast para o tipo PaintInterface, assim podemos efetuar cha-
        ** madas de métodos remotos através desta _interface.
        */
        _interface = (PaintInterface) Naming.lookup ("rmi://localhost/PaintService");

        _interface.criarQuadro(_nomeDoQuadro, _nomeDoUsuario);


        (new Thread(new ThreadQueAtualizaQuadro())).start();


        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 1)
                {
                    pointList.add(new Point(e.getX(), e.getY()));
                    //repaint();
                    try
                    {
                        System.out.println("Antes do imgRetornado");
                        _imgRetornado.setRGB(0, 0, 800, 600, _interface.getArray(), 0, 800);  
                        System.out.println("Antes do repaint()");
                        repaint();  
                        System.out.println("Passou no repaint()");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Entrou no Catch do mouseClicked - Quadro.java");
                    }
                }
            }
        });
    };


    class ThreadQueAtualizaQuadro implements Runnable
    {
        public void run()
        {
            try
            {
                while(true)
                {
                    _imgRetornado.setRGB(0, 0, 800, 600, _interface.getArray(), 0, 800);  
                    repaint();
                    Thread.sleep(500);    
                }
            }
            catch(Exception exp)
            {
                System.out.println("Erro - Catch ThreadQueAtualizaQuadro.");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        System.out.println("Passou no paintComponent");
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.drawImage(_imgRetornado, 0, 0, 800, 600, Color.white, null);
        



        // Graphics2D g2d = (Graphics2D) g.create();

        // if (pointList.size() < 2)
        //     return;

        // Point lastPoint = pointList.get(0);
        // for (int i = 1; i < pointList.size(); i++)
        // {
        //     g2d.drawLine(lastPoint.x, lastPoint.y, pointList.get(i).x,
        //             pointList.get(i).y);
        //     lastPoint = pointList.get(i);
        // }
        
        // g2d.dispose();
    }
}