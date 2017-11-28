public class Connect4Grid2DArray implements Connect4Grid{

	private final int COLUMN_SIZE = 7;
	private final int ROW_SIZE = 6;
	private final int BLANK = ' ';				//set as public for blank to be used in main
	private final int ROWS_TO_WIN = 4;
	private char[][] board;
	private char championPiece;

	Connect4Grid2DArray()
	{
		board = new char[ROW_SIZE][COLUMN_SIZE];
		emptyGrid();
		championPiece=BLANK;
	}

	public String toString()
	{
		return getClass().getName();		
	}

	public void printBoard()
	{
		for (int index = 0; index<this.board.length; index++ )
		{
			System.out.print("|");
			for ( int index2 = 0; index2 < this.board[index].length; index2++)
			{
				System.out.print(this.board[index][index2]+"|");
			}
			System.out.println();
		}
		System.out.println("---------------");
		System.out.println(" 1 2 3 4 5 6 7 ");
	}

	@Override
	public void emptyGrid() {
		for (int index = 0; index<this.board.length; index++ )
		{
			for ( int index2 = 0; index2 < this.board[index].length; index2++)
			{
				this.board[index][index2]=BLANK;
			}
		}
	}

	@Override
	public boolean isValidColumn(int column) {
		boolean isValid = false;	
		if ( column >= 1 && column <= 7 )
		{
			isValid = true;
		}
		return isValid;
	}

	@Override
	public boolean isColumnFull(int column) {

		boolean full = true;

		if (column>=1&&column<=7)
		{
			for ( int index = ROW_SIZE-1; index>=0; index-- )
			{
				if ( board[index][column-1] == BLANK )
				{
					full = false;
				}
			}
		}
		return full;
	}

	@Override
	public void dropPiece(ConnectPlayer player, int column) {

		if ( player!= null&&column>=1&&column<=7)
		{
			char currentPiece = player.getPiece();
			boolean dropped = false;
			int index = ROW_SIZE-1;

			while (!dropped && index>=0)
			{
				if ( board[index][column-1] == BLANK )
				{
					board[index][column-1] = currentPiece;
					dropped = true;
				}
				index--;
			}
		}
	}

	@Override
	public boolean didLastPieceConnect4() 
	{
		boolean win = false;
//check horizontal
		for (int index = 0; index<this.board.length; index++ )
		{
			for ( int index2 = 0; index2 <= this.board[index].length-ROWS_TO_WIN; index2++)
			{
				if (this.board[index][index2]!=BLANK)
				{
					if ((this.board[index][index2]==this.board[index][index2+1])
							&&(this.board[index][index2]==this.board[index][index2+2])
							&&(this.board[index][index2]==this.board[index][index2+3]))
					{
						win = true;
						championPiece = this.board[index][index2];
					}
				}
			}
		}

		//check diagonal down to right
		for ( int index = 0; index<=this.board.length-ROWS_TO_WIN; index++)
		{
			for ( int index2 = 0; index2 <=this.board[index].length-ROWS_TO_WIN; index2++)
			{
				if (this.board[index][index2]!=BLANK)
				{
					if ((this.board[index][index2]==this.board[index+1][index2+1])
							&&(this.board[index][index2]==this.board[index+2][index2+2])
							&&(this.board[index][index2]==this.board[index+3][index2+3]))
					{
						win = true;
						championPiece = this.board[index][index2];
					}
				}
			}
		}

		//check diagonal up to left
		for ( int index = 0; index<=this.board.length-ROWS_TO_WIN; index++ )
		{
			for ( int index2 = COLUMN_SIZE-1; index2 >=this.board[index].length-ROWS_TO_WIN; index2-- )
			{
				if (this.board[index][index2]!=BLANK)
				{
					if ((this.board[index][index2]==this.board[index+1][index2-1])
							&&(this.board[index][index2]==this.board[index+2][index2-2])
							&&(this.board[index][index2]==this.board[index+3][index2-3]))
					{
						win = true;
						championPiece = this.board[index][index2];
					}
				}
			}
		}

		//check vertical
		for ( int index2=0; index2<COLUMN_SIZE; index2++)
		{
			for ( int index = this.board.length-1; index>=this.board.length-ROWS_TO_WIN; index--)
			{
				if ( this.board[index][index2]!=BLANK)
				{
					if ((this.board[index][index2]==this.board[index-1][index2])&&(this.board[index][index2]==this.board[index-2][index2])
							&&(this.board[index][index2]==this.board[index-3][index2]))
					{
						win = true;
						championPiece = this.board[index][index2];
					}
				}
			}
		}
		return win;
	}

	//display winner in ai mode 
	public void getResult(ConnectPlayer player, ConnectPlayer player2)
	{
		if (player!=null&&player2!=null)
		{
			if (championPiece==BLANK) System.out.println("The match is a draw");
			else if (championPiece==player.getPiece()) System.out.println("The winner is player");
			else if (championPiece==player2.getPiece()) System.out.println("The winner is AI");
		}
	}

	//display winner in pvp mode 
	public void getWinner(ConnectPlayer player, ConnectPlayer player2)
	{
		if(player!=null&&player2!=null)
		{
			if (championPiece==BLANK) System.out.println("The match is a draw");
			else if (championPiece==player.getPiece()) System.out.println("The winner is player");
			else if (championPiece==player2.getPiece()) System.out.println("The winner is player2");
		}
	}

	@Override //check if it is a tie 
	public boolean isGridFull() {
		boolean full = true;

		for (int index = 0; index<this.board.length; index++ )
		{
			for ( int index2 = 0; index2 < this.board[index].length; index2++)
			{
				if (this.board[index][index2]==BLANK)
				{
					full = false;
				}
			}
		}
		return full;
	}


}
