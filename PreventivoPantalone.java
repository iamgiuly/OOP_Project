/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;
import merce.Pantalone;
/**
 *
 * @author Giulia Evangelisti
 */
public class PreventivoPantalone {
    float parzialePantalone;
    Pantalone pantalone;
    
    public PreventivoPantalone(){
        this.parzialePantalone=0;
    }
    
    public float ParzialePantalone(String color, String mo){
        pantalone = new Pantalone();
        parzialePantalone+=pantalone.CalcoloPU(color, mo);
    return parzialePantalone;
    }
}
