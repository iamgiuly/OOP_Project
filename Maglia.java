package merce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Maglia extends Abbigliamento {
    
    boolean scollatura;
    boolean maniche;

    

    
    public void setScollatura(boolean b){
       scollatura=b;
    }
    public boolean getScollatura(){
        return this.scollatura;
    }
    public void setManiche(boolean b){
      this.maniche=b;
    }
    public boolean getManiche(){
        return this.maniche;
    }
    

public Maglia()
{ 
    this.genere="";
    this.materiale="";
    this.colore="Bianco";
    this.scollatura=false;
    this.maniche=false;
    
}
public Maglia (String g, String m, String s, boolean scoll, boolean manica, float pu)
{
    
    this.genere=g;
    this.materiale=m;
    this.colore=s;
    this.scollatura=scoll;
    this.maniche=manica;
    this.PrezzoUnitario=pu;
}

    public float CalcoloPU(String color, boolean scoll, boolean manica) throws SQLException{
        try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT PrezzoBase FROM maglia WHERE Colore=? AND Scollatura=? AND Maniche=? AND IDmaglia IS NOT NULl");        
        ps.setString(1, color);
        if(scoll==true){
        ps.setString(2, "V");}
        else if(scoll==false) {        
            ps.setString(2,"Girocollo");}
        
        if(manica==true){
            ps.setString(3, "Lunghe");}
                    
        else if(manica==false){
        
            ps.setString(3, "Corte");}
        
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            PrezzoUnitario=rs.getFloat("PrezzoBase");
            
        }
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;
    if(scoll==true)
        this.PrezzoUnitario+=1.00;
    if (manica==true)
        this.PrezzoUnitario+=1.00;
    rs.close();
    conn.close();
    return this.PrezzoUnitario;
        }catch(SQLException ex){
    ex.printStackTrace();
}return this.PrezzoUnitario;
}}