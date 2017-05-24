package maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadMazeGameCreator extends MazeGameCreator {
	
	public Maze createMaze()
	{
		
		Maze maze = new Maze();
		Room r1 = new RedRoom(0); 
		Room r2 = new RedRoom(1); 
		Door theDoor = new Door(r1, r2); 
		maze.addRoom(r1);
		maze.addRoom(r2);
		
		r1.setSide(Direction.North, new RedWall());
		r1.setSide(Direction.South, new RedWall());
		r1.setSide(Direction.West, new RedWall());
		r1.setSide(Direction.East, theDoor);
		
		r2.setSide(Direction.North, new RedWall());
		r2.setSide(Direction.East, new RedWall());
		r2.setSide(Direction.South, new RedWall());
		r2.setSide(Direction.West, theDoor);
		
		//System.out.println("The maze does not have any rooms yet!");
		return maze;
		

	}

	public  Maze MakeMaze(){
		return new Maze();	
	}
	public  Wall MakeWall(){
		return new RedWall();
	}
	public  Door MakeDoor(Room r1, Room r2){
		return new Door(r1, r2); 
	}
	public  Room MakeRoom(int num){
		return new RedRoom(num); 
	}
}
