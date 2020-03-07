/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maria
 */

package mg;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Description {
    
    private DBQuery dbq = new DBQuery();
    private ResultSet rs = null;
    
    public void showDescription(String prname)
    {
        
        final JFrame desc = new JFrame("Description");
        desc.setSize(500, 300);
        desc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton exit = new JButton();               
        JPanel text = new JPanel();
        JLabel desc_text = new JLabel();
        
        
        exit.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent me) {
                desc.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
        dbq.statement("SELECT description FROM products WHERE pname = '" + prname + "'");
        rs = dbq.getResultSet();
        try {
            String mytext = new String();
            while (rs.next())
            {
                mytext = rs.getString("description");
            }
            desc_text.setText(mytext);
            text.add(desc_text);
            
            JScrollPane scroll = new JScrollPane(text);
            scroll.setMinimumSize(new Dimension(500, 300));
            scroll.setPreferredSize(new Dimension(500, 300));
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            
            
            desc.add(scroll);
            desc.setVisible(true);
                   
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(null, "An error has occured!\n"+sqlex.getMessage(),
										  "Error", JOptionPane.ERROR_MESSAGE);
            }

    } 
                        
}
