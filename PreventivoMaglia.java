/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;

import merce.Maglia;

/**
 *
 * @author Giulia Evangelisti
 */

public class PreventivoMaglia {
    float parzialeMaglia;
    Maglia maglia;
    
    public PreventivoMaglia(){
        this.parzialeMaglia=0;
    }
    
    public float ParzialeMaglia(String color, boolean scoll, boolean manica){
        maglia = new Maglia();
        parzialeMaglia+=maglia.CalcoloPU(color, scoll, manica);
    return parzialeMaglia;
    }
    
}
