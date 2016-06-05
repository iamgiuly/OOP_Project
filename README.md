# Classe Merce
package merce;
/**
 *
 * @author Giulia Evangelisti
 */
public class Merce {

    int quantita;
    
    void setquantita(int quant){
        this.quantita=quant;}
    
    void getquantita(){
System.out.println(this.quantita);    }

    Merce(int quant){
        this.quantita=quant; //Costruttore con parametri
       
    }
    Merce(){
        this.quantita=-1; //Costruttore senza paramentri, inizializza la variabile a -1
    }}
