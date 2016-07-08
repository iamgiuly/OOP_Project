/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazzino;

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
public class GestionePubblicità {
    
    public GestionePubblicità()
    {
        
    }
    
    public void inserisciPubblicità(int id,String tc,String form,float sp,String col,float pb,int q)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
            PreparedStatement pst=conn.prepareStatement("INSERT into pubblicità(TipoCarta,Formato,Spessore,Colore,PrezzoBase,Quantità) VALUES (?,?,?,?,?,?)");
            pst.setString(1,tc);
            pst.setString(2,form);
            pst.setFloat(3,sp);
            pst.setString(4,col);
            pst.setFloat(5,pb);
            pst.setInt(6,q);
            pst.executeUpdate();
            conn.close();
            System.out.println("Fatto");
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
        }
    }
    
    public void cambiaQuantitaPubblicità(int id,int q) //cambia la quantità
    // NB: AGGIORNA, NON SOTTRAE! Se ho 20 maglie e ne ordino 5, 
    //alla funzione dovrò passare 15, il risultato già della sottrazione
    //la sottrazione sarà fatta dalla riga di codice precedente al richiamo della funzione
    {
        try{ 
        int qatt=this.getQuantitaAttuale(id);
        if(q<=qatt)
        {
            //questa parte funziona quella prima di controllo no, ma il compilatore
            //non da' errori, eseguendolo da solo Errore Sql
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        PreparedStatement pst=conn.prepareStatement("UPDATE pubblicità SET Quantità=? WHERE IDpubb=?");
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
    
    public int getQuantitaAttuale(int id)
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        Statement st=conn.createStatement();
        ResultSet res=st.executeQuery("SELECT * FROM pubblicità WHERE IDpubb="+id+""); //gli spazi sono importanti!!
        //altrimenti è come scrivere FROMtabella che non è un comando sql
        
        if(res.next())
        {
            int t=res.getInt("Quantità");
            System.out.println("Quantità: "+t+"");
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
    
    public void eliminaPubblicità(int id) //elimina intera riga
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("DELETE from pubblicità WHERE IDpubb="+id+"");
        }catch(SQLException s)
        {
            System.out.println("Errore SQL!");
            s.printStackTrace();
        }
    }
    
    public ResultSet visualizzaPubblicita()
     {
         ResultSet rs=null;
         try{
             Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
             Statement st=conn.createStatement();
             rs=st.executeQuery("SELECT * from pubblicità");
             ResultSetMetaData rm=rs.getMetaData();
             return rs;
         }catch(SQLException s)
         {
             System.out.println("Errore SQL!");
         }
         return rs;
     }
    
    public int getLastID()
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
             Statement st=conn.createStatement();
             ResultSet rs=st.executeQuery("SELECT IDpubb from pubblicità");
             rs.last();
             int lastid=rs.getInt("IDpubb");
             return lastid;
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
            ex.printStackTrace();
        }
        return -1;
    }
}
