/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.paint.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static mg.SQLCommands.orderList;
/**
 *
 * @author Maria
 */
public class ClientPanel extends javax.swing.JFrame{
    private javax.swing.JButton ClearCartButton;
    private javax.swing.JButton ClientCartButton;
    private javax.swing.JLabel ClientNameLabel;
    private javax.swing.JButton ClientOrderButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JComboBox OrderByCombo;
    private javax.swing.JComboBox OrderTypeCombo;
    private javax.swing.JButton ProductSearchButton;
    private javax.swing.JTextField ProductSearchText;
    private javax.swing.JButton ShowDiscountButton;
    private javax.swing.JButton ShowPoductsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    
    private DBQuery dbq = new DBQuery();
    private ResultSet res = null; 
    private SQLCommands comm = new SQLCommands();
    public static String orderby = new String("pname");
    public static String searchtext = new String();
    public static String ordertype = new String("ASC");
    
    public ClientPanel(){
        initComponents();
        this.setLocationRelativeTo(this);
    }

    private void initComponents() {
        
        getContentPane().setBackground(java.awt.Color.WHITE);
        
   ClientNameLabel = new javax.swing.JLabel(LogIn.user);
   OrderByCombo = new javax.swing.JComboBox();
   jLabel1 = new javax.swing.JLabel();
   jLabel2 = new javax.swing.JLabel();
   OrderTypeCombo = new javax.swing.JComboBox();
   ProductSearchText = new javax.swing.JTextField();
   ProductSearchButton = new javax.swing.JButton();
   ClientCartButton = new javax.swing.JButton();
   ClientOrderButton = new javax.swing.JButton();
   ClearCartButton = new javax.swing.JButton();
   ShowPoductsButton = new javax.swing.JButton();
   ShowDiscountButton = new javax.swing.JButton();
   EditButton = new javax.swing.JButton();
   jLabel3 = new javax.swing.JLabel();
   
   setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
   setTitle("MG SHOP");
   getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ClientNameLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        ClientNameLabel.setText(LogIn.user);
        getContentPane().add(ClientNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 33, -1, -1));
  OrderByCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Name", "Price" }));
        OrderByCombo.setSelectedIndex(0);
        OrderByCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OrderByComboItemStateChanged(evt);
            }
        });
        OrderByCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrderByComboActionPerformed(evt);
            }
        });
        getContentPane().add(OrderByCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 80, 30));
 jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel1.setText("Order by:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 70, 20));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel2.setText("Order type:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, -1, 20));

        OrderTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ASC", "DESC" }));
        OrderTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrderTypeComboActionPerformed(evt);
            }
        });
        getContentPane().add(OrderTypeCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 80, 30));
        getContentPane().add(ProductSearchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 37, 198, -1));

        ProductSearchButton.setBackground(new java.awt.Color(51-204-255));
       // ProductSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resurse/imagini/search1.png"))); // NOI18N
        ProductSearchButton.setText("Search");
        ProductSearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductSearchButtonMouseClicked(evt);
            }
        });
        getContentPane().add(ProductSearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 25, -1, 46));
        ClientCartButton.setBackground(new java.awt.Color(51-204-255));
       // ClientCartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resurse/imagini/makeup1.png"))); // NOI18N
        ClientCartButton.setText("Show Cart");
        ClientCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClientCartButtonMouseClicked(evt);
            }
        });
        getContentPane().add(ClientCartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, 237, -1));

        ClientOrderButton.setBackground(new java.awt.Color(51-204-255));
      //  ClientOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resurse/imagini/order_tracking.png"))); // NOI18N
        ClientOrderButton.setText("Order");
        ClientOrderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClientOrderButtonMouseClicked(evt);
            }
        });
        getContentPane().add(ClientOrderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 355, 143, -1));

        ClearCartButton.setBackground(new java.awt.Color(51-204-255));
       // ClearCartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resurse/imagini/cancel.png"))); // NOI18N
        ClearCartButton.setText("Clear Cart");
        ClearCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClearCartButtonMouseClicked(evt);
            }
        });
        getContentPane().add(ClearCartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 355, 149, -1));

        ShowPoductsButton.setBackground(new java.awt.Color(51-204-255));
       // ShowPoductsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resurse/Pictures/benchmarking.png"))); // NOI18N
        ShowPoductsButton.setText("Show Products");
        ShowPoductsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowPoductsButtonMouseClicked(evt);
            }
        });
        getContentPane().add(ShowPoductsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 126, 375, -1));

        ShowDiscountButton.setBackground(new java.awt.Color(51-204-255));
       // ShowDiscountButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resurse/imagini/discount1.png"))); // NOI18N
        ShowDiscountButton.setText("Show Discounts");
        ShowDiscountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowDiscountButtonMouseClicked(evt);
            }
        });
        getContentPane().add(ShowDiscountButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 246, 375, 74));

        EditButton.setBackground(new java.awt.Color(51-204-255));
       // EditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resurse/imagini/user_edit1.png"))); // NOI18N
        EditButton.setText("Edit");
        EditButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditButtonMouseClicked(evt);
            }
        });
        getContentPane().add(EditButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 25, -1, 46));

      // jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resurse/Pictures/cmo_shop.png"))); // NOI18N
        jLabel3.setText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 430));
        this.setSize(400,400);

        pack();
    }
    private void ShowPoductsButtonMouseClicked(java.awt.event.MouseEvent evt) {
        ProductList products = new ProductList();
    }

    private void ProductSearchButtonMouseClicked(java.awt.event.MouseEvent evt) {
        searchtext = ProductSearchText.getText();
        SearchList searchlist = new SearchList();
    }

    private void ClientCartButtonMouseClicked(java.awt.event.MouseEvent evt) {
        ShoppingCart myCart = new ShoppingCart();
        myCart.showCart();
    }

    private void ClearCartButtonMouseClicked(java.awt.event.MouseEvent evt) {
        for (Articles art: orderList)
        {
            dbq.statement("UPDATE stock s, products p SET cantity = cantity + 1 WHERE p.pname = '" +
                                        art.getName() + "' AND p.id = s.id_Product;");
        }
        comm.clearOrder();
    }
    private void ClientOrderButtonMouseClicked(java.awt.event.MouseEvent evt) {
        OrderPanel orderIt = new OrderPanel();
        if (!orderList.isEmpty())
        {
            orderIt.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your shopping cart is empty!");
        }
    }

    private void OrderByComboItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void OrderByComboActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == OrderByCombo)
        {
            int ind = (int) OrderByCombo.getSelectedIndex();
            if (ind == 0)
            {
                orderby = "pname";
            }
            else
            {
                orderby = "price";
            }
        }
        System.out.println(orderby);
    }

    private void OrderTypeComboActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == OrderTypeCombo)
        {
            int ind = (int) OrderTypeCombo.getSelectedIndex();
            
            if (ind == 1)
            {
                ordertype = "ASC";
            }
            else 
            {
                ordertype = "DESC";
            }
        }
        
        System.out.println(ordertype);
    }

    private void ShowDiscountButtonMouseClicked(java.awt.event.MouseEvent evt) {
        Discounts disc = new Discounts();
    }

    private void EditButtonMouseClicked(java.awt.event.MouseEvent evt) {
        EditAcc edit_acc = new EditAcc();
        edit_acc.setVisible(true);
    }
    
}
