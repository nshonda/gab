package classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class LinePanel extends JPanel
{
    List<Point> pointList = new ArrayList<Point>();

    public LinePanel()
    {
        
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                //Após cada clique, guardamos a posição do ponto onde foi clicado.
                if (e.getClickCount() == 1)
                {
                    pointList.add(new Point(e.getX(), e.getY()));
                    repaint(); //E pedimos para o painel se repintar.
                }
            }
        });
    };

    /**
     * Esse método será chamado toda vez que o componente precisar ser repintado.
     * Isso é, quando a janela for parcial ou totalmente escondida e reexibida ou 
     * quando o paint for chamado.
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //Não podemos alterar o estado do objeto g, portanto fazemos uma cópia dele.
        Graphics2D g2d = (Graphics2D) g.create();

        if (pointList.size() < 2)
            return;

        // Percorre a nossa lista, pintando de dois em dois pontos.
        Point lastPoint = pointList.get(0);
        for (int i = 1; i < pointList.size(); i++)
        {
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(5.0f));
            g2d.drawLine(lastPoint.x, lastPoint.y, pointList.get(i).x,
                    pointList.get(i).y);
            lastPoint = pointList.get(i);
        }
        
        //E aqui destruimos a cópia do objeto g. 
        //Não se preocupe. O desenho feito por ele não será destruído.
        g2d.dispose();
    }
}
