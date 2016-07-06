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
public class GestioneBorse {
    
    public GestioneBorse()
    {
    
    }
    
    public void inserisciBorsa(int idborsa, String mod,String col,float pb,int q)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement pst=conn.prepareStatement("INSERT INTO borse(Modello,Colore,PrezzoBase,Quantità) VALUES(?,?,?,?)");
        pst.setString(1,mod);
        pst.setString(2,col);
        pst.setFloat(3,pb);
        pst.setInt(4,q);
        pst.executeUpdate();
        conn.close();
        System.out.println("Fatto");
        }catch(SQLException ex)
     {
         System.out.println("ErrorSql!");
     }
    }
    
    public void cambiaQuantitaBorsa(int id,int q) //cambia la quantità
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
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement pst=conn.prepareStatement("UPDATE borse SET Quantità=? WHERE IDborsa=?");
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
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        Statement st=conn.createStatement();
        ResultSet res=st.executeQuery("SELECT * FROM borse WHERE IDborse="+id+""); //gli spazi sono importanti!!
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
    
    public void eliminaBorsa(int id)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("DELETE from borse WHERE IDborse="+id+"");
        }catch(SQLException s)
        {
            System.out.println("Errore SQL!");
            s.printStackTrace();
        }
    }
    
    public void visualizzaBorse()
     {
         try{
             Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
             Statement st=conn.createStatement();
             ResultSet rs=st.executeQuery("SELECT * from borse");
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
    
     public int getLastID()
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
             Statement st=conn.createStatement();
             ResultSet rs=st.executeQuery("SELECT IDborse from borse");
             rs.last();
             int lastid=rs.getInt("IDborse");
             return lastid;
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
            ex.printStackTrace();
        }
        return -1;
    }
    
}


