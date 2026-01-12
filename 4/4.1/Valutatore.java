import java.io.*; 

public class Valutatore {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Valutatore(Lexer l, BufferedReader br) { 
        lex = l; 
        pbr = br;
        move(); 
    }
   
    void move() { 
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) { 
        throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
        if (look.tag == t) {
            if (look.tag != Tag.EOF) move();
        } else error("syntax error");
    }

    public void start() { 
        switch (look.tag){
            case '(':
            case Tag.NUM: {
                int expr_val;
                expr_val = expr();
                match(Tag.EOF);
                System.out.println(expr_val);
                break;
            }
            default:
                error("error in start");
        }
    }

    private int expr() { 
        int term_val, exprp_val;
        switch (look.tag){
            case '(':
            case Tag.NUM:{
                term_val = term();
                exprp_val = exprp(term_val);
                return exprp_val;
            }
            default:
                error("error in expr");
                return 0;
        }
       
    }

    private int exprp(int exprp_i) {
        int term_val, exprp_val;
        switch (look.tag) {
        case '+':
            match('+');
            term_val = term();
            exprp_val = exprp(exprp_i + term_val);
            return(exprp_val);
        case '-':
            match('-');
            term_val = term();
            exprp_val = exprp(exprp_i - term_val);
            return(exprp_val);
        case Tag.EOF:
        case ')':
            return exprp_i;
        default:
            error("error in exprp");
            return 0;
        }
    }

    private int term() { 
        int fact_val, termp_val;
        switch (look.tag){
            case '(':
            case Tag.NUM:
                fact_val = fact();
                termp_val = termp(fact_val);
                return termp_val;
            default:
                error("error in term");
                return 0;
        }
    }
    
    private int termp(int termp_i) { 
        int fact_val, termp_val;
        switch (look.tag) {
            case '*':
                match('*');
                fact_val = fact();
                termp_val = termp(termp_i * fact_val);
                return termp_val;
            case '/':
                match('/');
                fact_val = fact();
                termp_val = termp(termp_i / fact_val);
                return termp_val;
            case Tag.EOF:
            case ')':
            case '+':
            case '-':
                return termp_i;
            default:
                error("error in termp");
                return 0;
        }
    }
    
    private int fact() {
        switch (look.tag){
            case '(': {
                int expr_val;
                match('(');
                expr_val = expr();
                match(')');
                return expr_val;
            }
            case Tag.NUM: {
                int num_val;
                num_val = ((NumberTok)look).value;
                match(Tag.NUM);
                return num_val;
            }
            default:
                error("error in fact");
                return 0;
        }
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "prova.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Valutatore valutatore = new Valutatore(lex, br);
            valutatore.start();
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}