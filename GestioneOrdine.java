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
public class GestioneOrdine {
    
    public GestioneOrdine()
    {
        
    }
    
    public void inserisciOrdine(int id,String cliente,String data,int idmaglia,int idpers,int quantita,String taglia,float pfin,int idb,int idf,int idgiubb,int idpantalone,int idpubb,String stato)
    {
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
        PreparedStatement pst=conn.prepareStatement("INSERT INTO ordine(idOrdine, Cliente, DataOrdine, IDmaglia, IDpers, Quantità, Taglia, PrezzoFinale, IDborse,IDfelpa,IDgiubbotto,IDpantalone,IDpubblicità,Stato) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setInt(1,id);
        pst.setString(2,cliente);
        pst.setString(3,data);
        pst.setInt(4,idmaglia);
        pst.setInt(5,idpers);
        pst.setInt(6,quantita);
        pst.setString(7,taglia);
        pst.setFloat(8,pfin);
        pst.setInt(9,idb);
        pst.setInt(10,idf);
        pst.setInt(11,idgiubb);
        pst.setInt(12,idpantalone);
        pst.setInt(13,idpubb);
        pst.setString(14,stato);
        pst.executeUpdate();
        conn.close();
        System.out.println("Fatto");
        }catch(SQLException ex)
     {
         System.out.println("ErrorSql!");
     }
    }
    
    public void eliminaOrdine(int id)
    {
        try{
            if(controllaStato(id)){
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("DELETE from ordine WHERE idOrdine="+id+"");
                System.out.println("Ordine eliminato!");
            }
            else
            {
                System.out.println("Ordine non completato!");
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
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("SELECT Stato from ordine WHERE idOrdine="+id+"");
        if(rs.next()){
            stato=rs.getString("Stato");
        }
        rs.close();
        st.close();
        conn.close();
        if(stato=="Emesso"){
            return true;
        }
        else{
            return false;
        }
        
    }
    
       public void visualizzaOrdini()
     {
         try{
             Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "admin", "password");
             Statement st=conn.createStatement();
             ResultSet rs=st.executeQuery("SELECT * from ordine");
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
