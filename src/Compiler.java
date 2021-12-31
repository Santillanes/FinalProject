
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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

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
    int cont = 0, linea = 1;
    String[] arre;
    List palabras = new ArrayList();
    List variables = new ArrayList();
    List clase = new ArrayList();
    List llaves = new ArrayList();
    int conta = 0;
    boolean bander = false, banNums = false, bandeHAZ = false, bandeSINO = false, bandeClase = false;

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
                    }else{
                        System.out.println("Variable: " + aux);
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
                                                System.out.println("ERROR DE VALOR DE VARIABLE EN LÍNEA: "+(linea+1));
                                                //System.out.println("ENTRA");
                                            } else {
                                                cadeAux += arre[i];
                                            }
                                        }
                                    }else{
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
                    } else {
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
                        }else{
                            palabras.add(txt);
                            System.out.println("Texto: " + txt);
                        }
                        
                        if (bandeComillas) {
                            palabras.add("\"");
                            System.out.println("Símbolo reservado: \"");
                        }else{
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
                System.out.println(pal + " " + palabras.get(i+1) + " " + palabras.get(i+2));
            }
            
            //CLASES
            //      acceso Var { instrucciones }
            //              1  2
            else if (i < palabras.size() - 2 && "bolsita".equals(pal)) {
                llaves.add(0,"CLASE");
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
                            llaves.add(0,"ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else {
                            System.out.println("**** DECLARACIÓN DE CLASE ****");
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString()); //Hasta el {
                            bandeClase = true;
                        }
                    } else {
                        System.out.println("ERROR EN CLASE");
                        System.out.println("NO SE ENCONTRÓ LLAVE DE INICIO");
                        llaves.add(0,"ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                } else {
                    System.out.println("ERROR EN CLASE");
                    System.out.println("NO SE ENCONTRÓ NOMBRE DE CLASE");
                    llaves.add(0,"ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
            } //DECLARACIÓN
            else if (i < palabras.size() - 3 && tipoDato.contains(pal) && revisarVariable(palabras.get(i + 1).toString()) && palabras.get(i + 2).toString().equals("=")) {

                String txt = "";
                boolean band = false;
                if (palabras.get(i + 3).toString().equals("\"")) {
                    txt = palabras.get(i + 3).toString() + palabras.get(i + 4).toString() + palabras.get(i + 5).toString();
                    band = true;
                } else {
                    txt = palabras.get(i + 3).toString();
                }
                if (revisarValorVar(txt)) {
                    if (bandeClase) {
                        System.out.println("**** DECLARACIÓN DE VARIABLE CON ASIGNACIÓN ****");
                        if (band) {
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + palabras.get(i + 4).toString() + palabras.get(i + 5).toString());
                            i += 5;
                        } else {
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString());
                            i += 3;
                        }
                    } else {
                        System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                        llaves.add(0,"ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                }
                //DECLARACIÓN
            } else if (i < palabras.size() - 1 && tipoDato.contains(pal) && revisarVariable(palabras.get(i + 1).toString())) {
                if (bandeClase) {
                    System.out.println("**** DECLARACIÓN DE VARIABLE SIN ASIGNACIÓN ****");
                    System.out.println(pal + " " + palabras.get(i + 1).toString());
                    i++;
                } else {
                    System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                    llaves.add(0,"ERROR");
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
                        if (band) {
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + palabras.get(i + 3).toString() + palabras.get(i + 4).toString());
                        } else {
                            System.out.println(pal + " " + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString());
                        }
                    } else {
                        System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                        llaves.add(0,"ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                }
                //SALIDA DE DATOS
            } else if (i < palabras.size() - 2  && "Ticket".equals(pal)) {
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
                            if (band) {
                                System.out.print(pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString() + palabras.get(i + 3).toString() + palabras.get(i + 4).toString() + " ");
                                int j = i+5;
                                while(palabras.get(j).equals("_")){
                                    System.out.print(palabras.get(j).toString() + " ");
                                    if (revisarVariable(palabras.get(j+1).toString())) {
                                        System.out.print(palabras.get(j+1).toString() + " ");
                                        j+=2;
                                    }else if("\"".equals(palabras.get(j+1).toString())){
                                        if ("\"".equals(palabras.get(j+3).toString())) {
                                            System.out.print(palabras.get(j+1).toString() + palabras.get(j+2).toString() + palabras.get(j+3).toString() + " ");
                                        }else{
                                            System.out.println("ERROR EN TICKET");
                                            System.out.println("DATOS INCORRECTOS PARA SALIDA");
                                            llaves.add(0,"ERROR");
                                            return false;
                                        }
                                        j+=4;
                                    }else{
                                        System.out.println("ERROR EN TICKET");
                                        System.out.println("DATOS INCORRECTOS PARA SALIDA");
                                        llaves.add(0,"ERROR");
                                        return false;
                                    }
                                }
                                System.out.println("");
                            } else {
                                System.out.println(pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString());
                            }
                        } else {
                            System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                            llaves.add(0,"ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        }
                    }else{
                        System.out.println("ERROR EN TICKET");
                        System.out.println("NO SE ENCONTRÓ DATOS PARA SALIDA");
                        llaves.add(0,"ERROR");
                        return false;
                    }
                    
                }else{
                    System.out.println("ERROR EN TICKET");
                    System.out.println("NO SE ENCONTRÓ \":\"");
                    llaves.add(0,"ERROR");
                    return false;
                }
                // ENTRADA DE DATOS
            } else if (i < palabras.size() - 2 && "Skittle".equals(pal)) {

                if (":".equals(palabras.get(i + 1).toString())) {
                    
                    if(revisarVariable(palabras.get(i + 2).toString())){
                        if (bandeClase) {
                            System.out.println("**** ENTRADA DE DATOS ****");
                            System.out.println(pal + palabras.get(i + 1).toString() + " " + palabras.get(i + 2).toString());
                        } else {
                            System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                            llaves.add(0,"ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        }
                    }else{
                        System.out.println("ERROR EN SKITTLE");
                        System.out.println("NO SE ENCONTRÓ VARIABLE PARA ENTRADA DE DATOS");
                        llaves.add(0,"ERROR");
                        return false;
                    }
                }else{
                    System.out.println("ERROR EN SKITTLE");
                    System.out.println("NO SE ENCONTRÓ \":\"");
                    llaves.add(0,"ERROR");
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
                llaves.add(0,"SI");
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
                            llaves.add(0,"ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else {
                            if (bandeClase) {
                                if (i - 1 > 0) {
                                    if (palabras.get(i - 1).equals("SINO")) {
                                        System.out.println("**** SENTENCIA SI DEL SINO ****");
                                    } else {
                                        System.out.println("**** SENTENCIA SI ****");
                                    }
                                } else {
                                    System.out.println("**** SENTENCIA SI ****");
                                }
                                System.out.println(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString()
                                        + palabras.get(i + 5).toString() + palabras.get(i + 6).toString()); // Se imprime hasta el "{"
                                if (j + 1 < palabras.size()) {
                                    if (palabras.get(j + 1).equals("SINO")) {
                                        bandeSINO = true;
                                    }
                                }
                            } else {
                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                llaves.add(0,"ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }

                        }

                    } else {
                        System.out.println("SENTENCIA IF ERRÓNEA");
                        System.out.println("REVISAR LA CONDICIÓN DEL IF");
                        llaves.add(0,"ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                } else {
                    System.out.println("SENTENCIA IF ERRÓNEA");
                    System.out.println("ERROR EN PARÉNTESIS");
                    llaves.add(0,"ERROR");
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
                            llaves.add(0,"ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else {
                            if (bandeClase) {
                                System.out.println("**** SENTENCIA SINO ****");
                                System.out.println(pal + palabras.get(i + 1));
                                llaves.add(0,"SINO");
                                //verificarCorchete = "SINO";
                            } else {
                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                llaves.add(0,"ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }
                        }
                    } else if (!"SI".equals(palabras.get(i + 1))) {
                        System.out.println("SENTENCIA SINO ERRÓNEA");
                        System.out.println("ERROR: FALTAN LLAVES");
                        llaves.add(0,"ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    } else {
                        System.out.println("**** SENTENCIA SINO SI ****");
                        System.out.println(pal + " " + palabras.get(i + 1));
                        llaves.add(0,"SINO");
                        //verificarCorchete = "SINO";
                    }
                }
            } /*
                    WHILE
                    MIENTRAS ( Var,num,cade opCond Var,num,cade ) { instrucciones }
             */ else if ("MIENTRAS".equals(pal) && !bandeHAZ) {
                llaves.add(0,"MIENTRAS");
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
                            llaves.add(0,"ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        } else {
                            if (bandeClase) {
                                System.out.println("**** SENTENCIA MIENTRAS ****");
                                System.out.println(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString()
                                        + palabras.get(i + 5).toString() + palabras.get(i + 6).toString()); // Se imprime hasta el "{"
                            } else {
                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                llaves.add(0,"ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }

                        }
                    } else {
                        System.out.println("SENTENCIA MIENTRAS ERRÓNEA");
                        System.out.println("REVISAR LA CONDICIÓN DEL CICLO");
                        llaves.add(0,"ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                } else {
                    System.out.println("SENTENCIA MIENTRAS ERRÓNEA");
                    System.out.println("ERROR EN PARÉNTESIS");
                    llaves.add(0,"ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
            } else if ("MIENTRAS".equals(pal) && bandeHAZ) {
                if (bandeClase) {
                    bandeHAZ = false;
                    System.out.println("**** SENTENCIA HAZ MIENTRAS ****");
                    System.out.println(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString() + palabras.get(i + 5).toString());
                } else {
                    System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                    return false;
                }
            } /*
                        DO WHILE
                        HAZ { instucciones } MIENTRAS ( Var,num,cade opCond Var,num,cade )
             */ else if ("HAZ".equals(pal)) {
                llaves.add(0,"HAZ");
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
                        llaves.add(0,"ERROR");
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
                                    } else {
                                        System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                        llaves.add("ERROR");
                                        return false;
                                        //verificarCorchete = "ERROR";
                                    }
                                } else {
                                    System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                                    System.out.println("REVISAR LA CONDICIÓN DEL CICLO");
                                    llaves.add(0,"ERROR");
                                    return false;
                                    //verificarCorchete = "ERROR";
                                }
                            } else {
                                System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                                System.out.println("ERROR: FALTA PARENTESIS DE INCIO O CIERRE");
                                llaves.add(0,"ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }
                        } else {
                            System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                            System.out.println("ERROR: FALTA PALABRA MIENTRAS");
                            llaves.add(0,"ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        }
                    }
                } else {
                    System.out.println("SENTENCIA HAZ MIENTRAS ERRÓNEA");
                    System.out.println("ERROR: LLAVE DE INICIO");
                    llaves.add(0,"ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
            } /*
                    FOR
                    PARA ( tipoDato Var = valVar     Var opCond Var,num    Var  =  Var opLogico num,var )    { instrucciones }
                         1    2      3  4   5         6    7         8      9  10   11    12    13      14   15    16        17
             */ else if ("PARA".equals(pal)) {
                llaves.add(0,"PARA");
                //verificarCorchete = "PARA";
                if (i + 14 <= palabras.size()) {
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
                                            llaves.add(0,"ERROR");
                                            return false;
                                            //verificarCorchete = "ERROR";
                                        } else {
                                            if (bandeClase) {
                                                System.out.println("**** SENTENCIA PARA ****");
                                                System.out.println(pal + palabras.get(i + 1).toString() + palabras.get(i + 2).toString() + " " + palabras.get(i + 3).toString() + " " + palabras.get(i + 4).toString() + " "
                                                        + palabras.get(i + 5).toString() + " " + palabras.get(i + 6).toString() + " " + palabras.get(i + 7).toString() + " " + palabras.get(i + 8).toString() + " "
                                                        + palabras.get(i + 9).toString() + " " + palabras.get(i + 10).toString() + " " + palabras.get(i + 11).toString() + " " + palabras.get(i + 12).toString() + " "
                                                        + palabras.get(i + 13).toString() + palabras.get(i + 14).toString() + palabras.get(i + 15).toString());
                                                i += 14;
                                            } else {
                                                System.out.println("ERROR: INSTRUCCIÓN FUERA DE CLASE.");
                                                llaves.add(0,"ERROR");
                                                return false;
                                                //verificarCorchete = "ERROR";
                                            }
                                        }
                                    } else {
                                        System.out.println("SENTENCIA PARA ERRÓNEA");
                                        System.out.println("ERROR: LLAVE DE INICIO");
                                        llaves.add(0,"ERROR");
                                        return false;
                                        //verificarCorchete = "ERROR";
                                    }
                                } else {
                                    System.out.println("SENTENCIA PARA ERRÓNEA");
                                    System.out.println("ERROR: SECCION 3 DEL CICLO PARA");
                                    llaves.add(0,"ERROR");
                                    return false;
                                    //verificarCorchete = "ERROR";
                                }
                            } else {
                                System.out.println("SENTENCIA PARA ERRÓNEA");
                                System.out.println("ERROR: SECCION 2 DEL CICLO PARA");
                                llaves.add(0,"ERROR");
                                return false;
                                //verificarCorchete = "ERROR";
                            }
                        } else {
                            System.out.println("SENTENCIA PARA ERRÓNEA");
                            System.out.println("ERROR: SECCION 1 DEL CICLO PARA");
                            llaves.add(0,"ERROR");
                            return false;
                            //verificarCorchete = "ERROR";
                        }
                    } else {
                        System.out.println("SENTENCIA PARA ERRÓNEA");
                        System.out.println("ERROR: FALTA PARENTESIS");
                        llaves.add(0,"ERROR");
                        return false;
                        //verificarCorchete = "ERROR";
                    }
                } else {
                    System.out.println("SENTENCIA PARA ERRÓNEA");
                    System.out.println("ERROR: CICLO PARA");
                    llaves.add(0,"ERROR");
                    return false;
                    //verificarCorchete = "ERROR";
                }
            }

            if ("}".equals(pal)) {
                if (!llaves.isEmpty()) {
                    String llave = llaves.get(0).toString();
                    switch (llave){
                        case "SI":
                            System.out.println("**** CIERRE DE CORCHETE DEL SI ****");
                            System.out.println("}");
                            llaves.remove(0);
                            break;
                        case "SINO":
                            System.out.println("**** CIERRE DE CORCHETE DEL SINO ****");
                            System.out.println("}");
                            llaves.remove(0);
                            break;
                        case "MIENTRAS":
                            System.out.println("**** CIERRE DE CORCHETE DEL MIENTRAS ****");
                            System.out.println("}");
                            llaves.remove(0);
                            break;
                        case "HAZ":
                            System.out.println("**** CIERRE DE CORCHETE DEL HAZ MIENTRAS ****");
                            System.out.println("}");
                            llaves.remove(0);
                            break;
                        case "PARA":
                            System.out.println("**** CIERRE DE CORCHETE DEL PARA ****");
                            System.out.println("}");
                            llaves.remove(0);
                            break;
                        case "CLASE":
                            System.out.println("**** CIERRE DE CORCHETE DE LA CLASE ****");
                            System.out.println("}");
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
                }else{
                    System.out.println("ERROR: CORCHETE SOBRANTE.");
                    return false;
                }
            }
            
            
            
            /*
            if ("}".equals(pal)) {
                switch (verificarCorchete) {
                    case "SI":
                        System.out.println("**** CIERRE DE CORCHETE DEL SI ****");
                        System.out.println("}");
                        verificarCorchete = "";
                        break;
                    case "SINO":
                        System.out.println("**** CIERRE DE CORCHETE DEL SINO ****");
                        System.out.println("}");
                        verificarCorchete = "";
                        break;
                    case "MIENTRAS":
                        System.out.println("**** CIERRE DE CORCHETE DEL MIENTRAS ****");
                        System.out.println("}");
                        verificarCorchete = "";
                        break;
                    case "HAZ":
                        System.out.println("**** CIERRE DE CORCHETE DEL HAZ MIENTRAS ****");
                        System.out.println("}");
                        verificarCorchete = "";
                        break;
                    case "PARA":
                        System.out.println("**** CIERRE DE CORCHETE DEL PARA ****");
                        System.out.println("}");
                        verificarCorchete = "";
                        break;
                    case "CLASE":
                        System.out.println("**** CIERRE DE CORCHETE DE LA CLASE ****");
                        System.out.println("}");
                        verificarCorchete = "";
                        bandeClase = false;
                        break;
                    case "ERROR":
                        break;
                    default:
                        System.out.println("ERROR: CORCHETE SOBRANTE EN LA LÍNEA: " + linea);
                }
            } */

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
                try ( FileWriter fw = new FileWriter(ruta)) {
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

    // ================================================= DISEÑO ==================================================
    public Compiler() {
        initComponents();

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

        jScrollPane1 = new javax.swing.JScrollPane();
        txaCode = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaConsola = new javax.swing.JTextArea();
        btnNuevo = new javax.swing.JButton();
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

        txaCode.setColumns(20);
        txaCode.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txaCode.setRows(5);
        txaCode.setText("INICIO\n\n\t(: Escriba su Código aquí :)\n\n\nFIN");
        jScrollPane1.setViewportView(txaCode);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(290, 30, 930, 460);

        txaConsola.setColumns(20);
        txaConsola.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txaConsola.setRows(5);
        txaConsola.setMinimumSize(new java.awt.Dimension(403, 156));
        txaConsola.setPreferredSize(new java.awt.Dimension(403, 156));
        txaConsola.setSelectionEnd(43);
        txaConsola.setSelectionStart(43);
        jScrollPane2.setViewportView(txaConsola);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(290, 510, 930, 190);

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

            try ( FileWriter fw = new FileWriter(fichero)) {
                fw.write(this.txaCode.getText());

                File destino = new File(fichero.getPath() + ".candy");
                System.out.println(destino.getPath());
                //InputStream in = new FileInputStream(origen);
                //OutputStream out = new FileOutputStream(destino);
                destino.createNewFile();
                try ( FileReader fr = new FileReader(fichero)) {
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
        //verificarCorchete = "";
        llaves.clear();
        variables.clear();
        palabras.clear();
        clase.clear();
        if (analizarLexico(txaCode.getText())) {
            txaConsola.setEditable(true);
            System.out.println("---------------------------------------Análisis léxico correcto.");
            System.out.println("**** PALABRAS ****");
            System.out.println(palabras);
            System.out.println("**** VARIABLES ****");
            System.out.println(variables);
            System.out.println("**** CLASES ****");
            System.out.println(clase);
            if (analizarSintactico()) {
                System.out.println("---------------------------------------Análisis sintáctico correcto.");
            }else{
                System.out.println("---------------------------------------Error en el análisis sintáctico.");
            }
            
        } else {
            System.out.println("---------------------------------------Error en el análisis léxico.");
        }

    }//GEN-LAST:event_btnCompilarActionPerformed

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
                    try ( FileWriter fw = new FileWriter(ruta)) {
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

            try ( FileReader fr = new FileReader(fichero)) {
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

        try ( FileWriter fw = new FileWriter(ruta)) {
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
                try ( FileWriter fw = new FileWriter(ruta)) {
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
    private javax.swing.JTextArea txaCode;
    private javax.swing.JTextArea txaConsola;
    // End of variables declaration//GEN-END:variables
}
