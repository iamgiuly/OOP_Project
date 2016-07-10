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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sara
 */
public class GestionePantalone {
    GestioneOrdine ord=new GestioneOrdine();
    
    public GestionePantalone()
    {
        
    }
    
    public void inserisciPantalone(int id,String gen,String col,String mat,String mod,int s,int m,int l,int xl,float pb)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        PreparedStatement pst=conn.prepareStatement("INSERT INTO pantaloni(Genere,Colore,Materiale,Modello,S,M,L,XL,PrezzoBase) VALUES(?,?,?,?,?,?,?,?,?)");
        pst.setString(1,gen);
        pst.setString(2,col);
        pst.setString(3,mat);
        pst.setString(4,mod);
        pst.setInt(5,s);
        pst.setInt(6,m);
        pst.setInt(7,l);
        pst.setInt(8,xl);
        pst.setFloat(9,pb);
        pst.executeUpdate();
        conn.close();
        System.out.println("Fatto");
        }catch(SQLException ex)
     {
         System.out.println("ErrorSql!");
         ex.printStackTrace();
     }
    }
  
    
    public int getQuantitaAttuale(int id, String taglia)
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        Statement st=conn.createStatement();
        ResultSet res=st.executeQuery("SELECT * FROM pantaloni WHERE IDpanta="+id+""); 
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
    
    public void cambiaQuantitaPantalone(int id,int q,String taglia) //cambia la quantità
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
        PreparedStatement pst=conn.prepareStatement("UPDATE pantaloni SET "+taglia+"=? WHERE IDpanta=?");
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
            ex.printStackTrace();
        }
    }
    
    public void eliminaPantalone(int id)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("DELETE from pantaloni WHERE IDpanta="+id+"");
        }catch(SQLException s)
        {
            System.out.println("Errore SQL!");
            s.printStackTrace();
        }
    }
    
    public ResultSet visualizzaPantaloni()
     {
         ResultSet rs=null;
         try{
             Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
             Statement st=conn.createStatement();
             rs=st.executeQuery("SELECT * from pantaloni");
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
             ResultSet rs=st.executeQuery("SELECT IDpanta from pantaloni");
             rs.last();
             int lastid=rs.getInt("IDpanta");
             return lastid;
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
            ex.printStackTrace();
        }
        return -1;
    }
    
    public void aggiornaMagazzinoPantaloni()
      {
          try{
        int j=0;
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("SELECT idOrdine,IDpantalone,Quantità,Taglia from ordine WHERE Stato='Da aggiornare'");
        List<Integer> id=new ArrayList<Integer>();
        List<Integer> quantita=new ArrayList<Integer>();
        List<String> taglia=new ArrayList<String>();
        List<Integer> idord=new ArrayList<Integer>();
        while(rs.next()) //da ripetere per ogni tipologia, aggiorna magazzino conterra tutto
        {
            idord.add(rs.getInt("idOrdine"));
            id.add(rs.getInt("IDpantalone"));
            quantita.add(rs.getInt("Quantità"));
            taglia.add(rs.getString("Taglia"));
            
        }
       
        st.close();
        rs.close();
        while(id.size()>j) //se entra nel while ho maglie da modificare
        {
            if((((id.get(j)).equals(0))==false))
            {
                
            st=conn.createStatement();
            rs=st.executeQuery("SELECT "+taglia.get(j)+" FROM pantaloni WHERE IDpanta="+id.get(j)+"");
            while(rs.next()){
            int q=rs.getInt(""+taglia.get(j)+""); //questa è la quantità nel database della taglia che ci interessa
            int rimanenti=q-(quantita.get(j));
            
            cambiaQuantitaPantalone(id.get(j),rimanenti,taglia.get(j));
            ord.modificaStato(idord.get(j),"In lavorazione");
            }
            }
            
            j++;
            
        }
        
      }catch(SQLException ex)
      {
          ex.printStackTrace();
      }
      }
}
