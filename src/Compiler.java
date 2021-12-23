
import java.util.StringTokenizer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Santillanes
 */
public class Compiler extends javax.swing.JFrame{

    
    
    /**
     * Creates new form compiler
     */
    
    String op = "[]{}()";
    String log = "+-*/%^";
    String cond = "MENORMAYORMAIGUALMEIGUALDIFNOYYOOOE";
    String tipoDato = "duvalinbocadinchiclerellerindocachetadamazapanSkwinklehersheys";
    String palRes = "abiertocerradobolsitapiñataticowinniSISINOTicket";
    String simbRes = "=:\"_";
    boolean band = false;
    String aux = "";
    String inicio;
    int cont, linea = 1;
    String[] arre;
    
    private void ifBan(boolean ban, int idx, int lin){
        if (!aux.isEmpty()) {
            inicio = String.valueOf(aux.charAt(0));
        }
        String letras = "qwertyuiopñlkjhgfdsazxcvbnmQWERTYUIOPÑLKJHGFDSAZXCVBNM";
        if (ban) {
            if (cond.contains(aux)) {
                System.out.println("Operador condicional: "+aux);
            }else if (tipoDato.contains(aux)) {
                System.out.println("Tipo de dato: "+aux);
            }else if (palRes.contains(aux)) {
                System.out.println("Palabra reservada: "+aux);
            }else if(letras.contains(inicio)){
                System.out.println("Identificador: "+aux);
            }else{
                int n = aux.length()+1;
                for (int j = idx-n; j >= 0; j--) {
                    if (!arre[j].isBlank()) {
                        if (arre[j].equals("=")) {
                            System.out.println("Valor de variable: "+aux);
                        }else{
                            System.out.println("Símbolo desconocido en la línea "+lin+": "+aux);
                            cont++;
                        }
                        //System.out.println("arre[j] = " + arre[j]);
                        break;
                    }
                }
                
            }
            band = false;
            aux = "";
        }
    }
    
    
    private boolean analizarLexico(String code){
        
        cont = 0;
        arre = code.split("");
        
        for (int i = 0; i < arre.length; i++) {
            String letra = arre[i];
            
            //     []+MENOR-duvalin()
            if (op.contains(letra)) {
                ifBan(band,i,linea);
                
                if (letra.equals("(") && arre[i+1].equals(":")) {
                    System.out.println("Inicio comentario: (:");
                    String txt = "";
                    for (int j = i+2; j < arre.length; j++) {
                        if (arre[j].equals(":") && arre[j+1].equals(")")) {
                            i = j+1;
                            break;
                        }else{
                            txt += arre[j];
                        }
                    }
                    if (!txt.equals("")) {
                        System.out.println("Texto: "+txt);
                    }
                    System.out.println("Fin comentario: :)");
                }else{
                    System.out.println("Operador: "+letra);
                }
                
            }else if (log.contains(letra)) {
                ifBan(band,i,linea);
                System.out.println("Op lógico: "+letra);
                
            }else if (simbRes.contains(letra)) {
                ifBan(band,i,linea);
                System.out.println("Simbolo reservado: "+letra);
                
                if (letra.equals("\"")) {
                    String txt = "";
                    for (int j = i+1; j < arre.length; j++) {
                        if (arre[j].equals("\"")) {
                            i = j;
                            break;
                        }else{
                            txt += arre[j];
                        }
                    }
                    if (!txt.equals("")) {
                        System.out.println("Texto: "+txt);
                    }
                    System.out.println("Símbolo reservado: \"");
                }
                
            }else{
                if (!letra.equals(" ") && !letra.equals("\n") && !letra.equals("	")) {
                    aux += letra;
                    band = true;   
                }else{
                    ifBan(band,i,linea);
                }
                if (i == arre.length-1) {
                    ifBan(band,i,linea);
                }
            }
            if (letra.equals("\n")) {
                System.out.println("================================================================ SALTO DE LÍNEA");
                linea++;
            }
        }
        System.out.println("cont = " + cont);
        return cont == 0;
    }
    
    
    
    public Compiler() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txaCode = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        txaCode.setColumns(20);
        txaCode.setRows(5);
        txaCode.setText("Hola");
        jScrollPane1.setViewportView(txaCode);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton1)
                .addGap(108, 108, 108)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jButton1)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (analizarLexico(txaCode.getText())) {
            System.out.println("Análisis léxico correcto");
        }else{
            System.out.println("Error en el análisis léxico");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compiler().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txaCode;
    // End of variables declaration//GEN-END:variables
}
