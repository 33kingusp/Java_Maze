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
		
		while (startList.size() > 0)
		{
			int n = rand.nextInt(startList.size());
			Vector2Int cell = startList.get(n);
			startList.remove(n);
			if (maze[cell.x][cell.y] == 0)
			{
				currentWallList.clear();
				CreateWall(rand, cell.x, cell.y);
			}
		}
	}

	private void CreateWall(Random rand, int x, int y)
	{
		List<Integer> dir = new ArrayList<Integer>();
		if (maze[x][y - 1] == 0 && !IsCurrentWall(x, y - 2))
			dir.add(0);
		if (maze[x + 1][y] == 0 && !IsCurrentWall(x + 2, y))
			dir.add(1);
		if (maze[x][y + 1] == 0 && !IsCurrentWall(x, y + 2))
			dir.add(2);
		if (maze[x - 1][y] == 0 && !IsCurrentWall(x - 2, y))
			dir.add(3);
		
		if (dir.size() > 0)
		{
			SetWall(x, y);
			
			boolean isPath = false;
			int n = rand.nextInt(dir.size());
			switch(dir.get(n))
			{
				case 0:
					isPath = (maze[x][y - 2] == 0);
					SetWall(x, --y);
					SetWall(x, --y);
					break;
				case 1:
					isPath = (maze[x + 2][y] == 0);
					SetWall(++x, y);
					SetWall(++x, y);
					break;
				case 2:
					isPath = (maze[x][y + 2] == 0);
					SetWall(x, ++y);
					SetWall(x, ++y);
					break;
				case 3:
					isPath = (maze[x - 2][y] == 0);
					SetWall(--x, y);
					SetWall(--x, y);
					break;
			}
			if (isPath) CreateWall(rand, x, y);
		}
		else
		{
			Vector2Int before = PopCurrentWall();	
			CreateWall(rand, before.x, before.y);
		}	
		
	}

	private void SetWall(int x, int y)
	{
		maze[x][y] = 1;
		if (x % 2 ==0 && y % 2 == 0)
			currentWallList.add(new Vector2Int(x, y));
	}

	private Vector2Int PopCurrentWall()
	{
		Vector2Int v = currentWallList.get(currentWallList.size() - 1);
		currentWallList.remove(currentWallList.size() - 1);
		return v;
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
				if (maze[x][y] == 0) System.out.print("  ");
				else System.out.print("■");
			System.out.println();
		}
	}
}
