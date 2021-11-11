package com.mardare;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class giocoImpiccato {

    //parole per il gioco

    public static final String[] WORDS = {
            "ASTRATTA", "PUBLIC", "CLASSE", "MAIN", "PRIVATE", "FINAL", "VETTORE", "INT", "DOUBLE", "FLOAT", "STRING", "ARGS", "LIBRERIE", "BOOLEAN", "CHAR", "STRING", "STATIC", "IF", "FOR", "DOWHILE", "WHILE", "VOID", "OGGETTO", "EREDITARIETA", "POLIMORFISMO", "JVM", "GARBAGECOLLECTOR", "HEAP", "SUPERCLASSE", "PROTECTED", "SOTTOCLASSE", "GERARCHIA", "METODI", "ATTRIBUTI", "GETTER", "SETTER", "COSTRUTTORI", "UML", "ADT", "ASTRAZIONE", "OOP", "INFORMATION HIDING", "INCAPSULAMENTO", "PARAMETRO", "DATO", "INTERFACCIA", "GENERALIZZAZIONE", "COMPOSIZIONE", "AGGREGAZIONE", "ISTANZA", "REINGEGNEERIZZAZIONE", "BYTE CODE", "PACKAGE", "PORTABILITA", "COMPILATORE", "INPUT", "OUTPUT" };

    public static final Random RANDOM = new Random();

    //numero massimo di errori che il giocatore può commettere
    public static final int ERRORI_MASSIMI = 8;

    //parola da indovinare
    private String parolaDaTrovare;

    private char[] parolaTrovata;
    private int numeroErrori;

    //conteggio lettere inserite dal giocatore
    private ArrayList < String > letters = new ArrayList<>();

    //metodo che ritorna la prossima parola casuale da trovare
    private String prossimaParolaDaTrovare() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    //metodo per iniziare una nuova partita
    public void nuovaPartita() {
        numeroErrori = 0;
        letters.clear();
        parolaDaTrovare = prossimaParolaDaTrovare();

        //metodo che inizializza la parola
        parolaTrovata = new char[parolaDaTrovare.length()];

        for (int i = 0; i < parolaTrovata.length; i++) {
            parolaTrovata[i] = '_';
        }
    }

    //metodo che ritorna VERO se il giocatore indovina la parola
    public boolean parolaTrovata(){
        return parolaDaTrovare.contentEquals(new String(parolaTrovata));
    }

    //metodo che aggiorni la parolaTrovata ogni volta che viene inserito un carattere
    public void inserisciLettera(String c){
        //facciamo in modo che aggiorni solamente se la lettera non è ancora stata inserita
        if(!letters.contains(c)){
            //controlliamo che la parola da trovare contega il carattere c inserito
            if(parolaDaTrovare.contains(c)) {
                //se la contiene, rimpiazziamo "_" con il carattere c inserito
                int indice = parolaDaTrovare.indexOf(c);


                while (indice >= 0) {
                    parolaTrovata[indice] = c.charAt(0);
                    indice = parolaDaTrovare.indexOf(c, indice + 1);
                }}
            else{
                    //c non è presente nella parola
                    numeroErrori++;
                }

                //c è presente nella parola
                letters.add(c);
            }
        }


    //metodo che ritorna lo stato della parola con le lettere inserite ed indovinate dal giocatore
    private String contenutoParolaTrovata(){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < this.parolaTrovata.length; i++){
            builder.append(parolaTrovata[i]);

            if (i < parolaTrovata.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    //metoodo per giocare
    public void gioca(){
        try (Scanner input = new Scanner(System.in)) {
            //possiamo giocare solamente se il numeroErrori è minore di erroriMassimi
            while (numeroErrori < ERRORI_MASSIMI) {
                System.out.println("\nInserisci una lettera: ");
                //prendiamo il prossimo input da tastiera
                String str = input.next();

                //prendiamo solamente la prima lettera
                if (str.length() > 1) {
                    str = str.substring(0,1);
                }

                //aggiorniamo la stringa
                inserisciLettera(str);

                //mostriamo la stringa corrente
                System.out.println("\n" + contenutoParolaTrovata());

                if (parolaTrovata()){
                    System.out.println("\nHai vinto!");
                    break;
                }
                else{
                    //mostriamo il numero di tentativi rimasti al giocatore
                    System.out.println("\n Tentativi rimasti =>" + (ERRORI_MASSIMI - numeroErrori));
                }
            }

            if (numeroErrori == ERRORI_MASSIMI){
                //diciamo al giocatore che ha perso
                System.out.println("\nHai perso!");
                System.out.println("La parola da troavare era:" + parolaDaTrovare);
            }
        }

}

    public static void main(String[] args){
        System.out.println("Gioco dell'impiccato, fatto dal treno con amore :D");
        giocoImpiccato giocoImpiccato = new giocoImpiccato();
        giocoImpiccato.nuovaPartita();
        giocoImpiccato.gioca();
    }
}
