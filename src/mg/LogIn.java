package mg;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maria
 */
public class LogIn extends javax.swing.JFrame {
    
    private javax.swing.JButton CreateAccButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPasswordField PasswordText;
    private javax.swing.JTextField UsernameText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    
    SQLCommands db = new SQLCommands();
    public static String user="Unknown";
    
    public LogIn(){
        initComponents();
        this.setLocationRelativeTo(this);
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

         getContentPane().setBackground(java.awt.Color.WHITE);
        jLabel1 = new javax.swing.JLabel();
        UsernameText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        PasswordText = new javax.swing.JPasswordField();
        CreateAccButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Log In");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Username:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 65, 22));

        UsernameText.setNextFocusableComponent(PasswordText);
        UsernameText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UsernameTextMouseClicked(evt);
            }
        });
        UsernameText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UsernameTextFocusGained(evt);
            }
        });
        getContentPane().add(UsernameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 173, -1));

        jLabel2.setText("Password:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 70, -1));

        LoginButton.setBackground(new java.awt.Color(51-204-255));
         
        LoginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/loging1.png"))); 
        LoginButton.setText("Login");
        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginButtonMouseClicked(evt);
            }
        });
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        LoginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LoginButtonKeyPressed(evt);
            }
        });
        getContentPane().add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 100, 40));

        ExitButton.setBackground(new java.awt.Color(51-204-255));
        ExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/logout1.png"))); 
        ExitButton.setText("Exit");
        ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitButtonMouseClicked(evt);
            }
        });
        ExitButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ExitButtonKeyPressed(evt);
            }
        });
        getContentPane().add(ExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 100, -1));

        PasswordText.setNextFocusableComponent(LoginButton);
        PasswordText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PasswordTextMouseClicked(evt);
            }
        });
        getContentPane().add(PasswordText, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 173, -1));

        CreateAccButton.setBackground(new java.awt.Color(51-204-255));
       CreateAccButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/user_add1.png"))); 
        CreateAccButton.setText("Create Account");
        CreateAccButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreateAccButtonMouseClicked(evt);
            }
        });
        getContentPane().add(CreateAccButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 170, 50));

       jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/m21.png"))); 
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, -1));

        pack();
    }
    private void UsernameTextFocusGained(java.awt.event.FocusEvent evt){
        UsernameText.setFocusable(true);
    }
    
    private void LoginButtonMouseClicked(java.awt.event.MouseEvent evt){
        String username = new String();
        String password = new String();
        username = UsernameText.getText();
        password = PasswordText.getText();
        ClientPanel cp = new ClientPanel();
        
         if (db.verifUserPass(username,password))
        {
            LogIn.user = UsernameText.getText();
            new ClientPanel().setVisible(true);      
        }
        else 
        {
          
            JOptionPane.showMessageDialog(null, "Wrong username or password!");
        }
    }
         
        private void ExitButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }
          private void UsernameTextMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        UsernameText.setText("");
    }

    private void PasswordTextMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        PasswordText.setText("");
    }

    private void LoginButtonKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
            String username = new String();
        String password = new String();
        username = UsernameText.getText();
        password = PasswordText.getText();
        
        if(db.verifUserPass(username,password)){
            LogIn.user = UsernameText.getText();
            new ClientPanel().setVisible(true);
        }
    }
    }
    private void ExitButtonKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) 
        {
           System.exit(0);
        }
    }
    private void CreateAccButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        CreateAccount acc = new CreateAccount();
        acc.setVisible(true);
      
    }
    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
