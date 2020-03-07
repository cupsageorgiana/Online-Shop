/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Maria
 */
public class CreateAccount extends javax.swing.JFrame{
    
    SQLCommands dbq = new SQLCommands();
    
    private javax.swing.JTextField AccCityText;
    private javax.swing.JTextField AccEmailText;
    private javax.swing.JButton AccExitButton;
    private javax.swing.JTextField AccNameText;
    private javax.swing.JTextField AccNrText;
    private javax.swing.JTextField AccPassText;
    private javax.swing.JTextField AccStrText;
    private javax.swing.JTextField AccTelText;
    private javax.swing.JTextField AccUserText;
    private javax.swing.JButton CreateAccButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    
    public CreateAccount(){
        initComponents();
        this.setLocationRelativeTo(this);
       
        AccCityText.setText("");
        AccUserText.setText("");
        AccPassText.setText("");
        AccEmailText.setText("");
        AccTelText.setText("");
        AccNameText.setText("");
    }
    
    @SuppressWarnings("unchecked")
     private void initComponents() {

          getContentPane().setBackground(java.awt.Color.WHITE);
        AccExitButton = new javax.swing.JButton();
        AccEmailText = new javax.swing.JTextField();
        AccCityText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CreateAccButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        AccNameText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        AccStrText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        AccPassText = new javax.swing.JTextField();
        AccUserText = new javax.swing.JTextField();
        AccNrText = new javax.swing.JTextField();
        AccTelText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Account");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AccExitButton.setBackground(new java.awt.Color(51-204-255));
//        AccExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Pictures/Log Out.png"))); 
        AccExitButton.setText("Exit");
        AccExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AccExitButtonMouseClicked(evt);
            }
        });
        AccExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccExitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(AccExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 130, 50));

        AccEmailText.setNextFocusableComponent(AccCityText);
        getContentPane().add(AccEmailText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 143, -1));

        //AccCityText.setNextFocusableComponent(AccStrText);
       getContentPane().add(AccCityText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 143, -1));

        jLabel1.setText("Username:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 295, -1, -1));

        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 335, -1, -1));

        CreateAccButton.setBackground(new java.awt.Color(51-204-255));
       // CreateAccButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/add-icon.png"))); // NOI18N
        CreateAccButton.setText("Create");
        CreateAccButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreateAccButtonMouseClicked(evt);
            }
        });
        CreateAccButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateAccButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CreateAccButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 130, 50));

        jLabel3.setText("Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        AccNameText.setNextFocusableComponent(AccEmailText);
        getContentPane().add(AccNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 143, -1));

        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 165, -1, -1));

       getContentPane().add(AccTelText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 143, -1));

        jLabel5.setText("City");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 205, -1, -1));

        jLabel6.setText("Telephone");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        AccPassText.setNextFocusableComponent(CreateAccButton);
        AccPassText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AccPassTextMouseClicked(evt);
            }
        });
        getContentPane().add(AccPassText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 143, -1));

        AccUserText.setNextFocusableComponent(AccPassText);
        AccUserText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AccUserTextMouseClicked(evt);
            }
        });
        getContentPane().add(AccUserText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 143, -1));


        AccTelText.setNextFocusableComponent(AccUserText);
        AccTelText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AccTelTextMouseClicked(evt);
            }
        });
        getContentPane().add(AccTelText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 245, 143, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); 
        jLabel9.setText("Create Account");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

     //   jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/make.png"))); 
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 510));

        pack();
    }
    
    private void AccExitButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        this.setVisible(false);
    }
        private void CreateAccButtonMouseClicked(java.awt.event.MouseEvent evt) {
        int succes;
        
        if ( AccPassText.getText() == "")
        {
            JOptionPane.showMessageDialog(null,"An error occured! Please fill in all the fields!","Error",JOptionPane.ERROR_MESSAGE);

        }
        else
        {
        try {    
            succes = dbq.insertUserProc(AccNameText.getText(), AccUserText.getText(), AccPassText.getText(), AccEmailText.getText(), AccTelText.getText(),
                 AccCityText.getText());
            if (succes == 1)
            {
                JOptionPane.showMessageDialog(null,"Your account has been created succesfully!");
                this.setVisible(false);
            }
      
        }
        catch (NumberFormatException  e)
        {
            JOptionPane.showMessageDialog(null,"An error occured! Please enter a number for the nr field!","Error",JOptionPane.ERROR_MESSAGE);
            AccNrText.setText(null);
        }
        }
    }
   
    private void AccPassTextMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }
    private void AccUserTextMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void AccTelTextMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void AccExitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void CreateAccButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
}
