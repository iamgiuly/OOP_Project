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
public class Pantalone extends Abbigliamento {
    String model;
    
public void setMod (String mod){
    this.model=mod;
}

public String geMod(){
    return this.model;
}

public Pantalone()
{ 
    this.genere="";
    this.materiale="";
    this.colore="Bianco";
    this.model="notdefined";
    this.PrezzoUnitario=0;
    
}

public Pantalone(String g, String m, String s, String mod, float pu)
{ 
    this.genere=g;
    this.materiale=m;
    this.colore=s;
    this.model=mod;
    this.PrezzoUnitario=pu;
}
    public float CalcoloPU(String color, String mo) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT PrezzoBase FROM pantaloni WHERE Colore=? AND Modello=? AND IDpanta IS NOT NULL");  
        ps.setString(1,color);
        ps.setString(2, mo);
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            PrezzoUnitario=rs.getInt("PrezzoBase");
        }
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;
    if ("Lunghi".equals(mo))
        this.PrezzoUnitario+=2.00;
    conn.close();
    return this.PrezzoUnitario;
        }

}