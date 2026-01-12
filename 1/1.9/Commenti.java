/* Esercizio 1.9. Implementare un DFA con alfabeto {/, *, a} che riconosca il linguaggio di commenti */

public class Commenti {
    public static boolean scan(String s){
        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);
            switch(state){
                case 0: 
                    if (ch == '/')
                        state = 1;
                    else 
                        state = -1;
                    break;
                case 1:
                    if (ch == '*')
                        state = 2;
                    else 
                        state = -1;
                    break;
                case 2:
                    if (ch == 'a' || ch == '/')
                        state = 2;
                    else if (ch == '*')
                        state = 3;
                    else 
                        state = -1;
                    break;
                case 3:
                    if (ch == 'a')
                        state = 2;
                    else if (ch == '/')
                        state = 4;
                    else if (ch == '*')
                        state = 3;
                    else 
                        state = -1;
                    break;
                case 4:
                    state = -1;
                    break;
            }
        }
        return state == 4;
    }


    public static void main(String args[]){
        //System.out.println(scan(args[0]) ? "OK" : "NOPE");
        
        //accettate
        System.out.print("/****/:  ");
        System.out.println(scan("/****/") ? "ok" : "no");
        System.out.println();

        System.out.print("/*a*a*/:  ");
        System.out.println(scan("/*a*a*/") ? "ok" : "no");
        System.out.println();

        System.out.print("/*a/**/:  ");
        System.out.println(scan("/*a/**/") ? "ok" : "no");
        System.out.println();

        System.out.print("/**a///a/a**/:  ");
        System.out.println(scan("/**a///a/a**/") ? "ok" : "no");
        System.out.println();

        System.out.print("/**/:  ");
        System.out.println(scan("/**/") ? "ok" : "no");
        System.out.println();

        System.out.print("/*/*/:  ");
        System.out.println(scan("/*/*/") ? "ok" : "no");
        System.out.println();

        //non accettate
        System.out.print("*/*a:  ");
        System.out.println(scan("*/*a") ? "ok" : "no");
        System.out.println();
        
        System.out.print("/*/:  ");
        System.out.println(scan("/*/") ? "ok" : "no");
        System.out.println();

        System.out.print("aaa/*/aa:  ");
        System.out.println(scan("aaa/*/aa") ? "ok" : "no");
        System.out.println();

        System.out.print("/**//***a:  ");
        System.out.println(scan("/**//***a") ? "ok" : "no");
        System.out.println();

        System.out.print("/**/***/:  ");
        System.out.println(scan("/**/***/") ? "ok" : "no");
        System.out.println();

        System.out.print("aaaaa:  ");
        System.out.println(scan("aaaaa") ? "ok" : "no");
        System.out.println();

        System.out.print("/*:  ");
        System.out.println(scan("/*") ? "ok" : "no");
        System.out.println();
    }
}
