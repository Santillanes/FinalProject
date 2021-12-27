
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
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
    String compara = "MENORMAYORMAIGUALMEIGUALDIFNOYYOOOE";
    String tipoDato = "duvalinbocadinchiclerellerindocachetadamazapanSkwinklehersheyswini";
    String palRes = "abiertocerradobolsitapiñataticowinniTicketSkittleimportarextiendeimplementanuevolanzarintentacarameloROMPERDEFECTOverdadmentira";
    String ciclos = "PARAMIENTRASHAZ";
    String condicion = "SISINOJUICIOCASO";
    String simbRes = "=:\"_";
    boolean band = false;
    String aux = "";
    String inicio;
    int cont = 0, linea = 1;
    String[] arre;
    List palabras = new ArrayList();
    List variables = new ArrayList();
    
    // ================================================= LÉXICO ==================================================
    private void ifBan(boolean ban, int idx, int lin){
        if (!aux.isEmpty()) {
            inicio = String.valueOf(aux.charAt(0));
        }
        String letras = "qwertyuiopñlkjhgfdsazxcvbnmQWERTYUIOPÑLKJHGFDSAZXCVBNM";
        if (ban) {
            if (compara.contains(aux)) {
                System.out.println("Operador comparativo: "+aux);
                palabras.add(aux);
            }else if (tipoDato.contains(aux)) {
                System.out.println("Tipo de dato: "+aux);
                palabras.add(aux);
            }else if (palRes.contains(aux)) {
                System.out.println("Palabra reservada: "+aux);
                palabras.add(aux);
            }else if(ciclos.contains(aux)){
                System.out.println("Sentencia: "+aux);
                palabras.add(aux);
            }else if(condicion.contains(aux)){
                System.out.println("Sentencia: "+aux);
                palabras.add(aux);
            }else if(letras.contains(inicio)){
                System.out.println("Identificador: "+aux);
                palabras.add(aux);
                variables.add(aux);
            }else{
                int n = aux.length()+1;
                for (int j = idx-n; j >= 0; j--) {
                    if (!arre[j].isBlank()) {
                        if (arre[j].equals("=")) {
                            System.out.println("Valor de variable: "+aux);
                            palabras.add(aux);
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
                    palabras.add("(:");
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
                        palabras.add(txt);
                        System.out.println("Texto: "+txt);
                    }
                    palabras.add(":)");
                    System.out.println("Fin comentario: :)");
                }else{
                    palabras.add(letra);
                    System.out.println("Operador: "+letra);
                }
                
            }else if (log.contains(letra)) {
                ifBan(band,i,linea);
                palabras.add(letra);
                System.out.println("Op lógico: "+letra);
                
            }else if (simbRes.contains(letra)) {
                ifBan(band,i,linea);
                palabras.add(letra);
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
                        palabras.add(txt);
                        System.out.println("Texto: "+txt);
                    }
                    palabras.add("\"");
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
    // ================================================= LÉXICO ==================================================
    
    
    
    // ================================================= SINTÁCTICO ==================================================
    public boolean analizarSintactico(){
       
        /*
                DECLARACIÓN
                    tipoDato Var
                    tipoDato nomVar = valVar
                ASIGNACIÖN
                    Var = valVar
        
                SALIDA DE DATOS
                    Ticket : Var,num,cade
                ENTRADA DE DATOS
                    Skittle : Var
                    
                IF
                    SI ( Var,num,cade opCond Var,num,cade ) { instrucciones }
                    SI ( Var,num,cade opCond Var,num,cade ) { instrucciones } SINO { instrucciones }
                    SI ( Var,num,cade opCond Var,num,cade ) { instrucciones } SINO SI ( Var,num,cade opCond Var,num,cade ) { instrucciones } ...
        
                WHILE
                    MIENTRAS ( Var,num,cade opCond Var,num,cade ) { instrucciones }
        
                DO WHILE
                    HAZ { instucciones } MIENTRAS ( Var,num,cade opCond Var,num,cade )
                FOR
                    PARA ( tipoDato Var = valVar     Var opCond Var,num,cade    Var = Var opLogico num ) { instrucciones }
                SWITCH
                    JUICIO ( VAR ) { CASO num,cade : instrucciones ROMPER (repite) }
                    JUICIO ( VAR ) { CASO num,cade : instrucciones ROMPER (repite) DEFECTO : instrucciones }
        
        */
        
        
        // De la lista, crear un arreglo de dos dimensiones, para la variable y su valor (tipo de variable?)
        System.out.println("======================================= Análisis sintáctico =====================================");
        
        for (int i = 0; i < palabras.size(); i++) {
            String pal = palabras.get(i).toString();
            
            while(pal.charAt(0) == ' '){
                pal = pal.substring(1);
            }
            while(pal.charAt(pal.length()-1) == ' '){
                pal = pal.substring(0, pal.length()-1);
            }
            
            
                        //DECLARACIÓN
            if (i<palabras.size()-3 && tipoDato.contains(pal) && revisarVariable(palabras.get(i+1).toString()) && palabras.get(i+2).toString().equals("=") ) {
                
                String txt = "";
                boolean band = false;
                if (palabras.get(i+3).toString().equals("\"")) {
                    txt = palabras.get(i+3).toString() + palabras.get(i+4).toString() + palabras.get(i+5).toString();
                    band = true;
                }else{
                    txt = palabras.get(i+3).toString();
                }
                if (revisarValorVar(txt)) {
                    System.out.println("**** Declaración de variable con asignación ****");
                    if (band) {
                        System.out.println(pal + " " + palabras.get(i+1).toString() + " " + palabras.get(i+2).toString() + " " + palabras.get(i+3).toString() + palabras.get(i+4).toString() + palabras.get(i+5).toString());
                        i+=5;
                    }else{
                        System.out.println(pal + " " + palabras.get(i+1).toString() + " " + palabras.get(i+2).toString() + " " + palabras.get(i+3).toString());
                        i+=3;
                    }
                }
                        //DECLARACIÓN
            }else if (i<palabras.size()-1 && tipoDato.contains(pal) && revisarVariable(palabras.get(i+1).toString())) {   
                System.out.println("**** Declaración de variable sin asignación ****");
                System.out.println(pal + " " + palabras.get(i+1).toString());
                i++;
                        //ASIGNACIÓN
            }else if (i<palabras.size()-2 && revisarVariable(pal) && palabras.get(i+1).toString().equals("=")) {
                String txt = "";
                boolean band = false;
                if (palabras.get(i+2).toString().equals("\"")) {
                    txt = palabras.get(i+2).toString() + palabras.get(i+3).toString() + palabras.get(i+4).toString();
                    band = true;
                }else{
                    txt = palabras.get(i+2).toString();
                }
                if (revisarValorVar(txt)) {
                    System.out.println("**** Asignación a variable ****");
                    if (band) {
                        System.out.println(pal + " " + palabras.get(i+1).toString() + " " + palabras.get(i+2).toString()+ palabras.get(i+3).toString()+ palabras.get(i+4).toString());
                    }else{
                        System.out.println(pal + " " + palabras.get(i+1).toString() + " " + palabras.get(i+2).toString());
                    }
                }
                        //SALIDA DE DATOS
            }else if (i<palabras.size()-2 && "Ticket".equals(pal) && ":".equals(palabras.get(i+1).toString())) {
                String txt = "";
                boolean band = false;
                if (palabras.get(i+2).toString().equals("\"")) {
                    txt = palabras.get(i+2).toString() + palabras.get(i+3).toString() + palabras.get(i+4).toString();
                    band = true;
                }else{
                    txt = palabras.get(i+2).toString();
                }
                if (revisarValorVar(txt)) {
                    System.out.println("**** Salida de datos ****");
                    if (band) {
                        System.out.println(pal + palabras.get(i+1).toString() + " " + palabras.get(i+2).toString()+ palabras.get(i+3).toString()+ palabras.get(i+4).toString());
                    }else{
                        System.out.println(pal + palabras.get(i+1).toString() + " " + palabras.get(i+2).toString());
                    }
                }
            }
            
            
            
            
        }
        return false;
    }
    
    private boolean revisarVariable(String var){
        for (int i = 0; i < variables.size(); i++) {
            if (var.equals(variables.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    private boolean revisarValorVar(String var){
        if (compara.contains(var) || tipoDato.contains(var) || palRes.contains(var) || ciclos.contains(var) ||condicion.contains(var) 
                || op.contains(var) || log.contains(var) || simbRes.contains(var) ) {
            return false;
        }
        return true;
    }
    // ================================================= SINTÁCTICO ==================================================
    
    
    
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
        cont = 0; linea = 1; aux = ""; band = false; variables.clear(); palabras.clear();
        if (analizarLexico(txaCode.getText())) {
            System.out.println("Análisis léxico correcto");
            System.out.println(palabras);
            analizarSintactico();
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
