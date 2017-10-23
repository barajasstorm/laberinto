/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author juanba
 */
public class RouteB {

    private int laberinto[][];
    private int laberintoOriginal[][];
    private int longitud;
    private int entrada[] = new int[2];
    private int salida[] = new int[2];
    private int posicionActual[] = new int[2];
    private int permuts[][];
    private int bestRoute[] = new int[24];

    Stack<String> ruta = new Stack();
    Stack<String> rutaPaint = new Stack();

    //Constructors 
    public RouteB() {
        this.permuts = new int[][]{
            {1, 2, 3, 4},
            {1, 2, 4, 3},
            {1, 3, 2, 4},
            {1, 3, 4, 2},
            {1, 4, 2, 3},
            {1, 4, 3, 2},
            {2, 1, 3, 4},
            {2, 1, 4, 3},
            {2, 3, 1, 4},
            {2, 3, 4, 1},
            {2, 4, 1, 3},
            {2, 4, 3, 1},
            {3, 1, 2, 4},
            {3, 1, 4, 2},
            {3, 2, 1, 4},
            {3, 2, 4, 1},
            {3, 4, 1, 2},
            {3, 4, 2, 1},
            {4, 1, 2, 3},
            {4, 1, 3, 2},
            {4, 2, 1, 3},
            {4, 2, 3, 1},
            {4, 3, 1, 2},
            {4, 3, 2, 1},};
    }

