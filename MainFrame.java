package Interfaccia;

import javax.swing.*;

/**
 *
 * @author Alessia
 */
public class MainFrame extends JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        home = new javax.swing.JMenu();
        mag = new javax.swing.JMenu();
        new_merce = new javax.swing.JMenu();
        fatt = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        ord = new javax.swing.JMenu();
        prev = new javax.swing.JMenu();
        show_ord = new javax.swing.JMenu();
        mod_ord = new javax.swing.JMenu();
        del_ord = new javax.swing.JMenu();
        new_ord = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CherryQueen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 757, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );

        home.setText("Home");
        jMenuBar1.add(home);

        mag.setText("Magazzino");

        new_merce.setText("Inserire nuova merce");
        mag.add(new_merce);

        jMenuBar1.add(mag);

        fatt.setText("Fattura");

        jMenu6.setText("Emetti fattura");
        fatt.add(jMenu6);

        jMenuBar1.add(fatt);

        ord.setText("Ordini");

        prev.setText("Calcolo preventivo");
        ord.add(prev);

        show_ord.setText("Visualizza ordini");
        ord.add(show_ord);

        mod_ord.setText("Modifica ordine");
        ord.add(mod_ord);

        del_ord.setText("Elimina ordine");
        ord.add(del_ord);

        new_ord.setText("Inserisci nuovo ordine");
        ord.add(new_ord);

        jMenuBar1.add(ord);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JMenu del_ord;
    private javax.swing.JMenu fatt;
    private javax.swing.JMenu home;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu mag;
    private javax.swing.JMenu mod_ord;
    private javax.swing.JMenu new_merce;
    private javax.swing.JMenu new_ord;
    private javax.swing.JMenu ord;
    private javax.swing.JMenu prev;
    private javax.swing.JMenu show_ord;
    // End of variables declaration                   
}