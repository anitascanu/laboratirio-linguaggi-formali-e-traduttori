import java.io.*; 
import java.util.*;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';
    
    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++;
            readch(br);
        }
        
        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;

            case '(':
                peek = ' ';
                return Token.lpt;

            case ')':
                peek = ' ';
                return Token.rpt;

            case '{':
                peek = ' ';
                return Token.lpg;

            case '}':
                peek = ' ';
                return Token.rpg;

            case '+':
                peek = ' ';
                return Token.plus;

            case '-':
                peek = ' ';
                return Token.minus;

            case '*':
                peek = ' ';
                return Token.mult;

            case '/':
                readch(br);
                if (peek == '/') { //line comment
                    do {
                        readch(br);
                    } while ((peek != '\n') && (peek != (char)-1));
                    peek = ' ';
                    return lexical_scan(br);
                }
                else if (peek == '*'){ //block comment
                    readch(br);
                    while (true) {
                        if (peek == '*') {
                            readch(br);
                            if (peek == '/') { //check if the comment is closed
                                peek = ' ';
                                return lexical_scan(br);
                            }
                        } else if (peek == '\n') {
                            line++;
                            readch(br);
                        } else if (peek == (char)-1) {
                            System.err.println("Error: comment not closed");
                            return null;
                        } else {
                            readch(br);
                        }
                    }
                }
                else {
                    return Token.div;
                }

            case ';':
                peek = ' ';
                return Token.semicolon;

            case ',':
                peek = ' ';
                return Token.comma;

            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } 
                else {
                    System.err.println("Erroneous character" + " after &: "  + peek);
                    return null;
                }

            case '|':
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                }
                else {
                    System.err.println("Erroneous character" + " after |: "  + peek);
                    return null;
                }

            case '<':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.le;
                }
                else if (peek == '>'){
                    peek = ' ';
                    return Word.ne;
                }
                else {
                    return Word.lt;
                }

            case '>':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.ge;
                }
                else {
                    return Word.gt;
                }

            case '=':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.eq;
                }
                else {
                    System.err.println("Erroneous character" + " after =: "  + peek);
                    return null;
                }

            case (char)-1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek) || peek == '_') {
                    String word = Character.toString(peek);
                    boolean allUnderscore = false;
                    if (peek == '_') allUnderscore = true;
                    readch(br);
                    while (Character.isDigit(peek) || Character.isLetter(peek) || peek == '_'){
                        if (Character.isDigit(peek) || Character.isLetter(peek)) allUnderscore = false;
                        word = word + Character.toString(peek);
                        readch(br);
                    }
                    switch (word) {
                        case "assign":
                            return Word.assign;
                        case "to":
                            return Word.to;
                        case "if":
                            return Word.iftok;
                        case "else":
                            return Word.elsetok;
                        case "while":
                            return Word.whiletok;
                        case "begin":
                            return Word.begin;
                        case "end":
                            return Word.end;
                        case "read":
                            return Word.read;
                        case "print":
                            return Word.print;
                        default:
                            if (allUnderscore == false){
                                return new Word(Tag.ID, word);
                            }
                            else {
                                System.err.println("Error: identifier cannot be composed only by underscore" );
                                return null;
                            }
                    }
                } 
                
                else if (Character.isDigit(peek)) {
                    String number = Character.toString(peek);
                    readch(br);
                    if (number.equals("0")){
                        if (peek >= '1' && peek <= '9'){
                            System.err.println("Erroneous character" + " after 0: "  + peek);
                            return null;
                        }
                    }
                    else{
                        while(Character.isDigit(peek)){
                            number = number + Character.toString(peek);
                            readch(br);
                        }
                    }
                    return new NumberTok(Integer.parseInt(number));
                }
                
                else {
                        System.err.println("Erroneous character: " + peek );
                        return null;
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "prova.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
                br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
