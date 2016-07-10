/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazzino;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
/**
 *
 * @author Sara
 */
public class GestioneOrdine {
    
    public GestioneOrdine()
    {
        
    }
    
    public void inserisciOrdine(String cliente,String data,int idmaglia,int idpers,int quantita,String taglia,int idb,int idf,int idgiubb,int idpantalone,int idpubb,String stato)
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        PreparedStatement pst=conn.prepareStatement("INSERT INTO ordine(Cliente, DataOrdine, IDmaglia, IDpers, Quantità, Taglia,IDborse,IDfelpa,IDgiubbotto,IDpantalone,IDpubblicità,Stato) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
        //pst.setInt(1,id);
        pst.setString(1,cliente);
        pst.setString(2,data);
        pst.setInt(3,idmaglia);
        pst.setInt(4,idpers);
        pst.setInt(5,quantita);
        pst.setString(6,taglia);
        pst.setInt(7,idb);
        pst.setInt(8,idf);
        pst.setInt(9,idgiubb);
        pst.setInt(10,idpantalone);
        pst.setInt(11,idpubb);
        pst.setString(12,stato);
        pst.executeUpdate();
        conn.close();
        }catch(SQLException ex)
     {
         System.out.println("ErrorSql!");
         ex.printStackTrace();
     }
    }
    
    public void eliminaOrdine(int id)
    {
        try{
            if(controllaStato(id)==true){
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
                PreparedStatement pst=conn.prepareStatement("DELETE FROM ordine WHERE idOrdine=?");
                pst.setInt(1, id);
                pst.executeUpdate();
            }
        }catch(SQLException s)
        {
            System.out.println("Errore SQL!");
            s.printStackTrace();
        }
    }
    
    public boolean controllaStato(int id) throws SQLException
    {
        
        String stato="";
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        PreparedStatement pst=conn.prepareStatement("SELECT Stato FROM ordine WHERE idOrdine=?");
        pst.setInt(1, id);
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            stato=rs.getString("Stato");
        }
        rs.close();
        pst.close();
        conn.close();
        if(stato.equals("Emesso")){
            return true;
        }
        else{
            return false;
        }
        
    }
    
       public ResultSet visualizzaOrdini()
     {
         ResultSet rs=null;
         try{
             Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
             Statement st=conn.createStatement();
             rs=st.executeQuery("SELECT * from ordine");
             ResultSetMetaData rm=rs.getMetaData();
             return rs;
         }catch(SQLException s)
         {
             System.out.println("Errore SQL!");
         }
         return rs;
     }
      
       
     public void selezionaOrdine(String cliente, String date){
           try{
               
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
           PreparedStatement ps=conn.prepareStatement("SELECT * FROM ordine WHERE Cliente=? AND DataOrdine=?");
           ps.setString(1,cliente);
           ps.setString(2,date);
           ResultSet rs=ps.executeQuery();
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
             ps.close();
             rs.close();
             conn.close();
           }           
         catch(SQLException s)
         {
             System.out.println("Errore SQL!");
             s.printStackTrace();
         }
       }
	   
	   public ResultSet CercaOrdine(int id) throws IOException,SQLException {
        ResultSet rs=null;
        String ordine="";
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        Statement st=conn.createStatement();
        rs=st.executeQuery("SELECT * from ordine WHERE idOrdine="+id+"");
        ResultSetMetaData rm=rs.getMetaData();
        return rs;
        }
           
        public int getLastIDord()
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
             Statement st=conn.createStatement();
             ResultSet rs=st.executeQuery("SELECT idOrdine from ordine");
             rs.last();
             int lastid=rs.getInt("idOrdine");
             return lastid;
        }catch(SQLException ex)
        {
            System.out.println("Errore SQL!");
            ex.printStackTrace();
        }
        return -1;
    }
        
        public void ModificaOrdine(int id,String cliente,String data,int idmaglia,int idpers,int quantità,String taglia,int idborsa,int idfelpa,int idgiubb,int idpant,int idpubb,String stato) throws SQLException {
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
           PreparedStatement ps=conn.prepareStatement("UPDATE ordine SET Cliente=?,DataOrdine=?,IDmaglia=?,IDpers=?,Quantità=?,Taglia=?,IDborse=?,IDfelpa=?,IDgiubbotto=?,IDpantalone=?,IDpubblicità=?,Stato=? WHERE idOrdine=?");
           ps.setString(1,cliente);
           ps.setString(2, data);
           ps.setInt(3, idmaglia);
           ps.setInt(4,idpers);
           ps.setInt(5, quantità);
           ps.setString(6,taglia);
           ps.setInt(7,idborsa);
           ps.setInt(8,idfelpa);
           ps.setInt(9, idgiubb);
           ps.setInt(10,idpant);
           ps.setInt(11, idpubb);
           ps.setString(12,stato);
           ps.setInt(13, id);
           ResultSet rs=ps.executeQuery();
        }
        
        public void modificaStato(int id,String stato) throws SQLException
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
            PreparedStatement pst=conn.prepareStatement("UPDATE ordine SET Stato=? WHERE idOrdine=?");
            pst.setString(1,stato);
            pst.setInt(2,id);
            pst.executeUpdate();
            System.out.println("Stato modificato");
            conn.close();
        }
    
}
