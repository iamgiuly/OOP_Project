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
Pubblicita pubb=new Pubblicita();


void PreventivoPubblicita(){
    this.totalePubblicita=0;
}

public float TotalePubblicita(int quantita,String colorecarta,String formato,boolean colorestampa,String spessore) throws IOException,NullPointerException{
         totalePubblicita=quantita*(pubb.calcoloPU(colorecarta, formato, colorestampa, spessore));
         return totalePubblicita;
}

}
