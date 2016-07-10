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
public class Abbigliamento extends Merce { 
//Ho lasciato comunque gli attributi Pers, NrPers, TipoStampa pensando che potreanno servire per "salvare" lo stato di un oggetto (maglia con specifiche personalizzazioni, ad esempio)
String genere;
String materiale;
boolean Pers;
int NrPers; 
String Tipostampa;


public void setGenere(String gen)
{
    this.genere=gen;
}
public String getGenere()
{
    return this.genere;
}
public void setMateriale(String m){
    this.materiale=m;
   
}
public String getMateriale(){
   return this.materiale;
}

public void setNumPers(boolean p, int num){
        if (p==true && num<=8)
            NrPers=num; 
        else if (num>8)
            System.out.println("Non e' possibile applicare più di 8 personalizzazioni"); 
                    else if(p==false)
                        System.out.println("Non sono previste personalizzazioni");}

public int getNumPers(){
        return this.NrPers;
    }

public void setTipostampa(String tipo){
    this.Tipostampa=tipo;
}

public String getTipostampa(){
    return this.Tipostampa;
    
}


public Abbigliamento()
{ 
    genere="";
    materiale="";
    NrPers=0;
    Pers=false; 
    prezzobase=(float)0.00;
    this.colore="Bianco";
    PrezzoUnitario=(float)0.00;
    this.Tipostampa=null;
}

    public Abbigliamento(String g, String m, int nrpers, boolean p, float pb, String s, String tipostampa)
{
    
    genere=g;
    materiale=m;
    NrPers=nrpers;
    Pers=p; 
    prezzobase=pb;
    this.colore=s;
    this.PrezzoUnitario=(float)0.00;
    this.Tipostampa=tipostampa;
}
}

