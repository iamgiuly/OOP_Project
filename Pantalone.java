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
public class Pantalone extends Abbigliamento {
    String model;
    
public void setMod (String mod){
    this.model=mod;
}

public String geMod(){
    return this.model;
}

public Pantalone()
{ 
    this.genere="";
    this.materiale="";
    this.prezzobase=(float)6.00;
    this.colore="Bianco";
    this.model="notdefined";
    this.PrezzoUnitario=0;
    
}

public Pantalone(String g, String m, String s, String mod, float pu)
{ 
    this.genere=g;
    this.materiale=m;
    this.prezzobase=(float)6.00;
    this.colore=s;
    this.model=mod;
    this.PrezzoUnitario=pu;
}
    public float CalcoloPU(String color, String mo){
        PrezzoUnitario=prezzobase;
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;
    if ("Lunghi".equals(mo))
        this.PrezzoUnitario+=2.00;

    return this.PrezzoUnitario;
        }

}