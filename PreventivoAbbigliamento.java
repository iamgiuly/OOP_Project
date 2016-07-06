package ordini;
import java.io.*;
/**
 *
 * @author Giulia Evangelisti
 */
public class PreventivoAbbigliamento {

PreventivoMaglia pmaglia;
PreventivoPantalone ppanta;
PreventivoGiubbotto pgiubb;
PreventivoFelpa pfelpa;
PreventivoBorsa pborsa;
Personalizzazione pers;
float totaleAbbigliamento;

public PreventivoAbbigliamento(){
    this.totaleAbbigliamento=0;
}

public float ParzialeAbbigliamento(String s) throws IOExeption, IOException  { 
    
boolean p=false; int nrpers=0; String i="nero"; String tipo="Serigrafia"; boolean fermo; String StringPers;

// E' richiesta l'inizializzazione delle variabili relative alla classe personalizzazione, p indica se sono presenti o meno personalizzazioni
//avendola impostata a false, posso inizializzare le altre a quelasiasi valore in quanto se p=false non entrerà mai nel ramo per calcolare
//il prezzo relativo alla personalizzazione

String color; 
boolean scoll=false; boolean manica=false; String pp; String mm; String ss;
boolean cern=false; boolean tasche=false; boolean capp=false;
String model; String materiale;
int quant;
    
    //pp, mm, ss servono per comunicare con l'utente e chiedergli se ci sono maniche, personalizzazioni e scollatura a V 
    //lui mi restituirà una String (Sì o No) e con un if la converto in boolean, purtroppo il metodo di java.io br.read() non 
    //ammette la conversione in boolean, se ci vengono in mente modi per farlo ben venga!!
    
    BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    
    //La richiesta all'utente sulle caratteristiche delle personalizzazioni viene fatta un'unica volta all'inizio (così da non ripetere il codice)
    //prima di entrare nel ramo specifico dello switch
    System.out.println("Inserire la quantità ");
    quant=br.read();
    do{
                 fermo=true;
                 System.out.println("Sono presenti personalizzazioni? (Si/No)");
                 StringPers=br.readLine();
      
                 fermo= ("Si".equals(StringPers) | "No".equals(StringPers));
                 
                 if("Si".equals(StringPers))
                     p=true;
             if (fermo==false){
             System.out.println("Errore! Riprovare...");  }           
     }while(fermo==false);
            
            if (p==true){
            pers = new Personalizzazione();
            do{
            fermo=true;
            System.out.println("Inserire il numero di personalizzazioni ");
            nrpers= (int) br.read();
            fermo=nrpers>0;
            }while(fermo==false);
            
            do{
            fermo=true;
            System.out.println("Inserire tipologia di stampa usata ");
            tipo=br.readLine();
            fermo=("serigrafia".equals(tipo) | "termosaldato".equals(tipo) | "transfer".equals(tipo) | "digitale".equals(tipo));
            if (fermo==false) {
                         System.out.println("Errore! Riprovare...");    }
            }while(fermo==false);
            
            do{
            System.out.println("Inserire 'colorato' se la stampa è a colori, 'nero' se la stampa è in bianco e nero");
            i=br.readLine();
            fermo=("colorato".equals(i) | "nero".equals(i));
                     if (fermo==false)
             System.out.println("Errore! Riprovare...");
            }while(fermo==false);
            
            }
            
    if(s!=null) switch(s){

        case "Maglia":
        case "maglia":
            
            pmaglia= new PreventivoMaglia();
            
            System.out.println("Scrivere il colore della maglia ");
            color=br.readLine();
  
            //Per ogni dettaglio che va ad aggiungere nella maglia (o nel pantalone, etc..) (maniche, scollo a V) si è inserito un DO post condizionale
            //la cui condizione di uscita è che la stringa combaci con Sì o No. Così si evita che l'utente inserisca una stringa differente e il programma 
            //prosegui
            
            do{                
            System.out.println("La maglia è a maniche lunghe? (Sì/No)");
            mm= br.readLine();
            
            fermo= ("Si".equals(mm) | "No".equals(mm));
            
            if(fermo==false)
                System.out.println("Errore!Inserisci Si o No");
            else if("Sì".equals(mm))
                 manica=true; //Se la stringa non è uguale a Sì e fermo = true significa che la stringa è uguale a false -> Perciò non ci sono maniche lunghe. (era inizializzata a false)
                                 
            }while(fermo==false);
            
            do{                
            System.out.println("La maglia ha lo scollo a V?");
            ss=br.readLine();
            fermo= ("Si".equals(ss) | "No".equals(ss));
            
            if(fermo==false)
                System.out.println("Errore, Inserire si o no");
            else if("Sì".equals(ss))
                scoll=true;
            
            }while(fermo==false);     
            
            totaleAbbigliamento+= pmaglia.ParzialeMaglia(color, scoll, manica);
            
            if (p==true){
            totaleAbbigliamento+=pers.prezzoPersonalizzazioni(tipo, i, nrpers);
            }
            return totaleAbbigliamento*quant;
                                 
        case "Pantalone":
        case "pantalone":
            
            ppanta = new PreventivoPantalone();
            
            System.out.println("Scrivere il colore del pantalone ");
            color=br.readLine();  
            
            do{               
            System.out.println("Inserire modello (Lunghi/Corti) ");
            model=br.readLine();
            fermo= ("Lunghi".equals(model) | "Corti".equals(model));
            if(fermo==false)
                System.out.println("Errore!Inserisci Lunghi o corti");                                
            }while(fermo==false);

            totaleAbbigliamento+=ppanta.ParzialePantalone(color, model);
            if (p==true){
            totaleAbbigliamento+=pers.prezzoPersonalizzazioni(tipo, i, nrpers);           
            }
            return totaleAbbigliamento*quant;
           
        
        
        case "Giubbotto":
        case "giubbotto":
            
            System.out.println("Scrivere il colore del giubbotto ");
            color=br.readLine();  
            
            do{
            System.out.println("Inserire maetriale (Pelle/Jeans) ");
            materiale=br.readLine();
            fermo= ("Pelle".equals(materiale) | "Jeans".equals(materiale));
            if(fermo==false)
                System.out.println("Errore!Inserisci Pelle o Jeans");                                
            }while(fermo==false);
            
            totaleAbbigliamento+=pgiubb.ParzialeGiubbotto(color, materiale);
            
            if (p==true){
            totaleAbbigliamento+=pers.prezzoPersonalizzazioni(tipo, i, nrpers);           
            }
            
            return totaleAbbigliamento*quant;
      
        case "Felpa":
        case "felpa":
            
            pfelpa = new PreventivoFelpa();
            
            System.out.println("Scrivere il colore dela felpa ");
            color=br.readLine();
         //Posso riutilizzare le variabili d'appoggio,  ss = cerniera /mm = tasche /pp = cappuccio come nella maglia
         //in quanto sono variabili momentanee che servono al calcolo del capo singolo che andrà poi sommato nel Preventivo vero e prorpio 
         
            do{
            System.out.println("La felpa ha il cappuccio?(Sì/No)");
            pp=br.readLine();
            fermo= ("Si".equals(pp) | "No".equals(pp));
            
            if(fermo==false)
                System.out.println("Errore, Inserire si o no");
            else if("Sì".equals(pp))
                capp=true;
            
            }while(fermo==false);  
            
            do{
            System.out.println("La felpa ha le tasche? (Sì/No)");
            mm=br.readLine();
            fermo= ("Si".equals(mm) | "No".equals(mm));
            
            if(fermo==false)
                System.out.println("Errore, Inserire si o no");
            else if("Sì".equals(mm))
                tasche=true;
            }while(fermo==false);
            
            do{
            System.out.println("La felpa ha la cerniera? (Sì/No)");
            ss=br.readLine();
            fermo= ("Si".equals(ss) | "No".equals(ss));
            
            if(fermo==false)
                System.out.println("Errore, Inserire si o no");
            else if("Sì".equals(ss))
                cern=true;
            }while(fermo==false);
            
            totaleAbbigliamento+=pfelpa.ParzialeFelpa(color, cern, capp, tasche);
            
            if (p==true){
            totaleAbbigliamento+=pers.prezzoPersonalizzazioni(tipo, i, nrpers);           
            }
            return totaleAbbigliamento*quant;
        
        
        case "Borsa":
        case "borsa":
            
            pborsa= new PreventivoBorsa();
            
            System.out.println("Scrivere il colore della borsa ");
            color=br.readLine();  
            
            do{
            System.out.println("Inserire il modello (Zaino/Tracolla) ");
            model=br.readLine();
            fermo= ("Zaino".equals(model) | "Tracolla".equals(model));
            if(fermo==false)
                System.out.println("Errore!Inserisci Zaino o Tracolla");                                
            }while(fermo==false);

            totaleAbbigliamento+=pborsa.ParzialeBorsa(model, color);
            if (p==true){
            totaleAbbigliamento+=pers.prezzoPersonalizzazioni(tipo, i, nrpers);           
            }
            return totaleAbbigliamento*quant;
            
    
    }
    return totaleAbbigliamento*quant;

}}

