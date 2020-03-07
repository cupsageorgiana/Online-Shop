/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg;
import java.awt.Color;
import static mg.SQLCommands.SearchDiscVisibel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import org.netbeans.lib.awtextra.AbsoluteLayout;
/**
 *
 * @author Maria
 */
public class SearchList {
    private SQLCommands comm = new SQLCommands();
    private ResultSet res = null;
    private ClientPanel cl = new ClientPanel();
    
    public SearchList(){
        DBConnect dbc = new DBConnect();
        DBQuery dbq = new DBQuery();
        dbc.connect();
        
        JFrame mf = new JFrame("Search Result");
         mf.setBackground(Color.white);
        
        mf.setSize(500,250);
        mf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
      //  mf.setBackground(new java.awt.Color(51-204-255));
        
        JPanel listArticole = new JPanel();
        JPanel menu = new JPanel();
        JLabel lblClient = new JLabel();
        
        listArticole = new JPanel();
	listArticole.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	listArticole.setLayout(new GridLayout(0, 1));
	listArticole.setBounds(10, 50, 100, 150);
        
         comm.addSearchResult(listArticole);
        JScrollPane scroll = new JScrollPane(listArticole);
        scroll.setMinimumSize(new Dimension(160, 200));
        scroll.setPreferredSize(new Dimension(160, 200));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mf.add(scroll);

        mf.setVisible(SearchDiscVisibel);
    }
}
