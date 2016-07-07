package Interfaccia;

import javax.swing.*;
import magazzino.*;

public class CherryQueen {
    public static void main(String args[]){
         
        /*Blocco try...catch per impostare lo stile grafico della
     finestra. Qui si usa lo stile del sistema operativo su
     cui si usa l'applicazione. Dato che questa parte di codice
     potrebbe generare un errore (non si riesce a impostare lo stile),
     si intercetta nel blocco catch e se tale errore si verifica
     viene fatto apparire un messageDialog che ci avverte.*/
   try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       }
   catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
    JOptionPane.showMessageDialog(null,"Impossibile impostare lo stile: " + e);
    }
   
     MainFrame CherryQueen;
     CherryQueen = new MainFrame();
    
}}