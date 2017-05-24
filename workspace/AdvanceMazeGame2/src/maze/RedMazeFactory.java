package maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RedMazeFactory implements MazeFactory {
	
	
	@Override
	public Maze MakeMaze() {
		// TODO Auto-generated method stub
		return new Maze();	
	}

	@Override
	public Wall MakeWall() {
		// TODO Auto-generated method stub
		return new RedWall();
	}

	@Override
	public Door MakeDoor(Room r1, Room r2) {
		// TODO Auto-generated method stub
		return new Door(r1, r2); 
	}

	@Override
	public Room MakeRoom(int num) {
		// TODO Auto-generated method stub
		return new RedRoom(num);
	}

}
