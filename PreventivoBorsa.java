/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;
import merce.Borsa;

/**
 *
 * @author Giulia Evangelisti
 */
public class PreventivoBorsa {
    float parzialeBorsa;
    Borsa borsa;
    
    //qui si potrebbe fare un'interfaccia del metodo visto che si ripete in tutte le classi Preventivo(Maglia,Borsa,...)
    public PreventivoBorsa(){
        this.parzialeBorsa=0;
    }
    
    public float ParzialeBorsa(String model, String color){
        borsa = new Borsa();
        parzialeBorsa+=borsa.CalcoloPU(model, color);
    return parzialeBorsa;
    }   
}
