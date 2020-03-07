/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;

import static mg.SQLCommands.orderList;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import static mg.SQLCommands.orderList;
/**
 *
 * @author Maria
 */
public class ShoppingCart {
    public void showCart()
    {
        JFrame mf = new JFrame("Products");
       
        
        mf.setSize(400, 200);
        mf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel listArticole = new JPanel();
        
        listArticole = new JPanel();
	listArticole.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	listArticole.setLayout(new GridLayout(0, 2));
	listArticole.setBounds(10, 50, 100, 150);
        
       // Iterator it = orderList.iterator();
        if (!(orderList.isEmpty())){
            for (Articles art: orderList)
            {
                JLabel p_name = new JLabel();
                p_name.setText(art.getName());
                JLabel p_price = new JLabel();
                p_price.setText(Float.toString(art.getPrice()) + "LEI");
                listArticole.add(p_name);
                listArticole.add(p_price);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your shopping cart is empty!");
        }
        
        JScrollPane scroll = new JScrollPane(listArticole);
        scroll.setMinimumSize(new Dimension(160, 200));
        scroll.setPreferredSize(new Dimension(160, 200));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mf.add(scroll);

        if (!orderList.isEmpty()) mf.setVisible(true);
    }
}
