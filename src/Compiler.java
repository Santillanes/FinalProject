
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

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
    int cont = 0, linea = 1;
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
                    } else {
                    }
                }
                
            }
            band = false;
            aux = "";
        }
    }
    
    private boolean analizarLexico(String code){
        
        if (cont != 0) {
            cont = 0;
        }
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
    
    
    // ================================================= DISEÑO ==================================================
    class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
    // ================================================= DISEÑO ==================================================
    public Compiler() {
        initComponents();
        
        this.setTitle("Net Candy Compiler");
        
        setSize(1295, 755);
        this.setLocationRelativeTo(null);
        
        btnCompilar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        txaCode.setEnabled(false);
      
        btnNuevo.setBorder(new RoundedBorder (30));
        
      
        
        
        
        setIconImage(new ImageIcon(getClass().getResource("/images/logopng.png")).getImage());
        
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
        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        txaCode.setColumns(20);
        txaCode.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txaCode.setRows(5);
        txaCode.setText("INICIO\n\n\t(; Escriba su Código aquí :)\n\n\nFIN");
        txaCode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 3, true));
        jScrollPane1.setViewportView(txaCode);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(327, 24, 930, 678);

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7183232.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(null);
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(50, 80, 190, 90);

        btnAbrir.setBackground(new java.awt.Color(255, 255, 255));
        btnAbrir.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnAbrir.setText("Abrir");
        getContentPane().add(btnAbrir);
        btnAbrir.setBounds(38, 203, 220, 41);

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnGuardar.setText("Guardar");
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(38, 276, 220, 41);

        btnCompilar.setBackground(new java.awt.Color(255, 255, 255));
        btnCompilar.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCompilar);
        btnCompilar.setBounds(38, 349, 220, 41);

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(38, 422, 220, 41);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/net candy compiler peque.png"))); // NOI18N
        getContentPane().add(logo);
        logo.setBounds(53, 524, 190, 128);

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setForeground(new java.awt.Color(255, 255, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Mesa de trabajo 1.png"))); // NOI18N
        getContentPane().add(fondo);
        fondo.setBounds(0, -190, 2020, 1090);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        btnCompilar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(true);
        txaCode.setEnabled(true); 
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        // TODO add your handling code here:
        cont = 0; linea = 1; aux = ""; band = false;
        if (analizarLexico(txaCode.getText())) {
            System.out.println("Análisis léxico correcto");
            Consola salida = new Consola();
            salida.setVisible(true);
        }else{
            System.out.println("Error en el análisis léxico");
        }
        
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < arre.length; i++) {
            System.out.print(arre[i]+", ");
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

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
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel fondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextArea txaCode;
    // End of variables declaration//GEN-END:variables
}
