/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;


import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static mg.ClientPanel.orderby;
import static mg.ClientPanel.ordertype;
import static mg.ClientPanel.searchtext;

/**
 *
 * @author Zoli
 */
public class SQLCommands {
    
	private DBQuery dbq = new DBQuery();
        private ResultSet rs = null;
        private ResultSet rs_aux = null;
   public static ArrayList<Articles> orderList = new ArrayList<Articles>();
        public static boolean SearchDiscVisibel = true;

       
	public boolean verifUserPass(String tfUserText, String pfPassText)
	{
		boolean aux;
		
		dbq.statement("SELECT username, pass FROM customers WHERE username = '" + tfUserText + "'"
					+ " and pass = '" + pfPassText + "'");
		
		
		aux = dbq.isResultSet();
		dbq.CloseStatement();
		return aux;
	}
       
        
        public void insertUser(String nume, String username, String password, String email, String tel, String city ) 
	{		
            
            dbq.statement("INSERT INTO customers(email, tel, username, pass, city, str, nr, nume) VALUES('" + email + "' ,'" + tel + "', '" + username + "', '" +
                    password + "', '" + city + "', '" + nume + "')");
            dbq.CloseStatement();
           /* if (rs != null)
                JOptionPane.showMessageDialog(null, "Your account has been created!",
                        "Succes", JOptionPane.PLAIN_MESSAGE);*/
            
        }
        
        public int insertUserProc(String nume, String username, String password, String email, String tel, String city ) 
	{		
            int succes;
            succes = dbq.callProcedure("{call populateCustomers('" + email + "' ,'" + tel + "', '" + username + "', '" +
                    password + "', '" + city + "', '" + nume + "')}");
            
            return succes;
        }
        
