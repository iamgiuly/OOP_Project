/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattura;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * sarebbe bene AGGIUNGERE LE COSTANTI PER LA DIMENSIONE ARRAY E L'IVA MA MI Dà ERRORE! non ci sono riuscita
 * @author Giulia Evangelisti
 */
public class Fattura {
    String cliente;
    String data;
    Float [] importi = new Float[35]; //Array che serve per salvare gli importi calcolati finali e poi andarli a sommare (pensare se si può modificare in un array dinamico)
    int scorri=0; //Scorre l'array di salvataggio importi ed essendo un attributo non si azzera alla fine di ogni metodo
    AtomicInteger numerofattura;
    
    private static String FILEOUT = "C:/Workspace/Fattura.pdf";
    private static String FILEIN ="C:/Workspace/TemplateInvoice.pdf";
    int y=0; int x=0; //Coordinate che vanno a posizionare il testo dinamicamente nella tabella

    
    
public Fattura(){
    
}

public Fattura (String cli, String d){ //questo può non servire se lascio i metodi con passggio di parametri cliente, data e numerofattura ma per ora lo lascio
    this.cliente=cli;
    this.data=d;
    numerofattura = new AtomicInteger(1); 
}

public static void main (String args[]) throws SQLException, IOException, DocumentException{
    String cliente="Mario Rossi";    
    PdfReader pdfReader = new PdfReader(FILEIN); 
    PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(FILEOUT));
    String data="2016-06-01";
    AtomicInteger numerofattura = new AtomicInteger(1); //COME INCREMENTARLO?
    Fattura fattura = new Fattura(cliente, data);
    fattura.setFattura(cliente, data, numerofattura, pdfStamper);
    pdfReader.close();
    

}

public void setFattura(String cliente, String data, AtomicInteger numerofattura, PdfStamper s) throws SQLException, DocumentException, FileNotFoundException, IOException{ //Ho messo che il numero della fattura va passato come parametro, voglio capire se si può fare altrimenti (con un contatore)
    for (int i=0; i<35; i++){ //Inizializza gli elementi dell'array a 0 (che altrimenti sarebbero a null e darebbe errore se ci sono dei campi vuoti)
        this.importi[i]=(float)0;
    }
    try
 {
       
    s.getAcroFields().setField("Num", numerofattura.toString()); // sistemare
    s.getAcroFields().setField("Data", data);
    s.getAcroFields().setField("Nome", cliente);   
    PdfContentByte content = s.getUnderContent(1);//1 for the first page
    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
    content.beginText();
    content.setFontAndSize(bf, 7);
    inserisciDatiMaglie(cliente, data, s);
    inserisciDatiBorse(cliente, data, s);
    inserisciDatiPanta(cliente, data, s);
    setImporti(s);
    content.endText();
    s.close();
    
  }
   catch (IOException | DocumentException e)
  {
      
      }
}


public void inserisciDatiMaglie(String cliente, String data, PdfStamper s) throws SQLException, IOException, DocumentException{
    ArrayMaglia arraymaglia=new ArrayMaglia(); 
    arraymaglia.ArrayIDmaglia(cliente, data);
    
    PdfContentByte content = s.getUnderContent(1);//1 for the first page
    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
    content.setFontAndSize(bf, 7);
    
    
    for (int j=0; j<arraymaglia.IDmaglie.length; j++){
        arraymaglia.accessoMaglie(j);
        String convertitore=String.valueOf(arraymaglia.importoMaglia(j)); //Serve per convertire il ritorno del metodo importoUnitarioMaglia (float) a string per stamparlo, è solo un appoggio
        this.importi[scorri++]=arraymaglia.importoMaglia(j); //salva l'importo maglia nella prima posizione array i cui elementi poi andranno sommati per calcolare il totale
        
        for(int z=0; z<arraymaglia.riga.length; z++){
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, arraymaglia.riga[z]+ "   ", 95+x,538-y,0);
        x+=97; //sposta la coordinata y ad ogni stampa
            }
        
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, "Maglia", 95+x,538-y,0);
        x+=97;
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, convertitore , 95+x,538-y,0);
        
        
    x=0;
    y+=20;
    }
    
}

public void inserisciDatiBorse(String cliente, String data, PdfStamper s) throws SQLException, IOException, DocumentException{
    ArrayBorsa arrayborsa=new ArrayBorsa();
    arrayborsa.ArrayIDborsa(cliente, data);
    
    PdfContentByte content = s.getUnderContent(1);//1 for the first page
    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
    content.setFontAndSize(bf, 7);
    
    
    for (int j=0; j<arrayborsa.IDborse.length; j++){
        arrayborsa.accessoBorse(j);
        for(int z=0; z<arrayborsa.riga.length; z++){
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, arrayborsa.riga[z]+ "   ", 95+x,538-y,0);
        x+=97; //sposta la coordinata y ad ogni stampa
        
    }
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, "Borsa", 95+x,538-y,0);
        
    x=0;
    y+=20;
    }
//MI calcolar il prezzo totale man mano che calcolo i preventivi lo agggiungo!

}
public void inserisciDatiPanta(String cliente, String data, PdfStamper s) throws SQLException, IOException, DocumentException{
    ArrayPantalone arraypanta=new ArrayPantalone(); 
    arraypanta.ArrayIDpantalone(cliente, data);
    
    PdfContentByte content = s.getUnderContent(1);//1 for the first page
    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
    content.setFontAndSize(bf, 7);
    
    
    for (int j=0; j<arraypanta.IDpantaloni.length; j++){
        arraypanta.accessoPantaloni(j);
        for(int z=0; z<arraypanta.riga.length; z++){
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, arraypanta.riga[z]+ "   ", 95+x,538-y,0);
        x+=97; //sposta la coordinata y ad ogni stampa
        
    }
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, "Pantalone", 95+x,538-y,0);
        
    x=0;
    y+=20;
    }
    
}

/*Calcola imponibile e totale con iva*/
public void setImporti(PdfStamper s) throws IOException, DocumentException{
    float imponibile=0; float totiva;
    for (int i=0; i<35; i++)
        imponibile+=this.importi[i];
    String convertitore = String.valueOf(imponibile);
    s.getAcroFields().setField("Imponibile", convertitore);
    totiva=(float) (1.22*imponibile); //calcola l'iva sull'imponibile
    convertitore=String.valueOf(totiva); //converte totiva in stringa per stamparla su pdf
    s.getAcroFields().setField("IVA", "22%");
    s.getAcroFields().setField("Totale", convertitore);
}

} 

