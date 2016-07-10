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
import ordini.Personalizzazione;
import ordini.PreventivoPantalone;

/**
 *
 * @author Giulia Evangelisti
 */
public class ArrayPantalone {
    Integer[] IDpantaloni;
    String[] riga;
    
    public ArrayPantalone(){
        
    }
    
    /*Dalla tabella ordine, passando cliente e data ordine, viene creato un array con gli ID di tutti i tipi di maglia che ha ordinato */
   
    public void ArrayIDpantalone(String cliente, String date) throws SQLException {
        try{
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", ""); //Apro il canale di connessione
           PreparedStatement ps=conn.prepareStatement("SELECT IDpantalone FROM ordine WHERE Cliente=? AND DataOrdine=? AND IDpantalone IS NOT NULL"); //Creo lo statement
           ps.setString(1,cliente);
           ps.setString(2,date);
           ResultSet rs=ps.executeQuery();

        /*si posiziona all'ultima riga della resultset e restituisce il suo indice, serve per vedere quante righe abbiamo e creare un array dimensionalmente appropriato */
        rs.last();
        int i=rs.getRow();
        IDpantaloni = new Integer[i]; //crea un vettore di IDpantaloni in base a quante maglie diverse sono state ordinate (variabile i)

        rs.first(); 
        
        for(int k=0; k<IDpantaloni.length; k++){
            IDpantaloni[k]=rs.getInt("IDpantalone");
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
    public String[] accessoPantaloni(int j) throws SQLException{
        try{
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT p.IDpanta, p.PrezzoBase, o.Quantità FROM pantaloni p JOIN ordine o ON p.IDpanta=o.IDpantalone WHERE p.IDpanta=?");        
        ps.setInt(1, IDpantaloni[j]);
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
        public float importoPanta(int j) throws SQLException, IOException{
        /*variabili d'appoggio */
        float importo;
        String color, ts, inchiostro, modello;
        int quantita;
        PreventivoPantalone ppanta = new PreventivoPantalone();
        Personalizzazione pers = new Personalizzazione();
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT p.Colore, p.Modello, o.Quantità, pers.TipoStampa, pers.Inchiostro FROM pantaloni p JOIN ordine o JOIN personalizzazione pers ON p.IDpanta=o.IDpantalone AND o.IDpers=pers.IDpers WHERE p.IDpanta=?");        
        ps.setInt(1, IDpantaloni[j]);
        ResultSet rs=ps.executeQuery();
        rs.next();
        /*Scorre le colonne della ResultSet che saranno in ordine uguale alla select e salva i valori da passare poi al metodo borsa.calcoloPU */
        color=rs.getString("p.Colore"); 
        modello=rs.getString("p.Modello");
        quantita=rs.getInt("o.Quantità");
        ts=rs.getString("pers.TipoStampa");
        inchiostro=rs.getString("pers.Inchiostro");
        importo=(ppanta.ParzialePantalone(color, modello)+pers.prezzoPersonalizzazioni(ts, inchiostro))*quantita;
        return importo;
    }
}
