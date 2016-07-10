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
import ordini.PreventivoFelpa;

/**
 *
 * @author Giulia Evangelisti
 */
public class ArrayFelpa {
    Integer[] IDfelpe;
    String[] riga;
    
    public ArrayFelpa(){
        
    }
    
    /*Dalla tabella ordine, passando cliente e data ordine, viene creato un array con gli ID di tutti i tipi di maglia che ha ordinato */
   
    public void ArrayIDfelpa(String cliente, String date) throws SQLException {
        try{
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", ""); //Apro il canale di connessione
           PreparedStatement ps=conn.prepareStatement("SELECT IDfelpa FROM ordine WHERE Cliente=? AND DataOrdine=? AND IDfelpa IS NOT NULL"); //Creo lo statement
           ps.setString(1,cliente);
           ps.setString(2,date);
           ResultSet rs=ps.executeQuery();

        /*si posiziona all'ultima riga della resultset e restituisce il suo indice, serve per vedere quante righe abbiamo e creare un array dimensionalmente appropriato */
        rs.last();
        int i=rs.getRow();
        IDfelpe = new Integer[i]; //crea un vettore di IDfelpe in base a quante maglie diverse sono state ordinate (variabile i)

        rs.first(); 
        
        for(int k=0; k<IDfelpe.length; k++){
            IDfelpe[k]=rs.getInt("IDfelpa");
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
    public String[] accessoFelpe(int j) throws SQLException{
        try{
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT f.IDfelpa, f.PrezzoBase, o.Quantità FROM felpa f JOIN ordine o ON f.IDfelpa=o.IDfelpa WHERE o.IDfelpa=?");        
        ps.setInt(1, IDfelpe[j]);
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
        public float importoFelpa(int j) throws SQLException, IOException{
        /*variabili d'appoggio */
        float importo;
        String color, ts, inchiostro;
        int quantita;
        boolean tasche=false, cern=false, capp=false;
        PreventivoFelpa pfelpa = new PreventivoFelpa();
        Personalizzazione pers = new Personalizzazione();
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT f.Colore, f.Cappuccio, f.Cerniera, f.Tasche, o.Quantità, pers.TipoStampa, pers.Inchiostro FROM felpa f JOIN ordine o JOIN personalizzazione pers ON f.IDfelpa=o.IDfelpa AND o.IDpers=pers.IDpers WHERE f.IDfelpa=?");        
        ps.setInt(1, IDfelpe[j]);
        ResultSet rs=ps.executeQuery();
        rs.next();
        /*Scorre le colonne della ResultSet che saranno in ordine uguale alla select e salva i valori da passare poi al metodo borsa.calcoloPU */
        color=rs.getString("f.Colore"); 
        capp=rs.getBoolean("f.Cappuccio");
        cern=rs.getBoolean("f.Cerniera");
        tasche=rs.getBoolean("f.Tasche");
        quantita=rs.getInt("o.Quantità");
        ts=rs.getString("pers.TipoStampa");
        inchiostro=rs.getString("pers.Inchiostro");
        importo=(pfelpa.ParzialeFelpa(color, cern, capp, tasche)+pers.prezzoPersonalizzazioni(ts, inchiostro))*quantita;
        return importo;
    }   
}
