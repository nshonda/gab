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

/*
    Classe:
        LinePanel

    Descrição:
        Criará um JPanel onde será mostrado as linhas desenhadas pelo(s)
        usuário(s).
*/

public class LinePanel extends JPanel
{
    /* Construtor */
    public LinePanel()
    {
        setLayout(null);            /* Definindo nenhum layout para o Painel        */
        setSize(643, 388);          /* Definindo o tamanho do Painel                */
        setVisible(true);           /* Deixando o Painel visível                    */
        setBackground(Color.white); /* Definindo o plano de fundo com a cor branca  */

        JButton _btnCriar  = new JButton("Criar" );             /* Criando o botão para CRIAR um novo Quadro                    */
        JButton _btnEntrar = new JButton("Entrar");             /* Criando um botão para ENTRAR em um Quadro já existente       */
        _btnCriar.setFont (new Font("Arial", Font.BOLD, 20));   /* Define a fonte como Arial, negrito, tamanho 20               */
        _btnEntrar.setFont(new Font("Arial", Font.BOLD, 20));   /* Define a fonte como Arial, negrito, tamanho 20               */
        _btnCriar.setBounds (130, 144, 136, 72);                /* Define a posição do botão (x, y, width, height)              */
        _btnEntrar.setBounds(363, 144, 136, 72);                /* Define a posição do botão (x, y, width, height)              */
        _btnCriar.addActionListener(new criar());               /* Adiciona funcionalidade de instanciar a classe criarQuadro   */
        _btnEntrar.addActionListener(new entrar());             /* Adiciona funcionalidade de instanciar a classe criarQuadro   */
        add(_btnCriar);                                         /* Adiciona o botão ao Painel                                   */
        add(_btnEntrar);                                        /* Adiciona o botão ao Painel                                   */

        // addMouseListener(new MouseAdapter()
        // {
        //     @Override
        //     public void mouseClicked(MouseEvent e)
        //     {
        //         //Após cada clique, guardamos a posição do ponto onde foi clicado.
        //         if (e.getClickCount() == 1)
        //         {
        //             pointList.add(new Point(e.getX(), e.getY()));
        //             repaint(); //E pedimos para o painel se repintar.
        //         }
        //     }
        // });
    }

    public void paintComponent(Graphics g) {        
        super.paintComponent(g);  /* Chama o construtor default da classe pai */
        Image imagem = new ImageIcon(this.getClass().getResource("Fundo_Paint.jpg")).getImage(); /* Obtem a imagem */
        g.drawImage(imagem, 0, 0, this);  /* Insere a imagem como background do Painel */
    }

    private class criar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            
            String _nomeDoQuadro;
            _nomeDoQuadro = JOptionPane.showInputDialog("Insira um nome para o quadro");
            
            try
            {
                new criarQuadro(_nomeDoQuadro).setVisible(true);    
            }
            catch(Exception e)
            {
                System.out.println("Entrou no Catch da classe criar");
            }
            
        }
        
    }

    private class entrar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
        }
        
    }

    /**
     * Esse método será chamado toda vez que o componente precisar ser repintado.
     * Isso é, quando a janela for parcial ou totalmente escondida e reexibida ou 
     * quando o paint for chamado.
     */
    // @Override
    // protected void paintComponent(Graphics g)
    // {
    //     super.paintComponent(g);
        
    //     //Não podemos alterar o estado do objeto g, portanto fazemos uma cópia dele.
    //     Graphics2D g2d = (Graphics2D) g.create();

    //     if (pointList.size() < 2)
    //         return;

    //     // Percorre a nossa lista, pintando de dois em dois pontos.
    //     Point lastPoint = pointList.get(0);
    //     for (int i = 1; i < pointList.size(); i++)
    //     {
    //         g2d.setColor(Color.red);
    //         g2d.setStroke(new BasicStroke(5.0f));
    //         g2d.drawLine(lastPoint.x, lastPoint.y, pointList.get(i).x,
    //                 pointList.get(i).y);
    //         lastPoint = pointList.get(i);
    //     }
        
    //     //E aqui destruimos a cópia do objeto g. 
    //     //Não se preocupe. O desenho feito por ele não será destruído.
    //     g2d.dispose();
    // }
}
