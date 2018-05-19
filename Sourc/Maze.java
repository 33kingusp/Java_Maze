import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze
{
	private Vector2Int size;
	private int[][] maze;
	private List<Vector2Int> startList;
	private List<Vector2Int> currentWallList;

	public Maze(int x, int y)
	{
		if (x <= 0) x = 2;
		if (y <= 0) y = 2;

		size = new Vector2Int(x * 2 + 1, y * 2 + 1);
		CreateMaze();
	}

	private void CreateMaze()
	{
		Random rand = new Random();
		maze = new int[size.x][size.y];
		startList = new ArrayList<Vector2Int>();
		currentWallList = new ArrayList<Vector2Int>();

		for (int y = 0; y < size.y; y++)
		{
			for (int x = 0; x < size.x; x++)
			{
				if (x == 0 || y == 0 || x == size.x - 1 || y == size.y - 1)
					maze[x][y] = 1;
				else
					maze[x][y] = 0;
				if (x % 2 == 0 && y % 2 == 0)
					startList.add(new Vector2Int(x, y));
			}
		}
	}

	private void CreateWall(int x, int y)
	{
		
	}

	private boolean IsCurrentWall(int x, int y)
	{
		for (int n = 0; n < currentWallList.size(); n++)
		{
			Vector2Int v = currentWallList.get(n);
			if (v.x == x && v.y ==y)
				return true;
		}
		return false;
	}

	public void Print()
	{
		for (int y = 0; y < size.y; y++)
		{
			for (int x = 0; x < size.x; x++)
				if (maze[x][y] == 0) System.out.print(" ");
				else System.out.print("*");
			System.out.println();
		}
	}
}
