import java.util.ArrayList;
import java.util.Stack;

/*
 * Created by Adolfo Segura
 * 
 */
public class Main {

   static String[][] upAndRight = {
            {"O"},
            {"P","P"},
            {"P","P","O"}
    };

   static String[][] leftAndUpRight = {
            {"O"},
            {"P","P"},
            {"O","P","P"}
    };

   static String[][] downAndDownRight = {
            {"P"},
            {"P","P"},
            {"O","P","O"}
    };

   static String[][] simpleBoard = {
            {"P"},
            {"P","O"},
            {"O","P","O"},
            {"P","P","O","P"}
    };

   static String[][] endSimpleBoard = {
            {"O"},
            {"P","O"},
            {"O","O","P"},
            {"P","O","O","P"}
    };
    
   static String[][] startBoardFinal19 = {
            {"P"},
            {"P","P"},
            {"O","P","P"},
            {"P","P","P","P"},
            {"P","P","P","P","P"},
            {"P","P","P","P","P","P"}
    };

    
   static String[][] startBoardFinal5 = {
            {"P"},
            {"P","P"},
            {"O","P","P"},
            {"P","O","P","P"},
            {"O","O","O","O","O"},
            {"O","O","O","O","O","O"}
    };

   static String[][] endBoardFinal5 = {
            {"O"},
            {"P","P"},
            {"O","O","O"},
            {"O","O","O","P"},
            {"O","O","O","O","O"},
            {"O","O","O","O","O","O"}
    };
    
   static String[][] endBoardFinal19 = {
            {"O"},
            {"O","O"},
            {"O","O","O"},
            {"O","O","O","O"},
            {"O","O","O","O","O"},
            {"O","O","O","P","O","O"}
     };
    
   static String[][] DD_LEFTStart = {
            {"P"},
            {"P","P"},
            {"P","P","P"},
            {"P","O","P","O"},
            {"O","O","O","O","O"},
            {"O","O","O","O","O","O"}
    };

   static String[][] DD_LEFTEnd = {
            {"P"},
            {"P","O"},
            {"P","P","O"},
            {"P","P","O","O"},
            {"O","O","O","O","O"},
            {"O","O","O","O","O","O"}
    };

    static NewGame startGame;

    public static void main(String[] args) {
        run();
    }
    
    static void run() {
        PegBoard sBoard = new PegBoard(upAndRight);
        PegBoard eBoard = new PegBoard(leftAndUpRight);

        startGame = new NewGame(sBoard,eBoard);
        
        if (startGame.solve()) {
            System.out.println("Solution Found");
        }
        else {
            System.out.println("No Solution Exists");
        }
    }
    
    static void testPegBoard(){
    	PegBoard board1 = new PegBoard(upAndRight);
    	PegBoard board2 = new PegBoard(leftAndUpRight);
    	
    	System.out.println(board1.toString());
    	System.out.println(board2.toString());
    	
    	board1.moveDown(0,0);
    	
    	System.out.println(board1.toString());
    	System.out.println(board2.toString());
    }

    static void Moves() {
        PegBoard pegBoard1 = new PegBoard(upAndRight);
        PegBoard pegBoard2 = new PegBoard(leftAndUpRight);

        System.out.println("Board Before: ");
        System.out.println(pegBoard1.toString());

        pegBoard1.move("Up", 2,0);
        System.out.println(pegBoard1.toString());

        pegBoard1.move("DownRight", 0,0);
        System.out.println(pegBoard1.toString());

        pegBoard1.move("Left", 2,2);
        System.out.println(pegBoard1.toString());

        System.out.println("Board Before: ");
        System.out.println(pegBoard2.toString());

        pegBoard2.move("UpLeft", 2,2);
        System.out.println(pegBoard2.toString());

        pegBoard2.move("Down", 0,0);
        System.out.println(pegBoard2.toString());

        pegBoard2.move("Right", 2,0);
        System.out.println(pegBoard2.toString());
    }

    static void testEquals(){
        PegBoard board1 = new PegBoard(upAndRight);
        PegBoard board2 = new PegBoard(leftAndUpRight);

        if (board1.equals(board2)) {
            System.out.println("works");
        }
        else {
            System.out.println("doesn't work");
        }
    }

