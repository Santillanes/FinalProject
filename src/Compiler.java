
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Santillanes
 */
public class Compiler extends javax.swing.JFrame {

    String ruta = "";
    /**
     * Creates new form compiler
     */
    String op = "[]{}()";
    String log = "+-*/%^";
    String compara = "MENORMAYORMAIGUALMEIGUALDIFNOYYOOOE";
    String tipoDato = "duvalinbocadinchiclerellerindocachetadamazapanSkwinklehersheyswini";
    String palRes = "bolsitapiñataticowinniTicketSkittleimportarextiendeimplementanuevolanzarintentacarameloROMPERDEFECTOverdadmentira";
    String acceso = "abiertocerrado";
    String ciclos = "PARAMIENTRASHAZ";
    String condicion = "SISINOJUICIOCASO";
    String numeros = "1234567890";
    String simbRes = "=:\"_";
    boolean band = false;
    String aux = "", nums = ""; //, verificarCorchete = ""
    String inicio;
    int cont = 0, linea = 1, contaObtener = 0;
    String[] arre;
    List palabras = new ArrayList();
    List variables = new ArrayList();
    List clase = new ArrayList();
    List llaves = new ArrayList();
    List instrucciones = new ArrayList();
    List detalleVariables = new ArrayList();
    int conta = 0;
    boolean bander = false, banNums = false, bandeHAZ = false, bandeSINO = false, bandeClase = false, bandeObtener = false;
    

    // ================================================= LÉXICO ==================================================
    private void ifBan(boolean ban, int idx, int lin) {
        if (!aux.isEmpty()) {
            inicio = String.valueOf(aux.charAt(0));
        }
        String letras = "qwertyuiopñlkjhgfdsazxcvbnmQWERTYUIOPÑLKJHGFDSAZXCVBNM";

        if (ban) {
            if (compara.contains(aux)) {
                System.out.println("Operador comparativo: " + aux);
                palabras.add(aux);
            } else if (tipoDato.contains(aux)) {
                System.out.println("Tipo de dato: " + aux);
                palabras.add(aux);
            } else if (palRes.contains(aux)) {

                System.out.println("Palabra reservada: " + aux);
                palabras.add(aux);
            } else if (ciclos.contains(aux)) {
                System.out.println("Sentencia: " + aux);
                palabras.add(aux);
            } else if (condicion.contains(aux)) {
                System.out.println("Sentencia: " + aux);
                palabras.add(aux);
            } else if (acceso.contains(aux)) {
                System.out.println("Acceso: " + aux);
                palabras.add(aux);
            } else if (letras.contains(inicio)) {

                if (palabras.size() > 0) {
                    String anterior = palabras.get(palabras.size() - 1).toString();
                    //System.out.println("anterior = " + anterior);
                    if (tipoDato.contains(anterior)) {
                        
                            variables.add(aux);
                            System.out.println("Variable: " + aux);
                        
                    } else if ("bolsita".equals(anterior)) {
                        clase.add(aux);
                        System.out.println("Nombre de clase: " + aux);
                    } else {
                        boolean bName = false;
                        for (int i = 0; i < variables.size(); i++) {
                            if (variables.get(i).equals(aux)) {
                                bName = true;
                            }
                        }
                        if (bName) {
                            System.out.println("Variable: " + aux);
                        }else{
                            if ("FALSO".equals(aux) || "VERDADERO".equals(aux)) {
                                System.out.println("Valor de variable: "+aux);
                                palabras.add(aux);
                            }else{
                                aux = aux.replace(".", "¿");
                                String[] pts = aux.split("¿");
                                //System.out.println("aux = " + aux);
                                //System.out.println("pts[k] = " + pts[0]);
                                if (pts.length == 2) {
                                    if (pts[1].equals("longitud") || pts[1].equals("esMayuscula")) {
                                        aux = aux.replace("¿", ".");
                                        System.out.println("Valor de variable: "+aux);
                                    }else if(pts[1].equals("obtener")){
                                        aux = aux.replace("¿", ".");
                                        if (arre[idx].equals("(")) {
                                            bandeObtener = true;
                                            for (int i = idx; i < arre.length; i++) {
                                                contaObtener++;
                                                if (arre[i].equals(")")) {
                                                    aux += arre[i];
                                                    break;
                                                }else{
                                                    aux += arre[i];
                                                }
                                            }
                                            System.out.println("Valor de variable: "+aux);
                                        }else{
                                            cont++;
                                            txaConsola.setText(txaConsola.getText()+"Error en el método \"obtener\"");
                                        }
                                    }else{
                                        cont++;
                                        txaConsola.setText(txaConsola.getText()+"Error1. La variable \""+aux+"\" no existe.");
                                    }
                                }else{
                                    cont++;
                                    txaConsola.setText(txaConsola.getText()+"Error2. La variable \""+aux+"\" no existe.");
                                }
                            }
                        }
                    }
                }

                palabras.add(aux);
            } else { //inicia con número
                //System.out.println("aux = " + aux);
                /*
                    rellerindo hola
                    hola = 123
                    hola = "adios"
                    hola = 5 * 26     

                 */
                int n = aux.length() + 1;
                String cadeAux = "";
                String auxi = "_____";
                conta = 0;
                for (int j = idx - n; j >= 0; j--) {
                    if (!arre[j].isBlank()) {
                        if (arre[j].equals("=")) {
                            //hola = 1+23+ 3
                            cadeAux = aux; // =1
                            //System.out.println("aux = " + aux);
                            for (int i = idx; i < arre.length; i++) {
                                //System.out.println("cadeAux = " + cadeAux);
                                //conta++;
                                //System.out.println("************************");
                                //System.out.println("arre[i] = " + arre[i]);

                                boolean b = false;
                                //System.out.println("auxi = " + auxi);
                                for (int k = 0; k < auxi.length(); k++) {
                                    String let = String.valueOf(auxi.charAt(k));
                                    if (letras.contains(let)) {
                                        b = true;
                                    }
                                }
                                //System.out.println("b = " + b);
                                //System.out.println("arre[i] = " + arre[i]);
                                if (!arre[i].equals(" ") && !arre[i].equals("\n") && !arre[i].equals("	")) {
                                    if (numeros.contains(arre[i]) || log.contains(arre[i]) || ".".equals(arre[i])) {
                                        if (log.contains(arre[i])) {

                                            if (!revisarValorVar(auxi) || b) {
                                                //Es una palabra reservada
                                                i = arre.length;
                                                //System.out.println("ENTRA");
                                            } else {
                                                auxi = auxi.replace("_", "");
                                                cadeAux += auxi;
                                                auxi = "_____";
                                                //System.out.println("Se debe añadir");
                                            }
                                            cadeAux += arre[i];
                                        } else {
                                            if (!revisarValorVar(auxi) || b) {
                                                //System.out.println("auxi = " + auxi);
                                                i = arre.length;
                                                cont++;
                                                System.out.println("ERROR DE VALOR DE VARIABLE EN LÍNEA: " + (linea + 1));
                                                //System.out.println("ENTRA");
                                            } else {
                                                cadeAux += arre[i];
                                            }
                                        }
                                    } else {
                                        i = arre.length;
                                    }
                                } else {
                                    if (!revisarValorVar(auxi) || b) {
                                        //Es una palabra reservada
                                        i = arre.length;
                                        //System.out.println("ENTRA");
                                    } else {
                                        auxi = auxi.replace("_", "");
                                        cadeAux += auxi;
                                        auxi = "_____";
                                        //System.out.println("Se debe añadir");
                                    }
                                }
                            }
                            /*
                            String fin = "";
                            boolean punto = true;
                            for (int i = 0; i < cadeAux.length(); i++) {
                                if (numeros.contains(String.valueOf(cadeAux.charAt(i)))) {
                                    fin += String.valueOf(cadeAux.charAt(i));
                                }else if (".".equals(String.valueOf(cadeAux.charAt(i))) && punto) {
                                    punto = false;
                                    fin += String.valueOf(cadeAux.charAt(i));
                                }
                                
                                if ("+".equals(cadeAux.charAt(cadeAux.length()-1))) {
                                    cont++;
                                    System.out.println("Símbolo desconocido en la línea " + lin + ": " + aux);
                                }
                            }
                             */
                            System.out.println("Valor de variable: " + cadeAux);
                            palabras.add(cadeAux);
                            bander = true;

                        } else if (numeros.contains(String.valueOf(aux.charAt(0)))) {
                            boolean b = true;
                            for (int i = 0; i < aux.length(); i++) {
                                if (!numeros.contains(String.valueOf(aux.charAt(i)))) {
                                    b = false;
                                }
                            }
                            if (b) {
                                System.out.println("Valor numérico: " + aux);
                                palabras.add(aux);
                            } else {
                                System.out.println("Símbolo desconocido en la línea " + lin + ": " + aux);
                                cont++;
                            }

                        } else {
                            System.out.println("palabras.get(palabras.size()-2).toString() = " + palabras.get(palabras.size() - 2).toString());
                            System.out.println("Símbolo desconocido en la línea " + lin + ": " + aux);
                            cont++;
                        }
                        break;
                    }
                }

            }
            //System.out.println("Conta = " + conta);
            band = false;
            aux = "";
        }
    }

    private boolean analizarLexico(String code) {

        if (cont != 0) {
            cont = 0;
        }
        arre = code.split("");

        for (int i = 0; i < arre.length; i++) {

            if (cont != 0) {
                return false;
            }

            String letra = arre[i];
            //System.out.println("letra = " + letra);
            if (bander) {
                //i += conta-1;

                if (!palabras.get(palabras.size() - 1).toString().contains(letra)) {
                    if (!letra.equals(" ")) {
                        bander = false;
                        //System.out.println(palabras.get(palabras.size() - 1).toString());
                        //System.out.println("ENTRA: " + letra);
                    }
                }

                //System.out.println("i2 = " + i);
            }
            //System.out.println("letra = " + letra);
            //     []+MENOR-duvalin()
            if (!bander) {

                if (op.contains(letra)) {
                    ifBan(band, i, linea);

                    if (letra.equals("(") && arre[i + 1].equals(":")) {
                        palabras.add("(:");
                        System.out.println("Inicio comentario: (:");

                        String txt = "";
                        for (int j = i + 2; j < arre.length; j++) {
                            if (arre[j].equals(":") && arre[j + 1].equals(")")) {
                                i = j + 1;
                                break;
                            } else {
                                txt += arre[j];
                            }
                        }
                        if (!txt.equals("")) {
                            palabras.add(txt);
                            System.out.println("Texto: " + txt);
                        }
                        palabras.add(":)");
                        System.out.println("Fin comentario: :)");
                    }else if(letra.equals("(") && bandeObtener){
                        i += contaObtener;
                    }else {
                        palabras.add(letra);
                        System.out.println("Operador: " + letra);
                    }

                } else if (log.contains(letra)) {
                    ifBan(band, i, linea);

                    if (!bander) {
                        palabras.add(letra);
                        System.out.println("Op lógico: " + letra);
                    }

                } else if (simbRes.contains(letra)) {
                    ifBan(band, i, linea);
                    palabras.add(letra);
                    System.out.println("Simbolo reservado: " + letra);

                    boolean bandeComillas = false;
                    if (letra.equals("\"")) {
                        String txt = "";
                        for (int j = i + 1; j < arre.length; j++) {
                            if (arre[j].equals("\"")) {
                                i = j;
                                bandeComillas = true;
                                break;
                            } else {
                                txt += arre[j];
                            }
                        }
                        if (!txt.equals("")) {
                            palabras.add(txt);
                            System.out.println("Texto: " + txt);
                        } else {
                            palabras.add(txt);
                            System.out.println("Texto: " + txt);
                        }

                        if (bandeComillas) {
                            palabras.add("\"");
                            System.out.println("Símbolo reservado: \"");
                        } else {
                            System.out.println("Faltan comillas en línea " + linea + ": " + aux);
                            cont++;
                        }

                    }

                } else {
                    if (!letra.equals(" ") && !letra.equals("\n") && !letra.equals("	")) {
                        aux += letra;
                        band = true;
                    } else {
                        ifBan(band, i, linea);
                        band = false;
                    }
                    if (i == arre.length - 1) {
                        ifBan(band, i, linea);
                    }
                }
                if (letra.equals("\n")) {
                    System.out.println("================================================================ SALTO DE LÍNEA");
                    linea++;
                }
            }
        }
        //System.out.println("cont = " + cont);
        return cont == 0;
    }
    // ================================================= LÉXICO ==================================================

