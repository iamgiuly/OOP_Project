package merce;

/*
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Maglia extends Abbigliamento {
    
    boolean scollatura;
    boolean maniche;

    

    
    public void setScollatura(boolean b){
       scollatura=b;
    }
    public boolean getScollatura(){
        return this.scollatura;
    }
    public boolean setManiche(boolean b){
        return this.maniche;
    }
    public void getManiche(){
        System.out.println(maniche);
    }
    

public Maglia()
{ 
    this.genere="";
    this.materiale="";
    this.prezzobase=(float)5.00;
    this.colore="Bianco";
    this.scollatura=false;
    this.maniche=false;
    this.PrezzoUnitario=0;
    
}
public Maglia (String g, String m, String s, boolean scoll, boolean manica, float pu)
{
    
    this.genere=g;
    this.materiale=m;
    this.prezzobase=(float)5.00;
    this.colore=s;
    this.scollatura=scoll;
    this.maniche=manica;
    this.PrezzoUnitario=pu;
}

    public float CalcoloPU(String color, boolean scoll, boolean manica){
        PrezzoUnitario=prezzobase;
    if (!"Bianco".equals(color))
            this.PrezzoUnitario+=1.00;
    if(scoll==true)
        this.PrezzoUnitario+=1.00;
    if (manica==true)
        this.PrezzoUnitario+=1.00;

    return this.PrezzoUnitario;
        }
}