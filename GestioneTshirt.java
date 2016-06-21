/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magazzino;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sara
 */
public class GestioneTshirt {
    
    public GestioneTshirt()
    {
        
    }
    
    public void inserisciMaglia(int idmaglia,String gen,String col,int s,int m,int l,int xl,String scol,String maniche,String mat,float pb)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        PreparedStatement pst=conn.prepareStatement("INSERT INTO maglia(IDmaglia,Genere,Colore,S,M,L,XL,Scollatura,Maniche,Materiale,PrezzoBase) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        pst.setInt(1,idmaglia);
        pst.setString(2,gen);
        pst.setString(3,col);
        pst.setInt(4,s);
        pst.setInt(5,m);
        pst.setInt(6,l);
        pst.setInt(7,xl);
        pst.setString(8,scol);
        pst.setString(9,maniche);
        pst.setString(10,mat);
        pst.setFloat(11,pb);
        pst.executeUpdate();
        conn.close();
        System.out.println("Fatto");
        }catch(SQLException ex)
     {
         System.out.println("ErrorSql!");
     }
    }
    
    public int getQuantitaAttuale(int id, String taglia)
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        Statement st=conn.createStatement();
        ResultSet res=st.executeQuery("SELECT * FROM maglia WHERE IDmaglia="+id+""); //gli spazi sono importanti!!
        //altrimenti è come scrivere FROMtabella che non è un comando sql
        
        if(res.next())
        {
            int t=res.getInt(taglia);
            System.out.println(""+taglia+": "+t+"");
            return t;
        }
        res.close(); //chiudere prima res
        conn.close(); //poi connessione
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
            ex.printStackTrace();
        }
        return -1;
    }
    
    public void cambiaQuantitaMaglia(int id,int q,String taglia) //cambia la quantità
    // NB: AGGIORNA, NON SOTTRAE! Se ho 20 maglie e ne ordino 5, 
    //alla funzione dovrò passare 15, il risultato già della sottrazione
    //la sottrazione sarà fatta dalla riga di codice precedente al richiamo della funzione
    {
        try{ 
        int qatt=this.getQuantitaAttuale(id,taglia);
        if(q<=qatt)
        {
            //questa parte funziona quella prima di controllo no, ma il compilatore
            //non da' errori, eseguendolo da solo Errore Sql
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        PreparedStatement pst=conn.prepareStatement("UPDATE maglia SET "+taglia+"=? WHERE IDmaglia=?");
        pst.setInt(1,q);
        pst.setInt(2,id);
        pst.executeUpdate();
        pst.close();
        conn.close();
        System.out.println("Fatto");
        }
        else
        {System.out.println("Quantità ecceduta!");}
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
        }
    }
    
    public void eliminaMaglia(int id)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("DELETE from maglia WHERE IDmaglia="+id+"");
        }catch(SQLException s)
        {
            System.out.println("Errore SQL!");
            s.printStackTrace();
        }
    }
    
    public void visualizzaMaglie()
     {
         try{
             Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
             Statement st=conn.createStatement();
             ResultSet rs=st.executeQuery("SELECT * from maglia");
             ResultSetMetaData rm=rs.getMetaData();
             int numColonne=rm.getColumnCount();
             for(int i=1; i<=numColonne;i++)
             {
                 System.out.print(rm.getColumnName(i) + "   ");
             }
             System.out.println();
             while(rs.next())
             {
                 for(int i = 1 ; i <= numColonne; i++){ //stampa una riga
                     System.out.print(rs.getString(i) + " ");
                 }
                 System.out.println();
             }
             st.close();
             rs.close();
             conn.close();
         }catch(SQLException s)
         {
             System.out.println("Errore SQL!");
         }
     }
}
