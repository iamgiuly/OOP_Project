/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;
import merce.Pubblicita;
import java.io.*;

/**
 *
 * @author Giulia Evangelisti
 */
public class PreventivoPubblicita {
 
float totalePubblicita;
Pubblicita pubb;


void PreventivoPubblicita(){
    this.totalePubblicita=0;
}

public float ParzialePubblicita() throws IOExeption, IOException{
   BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
           pubb = new Pubblicita();
           String colorecarta; 
           String formato; 
           boolean colorestampa=false; String colorestampa2; boolean fermo;//colore stampa 2 legge se l'utente mette a colori o in bianco e nero
           String spessore;
           
         System.out.println("Inserire il colore della carta");
         colorecarta=br.readLine();
         
         do{
         System.out.println("Inserire formato (A2 - A3 - A4 - 60x120");
         formato=br.readLine();
         fermo="A2".equals(formato)|"A3".equals(formato)|"A4".equals(formato)|"60x120".equals(formato);
         if (fermo==false)
             System.out.println("Errore! Riprovare...");
         }while(fermo==false);
         
         do{
         System.out.println("Inserire 'Colore' per stampa a colori 'Nero' per stampa in B N");
            colorestampa2=br.readLine();
            if("Colore".equals(colorestampa2))
            colorestampa=true;
            fermo="Colore".equals(colorestampa2)|"Nero".equals(colorestampa2);
            if (fermo==false)
             System.out.println("Errore! Riprovare...");           
             }while(fermo==false);
                    
         do{
         System.out.println("Inserire lo spessore");
            spessore=br.readLine();
         fermo="7mm".equals(spessore)|"8mm".equals(spessore)|"1cm".equals(spessore);
          if (fermo==false)
             System.out.println("Errore! Riprovare...");
         }while(fermo==false);
         
         totalePubblicita+=pubb.calcoloPU(colorecarta, formato, colorestampa, spessore);
         return totalePubblicita;
}
}