    static void ValidMoves() {
    	
        PegBoard board1 = new PegBoard(upAndRight);
        System.out.println(board1.toString());

        ArrayList<String> tempList = board1.getMoves(2,0);
        for (String s : tempList) {
            System.out.println("Valid Moves: " + s);
        }

        //System.out.println();
        PegBoard board2 = new PegBoard(leftAndUpRight);
        System.out.println(board2.toString());

        tempList = board2.getMoves(2,2);
        for (String s : tempList) {
            System.out.println("Valid Moves: " + s);
        }

        System.out.println();
        PegBoard board3 = new PegBoard(downAndDownRight);
        System.out.println(board3.toString());

        tempList = board3.getMoves(0,0);
        for (String s : tempList) {
            System.out.println("Valid Moves: " + s);
        }
    }
}

class NewGame {
    PegBoard startBoard;
    PegBoard winBoard;
    Stack<String> pStack;
    int[] size;
    

    
    public NewGame(PegBoard start, PegBoard win) {
    	
        this.startBoard = start;
        this.winBoard = win;
        pStack = new Stack<>();

        size = new int[start.getBoard().length];
        for (int i = 0; i < start.getBoard().length; i++) {
            size[i] = start.getBoard()[i].length;
        }
    }

    public boolean solve() {
    	System.out.println(startBoard.toString());
    	System.out.println(winBoard);
    	
    	boolean done = rBT(startBoard); 
        
    	while(!pStack.isEmpty()) {
    		System.out.println(pStack.pop());
    	}
    	
    	return done;
    }

    public boolean rBT(PegBoard board) {
        for (int i = 0; i < size.length; i++) {
            for (int j = 0; j < size[i]; j++) {
            	System.out.println(board);
            	if (board.getNumPegs() < winBoard.getNumPegs()) {
            		return false;
            	}
                if (!board.equals(winBoard) && board.moveUp(i,j)) {
                	PegBoard tempBoard = new PegBoard(board);
                	tempBoard.move("Up",i, j);
                	pStack.push("SRC:(" + i + "," + j + ") DEST:(" + (i-2) + "," + j + ")");
                	if (tempBoard.equals(winBoard)) {               		
                		return true;
                	}
                	else {         		
                		rBT(tempBoard);
                		pStack.pop();
                	}
                }
                
                if (!board.equals(winBoard) && board.moveDown(i, j)) {
                	PegBoard tempBoard = new PegBoard(board);
                	tempBoard.move("Down",i, j);
                	pStack.push("SRC:(" + i + "," + j + ") DEST:(" + (i+2) + "," + j + ")");
                	if (tempBoard.equals(winBoard)) {               		
                		return true;
                	}
                	else {             		
                		rBT(tempBoard);
                		pStack.pop();
                	}
                }

                if (!board.equals(winBoard) && board.moveLeft(i,j)) {
                	PegBoard tempBoard = new PegBoard(board);
                	tempBoard.move("Left",i, j);
                	pStack.push("SRC:(" + i + "," + j + ") DEST:(" + i + "," + (j-2) + ")");
                	if (tempBoard.equals(winBoard)) {
                		return true;
                	}
                	else {
                		rBT(tempBoard);
                		pStack.pop();
                	}
                }

                if (!board.equals(winBoard) && board.moveRight(i,j)) {
                	PegBoard tempBoard = new PegBoard(board);
                	tempBoard.move("Right",i, j);
                	pStack.push("SRC:(" + i + "," + j + ") DEST:(" + i + "," + (j+2) + ")");
                	if (tempBoard.equals(winBoard))
                		return true;
                	else {
                		rBT(tempBoard);
                		pStack.pop();
                	}
                }

                if (!board.equals(winBoard) && board.moveDownRight(i,j)) {
                	PegBoard tempBoard = new PegBoard(board);
                	tempBoard.move("DownRight",i,j);
                	pStack.push("SRC:(" + i + "," + j + ") DEST:(" + (i+2) + "," + (j+2) + ")");
                	if (tempBoard.equals(winBoard)) {
                		return true;
                	}
                	else {
                		rBT(tempBoard);
                		pStack.pop();
                	}
                }

                if (!board.equals(winBoard) && board.moveUpLeft(i,j)) {
                	PegBoard tempBoard = new PegBoard(board);
                	tempBoard.move("UpLeft",i,j);
                	pStack.push(" SRC:(" + i + "," + j + ") DEST:(" + (i-2) + "," + (j-2) + ")");
                	if (tempBoard.equals(winBoard)) {
                		return true;
                	}
                	else {
                		rBT(tempBoard);
                		pStack.pop();
                	}
                }
            }
        }
        return false;
    }
}
