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
public class GestionePubblicità {
    
    GestioneOrdine ord=new GestioneOrdine();
    
    public GestionePubblicità()
    {
        
    }
    
    public void inserisciPubblicità(int id,String tc,String form,String sp,String col,float pb,int q)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
            PreparedStatement pst=conn.prepareStatement("INSERT into pubblicità(TipoCarta,Formato,Spessore,Colore,PrezzoBase,Quantita) VALUES (?,?,?,?,?,?)");
            pst.setString(1,tc);
            pst.setString(2,form);
            pst.setString(3,sp);
            pst.setString(4,col);
            pst.setFloat(5,pb);
            pst.setInt(6,q);
            pst.executeUpdate();
            conn.close();
            System.out.println("Fatto");
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
            ex.printStackTrace();
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
        PreparedStatement pst=conn.prepareStatement("UPDATE pubblicità SET Quantita=? WHERE IDpubb=?");
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
            int t=res.getInt("Quantita");
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
    
    public void aggiornaMagazzinoPubblicita()
      {
          try{
        int j=0;
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("SELECT idOrdine,IDpubblicità,Quantità,Taglia from ordine WHERE Stato='Da aggiornare'");
        List<Integer> id=new ArrayList<Integer>();
        List<Integer> quantita=new ArrayList<Integer>();
        List<Integer> idord=new ArrayList<Integer>();
        while(rs.next()) //da ripetere per ogni tipologia, aggiorna magazzino conterra tutto
        {
            idord.add(rs.getInt("idOrdine"));
            id.add(rs.getInt("IDpubblicità"));
            quantita.add(rs.getInt("Quantità"));
            
        }
       
        st.close();
        rs.close();
        while(id.size()>j) //se entra nel while ho maglie da modificare
        {
            if((((id.get(j)).equals(0))==false))
            {
                
            st=conn.createStatement();
            rs=st.executeQuery("SELECT Quantita FROM pubblicità WHERE IDpubb="+id.get(j)+"");
            while(rs.next()){
            int q=rs.getInt("Quantita"); //questa è la quantità nel database della taglia che ci interessa
            int rimanenti=q-(quantita.get(j));
            
            cambiaQuantitaPubblicità(id.get(j),rimanenti);
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
