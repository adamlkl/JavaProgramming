import java.util.Scanner;

public class Connect4Game {

	public static final char[] PIECE= {'O','X'};
	public static final char BLANK = ' ';
	public static void main(String[] args)
	{
		Scanner inputScanner = new Scanner ( System.in );
		boolean done = false;
		Connect4Grid2DArray theGame;
		C4RandomAIPlayer ai;
		C4HumanPlayer p1;
		C4HumanPlayer p2;
		int column;

		while (!done)
		{
			System.out.println("Enter the 'pvp' or 'ai' to begin or 'quit' to exit: ");
			{
				String reply = inputScanner.nextLine();
				if (reply.equalsIgnoreCase("ai"))
				{
					p1 = new C4HumanPlayer (PIECE[0]);
					ai = new C4RandomAIPlayer (PIECE[1]);
					theGame = new Connect4Grid2DArray();
					theGame.emptyGrid();
					char currentPiece=p1.getPiece();

					while ( !theGame.isGridFull() && !theGame.didLastPieceConnect4())
					{
						theGame.toString();
						theGame.printBoard();

						if ( currentPiece == PIECE[0])
						{
							System.out.println("Enter column number from 1 to 7:");
							column = inputScanner.nextInt();
							if (theGame.isValidColumn(column)&&!theGame.isColumnFull(column))
							{
								theGame.dropPiece(p1, column);
								currentPiece = (currentPiece==PIECE[0])?PIECE[1]:PIECE[0];
							}

							else 
							{
								System.out.println("Invalid column number.");
							}
						}

						else if ( currentPiece == PIECE[1])
						{
							System.out.println("AI move:");
							column = ai.makeMove();
							if (theGame.isValidColumn(column))
							{
								theGame.dropPiece(ai, column);
							}
							currentPiece = (currentPiece==PIECE[0])?PIECE[1]:PIECE[0];
						}

						else 
						{
							System.out.println("Invalid move, column does not exist");
						}
					}
					theGame.getResult(p1, ai);
				}

				else if (reply.equalsIgnoreCase("pvp"))
				{
					p1 = new C4HumanPlayer (PIECE[0]);
					p2 = new C4HumanPlayer (PIECE[1]);
					theGame = new Connect4Grid2DArray();
					theGame.emptyGrid();
					char currentPiece=p1.getPiece();
					while ( !theGame.isGridFull() && !theGame.didLastPieceConnect4())
					{
						theGame.toString();
						theGame.printBoard();
						System.out.println("Enter column number from 1 to 7:");
						column = inputScanner.nextInt();
						if (theGame.isValidColumn(column)&&!theGame.isColumnFull(column))
						{
							if ( currentPiece == p1.getPiece())
							{
								theGame.dropPiece(p1, column);
							}
							else 
							{
								theGame.dropPiece(p2, column);
							}
							currentPiece = (currentPiece==p1.getPiece())?p2.getPiece():p1.getPiece();
						}

						else 
						{
							System.out.println("Invalid move, column does not exist");
						}
					}
					theGame.getWinner(p1, p2);
				}

				else if ( reply.equalsIgnoreCase("quit"))
				{
					done = true;
					System.out.println("bye");
				}

				else 
				{
					System.out.println("Invalid input!!!");
				}
			}
		}
	}
}


