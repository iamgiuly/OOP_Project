/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordini;
import merce.Giubbotto;

/**
 *
 * @author Giulia Evangelisti
 */
public class PreventivoGiubbotto {
        float parzialeGiubbotto;
    Giubbotto giubb;
    
    public PreventivoGiubbotto(){
        this.parzialeGiubbotto=0;
    }
    
    public float ParzialeGiubbotto(String color, String materiale){
        giubb = new Giubbotto();
        parzialeGiubbotto+=giubb.CalcoloPU(color, materiale);
    return parzialeGiubbotto;
    }
}
