import java.util.ArrayList;
import java.util.Scanner;

/*
 * 
 * Created by Adolfo Segura
 * 
 */

public class InitializeBoard {

	char[][] startBoard = new char[6][];
	char[][] winBoard = new char[6][];
	Scanner input = new Scanner(System.in);
	
	public InitializeBoard() {
		startBoard[0] = new char[1];
		startBoard[1] = new char[2];
		startBoard[2] = new char[3];
		startBoard[3] = new char[4];
		startBoard[4] = new char[5];
		startBoard[5] = new char[6];
		
		winBoard[0] = new char[1];
		winBoard[1] = new char[2];
		winBoard[2] = new char[3];
		winBoard[3] = new char[4];
		winBoard[4] = new char[5];
		winBoard[5] = new char[6];
	}
	
	public void getWinningBoard() {
		System.out.println("Please Enter the End board using P and O");
		
		
	Boolean moreInput = true;
	while(moreInput) {
		
		moreInput = false;

		for(int j = 0; j<6; j++) {
			 String line = input.nextLine();
			 if(line.length() != winBoard[j].length) {
				 j = 6;
				 moreInput = true;
				 System.out.println("Invalid!! try again");
				 System.out.println();
			 }
				
			 else {
				 for(int i = 0; i < winBoard[j].length; i++) {
					 if(line.charAt(i) != 'P' && line.charAt(i)!= 'O') {
						 moreInput = true;
					 }
					 winBoard[j][i] = line.charAt(i); 
					 }
				 }
			}
		}
	}

	public void getStartingBoard() {
		
		System.out.println("Enter the starting board ");
		System.out.println();
		
	Boolean inputFail = true;
	while(inputFail) {
			inputFail = false;

		for(int j = 0; j<6; j++) {
			 String line = input.nextLine();
			 if(line.length() != startBoard[j].length) {
				 j= 6;
				 inputFail = true;
				 System.out.println("Please try again");
				 System.out.println();
			 }
				
			 else {
				 for(int i = 0; i < startBoard[j].length; i++) {
					 if(line.charAt(i) != 'P' && line.charAt(i)!= 'O') {
						 inputFail = true;
					 }
					 startBoard[j][i] = line.charAt(i); 
					 
					 }
				 }
			}
		}
	}		

	public String toString() {
		String buildString = "The start board\n";
		for(int i = 0; i < startBoard.length; i++) {
			for(int j = 0; j < startBoard[i].length; j++)
				buildString += startBoard[i][j];
				buildString += "\n";	
		}
		buildString ="The end board\n";
		for(int i = 0; i < winBoard.length; i++) {
			for(int j = 0; j < winBoard[i].length; j++)
				buildString += winBoard[i][j];
				buildString += "\n";
		}
		return buildString;
	}
}

class PegBoard{
    public String[][] sBoard;
    public int nPegs = 0;

    public PegBoard(String[][] board) {
        this.sBoard = board;
        for (int i = 0; i < board.length; i ++){
        	for (int j = 0; j < board[i].length; j++) {
        		if (board[i][j].equalsIgnoreCase("P"))
        			this.nPegs++;
        	}
        }	
    }
    
    public PegBoard(PegBoard oldBoard) {
    	String[][] tempBoard = oldBoard.getBoard();
    	
    	sBoard = new String[tempBoard.length][];
    	
    	for(int i = 0; i < tempBoard.length; i++) {
    		sBoard[i] = new String[tempBoard[i].length];
    		for(int j = 0; j < tempBoard[i].length; j++) {
    			sBoard[i][j] = tempBoard[i][j];
    		}
    	}
    	
    	nPegs = (oldBoard.getNumPegs());
    }

    public boolean move(String move, int i, int j) {
        boolean validMove = false;
        switch (move) {
            case "Up":
                sBoard[i][j] = "O";
                sBoard[i-1][j] = "O";
                sBoard[i-2][j] = "P";
                validMove = true;
                break;
            case "Left":
                sBoard[i][j] = "O";
                sBoard[i][j-1] = "O";
                sBoard[i][j-2] = "P";
                validMove = true;
                break;
            case "Right":
                sBoard[i][j] = "O";
                sBoard[i][j+1] = "O";
                sBoard[i][j+2] = "P";
                validMove = true;
                break;
            case "DownRight":
                sBoard[i][j] = "O";
                sBoard[i+1][j+1] = "O";
                sBoard[i+2][j+2] = "P";
                validMove = true;
                break;
            case "UpLeft":
                sBoard[i][j] = "O";
                sBoard[i-1][j-1] = "O";
                sBoard[i-2][j-2] = "P";
                validMove = true;
                break;
            case "Down":
                sBoard[i][j] = "O";
                sBoard[i+1][j] = "O";
                sBoard[i+2][j] = "P";
                validMove = true;
                break;
            default:
        }

        if (validMove) {
        	nPegs--;
        }
        return validMove;
    }

