package lab;

import java.util.Iterator;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juanba
 */
public class RouteA {
    private int laberinto[][];
    private int longitud;
    private int entrada[] = new int[2];
    private int salida[] = new int[2];
    private int posicionActual[] = new int[2];
    
    Stack<String> ruta = new Stack();
    Stack<String> rutaPaint = new Stack();
    
    //Constructors 
    public RouteA() {
    }

    public RouteA(int[][] laberinto, int longitud) {
        this.laberinto = laberinto;
        this.longitud = longitud;
    }

    //Getters and Setters
    public int[][] getLaberinto() {
        return laberinto;
    }

    public void setLaberinto(int[][] laberinto) {
        this.laberinto = laberinto;
    }

    public Stack<String> getRuta() {
        return ruta;
    }

    public void setRuta(Stack<String> ruta) {
        this.ruta = ruta;
    }
    
    public Stack<String> getRutaPaint() {
        return rutaPaint;
    }

    public void setRutaPaint(Stack<String> rutaPaint) {
        this.rutaPaint = rutaPaint;
    }
    
    
    //Methods
    public int solveLaberinto() {
        //Find Start and End
        int cont = 0;
        encontrarEntradaSalida();
        boolean puntoMuerto;
        posicionActual[0] = entrada[0]; // row value
        posicionActual[1] = entrada[1]; // column value
         
        while(posicionActual[0] != salida[0] || posicionActual[1] != salida[1]) {
            
            puntoMuerto = false;
             
            //Check Down
            if( posicionActual[0] + 1 >= 0 && posicionActual[0] + 1 <= longitud - 1 ) {
                if( laberinto[posicionActual[0] + 1][posicionActual[1]] == 0 || laberinto[posicionActual[0] + 1][posicionActual[1]] == 3  ) {
                    String cellname = String.format("%d_%d",posicionActual[0] + 1, posicionActual[1]);
                    ruta.push(cellname);
                    puntoMuerto = true;
                    
                }
            }
            
            //Check Left
            if( posicionActual[1] - 1 >= 0 && posicionActual[1] - 1 <= longitud - 1 ) {
                if( laberinto[posicionActual[0]][posicionActual[1] - 1] == 0 || laberinto[posicionActual[0]][posicionActual[1] - 1] == 3) {
                    String cellname = String.format("%d_%d",posicionActual[0], posicionActual[1] - 1);
                    ruta.push(cellname);
                    puntoMuerto = true;
                    
                }
            }
            
            //Check Up
            if( posicionActual[0] - 1 >= 0 && posicionActual[0] - 1 <= longitud - 1 ) {
                if( laberinto[posicionActual[0] - 1][posicionActual[1]] == 0 || laberinto[posicionActual[0] - 1][posicionActual[1]] == 3 ) {
                    String cellname = String.format("%d_%d",posicionActual[0] - 1, posicionActual[1]);
                    ruta.push(cellname);
                    puntoMuerto = true;
                    
                }
            }
            
            //Check Right
            if( posicionActual[1] + 1 >= 0 && posicionActual[1] + 1 <= longitud - 1 ) {
                if( laberinto[posicionActual[0]][posicionActual[1] + 1] == 0 || laberinto[posicionActual[0]][posicionActual[1] + 1] == 3 ) {
                    String cellname = String.format("%d_%d",posicionActual[0], posicionActual[1] + 1);
                    ruta.push(cellname);
                    puntoMuerto = true;
                    
                }
            }
            
            
            if(!puntoMuerto) {
                System.out.print("Punto Muerto\n");
            } 
            String posAct = ruta.pop();
            rutaPaint.push(posAct);
            cont++;
            String[] strArray = posAct.split("_");
            int[] intArray = new int[strArray.length];
            for(int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i]);
            }
            if(posicionActual[0] != entrada[0] || posicionActual[1] != entrada[1]) {
                laberinto[posicionActual[0]][posicionActual[1]] = 8;
            }
            posicionActual[0] = intArray[0];
            posicionActual[1] = intArray[1];         
        }
        rutaPaint.pop();
        return cont;
    }
    
    public void encontrarEntradaSalida() {
        for (int row = 0; row < longitud; row++) {
            for (int col = 0; col < longitud; col++) {
                if(laberinto[row][col] == 2) {
                    entrada[0] = row;
                    entrada[1] = col;   
                }
                
                if(laberinto[row][col] == 3) {
                   salida[0] = row;
                   salida[1] = col;
                }
            }
        }   
    }
    
    public void printAll() {
        Iterator<String> iter = ruta.iterator();

        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }
    
    public void popAll() {
        while(!ruta.empty()){
            ruta.pop();
        }
        while(!rutaPaint.empty()){
            rutaPaint.pop();
        }
    }
}

