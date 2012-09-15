package classes;

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

public class criarQuadro extends JFrame
{
	public criarQuadro(String _nomeQuadro)
	{
		setTitle("Paint Distribuído || Quadro: " + _nomeQuadro);    /* Define o Título da Janela que contêm o JFrame / JPanel */
        setSize(800, 600);    /* Define o tamanho do JFrame */
        setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width  / 2) - (getWidth() / 2)),
                    ((Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (getHeight() / 2)));    /* Inicializa o frame no centro da Tela */

        JPanel _painel = new JPanel(); 		/* Cria um Painel para Desenhar 				*/
        _painel.setLayout(null);            /* Definindo nenhum layout para o Painel        */
        _painel.setSize(800, 600);          /* Definindo o tamanho do Painel                */
        _painel.setVisible(true);           /* Deixando o Painel visível                    */
        _painel.setBackground(Color.white); /* Definindo o plano de fundo com a cor branca  */

	}
}