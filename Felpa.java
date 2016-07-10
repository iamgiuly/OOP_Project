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
public class Felpa extends Abbigliamento {
    boolean cappuccio;
    boolean tasche;
    boolean cerniera;
    
    public void setcapp(boolean c){
        this.cappuccio=c;
    }
    
    public boolean getcapp(){
        return this.cappuccio;
    }
    
    public void settasche(boolean t){
        this.tasche=t;
    }
    
    public boolean gettasche(){
        return this.tasche;
    }
       
    public void setcerniera(boolean c){
        this.cerniera=c;
    }
    
    public boolean getcerniera(){
        return this.cerniera;
    }
    
    
    public Felpa()
    {
        this.genere="NotDefined";
        this.materiale="Cotone";
        this.colore="Bianco";
        this.cappuccio=false;
        this.tasche=false;
        this.cerniera=false;
        this.PrezzoUnitario=0;
    }
    public Felpa(String g, String m, String color, boolean capp, boolean tasc, boolean cern, float pu)
    {
        this.genere=g;
        this.materiale=m;
        this.colore=color;
        this.cappuccio=capp;
        this.tasche=tasc;
        this.cerniera=cern;
        this.PrezzoUnitario=pu;
    }
    
        public float CalcoloPU(String color, boolean cern, boolean capp, boolean tasc) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT PrezzoBase FROM felpa WHERE Colore=? AND Cappuccio=? AND Cerniera=? AND Tasche=?");      
        ps.setString(1, color);
        if (capp==true)
        { ps.setInt(2,1);}
        else{
            ps.setInt(2,0);
                    }
        if(cern==true){
            ps.setInt(3,1);
        }
        else{
            ps.setInt(3,0);
        }
        if(tasc==true){
            ps.setInt(4, 1);            
        }
        else{
            ps.setInt(4, 0);
        }
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
        PrezzoUnitario=rs.getFloat("PrezzoBase");}
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;
    if (capp==true)
        this.PrezzoUnitario+=2.00;
    if (cern==true)
        this.PrezzoUnitario+=1.50;
    if (tasc==true)
        this.PrezzoUnitario+=2.00;
    conn.close();
    return this.PrezzoUnitario;
        }
}