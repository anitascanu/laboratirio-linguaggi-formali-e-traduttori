/* Esercizio 1.5. Implementare un DFA che riconosca il linguaggio di stringhe che contengono 
matricola e cognome degli studenti del corso A che hanno un numero di matricola pari oppure 
studenti del corso B che hanno un numero di matricola dispari. Il cognome precede il numero 
di matricola */
public class MatricoleInverso {

    public static boolean scan(String s){
        int state = 0;
        int i = 0;

        while (state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);
            switch(state){
                case 0:
                    if (ch >= 'A' && ch <= 'K')
                        state = 1;
                    else if (ch >= 'L' && ch <= 'Z')
                        state = 2;
                    else
                        state = -1;
                    break;
                case 1:
                    if (ch >= 'a' && ch <= 'z')
                        state = 1;
                    else if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 4;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 3;
                    else 
                        state = -1;
                    break;
                case 2:
                    if (ch >= 'a' && ch <= 'z')
                        state = 2;
                    else if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
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
                    else 
                        state = -1;
                    break;
                case 4:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 4;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 3;
                    else 
                        state = -1;
                    break;
                case 5:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 6;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 5;
                    else 
                        state = -1;
                    break;
                case 6:
                    if (ch == '0' || ch == '2' || ch == '4' || ch == '6' || ch == '8')
                        state = 6;
                    else if (ch == '1' || ch == '3' || ch == '5' || ch == '7' || ch == '9')
                        state = 5;
                    else 
                        state = -1;
                    break;
            }
        }
        return state == 4 || state == 5;
    }

    public static void main(String args[]){
        //System.out.println(scan(args[0]) ? "OK" : "NOPE");
        
        //accettate
        System.out.print("Bianchi123456  ");
        System.out.println(scan("Bianchi123456") ? "ok" : "no");

        System.out.print("Rossi654321  ");
        System.out.println(scan("Rossi654321") ? "ok" : "no");
    
        System.out.print("Bianchi2  ");
        System.out.println(scan("Bianchi2") ? "ok" : "no");

        System.out.print("B122  ");
        System.out.println(scan("B122") ? "ok" : "no");
        
        System.out.print("Scanu12312127  ");
        System.out.println(scan("Scanu12312127") ? "ok" : "no");
    
        System.out.print("Caio290  ");
        System.out.println(scan("Caio290") ? "ok" : "no");

        System.out.print("Zasdads1111  ");
        System.out.println(scan("Zasdads1111") ? "ok" : "no");

        System.out.println();

        //non accettate
        System.out.print("Bianchi654321  ");
        System.out.println(scan("Bianchi654321 ") ? "ok" : "no");

        System.out.print("Rossi123456  ");
        System.out.println(scan("Rossi123456") ? "ok" : "no");

        System.out.print("654322  ");
        System.out.println(scan("654322") ? "ok" : "no");

        System.out.print("Rossi  ");
        System.out.println(scan("Rossi") ? "ok" : "no");

        System.out.print("RoSsI23123  ");
        System.out.println(scan("RoSsI23123") ? "ok" : "no");

        System.out.print("344231Rossi  ");
        System.out.println(scan("344231Rossi") ? "ok" : "no");

        System.out.print("B1  ");
        System.out.println(scan("B1") ? "ok" : "no");

        System.out.print("Bianchi$(1290  ");
        System.out.println(scan("Bianchi$(1290") ? "ok" : "no");

    }
}
