/* Esercizio 1.1. Implementazione in java di un DFA che sia in grado di riconoscere stringhe che 
non contengono al loro interno tre zeri consecutivi. L'automa e' definito sull'alfabeto {0, 1} */

/* La struttura di questo automa e' la stessa dell'automa che riconosce tre zeri. L'unica differenza
consiste nell'insieme degli stati finali, che sara' il complementare dell'insieme degli stati finali
dell'automa che riconosce tre zeri.

F2 = Q - F1, con F1 insieme degli stati finali dell'automa che riconosce tre zeri consecutivi, F2 
insieme degli stati finali dell'automa complementare e Q insieme di tutti gli stati dell'automa */

public class TreZeriComplementare {
    public static boolean scan(String s){
        int state = 0;
        int i = 0;

        while (state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);

            switch(state){
                case 0:
                    if (ch == '0')
                        state = 1;
                    else if (ch == '1')
                        state = 0;
                    else
                        state = -1;
                    break;
                case 1:
                    if (ch == '0')
                        state = 2;
                    else if (ch == '1')
                        state = 0;
                    else
                        state = -1;
                    break;
                case 2:
                    if (ch == '1')
                        state = 0;
                    else
                        state = -1;
                    break;
            }
        }
        return state == 0 || state == 1 || state == 2;
    }

    public static void main(String args[]){
        System.out.println(scan(args[0]) ? "OK" : "NOPE");
    }
}
