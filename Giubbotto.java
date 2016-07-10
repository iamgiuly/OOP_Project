/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Giulia Evangelisti
 */
public class Giubbotto extends Abbigliamento {
    
    public Giubbotto()
    {
        this.genere="NotDefined";
        this.materiale="Pelle";
        this.colore="Nero";
        this.PrezzoUnitario=0;
    }
    public Giubbotto (String g, String m, String color, float pu)
    {
        this.genere=g;
        this.materiale=m;
        this.colore=color;
        this.PrezzoUnitario=pu;
    }
    
        public float CalcoloPU(String color, String materiale) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT PrezzoBase FROM giubbotto WHERE Colore=? AND Materiale=? AND IDgiubb IS NOT NULL");  
        ps.setString(1, color);
        ps.setString(2, materiale);
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
          PrezzoUnitario=rs.getFloat("PrezzoBase");
        }
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;
    if ("Pelle".equals(materiale))
            this.PrezzoUnitario+=7.00;
    conn.close();
    return this.PrezzoUnitario;
        }

}