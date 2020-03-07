/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;

import static mg.SQLCommands.orderList;
import javax.swing.JOptionPane;
import static mg.LogIn.user;
import static mg.SQLCommands.orderList;

/**
 *
 * @author Maria
 */
public class CreditCard extends javax.swing.JFrame{
    private  String AccNum = new String();
    private javax.swing.JTextField BancAccNumberText;
    private javax.swing.JButton BancAccSubmitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public CreditCard() {
        initComponents();
    }
    
    private void initComponents() {

        getContentPane().setBackground(java.awt.Color.WHITE);
        jLabel2 = new javax.swing.JLabel();
        BancAccNumberText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BancAccSubmitButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Credit Card Number Validation");
        setBackground(new java.awt.Color(51-204-255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BancAccNumberText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BancAccNumberTextActionPerformed(evt);
            }
        });
        getContentPane().add(BancAccNumberText, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 34, 267, 33));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Enter your bank account number");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 33, -1, 33));

       // BancAccSubmitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/submit.png"))); // NOI18N
        BancAccSubmitButton.setText("Submit");
        BancAccSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BancAccSubmitButtonMouseClicked(evt);
            }
        });
        getContentPane().add(BancAccSubmitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 140, 40));

     //   jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/creditcard.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, 109));

        pack();
    }
    private void BancAccNumberTextActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void BancAccSubmitButtonMouseClicked(java.awt.event.MouseEvent evt) {
        AccNum = BancAccNumberText.getText();
        DBQuery bdq = new DBQuery();
        
        int succes = bdq.callProcedure("{call populateBankAccount('" +  user + "' ,'" + AccNum + "')}");
            if (succes == 1)
            {
                int two = 2;
                int one = 1;
                for (Articles art: orderList)
                {
                    bdq.callProcedure("{call populateOrders('" +  art.getName() + "' ,'" + one + "', '" + user + "', '" +
                                    one  + "')}");
                }
                bdq.statement("UPDATE orders SET id_Pay = '" + two + "' WHERE id_Customer = '" + user + "'");
                orderList.clear();
                JOptionPane.showMessageDialog(null, "Thank you for buying!");
                this.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Insert a valid bank account number!",
										  "Help", JOptionPane.PLAIN_MESSAGE);
            }
        this.setVisible(false);
        super.setVisible(false);
    }
     public String getAccNum()
    {
        return AccNum;
    }
}
