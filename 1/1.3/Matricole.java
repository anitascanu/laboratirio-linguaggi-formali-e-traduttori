/* Esercizio 1.3. Implementare un DFA che riconosca il linguaggio di stringhe che contengono 
un numero di matricola seguito (subito) da un cognome, dove la combinazione di matricola e 
cognome corrisponde a studenti del corso A (A - K) che hanno un numero di matricola pari 
oppure a studenti del corso B (L - Z) che hanno un numero di matricola dispari */ 

public class Matricole {
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
                        state = 2;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 1;
                    else if (ch >= 'L' && ch <= 'Z')
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
                        state = 3;
                    else
                        state = -1;
                    break;
                case 3:
                    if (ch >= 'a' && ch <= 'z')
                        state = 3;
                    else
                        state = -1;
                    break;
            }
        }
        return state == 3;
    }

    public static void main(String args[]){
        //System.out.println(scan(args[0]) ? "OK" : "NOPE");

        //ACCETTATE
        System.out.print("123456Bianchi  ");
        System.out.println(scan("123456Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("654321Rossi  ");
        System.out.println(scan("654321Rossi") ? "ok" : "no");
        System.out.println();
    
        System.out.print("2Bianchi  ");
        System.out.println(scan("2Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("122B  ");
        System.out.println(scan("122B") ? "ok" : "no");
        System.out.println();

        System.out.print("1990Azzurri  ");
        System.out.println(scan("1990Azzurri") ? "ok" : "no");
        System.out.println();

        System.out.print("82Ciao  ");
        System.out.println(scan("82Ciao") ? "ok" : "no");
        System.out.println();
    
        System.out.print("01Scanu  ");
        System.out.println(scan("01Scanu") ? "ok" : "no");
        System.out.println();

        System.out.print("289107Rossi  ");
        System.out.println(scan("289107Rossi") ? "ok" : "no");
        System.out.println();

        //RIFIUTATE
        System.out.print("654321Bianchi ");
        System.out.println(scan("654321Bianchi ") ? "ok" : "no");
        System.out.println();

        System.out.print("123456Rossi ");
        System.out.println(scan("123456Rossi") ? "ok" : "no");
        System.out.println();

        System.out.print("654322 ");
        System.out.println(scan("654322") ? "ok" : "no");
        System.out.println();

        System.out.print("Rossi ");
        System.out.println(scan("Rossi") ? "ok" : "no");
        System.out.println();

        System.out.print("abc1567 ");
        System.out.println(scan("abc1567 ") ? "ok" : "no");
        System.out.println();

        System.out.print("??? ");
        System.out.println(scan("???") ? "ok" : "no");
        System.out.println();

        System.out.print("123456Bianchi!!! ");
        System.out.println(scan("123456Bianchi!!!") ? "ok" : "no");
        System.out.println();

        System.out.print("123456 Bianchi ");
        System.out.println(scan("123456 Bianchi") ? "ok" : "no");
        System.out.println();

        System.out.print("123456BiaNcHi ");
        System.out.println(scan("123456BiaNcHi") ? "ok" : "no");
        System.out.println();

        System.out.print("6A2B ");
        System.out.println(scan("6A2B") ? "ok" : "no");
        System.out.println();

    }
}