    public RouteB(int[][] laberinto, int longitud) {
        this.permuts = new int[][]{
            {1, 2, 3, 4},
            {1, 2, 4, 3},
            {1, 3, 2, 4},
            {1, 3, 4, 2},
            {1, 4, 2, 3},
            {1, 4, 3, 2},
            {2, 1, 3, 4},
            {2, 1, 4, 3},
            {2, 3, 1, 4},
            {2, 3, 4, 1},
            {2, 4, 1, 3},
            {2, 4, 3, 1},
            {3, 1, 2, 4},
            {3, 1, 4, 2},
            {3, 2, 1, 4},
            {3, 2, 4, 1},
            {3, 4, 1, 2},
            {3, 4, 2, 1},
            {4, 1, 2, 3},
            {4, 1, 3, 2},
            {4, 2, 1, 3},
            {4, 2, 3, 1},
            {4, 3, 1, 2},
            {4, 3, 2, 1},};
        this.laberinto = laberinto;
        this.longitud = longitud;
        this.laberintoOriginal = new int[longitud][longitud];

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
    public void solveLaberintoB() {

        for (int i = 0; i < longitud; i++) {
            System.arraycopy(laberinto[i], 0, laberintoOriginal[i], 0, longitud);
        }

        int contador;
        int permutacion = 0;
        for (int[] permut : permuts) {
            //Find Start and End
            contador = 0;
            encontrarEntradaSalida();
            posicionActual[0] = entrada[0]; // row value
            posicionActual[1] = entrada[1]; // column value

            //Run Algorithm
            while (posicionActual[0] != salida[0] || posicionActual[1] != salida[1]) {
                for (int i = 0; i < 4; i++) {
                    if (permut[i] == 1) {
                        checkUp();
                    }
                    if (permut[i] == 2) {
                        checkDown();
                    }
                    if (permut[i] == 3) {
                        checkLeft();
                    }
                    if (permut[i] == 4) {
                        checkRight();
                    }
                }
                String posAct = ruta.pop();
                contador += 1;

                String[] strArray = posAct.split("_");
                int[] intArray = new int[strArray.length];
                for (int i = 0; i < strArray.length; i++) {
                    intArray[i] = Integer.parseInt(strArray[i]);
                }
                if(posicionActual[0] != entrada[0] || posicionActual[1] != entrada[1]) {
                    laberinto[posicionActual[0]][posicionActual[1]] = 8;
                }
                posicionActual[0] = intArray[0];
                posicionActual[1] = intArray[1];
            }

            bestRoute[permutacion] = contador;
            permutacion++;
            while (!ruta.empty()) {
                ruta.pop();
            }

            for (int i = 0; i < longitud; i++) {
                System.arraycopy(laberintoOriginal[i], 0, laberinto[i], 0, longitud);
            }
        }
    }

    public void solveLaberinto(int[] combo) {
        //Find Start and End
        int cont = 0;
        encontrarEntradaSalida();
        posicionActual[0] = entrada[0]; // row value
        posicionActual[1] = entrada[1]; // column value

        while (posicionActual[0] != salida[0] || posicionActual[1] != salida[1]) {
            for (int i = 0; i < 4; i++) {
                if (combo[i] == 1) {
                    checkUp();
                }
                if (combo[i] == 2) {
                    checkDown();
                }
                if (combo[i] == 3) {
                    checkLeft();
                }
                if (combo[i] == 4) {
                    checkRight();
                }
            }

            String posAct = ruta.pop();
            rutaPaint.push(posAct);
            String[] strArray = posAct.split("_");
            int[] intArray = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i]);
            }
            if(posicionActual[0] != entrada[0] || posicionActual[1] != entrada[1]) {
                laberinto[posicionActual[0]][posicionActual[1]] = 8;
            }
            posicionActual[0] = intArray[0];
            posicionActual[1] = intArray[1];
        }
        rutaPaint.pop();
    }

    public void encontrarEntradaSalida() {
        for (int row = 0; row < longitud; row++) {
            for (int col = 0; col < longitud; col++) {
                if (laberinto[row][col] == 2) {
                    entrada[0] = row;
                    entrada[1] = col;
                }

                if (laberinto[row][col] == 3) {
                    salida[0] = row;
                    salida[1] = col;
                }
            }
        }
    }

    public void printAll() {
        Iterator<String> iter = ruta.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    public boolean checkUp() {
        if (posicionActual[0] - 1 >= 0 && posicionActual[0] - 1 <= longitud - 1) {
            if (laberinto[posicionActual[0] - 1][posicionActual[1]] == 0 || laberinto[posicionActual[0] - 1][posicionActual[1]] == 3) {
                String cellname = String.format("%d_%d", posicionActual[0] - 1, posicionActual[1]);
                ruta.push(cellname);
                return true;
            }
        }
        return false;
    }

    public boolean checkDown() {
        if (posicionActual[0] + 1 >= 0 && posicionActual[0] + 1 <= longitud - 1) {
            if (laberinto[posicionActual[0] + 1][posicionActual[1]] == 0 || laberinto[posicionActual[0] + 1][posicionActual[1]] == 3) {
                String cellname = String.format("%d_%d", posicionActual[0] + 1, posicionActual[1]);
                ruta.push(cellname);
                return true;
            }
        }
        return false;
    }

    public boolean checkLeft() {
        if (posicionActual[1] - 1 >= 0 && posicionActual[1] - 1 <= longitud - 1) {
            if (laberinto[posicionActual[0]][posicionActual[1] - 1] == 0 || laberinto[posicionActual[0]][posicionActual[1] - 1] == 3) {
                String cellname = String.format("%d_%d", posicionActual[0], posicionActual[1] - 1);
                ruta.push(cellname);
                return true;
            }
        }
        return false;
    }

    public boolean checkRight() {
        if (posicionActual[1] + 1 >= 0 && posicionActual[1] + 1 <= longitud - 1) {
            if (laberinto[posicionActual[0]][posicionActual[1] + 1] == 0 || laberinto[posicionActual[0]][posicionActual[1] + 1] == 3) {
                String cellname = String.format("%d_%d", posicionActual[0], posicionActual[1] + 1);
                ruta.push(cellname);
                return true;
            }
        }
        return false;
    }

    public void printPermutations() {
        for (int[] permut : permuts) {
            for (int j = 0; j < 4; j++) {
                System.out.print(permut[j]);
            }
            System.out.println();
        }
    }

    public int[] getBestRoute() {
        int indexSmallest = 0;
        int value = 99999;
        int combo[] = null;
        for (int i = 0; i < bestRoute.length; i++) {
            if (value > bestRoute[i]) {
                value = bestRoute[i];
                indexSmallest = i;
            }
        }

        for (int i = 0; i < permuts.length; i++) {
            if (indexSmallest == i) {
                System.out.println("Best Combination: " + Arrays.toString(permuts[i]));
                combo = permuts[i];
            }
        }
        return combo;
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
