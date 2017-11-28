import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer{
	
	private Random generator;
	
	C4RandomAIPlayer (char piece)
	{
		super (piece);
		this.generator = new Random();
	}
	
	//random move for ai 
	public int makeMove ()
	{
		int move = generator.nextInt(6)+1;
		return move;
	}
	
	
}
