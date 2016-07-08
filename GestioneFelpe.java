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
public class GestioneFelpe {
    public GestioneFelpe()
    {
        
    }
    
    public void inserisciFelpa(int id,String gen,String cv,int sv,int mv,int lv,int xlv,int cppv,int cernv,int tasv,float pbv,String Matv)
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement pst=conn.prepareStatement("INSERT INTO felpa(Genere,Colore, S, M, L, XL, Cappuccio, Cerniera, Tasche, PrezzoBase, Materiale) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1,gen);
        pst.setString(2,cv);
        pst.setInt(3,sv);
        pst.setInt(4,mv);
        pst.setInt(5,lv);
        pst.setInt(6,xlv);
        pst.setInt(7,cppv);
        pst.setInt(8,cernv);
        pst.setInt(9,tasv);
        pst.setFloat(10,pbv);
        pst.setString(11,Matv);
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
        ResultSet res=st.executeQuery("SELECT * FROM felpa WHERE IDfelpa="+id+""); //gli spazi sono importanti!!
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
    
    public void cambiaQuantitaFelpa(int id,int q,String taglia) //cambia la quantità
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
        PreparedStatement pst=conn.prepareStatement("UPDATE felpa SET "+taglia+"=? WHERE IDfelpa=?");
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
    
    public void eliminaFelpa(int id)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("DELETE from felpa WHERE IDfelpa="+id+"");
        }catch(SQLException s)
        {
            System.out.println("Errore SQL!");
            s.printStackTrace();
        }
    }
    
     public ResultSet visualizzaFelpe()
     {
         ResultSet rs=null;
         try{
             Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
             Statement st=conn.createStatement();
             rs=st.executeQuery("SELECT * from felpa");
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
             ResultSet rs=st.executeQuery("SELECT IDfelpa from felpa");
             rs.last();
             int lastid=rs.getInt("IDfelpa");
             return lastid;
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
            ex.printStackTrace();
        }
        return -1;
    }
}
