/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattura;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import ordini.PreventivoPubblicita;

/**
 *
 * @author Giulia Evangelisti
 */
public class ArrayPubblicita {
    Integer[] IDpubb;
    String[] riga;
    
    public ArrayPubblicita(){
        
    }
    
    /*Dalla tabella ordine, passando cliente e data ordine, viene creato un array con gli ID di tutti i tipi di maglia che ha ordinato */
   
    public void ArrayIDpubb(String cliente, String date) throws SQLException {
        try{
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", ""); //Apro il canale di connessione
           PreparedStatement ps=conn.prepareStatement("SELECT IDpubblicità FROM ordine WHERE Cliente=? AND DataOrdine=? AND IDpubblicità IS NOT NULL"); //Creo lo statement
           ps.setString(1,cliente);
           ps.setString(2,date);
           ResultSet rs=ps.executeQuery();

        /*si posiziona all'ultima riga della resultset e restituisce il suo indice, serve per vedere quante righe abbiamo e creare un array dimensionalmente appropriato */
        rs.last();
        int i=rs.getRow();
        IDpubb = new Integer[i]; //crea un vettore di IDpubb in base a quante maglie diverse sono state ordinate (variabile i)

        rs.first(); //si riposiziona alla prima riga della result set
        
        for(int k=0; k<IDpubb.length; k++){
            IDpubb[k]=rs.getInt("IDpubblicità");
            rs.next();
            }
        rs.close();
        conn.close();
         }
        catch(Exception e)
         {
             e.printStackTrace();
         } 
        
    }
    
    //Prende gli ID maglia trovati per quel cliente nella stessa data e va a pescare le caratteristiche relative agli ID nella tabella maglie, e li stampa a video
    public String[] accessoPubb(int j) throws SQLException{
        try{
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT p.IDpubb, p.PrezzoBase, o.Quantità FROM pubblicità p JOIN ordine o ON p.IDpubb=o.IDpubblicità WHERE o.IDpubblicità=?");        
        ps.setInt(1, IDpubb[j]);
        ResultSet rs=ps.executeQuery();
        ResultSetMetaData rm=rs.getMetaData();
        int numColonne= rm.getColumnCount();
        riga = new String[numColonne]; //AGGIUNGERE PROBLEMA INDICE SU TESINA //
             while(rs.next())
             {
                 for(int i = 1 ; i <=numColonne; i++){ //stampa una riga
                     riga[i-1]= rs.getString(i);
                 }
                 rs.next();
             }
    }catch(Exception e)
         {
             e.printStackTrace();
         } 
      return riga;
}
        public float importoPubblicita(int j) throws SQLException, IOException{
        /*variabili d'appoggio */
        float importo;
        String color, formato, spessore, inchiostro; boolean inc=false; //nel metodo di calcolo preventivo pubblicità l'inchiostro è boolean lo converto sotto
        int quantita;
        PreventivoPubblicita ppubb = new PreventivoPubblicita();
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT p.Colore, p.Formato, p.Spessore, o.Quantità, pers.Inchiostro FROM pubblicità p JOIN ordine o JOIN personalizzazione pers ON p.IDpubb=o.IDpubblicità AND o.IDpers=pers.IDpers WHERE p.IDpubb=?");        
        ps.setInt(1, IDpubb[j]);
        ResultSet rs=ps.executeQuery();
        rs.next();
        /*Scorre le colonne della ResultSet che saranno in ordine uguale alla select e salva i valori da passare poi al metodo borsa.calcoloPU */
        color=rs.getString("p.Colore"); 
        formato=rs.getString("p.Formato");
        spessore=rs.getString("p.Spessore");
        quantita=rs.getInt("o.Quantità");
        inchiostro=rs.getString("pers.Inchiostro");
        if("Colorato".equals(inchiostro))
            inc=true;
        importo=ppubb.TotalePubblicita(quantita, color, formato, inc, spessore);
        //Somma personalizzazioni e moltiplicazione per quant
        return (int)importo;
    }   
}

