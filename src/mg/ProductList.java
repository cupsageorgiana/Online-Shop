/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import static mg.SQLCommands.SearchDiscVisibel;
/**
 *
 * @author Maria
 */
public class ProductList extends JFrame{
    
    private SQLCommands comm = new SQLCommands();
    private ResultSet res = null; 
    
    public ProductList(){
        DBConnect dbc = new DBConnect();
        DBQuery dbq = new DBQuery();
        dbc.connect();
         getContentPane().setBackground(java.awt.Color.WHITE);
        JFrame mf = new JFrame("Products");
        
        mf.setSize(500,700);
        mf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mf.setBackground(new java.awt.Color(51-204-255));
        
        JPanel listArticole = new JPanel();
        JPanel menu = new JPanel();
        JLabel lblClient = new JLabel();
        
         listArticole = new JPanel();
	listArticole.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	listArticole.setLayout(new GridLayout(0, 1));
	listArticole.setBounds(10, 50, 100, 150);
     ClientPanel cp = new ClientPanel();
        
        comm.addArticles(listArticole);
        JScrollPane scroll = new JScrollPane(listArticole);
        scroll.setMinimumSize(new Dimension(160, 200));
        scroll.setPreferredSize(new Dimension(160, 200));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mf.add(scroll);
        
        mf.setVisible(SearchDiscVisibel);
    }
    
}
