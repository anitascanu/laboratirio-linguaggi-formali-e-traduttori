/* Esercizio 1.10. Implementare un DFA con alfabeto {/, *, a} che riconosca il linguaggio di commenti */



public class Commenti {
    public static boolean scan(String s){
        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()){
            final char ch = s.charAt(i++);
            switch(state){
                case 0: 
                    if (ch == 'a' || ch == '*')
                        state = 0;
                    else if (ch == '/')
                        state = 1;
                    else 
                        state = -1;
                    break;
                case 1:
                    if (ch == 'a')
                        state = 0;
                    else if (ch == '/')
                        state = 1;
                    else if (ch == '*')
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
                        state = 0;
                    else if (ch == '*')
                        state = 3;
                    else 
                        state = -1;
                    break;
            }
        }
        return state == 0 || state == 1;
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

        System.out.print("/****/:  ");
        System.out.println(scan("/****/") ? "ok" : "no");
        System.out.println();

        System.out.print("aaa/****/aa:  ");
        System.out.println(scan("aaa/****/aa") ? "ok" : "no");
        System.out.println();

        System.out.print("aa/*a*a*/:  ");
        System.out.println(scan("aa/*a*a*/") ? "ok" : "no");
        System.out.println();

        System.out.print("aaaaaa:  ");
        System.out.println(scan("aaaaaa") ? "ok" : "no");
        System.out.println();

        System.out.print("/*aa*/:  ");
        System.out.println(scan("/*aa*/") ? "ok" : "no");
        System.out.println();

        System.out.print("*/a:  ");
        System.out.println(scan("*/a") ? "ok" : "no");
        System.out.println();

        System.out.print("a/**/***a:  ");
        System.out.println(scan("a/**/***a") ? "ok" : "no");
        System.out.println();

        System.out.print("a/**/aa/***/a:  ");
        System.out.println(scan("a/**/aa/***/a") ? "ok" : "no");
        System.out.println();

        System.out.print("aaa**/a////**/aa:  ");
        System.out.println(scan("aaa**/a////**/aa") ? "ok" : "no");
        System.out.println();

        //non accettate
        System.out.print("*/*a:  ");
        System.out.println(scan("*/*a") ? "ok" : "no");
        System.out.println();

        System.out.print("aaa/*/aa:  ");
        System.out.println(scan("aaa/*/aa") ? "ok" : "no");
        System.out.println();

        System.out.print("a/**//***a:  ");
        System.out.println(scan("a/**//***a") ? "ok" : "no");
        System.out.println();

        System.out.print("aa/*aa:  ");
        System.out.println(scan("aa/*aa") ? "ok" : "no");
        System.out.println();

        System.out.print("/*/:  ");
        System.out.println(scan("/*/") ? "ok" : "no");
        System.out.println();

        System.out.print("aaaaa/*a:  ");
        System.out.println(scan("aaaaa/*a") ? "ok" : "no");
        System.out.println();

        System.out.print("/*:  ");
        System.out.println(scan("/*") ? "ok" : "no");
        System.out.println();
    }
}
