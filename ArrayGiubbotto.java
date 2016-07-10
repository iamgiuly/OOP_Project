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
import ordini.PreventivoGiubbotto;

/**
 *
 * @author Giulia Evangelisti
 */
public class ArrayGiubbotto {
    Integer[] IDgiubbotti;
    String[] riga;
    
    public ArrayGiubbotto(){
        
    }
    
    /*Dalla tabella ordine, passando cliente e data ordine, viene creato un array con gli ID di tutti i tipi di maglia che ha ordinato */
   
    public void ArrayIDgiubbotto(String cliente, String date) throws SQLException {
        try{
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", ""); //Apro il canale di connessione
           PreparedStatement ps=conn.prepareStatement("SELECT IDgiubbotto FROM ordine WHERE Cliente=? AND DataOrdine=? AND IDgiubbotto IS NOT NULL"); //Creo lo statement
           ps.setString(1,cliente);
           ps.setString(2,date);
           ResultSet rs=ps.executeQuery();

        /*si posiziona all'ultima riga della resultset e restituisce il suo indice, serve per vedere quante righe abbiamo e creare un array dimensionalmente appropriato */
        rs.last();
        int i=rs.getRow();
        IDgiubbotti = new Integer[i]; //crea un vettore di IDgiubbotti in base a quante maglie diverse sono state ordinate (variabile i)

        rs.first(); 
        
        for(int k=0; k<IDgiubbotti.length; k++){
            IDgiubbotti[k]=rs.getInt("IDgiubbotto");
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
    public String[] accessoGiubbotti(int j) throws SQLException{
        try{
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT g.IDgiubb, g.PrezzoBase, o.Quantità FROM giubbotto g JOIN ordine o ON g.IDgiubb=o.IDgiubbotto WHERE g.IDgiubb=?");        
        ps.setInt(1, IDgiubbotti[j]);
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
        public float importoGiubbotto(int j) throws SQLException, IOException{
        /*variabili d'appoggio */
        float importo;
        String color, materiale, ts, inchiostro;
        int quantita;
        PreventivoGiubbotto ppanta = new PreventivoGiubbotto();
        Personalizzazione pers = new Personalizzazione();
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cherryqueen", "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT g.Colore, g.Materiale, o.Quantità, pers.TipoStampa, pers.Inchiostro FROM giubbotto g JOIN ordine o JOIN personalizzazione pers ON g.IDgiubb=o.IDgiubbotto AND o.IDpers=pers.IDpers WHERE g.IDgiubb=?");        
        ps.setInt(1, IDgiubbotti[j]);
        ResultSet rs=ps.executeQuery();
        rs.next();
        /*Scorre le colonne della ResultSet che saranno in ordine uguale alla select e salva i valori da passare poi al metodo borsa.calcoloPU */
        color=rs.getString(1); 
        materiale=rs.getString(2);
        quantita=rs.getInt(3);
        ts=rs.getString(4);
        inchiostro=rs.getString(5);
        importo = (ppanta.ParzialeGiubbotto(color, materiale)+pers.prezzoPersonalizzazioni(ts, inchiostro))*quantita;
        return importo;
    }
}
