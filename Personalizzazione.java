/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;
import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Serena
 */

/**Ho creato la classe Personalizzazione per poter sfruttare il suo metodo
 * prezzoPersona1izzazioni dato che le sue funzionalità sono spesso utili
 * all'interno del codice.
 */
 
public class Personalizzazione {
    private String tipoStampa;
    private String inchiostro;
    private float prezzoBase;
    private int numPersonalizzazioni;
    
    public void setTipoStampa(String ts) {
        this.tipoStampa=ts;
    }
    
    public void setInchiostro(String i) {
        this.inchiostro=i;
    }
    
    public void setPrezzoBase(float pb) {
        this.prezzoBase=pb;
    }
    
    public void setNumPersonalizzazioni(int np) {
        this.numPersonalizzazioni=np;
    }
    
    public String getTipoStampa() {
        return this.tipoStampa;
    }
    
    public String getInchiostro() {
        return this.inchiostro;
    }
    
    public float getPrezzoBase() {
        return this.prezzoBase;
    }
    
    public int getNumPersonalizzazioni() {
        return this.numPersonalizzazioni;
    }
    
    public Personalizzazione() {
        this.tipoStampa=null;
        this.inchiostro=null;
        this.prezzoBase=(float)1.50 ;
    }
    
    public Personalizzazione(String ts,String i,int np) {
        this.tipoStampa=ts;
        this.inchiostro=i;
        this.prezzoBase=(float)1.50;
    }
    
    /**Il metodo sotto implementato calcola il prezzo relativo a una personalizzazione
     * analizzando caso per caso il tipo di stampa e l'inchiostro utilizzato tramite uno switch case.
     * Inoltre, nel caso in cui venga fornita una stringa "scorretta" stampa a video un messaggio
     * di errore e permette di reinserire l'informazione corretta, che verrà rianalizzata per
     * poter calcolare il prezzo giusto (ciclo do while). 
     * @param ts
     * @param i
     * @param np
     * @return 
     * @throws ordini.IOExeption
     * @throws java.io.IOException
     */
    
    public float prezzoPersonalizzazioni(String ts,String i) throws IOException {
        float prezzo=prezzoBase;
        //BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
        if(ts!=null && i!=null) switch(ts) {
            case "serigrafia": if("colorato".equals(i)) prezzo+=0.50;
                               
                               break;
            case "termosaldato": prezzo+=1.00;
                                 if("colorato".equals(i)) prezzo+=0.50;

                                 break;
            case "transfer": prezzo+=2.00;
                             if("colorato".equals(i)) prezzo+=0.50;

                             break;
            case "digitale": prezzo+=3.00;
                             if("colorato".equals(i)) prezzo+=0.50;

                             break;

        }
        
        return prezzo;
    }
    
}