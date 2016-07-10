package merce;

/**
 *
 * @author Giulia
 */
public class Pubblicita extends Merce {
    String TipoCarta;
    String formato;
    String spessore; 
    float parzialeSpessore;
    //il tipo stampa è uno solo, il numero stampe è uno, l'ho considerato così poi si può sempre modificare

    public String getTipoCarta()
    {
        return this.TipoCarta;
    }
    public void setTipoCarta(String tc)
    {
        this.TipoCarta=tc;
    }
    public String getSpessore()
    {
        return this.spessore;
    }
    public void setSpessore(String sp)
    {
        this.spessore=sp;
    }
    public String getColoreCarta(){
        return this.colore;
    }
    public void setColoreCarta(String cc){
        this.colore=cc;
    }
    public String getFormato()
    {
        return this.formato;
    }
    public void setFormato(String form)
    {
        this.formato=form;
    }


            
    public Pubblicita()
    {
        this.TipoCarta="Liscia";
        this.formato="A4";
        this.coloreStampa=false;
        this.spessore="7 mm";
        this.colore="Bianco";
        this.parzialeSpessore=0;
    }
    public Pubblicita(String tc, String color, String formato, boolean cs, String sp)
    {
        this.TipoCarta=tc;
        this.formato=formato;
        this.coloreStampa=cs;
        this.spessore=sp;
        this.colore=color;
        this.parzialeSpessore=0;
    }
    public float calcoloSP(String s){
        if(s!=null) switch(s){
            case "7 mm":
                parzialeSpessore+=0.60;
                break;
            case "10 mm":
                parzialeSpessore+=0.80;
                break;
            case "1 cm":
                parzialeSpessore+=1.30;
        }
        return parzialeSpessore;
    }
    
    public float calcoloPU(String cc, String formato, boolean cs, String sp){
       if(!"Bianco".equals(cc))
           PrezzoUnitario=prezzobase; 
       if(formato!=null) switch (formato){
           case "A2":
               PrezzoUnitario+=2.5;
               if (cs==true)
                   PrezzoUnitario+=3.00;
               PrezzoUnitario+=this.calcoloSP(sp);
               break;
           case "A3":
               PrezzoUnitario+=2.00;
                if (cs==true)
                   PrezzoUnitario+=3.00;
               PrezzoUnitario+=this.calcoloSP(sp);              
               break;
           case "A4":
               PrezzoUnitario+=1.50;
               if (cs==true)
                   PrezzoUnitario+=3.00;
               PrezzoUnitario+=this.calcoloSP(sp);               
               break;
           case "60x120":
               PrezzoUnitario+=5.00;
               if (cs==true)
                   PrezzoUnitario+=3.00;
               PrezzoUnitario+=this.calcoloSP(sp);               
               break;    
               }
return PrezzoUnitario;}
    
       
}