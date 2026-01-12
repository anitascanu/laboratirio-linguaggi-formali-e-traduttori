/* Esercizio 1.7. Implementare un DFA che riconosca il linguaggio di stringhe che contengono 
un numero di matricola seguito (subito) da un cognome, dove la combinazione di matricola e 
cognome corrisponde a studenti del turno T2 o del turno T3 del laboratorio di Linguaggi Formali 
e Traduttori. Si ricorda le regole per suddivisione di studenti in turni:
• Turno T1: cognomi la cui iniziale e compresa tra A e K, e la penultima cifra del numero di
matricola e dispari;
• Turno T2: cognomi la cui iniziale e compresa tra A e K, e la penultima cifra del numero di
matricola e pari;
• Turno T3: cognomi la cui iniziale e compresa tra L e Z, e la penultima cifra del numero di
matricola e dispari;
• Turno T4: cognomi la cui iniziale e compresa tra L e Z, e la penultima cifra del numero di
matricola e pari.

Un numero di matricola deve essere composto di almeno due cifre, ma non ha un numero massimo 
prestabilito di cifre. */

public class Turni{
    
    public static boolean scan(String s){
        int state = 0;
        int i = 0;

        while (state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);

            switch(state){
                case 0: 
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 2;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 1;
                    else 
                        state = -1;
                    break;
                case 1:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 4;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 3;
                    else 
                        state = -1;
                    break;
                case 2:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 6;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 5;
                    else 
                        state = -1;
                    break;
                case 3:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 4;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 3;
                    else if (ch >= 'L' && ch <= 'Z')
                        state = 7;
                    else 
                        state = -1;
                    break;
                case 4:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 6;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 5;
                    else if (ch >= 'L' && ch <= 'Z')
                        state = 7;
                    else 
                        state = -1;
                    break;
                case 5:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 4;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 3;
                    else if (ch >= 'A' && ch <= 'K')
                        state = 7;
                    else 
                        state = -1;
                    break;
                case 6:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 6;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 5;
                    else if (ch >= 'A' && ch <= 'K')
                        state = 7;
                    else 
                        state = -1;
                    break;
                case 7:
                    if (ch >= 'a' && ch <= 'z')
                        state = 7;
                    else 
                        state = -1;
                    break;
            }
        }
        return state == 7;
    }
    public static void main(String args[]){
        //System.out.println(scan(args[0]) ? "OK" : "NOPE");

        //accettate
        System.out.print("654321Bianchi:  ");
        System.out.println(scan("654321Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("123456Rossi:  ");
        System.out.println(scan("123456Rossi") ? "ok" : "no");
        System.out.println();

        System.out.print("221B:  ");
        System.out.println(scan("221B") ? "ok" : "no");
        System.out.println();

        System.out.print("902772Scanu:  ");
        System.out.println(scan("902772Scanu") ? "ok" : "no");
        System.out.println();

        System.out.print("972Scanu:  ");
        System.out.println(scan("972Scanu") ? "ok" : "no");
        System.out.println();

        //rifiutate
        System.out.print("123456Bianchi:  ");
        System.out.println(scan("123456Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("654321Rossi:  ");
        System.out.println(scan("654321Rossi") ? "ok" : "no");
        System.out.println();

        System.out.print("5:  ");
        System.out.println(scan("5") ? "ok" : "no");
        System.out.println();

        System.out.print("654322:  ");
        System.out.println(scan("654322") ? "ok" : "no");
        System.out.println();

        System.out.print("Rossi:  ");
        System.out.println(scan("Rossi") ? "ok" : "no");
        System.out.println();

        System.out.print("2Bianchi:  ");
        System.out.println(scan("2Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("902782Scanu:  ");
        System.out.println(scan("902782Scanu") ? "ok" : "no");
        System.out.println();
}
}