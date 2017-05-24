package maze;

import java.io.IOException;

public interface MazeFactory {
	
	public  Maze MakeMaze();
	

	public  Wall MakeWall();


	public  Door MakeDoor(Room r1, Room r2);


	public  Room MakeRoom(int num);





	

}
