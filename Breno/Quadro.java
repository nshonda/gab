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


public class Quadro extends JPanel 
{
	List<Point> pointList = new ArrayList<Point>();
	
	
	
	public Quadro() throws Exception
	{
		setLayout(null);            /* Definindo nenhum layout para o Painel        */
        setSize(800, 600);          /* Definindo o tamanho do Painel                */
        setVisible(true);           /* Deixando o Painel visível                    */
        setBackground(Color.white); /* Definindo o plano de fundo com a cor branca  */

        System.setSecurityManager(new RMISecurityManager());
        final PaintInterface _interface;
        _interface = (PaintInterface) Naming.lookup ("rmi://localhost/PaintService");

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 1)
                {
                    pointList.add(new Point(e.getX(), e.getY()));
                    repaint();
                    try
                    {
                        _interface.getArray("Testando", "Testando");    
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Entrou no Catch do mouseClicked - Quadro.java");
                    }
                    
                }
            }
        });
    };

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();

        if (pointList.size() < 2)
            return;

        Point lastPoint = pointList.get(0);
        for (int i = 1; i < pointList.size(); i++)
        {
            g2d.drawLine(lastPoint.x, lastPoint.y, pointList.get(i).x,
                    pointList.get(i).y);
            lastPoint = pointList.get(i);
        }
        
        g2d.dispose();
    }
}