    public ArrayList<String> getMoves(int i, int j) {
        ArrayList<String> temp = new ArrayList<>();

        if (isOnBoard(i,j) && sBoard[i][j].equals("P")) {
            if (isOnBoard(i - 2, j) && isOnBoard(i - 1, j)) {
                if (sBoard[i - 2][j].equals("O") && sBoard[i - 1][j].equals("P")) {
                    temp.add("Up");
                }
            }

            if (isOnBoard(i, j - 1) && isOnBoard(i, j - 2)) {
                if (sBoard[i][j - 2].equals("O") && sBoard[i][j - 1].equals("P")) {
                    temp.add("Left");
                }
            }

            if (isOnBoard(i,j+1) && isOnBoard(i,j+2)) {
                if (sBoard[i][j+2].equalsIgnoreCase("O") && sBoard[i][j+1].equalsIgnoreCase("P")) {
                    temp.add("Right");
                }
            }

            if (isOnBoard(i+1,j+1) && isOnBoard(i+2,j+2)) {
                if (sBoard[i+2][j+2].equalsIgnoreCase("O") && sBoard[i+1][j+1].equalsIgnoreCase("P")) {
                    temp.add("DownRight");
                }
            }

            if (isOnBoard(i-1,j-1) && isOnBoard(i-2,j-2)) {
                if (sBoard[i-2][j-2].equalsIgnoreCase("O") && sBoard[i-1][j-1].equalsIgnoreCase("P")) {
                    temp.add("UpLeft");
                }
            }

            if (isOnBoard(i+1,j) && isOnBoard(i+2,j)) {
                if (sBoard[i+2][j].equalsIgnoreCase("O") && sBoard[i+1][j].equalsIgnoreCase("P")) {
                    temp.add("Down");
                }
            }
        }

        return temp;
    }

    public boolean moveUp(int i, int j) {
        if (isOnBoard(i - 2, j) && isOnBoard(i - 1, j) && isOnBoard(i,j)) {
            if (sBoard[i - 2][j].equalsIgnoreCase("O") && sBoard[i - 1][j].equalsIgnoreCase("P") && sBoard[i][j].equalsIgnoreCase("P")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean moveDown(int i, int j) {
        if (isOnBoard(i+1,j) && isOnBoard(i+2,j) && isOnBoard(i,j)) {
            if (sBoard[i+2][j].equals("O") && sBoard[i+1][j].equals("P") && sBoard[i][j].equals("P")) {
                return true;
            }
        }
        return false;
    }

    public boolean moveLeft(int i, int j) {
        if (isOnBoard(i, j - 1) && isOnBoard(i, j - 2) && isOnBoard(i,j)) {
            if (sBoard[i][j - 2].equals("O") && sBoard[i][j - 1].equals("P") && sBoard[i][j].equals("P")){
                return true;
            }
        }
        return false;
    }

    public boolean moveRight(int i, int j) {
        if (isOnBoard(i,j+1) && isOnBoard(i,j+2) && isOnBoard(i,j)) {
            if (sBoard[i][j+2].equals("O") && sBoard[i][j+1].equals("P") && sBoard[i][j].equals("P")){
                return true;
            }
        }
        return false;
    }

    public boolean moveDownRight(int i, int j) {
        if (isOnBoard(i+1,j+1) && isOnBoard(i+2,j+2) && isOnBoard(i,j)) {
            if (sBoard[i+2][j+2].equals("O") && sBoard[i+1][j+1].equals("P") && sBoard[i][j].equals("P")){
                return true;
            }
        }
        return false;
    }

    public boolean moveUpLeft(int i, int j){
        if (isOnBoard(i-1,j-1) && isOnBoard(i-2,j-2) && isOnBoard(i,j)) {
            if (sBoard[i-2][j-2].equals("O") && sBoard[i-1][j-1].equals("P") && sBoard[i][j].equals("P")){
                return true;
            }
        }
        return false;
    }

    private boolean isOnBoard(int i,int j) {
        if (0 <= i && i < sBoard.length && 0 <= j && j < sBoard[i].length) {
            return true;
        }
        else {
            return false;
        }
    }

    public String[][] getBoard() {
        return sBoard;
    }
    
    public int getNumPegs() {
    	return nPegs;
    }
    
    public void setNumPegs(int n){
		nPegs = n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sBoard.length; i++) {
            for (int j = 0; j < sBoard[i].length; j++) {
                sb.append("[" + sBoard[i][j] + "]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
