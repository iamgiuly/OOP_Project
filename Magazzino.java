package Magazzino;

public class Magazzino {
    private GestioneBorse b;
    private GestioneFelpe f;
    private GestioneGiubbotto g;
    private GestionePantalone j;
    private GestionePubblicità p;
    private GestioneOrdine o;
    private GestioneTshirt t;
    
    public Magazzino()
    {
     try{
     Class.forName("com.mysql.jdbc.Driver");
     b=new GestioneBorse();
     f=new GestioneFelpe();
     g=new GestioneGiubbotto();
     o=new GestioneOrdine();
     t=new GestioneTshirt();
     j=new GestionePantalone();
     p=new GestionePubblicità();
     }catch(ClassNotFoundException s)
     {
         System.out.println("Errore Connessione!");
         s.printStackTrace();
     }
    }
    
    public void inserisciFelpa(int idfelpa,String gen,String cv,int sv,int mv,int lv,int xlv,int cppv,int cernv,int tasv,float pbv,String Matv)
    {
        f.inserisciFelpa(idfelpa,gen,cv, sv, mv, lv, xlv, cppv, cernv, tasv, pbv, Matv);
    }
    
    public void cambiaQuantitaFelpa(int id,int q,String taglia) 
    {
        f.cambiaQuantitaFelpa(id, q, taglia);
    }
    
    public void eliminaFelpa(int id)
    {
        f.eliminaFelpa(id);
    }
    
    public void inserisciMaglia(int idmaglia,String gen,String col,int s,int m,int l,int xl,String scol,String maniche,String mat,float pb)
    {
        t.inserisciMaglia(idmaglia,gen,col,s,m,l,xl,scol,maniche,mat,pb);
    }
    
    public void inserisciBorsa(int idborsa, String mod,String col,float pb,int q)
    {
        b.inserisciBorsa(idborsa,mod, col, pb, q);
    }
    
    public void cambiaQuantitaBorsa(int id,int q)
    {
        b.cambiaQuantitaBorsa(id, q);
    }
    
    public void eliminaBorsa(int id)
    {
        b.eliminaBorsa(id);
    }   
    
    public void inserisciGiubbotto(int idgiubb,String gen,String mat,String col,int s,int m,int l,int xl,float pb)
    {
        g.inserisciGiubbotto(idgiubb,gen, mat, col, s, m, l, xl, pb);
    }
    
    public void cambiaQuantitaGiubbotto(int id,int q,String taglia)
    {
        g.cambiaQuantitaGiubbotto(id, q, taglia);
    }
    
    public void eliminaGiubbotto(int id)
    {
        g.eliminaGiubbotto(id);
    }
    
       public void inserisciPantalone(int idpant, String gen,String col,String mat,String mod,int s,int m,int l,int xl,float pb)
    {
        j.inserisciPantalone(idpant,gen, col, mat, mod, s, m, l, xl, pb);
    }
       
    public void cambiaQuantitaPantalone(int id,int q,String taglia)
    {
        j.cambiaQuantitaPantalone(id,q,taglia);
    }
    
     public void eliminaPantalone(int id)
     {
         j.eliminaPantalone(id);
     }
     
      
     
     public void inserisciPubblicità(int idpubb,String tc,String form,float sp,String col,float pb,int q)
     {
         p.inserisciPubblicità(idpubb,tc, form, sp, col, pb, q);
     }
     
     public void cambiaQuantitaPubblicità(int id,int q)
     {
         p.cambiaQuantitaPubblicità(id, q);
     }
     
     public void eliminaPubblicità(int id)
     {
         p.eliminaPubblicità(id);
     }
     
     public void inserisciOrdine(int id,String cliente,String data,int idmaglia,int idpers,int quantita,String taglia,int idb,int idf,int idgiubb,int idpantalone,int idpubb,String stato)
     {
         o.inserisciOrdine(id, cliente, data, idmaglia, idpers, quantita, taglia, idb, idf, idgiubb, idpantalone, idpubb, stato);
     }
     
     public void eliminaOrdine(int id)
     {
         o.eliminaOrdine(id);
     }
     
     public void visualizzaMagazzino()
     {
         try{
             b.visualizzaBorse();
             f.visualizzaFelpe();
             g.visualizzaGiubbotti();
             j.visualizzaPantaloni();
             t.visualizzaMaglie();
             p.visualizzaPubblicita();
         }catch(NullPointerException e)
         {
             System.out.println("Errore!");
             e.printStackTrace();
         }
     }
     
     public void visualizzaOrdini()
     {
         o.visualizzaOrdini();
     }
     
      public int getLastIDmaglia()
     {
         int i=t.getLastID();
         return i;
     }
      
       public int getLastIDborsa()
     {
         int i=b.getLastID();
         return i;
     }
       
        public int getLastIDfelpa()
     {
         int i=f.getLastID();
         return i;
     }
        
         public int getLastIDgiubb()
     {
         int i=g.getLastID();
         return i;
     }
         
          public int getLastIDpanta()
     {
         int i=j.getLastID();
         return i;
     }
          
           public int getLastIDpubb()
     {
         int i=p.getLastID();
         return i;
     }
     public int getLastIDord()
     {
         int i=o.getLastID();
         return i;
     }
}
