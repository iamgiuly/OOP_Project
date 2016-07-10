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
public class Borsa extends Abbigliamento {
    public modello model;

    public enum modello {Tracolla, Zaino, Notdefined} //per ora lascio enum ma penso sia più facile metterlo String
    
    public void setMod(String m){  //setto il modello, ricordando che in Java enum è una vera e propria classe
        this.model=modello.valueOf(m);
    }
    public modello getMod(){
        return this.model;
    }
    
    /*Costruttore senza parametri */
    public Borsa()
    {
        this.genere="notdefined";
        this.materiale="cotone";
        this.colore="Nero";
        this.model=modello.Notdefined;
        PrezzoUnitario=0;
    }
    
    /* Costruttore con parametri */
    public Borsa(String g, String m, String mo, String color){  
                                                                                              
    this.genere=g;
    this.materiale=m;
    this.model=modello.valueOf("mo");
    this.colore=color;
    PrezzoUnitario=0;
    }
    public float CalcoloPU(String mo, String color) throws SQLException{
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
    PreparedStatement ps=conn.prepareStatement("SELECT PrezzoBase FROM borse WHERE Colore=? AND Modello=? AND IDborse IS NOT NULL");    
    ps.setString(1, color);
    ps.setString(2,mo);
    ResultSet rs=ps.executeQuery();
    
    while(rs.next()){
        PrezzoUnitario=rs.getFloat("PrezzoBase");
    }
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;

    if ("Zaino".equals(mo)){
        this.PrezzoUnitario+=3.00;}
    conn.close();
    return this.PrezzoUnitario;
        }

   
}
   