    // ================================================= SINTÁCTICO ==================================================
    public boolean analizarSintactico() {

        /*
                CLASES
                    acceso Var { instrucciones }
        
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
        //txaConsola.setText("======================================= Análisis sintáctico =====================================");

        for (int i = 0; i < palabras.size(); i++) {
            String pal = palabras.get(i).toString();

            while (pal.charAt(0) == ' ') {
                pal = pal.substring(1);
            }
            while (pal.charAt(pal.length() - 1) == ' ') {
                pal = pal.substring(0, pal.length() - 1);
            }

            if ("(:".equals(pal)) {
                System.out.println("**** COMENTARIO ****");
                System.out.println(pal + " " + palabras.get(i + 1) + " " + palabras.get(i + 2));
            } //CLASES
            //      acceso Var { instrucciones }
            //              1  2
            else if (i < palabras.size() - 2 && "bolsita".equals(pal)) {
                llaves.add(0, "CLASE");
                //verificarCorchete = "CLASE";
                if (esClase(palabras.get(i + 1).toString())) {
                    if ("{".equals(palabras.get(i + 2).toString())) {
                        boolean ba = false;
                        int j;
                        for (j = i + 7; j < palabras.size(); j++) {
                            String palab = palabras.get(j).toString();
                            if ("}".equals(palab)) {
                                ba = true;
                                break;
                            }
                        }
                        if (!ba) {
                            System.out.println("ERROR EN CLASE");
                            System.out.println("NO SE ENCONTRÓ CIERRE DE LLAVE");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else {
                            System.out.println("**** DECLARACIÓN DE CLASE ****");
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString()); //Hasta el {
                            bandeClase = true;
                            instrucciones.add(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString());
                        }
                    } else {
                        System.out.println("ERROR EN CLASE");
                        System.out.println("NO SE ENCONTRÓ LLAVE DE INICIO");
                        llaves.add(0, "ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                } else {
                    System.out.println("ERROR EN CLASE");
                    System.out.println("NO SE ENCONTRÓ NOMBRE DE CLASE");
                    llaves.add(0, "ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
            } //DECLARACIÓN
            else if (i < palabras.size() - 3 && tipoDato.contains(pal) && revisarVariable(palabras.get(i + 1).toString()) && palabras.get(i + 2).toString().equals("=")) {
                    // Skwinkle, skVar, =, rNum1, _, ", hola a todos, "
                    //    i       1     2   3     4  5         6       7
                String txt = "";
                boolean band = false;
                if (palabras.get(i + 3).toString().equals("\"")) {
                    txt = palabras.get(i + 3).toString() + palabras.get(i + 4).toString() + palabras.get(i + 5).toString();
                    band = true;
                } else {
                    //System.out.println("PAÑABRA i:"+palabras.get(i));
                    //System.out.println("PAÑABRA i:"+palabras.get(i).toString());
                    txt = palabras.get(i + 3).toString();
                }
                if (revisarValorVar(txt)) {
                    if (bandeClase) {
                        System.out.println("**** DECLARACIÓN DE VARIABLE CON ASIGNACIÓN ****");
                        
                        //¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡
                        
                        
                        if (band) {
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + palabras.get(i + 4).toString() + palabras.get(i + 5).toString());
                            instrucciones.add(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + palabras.get(i + 4).toString() + palabras.get(i + 5).toString());
                            i += 5;
                        } else {
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString());
                            instrucciones.add(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString());
                            i += 3;
                        }
                        
                    } else {
                        System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                        llaves.add(0, "ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                }
                //DECLARACIÓN
            } else if (i < palabras.size() - 1 && tipoDato.contains(pal) && revisarVariable(palabras.get(i + 1).toString())) {
                if (bandeClase) {
                    System.out.println("**** DECLARACIÓN DE VARIABLE SIN ASIGNACIÓN ****");
                    System.out.println(pal + " " + palabras.get(i + 1).toString());
                    instrucciones.add(pal + " " + palabras.get(i + 1).toString());
                    i++;
                } else {
                    System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                    llaves.add(0, "ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
                //ASIGNACIÓN
            } else if (i < palabras.size() - 2 && revisarVariable(pal) && palabras.get(i + 1).toString().equals("=")) {
                String txt = "";
                boolean band = false;
                if (palabras.get(i + 2).toString().equals("\"")) {
                    txt = palabras.get(i + 2).toString() + palabras.get(i + 3).toString() + palabras.get(i + 4).toString();
                    band = true;
                } else {

                    txt = palabras.get(i + 2).toString();

                }
                if (revisarValorVar(txt)) {
                    if (bandeClase) {
                        System.out.println("**** ASIGNACIÓN A VARIABLE ****");
                        if (band) { //  hola = "asfs"_sdfs
                            
                            
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + palabras.get(i + 3).toString() + palabras.get(i + 4).toString());
                            instrucciones.add(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + palabras.get(i + 3).toString() + palabras.get(i + 4).toString());
                        } else {//  hola = sdfs _ sdfs_ sdfs_ "asfs" 
                            
                            //???????????????????????????????????????????????
                            txt = palabras.get(i).toString() + " " + palabras.get(i + 1).toString() + " "  + palabras.get(i + 2).toString();
                            
                            
                            int j = i + 3;
                            while (palabras.get(j).equals("+")) {
                                System.out.print("PALABRA PARA ASIGNACIÓN: "+palabras.get(j).toString());
                                txt += palabras.get(j).toString();
                                if (revisarVariable(palabras.get(j + 1).toString())) {
                                    
                                    for (int k = 0; k < detalleVariables.size(); k++) {
                                        String[] detalle = detalleVariables.get(k).toString().split("¿");
                                        if (detalle[1].equals(palabras.get(j + 1).toString())) {
                                            if (detalle.length == 3) {
                                                txt += detalle[2];
                                                j += 2;
                                            }else{
                                                txaConsola.setText(txaConsola.getText()+"Error. La variable "+detalle[1]+" no ha sido inicializada.");
                                                return false;
                                            }
                                        }
                                    }
                                    
                                    
                                } else {
                                    for (int k = 0; k < palabras.get(j + 1).toString().length(); k++) {
                                        if (!numeros.contains(String.valueOf(palabras.get(j + 1).toString().charAt(k)))) {
                                            System.out.println("ERROR2 EN ASIGNACIÓN");
                                            System.out.println("DATOS INCORRECTOS PARA ASIGNACIÓN");
                                            llaves.add(0, "ERROR");
                                            return false;
                                        }
                                    }
                                    txt += palabras.get(j + 1).toString();
                                    j += 2;
                                }
                            }
                            
                            
                            //???????????????????????????????????????????????
                            
                            System.out.println("SE AÑADE: "+txt);
                            instrucciones.add(txt);
                        }
                    } else {
                        System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                        llaves.add(0, "ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                }
                //SALIDA DE DATOS
            } else if (i < palabras.size() - 2 && "Ticket".equals(pal)) {
                if (":".equals(palabras.get(i + 1).toString())) {

                    String txt = "";
                    boolean band = false;
                    if (palabras.get(i + 2).toString().equals("\"")) {
                        txt = palabras.get(i + 2).toString() + palabras.get(i + 3).toString() + palabras.get(i + 4).toString();
                        band = true;
                    } else {
                        txt = palabras.get(i + 2).toString();
                    }
                    if (revisarValorVar(txt)) {
                        if (bandeClase) {
                            System.out.println("**** SALIDA DE DATOS ****");
                            String cad = "";
                            if (band) {
                                System.out.print(pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + palabras.get(i + 3).toString() + palabras.get(i + 4).toString());
                                cad = pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + palabras.get(i + 3).toString() + palabras.get(i + 4).toString();
                                int j = i + 5;
                                while (palabras.get(j).equals("_")) {
                                    System.out.print(palabras.get(j).toString());
                                    cad += palabras.get(j).toString();
                                    if (revisarVariable(palabras.get(j + 1).toString())) {
                                        System.out.print(palabras.get(j + 1).toString());
                                        cad += palabras.get(j + 1).toString();
                                        j += 2;
                                    } else if ("\"".equals(palabras.get(j + 1).toString())) {
                                        if ("\"".equals(palabras.get(j + 3).toString())) {
                                            System.out.print(palabras.get(j + 1).toString() + palabras.get(j + 2).toString() + palabras.get(j + 3).toString());
                                            cad += palabras.get(j + 1).toString() + palabras.get(j + 2).toString() + palabras.get(j + 3).toString();
                                        } else {
                                            System.out.println("ERROR EN TICKET");
                                            System.out.println("DATOS INCORRECTOS PARA SALIDA");
                                            llaves.add(0, "ERROR");
                                            return false;
                                        }
                                        j += 4;
                                    } else {
                                        System.out.println("ERROR EN TICKET");
                                        System.out.println("DATOS INCORRECTOS PARA SALIDA");
                                        llaves.add(0, "ERROR");
                                        return false;
                                    }
                                }
                            } else {
                                if (revisarVariable(txt)) { // Ticket: rNum2_"hola a todos"
                                                            // Ticket, :, rNum2, _, ", hola a todos, ",
                                    System.out.println(pal+palabras.get(i + 1).toString() + " " +txt);
                                    cad += pal+palabras.get(i + 1).toString() + " " +txt;
                                    int j = i + 3;
                                    while (palabras.get(j).equals("_")) {                                       
                                        System.out.print(palabras.get(j).toString());
                                        cad += palabras.get(j).toString();
                                        if (revisarVariable(palabras.get(j + 1).toString())) {
                                            System.out.print(palabras.get(j + 1).toString());
                                            cad += palabras.get(j + 1).toString();
                                            j += 2;
                                        } else if ("\"".equals(palabras.get(j + 1).toString())) {
                                            if ("\"".equals(palabras.get(j + 3).toString())) {
                                                System.out.print(palabras.get(j + 1).toString() + palabras.get(j + 2).toString() + palabras.get(j + 3).toString());
                                                cad += palabras.get(j + 1).toString() + palabras.get(j + 2).toString() + palabras.get(j + 3).toString();
                                            } else {
                                                System.out.println("ERROR EN TICKET");
                                                System.out.println("DATOS INCORRECTOS PARA SALIDA");
                                                llaves.add(0, "ERROR");
                                                return false;
                                            }
                                            j += 4;
                                        } else {
                                            System.out.println("ERROR EN TICKET");
                                            System.out.println("DATOS INCORRECTOS PARA SALIDA");
                                            llaves.add(0, "ERROR");
                                            return false;
                                        }
                                    }
                                }else{
                                    System.out.println("ERROR EN TICKET");
                                    System.out.println("DATOS INCORRECTOS PARA SALIDA");
                                    llaves.add(0, "ERROR");
                                    return false;
                                }
                                
                            }
                            instrucciones.add(cad);
                            System.out.println("");
                        } else {
                            System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        }
                    } else {
                        System.out.println("ERROR EN TICKET");
                        System.out.println("NO SE ENCONTRÓ DATOS PARA SALIDA");
                        llaves.add(0, "ERROR");
                        return false;
                    }

                } else {
                    System.out.println("ERROR EN TICKET");
                    System.out.println("NO SE ENCONTRÓ \":\"");
                    llaves.add(0, "ERROR");
                    return false;
                }
                // ENTRADA DE DATOS
            } else if (i < palabras.size() - 2 && "Skittle".equals(pal)) {

                if (":".equals(palabras.get(i + 1).toString())) {

                    if (revisarVariable(palabras.get(i + 2).toString())) {
                        if (bandeClase) {
                            System.out.println("**** ENTRADA DE DATOS ****");
                            System.out.println(pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString());
                            instrucciones.add(pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString());
                        } else {
                            System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        }
                    } else {
                        System.out.println("ERROR EN SKITTLE");
                        System.out.println("NO SE ENCONTRÓ VARIABLE PARA ENTRADA DE DATOS");
                        llaves.add(0, "ERROR");
                        return false;
                    }
                } else {
                    System.out.println("ERROR EN SKITTLE");
                    System.out.println("NO SE ENCONTRÓ \":\"");
                    llaves.add(0, "ERROR");
                    return false;
                }
            }

            /*
                IF
                    SI ( Var,num,cade opCond Var,num,cade ) { instrucciones }
                    SI ( Var,num,cade opCond Var,num,cade ) { instrucciones } SINO { instrucciones }
                    SI ( Var,num,cade opCond Var,num,cade ) { instrucciones } SINO SI ( Var,num,cade opCond Var,num,cade ) { instrucciones } ...
        
             */
            if ("SI".equals(pal)) {
                llaves.add(0, "SI");
                //verificarCorchete = "SI";
                //if (palabras.get(i-1).equals("SINO")) {

                //}else{
                if ("(".equals(palabras.get(i + 1).toString()) && ")".equals(palabras.get(i + 5).toString())) {
                    String parte1 = palabras.get(i + 2).toString();
                    String parte2 = palabras.get(i + 4).toString();
                    if (numOvar(parte1) && numOvar(parte2) && compara.contains(palabras.get(i + 3).toString()) && "{".equals(palabras.get(i + 6).toString())) {
                        boolean ba = false;
                        int j;
                        for (j = i + 7; j < palabras.size(); j++) {
                            String palab = palabras.get(j).toString();
                            if ("}".equals(palab)) {
                                ba = true;
                                break;
                            }
                        }
                        if (!ba) {
                            System.out.println("SENTENCIA IF ERRÓNEA");
                            System.out.println("NO SE ENCONTRÓ CIERRE DE CORCHETES");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else {
                            if (bandeClase) {
                                if (i - 1 > 0) {
                                    if (palabras.get(i - 1).equals("SINO")) {
                                        System.out.println("**** SENTENCIA SI DEL SINO ****");
                                        llaves.remove(0);
                                    } else {
                                        System.out.println("**** SENTENCIA SI ****");
                                    }
                                } else {
                                    System.out.println("**** SENTENCIA SI ****");
                                }
                                System.out.println(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString()
                                        + palabras.get(i + 5).toString() + palabras.get(i + 6).toString()); // Se imprime hasta el "{"
                                instrucciones.add(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString()
                                        + palabras.get(i + 5).toString() + palabras.get(i + 6).toString());
                                if (j + 1 < palabras.size()) {
                                    if (palabras.get(j + 1).equals("SINO")) {
                                        bandeSINO = true;
                                    }
                                }
                            } else {
                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                llaves.add(0, "ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }

                        }

                    } else {
                        System.out.println("SENTENCIA IF ERRÓNEA");
                        System.out.println("REVISAR LA CONDICIÓN DEL IF");
                        llaves.add(0, "ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                }else if("(".equals(palabras.get(i + 1).toString()) && ")".equals(palabras.get(i + 3).toString())){
                    if ("{".equals(palabras.get(i + 4).toString())) {
                        boolean ba = false;
                        int j;
                        for (j = i + 4; j < palabras.size(); j++) {
                            String palab = palabras.get(j).toString();
                            if ("}".equals(palab)) {
                                ba = true;
                                break;
                            }
                        }
                        if (!ba) {
                            System.out.println("SENTENCIA IF ERRÓNEA2");
                            System.out.println("NO SE ENCONTRÓ CIERRE DE CORCHETES2");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else{
                            if (bandeClase) {
                                String con = palabras.get(i + 2).toString();
                                if (revisarVariable(con)) {
                                    System.out.println("**** SENTENCIA SI2 ****");
                                    System.out.println(pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString()); // Se imprime hasta el "{"
                                    instrucciones.add(pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString());
                                }else{
                                    System.out.println("ERROR: SE ESPERABA UNA VARIABLE.2");
                                    llaves.add(0, "ERROR");
                                    return false;
                                }
                            }else {
                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.2");
                                llaves.add(0, "ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }
                        }
                    }else{
                        System.out.println("SENTENCIA IF ERRÓNEA2");
                        System.out.println("REVISAR LA CONDICIÓN DEL IF2");
                        llaves.add(0, "ERROR");
                        return false;
                    }
                
                
                
                } else {
                    System.out.println("SENTENCIA IF ERRÓNEA");
                    System.out.println("ERROR EN PARÉNTESIS");
                    llaves.add(0, "ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
                //}

            } else if ("SINO".equals(pal)) {
                if (!bandeSINO) {
                    System.out.println("ERROR: SENTENCIA SINO SIN LA SENTENCIA SI.");
                    return false;
                } else {
                    if ("{".equals(palabras.get(i + 1))) {
                        boolean ba = false;
                        int j;
                        for (j = i + 1; j < palabras.size(); j++) {
                            String palab = palabras.get(j).toString();
                            if ("}".equals(palab)) {
                                ba = true;
                                break;
                            }
                        }
                        bandeSINO = false;
                        if (!ba) {
                            System.out.println("SENTENCIA SINO ERRÓNEA");
                            System.out.println("NO SE ENCONTRÓ CIERRE DE CORCHETES");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else {
                            if (bandeClase) {
                                System.out.println("**** SENTENCIA SINO ****");
                                System.out.println(pal + palabras.get(i + 1));
                                instrucciones.add(pal + palabras.get(i + 1));
                                llaves.add(0, "SINO");
                                //verificarCorchete = "SINO";
                            } else {
                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                llaves.add(0, "ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }
                        }
                    } else if (!"SI".equals(palabras.get(i + 1))) {
                        System.out.println("SENTENCIA SINO ERRÓNEA");
                        System.out.println("ERROR: FALTAN LLAVES");
                        llaves.add(0, "ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    } else {
                        System.out.println("**** SENTENCIA SINO SI ****");
                        System.out.println(pal + " " + palabras.get(i + 1));
                        instrucciones.add(pal + " " + palabras.get(i + 1));
                        llaves.add(0, "SINO");
                        //verificarCorchete = "SINO";
                    }
                }
            } /*
                    WHILE
                    MIENTRAS ( Var,num,cade opCond Var,num,cade ) { instrucciones }
             */ else if ("MIENTRAS".equals(pal) && !bandeHAZ) {
                llaves.add(0, "MIENTRAS");
                //verificarCorchete = "MIENTRAS";
                if ("(".equals(palabras.get(i + 1).toString()) && ")".equals(palabras.get(i + 5).toString())) {
                    String parte1 = palabras.get(i + 2).toString();
                    String parte2 = palabras.get(i + 4).toString();
                    if (numOvar(parte1) && numOvar(parte2) && compara.contains(palabras.get(i + 3).toString()) && "{".equals(palabras.get(i + 6).toString())) {
                        boolean ba = false;
                        int j;
                        for (j = i + 7; j < palabras.size(); j++) {
                            String palab = palabras.get(j).toString();
                            if ("}".equals(palab)) {
                                ba = true;
                                break;
                            }
                        }
                        if (!ba) {
                            System.out.println("SENTENCIA MIENTRAS ERRÓNEA");
                            System.out.println("NO SE ENCONTRÓ CIERRE DE CORCHETES");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else {
                            if (bandeClase) {
                                System.out.println("**** SENTENCIA MIENTRAS ****");
                                System.out.println(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString()
                                        + palabras.get(i + 5).toString() + palabras.get(i + 6).toString()); // Se imprime hasta el "{"
                                instrucciones.add(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString()
                                        + palabras.get(i + 5).toString() + palabras.get(i + 6).toString());
                            } else {
                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                llaves.add(0, "ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }

                        }
                    } else {
                        System.out.println("SENTENCIA MIENTRAS ERRÓNEA");
                        System.out.println("REVISAR LA CONDICIÓN DEL CICLO");
                        llaves.add(0, "ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                } else {
                    System.out.println("SENTENCIA MIENTRAS ERRÓNEA");
                    System.out.println("ERROR EN PARÉNTESIS");
                    llaves.add(0, "ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
            } else if ("MIENTRAS".equals(pal) && bandeHAZ) {
                if (bandeClase) {
                    bandeHAZ = false;
                    System.out.println("**** SENTENCIA HAZ MIENTRAS ****");
                    System.out.println(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString() + palabras.get(i + 5).toString());
                    instrucciones.add(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString() + palabras.get(i + 5).toString());
                } else {
                    System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                    return false;
                }
            } /*
                        DO WHILE
                        HAZ { instucciones } MIENTRAS ( Var,num,cade opCond Var,num,cade )
             */ else if ("HAZ".equals(pal)) {
                llaves.add(0, "HAZ");
                //verificarCorchete = "HAZ";
                bandeHAZ = true;
                if ("{".equals(palabras.get(i + 1).toString())) {
                    boolean ba = false;
                    int j;
                    for (j = i + 1; j < palabras.size(); j++) {
                        String palab = palabras.get(j).toString();
                        if ("}".equals(palab)) {
                            ba = true;
                            break;
                        }
                    }
                    if (!ba) {
                        System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                        System.out.println("ERROR: LLAVE DE CIERRE");
                        llaves.add(0, "ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    } else {
                        if ("MIENTRAS".equals(palabras.get(j + 1).toString())) {
                            if ("(".equals(palabras.get(j + 2).toString()) && ")".equals(palabras.get(j + 6).toString())) {
                                String parte1 = palabras.get(j + 3).toString();
                                String parte2 = palabras.get(j + 5).toString();
                                if (numOvar(parte1) && numOvar(parte2) && compara.contains(palabras.get(j + 4).toString())) {
                                    if (bandeClase) {
                                        System.out.println("**** SENTENCIA HAZ MIENTRAS ****");
                                        System.out.println(pal + palabras.get(i + 1).toString());
                                        instrucciones.add(pal + palabras.get(i + 1).toString());
                                    } else {
                                        System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                        llaves.add("ERROR");
                                        return false;
                                        //verificarCorchete = "ERROR";
                                    }
                                } else {
                                    System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                                    System.out.println("REVISAR LA CONDICIÓN DEL CICLO");
                                    llaves.add(0, "ERROR");
                                    return false;
                                    //verificarCorchete = "ERROR";
                                }
                            } else {
                                System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                                System.out.println("ERROR: FALTA PARENTESIS DE INCIO O CIERRE");
                                llaves.add(0, "ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }
                        } else {
                            System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                            System.out.println("ERROR: FALTA PALABRA MIENTRAS");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        }
                    }
                } else {
                    System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                    System.out.println("ERROR: LLAVE DE INICIO");
                    llaves.add(0, "ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
            } /*
                    FOR
                    PARA ( tipoDato Var = valVar     Var opCond Var,num    Var  =  Var opLogico num,var )    { instrucciones }
                         1    2      3  4   5         6    7         8      9  10   11    12    13      14   15    16        17   
             */ else if ("PARA".equals(pal)) {
                llaves.add(0, "PARA");
                //verificarCorchete = "PARA";
                if (i + 14 <= palabras.size()) {
                    //System.out.println("Primero: "+palabras.get(i + 1));
                    //System.out.println("Ultimo: "+palabras.get(i + 14));
                    if ("(".equals(palabras.get(i + 1)) && ")".equals(palabras.get(i + 14))) {
                        if (tipoDato.contains(palabras.get(i + 2).toString()) && revisarVariable(palabras.get(i + 3).toString()) && "=".equals(palabras.get(i + 4)) && numOvar(palabras.get(i + 5).toString())) {
                            String variable = palabras.get(i + 3).toString();
                            if (variable.equals(palabras.get(i + 6).toString()) && compara.contains(palabras.get(i + 7).toString()) && numOvar(palabras.get(i + 8).toString())) {
                                if (variable.equals(palabras.get(i + 9).toString()) && "=".equals(palabras.get(i + 10).toString()) && variable.equals(palabras.get(i + 11).toString()) && log.contains(palabras.get(i + 12).toString()) && numOvar(palabras.get(i + 13).toString())) {
                                    if ("{".equals(palabras.get(i + 15).toString())) {
                                        boolean ba = false;
                                        int j;
                                        for (j = i + 1; j < palabras.size(); j++) {
                                            String palab = palabras.get(j).toString();
                                            if ("}".equals(palab)) {
                                                ba = true;
                                                break;
                                            }
                                        }
                                        if (!ba) {
                                            System.out.println("SENTENCIA PARA ERRÓNEA");
                                            System.out.println("ERROR: LLAVE DE CIERRE");
                                            llaves.add(0, "ERROR");
                                            return false;
                                            //verificarCorchete = "ERROR";
                                        } else {
                                            if (bandeClase) {
                                                System.out.println("**** SENTENCIA PARA ****");
                                                System.out.println(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString() + " "
                                                        + palabras.get(i + 5).toString() + " " + palabras.get(i + 6).toString() + " " + palabras.get(i + 7).toString() + " " + palabras.get(i + 8).toString() + " "
                                                        + palabras.get(i + 9).toString() + " " + palabras.get(i + 10).toString() + " " + palabras.get(i + 11).toString() + " " + palabras.get(i + 12).toString() + " "
                                                        + palabras.get(i + 13).toString() + palabras.get(i + 14).toString() + palabras.get(i + 15).toString());
                                                instrucciones.add(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString() + " "
                                                        + palabras.get(i + 5).toString() + " " + palabras.get(i + 6).toString() + " " + palabras.get(i + 7).toString() + " " + palabras.get(i + 8).toString() + " "
                                                        + palabras.get(i + 9).toString() + " " + palabras.get(i + 10).toString() + " " + palabras.get(i + 11).toString() + " " + palabras.get(i + 12).toString() + " "
                                                        + palabras.get(i + 13).toString() + palabras.get(i + 14).toString() + palabras.get(i + 15).toString());
                                                i += 14;
                                                
                                                
                                            } else {
                                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                                llaves.add(0, "ERROR");
                                                return false;
                                                //verificarCorchete = "ERROR";
                                            }
                                        }
                                    } else {
                                        System.out.println("SENTENCIA PARA ERRÓNEA");
                                        System.out.println("ERROR: LLAVE DE INICIO");
                                        llaves.add(0, "ERROR");
                                        return false;
                                        //verificarCorchete = "ERROR";
                                    }
                                } else {
                                    System.out.println("SENTENCIA PARA ERRÓNEA");
                                    System.out.println("ERROR: SECCION 3 DEL CICLO PARA");
                                    llaves.add(0, "ERROR");
                                    return false;
                                    //verificarCorchete = "ERROR";
                                }
                            } else {
                                System.out.println("SENTENCIA PARA ERRÓNEA");
                                System.out.println("ERROR: SECCION 2 DEL CICLO PARA");
                                llaves.add(0, "ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }
                        } else {
                            System.out.println("SENTENCIA PARA ERRÓNEA");
                            System.out.println("ERROR: SECCION 1 DEL CICLO PARA");
                            llaves.add(0, "ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        }
                    } else {
                        System.out.println("SENTENCIA PARA ERRÓNEA");
                        System.out.println("ERROR: FALTA PARENTESIS");
                        llaves.add(0, "ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                } else {
                    System.out.println("SENTENCIA PARA ERRÓNEA");
                    System.out.println("ERROR: CICLO PARA");
                    llaves.add(0, "ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
            }

            if ("}".equals(pal)) {
                if (!llaves.isEmpty()) {
                    String llave = llaves.get(0).toString();
                    switch (llave) {
                        case "SI":
                            System.out.println("**** CIERRE DE CORCHETE DEL SI ****");
                            System.out.println("}SI");
                            instrucciones.add("}SI");
                            llaves.remove(0);
                            break;
                        case "SINO":
                            System.out.println("**** CIERRE DE CORCHETE DEL SINO ****");
                            System.out.println("}SINO");
                            instrucciones.add("}SINO");
                            llaves.remove(0);
                            break;
                        case "MIENTRAS":
                            System.out.println("**** CIERRE DE CORCHETE DEL MIENTRAS ****");
                            System.out.println("}MIENTRAS");
                            instrucciones.add("}MIENTRAS");
                            llaves.remove(0);
                            break;
                        case "HAZ":
                            System.out.println("**** CIERRE DE CORCHETE DEL HAZ MIENTRAS ****");
                            System.out.println("}HAZ");
                            instrucciones.add("}HAZ");
                            llaves.remove(0);
                            break;
                        case "PARA":
                            System.out.println("**** CIERRE DE CORCHETE DEL PARA ****");
                            System.out.println("}PARA");
                            instrucciones.add("}PARA");
                            llaves.remove(0);
                            break;
                        case "CLASE":
                            System.out.println("**** CIERRE DE CORCHETE DE LA CLASE ****");
                            System.out.println("}CLASE");
                            instrucciones.add("}CLASE");
                            llaves.remove(0);
                            bandeClase = false;
                            break;
                        case "ERROR":
                            llaves.remove(0);
                            break;
                        default:
                            System.out.println("ERROR: CORCHETE SOBRANTE.");
                            return false;
                    }
                } else {
                    System.out.println("ERROR: CORCHETE SOBRANTE.");
                    return false;
                }
            }

        }
        for (int i = 0; i < llaves.size(); i++) {
            System.out.println("");
            System.out.println("ENTRA");
            if (!"ERROR".equals(llaves.get(i))) {
                System.out.println("ERROR: CORCHETE FALTANTE.");
                return false;
            }
        }
        return true;
    }

    private boolean esClase(String var) {
        for (int i = 0; i < clase.size(); i++) {
            if (var.equals(clase.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean numOvar(String var) {
        boolean vari = revisarVariable(var);
        if (vari) {
            return true;
        } else {
            for (int i = 0; i < var.length(); i++) {
                if (!numeros.contains(String.valueOf(var.charAt(i)))) {
                    System.out.println("ERROR: " + var);
                    return false;
                }
            }
            return true;
        }
    }

    private boolean revisarVariable(String var) {
        for (int i = 0; i < variables.size(); i++) {
            if (var.equals(variables.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean revisarValorVar(String var) {
        if (acceso.contains(var) || compara.contains(var) || tipoDato.contains(var) || palRes.contains(var) || ciclos.contains(var) || condicion.contains(var)
                || op.contains(var) || log.contains(var) || simbRes.contains(var)) {
            return false;
        }
        return true;
    }
    // ================================================= SINTÁCTICO ==================================================
    
    // ================================================= SEMNÁNTICO ==================================================
    
    public boolean analizarSemantico(){
//   [bolsita Ejemplo {, rellerindo rNum1 = 3, rellerindo rNum2 = 8, SI(rNum1 MAYOR rNum2){, Ticket: "Es mayor" _ rNum1 , SINO{, Ticket: "Es mayor" _ rNum2 ]
        
        for (int i = 0; i < instrucciones.size(); i++) {
            
            // DECLARACIÖN CON O SIN ASINGACIÓN
            String[] car = instrucciones.get(i).toString().split("");
            String[] ins = instrucciones.get(i).toString().split(" ");
            if (tipoDato.contains(ins[0])) {
                //ES DECLARACIÓN
                if (ins.length == 2) {
                    //DECLARACIÓN SIN ASIGNACIÓN
                    
                    if (!detalleVariables.isEmpty()) {
                        for (int j = 0; j < detalleVariables.size(); j++) {
                            String nomb = detalleVariables.get(j).toString().split("¿")[1];
                            if (ins[1].equals(nomb)) {
                                System.out.println("ERROR. Ya existe una variable con el nombre: "+nomb);
                                txaConsola.setText(txaConsola.getText()+"ERROR. Ya existe una variable con el nombre: "+nomb);
                                return false;
                            }
                        }
                    }
                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN SIN ASIGNACIÓN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                    detalleVariables.add(ins[0]+"¿"+ins[1]);
                    
                }else{
                    // boolean   char     int       double   String        Array 
                    // duvalin  chicle rellerindo  mazapan Skwinkle       hersheys 
                    String tipo = ins[0];
                    switch(tipo){
                        case "duvalin":
                            // duvalin duvVar = FALSO
                            if (ins.length != 4) {
                                System.out.println("ERROR3. Se esperaba un valor VERDADERO o FALSO");
                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor VERDADERO o FALSO \n");
                                return false;
                            }else{
                                
                                if (!detalleVariables.isEmpty()) {
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String nomb = detalleVariables.get(j).toString().split("¿")[1];
                                        if (ins[1].equals(nomb)) {
                                            System.out.println("ERROR. Ya existe una variable con el nombre: "+nomb);
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Ya existe una variable con el nombre: "+nomb);
                                            return false;
                                        }
                                    }
                                }
                                
                                if (ins[3].equals("VERDADERO") || ins[3].equals("FALSO")) {
                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+ins[3]);
                                    //System.out.println("ins[3] = " + ins[3]);
                                }else if (revisarVariable(ins[3])) {
                                    boolean encontrado = false;
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String[] datos = detalleVariables.get(j).toString().split("¿");
                                        //String valor = detalleVariables.get(j).toString().split("¿")[2];
                                        
                                        if (datos[0].equals("duvalin") && datos[1].equals(ins[3])) {
                                            if (datos.length == 2 || datos[1].equals(datos[2])) {
                                                System.out.println("ERROR. La variable "+datos[1]+" no ha sido inicializada.");
                                                txaConsola.setText(txaConsola.getText()+"ERROR. La variable "+datos[1]+" no ha sido inicializada. \n");
                                                return false;
                                            }else{
                                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+datos[2]);
                                                encontrado = true;
                                            }
                                        }
                                    }
                                    if (!encontrado) {
                                        System.out.println("ERROR2. Se esperaba un valor VERDADERO o FALSO");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor VERDADERO o FALSO \n");
                                        return false;
                                    }
                                }else{
                                    
                                    String valo = ins[3];
                                    //System.out.println("valo = " + valo);
                                    valo = valo.replace(".", "¿");
                                    String[] partes = valo.split("¿");
                                    if (partes[1].equals("esMayuscula")) {
                                        for (int j = 0; j < detalleVariables.size(); j++) {
                                            String[] deta = detalleVariables.get(j).toString().split("¿");
                                            if (partes[0].equals(deta[1])) {
                                                
                                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+ins[3]);
                                                
                                                /*
                                                if (deta[0].equals("chicle")) {
                                                    char cara = deta[2].replace("\"", "").charAt(0);//
                                                    if (Character.isUpperCase(cara)) {
                                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                        detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+"VERDADERO");
                                                    }else{
                                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                        detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+"FALSO");
                                                    }
                                                }else{
                                                    System.out.println("ERROR2. Se esperaba un valor tipo chicle");
                                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo chicle \n");
                                                    return false;
                                                }
                                                break;
                                                */
                                            }
                                        }
                                    }else{
                                        System.out.println("ERROR2. Se esperaba un valor VERDADERO o FALSO");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor VERDADERO o FALSO \n");
                                        return false;
                                    }
                                    
                                    
                                }
                            }
                            break;
                        case "chicle":
                            // chile chVar = "d"
                            if (ins.length != 4) {
                                System.out.println("ERROR. Se esperaba un valor tipo chicle");
                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo chicle \n");
                                return false;
                            }else{
                                
                                if (!detalleVariables.isEmpty()) {
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String nomb = detalleVariables.get(j).toString().split("¿")[1];
                                        if (ins[1].equals(nomb)) {
                                            System.out.println("ERROR. Ya existe una variable con el nombre: "+nomb);
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Ya existe una variable con el nombre: "+nomb);
                                            return false;
                                        }
                                    }
                                }
                                
                                    if (revisarVariable(ins[3])) {
                                        boolean encontrado = false;
                                        for (int j = 0; j < detalleVariables.size(); j++) {
                                            String[] datos = detalleVariables.get(j).toString().split("¿");
                                            //String valor = detalleVariables.get(j).toString().split("¿")[2];

                                            if (datos[0].equals("chicle") && datos[1].equals(ins[3])) {
                                                if (datos.length == 2 || datos[1].equals(datos[2])) {
                                                    System.out.println("ERROR. La variable "+datos[1]+" no ha sido inicializada.");
                                                    txaConsola.setText(txaConsola.getText()+"ERROR. La variable "+datos[1]+" no ha sido inicializada. \n");
                                                    return false;
                                                }else{
                                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                    detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+datos[2]);
                                                    encontrado = true;
                                                }
                                            }
                                        }
                                        if (!encontrado) {
                                            System.out.println("ERROR45. Se esperaba un valor tipo chicle");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo chicle \n");
                                            return false;
                                        }
                                    }else{
                                        String[] chicle = ins[3].split("");
                                        if (chicle.length == 3) {
                                            if (chicle[0].equals("\"") && chicle[2].equals("\"")) {
                                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+ins[3]);
                                            }else{
                                                    System.out.println("ERROR23. Se esperaba un valor tipo chicle");
                                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo chicle \n");
                                                    return false;
                                                
                                            }
                                        }else{
                                            String in = ins[3].replace(".", "¿");
                                                String[] sepa = in.split("¿");
                                                if (revisarVariable(sepa[0])) {
                                                    if ("obtener".equals(sepa[1].substring(0, 7))) {
                                                        for (int j = 0; j < detalleVariables.size(); j++) {
                                                            String[] datos = detalleVariables.get(j).toString().split("¿");
                                                            if (datos[1].equals(sepa[0])) {
                                                                
                                                                if (revisarVariable(sepa[0])) {
                                                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                                    detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+"PORDEFINIR");
                                                                }else{
                                                                    System.out.println("ERROR123. Se esperaba una variable en el metodo obtener.");
                                                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba una variable en el metodo obtener.\n");
                                                                    return false;
                                                                }
                                                                
                                                                
                                                                
                                                                /*
                                                                String valor = "";
                                                                String cade = "";
                                                                if (String.valueOf(valor.charAt(0)).equals("\"")) {
                                                                    cade = valor.substring(1);
                                                                    cade = cade.substring(0, cade.length()-1);
                                                                }else{
                                                                   cade = valor;    
                                                                }
                                                                
                                                                
                                                                
                                                                String varNum = sepa[1].substring(8, sepa[1].length()-1);
                                                                boolean ba = false;
                                                                for (int k = 0; k < varNum.length(); k++) {
                                                                    if (!numeros.contains(String.valueOf(varNum.charAt(k)))) {
                                                                        ba = true;
                                                                    }
                                                                }
                                                                
                                                                if (ba) { //es variable
                                                                    
                                                                    if (revisarVariable(varNum)) {
                                                                        for (int k = 0; k < detalleVariables.size(); k++) {
                                                                            String[] dts = detalleVariables.get(k).toString().split("¿");
                                                                            if (dts[1].equals(String.valueOf(varNum))) {

                                                                                if ("rellerindo".equals(dts[0])) {
                                                                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                                                    detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+"PORDEFINIR");
                                                                                    break;
                                                                                }else{
                                                                                    System.out.println("ERROR123. Se esperaba un valor tipo rellerindo");
                                                                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                                                                    return false;
                                                                                }

                                                                            }
                                                                        }
                                                                    }else{
                                                                        System.out.println("ERROR24. Se esperaba un valor tipo rellerindo");
                                                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                                                        return false;
                                                                    }
                                                                    
                                                                }else{ // es número
                                                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                                    detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+"PORDEFINIR");
                                                                }
                                                                */
                                                                
                                                                
                                                            }
                                                        }
                                                    }else{
                                                        System.out.println("ERROR123. Se esperaba un valor tipo chicle");
                                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo chicle \n");
                                                        return false;
                                                    }
                                                }else{
                                                    System.out.println("ERROR2020. Se esperaba un valor tipo chicle");
                                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo chicle \n");
                                                    return false;
                                                }
                                        }
                                        
                                    }
                                
                            }
                            break;
                        case "rellerindo":
                            // rellerindo reVar = 23152151 + 134251
                            if (ins.length != 4) {
                                System.out.println("ERROR1. Se esperaba un valor tipo rellerindo");
                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                return false;
                            }else{
                                
                                if (!detalleVariables.isEmpty()) {
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String nomb = detalleVariables.get(j).toString().split("¿")[1];
                                        if (ins[1].equals(nomb)) {
                                            System.out.println("ERROR. Ya existe una variable con el nombre: "+nomb);
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Ya existe una variable con el nombre: "+nomb);
                                            return false;
                                        }
                                    }
                                }
                                
                                
                                if (revisarVariable(ins[3])) {
                                    boolean encontrado = false;
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String[] datos = detalleVariables.get(j).toString().split("¿");
                                        //String valor = detalleVariables.get(j).toString().split("¿")[2];

                                        if (datos[0].equals("rellerindo") && datos[1].equals(ins[3])) {
                                            if (datos.length == 2 || datos[1].equals(datos[2])) {
                                                System.out.println("ERROR. La variable "+datos[1]+" no ha sido inicializada.");
                                                txaConsola.setText(txaConsola.getText()+"ERROR. La variable "+datos[1]+" no ha sido inicializada. \n");
                                                return false;
                                            }else{
                                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN RELLERINDO CORRECTA2 ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+datos[2]);
                                                encontrado = true;
                                            }
                                        }
                                    }
                                    if (!encontrado) {
                                        System.out.println("ERROR. Se esperaba un valor tipo rellerindo");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                        return false;
                                    }
                                }else{
                                            
                                    for (int j = 0; j < ins[3].length()-1; j++) {
                                        String num = String.valueOf(ins[3].charAt(j)); // 2
                                        if (log.contains(num)) {
                                            if (j==ins[3].length()-1) {
                                                System.out.println("ERROR2. Se esperaba un valor tipo rellerindo");
                                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                                return false;
                                            }
                                        }else if (!numeros.contains(num)) {
                                            //System.out.println("num1 = " + num);
                                            String nume = ins[3].replace(".", "¿");
                                            //System.out.println("num2 = " + num);
                                            String[] sepa = nume.split("¿");
                                            //System.out.println("sepa[0] = " + sepa[0]);
                                            if (revisarVariable(sepa[0])) {
                                                if ("longitud".equals(sepa[1])) {
                                                    /*
                                                    int longi = 0;
                                                    for (int k = 0; k < detalleVariables.size(); k++) {
                                                        String[] part = detalleVariables.get(k).toString().split("¿");
                                                        if (sepa[0].equals(part[1])) {
                                                            longi = part[2].length();
                                                            if (String.valueOf(part[2].charAt(0)).equals("\"")) {
                                                                longi-=2;
                                                            }
                                                            break;
                                                        }
                                                    }
                                                    banLong = true;
                                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN RELLERINDO CORRECTA1 ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                    detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+longi);
                                                    break;
                                                    */
                                                }else{
                                                    System.out.println("ERROR6. Se esperaba un valor tipo rellerindo");
                                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                                    return false;
                                                }
                                            }else{
                                                System.out.println("ERROR53. La variable "+sepa[0]+" no existe.");
                                                txaConsola.setText(txaConsola.getText()+"ERROR. La variable "+sepa[0]+" no existe. \n");
                                                return false;
                                            }
                                        }
                                    }
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN RELLERINDO CORRECTA3 ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+ins[3]);
                                }
                                
                            }
                            break;
                        case "mazapan":
                            // mazapan maVar = 14.324 + 243.1234
                            if (ins.length != 4) {
                                System.out.println("ERROR1. Se esperaba un valor tipo mazapan");
                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                return false;
                            }else{
                                
                                if (!detalleVariables.isEmpty()) {
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String nomb = detalleVariables.get(j).toString().split("¿")[1];
                                        if (ins[1].equals(nomb)) {
                                            System.out.println("ERROR123. Ya existe una variable con el nombre: "+nomb);
                                            txaConsola.setText(txaConsola.getText()+"ERROR123. Ya existe una variable con el nombre: "+nomb);
                                            return false;
                                        }
                                    }
                                }
                                
                                if (log.contains(String.valueOf(ins[3].charAt(ins[3].length()-1)))) {
                                    System.out.println("ERROR2. Se esperaba un valor tipo mazapan");
                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                    return false;
                                }else{
                                    
                                    if (revisarVariable(ins[3])) {
                                        boolean encontrado = false;
                                        for (int j = 0; j < detalleVariables.size(); j++) {
                                            String[] datos = detalleVariables.get(j).toString().split("¿");
                                            //String valor = detalleVariables.get(j).toString().split("¿")[2];

                                            if (datos[0].equals("mazapan") && datos[1].equals(ins[3])) {
                                                if (datos.length == 2 || datos[1].equals(datos[2])) {
                                                    System.out.println("ERROR. La variable "+datos[1]+" no ha sido inicializada.");
                                                    txaConsola.setText(txaConsola.getText()+"ERROR. La variable "+datos[1]+" no ha sido inicializada. \n");
                                                    return false;
                                                }else{
                                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN MAZAPAN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                    detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+datos[2]);
                                                    encontrado = true;
                                                }
                                            }
                                        }
                                        if (!encontrado) {
                                            System.out.println("ERROR. Se esperaba un valor tipo mazapan");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                            return false;
                                        }
                                    }else{
                                        String valor = ins[3]; // 14.324+243.1234-43.234 --- [14.324,243.1234,43.234]
                                        // "+-*/%^";
                                        valor = valor.replace("+"," ");valor = valor.replace("-"," ");valor = valor.replace("+"," ");
                                        valor = valor.replace("/"," ");valor = valor.replace("%"," ");valor = valor.replace("^"," ");

                                        String[] separados = valor.split(" "); // [14.324,243.1234,43.234]

                                        for (int j = 0; j < separados.length; j++) {
                                            boolean bandePunto = true;
                                            String actual = separados[j]; // 14.324
                                            String[] caracter = actual.split("");
                                            for (int k = 0; k < caracter.length; k++) {
                                                String carAct = caracter[k];
                                                if (".".equals(carAct)) {
                                                    if (bandePunto) {
                                                        bandePunto = false;
                                                    }else{
                                                        System.out.println("ERROR3. Se esperaba un valor tipo mazapan");
                                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                                        return false;
                                                    }
                                                }else if (!numeros.contains(carAct)) {
                                                    System.out.println("ERROR4. Se esperaba un valor tipo mazapan");
                                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                                    return false;
                                                }
                                            }
                                        }
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN MAZAPAN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+ins[3]);
                                    }
                                }
                                
                            }
                            break;
                        case "Skwinkle": /*
                                            Skwinkle skVar = rNum1
                                            Skwinkle skVar = "hola mundo!"
                                        */
                            
                            if (!detalleVariables.isEmpty()) {
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String nomb = detalleVariables.get(j).toString().split("¿")[1];
                                    if (ins[1].equals(nomb)) {
                                        System.out.println("ERROR. Ya existe una variable con el nombre: "+nomb);
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Ya existe una variable con el nombre: "+nomb);
                                        return false;
                                    }
                                }
                            }
                            
                            String inst = instrucciones.get(i).toString();
                            String[] partes = instrucciones.get(i).toString().split(" ");
                            inst = inst.substring(12+partes[1].length());
                            
                            if (!"\"".equals(String.valueOf(inst.charAt(0)))) {
                                if (revisarVariable(inst)) {
                                    
                                    boolean encontrado = false;
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String[] datos = detalleVariables.get(j).toString().split("¿");
                                        //String valor = detalleVariables.get(j).toString().split("¿")[2];

                                        if (datos[0].equals("Skwinkle") && datos[1].equals(ins[3])) {
                                            if (datos.length == 2 || datos[1].equals(datos[2])) {
                                                System.out.println("ERROR. La variable "+datos[1]+" no ha sido inicializada.");
                                                txaConsola.setText(txaConsola.getText()+"ERROR. La variable "+datos[1]+" no ha sido inicializada. \n");
                                                return false;
                                            }else{
                                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN SKWINKLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                                detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+datos[2]);
                                                encontrado = true;
                                            }
                                        }
                                    }
                                    if (!encontrado) {
                                        System.out.println("ERROR. Se esperaba un valor tipo Skwinkle");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo Skwinkle \n");
                                        return false;
                                    }
                                    
                                }else{
                                    System.out.println("ERROR1. Se esperaba un valor tipo Skwinkle");
                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo Skwinkle \n");
                                    return false;
                                }
                            }else{
                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; DECLARACIÓN SKWINKLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                detalleVariables.add(ins[0]+"¿"+ins[1]+"¿"+inst);
                            }
                            break;
                        case "hersheys":
                    }
                }
            }
            // ASIGNACIÓN      rNum1 = 34+23.342
            //                 ins0  1     2
            else if (revisarVariable(ins[0])) {
                //saber el tipo de variable que se trata
                
                boolean encontrado = false;
                String tipo = "";
                for (int j = 0; j < detalleVariables.size(); j++) {
                    String[] datos = detalleVariables.get(j).toString().split("¿");
                    if (ins[0].equals(datos[1])) {
                        encontrado = true;
                        tipo = datos[0];
                    }
                }
                if (!encontrado) {
                    System.out.println("ERROR1. No se encontró la variable: "+ins[0]);
                    txaConsola.setText(txaConsola.getText()+"ERROR. No se encontró la variable: "+ins[0] +"\n");
                    return false;
                }else{
                    // boolean   char     int       double   String        Array 
                    // duvalin  chicle rellerindo  mazapan Skwinkle       hersheys }
                    
                    //    duvalin duVar = FALSO   - datos
                    //    duVar = VERDADERO       - instruccion
                    switch(tipo){
                        case "duvalin":
                            if (ins[2].equals("FALSO") || ins[2].equals("VERDADERO")) {
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] datos = detalleVariables.get(j).toString().split("¿");
                                    if (ins[0].equals(datos[1])) {
                                        
                                        for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                            String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                            if (aEliminar[0].equals("duvalin") && aEliminar[1].equals(ins[0])) {
                                                detalleVariables.remove(k);
                                            }
                                        }
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        detalleVariables.add(datos[0]+"¿"+datos[1]+"¿"+ins[2]);
                                    }
                                }
                            }else if(revisarVariable(ins[2])){
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] datos = detalleVariables.get(j).toString().split("¿");
                                    if (ins[2].equals(datos[1])) {
                                        if (datos[0].equals("duvalin") && datos.length > 2 ) {
                                            for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                                String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                                if (aEliminar[0].equals("duvalin") && aEliminar[1].equals(ins[0])) {
                                                    detalleVariables.remove(k);
                                                }
                                            }
                                            detalleVariables.add(datos[0]+"¿"+ins[0]+"¿"+datos[2]);
                                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        }else{
                                            System.out.println("ERROR1. Se esperaba un valor VERDADERO o FALSO.");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor VERDADERO o FALSO. \n");
                                            return false;
                                        }
                                    }
                                }
                            }else{
                                System.out.println("ERROR1. Se esperaba un valor VERDADERO o FALSO.");
                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor VERDADERO o FALSO. \n");
                                return false;
                            }
                            break;
                        case "chicle":
                            // chVar = "9"
                            // chVar = chVar2
                            if(revisarVariable(ins[2])){
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] datos = detalleVariables.get(j).toString().split("¿");
                                    if (ins[2].equals(datos[1])) {
                                        if (datos[0].equals("chicle") && datos.length > 2 ) {
                                            for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                                String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                                if (aEliminar[0].equals("chicle") && aEliminar[1].equals(ins[0])) {
                                                    detalleVariables.remove(k);
                                                }
                                            }
                                            detalleVariables.add(datos[0]+"¿"+ins[0]+"¿"+datos[2]);
                                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        }else{
                                            System.out.println("ERROR1. Se esperaba un valor de tipo chicle.");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo chicle. \n");
                                            return false;
                                        }
                                    }
                                }
                            }else if(ins[2].length() == 3){
                                
                                if ("\"".equals(String.valueOf(ins[2].charAt(0))) && "\"".equals(String.valueOf(ins[2].charAt(2)))) {
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String[] aEliminar = detalleVariables.get(j).toString().split("¿");
                                        if (aEliminar[0].equals("chicle") && aEliminar[1].equals(ins[0])) {
                                            detalleVariables.remove(j);
                                            detalleVariables.add(aEliminar[0]+"¿"+ins[0]+"¿"+ins[2]);
                                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        }
                                    }
                                }else{
                                    System.out.println("ERROR2. Se esperaba un valor de tipo chicle.");
                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo chicle. \n");
                                    return false;
                                }
                            }else{
                                System.out.println("ERROR3. Se esperaba un valor de tipo chicle.");
                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo chicle. \n");
                                return false;
                            }
                            
                            break;
                        case "rellerindo":
                            // reVar = 123
                            // reVar = reVar2
                            
                            if (revisarVariable(ins[2])) {
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] datos = detalleVariables.get(j).toString().split("¿");
                                    if (ins[2].equals(datos[1])) {
                                        if (datos[0].equals("rellerindo") && datos.length > 2 ) {
                                            for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                                String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                                if (aEliminar[0].equals("rellerindo") && aEliminar[1].equals(ins[0])) {
                                                    detalleVariables.remove(k);
                                                }
                                            }
                                            detalleVariables.add(datos[0]+"¿"+ins[0]+"¿"+datos[2]);
                                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN RELLERINDO CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        }else{
                                            System.out.println("ERROR1. Se esperaba un valor de tipo rellerindo.");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo rellerindo. \n");
                                            return false;
                                        }
                                    }
                                }
                            }else{  // reVar = 123
                                    // reVar = reVar2
                                String res = "";
                                boolean ban = false;
                                for (int j = 0; j < ins[2].length(); j++) { //235
                                    String num = String.valueOf(ins[2].charAt(j)); // 2
                                    if (log.contains(num)) {
                                        if (j==ins[2].length()-1) {
                                            System.out.println("ERROR2. Se esperaba un valor tipo rellerindo");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                            return false;
                                        }
                                    }else if (!numeros.contains(num)) {
                                        //   + - * / 
                                        ban = true;
                                        break;
                                    }
                                    
                                }
                                if (ban) {
                                    String op = ins[2];
                                    List signos = new ArrayList();


                                    for (int k = 0; k < op.length(); k++) {
                                        if (log.contains(String.valueOf(op.charAt(k)))) {
                                            signos.add(String.valueOf(op.charAt(k)));
                                        }
                                    }
                                    
                                    op = op.replace("+", "¿");   op = op.replace("-", "¿"); 
                                    op = op.replace("*", "¿");     op = op.replace("/", "¿"); 
                                    String[] sepa = op.split("¿");   // hola+1   ---  hola¿1
                                    

                                    for (int k = 0; k < sepa.length; k++) { // hola, 1
                                        boolean band = false;
                                        for (int l = 0; l < sepa[k].length(); l++) {
                                            String aEvaluar = String.valueOf(sepa[k].charAt(l));
                                            if (!numeros.contains(aEvaluar)) {
                                                band = true;
                                                break;
                                            }
                                        }
                                        if (band) { // variable
                                            if (!revisarVariable(sepa[k])) {
                                                System.out.println("ERROR42. Se esperaba un valor tipo rellerindo");
                                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                                return false;
                                            }else{
                                                for (int l = 0; l < detalleVariables.size(); l++) {
                                                    String[] deta = detalleVariables.get(l).toString().split("¿");
                                                    if (deta[1].equals(sepa[k])) {
                                                        if (!deta[0].equals("rellerindo")) {
                                                            System.out.println("ERROR45. Se esperaba un valor tipo rellerindo");
                                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                                            return false;
                                                        }else{
                                                            res += deta[2];
                                                            System.out.println("res = " + res);
                                                            break;
                                                        }
                                                    }

                                                }
                                            }
                                            if (signos.size() > 0) {
                                                res += signos.get(0);
                                                signos.remove(0);
                                            }                                            
                                        }else{
                                            res += sepa[k];
                                            if (signos.size() > 0) {
                                                res += signos.get(0);
                                                signos.remove(0);
                                            }  
                                        }

                                    }

                                    
                                }
                                
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] aEliminar = detalleVariables.get(j).toString().split("¿");
                                    if (aEliminar[0].equals("rellerindo") && aEliminar[1].equals(ins[0])) {
                                        detalleVariables.remove(j);
                                        detalleVariables.add(aEliminar[0]+"¿"+ins[0]+"¿"+res);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN RELLERINDO CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }
                                }
                                
                                
                            }
                            
                            break;
                        case "mazapan":
                            // maVar = 123.123
                            // maVar = maVar2
                            
                            if (revisarVariable(ins[2])) {
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] datos = detalleVariables.get(j).toString().split("¿");
                                    if (ins[2].equals(datos[1])) {
                                        if (datos[0].equals("mazapan") && datos.length > 2 ) {
                                            for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                                String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                                if (aEliminar[0].equals("mazapan") && aEliminar[1].equals(ins[0])) {
                                                    detalleVariables.remove(k);
                                                }
                                            }
                                            detalleVariables.add(datos[0]+"¿"+ins[0]+"¿"+datos[2]);
                                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN MAZAPAN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        }else{
                                            System.out.println("ERROR1. Se esperaba un valor de tipo mazapan.");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo mazapan. \n");
                                            return false;
                                        }
                                    }
                                }
                            }else{
                                
                                String valor = ins[2]; // 14.324+243.1234-43.234 --- [14.324,243.1234,43.234]
                                        // "+-*/%^";
                                valor = valor.replace("+"," ");valor = valor.replace("-"," ");valor = valor.replace("+"," ");
                                valor = valor.replace("/"," ");valor = valor.replace("%"," ");valor = valor.replace("^"," ");

                                String[] separados = valor.split(" "); // [14.324,243.1234,43.234]

                                for (int j = 0; j < separados.length; j++) {
                                    boolean bandePunto = true;
                                    String actual = separados[j]; // 14.324
                                    String[] caracter = actual.split("");
                                    for (int k = 0; k < caracter.length; k++) {
                                        String carAct = caracter[k];
                                        if (".".equals(carAct)) {
                                            if (bandePunto) {
                                                bandePunto = false;
                                            }else{
                                                System.out.println("ERROR3. Se esperaba un valor tipo mazapan");
                                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                                return false;
                                            }
                                        }else if (!numeros.contains(carAct)) {
                                            System.out.println("ERROR4. Se esperaba un valor tipo mazapan");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                            return false;
                                        }
                                    }
                                }
                                
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] aEliminar = detalleVariables.get(j).toString().split("¿");
                                    if (aEliminar[0].equals("mazapan") && aEliminar[1].equals(ins[0])) {
                                        detalleVariables.remove(j);
                                        detalleVariables.add(aEliminar[0]+"¿"+ins[0]+"¿"+ins[2]);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN MAZAPAN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }
                                }
                                
                            }
                            break;
                        case "Skwinkle":
                            // skVar = "gsdj"
                            // skVar = skVar2
                            
                            if (revisarVariable(ins[2])) {
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] datos = detalleVariables.get(j).toString().split("¿");
                                    if (ins[2].equals(datos[1])) {
                                        if (datos[0].equals("Skwinkle") && datos.length > 2 ) {
                                            for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                                String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                                if (aEliminar[0].equals("Skwinkle") && aEliminar[1].equals(ins[0])) {
                                                    detalleVariables.remove(k);
                                                }
                                            }
                                            detalleVariables.add(datos[0]+"¿"+ins[0]+"¿"+datos[2]);
                                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN SKWINKLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        }else{
                                            System.out.println("ERROR1. Se esperaba un valor de tipo Skwinkle.");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo Skwinkle. \n");
                                            return false;
                                        }
                                    }
                                }
                            }else{
                                
                                String val = instrucciones.get(i).toString();
                                val = val.substring(ins[0].length()+3);
                                System.out.println("val = " + val);
                                
                                if("\"".equals(String.valueOf(val.charAt(0))) && "\"".equals(String.valueOf(val.charAt(val.length()-1)))){
                                    
                                    for (int j = 0; j < detalleVariables.size(); j++) {
                                        String[] aEliminar = detalleVariables.get(j).toString().split("¿");
                                        if (aEliminar[0].equals("Skwinkle") && aEliminar[1].equals(ins[0])) {
                                            detalleVariables.remove(j);
                                            detalleVariables.add(aEliminar[0]+"¿"+ins[0]+"¿"+val);
                                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN SKWINKLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                        }
                                    }
                                    
                                }else{
                                    System.out.println("ERROR2. Se esperaba un valor de tipo Skwinkle.");
                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo Skwinkle. \n");
                                    return false;
                                }
                            }
                            break;
                        case "hersheys":
                            break;
                    }
                }
            } // SI
            else if ("S".equals(car[0]) && "I".equals(car[1]) && "(".equals(car[2])) {
                
                if (compara.contains(ins[1])) {
                    String var1 = ins[0].substring(3);
                    String var2 = ins[2].substring(0,ins[2].length()-2);
                    int c = 0;
                    boolean v1 = false;
                    boolean v2 = false;
                    for (int j = 0; j < var1.length(); j++) {
                        if (!numeros.contains(String.valueOf(var1.charAt(j)))) {
                            v1 = true;
                        }
                    }
                    for (int j = 0; j < var2.length(); j++) {
                        if (!numeros.contains(String.valueOf(var2.charAt(j)))) {
                            v2 = true;
                        }
                    }
                    
                    if (v1 && v2) {
                        for (int j = 0; j < detalleVariables.size(); j++) {
                            String[] encontrado = detalleVariables.get(j).toString().split("¿");
                            if (encontrado[1].equals(var1) && (encontrado[0].equals("rellerindo") || encontrado[0].equals("mazapan"))) {
                                if (encontrado.length == 3) {
                                    c++;
                                }else{
                                    System.out.println("Error1. Sentencia SI inválida.");
                                    System.out.println("La variable "+var1+" no se ha inicializado.");
                                    txaConsola.setText("Error. Sentencia SI inválida.\nLa variable "+var1+" no se ha inicializado.");
                                    return false;
                                }
                            }
                            if (encontrado[1].equals(var2) && (encontrado[0].equals("rellerindo") || encontrado[0].equals("mazapan"))) {
                                if (encontrado.length == 3) {
                                    c++;
                                }else{
                                    System.out.println("Error2. Sentencia SI inválida.");
                                    System.out.println("La variable "+var2+" no se ha inicializado.");
                                    txaConsola.setText("Error. Sentencia SI inválida.\nLa variable "+var2+" no se ha inicializado.");
                                    return false;
                                }
                            }
                        }

                        if (c == 2) {
                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; SENTENCIA SI CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                        }else{
                            System.out.println("Error3. Sentencia SI inválida.");
                            System.out.println("Se esperaban variables tipo rellerindo.");
                            txaConsola.setText("Error. Sentencia SI inválida.\nSe esperaban variables tipo rellerindo.");
                            return false;
                        }
                    }else{
                        if (v1 && !v2) { //v1 es variable
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] encontrado = detalleVariables.get(j).toString().split("¿");
                                if (encontrado[1].equals(var1) && (encontrado[0].equals("rellerindo") || encontrado[0].equals("mazapan"))) {
                                    if (encontrado.length == 3) {
                                        c++;
                                    }else{
                                        System.out.println("Error1. Sentencia SI inválida.");
                                        System.out.println("La variable "+var1+" no se ha inicializado.");
                                        txaConsola.setText("Error. Sentencia SI inválida.\nLa variable "+var1+" no se ha inicializado.");
                                        return false;
                                    }
                                }
                            }
                            if (c == 1) {
                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; SENTENCIA SI CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                            }else{
                                System.out.println("Error3. Sentencia SI inválida.");
                                System.out.println("Se esperaban variables tipo rellerindo.");
                                txaConsola.setText("Error. Sentencia SI inválida.\nSe esperaban variables tipo rellerindo.");
                                return false;
                            }
                        }else if(!v1 && v2){ //v2 es variable
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] encontrado = detalleVariables.get(j).toString().split("¿");
                                if (encontrado[1].equals(var2) && (encontrado[0].equals("rellerindo") || encontrado[0].equals("mazapan"))) {
                                    if (encontrado.length == 3) {
                                        c++;
                                    }else{
                                        System.out.println("Error1. Sentencia SI inválida.");
                                        System.out.println("La variable "+var2+" no se ha inicializado.");
                                        txaConsola.setText("Error. Sentencia SI inválida.\nLa variable "+var2+" no se ha inicializado.");
                                        return false;
                                    }
                                }
                            }
                            if (c == 1) {
                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; SENTENCIA SI CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                            }else{
                                System.out.println("Error3. Sentencia SI inválida.");
                                System.out.println("Se esperaban variables tipo rellerindo.");
                                txaConsola.setText("Error. Sentencia SI inválida.\nSe esperaban variables tipo rellerindo.");
                                return false;
                            }
                        }else{
                            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; SENTENCIA SI CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                        }
                    }
                    
                }else{
                    String var = ins[1];
                    for (int j = 0; j < detalleVariables.size(); j++) {
                        String[] det = detalleVariables.get(j).toString().split("¿");
                        if (det[1].equals(var)) {
                            if (!"duvalin".equals(det[0])) {
                                System.out.println("Error3. Sentencia SI inválida.");
                                System.out.println("Se esperaba variable tipo duvalin.");
                                txaConsola.setText("Error. Sentencia SI inválida.\nSe esperaba variable tipo duvalin.");
                                return false;
                            }
                            break;
                        }
                    }
                }
                
            }
            /*
                
        
                DO WHILE
                    HAZ { instucciones } MIENTRAS ( Var,num,cade opCond Var,num,cade )
                
            */
            else if (instrucciones.get(i).toString().length() > 7) {
                if ("MIENTRAS".equals(instrucciones.get(i).toString().substring(0, 8))) { // MIENTRAS(hola MAIGUAL 5){
                    if (compara.contains(ins[1])) {
                        String var1 = ins[0].substring(9);
                        String var2 = ins[2].substring(0,ins[2].length()-1);

                        //=======================

                        int c = 0;
                        boolean v1 = false;
                        boolean v2 = false;
                        for (int j = 0; j < var1.length(); j++) {
                            if (!numeros.contains(String.valueOf(var1.charAt(j)))) {
                                v1 = true;
                            }
                        }
                        for (int j = 0; j < var2.length(); j++) {
                            if (!numeros.contains(String.valueOf(var2.charAt(j)))) {
                                v2 = true;
                            }
                        }

                        if (v1 && v2) {
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] encontrado = detalleVariables.get(j).toString().split("¿");
                                if (encontrado[1].equals(var1) && (encontrado[0].equals("rellerindo") || encontrado[0].equals("mazapan"))) {
                                    if (encontrado.length == 3) {
                                        c++;
                                    }else{
                                        System.out.println("Error1. Sentencia MIENTRAS inválida.");
                                        System.out.println("La variable "+var1+" no se ha inicializado.");
                                        txaConsola.setText("Error. Sentencia MIENTRAS inválida.\nLa variable "+var1+" no se ha inicializado.");
                                        return false;
                                    }
                                }
                                if (encontrado[1].equals(var2) && (encontrado[0].equals("rellerindo") || encontrado[0].equals("mazapan"))) {
                                    if (encontrado.length == 3) {
                                        c++;
                                    }else{
                                        System.out.println("Error2. Sentencia MIENTRAS inválida.");
                                        System.out.println("La variable "+var2+" no se ha inicializado.");
                                        txaConsola.setText("Error. Sentencia MIENTRAS inválida.\nLa variable "+var2+" no se ha inicializado.");
                                        return false;
                                    }
                                }
                            }
                            if (c == 2) {
                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; SENTENCIA MIENTRAS CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                            }else{
                                System.out.println("Error3. Sentencia MIENTRAS inválida.");
                                System.out.println("Se esperaban variables tipo rellerindo.");
                                txaConsola.setText("Error. Sentencia MIENTRAS inválida.\nSe esperaban variables tipo rellerindo.");
                                return false;
                            }
                        }else{
                            if (v1 && !v2) { //v1 es variable
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] encontrado = detalleVariables.get(j).toString().split("¿");
                                    if (encontrado[1].equals(var1) && (encontrado[0].equals("rellerindo") || encontrado[0].equals("mazapan"))) {
                                        if (encontrado.length == 3) {
                                            c++;
                                        }else{
                                            System.out.println("Error1. Sentencia MIENTRAS inválida.");
                                            System.out.println("La variable "+var1+" no se ha inicializado.");
                                            txaConsola.setText("Error. Sentencia MIENTRAS inválida.\nLa variable "+var1+" no se ha inicializado.");
                                            return false;
                                        }
                                    }
                                }
                                if (c == 1) {
                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; SENTENCIA MIENTRAS CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                }else{
                                    System.out.println("Error3. Sentencia MIENTRAS inválida.");
                                    System.out.println("Se esperaban variables tipo rellerindo.");
                                    txaConsola.setText("Error. Sentencia MIENTRAS inválida.\nSe esperaban variables tipo rellerindo.");
                                    return false;
                                }
                            }else if(!v1 && v2){ //v2 es variable
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] encontrado = detalleVariables.get(j).toString().split("¿");
                                    System.out.println("encontrado[1] = " + encontrado[1]);
                                    System.out.println("var2 = " + var2);
                                    if (encontrado[1].equals(var2) && (encontrado[0].equals("rellerindo") || encontrado[0].equals("mazapan"))) {
                                        if (encontrado.length == 3) {
                                            c++;
                                        }else{
                                            System.out.println("Error1. Sentencia MIENTRAS inválida.");
                                            System.out.println("La variable "+var2+" no se ha inicializado.");
                                            txaConsola.setText("Error. Sentencia MIENTRAS inválida.\nLa variable "+var2+" no se ha inicializado.");
                                            return false;
                                        }
                                    }
                                }
                                System.out.println("c = " + c);
                                if (c == 1) {
                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; SENTENCIA MIENTRAS CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                }else{
                                    System.out.println("Error3. Sentencia MIENTRAS inválida.");
                                    System.out.println("Se esperaban variables tipo rellerindo.");
                                    txaConsola.setText("Error. Sentencia MIENTRAS inválida.\nSe esperaban variables tipo rellerindo.");
                                    return false;
                                }
                            }else{
                                System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; SENTENCIA MIENTRAS CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                            }
                        }
                        //=======================

                    }else{

                    }                                                                        //                    v            v               v
                }
            }else if (instrucciones.get(i).toString().length() > 4 && "PARA".equals(instrucciones.get(i).toString().substring(0, 4))) {//PARA(rellerindo re1 = 0 re1 MAYOR 10 re1 = re1  +  1){
                                                                                        //     ins[0]     1  2 3  4   5     6  7  8  9  10   11
                String p0 = ins[0].substring(5, ins[0].length());
                String p1 = ins[1];
                String p2 = ins[2];
                String p3 = ins[3];
                String p4 = ins[4];
                String p5 = ins[5];
                String p6 = ins[6];
                String p7 = ins[7];
                String p8 = ins[8];
                String p9 = ins[9];
                String p10 = ins[10];
                String p11 = ins[11].substring(0, ins[11].length()-2);
                
                int co = 0;
                for (int j = 0; j < variables.size(); j++) {
                    System.out.println("datos[1] = " + variables.get(j));
                    System.out.println("p1 = " + p1);
                    if (variables.get(j).equals(p1)) {
                        co++;
                    }
                }
                if (co != 1) {
                    System.out.println("Error1. Sentencia PARA inválida.");
                    System.out.println("La variable "+p1+" ya ha sido declarada previamente.");
                    txaConsola.setText("Error. Sentencia PARA inválida.\nLa variable "+p1+" ya ha sido declarada previamente.");
                    return false;
                }
                
                if (revisarVariable(p3)) {
                    for (int j = 0; j < p3.length(); j++) {
                        if (!numeros.contains(String.valueOf(p3.charAt(j)))) {
                            for (int k = 0; k < detalleVariables.size(); k++) {
                                String[] datos = detalleVariables.get(k).toString().split("¿");
                                if (datos[1].equals(p3)) {
                                    if (!datos[0].equals("rellerindo")) {
                                        System.out.println("Error1. Sentencia PARA inválida.");
                                        System.out.println("Se esperaba variable tipo rellerindo.");
                                        txaConsola.setText("Error. Sentencia PARA inválida.\nSe esperaba variable tipo rellerindo.");
                                        return false;
                                    }else if (datos.length != 3) {
                                        System.out.println("Error1. Sentencia PARA inválida.");
                                        System.out.println("No se ha inicializado la variable "+p3);
                                        txaConsola.setText("Error. Sentencia PARA inválida.\nNo se ha inicializado la variable "+p3);
                                        return false;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                if (revisarVariable(p6)) {
                    for (int j = 0; j < p6.length(); j++) {
                        if (!numeros.contains(String.valueOf(p6.charAt(j)))) {
                            for (int k = 0; k < detalleVariables.size(); k++) {
                                String[] datos = detalleVariables.get(k).toString().split("¿");
                                if (datos[1].equals(p6)) {
                                    if (!datos[0].equals("rellerindo")) {
                                        System.out.println("Error2. Sentencia PARA inválida.");
                                        System.out.println("Se esperaba variable tipo rellerindo.");
                                        txaConsola.setText("Error. Sentencia PARA inválida.\nSe esperaba variable tipo rellerindo.");
                                        return false;
                                    }else if (datos.length != 3) {
                                        System.out.println("Error1. Sentencia PARA inválida.");
                                        System.out.println("No se ha inicializado la variable "+p6);
                                        txaConsola.setText("Error. Sentencia PARA inválida.\nNo se ha inicializado la variable "+p6);
                                        return false;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                if (revisarVariable(p11)) {
                    for (int j = 0; j < p11.length(); j++) {
                        if (!numeros.contains(String.valueOf(p11.charAt(j)))) {
                            for (int k = 0; k < detalleVariables.size(); k++) {
                                String[] datos = detalleVariables.get(k).toString().split("¿");
                                if (datos[1].equals(p11)) {
                                    if (!datos[0].equals("rellerindo")) {
                                        System.out.println("Error3. Sentencia PARA inválida.");
                                        System.out.println("Se esperaba variable tipo rellerindo.");
                                        txaConsola.setText("Error. Sentencia PARA inválida.\nSe esperaba variable tipo rellerindo.");
                                        return false;
                                    }else if (datos.length != 3) {
                                        System.out.println("Error1. Sentencia PARA inválida.");
                                        System.out.println("No se ha inicializado la variable "+p11);
                                        txaConsola.setText("Error. Sentencia PARA inválida.\nNo se ha inicializado la variable "+p11);
                                        return false;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                

                if ("rellerindo".equals(p0) && revisarVariable(p1) && "=".equals(p2) && numOvar(p3) && p1.equals(p4) && compara.contains(p5) && numOvar(p6)
                        && p1.equals(p7) && "=".equals(p8) && p1.equals(p9) && log.contains(p10) && numOvar(p11.substring(0, ins[11].length()-2))) {
                    txaConsola.setText("Sentencia PARA correcta.");
                }
                                                                                        
            }
 
            System.out.println("instrucción "+i+"--->  "+ instrucciones.get(i));
        }
        System.out.println("DETALLE DE VARIABLES:");
        for (int i = 0; i < detalleVariables.size(); i++) {
            System.out.println(detalleVariables.get(i));
        }
        return true;
        
    }
    // ================================================= SEMNÁNTICO ==================================================
    
    
    // ================================================= DISEÑO ==================================================
    //CONFRIMAR CERRAR PROGRAMA
    private void Cerrar() {
        if (!ruta.equals("")) {
            int iOpcion = JOptionPane.showOptionDialog(null, "¿Desea guardar los cambios?",
                    "Selecciona una opcion...",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new Object[]{"Guardar", "No Guardar", "Cancelar"}, "Guardar");

            System.out.println(iOpcion);

            if (iOpcion == JOptionPane.YES_OPTION) {
                try (FileWriter fw = new FileWriter(ruta)) {
                    fw.write(this.txaCode.getText());

                } catch (IOException el) {
                    el.printStackTrace();
                }

                if (rootPaneCheckingEnabled) { //Si le da clic en si

                }
                System.exit(0);

            } else if (iOpcion == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else if (iOpcion == JOptionPane.CANCEL_OPTION) {
                System.out.println("Se cancelo");
            }
        } else {
            System.exit(0);
        }
    }

    //BORDES REDONDOS
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

    NumeroLinea numeroLinea;

    // ================================================= DISEÑO ==================================================
    public Compiler() {
        initComponents();
        int x = 1;
        numeroLinea = new NumeroLinea(txaCode);
        jScrollPane1.setRowHeaderView(numeroLinea);

        ((AbstractDocument) txaCode.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        
        

        txaCode.setText("INICIO\n"
                + "\n"
                + "	(: Escriba su Código aquí :)\n"
                + "\n"
                + "\n"
                + "FIN");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.setTitle("Net Candy Compiler");

        setSize(1295, 755);
        this.setLocationRelativeTo(null);

        btnCompilar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCerrar.setEnabled(false);
        txaCode.setEnabled(false);
        txaConsola.setEditable(false);

        //btnNuevo.setBorder(new RoundedBorder (30));
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

        btnNuevo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaConsola = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaCode = new javax.swing.JTextPane();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abrir.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(null);
        btnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevoMouseExited(evt);
            }
        });
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(80, 70, 130, 50);

        txaConsola.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 3, true));
        txaConsola.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(txaConsola);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(290, 510, 930, 190);

        txaCode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 3, true));
        txaCode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txaCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txaCodeKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txaCode);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(290, 30, 930, 460);

        btnAbrir.setBackground(new java.awt.Color(255, 255, 255));
        btnAbrir.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abrir2.png"))); // NOI18N
        btnAbrir.setText(" Abrir");
        btnAbrir.setBorder(null);
        btnAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAbrirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAbrirMouseExited(evt);
            }
        });
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        getContentPane().add(btnAbrir);
        btnAbrir.setBounds(80, 143, 130, 50);

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gua.png"))); // NOI18N
        btnGuardar.setText(" Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(80, 216, 130, 50);

        btnCompilar.setBackground(new java.awt.Color(255, 255, 255));
        btnCompilar.setFont(new java.awt.Font("Segoe Print", 1, 16)); // NOI18N
        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play.png"))); // NOI18N
        btnCompilar.setText("Compilar");
        btnCompilar.setBorder(null);
        btnCompilar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCompilarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCompilarMouseExited(evt);
            }
        });
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCompilar);
        btnCompilar.setBounds(80, 289, 130, 50);

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(80, 362, 130, 50);

        btnCerrar.setBackground(new java.awt.Color(255, 255, 255));
        btnCerrar.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cerrar.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.setBorder(null);
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar);
        btnCerrar.setBounds(80, 435, 130, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PALABRA 1.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(1220, 110, 40, 290);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PALABRA 1.2.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(1220, 490, 50, 240);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/net candy compiler peque.png"))); // NOI18N
        getContentPane().add(logo);
        logo.setBounds(53, 562, 190, 90);

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setForeground(new java.awt.Color(255, 255, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Mesa de trabajo 1.png"))); // NOI18N
        getContentPane().add(fondo);
        fondo.setBounds(0, -190, 2020, 1090);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:

        JFileChooser fc = new JFileChooser();

        int seleccion = fc.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();

            try (FileWriter fw = new FileWriter(fichero)) {
                fw.write(this.txaCode.getText());

                File destino = new File(fichero.getPath() + ".candy");
                System.out.println(destino.getPath());
                //InputStream in = new FileInputStream(origen);
                //OutputStream out = new FileOutputStream(destino);
                destino.createNewFile();
                try (FileReader fr = new FileReader(fichero)) {
                    String cadena = "";
                    int valor = fr.read();
                    while (valor != -1) {
                        cadena = cadena + (char) valor;
                        valor = fr.read();
                    }
                    System.out.println("cadena = " + txaCode.getText());
                    FileWriter filew = new FileWriter(destino);
                    BufferedWriter bw = new BufferedWriter(filew);
                    ruta = destino.getPath();
                    bw.write(txaCode.getText());
                    bw.close();
                }
                fw.close();
                fichero.delete();
                //in.close();
                //out.close();
            } catch (IOException el) {
                el.printStackTrace();
            }
            btnCompilar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
            btnCerrar.setEnabled(true);
            txaCode.setEnabled(true);
        }


    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        // TODO add your handling code here:

        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        cont = 0;
        linea = 1;
        aux = "";
        band = false;
        bander = false;
        bandeClase = false;
        conta = 0;
        contaObtener = 0;
        bandeObtener = false;
        //verificarCorchete = "";
        llaves.clear();
        variables.clear();
        palabras.clear();
        clase.clear();
        instrucciones.clear();
        txaConsola.setText("");
        detalleVariables.clear();
        if (analizarLexico(txaCode.getText())) {
            
            System.out.println("**** PALABRAS ****");
            System.out.println(palabras);
            System.out.println("**** VARIABLES ****");
            System.out.println(variables);
            System.out.println("**** CLASES ****");
            System.out.println(clase);
            System.out.println("---------------------------------------Análisis léxico correcto.");
            if (analizarSintactico()) {
                System.out.println("---------------------------------------Análisis sintáctico correcto.");
                System.out.println("LISTA DE INSTRUCCIONES");
                System.out.println(instrucciones);
                if (analizarSemantico()) {
                    System.out.println("---------------------------------------Análisis semántico correcto.");
                    
                    //detalleVariables
                    //instrucciones
                    //variables
                    
                    //simplificarValores();
                    
                    
                    System.out.println("========================== REALIZACIÓN DE INSTRUCCIONES ==========================");
                    for (int i = 0; i < instrucciones.size(); i++) {
                        
                        int j = evaluarInstrucciones(instrucciones.get(i).toString(), i);
                        if (j != 0) {
                            i = j;
                        }
                    }
                    
                    
                    
                    
                }else{
                    System.out.println("---------------------------------------Error en el análisis semántico.");
                }
            } else {
                System.out.println("---------------------------------------Error en el análisis sintáctico.");
            }

        } else {
            System.out.println("---------------------------------------Error en el análisis léxico.");
        }

    }//GEN-LAST:event_btnCompilarActionPerformed
    public void simplificarValores(){
        String letras = "qwertyuiopñlkjhgfdsazxcvbnmQWERTYUIOPÑLKJHGFDSAZXCVBNM";
        for (int i = 0; i < detalleVariables.size(); i++) {
            String resul = "";
            String[] datos = detalleVariables.get(i).toString().split("¿");
            if (("rellerindo".equals(datos[0]) || "mazapan".equals(datos[0])) && datos.length == 3) {
                String valor = datos[2];
                boolean esOp = false;
                for (int j = 0; j < valor.length(); j++) {
                    if (log.contains(String.valueOf(valor.charAt(j)))) {
                        esOp = true;
                    }
                }
                if (esOp) { // 223+13-3
                    String[] caracts = datos[2].split("");
                    List partes = new ArrayList();
                    String nu = "";
                    for (int j = 0; j < caracts.length; j++) {
                        if (j == caracts.length-1) {
                            nu += caracts[j];
                            partes.add(nu);
                        }else if (numeros.contains(caracts[j]) || ".".equals(caracts[j]) || letras.contains(caracts[j])) {
                            nu += caracts[j];
                        }else{
                            boolean ban = false;
                            for (int k = 0; k < caracts.length; k++) {
                                if (!numeros.contains(caracts[k])) {
                                    ban = true;
                                }
                            }
                            //if (ban) { // es variable
                              //  if (!revisarVariable(nu)) {
                                //    txaConsola.setText(txaConsola.getText()+"Error. Se esperaba una variable en la operación.");
                                //}
                            //}
                            partes.add(nu);
                            nu = "";
                        }
                    }
                    // Ya tenemos cada parte de numeros
                    List signos = new ArrayList();
                    for (int j = 0; j < caracts.length; j++) {
                        if (log.contains(caracts[j])) {
                            signos.add(caracts[j]);
                        }
                    }
                    // Numeros y signos en su respectiva lista
                    /*
                        Jerarquia
                                  ^
                                 * /
                                 + -Math.pow(n1, n2);
                    */
                    boolean powWhile = true;

                    while (powWhile) {                                    
                        int c = 0;
                        for (int j = 0; j < signos.size(); j++) {
                            if ("^".equals(signos.get(j))) {
                                //System.out.println("PARTES: "+partes);
                                //System.out.println("SIGNOS: "+signos);
                                c++;
                                if ("rellerindo".equals(datos[0])) {
                                    signos.remove(j);
                                    int n1 = Integer.parseInt(partes.get(j).toString());
                                    int n2 = Integer.parseInt(partes.get(j+1).toString());
                                    int res = (int) Math.pow(n1, n2);
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }else{
                                    signos.remove(j);
                                    double n1 = Double.parseDouble(partes.get(j).toString());
                                    double n2 = Double.parseDouble(partes.get(j+1).toString());
                                    double res = Math.pow(n1, n2);
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }
                            }
                        }
                        if (c == 0) {
                            powWhile = false;
                        }
                    }

                    boolean mulDiv = true;

                    while (mulDiv) {                                    
                        int c = 0;
                        for (int j = 0; j < signos.size(); j++) {
                            if ("*".equals(signos.get(j))) {
                                //System.out.println("PARTES: "+partes);
                                //System.out.println("SIGNOS: "+signos);
                                c++;
                                if ("rellerindo".equals(datos[0])) {
                                    signos.remove(j);
                                    int n1 = Integer.parseInt(partes.get(j).toString());
                                    int n2 = Integer.parseInt(partes.get(j+1).toString());
                                    int res = n1*n2;
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }else{
                                    signos.remove(j);
                                    double n1 = Double.parseDouble(partes.get(j).toString());
                                    double n2 = Double.parseDouble(partes.get(j+1).toString());
                                    double res = n1*n2;
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }
                            }else if ("/".equals(signos.get(j))) {
                                c++;
                                if ("rellerindo".equals(datos[0])) {
                                    signos.remove(j);
                                    int n1 = Integer.parseInt(partes.get(j).toString());
                                    int n2 = Integer.parseInt(partes.get(j+1).toString());
                                    int res = n1/n2;
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }else{
                                    signos.remove(j);
                                    double n1 = Double.parseDouble(partes.get(j).toString());
                                    double n2 = Double.parseDouble(partes.get(j+1).toString());
                                    double res = n1/n2;
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }

                            }
                        }
                        if (c == 0) {
                            mulDiv = false;
                        }
                    }

                    boolean sumRes = true;

                    while(sumRes){
                        int c = 0;
                        for (int j = 0; j < signos.size(); j++) {
                            if ("+".equals(signos.get(j))) {
                                //System.out.println("PARTES: "+partes);
                                //System.out.println("SIGNOS: "+signos);
                                c++;
                                if ("rellerindo".equals(datos[0])) {
                                    signos.remove(j);

                                    int n1 = Integer.parseInt(partes.get(j).toString());
                                    int n2 = Integer.parseInt(partes.get(j+1).toString());
                                    int res = n1+n2;
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }else{
                                    signos.remove(j);
                                    double n1 = Double.parseDouble(partes.get(j).toString());
                                    double n2 = Double.parseDouble(partes.get(j+1).toString());
                                    double res = n1+n2;
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }
                            }else if ("-".equals(signos.get(j))) {
                                c++;
                                if ("rellerindo".equals(datos[0])) {
                                    signos.remove(j);
                                    int n1 = Integer.parseInt(partes.get(j).toString());
                                    int n2 = Integer.parseInt(partes.get(j+1).toString());
                                    int res = n1-n2;
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }else{
                                    signos.remove(j);
                                    double n1 = Double.parseDouble(partes.get(j).toString());
                                    double n2 = Double.parseDouble(partes.get(j+1).toString());
                                    double res = n1-n2;
                                    partes.remove(j+1);partes.remove(j);
                                    partes.add(j, res);
                                    j = signos.size();
                                }
                            }
                        }
                        if (c == 0) {
                            sumRes = false;
                        }
                    }


                    resul = String.valueOf(partes.get(0));
                    detalleVariables.add(0,datos[0]+"¿"+datos[1]+"¿"+resul);
                    detalleVariables.remove(i+1);
                }
            }
        //System.out.println("RESU: "+resul);

        }
        System.out.println("NUEVA TABLA DE VARIABLES");
        for (int i = 0; i < detalleVariables.size(); i++) {
            System.out.println(detalleVariables.get(i));
        }
    }
    public int evaluarInstrucciones(String ins, int id){
        //simplificarValores();
        int indx = 0;
                    //   PARA
        if (ins.length() > 4 && "PARA".equals(ins.substring(0, 4))) {
            String[] partes = ins.split(" ");
            if (revisarVariable(partes[6])) {
                for (int j = 0; j < detalleVariables.size(); j++) {
                    String[] dat = detalleVariables.get(j).toString().split("¿");
                    if (dat[1].equals(partes[6])) {
                        partes[6] = dat[2];
                        break;
                    }
                }
            }
            int repeticiones = 0;
            switch(partes[5]){
                case "MAYOR":
                    if (Integer.parseInt(partes[3]) < Integer.parseInt(partes[6])) {
                        txaConsola.setText("El ciclo PARA no se ejecutará jamás.");
                    }else if (Integer.parseInt(partes[3]) > Integer.parseInt(partes[6]) && "+".equals(partes[10])) {
                        txaConsola.setText("El ciclo PARA se ejecutará infinitamente.");
                    }else{
                        repeticiones = (Integer.parseInt(partes[3]) - Integer.parseInt(partes[6])) / Integer.parseInt(partes[11].substring(0, partes[11].length()-2));
                        System.out.println("repeticiones = " + repeticiones);
                    }
                    break;
                case "MENOR":
                    System.out.println("partes[3] = " + partes[3]);
                    System.out.println("partes[6] = " + partes[6]);
                    if (Integer.parseInt(partes[3]) > Integer.parseInt(partes[6])) {
                        txaConsola.setText("El ciclo PARA no se ejecutará jamás.");
                    }else if (Integer.parseInt(partes[3]) < Integer.parseInt(partes[6]) && "-".equals(partes[10])) {
                        txaConsola.setText("El ciclo PARA se ejecutará infinitamente.");
                    }else{
                        repeticiones = (Integer.parseInt(partes[6]) - Integer.parseInt(partes[3])) / Integer.parseInt(partes[11].substring(0, partes[11].length()-2));
                        System.out.println("repeticiones = " + repeticiones);
                    }
                    break;
            }

            List insTemp = new ArrayList();
            for (int j = id+1; j < instrucciones.size(); j++) {
                if ("}PARA".equals(instrucciones.get(j))) {
                    break;
                }else{
                    insTemp.add(instrucciones.get(j));
                }
            }
            
            System.out.println("insTemp = " + insTemp);

            System.out.println("--- EN PARA ---");
            for (int j = 0; j < repeticiones; j++) {
                System.out.println("------------");
                for (int i = 0; i < detalleVariables.size(); i++) {
                    System.out.println(detalleVariables.get(i));
                }
                System.out.println("------------");
                for (int k = 0; k < insTemp.size(); k++) {
                    boolean encon = false;
                    int i;
                    for (i = 0; i < detalleVariables.size(); i++) {
                        String[] datos = detalleVariables.get(i).toString().split("¿");
                        if (partes[1].equals(datos[1])) {
                            encon = true;
                            break;
                        }
                    }
                    if (encon) {
                        //System.out.println("Se removerá: "+detalleVariables.get(i));
                        detalleVariables.remove(i);
                        detalleVariables.add(partes[0].substring(5)+"¿"+partes[1]+"¿"+j);
                    }else{
                        detalleVariables.add(partes[0].substring(5)+"¿"+partes[1]+"¿"+j);
                    }
                    int a = evaluarInstrucciones(insTemp.get(k).toString(), id);
                    if (a != 0) {
                        j = a;
                    }
                }    
            }
            System.out.println("--- EN PARA ---");
            
            for (int i = 0; i < detalleVariables.size(); i++) {
                String[] datos = detalleVariables.get(i).toString().split("¿");
                if (partes[1].equals(datos[1])) {
                    detalleVariables.remove(i);
                    break;
                }
            }
            indx = id + insTemp.size();

                        //   TICKET:
        }else if (ins.length() > 6 && "Ticket".equals(ins.substring(0, 6))) { // Ticket: "Hola Mundo!"
            String[] parte = ins.split(" ");
            if (revisarVariable(parte[1])) {
                for (int i = 0; i < detalleVariables.size(); i++) {
                    String[] dts = detalleVariables.get(i).toString().split("¿");
                    if (dts[1].equals(parte[1])) {
                        txaConsola.setText(txaConsola.getText()+dts[2]+"\n");
                        System.out.println("Se imprime: "+dts[2]);
                        break;
                    }
                }
            }else{
                String aTicket = parte[1].substring(1);
                aTicket = aTicket.substring(0, aTicket.length()-1);
                txaConsola.setText(txaConsola.getText()+aTicket+"\n");
                System.out.println("Se imprime: "+aTicket);
            }

                    //      SKITTLE:
        }else if (ins.length() > 7 && "Skittle".equals(ins.substring(0, 7))) { // Skittle: skVar
            String[] parte = ins.split(" ");
            
            String msj = JOptionPane.showInputDialog("Ingrese el valor para: "+parte[1]+"\nSi es tipo Skwinkle o chicle, no use comillas.");
            txaConsola.setText(txaConsola.getText()+msj+"\n");
            
            for (int i = 0; i < detalleVariables.size(); i++) {
                String[] dat = detalleVariables.get(i).toString().split("¿");
                if (parte[1].equals(dat[1])) {
                    switch(dat[0]){
                        case "rellerindo":
                            for (int j = 0; j < msj.length(); j++) {
                                if (!numeros.contains(String.valueOf(msj.charAt(j)))) {
                                    txaConsola.setText(txaConsola.getText() + "\nERROR. Se esperaba un valor tipo rellerindo.");
                                    return 99999;
                                }
                            }
                            detalleVariables.remove(i);
                            detalleVariables.add(dat[0]+"¿"+dat[1]+"¿"+msj);
                            //detalleVariables.remove(j);
                            //detalleVariables.add(aEliminar[0]+"¿"+ins[0]+"¿"+ins[2]);
                            break;
                        case "mazapan":
                            boolean banPunto = false;
                            for (int j = 0; j < msj.length(); j++) {
                                if (!numeros.contains(String.valueOf(msj.charAt(j)))) {
                                    if (".".equals(String.valueOf(msj.charAt(j))) && !banPunto) {
                                        banPunto = true;
                                    }else{
                                        txaConsola.setText(txaConsola.getText() + "\nERROR. Se esperaba un valor tipo mazapan.");
                                        return 99999;
                                    }
                                }
                                detalleVariables.remove(i);
                                detalleVariables.add(dat[0]+"¿"+dat[1]+"¿"+msj);
                            }
                            break;
                        case "Skwinkle":
                            // Se aceptará siempre lo que se ingrese
                            detalleVariables.remove(i);
                            detalleVariables.add(dat[0]+"¿"+dat[1]+"¿\""+msj+"\"");
                            break;
                        case "duvalin":
                            if ("VERDADERO".equals(msj) || "FALSO".equals(msj)) {
                                detalleVariables.remove(i);
                                detalleVariables.add(dat[0]+"¿"+dat[1]+"¿\""+msj+"\"");
                            }else{
                                txaConsola.setText(txaConsola.getText() + "\nERROR. Se esperaba un valor tipo duvalin.");
                                return 99999;
                            }
                            break;
                        case "chicle":
                            if (msj.length() != 1) {
                                txaConsola.setText(txaConsola.getText() + "\nERROR. Se esperaba un valor tipo chicle.");
                                return 99999;
                            }else{
                                detalleVariables.remove(i);
                                detalleVariables.add(dat[0]+"¿"+dat[1]+"¿\""+msj+"\"");
                            }
                            break;
                    }
                    break;
                }
            }
            System.out.println("NUEVO DETALLE VARIABLES");
            for (int i = 0; i < detalleVariables.size(); i++) {
                System.out.println(detalleVariables.get(i));
            }
        }else if (ins.contains(".obtener(")) {
            String parte = ins.split(" ")[3];
            parte = parte.replace(".", "¿");
            String var = parte.split("¿")[0];
            String num = parte.split("¿")[1].substring(8, parte.split("¿")[1].length()-1);
            boolean band = false;
            
            
            for (int i = 0; i < num.length(); i++) {
                if (!numeros.contains(String.valueOf(num.charAt(i)))) {
                    band = true;
                }
            }
            
            int numCar = 0;
            if (band) { //es variable
                if (revisarVariable(num)) {
                    for (int i = 0; i < detalleVariables.size(); i++) {
                        String[] partes = detalleVariables.get(i).toString().split("¿");
                        if (partes[1].equals(num)) {
                            if (partes.length == 3) {
                                if (partes[0].equals("rellerindo")) {
                                    numCar = Integer.parseInt(partes[2]);
                                    break;
                                }else{
                                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba una variable de tipo rellerindo.");
                                    return 999999;
                                }
                            }else{
                                txaConsola.setText(txaConsola.getText()+"ERROR. La variable "+partes[1]+" no se ha inicializado.");
                                return 999999;
                            }
                        }
                    }
                }else{
                    txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor rellerindo en el método obtener.");
                    return 999999;
                }
            }else{ // es numero
                numCar = Integer.parseInt(num);
            }
            
            String cade = "";
            for (int i = 0; i < detalleVariables.size(); i++) {
                String[] partes = detalleVariables.get(i).toString().split("¿");
                    if (partes[1].equals(var)) {
                        cade = partes[2];
                        break;
                    }
            }
            if (String.valueOf(cade.charAt(0)).equals("\"")) { // Es cadena
                cade = cade.substring(1);
                cade = cade.substring(0,cade.length()-1);
            }
            
            if (numCar < cade.length() && (-1 < numCar)) {
                String letra = String.valueOf(cade.charAt(numCar));
                boolean encontrado = false;
                int i = 0;
                for ( i = 0; i < detalleVariables.size(); i++) {
                    String[] partes = detalleVariables.get(i).toString().split("¿");
                    
                    if (partes[1].equals(ins.split(" ")[1])) {
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    detalleVariables.remove(i);
                    detalleVariables.add(ins.split(" ")[0]+"¿"+ins.split(" ")[1]+"¿\""+letra+"\"");
                }else{
                    detalleVariables.add(ins.split(" ")[0]+"¿"+ins.split(" ")[1]+"¿\""+letra+"\"");
                }
            }else{
                txaConsola.setText(txaConsola.getText()+"\nError de posición "+numCar+" en variable de longitud "+cade.length());
                return 99999;
            }
            
        }else if (ins.contains(".longitud")) {
            String parte = ins.split(" ")[3];
            parte = parte.replace(".", "¿");
            String var = parte.split("¿")[0];
            String valor = "";
            
            for (int i = 0; i < detalleVariables.size(); i++) {
                String[] partes = detalleVariables.get(i).toString().split("¿");
                if (partes[1].equals(var)) {
                    valor = partes[2];
                    break;
                }
            }
            
            if (String.valueOf(valor.charAt(0)).equals("\"")) {
                valor = valor.substring(1, valor.length()-1);
            }
            
            for (int i = 0; i < detalleVariables.size(); i++) {
                String[] partes = detalleVariables.get(i).toString().split("¿");
                if (partes[1].equals(ins.split(" ")[1])) {
                    detalleVariables.remove(i);
                    int tam = valor.length();
                    detalleVariables.add(ins.split(" ")[0]+"¿"+ins.split(" ")[1]+"¿"+tam);
                    break;
                }
            }
            
            
        }else if (ins.contains(".esMayuscula")) { //duvalin duVar = chVar.esMayuscula
            String parte = ins.split(" ")[3];
            parte = parte.replace(".", "¿");
            String var = parte.split("¿")[0];
            String valor = "";
            
            for (int i = 0; i < detalleVariables.size(); i++) {
                String[] partes = detalleVariables.get(i).toString().split("¿");
                if (partes[1].equals(var)) {
                    if (partes[0].equals("chicle")) {
                        if (partes.length == 3) {
                            valor = partes[2];
                            valor = valor.substring(1, 3);
                            break;
                        }else{
                            txaConsola.setText(txaConsola.getText()+"Error. La variable "+var+" no ha sido inicializada.");
                            return 99999;
                        }
                    }else{
                        txaConsola.setText(txaConsola.getText()+"Error. Se esperaba un valor chicle para el método \"esMayuscula\"");
                        return 99999;
                    }
                }
            }
            for (int i = 0; i < detalleVariables.size(); i++) {
                String[] partes = detalleVariables.get(i).toString().split("¿");
                if (partes[1].equals(ins.split(" ")[1])) {
                    detalleVariables.remove(i);
                    if (Character.isUpperCase(valor.charAt(0))) {
                        detalleVariables.add("duvalin¿"+ins.split(" ")[1]+"¿VERDADERO");
                    }else{
                        detalleVariables.add("duvalin¿"+ins.split(" ")[1]+"¿FALSO");
                    }
                    break;
                }
            }
                // ASIGNACIÓN
        }else if (revisarVariable(ins.split(" ")[0]) && "=".equals(ins.split(" ")[1])) {
            
            //String valor = ins.split(" ")[2];
            
            boolean encontrado = false;
            String tipo = "";
            for (int j = 0; j < detalleVariables.size(); j++) {
                String[] datos = detalleVariables.get(j).toString().split("¿");
                if (ins.split(" ")[0].equals(datos[1])) {
                    encontrado = true;
                    tipo = datos[0];
                }
            }
            if (!encontrado) {
                System.out.println("ERROR1. No se encontró la variable: "+ins.split(" ")[0]);
                txaConsola.setText(txaConsola.getText()+"ERROR. No se encontró la variable: "+ins.split(" ")[0] +"\n");
                return 9999;
            }else{
                switch(tipo){
                    case "duvalin":
                        if (ins.split(" ")[2].equals("FALSO") || ins.split(" ")[2].equals("VERDADERO")) {
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] datos = detalleVariables.get(j).toString().split("¿");
                                if (ins.split(" ")[0].equals(datos[1])) {

                                    for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                        String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                        if (aEliminar[0].equals("duvalin") && aEliminar[1].equals(ins.split(" ")[0])) {
                                            detalleVariables.remove(k);
                                        }
                                    }
                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    detalleVariables.add(datos[0]+"¿"+datos[1]+"¿"+ins.split(" ")[2]);
                                }
                            }
                        }else if(revisarVariable(ins.split(" ")[2])){
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] datos = detalleVariables.get(j).toString().split("¿");
                                if (ins.split(" ")[2].equals(datos[1])) {
                                    if (datos[0].equals("duvalin") && datos.length > 2 ) {
                                        for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                            String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                            if (aEliminar[0].equals("duvalin") && aEliminar[1].equals(ins.split(" ")[0])) {
                                                detalleVariables.remove(k);
                                            }
                                        }
                                        detalleVariables.add(datos[0]+"¿"+ins.split(" ")[0]+"¿"+datos[2]);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN DUVALIN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }else{
                                        System.out.println("ERROR1. Se esperaba un valor VERDADERO o FALSO.");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor VERDADERO o FALSO. \n");
                                        return 9999;
                                    }
                                }
                            }
                        }else{
                            System.out.println("ERROR1. Se esperaba un valor VERDADERO o FALSO.");
                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor VERDADERO o FALSO. \n");
                            return 9999;
                        }
                        break;
                    case "chicle":
                        // chVar = "9"
                        // chVar = chVar2
                        if(revisarVariable(ins.split(" ")[2])){
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] datos = detalleVariables.get(j).toString().split("¿");
                                if (ins.split(" ")[2].equals(datos[1])) {
                                    if (datos[0].equals("chicle") && datos.length > 2 ) {
                                        for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                            String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                            if (aEliminar[0].equals("chicle") && aEliminar[1].equals(ins.split(" ")[0])) {
                                                detalleVariables.remove(k);
                                            }
                                        }
                                        detalleVariables.add(datos[0]+"¿"+ins.split(" ")[0]+"¿"+datos[2]);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }else{
                                        System.out.println("ERROR1. Se esperaba un valor de tipo chicle.");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo chicle. \n");
                                        return 9999;
                                    }
                                }
                            }
                        }else if(ins.split(" ")[2].length() == 3){

                            if ("\"".equals(String.valueOf(ins.split(" ")[2].charAt(0))) && "\"".equals(String.valueOf(ins.split(" ")[2].charAt(2)))) {
                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] aEliminar = detalleVariables.get(j).toString().split("¿");
                                    if (aEliminar[0].equals("chicle") && aEliminar[1].equals(ins.split(" ")[0])) {
                                        detalleVariables.remove(j);
                                        detalleVariables.add(aEliminar[0]+"¿"+ins.split(" ")[0]+"¿"+ins.split(" ")[2]);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN CHICLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }
                                }
                            }else{
                                System.out.println("ERROR2. Se esperaba un valor de tipo chicle.");
                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo chicle. \n");
                                return 9999;
                            }
                        }else{
                            System.out.println("ERROR3. Se esperaba un valor de tipo chicle.");
                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo chicle. \n");
                            return 9999;
                        }

                        break;
                    case "rellerindo":
                        // reVar = 123
                        // reVar = reVar2

                        if (revisarVariable(ins.split(" ")[2])) {
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] datos = detalleVariables.get(j).toString().split("¿");
                                if (ins.split(" ")[2].equals(datos[1])) {
                                    if (datos[0].equals("rellerindo") && datos.length > 2 ) {
                                        for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                            String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                            if (aEliminar[0].equals("rellerindo") && aEliminar[1].equals(ins.split(" ")[0])) {
                                                detalleVariables.remove(k);
                                            }
                                        }
                                        detalleVariables.add(datos[0]+"¿"+ins.split(" ")[0]+"¿"+datos[2]);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN RELLERINDO CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }else{
                                        System.out.println("ERROR1. Se esperaba un valor de tipo rellerindo.");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo rellerindo. \n");
                                        return 9999;
                                    }
                                }
                            }
                        }else{  // reVar = 123
                                // reVar = reVar2
                            String res = "";
                            boolean ban = false;
                            for (int j = 0; j < ins.split(" ")[2].length(); j++) { //235
                                String num = String.valueOf(ins.split(" ")[2].charAt(j)); // 2
                                if (log.contains(num)) {
                                    if (j==ins.split(" ")[2].length()-1) {
                                        System.out.println("ERROR2. Se esperaba un valor tipo rellerindo");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                        return 9999;
                                    }
                                }else if (!numeros.contains(num)) {
                                    //   + - * / 
                                    ban = true;
                                    break;
                                }

                            }
                            if (ban) {
                                String op = ins.split(" ")[2];
                                List signos = new ArrayList();


                                for (int k = 0; k < op.length(); k++) {
                                    if (log.contains(String.valueOf(op.charAt(k)))) {
                                        signos.add(String.valueOf(op.charAt(k)));
                                    }
                                }

                                op = op.replace("+", "¿");   op = op.replace("-", "¿"); 
                                op = op.replace("*", "¿");     op = op.replace("/", "¿"); 
                                String[] sepa = op.split("¿");   // hola+1   ---  hola¿1


                                for (int k = 0; k < sepa.length; k++) { // hola, 1
                                    boolean band = false;
                                    for (int l = 0; l < sepa[k].length(); l++) {
                                        String aEvaluar = String.valueOf(sepa[k].charAt(l));
                                        if (!numeros.contains(aEvaluar)) {
                                            band = true;
                                            break;
                                        }
                                    }
                                    if (band) { // variable
                                        if (!revisarVariable(sepa[k])) {
                                            System.out.println("ERROR42. Se esperaba un valor tipo rellerindo");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                            return 9999;
                                        }else{
                                            for (int l = 0; l < detalleVariables.size(); l++) {
                                                String[] deta = detalleVariables.get(l).toString().split("¿");
                                                if (deta[1].equals(sepa[k])) {
                                                    if (!deta[0].equals("rellerindo")) {
                                                        System.out.println("ERROR45. Se esperaba un valor tipo rellerindo");
                                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo rellerindo \n");
                                                        return 9999;
                                                    }else{
                                                        res += deta[2];
                                                        System.out.println("res = " + res);
                                                        break;
                                                    }
                                                }

                                            }
                                        }
                                        if (signos.size() > 0) {
                                            res += signos.get(0);
                                            signos.remove(0);
                                        }                                            
                                    }else{
                                        res += sepa[k];
                                        if (signos.size() > 0) {
                                            res += signos.get(0);
                                            signos.remove(0);
                                        }  
                                    }

                                }


                            }

                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] aEliminar = detalleVariables.get(j).toString().split("¿");
                                if (aEliminar[0].equals("rellerindo") && aEliminar[1].equals(ins.split(" ")[0])) {
                                    detalleVariables.remove(j);
                                    detalleVariables.add(aEliminar[0]+"¿"+ins.split(" ")[0]+"¿"+res);
                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN RELLERINDO CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                }
                            }
                            simplificarValores();

                        }

                        break;
                    case "mazapan":
                        // maVar = 123.123
                        // maVar = maVar2

                        if (revisarVariable(ins.split(" ")[2])) {
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] datos = detalleVariables.get(j).toString().split("¿");
                                if (ins.split(" ")[2].equals(datos[1])) {
                                    if (datos[0].equals("mazapan") && datos.length > 2 ) {
                                        for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                            String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                            if (aEliminar[0].equals("mazapan") && aEliminar[1].equals(ins.split(" ")[0])) {
                                                detalleVariables.remove(k);
                                            }
                                        }
                                        detalleVariables.add(datos[0]+"¿"+ins.split(" ")[0]+"¿"+datos[2]);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN MAZAPAN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }else{
                                        System.out.println("ERROR1. Se esperaba un valor de tipo mazapan.");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo mazapan. \n");
                                        return 9999;
                                    }
                                }
                            }
                        }else{

                            String valor = ins.split(" ")[2]; // 14.324+243.1234-43.234 --- [14.324,243.1234,43.234]
                                    // "+-*/%^";
                            valor = valor.replace("+"," ");valor = valor.replace("-"," ");valor = valor.replace("+"," ");
                            valor = valor.replace("/"," ");valor = valor.replace("%"," ");valor = valor.replace("^"," ");

                            String[] separados = valor.split(" "); // [14.324,243.1234,43.234]

                            for (int j = 0; j < separados.length; j++) {
                                boolean bandePunto = true;
                                String actual = separados[j]; // 14.324
                                String[] caracter = actual.split("");
                                for (int k = 0; k < caracter.length; k++) {
                                    String carAct = caracter[k];
                                    if (".".equals(carAct)) {
                                        if (bandePunto) {
                                            bandePunto = false;
                                        }else{
                                            System.out.println("ERROR3. Se esperaba un valor tipo mazapan");
                                            txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                            return 9999;
                                        }
                                    }else if (!numeros.contains(carAct)) {
                                        System.out.println("ERROR4. Se esperaba un valor tipo mazapan");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor tipo mazapan \n");
                                        return 9999;
                                    }
                                }
                            }

                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] aEliminar = detalleVariables.get(j).toString().split("¿");
                                if (aEliminar[0].equals("mazapan") && aEliminar[1].equals(ins.split(" ")[0])) {
                                    detalleVariables.remove(j);
                                    detalleVariables.add(aEliminar[0]+"¿"+ins.split(" ")[0]+"¿"+ins.split(" ")[2]);
                                    System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN MAZAPAN CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                }
                            }

                        }
                        break;
                    case "Skwinkle":
                        // skVar = "gsdj"
                        // skVar = skVar2

                        if (revisarVariable(ins.split(" ")[2])) {
                            for (int j = 0; j < detalleVariables.size(); j++) {
                                String[] datos = detalleVariables.get(j).toString().split("¿");
                                if (ins.split(" ")[2].equals(datos[1])) {
                                    if (datos[0].equals("Skwinkle") && datos.length > 2 ) {
                                        for (int k = 0; k < detalleVariables.size(); k++) {//Eliminar el anterior de la tabla
                                            String[] aEliminar = detalleVariables.get(k).toString().split("¿");
                                            if (aEliminar[0].equals("Skwinkle") && aEliminar[1].equals(ins.split(" ")[0])) {
                                                detalleVariables.remove(k);
                                            }
                                        }
                                        detalleVariables.add(datos[0]+"¿"+ins.split(" ")[0]+"¿"+datos[2]);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN SKWINKLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }else{
                                        System.out.println("ERROR1. Se esperaba un valor de tipo Skwinkle.");
                                        txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo Skwinkle. \n");
                                        return 9999;
                                    }
                                }
                            }
                        }else{

                            String val = ins;
                            val = val.substring(ins.split(" ")[0].length()+3);
                            System.out.println("val = " + val);

                            if("\"".equals(String.valueOf(val.charAt(0))) && "\"".equals(String.valueOf(val.charAt(val.length()-1)))){

                                for (int j = 0; j < detalleVariables.size(); j++) {
                                    String[] aEliminar = detalleVariables.get(j).toString().split("¿");
                                    if (aEliminar[0].equals("Skwinkle") && aEliminar[1].equals(ins.split(" ")[0])) {
                                        detalleVariables.remove(j);
                                        detalleVariables.add(aEliminar[0]+"¿"+ins.split(" ")[0]+"¿"+val);
                                        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;; ASIGNACIÓN SKWINKLE CORRECTA ;;;;;;;;;;;;;;;;;;;;;;;;;;;");
                                    }
                                }

                            }else{
                                System.out.println("ERROR2. Se esperaba un valor de tipo Skwinkle.");
                                txaConsola.setText(txaConsola.getText()+"ERROR. Se esperaba un valor de tipo Skwinkle. \n");
                                return 9999;
                            }
                        }
                        break;
                    case "hersheys":
                        break;
                }
            }
            
        }else if ("SI(".equals(ins.split(" ")[0])) {
            String var = ins.split(" ")[1];
            List insTemp = new ArrayList();
            boolean si = false, encontrado = false;
            for (int i = 0; i < detalleVariables.size(); i++) {
                String[] dats = detalleVariables.get(i).toString().split("¿");
                if (dats[1].equals(var) && dats.length == 3) {
                    for (int j = id+1; j < instrucciones.size(); j++) {
                        insTemp.add(instrucciones.get(j));
                        if ("}SI".equals(instrucciones.get(j))) {
                            if (dats[2].equals("VERDADERO")) {
                                si = true;
                            }
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        txaConsola.setText(txaConsola.getText()+"Error en variable del if.");
                        return 99999;
                    }
                    break;
                }
            }
            if (si) {
                System.out.println("ENTRA POR VERDADERO");
                for (int i = 0; i < insTemp.size(); i++) {
                    evaluarInstrucciones(insTemp.get(i).toString(), id);
                }
            }
            indx = id + insTemp.size();
        }
        return indx;
    }
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int confirmar = JOptionPane.showConfirmDialog(rootPane, "¿Estás seguro?", "Eliminar", JOptionPane.WARNING_MESSAGE, 2);
        if (confirmar == JOptionPane.OK_OPTION) {
            txaCode.setText("INICIO\n"
                    + "                        + \n"
                    + "                        + \"	(: Escriba su Código aquí :)\n"
                    + "                        + \n"
                    + "                        + \n"
                    + "FIN");
            File destino = new File(ruta);
            destino.delete();
            btnCerrar.setEnabled(false);
            btnCompilar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
        }


    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseEntered
        // TODO add your handling code here:
        btnNuevo.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnNuevoMouseEntered

    private void btnNuevoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseExited
        // TODO add your handling code here:
        btnNuevo.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnNuevoMouseExited

    private void btnAbrirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseEntered
        // TODO add your handling code here:
        btnAbrir.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnAbrirMouseEntered

    private void btnAbrirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseExited
        // TODO add your handling code here:
        btnAbrir.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAbrirMouseExited

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        // TODO add your handling code here:
        btnGuardar.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        // TODO add your handling code here:
        btnGuardar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnCompilarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompilarMouseEntered
        // TODO add your handling code here:
        btnCompilar.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnCompilarMouseEntered

    private void btnCompilarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompilarMouseExited
        // TODO add your handling code here:
        btnCompilar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnCompilarMouseExited

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        // TODO add your handling code here:
        btnEliminar.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        // TODO add your handling code here:
        btnEliminar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        // TODO add your handling code here:

        if (!ruta.equals("")) {
            int iOpcion = JOptionPane.showOptionDialog(null, "¿Desea guardar los cambios?",
                    "Selecciona una opcion...",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new Object[]{"Guardar", "No Guardar", "Cancelar"}, "Guardar");

            System.out.println(iOpcion);

            switch (iOpcion) {
                case 0:
                    //PRIMERO GUARDA
                    System.out.println("Guardar");
                    try (FileWriter fw = new FileWriter(ruta)) {
                        fw.write(this.txaCode.getText());

                    } catch (IOException el) {
                        el.printStackTrace();
                    }

                    if (rootPaneCheckingEnabled) { //Si le da clic en si

                    }
                    //LUEGO CIERRA EL ARCHIVO
                    ruta = "";
                    txaCode.setText("INICIO\n"
                            + "\n"
                            + "	(: Escriba su Código aquí :)\n"
                            + "\n"
                            + "\n"
                            + "FIN");
                    txaConsola.setText(null);
                    txaConsola.setEditable(false);
                    txaCode.setEnabled(false); //DESHABILITA LOS BOTONES Y EL PANEL DE TEXTO POR DEFECTO
                    btnCerrar.setEnabled(false);
                    btnCompilar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnGuardar.setEnabled(false);

                    break;
                case 1:
                    System.out.println("No Guardar");
                    //SOLO CIERRA EL ARCHIVO
                    ruta = "";
                    txaCode.setText("INICIO\n"
                            + "\n"
                            + "	(: Escriba su Código aquí :)\n"
                            + "\n"
                            + "\n"
                            + "FIN");
                    txaConsola.setText(null);
                    txaConsola.setEditable(false);
                    txaCode.setEnabled(false); //DESHABILITA LOS BOTONES Y EL PANEL DE TEXTO POR DEFECTO
                    btnCerrar.setEnabled(false);
                    btnCompilar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnGuardar.setEnabled(false);
                    break;
                case 2:
                    System.out.println("Cancelar");
                //SOLO CIERRA LA VENTANA DE CONFIRMACION
            }

        }
        JFileChooser fc = new JFileChooser();

        int seleccion = fc.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            //this.txaCode.setText(fichero.getAbsolutePath());

            try (FileReader fr = new FileReader(fichero)) {
                String cadena = "";
                int valor = fr.read();
                while (valor != -1) {
                    cadena = cadena + (char) valor;
                    valor = fr.read();
                }
                if (fichero.getName().substring(fichero.getName().length() - 6, fichero.getName().length()).equals(".candy")) {
                    this.txaCode.setText(cadena);
                    String title = fichero.getName().substring(0, fichero.getName().length() - 6);
                    this.setTitle(title + " - Net Candy Compiler 1.0");
                    btnCompilar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    btnGuardar.setEnabled(true);
                    btnCerrar.setEnabled(true);
                    txaCode.setEnabled(true);
                    ruta = fichero.getPath();
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo no compatible.");

                }

            } catch (IOException el) {
                el.printStackTrace();
            }
        }


    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:

        try (FileWriter fw = new FileWriter(ruta)) {
            fw.write(this.txaCode.getText());

        } catch (IOException el) {
            el.printStackTrace();
        }

        if (rootPaneCheckingEnabled) { //Si le da clic en si

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:

        int iOpcion = JOptionPane.showOptionDialog(null, "¿Desea guardar los cambios?",
                "Selecciona una opcion...",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new Object[]{"Guardar", "No Guardar", "Cancelar"}, "Guardar");

        System.out.println(iOpcion);

        switch (iOpcion) {
            case 0:
                //PRIMERO GUARDA
                System.out.println("Guardar");
                try (FileWriter fw = new FileWriter(ruta)) {
                    fw.write(this.txaCode.getText());

                } catch (IOException el) {
                    el.printStackTrace();
                }

                if (rootPaneCheckingEnabled) { //Si le da clic en si

                }
                //LUEGO CIERRA EL ARCHIVO
                ruta = "";
                txaCode.setText("INICIO\n"
                        + "\n"
                        + "	(: Escriba su Código aquí :)\n"
                        + "\n"
                        + "\n"
                        + "FIN");
                txaConsola.setText(null);
                txaCode.setEnabled(false); //DESHABILITA LOS BOTONES Y EL PANEL DE TEXTO POR DEFECTO
                btnCerrar.setEnabled(false);
                btnCompilar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(false);

                break;
            case 1:
                System.out.println("No Guardar");
                //SOLO CIERRA EL ARCHIVO
                ruta = "";
                txaCode.setText("INICIO\n"
                        + "\n"
                        + "	(: Escriba su Código aquí :)\n"
                        + "\n"
                        + "\n"
                        + "FIN");
                txaConsola.setText(null);
                txaCode.setEnabled(false); //DESHABILITA LOS BOTONES Y EL PANEL DE TEXTO POR DEFECTO
                btnCerrar.setEnabled(false);
                btnCompilar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(false);
                break;
            case 2:
                System.out.println("Cancelar");
            //SOLO CIERRA LA VENTANA DE CONFIRMACION
        }


    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        // TODO add your handling code here:
        btnCerrar.setBackground(Color.GRAY);
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        // TODO add your handling code here:
        btnCerrar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnCerrarMouseExited

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void txaCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txaCodeKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txaCodeKeyPressed

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
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logo;
    private javax.swing.JTextPane txaCode;
    private javax.swing.JTextPane txaConsola;
    // End of variables declaration//GEN-END:variables

    private final class CustomDocumentFilter extends DocumentFilter
{
        private final StyledDocument styledDocument = txaCode.getStyledDocument();

        private final StyleContext styleContext = StyleContext.getDefaultStyleContext();
        
        Color tipoDato = new Color(25,185,89);
        Color reservadas = new Color(250,134,81);
        Color colorCiclos = new Color(104,42,142);
        Color colorOutin = new Color(221,210,67);
        //Color tipoDato = new Color(25,185,89);
        //Color tipoDato = new Color(25,185,89);
        
        private final AttributeSet greenAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, tipoDato);
        private final AttributeSet orangeAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, reservadas);
        private final AttributeSet blackAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        private final AttributeSet purpleAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, colorCiclos);
        private final AttributeSet yellowAttributeSet = styleContext.addAttribute(styleContext.getEmptySet(), StyleConstants.Foreground, colorOutin);

    // Use a regular expression to find the words you are looking for
    Pattern pattern = buildPattern();

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attributeSet) throws BadLocationException {
        super.insertString(fb, offset, text, attributeSet);

        handleTextChanged();
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);

        handleTextChanged();
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException {
        super.replace(fb, offset, length, text, attributeSet);

        handleTextChanged();
    }

    /**
     * Runs your updates later, not during the event notification.
     */
    private void handleTextChanged()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updateTextStyles();
            }
        });
    }

    /**
     * Build the regular expression that looks for the whole Word of each Word that you wish to find.  The "\\b" is the beginning or end of a Word boundary.  The "|" is a regex "or" operator.
     * @return
     */
    
    String[] tipoD = {"rellerindo","duvalin","mazapan","bocadin","chicle","cachetada","Skwinkle"};
    String[] reserv = {"bolsita","piñata","abierto","cerrado","wini"};
    String[] ciclos = {"PARA","MIENTRAS","HAZ","SI","SINO","JUICIO", "CASO","DEFECTO"};
    String[] outIn = {"Skittle","Ticket"};
    
    private Pattern buildPattern()
    {
        StringBuilder sb = new StringBuilder();
        String[] todas = {"Skittle","Ticket","PARA","MIENTRAS","HAZ","SI","SINO","JUICIO", "CASO","DEFECTO","rellerindo","duvalin","mazapan","bocadin","chicle","cachetada","Skwinkle","bolsita","piñata","abierto","cerrado","wini"};
        for (String token : todas) {
            sb.append("\\b"); // Start of Word boundary
            sb.append(token);
            sb.append("\\b|"); // End of Word boundary and an or for the next Word
        }
        
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // Remove the trailing "|"
        }

        Pattern p = Pattern.compile(sb.toString());

        return p;
    }


    private void updateTextStyles()
    {
        // Clear existing styles
        styledDocument.setCharacterAttributes(0, txaCode.getText().length(), blackAttributeSet, true);

        // Look for tokens and highlight them
        Matcher matcher = pattern.matcher(txaCode.getText());
        while (matcher.find()) {
            // Change the color of recognized tokens
            String pal = txaCode.getText().substring(matcher.start(), matcher.end());
            int b = 0;
            for (int i = 0; i < tipoD.length; i++) {
                if (pal.equals(tipoD[i])) {
                    b = 1;
                    break;
                }
            }
            for (int i = 0; i < reserv.length; i++) {
                if (pal.equals(reserv[i])) {
                    b = 2;
                    break;
                }
            }
            for (int i = 0; i < ciclos.length; i++) {
                if (pal.equals(ciclos[i])) {
                    b = 3;
                    break;
                }
            }
            for (int i = 0; i < outIn.length; i++) {
                if (pal.equals(outIn[i])) {
                    b = 4;
                    break;
                }
            }
            
            switch(b){
                case 0:
                    break;
                case 1:
                    styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), greenAttributeSet, false);
                    break;
                case 2:
                    styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), orangeAttributeSet, false);
                    break;
                case 3:
                    styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), purpleAttributeSet, false);
                    break;
                case 4:
                    styledDocument.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), yellowAttributeSet, false);
                    break;
            }
            
        }
    }
}
    
    
    
}
