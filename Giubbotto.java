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
public class Giubbotto extends Abbigliamento {
    
    public Giubbotto()
    {
        this.genere="NotDefined";
        this.materiale="Pelle";
        this.colore="Nero";
        this.prezzobase= (float)15.00;
        this.PrezzoUnitario=0;
    }
    public Giubbotto (String g, String m, String color, float pu)
    {
        this.genere=g;
        this.materiale=m;
        this.colore=color;
        this.prezzobase=(float)15.00;
        this.PrezzoUnitario=pu;
    }
    
        public float CalcoloPU(String color, String materiale){
            PrezzoUnitario=prezzobase;
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;
    if ("Pelle".equals(materiale))
            this.PrezzoUnitario+=7.00;

    return this.PrezzoUnitario;
        }

}