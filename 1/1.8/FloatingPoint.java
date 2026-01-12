/* Esercizio 1.7. Progettare e implementare un DFA che riconosca il linguaggio delle costanti 
numeriche in virgola mobile utilizzando la notazione scientifica dove il simbolo e indica la 
funzione esponenziale con base 10. L’alfabeto del DFA contiene i seguenti elementi: le cifre 
numeriche 0, 1, . . . , 9, il segno . (punto) che precede una eventuale parte decimale, i segni 
+ (piu) e - (meno) per indicare positivita' o negativita', e il simbolo e.
Le stringhe accettate devono seguire le solite regole per la scrittura delle costanti numeriche.
In particolare, una costante numerica consiste di due segmenti, la seconda di quale e' opzionale:
il primo segmento e una sequenza di cifre numeriche che (1) puo' essere preceduta da un segno 
+ o meno -, (2) puo essere seguita da un segno punto ., che a sua volta deve essere seguito da
una sequenza non vuota di cifre numeriche; il secondo segmento inizia con il simbolo e, che a
sua volta e seguito da una sequenza di cifre numeriche che soddisfa i punti (1) e (2) scritti 
per il primo segmento. Si nota che, sia nel primo segmento, sia in un eventuale secondo segmento, 
un segno punto . non deve essere preceduto per forza da una cifra numerica. */

public class FloatingPoint{

    public static boolean scan(String str){
        int state = 0;
        int i = 0;

        while (state >= 0 && i < str.length()){
            final char ch = str.charAt(i++);

            switch(state){
                case 0: 
                    if (ch == '0')
                        state = 2;
                    else if (ch >= '1' && ch <= '9')
                        state = 3;
                    else if ((ch == '+') || (ch == '-'))
                        state = 1;
                    else if (ch == '.')
                        state = 4;
                    else 
                        state = -1;
                    break;
                case 1:
                    if (ch == '0')
                        state = 2;
                    else if (ch >= '1' && ch <= '9')
                        state = 3;
                    else if (ch == '.')
                        state = 4;
                    else 
                        state = -1;
                    break;
                case 2:
                    if (ch == '.')
                        state = 4;
                    else 
                        state = -1;
                    break;
                case 3:
                    if (ch >= '0' && ch <= '9')
                        state = 3;
                    else if (ch == '.')
                        state = 4;
                    else if (ch == 'e')
                        state = 6;
                    else 
                        state = -1;
                    break;
                case 4:
                    if (ch >= '0' && ch <= '9')
                        state = 5;
                    else 
                        state = -1;
                    break;
                case 5:
                    if (ch >= '0' && ch <= '9')
                        state = 5;
                    else if (ch == 'e')
                        state = 6;
                    else 
                        state = -1;
                    break;
                case 6:
                    if (ch == '0')
                        state = 8;
                    else if (ch >= '1' && ch <= '9')
                        state = 9;
                    else if (ch == '+' || ch == '-')
                        state = 7;
                    else if (ch == '.')
                        state = 10;
                    else 
                        state = -1;
                    break;
                case 7:
                    if (ch == '0')
                        state = 8;
                    else if (ch >= '1' && ch <= '9')
                        state = 9;
                    else if (ch == '.')
                        state = 10;
                    else 
                        state = -1;
                    break;
                case 8:
                    if (ch == '.')
                        state = 10;
                    else 
                        state = -1;
                    break;
                case 9:
                    if (ch >= '0' && ch <= '9')
                        state = 9;
                    else if (ch == '.')
                        state = 10;
                    else 
                        state = -1;
                    break;
                case 10:
                    if (ch >= '0' && ch <= '9')
                        state = 11;
                    else 
                        state = -1;
                    break;
                case 11:
                    if(ch >= '0' && ch <= '9')
                        state = 11;
                    else 
                        state = -1;
                    break;
            }
        }
        return state == 2 || state == 3 || state == 5 || state == 6 || state == 8 || state == 9 || state == 11;
    }

    public static void main(String args[]){
        System.out.print(args[0] + ":  ");
        System.out.println(scan(args[0]) ? "OK" : "NOPE");
        System.out.println();

        //accettate
        System.out.print("123:  ");
        System.out.println(scan("123") ? "ok" : "no");
        System.out.println();

        System.out.print("123.5:  ");
        System.out.println(scan("123.5") ? "ok" : "no");
        System.out.println();

        System.out.print(".567:  ");
        System.out.println(scan(".567") ? "ok" : "no");
        System.out.println();

        System.out.print("+7.5:  ");
        System.out.println(scan("+7.5") ? "ok" : "no");
        System.out.println();

        System.out.print("-.7:  ");
        System.out.println(scan("-.7") ? "ok" : "no");
        System.out.println();

        System.out.print("67e10:  ");
        System.out.println(scan("67e10") ? "ok" : "no");
        System.out.println();

        System.out.print("1e-2:  ");
        System.out.println(scan("1e-2") ? "ok" : "no");
        System.out.println();

        System.out.print("-.7e2:  ");
        System.out.println(scan("-.7e2") ? "ok" : "no");
        System.out.println();

        System.out.print("1e2.3:  ");
        System.out.println(scan("1e2.3") ? "ok" : "no");
        System.out.println();

        System.out.print("0.7e-2.3:  ");
        System.out.println(scan("1e2.3") ? "ok" : "no");
        System.out.println();

        //rifiutate
        System.out.print(".:  ");
        System.out.println(scan(".") ? "ok" : "no");
        System.out.println();

        System.out.print("e3:  ");
        System.out.println(scan("e3") ? "ok" : "no");
        System.out.println();

        System.out.print("123.:  ");
        System.out.println(scan("123.") ? "ok" : "no");
        System.out.println();

        System.out.print("+e6:  ");
        System.out.println(scan("+e6") ? "ok" : "no");
        System.out.println();

        System.out.print("1.2.3:  ");
        System.out.println(scan("1.2.3") ? "ok" : "no");
        System.out.println();

        System.out.print("4e5e6:  ");
        System.out.println(scan("4e5e6") ? "ok" : "no");
        System.out.println();

        System.out.print("++3:  ");
        System.out.println(scan("++3") ? "ok" : "no");
        System.out.println();

        System.out.print("00000.8:  ");
        System.out.println(scan("00000.8") ? "ok" : "no");
        System.out.println();

        System.out.print("0010:  ");
        System.out.println(scan("0010") ? "ok" : "no");
        System.out.println();
    }
}