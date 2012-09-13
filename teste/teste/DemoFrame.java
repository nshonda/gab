package teste;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DemoFrame extends JFrame
{
    public DemoFrame()
    {
        setSize(200, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new LinePanel(), BorderLayout.CENTER); 
        add(new JLabel("Use o mouse para desenhar"), BorderLayout.NORTH);        
    }
}
