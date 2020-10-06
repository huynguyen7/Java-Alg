public class MyRunnable implements Runnable{
	
	private int[][] board;
	private int[][] tmp;
	private int startRow;
	private int endRow;
	
	public MyRunnable(int[][] board, int[][] tmp, int startRow, int endRow) {
		this.board = board;
		this.tmp = tmp;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	private void validateBoard() {
		for(int i = startRow; i <= endRow; ++i) {
			for(int j = 0; j < board[i].length; ++j) {
				if(cellCanReproduce(i, j)) tmp[i][j] = 1;
				else tmp[i][j] = 0;
			}
		}
	}

	private boolean cellCanReproduce(int row, int col) {
		int countLiveNeighbors = 0;
		
		if(row > 0 &&  isAlive(row - 1, col)) countLiveNeighbors++;
		if(row < board.length - 1 && isAlive(row + 1, col)) countLiveNeighbors++;

		if(col > 0 && isAlive(row, col - 1)) countLiveNeighbors++;
		if(col < board[row].length - 1 && isAlive(row, col + 1)) countLiveNeighbors++;
		
		if(row > 0 && col > 0 && isAlive(row - 1, col - 1)) countLiveNeighbors++;
		if(row > 0 && col < board[row].length - 1 && isAlive(row - 1, col + 1)) countLiveNeighbors++;
		if(row < board.length - 1 && col > 0 && isAlive(row + 1, col - 1)) countLiveNeighbors++;
		if(row < board.length - 1 && col < board[row].length - 1 && isAlive(row + 1, col + 1)) countLiveNeighbors++;

		if(isAlive(row, col)) { // current cell is alive.
			if(countLiveNeighbors < 2 || countLiveNeighbors > 3) return false;
			return true;
		} else {
			if(countLiveNeighbors == 3) return true;
			return false;
		}
	}

	private boolean isAlive(int row, int col) {
		return board[row][col] == 1;
	}

	public void run() {
		validateBoard();
	}
}
