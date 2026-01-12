/* Esercizio 1.4. Implementare un DFA che riconosca il linguaggio di stringhe che contengono 
un numero di matricola seguito (subito) da un cognome, dove la combinazione di matricola e 
cognome corrisponde a studenti del corso A (A - K) che hanno un numero di matricola pari 
oppure a studenti del corso B (L - Z) che hanno un numero di matricola dispari.  Il numero
di matricola e il cognome possono essere separati da una sequenza di spazi, e possono essere
preceduti e/o seguiti da sequenze eventualmente vuote di spazi */ 

public class MatricoleConSpazi {
    public static boolean scan(String s){
        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);
            switch(state){
                case 0:
                    if (ch == ' ')
                        state = 0;
                    else if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 2;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 1;
                    else 
                        state = -1;
                    break;
                case 1:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 2;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 1;
                    else if (ch >= 'L' && ch <= 'Z')
                        state = 5;
                    else if (ch == ' ')
                        state = 3;
                    else
                        state = -1;
                    break;
                case 2:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 2;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 1;
                    else if (ch >= 'A' && ch <= 'K')
                        state = 5;
                    else if (ch == ' ')
                        state = 4;
                    else
                        state = -1;
                    break;
                case 3:
                    if (ch >= 'L' && ch <= 'Z')
                        state = 5;
                    else if (ch == ' ')
                        state = 3;
                    else
                        state = -1;
                    break;
                case 4:
                    if (ch >= 'A' && ch <= 'K')
                        state = 5;
                    else if (ch == ' ')
                        state = 4;
                    else
                        state = -1;
                    break;
                case 5:
                    if (ch >= 'a' && ch <= 'z')
                        state = 5;
                    else if (ch == ' ')
                        state = 6;
                    else
                        state = -1;
                    break;
                case 6:
                    if (ch >= 'A' && ch <= 'Z')
                        state = 5;
                    else if(ch == ' ')
                        state = 6;
                    else 
                        state = -1;
                    break;
            }
        }
        return state == 5 || state == 6;
    }

    public static void main(String args[]){
        //System.out.println(scan(args[0]) ? "OK" : "NOPE");
        
        //accettate
        System.out.print("123456Bianchi:   ");
        System.out.println(scan("123456Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("654321Rossi:   ");
        System.out.println(scan("654321Rossi") ? "ok" : "no");
        System.out.println();
    
        System.out.print("2Bianchi:   ");
        System.out.println(scan("2Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("122B:   ");
        System.out.println(scan("122B") ? "ok" : "no");
        System.out.println();

        System.out.print("123456 Bianchi:   ");
        System.out.println(scan("123456 Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("654321 Rossi:   ");
        System.out.println(scan("654321 Rossi") ? "ok" : "no");
        System.out.println();

        System.out.print("123456 De Gasperi:   ");
        System.out.println(scan("123456 De Gasperi") ? "ok" : "no");
        System.out.println();

        System.out.print("    123456  De  Gasperi  :   ");
        System.out.println(scan(" 123456  De  Gasperi  ") ? "ok" : "no");
        System.out.println();

        //rifiutate
        System.out.print("654321Bianchi:   ");
        System.out.println(scan("654321Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("123456Rossi:   ");
        System.out.println(scan("123456Rossi") ? "ok" : "no");
        System.out.println();

        System.out.print("654322:   ");
        System.out.println(scan("654322") ? "ok" : "no");
        System.out.println();

        System.out.print("Rossi:   ");
        System.out.println(scan("Rossi") ? "ok" : "no");
        System.out.println();

        System.out.print("654 321Bianchi:   ");
        System.out.println(scan("654 321Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print(" 123456  De  Gas peri  :   ");
        System.out.println(scan(" 123456  De  Gas peri  ") ? "ok" : "no");
        System.out.println();

        System.out.print("65432 2B:   ");
        System.out.println(scan("65432 2B") ? "ok" : "no");
        System.out.println();

        System.out.print("Ro ssi:   ");
        System.out.println(scan("Ro ssi") ? "ok" : "no");
        System.out.println();

        System.out.print("   1 23 456 Bi anc hi:   ");
        System.out.println(scan("   1 23 456 Bi anc hi") ? "ok" : "no");
        System.out.println();

        
    }
}
