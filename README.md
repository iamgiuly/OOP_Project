# Classe Abbigliamento
package abbigliamento;
import merce.*; //qui mi dà errore! Non mi trova il package

/**
 *
 * @author Giulia Evangelisti
 */
public class Abbigliamento extends Merce { //Qui dà errore

    char taglia;
    String genere;
    int IDabb;
      void setTaglia (char tg){
        this.taglia=tg;}
      
    void getTaglia(){
        System.out.println(this.taglia);
    }
}
