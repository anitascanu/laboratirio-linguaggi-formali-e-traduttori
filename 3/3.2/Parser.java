import java.io.*;

public class Parser {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser(Lexer l, BufferedReader br) {
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

    public void prog() {
        switch(look.tag){
            case Tag.ASSIGN:
            case Tag.PRINT:
            case Tag.READ:
            case Tag.WHILE: 
            case Tag.IF: 
            case '{':
                statlist();
                match(Tag.EOF);
                break;
            default:
                error("error in prog");
        }
    }

    private void statlist() {
        switch (look.tag){
            case Tag.ASSIGN:
            case Tag.PRINT:
            case Tag.READ:
            case Tag.WHILE: 
            case Tag.IF: 
            case '{':
                stat();
                statlistp();
                break;
            default:
                error("error in statlist");
        }
    }

    private void statlistp() {
        switch (look.tag) {
            case ';':
                match(Token.semicolon.tag);
                stat();
                statlistp();
                break;
            case Tag.EOF:
            case '}':
                break;
            default:
                error("error in statlistp");
        }
    }

    private void stat() {
        switch (look.tag){
            case Tag.ASSIGN: 
                match(Tag.ASSIGN);
                expr();
                match(Tag.TO);
                idlist();
                break;
            case Tag.PRINT:
                match(Tag.PRINT);
                match(Token.lpt.tag);
                exprlist();
                match(Token.rpt.tag);
                break;
            case Tag.READ: 
                match(Tag.READ);
                match(Token.lpt.tag);
                idlist();
                match(Token.rpt.tag);
                break;
            case Tag.WHILE:
                match(Tag.WHILE);
                match(Token.lpt.tag);
                bexpr();
                match(Token.rpt.tag);
                stat();
                break;
            case Tag.IF:
                match(Tag.IF);
                match(Token.lpt.tag);
                bexpr();
                match(Token.rpt.tag);
                stat();
                if_else_end();
                break;
            case '{': 
                match(Token.lpg.tag);
                statlist();
                match(Token.rpg.tag);
                break;
            default:
                error("error in stat");
        }
    }

    private void if_else_end() {
        switch (look.tag) {
            case Tag.ELSE:
                match(Tag.ELSE);
                stat();
                match(Tag.END);
                break;
            case Tag.END:
                match(Tag.END);
                break;
            default:
                error("error in if_else_end");
        }
    }

    private void idlist() {
        switch (look.tag){
            case Tag.ID:
                match(Tag.ID);
                idlistp();
                break;
            default:
                error("error in idlist");
        }
    }

    private void idlistp() {
        switch (look.tag){
            case ',':
                match(Token.comma.tag);
                match(Tag.ID);
                idlistp();
                break;
            case ';':
            case Tag.EOF:
            case ')':
            case Tag.END:
            case Tag.ELSE:
            case '}':
                break;
            default:
                error("error in idlistp");
        }
    }

    private void bexpr() {
        switch (look.tag){
            case Tag.RELOP:
                match(Tag.RELOP);
                expr();
                expr();
                break;
            default:
                error("error in bexpr");
        }
    }

    private void expr() {
        switch (look.tag){
            case '+':
                match(Token.plus.tag);
                match(Token.lpt.tag);
                exprlist();
                match(Token.rpt.tag);
                break;
            case '-':
                match(Token.minus.tag);
                expr();
                expr();
                break;
            case '*':
                match(Token.mult.tag);
                match(Token.lpt.tag);
                exprlist();
                match(Token.rpt.tag);
                break;
            case '/':
                match(Token.div.tag);
                expr();
                expr();
                break;
            case Tag.NUM:
                match(Tag.NUM);
                break;
            case Tag.ID:
                match(Tag.ID);
                break;
            default:
                error("error in expr");
        }
    }

    private void exprlist() {
        switch (look.tag){
            case '+':
            case '-':
            case '*':
            case '/':
            case Tag.NUM:
            case Tag.ID:
                expr();
                exprlistp();
                break;
            default:
                error("error in exprlist");
        }
    }

    private void exprlistp() {
        switch (look.tag){
            case ',':
                match(Token.comma.tag);
                expr();
                exprlistp();
                break;
            case ')':
                break;
            default:
                error("error in exprlistp");
        }
    }

		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "prova.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.prog();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}