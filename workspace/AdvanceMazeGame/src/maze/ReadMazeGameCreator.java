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
	public Maze loadMaze(final String path) throws IOException
	{
		Maze maze = new Maze();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String myLine;
		
		HashMap<Integer, Room> room_ = new HashMap<>();
		HashMap<String, Door> door_ = new HashMap<>();
		 
		while ((myLine = reader.readLine()) != null) {
			
			String[] array = myLine.split(" ");
			
		        if (array[0].equals("room")){
		        	
		        
		            Room room = new RedRoom(Integer.parseInt( array[1]));
		            room_.put(Integer.parseInt( array[1]), room);
		            maze.addRoom(room);
		            
		       } 
		       else if (array[0].equals("door")) {
		 
		            	Door theDoor = new Door(maze.getRoom(Integer.parseInt( array[2])), maze.getRoom(Integer.parseInt( array[3])));
			            	if(array[4].equals("open")){
			            		theDoor.setOpen(true);
			            	}
			            	else{
			            		theDoor.setOpen(false);
			            	}
			            door_.put(array[1], theDoor);
		        }
			}	
		
		BufferedReader reader2 = new BufferedReader(new FileReader(path));
		String myLine2;
		 
		while ((myLine2 = reader2.readLine()) != null) {
			
		String[] array2 = myLine2.split(" ");
				
		        if (array2[0].equals("room")){
		        	
		 
			        	if(array2[2].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North, new RedWall());
						}
						else if(array2[2].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North, door_.get(array2[2]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North, room_.get(Integer.parseInt(array2[2])));
						}
			        	
				
			        	if(array2[3].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, new RedWall());
						}
						else if(array2[3].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, door_.get(array2[3]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, room_.get(Integer.parseInt(array2[3])));
						}
			        	
	
						if(array2[4].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, new RedWall());
						}
						else if(array2[4].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, door_.get(array2[4]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, room_.get(Integer.parseInt(array2[4])));
						}
			        	
			       
						if(array2[5].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.West, new RedWall());
						}
						else if(array2[5].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.West, door_.get(array2[5]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.West, room_.get(Integer.parseInt(array2[5])));
						}
			            
			       } 		
  
	        }
		
		
		reader.close();
		reader2.close();
		maze.setCurrentRoom(room_.get(0));
		
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
