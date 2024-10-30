package vttp.batch5.sdf.task02;
import java.util.*;
import java.io.*;

//Find all open position given a file 
//evaluate the position and give it a score
public class Main {


	public static void main(String[] args) throws Exception {

		if (args.length < 1){
			System.out.println("Please input a File");
		}
		String fileloc = args[0];

		FileReader fr = new FileReader(fileloc);
		BufferedReader br = new BufferedReader(fr);
		String line;

		System.out.println("Procesing: :" + fileloc);
		System.out.println();
		System.out.println("Board:");

		int Row = 0;
		List<String> emptyPos = new ArrayList<>();
		String[][] board = new String[3][3];

		List<String[]> positions = new ArrayList<>();
		while((line = br.readLine()) != null){
			System.out.println(line);
			String[] ticline = line.split("");
			positions.add(ticline);
			for(int i=0;i<ticline.length;i++){
				if(ticline[i].equalsIgnoreCase(".")){
					String tempString = String.valueOf(Row)+","+String.valueOf(i);
					emptyPos.add(tempString);
				}
			}
			for(int col=0;col<ticline.length;col++){
				board[Row][col] = ticline[col];
			}
			Row++;
		}
		String[][] boardclone = null;
		List<String> Utility = new ArrayList<>();
		System.out.println();
		System.out.println("------------------------------------");

		for(String entry:emptyPos){
			//create a board clone copy
			boardclone = copy(board,3);
			String[] coordinates = entry.split(",");
			String y = coordinates[0];
			String x = coordinates[1];
			boardclone[Integer.parseInt(y)][Integer.parseInt(x)] = "X";

			//evaluate
			int util = calculateWinner(boardclone);

			Utility.add(entry+","+util);
		}
		for(String s:Utility){
			String[] finalstep = s.split(",");
			System.out.println("y="+finalstep[0]+", x="+finalstep[1]+", utility="+finalstep[2]);
		}

		// for (String[] x : boardclone)
		// {
		//    for (String y : x)
		//    {
		// 		System.out.print(y + " ");
		//    }
		//    System.out.println();
		// }
		// for (String[] x : board)
		// {
		//    for (String y : x)
		//    {
		// 		System.out.print(y + " ");
		//    }
		//    System.out.println();
		// }

		// for(String s:emptyPos){
		// 	System.out.println(s);
		// }
	}
	public static String[][] copy(String[][] matrix, int n) 
	{
		String[][] out = new String[n][n];
		for (int i = 0; i < n; i++) 
		  for (int j = 0; j < n; j++) {
		   if(matrix[i][j] != null) {
			   String cp = new String(matrix[i][j]);
			   out[i][j] = cp;
		   }
		  }
		return out;
	}
	public static  int calculateWinner(String[][] board) {//should return 1 -1 or 0
		int boardWidth = 3;
		//check X
        // Check rows
        for (int row = 0; row < boardWidth; row++) {
            if (!board[row][0].equals(".") && board[row][0].equalsIgnoreCase(board[row][1]) && board[row][1].equalsIgnoreCase(board[row][2]) && board[row][0].equalsIgnoreCase("X") && board[row][1].equalsIgnoreCase("X") && board[row][2].equalsIgnoreCase("X")) {
                return 1;
            }
        }
        // Check columns
        for (int col = 0; col < boardWidth; col++) {
            if (!board[0][col].equals(".")&& board[0][col].equalsIgnoreCase(board[1][col]) && board[1][col].equalsIgnoreCase(board[2][col]) && board[0][col].equalsIgnoreCase("X") && board[1][col].equalsIgnoreCase("X") && board[2][col].equalsIgnoreCase("X")) {
                return 1;
            }
        }
        // Check diagonals
        if (!board[0][0].equals(".") && !board[1][1].equals(".") && !board[2][2].equals(".")
		 && board[0][0].equalsIgnoreCase(board[1][1]) && board[1][1].equalsIgnoreCase(board[2][2])
		 && board[0][0].equalsIgnoreCase("X") && board[1][1].equalsIgnoreCase("X")&& board[2][2].equalsIgnoreCase("X")) {
            return 1;
        }
        if (!board[0][2].equals(".") && !board[1][1].equals(".") && !board[2][0].equals(".")
		&& board[0][2].equalsIgnoreCase(board[1][1]) && board[1][1].equalsIgnoreCase(board[2][0])
		&& board[0][2].equalsIgnoreCase("X") && board[1][1].equalsIgnoreCase("X")&& board[2][0].equalsIgnoreCase("X")) {
            return 1;
        }

		//check O//horizontal
		for(int row = 0; row < boardWidth; row++){
		if( (board[row][0].equals(".") && board[row][1].equalsIgnoreCase(board[row][2]) && board[row][1].equalsIgnoreCase("O")&& board[row][2].equalsIgnoreCase("O"))
		|| (board[row][1].equals(".") && board[row][0].equalsIgnoreCase(board[row][2]) && board[row][0].equalsIgnoreCase("O")&& board[row][2].equalsIgnoreCase("O"))
		|| (board[row][2].equals(".") && board[row][0].equalsIgnoreCase(board[row][1]) && board[row][0].equalsIgnoreCase("O")&& board[row][1].equalsIgnoreCase("O")))
		return -1;
		}

		for(int col = 0; col < boardWidth; col++){
			if( (board[0][col].equals(".") && board[1][col].equalsIgnoreCase(board[2][col]) && board[1][col].equalsIgnoreCase("O")&& board[2][col].equalsIgnoreCase("O"))
			|| (board[1][col].equals(".") && board[0][col].equalsIgnoreCase(board[2][col]) && board[0][col].equalsIgnoreCase("O")&& board[2][col].equalsIgnoreCase("O"))
			|| (board[2][col].equals(".") && board[0][col].equalsIgnoreCase(board[1][col]) && board[0][col].equalsIgnoreCase("O")&& board[1][col].equalsIgnoreCase("O")))
			return -1;
		}
		if( (board[0][0].equals(".") && board[1][1].equalsIgnoreCase(board[2][2]) && board[1][1].equalsIgnoreCase("O")&& board[2][2].equalsIgnoreCase("O"))
			|| (board[1][1].equals(".") && board[0][0].equalsIgnoreCase(board[2][2]) && board[0][0].equalsIgnoreCase("O")&& board[2][2].equalsIgnoreCase("O"))
			|| (board[2][2].equals(".") && board[0][0].equalsIgnoreCase(board[1][1]) && board[0][0].equalsIgnoreCase("O")&& board[1][1].equalsIgnoreCase("O"))){
				return -1;
			}
		if( (board[0][2].equals(".") && board[1][1].equalsIgnoreCase(board[2][0]) && board[1][1].equalsIgnoreCase("O")&& board[2][0].equalsIgnoreCase("O"))
			|| (board[1][1].equals(".") && board[0][2].equalsIgnoreCase(board[2][0]) && board[0][2].equalsIgnoreCase("O")&& board[2][0].equalsIgnoreCase("O"))
			|| (board[2][0].equals(".") && board[0][2].equalsIgnoreCase(board[1][1]) && board[0][2].equalsIgnoreCase("O")&& board[1][1].equalsIgnoreCase("O"))){
				return -1;
			}

        // No winner
        return 0;
    }

}
