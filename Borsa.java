/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merce;

/**
 *
 * @author Giulia Evangelisti
 */
public class Borsa extends Abbigliamento {
    public modello model;

    public enum modello {Tracolla, Zaino, Notdefined} //per ora lascio enum ma penso sia più facile metterlo String
    
    public void setMod(String m){  //setto il modello, ricordando che in Java enum è una vera e propria classe
        this.model=modello.valueOf(m);
    }
    public modello getMod(){
        return this.model;
    }
    
    /*Costruttore senza parametri */
    public Borsa()
    {
        this.prezzobase=(float)7.00;
        this.genere="notdefined";
        this.materiale="cotone";
        this.colore="Nero";
        this.model=modello.Notdefined;
        PrezzoUnitario=0;
    }
    
    /* Costruttore con parametri */
    public Borsa(String g, String m, String mo, String color){  //Ho rimosso la possibilità di inserire qui il prezzo base
                                                                                                    //lo si può fare solo richiamano la set prezzo base()
    this.genere=g;
    this.materiale=m;
    this.prezzobase=(float)7.00; //Il prezzo base viene automaticamente settato a 7 euro
    this.model=modello.valueOf("mo");
    this.colore=color;
    PrezzoUnitario=0;
    }
    public float CalcoloPU(String mo, String color){
        PrezzoUnitario=prezzobase;
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;

    if ("Zaino".equals(mo)){
        this.PrezzoUnitario+=3.00;}
    return this.PrezzoUnitario;
        }

   
}
   