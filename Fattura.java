/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattura;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Giulia Evangelisti
 */
public class Fattura {
    String cliente;
    String data;
    AtomicInteger numerofattura;
    
    private static String FILEOUT = "C:/Workspace/Fattura.pdf";
    private static String FILEIN ="C:/Workspace/TemplateInvoice.pdf";
    int y=0; int x=0; //Coordinate che vanno a posizionare il testo dinamicamente nella tabella

    
    
public Fattura(){
    
}

public Fattura (String cli, String d){
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
    content.endText();
    s.close();
    
  }
   catch (IOException | DocumentException e)
  {
      
      }
}
//IDEA: fare un metodo inserisciDati per ogni Array diverso (uno per arraymaglia, uno per arrayborsa e così via)
public void inserisciDatiMaglie(String cliente, String data, PdfStamper s) throws SQLException, IOException, DocumentException{
    ArrayMaglia arraymaglia=new ArrayMaglia(); 
    arraymaglia.ArrayIDmaglia(cliente, data);
    
    PdfContentByte content = s.getUnderContent(1);//1 for the first page
    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
    content.setFontAndSize(bf, 7);
    
    
    for (int j=0; j<arraymaglia.IDmaglie.length; j++){
        arraymaglia.accessoMaglie(j);
        for(int z=0; z<arraymaglia.riga.length; z++){
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, arraymaglia.riga[z]+ "   ", 95+x,538-y,0);
        x+=97; //sposta la coordinata y ad ogni stampa
        
    }
        content.showTextAligned(PdfContentByte.ALIGN_LEFT, "Maglia", 95+x,538-y,0);
        
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

}} 

