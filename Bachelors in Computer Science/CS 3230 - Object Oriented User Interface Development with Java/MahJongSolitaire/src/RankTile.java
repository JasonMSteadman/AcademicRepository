public abstract class RankTile extends Tile 
{
	
	//Rank of tile (1-9)
	protected int	rank;
	
	//Basic constructor
	public RankTile(int rank)
	{
		this.rank = rank;
	}
	
	
	//Check to see if other has the same rank
	public boolean matches(Tile other)
	{
		if (!super.matches(other))
			return false;
	
		try
		{
			//Cast other back to Fraction
			RankTile temp = (RankTile)other;
			return this.rank == temp.rank;
		}
		catch(ClassCastException e)
		{
			//If unable to cast other
			return false;
		}
	}
}
