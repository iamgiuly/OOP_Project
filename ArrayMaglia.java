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
import ordini.PreventivoMaglia;
import ordini.Personalizzazione;

/**
 *
 * @author Giulia Evangelisti
 */
public class ArrayMaglia {
    Integer[] IDmaglie;
    String[] riga;
    
    public ArrayMaglia(){
        
    }
    
    /*Dalla tabella ordine, passando cliente e data ordine, viene creato un array con gli ID di tutti i tipi di maglia che ha ordinato */
   
    public void ArrayIDmaglia(String cliente, String date) throws SQLException {
        try{
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", ""); //Apro il canale di connessione
           PreparedStatement ps=conn.prepareStatement("SELECT IDmaglia FROM ordine WHERE Cliente=? AND DataOrdine=? AND IDmaglia IS NOT NULL"); //Creo lo statement
           ps.setString(1,cliente);
           ps.setString(2,date);
           ResultSet rs=ps.executeQuery();

        /*si posiziona all'ultima riga della resultset e restituisce il suo indice, serve per vedere quante righe abbiamo e creare un array dimensionalmente appropriato */
        rs.last();
        int i=rs.getRow();
        IDmaglie = new Integer[i]; //crea un vettore di IDmaglie in base a quante maglie diverse sono state ordinate (variabile i)

        rs.first(); 
        
        for(int k=0; k<IDmaglie.length; k++){
            IDmaglie[k]=rs.getInt("IDmaglia");
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
    public String[] accessoMaglie(int j) throws SQLException{
        try{
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT m.IDmaglia, m.PrezzoBase, o.Quantità FROM maglia m JOIN ordine o ON m.IDmaglia=o.IDmaglia WHERE m.IDmaglia=?");        
        ps.setInt(1, IDmaglie[j]);
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
    /*calcola in base alle caratteristiche e alle personalizzazioni e alla quantità l'importo totale delle maglia acquistate*/
    public float importoMaglia(int j) throws SQLException, IOException{
        /*variabili d'appoggio */
        float importo;
        String color, ts, inchiostro, appoggio;
        boolean scoll = false, manica;
        int quantita;
        PreventivoMaglia pmaglia = new PreventivoMaglia();
        Personalizzazione pers = new Personalizzazione();
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT m.Colore, m.Scollatura, m.Maniche, o.Quantità, pers.TipoStampa, pers.Inchiostro FROM maglia m JOIN ordine o JOIN personalizzazione pers ON m.IDmaglia=o.IDmaglia AND o.IDpers=pers.IDpers WHERE m.IDmaglia=?");        
        ps.setInt(1, IDmaglie[j]);
        ResultSet rs=ps.executeQuery();
        rs.next();
        color=rs.getString(1);
        appoggio=rs.getString(2);
        if("V".equals(appoggio))
            scoll=true;
        appoggio=rs.getString(3);
        if("Lunghe".equals(appoggio));
            manica=true;
        quantita=rs.getInt(4);
        ts=rs.getString(5);
        inchiostro=rs.getString(6);
        importo=(pmaglia.ParzialeMaglia(color, scoll, manica)+pers.prezzoPersonalizzazioni(ts, inchiostro))*quantita;
        return importo;
    }

}
