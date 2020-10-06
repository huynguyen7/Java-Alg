import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class App {
	public static void main(String[] args) {
		System.out.println("----GAMEOFLIFE----");
		System.out.println("0 is dead, 1 is alive.");
		System.out.println("Type '1' to continue.");

		int dimension = Integer.parseInt(args[0]);
		App app = new App(dimension);

		//int[][] customBoard = new int[5][5];
		//customBoard[2][1] = 1;
		//customBoard[2][2] = 1;
		//customBoard[2][3] = 1;
		//App app = new App(customBoard);
	}

	// 0 is dead, 1 is alive.
	private int[][] board;
	
	public App(int dimension) {
		board = new int[dimension][dimension];
		initBoard();
		System.out.println();
		for(int[] arr: board)
			System.out.println(Arrays.toString(arr));
		start();
	}

	public App(int[][] customBoard) {
		this.board = customBoard;
		System.out.println();
		for(int[] arr: board)
			System.out.println(Arrays.toString(arr));
		start();
	}

	private void start() {
		Scanner sc = new Scanner(System.in);
		int choice = 1; // 1 is continue.
		while(true) {
			System.out.println("--------");
			System.out.print("Choice: ");
			choice = sc.nextInt();
			
			System.out.println();
			if(choice != 1) break;

			int numThreads = 1;
			while(true) {
				System.out.print("Enter number of threads: ");
				numThreads = sc.nextInt();
				if(board.length % numThreads == 0) break;
				else System.out.println("Cannot divide number of threads evenly.");
			}
			
			System.out.println("numThreads: " + numThreads);
			
			long startTime = System.nanoTime();
			if(numThreads == 1) {
				int[][] tmp = new int[board.length][board[0].length];
				validateBoard(tmp); // single thread validation 
			} else if (numThreads > 1) {
				int[][] tmp = new int[board.length][board[0].length];
				validateBoardMultiThreading(tmp, numThreads); // multithreading version.
			}
			long endTime = System.nanoTime();
			System.out.printf("Total time take: %d ms\n\n", endTime - startTime);
			
			// Comment these 2 lines to prevent printing out
			for(int[] arr: board)
				System.out.println(Arrays.toString(arr));
		}

		System.out.println("\nGame stopped...");
	}

	// multi threads
	private void validateBoardMultiThreading(int[][] tmp, int numThreads) {
		int chunk = (int) board.length / numThreads;
		int counter = 0;
		for(int i = 1; i <= numThreads; ++i) {
			if(counter >= board.length) break;
			int end = counter + chunk < board.length ? counter + chunk : board.length - 1;
			Thread th = new Thread(new MyRunnable(board, tmp, counter, 
						counter + chunk < board.length ? counter + chunk : board.length - 1));
			th.start();
			counter = i * chunk + 1;
		}
		
		board = tmp;
	}
	
	// single thread
	private void validateBoard(int[][] tmp) {
		for(int i = 0; i < board.length; ++i) {
			for(int j = 0; j < board[i].length; ++j) {
				if(cellCanReproduce(i, j)) tmp[i][j] = 1;
				else tmp[i][j] = 0;
			}
		}

		board = tmp;
	}


	// a neighbour is a cell that borders
	// on it horizontallly, vertically, or diagonally
	private boolean cellCanReproduce(int row, int col) {
		int countLiveNeighbors = 0;

		// check vertical
		if(row > 0 && isAlive(row - 1, col)) countLiveNeighbors++;
		if(row < board.length - 1 && isAlive(row + 1, col)) countLiveNeighbors++;

		// check horizontal
		if(col > 0 && isAlive(row, col - 1)) countLiveNeighbors++;
		if(col < board[row].length - 1 && isAlive(row, col + 1)) countLiveNeighbors++;

		// check diagonal
		if(row > 0 && col > 0 && isAlive(row - 1, col - 1)) countLiveNeighbors++;
		if(row > 0 && col < board[row].length - 1 && isAlive(row - 1, col + 1)) countLiveNeighbors++;
		if(row < board.length - 1 && col > 0 && isAlive(row + 1, col - 1)) countLiveNeighbors++;
		if(row < board.length - 1 && col < board[row].length - 1 && isAlive(row + 1, col + 1)) countLiveNeighbors++;

		if(isAlive(row, col)) { // current cell is alive.
			// validate all constraints.
			if(countLiveNeighbors < 2 || countLiveNeighbors > 3)
				return false;
			return true;
		} else { // current cell is dead.
			if(countLiveNeighbors == 3) return true;
			return false;
		}
	}

	private boolean isAlive(int row, int col) {
		return board[row][col] == 1;
	}

	// create random value for each cell in board.
	private void initBoard() {
		Random rand = new Random();
		for(int i = 0; i < board.length; ++i) {
			for(int j = 0; j < board[i].length; ++j)
				board[i][j] = rand.nextInt(2);
		}
	}
}
