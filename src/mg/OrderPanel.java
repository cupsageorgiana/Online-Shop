/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;

import static mg.LogIn.user;
import static mg.SQLCommands.orderList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static mg.LogIn.user;
import static mg.SQLCommands.orderList;
/**
 *
 * @author Maria
 */
public class OrderPanel extends javax.swing.JFrame{
    public static String payment = new String("PayPal");
    
    private javax.swing.JButton BuyButton;
    private javax.swing.JComboBox PaymentCombo;
    private javax.swing.JLabel TotalPriceLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private ResultSet rs = null;
  
    public OrderPanel() {
        initComponents();
    }
private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TotalPriceLabel = new javax.swing.JLabel();
        PaymentCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        BuyButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUY");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

      //  jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/makeup.png"))); 
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 340));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        jLabel1.setText("Total Price:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        TotalPriceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        SQLCommands comm = new SQLCommands();

        int sum = 0;

        for (Articles art: comm.getOrderList())
        {
            sum += art.getPrice();
        }
        TotalPriceLabel.setText(Integer.toString(sum) + "LEI");
        getContentPane().add(TotalPriceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        PaymentCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PayPal", "CreditCard", "WePay", "Visa" }));
        PaymentCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentComboActionPerformed(evt);
            }
        });
        getContentPane().add(PaymentCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 92, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Payment method:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        BuyButton.setBackground(new java.awt.Color(51-204-255));
        BuyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/buy1.png"))); 
        BuyButton.setText("Buy");
        BuyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuyButtonMouseClicked(evt);
            }
        });
        getContentPane().add(BuyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 174, -1));

        pack();
    }
 private void BuyButtonMouseClicked(java.awt.event.MouseEvent evt) {
       
        DBQuery bdq = new DBQuery();
        
        if (!payment.equalsIgnoreCase("CreditCard"))   
        { 
            
            int one = 1;
            for (Articles art: orderList)
            {
                bdq.callProcedure("{call populateOrders('" +  art.getName() + "' ,'" + one + "', '" + user + "', '" +
                                    one  + "')}");
            }
            bdq.statement("UPDATE orders SET id_Pay = '" + PaymentCombo.getSelectedIndex() + "' WHERE id_Customer = '" + user + "'");
            orderList.clear();
            JOptionPane.showMessageDialog(null, "Thank you for buying!");
            this.setVisible(false);
        }
        else
        {
            bdq.statement("SELECT b.id FROM bank_account b, customers c WHERE c.username = '" + user + "' AND c.id = b.id_Customer");
            rs = bdq.getResultSet();
            int succes = 0; 
            
            try {
                while (rs.next())
                {
                    succes = 1;
                }
            } catch (SQLException ex) {
                succes = 0;
            }
            
            if (succes == 0)
            {
                CreditCard cr = new CreditCard();
                cr.setVisible(true);  
            }
            else
            {
                int one = 1;
                for (Articles art: orderList)
                {
                    bdq.callProcedure("{call populateOrders('" +  art.getName() + "' ,'" + one + "', '" + user + "', '" +
                                    one  + "')}");
                }
                bdq.statement("UPDATE orders SET id_Pay = '" + PaymentCombo.getSelectedIndex() + "' WHERE id_Customer = '" + user + "'");
                orderList.clear();
                JOptionPane.showMessageDialog(null, "Thank you for buying!");
                this.setVisible(false);
            }
        }
    }
  private void PaymentComboActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == PaymentCombo)
        {
            payment = (String) PaymentCombo.getSelectedItem();
        }
    }
}
