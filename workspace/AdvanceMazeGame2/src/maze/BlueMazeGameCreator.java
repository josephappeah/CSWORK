package maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BlueMazeGameCreator extends MazeGameCreator {
	
	public Maze createMaze()
	{
		
		Maze maze = new Maze();
		Room r1 = new GreenRoom(0); 
		Room r2 = new GreenRoom(1); 
		Door theDoor = new Door(r1, r2); 
		maze.addRoom(r1);
		maze.addRoom(r2);
		
		r1.setSide(Direction.North, new BlueWall());
		r1.setSide(Direction.South, new BlueWall());
		r1.setSide(Direction.West, new BlueWall());
		r1.setSide(Direction.East, theDoor);
		
		r2.setSide(Direction.North, new BlueWall());
		r2.setSide(Direction.East, new BlueWall());
		r2.setSide(Direction.South, new BlueWall());
		r2.setSide(Direction.West, theDoor);
		
		//System.out.println("The maze does not have any rooms yet!");
		return maze;
		

	}

	public  Maze MakeMaze(){
		return new Maze();	
	}
	public  Wall MakeWall(){
		return new BlueWall();
	}
	
	public  Door MakeDoor(Room r1, Room r2){
		return new BrownDoor(r1, r2); 
	}
	public  Room MakeRoom(int num){
		return new GreenRoom(num); 
	}
}
