/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazzino;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Sara
 */
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
    
    public void inserisciFelpa(int id,String gen,String cv,int sv,int mv,int lv,int xlv,int cppv,int cernv,int tasv,float pbv,String Matv)
    {
        f.inserisciFelpa(id,gen,cv, sv, mv, lv, xlv, cppv, cernv, tasv, pbv, Matv);
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
    
    public void inserisciBorsa(int id,String mod,String col,float pb,int q)
    {
        b.inserisciBorsa(id,mod, col, pb, q);
    }
    
    public void cambiaQuantitaBorsa(int id,int q)
    {
        b.cambiaQuantitaBorsa(id, q);
    }
    
    public void eliminaBorsa(int id)
    {
        b.eliminaBorsa(id);
    }   
    
    public void inserisciGiubbotto(int id,String gen,String mat,String col,int s,int m,int l,int xl,float pb)
    {
        g.inserisciGiubbotto(id,gen, mat, col, s, m, l, xl, pb);
    }
    
    public void cambiaQuantitaGiubbotto(int id,int q,String taglia)
    {
        g.cambiaQuantitaGiubbotto(id, q, taglia);
    }
    
    public void eliminaGiubbotto(int id)
    {
        g.eliminaGiubbotto(id);
    }
    
       public void inserisciPantalone(int id,String gen,String col,String mat,String mod,int s,int m,int l,int xl,float pb)
    {
        j.inserisciPantalone(id,gen, col, mat, mod, s, m, l, xl, pb);
    }
       
    public void cambiaQuantitaPantalone(int id,int q,String taglia)
    {
        j.cambiaQuantitaPantalone(id,q,taglia);
    }
    
     public void eliminaPantalone(int id)
     {
         j.eliminaPantalone(id);
     }
     
      
     
     public void inserisciPubblicità(int id,String tc,String form,String sp,String col,float pb,int q)
     {
         p.inserisciPubblicità(id,tc, form, sp, col, pb, q);
     }
     
     public void cambiaQuantitaPubblicità(int id,int q)
     {
         p.cambiaQuantitaPubblicità(id, q);
     }
     
     public void eliminaPubblicità(int id)
     {
         p.eliminaPubblicità(id);
     }
     
     public void inserisciOrdine(String cliente,String data,int idmaglia,int idpers,int quantita,String taglia,int idb,int idf,int idgiubb,int idpantalone,int idpubb,String stato)
     {
         o.inserisciOrdine(cliente, data, idmaglia, idpers, quantita, taglia,idb, idf, idgiubb, idpantalone, idpubb, stato);
     }
     
     public void eliminaOrdine(int id)
     {
         o.eliminaOrdine(id);
     }
     
      public void selezionaOrdine(String cliente, String date)
      {
          o.selezionaOrdine(cliente, date);
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
     
     public DefaultTableModel resultSetToTableModel(ResultSet row) throws SQLException
    {
        DefaultTableModel model=null;
    ResultSetMetaData meta= row.getMetaData();
    if(model==null) model= new DefaultTableModel();
    String cols[]=new String[meta.getColumnCount()];
    for(int i=0;i< cols.length;++i)
        {
        cols[i]= meta.getColumnLabel(i+1);
        }

    model.setColumnIdentifiers(cols);

    while(row.next())
        {
        Object data[]= new Object[cols.length];
        for(int i=0;i< data.length;++i)
             {
             data[i]=row.getObject(i+1);
             }
        model.addRow(data);
        }
    return model;
    }
     
     public ResultSet visualizzaOrdini()
     {
         ResultSet rs=o.visualizzaOrdini();
         return rs;
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
         int i=o.getLastIDord();
         return i;
     }
     
     public ResultSet CercaOrdine(int id) throws IOException, SQLException{
         ResultSet rs=o.CercaOrdine(id);
         return rs;
     
	}
     
      public void ModificaOrdine(int id,String cliente,String data,int idmaglia,int idpers,int quantità,String taglia,int idborsa,int idfelpa,int idgiubb,int idpant,int idpubb,String stato) throws SQLException{
        o.ModificaOrdine(id, cliente, data, idmaglia, idpers, quantità, taglia, idborsa, idfelpa, idgiubb, idpant, idpubb, stato);
        }
      public void aggiornaMagazzino()
      {
          t.aggiornaMagazzinoMaglie();
          b.aggiornaMagazzinoBorse();
          f.aggiornaMagazzinoFelpe();
          p.aggiornaMagazzinoPubblicita();
          j.aggiornaMagazzinoPantaloni();
          g.aggiornaMagazzinoGiubbotti();
      }
 
}
