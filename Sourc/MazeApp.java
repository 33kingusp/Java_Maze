class MazeApp
{
	public static void main(String args[])
	{
		Vector2Int size = new Vector2Int(5, 5);
		if (args.length >= 1)
			size.x = size.y = Integer.parseInt(args[0]);
		if(args.length >= 2)	
			size.y = Integer.parseInt(args[1]);

		Maze maze = new Maze(size.x, size.y);
		maze.Print();

		System.exit(0);
	}
}
