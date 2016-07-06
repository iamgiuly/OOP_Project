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

public class Merce {

    String colore; 
    float PrezzoUnitario;
    float prezzobase;
    boolean coloreStampa; //True se la stampa è a colori , false se è in bianco e nero
    
    /* Di seguito abbiamo tutti i metodi set e get necessari per accedere agli attributi della classe Merce */
    

    public void setcolore(String c){
        colore=c; 
    }
    public String getcolore(){
        return this.colore;
    }
    
    public boolean getColoreStampa(){
        return this.coloreStampa;
    }
    public void setColoreStampa(boolean cs){
        this.coloreStampa=cs;
    }
    
    public void setPU(float pu){
        this.PrezzoUnitario=pu;
    }
    
    public float getPU(){
        return this.PrezzoUnitario;
    }
    
    public void setPB(float pb){
        this.prezzobase=pb;
    }
    
    public float gelPB(){
        return this.prezzobase;
    }
    
    /*Costruttore con parametri */
    public Merce(String colore, float pu, float pb, boolean colorestampa){
        this.colore=colore;
        this.PrezzoUnitario=pu;
        this.prezzobase=pb;
        this.coloreStampa=colorestampa;
    }
    
    /* Costruttore senza paramentri*/
    public Merce(){
        this.colore="Bianco";
        this.PrezzoUnitario=(float)0.00;
        this.prezzobase=(float)0.00;
        this.coloreStampa=false;
        
    }}
