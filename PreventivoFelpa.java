/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;
import merce.Felpa;
/**
 *
 * @author Giulia Evangelisti
 */
public class PreventivoFelpa {
    float parzialeFelpa;
    Felpa felpa;
    
    public PreventivoFelpa(){
        this.parzialeFelpa=0;
    }
    
    public float ParzialeFelpa (String color, boolean cern, boolean capp, boolean tasche){
        felpa = new Felpa();
        parzialeFelpa+=felpa.CalcoloPU(color, cern, capp, tasche);
    return parzialeFelpa;
    }
}
