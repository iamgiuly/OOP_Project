/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;
import java.io.*;


/**
 
 * @author Giulia Evangelisti
 */
public class Preventivo {
    
 String s1, s2; int quant;
 boolean fermo=false; boolean fermo2=false;
 float totale=0; float totabb=0; float totpubb=0;
 PreventivoAbbigliamento pabb;
 PreventivoPubblicita ppubb;
 
public void Preventivo(){
    this.totale=0;
}
        
/* public static void main(String[] args) throws IOExeption, IOException {
      Preventivo preventivo = new Preventivo();
      float result;
      result=preventivo.CalcoloPreventivo();
      System.out.println("Il totale del preventivo e' " + result);
  }*/
 
 float CalcoloPreventivo() throws IOExeption, IOException {
     
     //Per il momento ho inserito il "dialogo" con l'utente qui dentro ma si può sempre tirar fuori e passare s come parametro.
 
     BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
     
     do {
         
         System.out.println("Scegliere Abbigliamento o Pubblicità");
         System.out.println("Immettere stop per uscire");
         s1=br.readLine();
         fermo= ("Stop".equals(s1) | "stop".equals(s1));
         
         if(null != s1) switch (s1) {
             case "Abbigliamento":
                 do{
                     System.out.println("Inserire il capo (Maglia, Pantalone, Borsa, Giubbotto, Felpa");
                     s2=br.readLine();

                     pabb= new PreventivoAbbigliamento();
                     totale+=pabb.ParzialeAbbigliamento(s2);
                     System.out.println("Immettere stop per uscire dalla sezione Abbigliamento, qualsiasi altro carattere per continuare");
                     s2=br.readLine();
                     fermo2= "Stop".equals(s2) | "stop".equals(s2);
                 }while(fermo2==false);
                 break;
                 
             case "Pubblicita":
                 do{
                 ppubb= new PreventivoPubblicita();
                 totale+=ppubb.ParzialePubblicita();
                 System.out.println("Inserire stop per uscire dalla sezione Pubblicità, qualsiasi altro carattere per continuare");
                 s2=br.readLine();
                 fermo2= "Stop".equals(s2) | "stop".equals(s2);
                 }while(fermo2==false);
                 break;
                 
         }
         
 }while(fermo==false);
 return totale;    
}

}

