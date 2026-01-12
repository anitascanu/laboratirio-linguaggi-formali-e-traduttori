/* Esercizio 1.7. Implementare un DFA che riconosca il linguaggio di stringhe che
contengono il tuo nome e tutte le stringhe ottenute dopo la sostituzione di un 
carattere del nome con un altro qualsiasi.*/


public class MyName{
    public static boolean scan(String s){
        int state = 0;
        int i = 0;

        while (state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);

            switch(state){
                case 0: 
                    if (ch == 'a')
                        state = 1;
                    else
                        state = 6;
                    break;
                case 1:
                    if (ch == 'n')
                        state = 2;
                    else 
                        state = 7;
                    break;
                case 2:
                    if (ch == 'i')
                        state = 3;
                    else 
                        state = 8;
                    break;
                case 3:
                    if (ch == 't')
                        state = 4;
                    else 
                        state = 9;
                    break;
                case 4:
                    state = 5;
                    break;
                case 5:
                    state = -1;
                    break;
                case 6:
                    if (ch == 'n')
                        state = 7;
                    else 
                        state = -1;
                    break;
                case 7:
                    if (ch == 'i')
                        state = 8;
                    else 
                        state = -1;
                    break;
                case 8:
                    if (ch == 't')
                        state = 9;
                    else 
                        state = -1;
                    break;
                case 9:
                    if (ch == 'a')
                        state = 5;
                    else 
                        state = -1;
                    break;
            }
        }
        return state == 5;
    }


    public static void main(String args[]){
        System.out.print(args[0] + ":  ");
        System.out.println(scan(args[0]) ? "OK" : "NOPE");
        System.out.println();

        //accettate
        System.out.print("anita:  ");
        System.out.println(scan("anita") ? "ok" : "no");
        System.out.println();

        System.out.print("a%ita:  ");
        System.out.println(scan("a%ita") ? "ok" : "no");
        System.out.println();

        System.out.print("$nita:  ");
        System.out.println(scan("$nita") ? "ok" : "no");
        System.out.println();

        System.out.print("an7ta:  ");
        System.out.println(scan("an7ta") ? "ok" : "no");
        System.out.println();

        System.out.print("ani!a:  ");
        System.out.println(scan("ani!a") ? "ok" : "no");
        System.out.println();

        System.out.print("anitr:  ");
        System.out.println(scan("anitr") ? "ok" : "no");
        System.out.println();

        System.out.print("aniTa:  ");
        System.out.println(scan("aniTa") ? "ok" : "no");
        System.out.println();

        System.out.print("nnita:  ");
        System.out.println(scan("nnita") ? "ok" : "no");
        System.out.println();

        //non accettate
        System.out.print("eva:  ");
        System.out.println(scan("eva") ? "ok" : "no");
        System.out.println();

        System.out.print("attta:  ");
        System.out.println(scan("attta") ? "ok" : "no");
        System.out.println();

        System.out.print("anitaaa:  ");
        System.out.println(scan("anitaaa") ? "ok" : "no");
        System.out.println();

        System.out.print("4nit4:  ");
        System.out.println(scan("4nit4") ? "ok" : "no");
        System.out.println();

        System.out.print("a*i*a:  ");
        System.out.println(scan("a*i*a") ? "ok" : "no");
        System.out.println();

        System.out.print("alessio:  ");
        System.out.println(scan("alessio") ? "ok" : "no");
        System.out.println();

        System.out.print(":  ");
        System.out.println(scan("") ? "ok" : "no");
        System.out.println();

        System.out.print("10101:  ");
        System.out.println(scan("10101") ? "ok" : "no");
        System.out.println();

        System.out.print("*:  ");
        System.out.println(scan("*") ? "ok" : "no");
        System.out.println();

        System.out.print("__ita:  ");
        System.out.println(scan("__ita") ? "ok" : "no");
        System.out.println();
        
    }
}