       public ArrayList<JPanel> getInfo()
        {
        final DBQuery dbq = new DBQuery();
        dbq.statement("SELECT * FROM products ORDER BY '" + orderby + "' '" + ordertype + "'" );
        rs = dbq.getResultSet();
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        
        try {
            while (rs.next())
            {
                int succes;
                final String[] pname = new String[1];
                final int one = 1;
                final float[] price = new float[1];
                
                
                JPanel paux = new JPanel();
                JLabel nume = new JLabel();
              
                pname[0] = rs.getString("pname");
               
                nume.setText(pname[0]);
                JLabel pret = new JLabel();
                price[0] = rs.getFloat("price");
                pret.setText(Float.toString(price[0])+ "LEI");
                JButton addButton = new JButton();
                addButton.setText("Add");
                
               //addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/add-icon.png")));
                JButton descButton = new JButton();
                descButton.setText("Detail");
               // descButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/info.png")));
                
                 
                descButton.addMouseListener(new MouseListener()
                {
                   
                        
                    public void mouseClicked(MouseEvent me) {
                        Description desc = new Description();
                        desc.showDescription(pname[0]);
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
               
                addButton.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent me) {
                      
                        int succes = 0;
                      
                        dbq.statement("SELECT s.cantity AS nr FROM stock s, products p WHERE p.pname = '" + pname[0] + 
                                "' AND p.id = s.id_Product;" );
                        
                        try {
                            rs_aux = dbq.getResultSet();
                            while (rs_aux.next())
                            {
                                succes = (int) rs_aux.getInt("nr");
                            }
                            if (succes > 0)
                            {
                                orderList.add(new Articles(pname[0],price[0]));
                                dbq.statement("UPDATE stock s, products p SET cantity = cantity - 1 WHERE p.pname = '" +
                                        pname[0] + "' AND p.id = s.id_Product;");
                                JOptionPane.showMessageDialog(null, "You have added a product to your shopping cart!");
                            }
                            else 
                            {
                                JOptionPane.showMessageDialog(null, "Too few products! You can try it later!");
                            }
                        } catch (SQLException sqlex) {
                            JOptionPane.showMessageDialog(null, "An error has occured!\n"+sqlex.getMessage(),
										  "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        
                     
                    }
                    @Override
                    public void mousePressed(MouseEvent me) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                       //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                       //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
               
                paux.add(nume);
                paux.add(pret);
              
                paux.add(descButton);
                paux.add(addButton);
                
                panels.add(paux);
              
                
                System.out.print(rs.getString("pname") + " ");
                System.out.println(rs.getFloat("price"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No data!",
					  "Error", JOptionPane.ERROR_MESSAGE);
        }        
        
        return panels;   
    }
        
         void addArticles(JPanel panel)
    {
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        panels = getInfo();

        Iterator iterator = panels.iterator();
        if (panels.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No data",
					  "Error", JOptionPane.ERROR_MESSAGE);
            SearchDiscVisibel = false;
        }
        else
        {
            while(iterator.hasNext()){
            panel.add((JPanel) iterator.next());
            }
            SearchDiscVisibel = true;
        }
    }
    
         
    ////////////////////////////////////////////////////////////////////////////
         
         
        public ArrayList<JPanel> getSearchInfo()
        {
        final DBQuery dbq = new DBQuery();
        System.out.println(orderby);
        System.out.println(ordertype);
        dbq.statement("SELECT * FROM products WHERE pname LIKE  '%" + searchtext + "%' ORDER BY '" + orderby + "' '" + ordertype + "'");
        rs = dbq.getResultSet();
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        
        try {
            while (rs.next())
            {
                int succes;
                final String[] pname = new String[1];
                final int one = 1;
                final float[] price = new float[1];
                
                
                JPanel paux = new JPanel();
                JLabel nume = new JLabel();
              
                pname[0] = rs.getString("pname");
              
                nume.setText(pname[0]);
                JLabel pret = new JLabel();
                price[0] = rs.getFloat("price");
                pret.setText(Float.toString(price[0])+ "LEI");
                JButton addButton = new JButton();
                addButton.setText("Add");
               // addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/add-icon.png")));
                JButton descButton = new JButton();
                descButton.setText("Detail");
                //descButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/info.png")));
                 
                descButton.addMouseListener(new MouseListener()
                {
                   
                        
                    public void mouseClicked(MouseEvent me) {
                        Description desc = new Description(); 
                        desc.showDescription(pname[0]);
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
               
                addButton.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent me) {
                      
                        int succes = 0;
                      
                       dbq.statement("SELECT s.cantity AS nr FROM stock s, products p WHERE p.pname = '" + pname[0] +
                                "' AND p.id = s.id_Product" );
                        
                        try {
                            succes = dbq.getResultSet().getInt("nr");
                            rs_aux = dbq.getResultSet();
                            while (rs_aux.next())
                            {
                                succes = (int) rs_aux.getInt("nr");
                            }
                            if (succes > 0)
                            {
                                orderList.add(new Articles(pname[0],price[0]));
                                dbq.statement("UPDATE stock s, products p SET cantity = cantity - 1 WHERE p.pname = '" +
                                        pname[0] + "' AND p.id = s.id_Product;");
                                JOptionPane.showMessageDialog(null, "You have added a product to your shopping cart!");
                            }
                            else 
                            {
                                JOptionPane.showMessageDialog(null, "Too few products! You can try it later!");
                            }
                        } catch (SQLException sqlex) {
                            JOptionPane.showMessageDialog(null, "An error has occured!\n"+sqlex.getMessage(),
										  "Error", JOptionPane.ERROR_MESSAGE);
                        } 
                   
                    }
                    @Override
                    public void mousePressed(MouseEvent me) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                       //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                       //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                
                
                paux.add(nume);
                paux.add(pret);
             
                paux.add(descButton);
                paux.add(addButton);
                
                panels.add(paux);
               
                System.out.print(rs.getString("pname") + " ");
                System.out.println(rs.getFloat("price"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No data!",
					  "Error", JOptionPane.ERROR_MESSAGE);
        }        
        
        return panels;   
    }
        
         void addSearchResult(JPanel panel)
    {
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        panels = getSearchInfo();

        Iterator iterator = panels.iterator();
        if (panels.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No data",
					  "Error", JOptionPane.ERROR_MESSAGE);
            SearchDiscVisibel = false;
        }
        else
        {
            while(iterator.hasNext()){
            
            panel.add((JPanel) iterator.next());
            }
            SearchDiscVisibel = true;
        }
        
        
    }
         
         public ArrayList<Articles> getOrderList()
         {
             return orderList;
         }
         
         public void clearOrder()
         {
             orderList.clear();
         }
         
         
    ///////////////////////////////////////////////////////////////////////////////////////
         
         
         public ArrayList<JPanel> getDiscountInfo()
        {
        final DBQuery dbq = new DBQuery();
        dbq.statement("SELECT d.new_price AS new_p, p.pname FROM products p, discounts d WHERE p.id = d.id_Product ORDER BY '" + orderby + "' '" + ordertype + "'");
        rs = dbq.getResultSet();
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        
        try {
            while (rs.next())
            {
                int succes;
                final String[] pname = new String[1];
                final int one = 1;
                final float[] price = new float[1];
                
                
                JPanel paux = new JPanel();
                JLabel nume = new JLabel();
            
                pname[0] = rs.getString("pname");
               
                nume.setText(pname[0]);
                JLabel pret = new JLabel();
                price[0] = rs.getFloat("new_p");
                pret.setText(Float.toString(price[0])+ "LEI");
                JButton addButton = new JButton();
                addButton.setText("Add");
               // addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/add_icon.png")));
                JButton descButton = new JButton();
                descButton.setText("Detail");
                //descButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imagini/info.png")));
                 
                descButton.addMouseListener(new MouseListener()
                {
                   
                        
                    public void mouseClicked(MouseEvent me) {
                        Description desc = new Description(); 
                        desc.showDescription(pname[0]);
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
                
               
                addButton.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent me) {
                      
                        int succes = 0;
                     
                       dbq.statement("SELECT s.cantity AS nr FROM stock s, products p WHERE p.pname = '" + pname[0] +
                                "' AND p.id = s.id_Product" );
                        
                        try {
                            succes = dbq.getResultSet().getInt("nr");
                            rs_aux = dbq.getResultSet();
                            while (rs_aux.next())
                            {
                                succes = (int) rs_aux.getInt("nr");
                            }
                            if (succes > 0)
                            {
                                orderList.add(new Articles(pname[0],price[0]));
                                dbq.statement("UPDATE stock s, products p SET cantity = cantity - 1 WHERE p.pname = '" +
                                        pname[0] + "' AND p.id = s.id_Product;");
                                JOptionPane.showMessageDialog(null, "You have added a product to your shopping cart!");
                            }
                            else 
                            {
                                JOptionPane.showMessageDialog(null, "Too few products! You can try it later!");
                            }
                        } catch (SQLException sqlex) {
                            JOptionPane.showMessageDialog(null, "An error has occured!\n"+sqlex.getMessage(),
										  "Error", JOptionPane.ERROR_MESSAGE);
                        } 
                        
                    }
                    @Override
                    public void mousePressed(MouseEvent me) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                       //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                         //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                       //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                
                
                paux.add(nume);
                paux.add(pret);
              
                paux.add(descButton);
                paux.add(addButton);
                
                panels.add(paux);
              
                
                System.out.print(rs.getString("pname") + " ");
                System.out.println(rs.getFloat("price"));
            }
        } catch (SQLException ex) {
      
        }        
        
        return panels;   
    }
        
         void addDiscountResult(JPanel panel)
    {
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        panels = getDiscountInfo();

        Iterator iterator = panels.iterator();
        if (panels.isEmpty())
        {
            SearchDiscVisibel = false;
            JOptionPane.showMessageDialog(null, "No data",
					  "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            while(iterator.hasNext()){
            
            panel.add((JPanel) iterator.next());
            }
            SearchDiscVisibel = true;
        }
    }


    public void updateUserProc(String nume, String username, String password, String email, String tel,  String city)
    {

        dbq.statement("UPDATE customers SET email = '" + email + "' , tel = '" + tel + "', username = '" + username + "', pass = '" +
                password + "', city = '" + city + "', nume = '" + nume + "'");


    }
}