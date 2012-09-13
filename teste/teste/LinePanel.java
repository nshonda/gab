package teste;

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
                if (e.getClickCount() == 1)
                {
                    pointList.add(new Point(e.getX(), e.getY()));
                    repaint(); 
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
