/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author Giulia Evangelisti
 */
public class ArrayBorsa {
    Integer[] IDborse;
    String[] riga;
    
    public ArrayBorsa(){
        
    }
    
    /*Dalla tabella ordine, passando cliente e data ordine, viene creato un array con gli ID di tutti i tipi di maglia che ha ordinato */
   
    public void ArrayIDborsa(String cliente, String date) throws SQLException {
        try{
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", ""); //Apro il canale di connessione
           PreparedStatement ps=conn.prepareStatement("SELECT IDborse FROM ordine WHERE Cliente=? AND DataOrdine=? AND IDborse IS NOT NULL"); //Creo lo statement
           ps.setString(1,cliente);
           ps.setString(2,date);
           ResultSet rs=ps.executeQuery();

        /*si posiziona all'ultima riga della resultset e restituisce il suo indice, serve per vedere quante righe abbiamo e creare un array dimensionalmente appropriato */
        rs.last();
        int i=rs.getRow();
        System.out.println("Borse trovate per cliente " + i); //Verifica da togliere poi (fin qui va bene)
        IDborse = new Integer[i]; //crea un vettore di IDborse in base a quante borse diverse sono state ordinate (variabile i)

        rs.first(); 
        
        for(int k=0; k<IDborse.length; k++){
            IDborse[k]=rs.getInt("IDborse");
            rs.next();
            }
        rs.close();
        for(int k=0; k<IDborse.length; k++){
            System.out.println("ID " + (k+1) + "° borsa " + IDborse[k] + "   "); //verifica che si può togliere dopo
        }
        System.out.println();
        conn.close();
         }
        catch(Exception e)
         {
             e.printStackTrace();
         } 
        
    }
    
    //Prende gli ID maglia trovati per quel cliente nella stessa data e va a pescare le caratteristiche relative agli ID nella tabella maglie, e li stampa a video
    public String[] accessoBorse(int j) throws SQLException{
        try{
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT b.IDborse, b.PrezzoBase, o.Quantità FROM borse b JOIN ordine o ON b.IDborse=o.IDborse WHERE b.IDborse=?");        
        ps.setInt(1, IDborse[j]);
        ResultSet rs=ps.executeQuery();
        ResultSetMetaData rm=rs.getMetaData();
        int numColonne= rm.getColumnCount();
        riga = new String[numColonne]; //AGGIUNGERE PROBLEMA INDICE SU TESINA //
             while(rs.next())
             {
                 for(int i = 1 ; i <=numColonne; i++){ //stampa una riga
                     riga[i-1]= rs.getString(i);
                     System.out.print(riga[i-1] + "    ");
                 }
                 rs.next();
                 System.out.println();
             }
    }catch(Exception e)
         {
             e.printStackTrace();
         } 
      return riga;
}

}