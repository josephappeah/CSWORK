/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import java.io.*;
import java.util.HashMap;

import maze.ui.MazeViewer;

/**
 * 
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class MazeGameCreator
{
	/**
	 * Creates a small maze.
	 */
	public Maze createMaze()
	{
		
		Maze maze = new Maze();
		Room r1 = new Room(0); 
		Room r2 = new Room(1); 
		Door theDoor = new Door(r1, r2); 
		maze.addRoom(r1);
		maze.addRoom(r2);
		
		r1.setSide(Direction.North, new Wall());
		r1.setSide(Direction.South, new Wall());
		r1.setSide(Direction.West, new Wall());
		r1.setSide(Direction.East, theDoor);
		
		r2.setSide(Direction.North, new Wall());
		r2.setSide(Direction.East, new Wall());
		r2.setSide(Direction.South, new Wall());
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
		        	
		        
		            Room room = new Room(Integer.parseInt( array[1]));
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
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North, new Wall());
						}
						else if(array2[2].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North, door_.get(array2[2]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North, room_.get(Integer.parseInt(array2[2])));
						}
			        	
				
			        	if(array2[3].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, new Wall());
						}
						else if(array2[3].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, door_.get(array2[3]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, room_.get(Integer.parseInt(array2[3])));
						}
			        	
	
						if(array2[4].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, new Wall());
						}
						else if(array2[4].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, door_.get(array2[4]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, room_.get(Integer.parseInt(array2[4])));
						}
			        	
			       
						if(array2[5].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.West, new Wall());
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
			return new Wall();
	}
	public  Door MakeDoor(Room r1, Room r2){
			return new Door(r1, r2); 
	}
	public  Room MakeRoom(int num){
			return new Room(num); 
	}
		
	
	public static void main(String[] args) throws IOException
	{
		if(args.length == 2){
			
			if(args[0].equals("blue")){
				MazeGameCreator blue = new BlueMazeGameCreator();
				Maze maze = blue.loadMaze(args[1]);
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
			}
			else if(args[0].equals("red") ){
				MazeGameCreator red = new ReadMazeGameCreator();
				Maze maze = red.loadMaze(args[1]);
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
			}

		}
		else if(args.length == 1){
			
			if(args[0].equals("blue")){
				MazeGameCreator blue = new BlueMazeGameCreator();
				Maze maze = blue.createMaze();
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
			}
			else if(args[0].equals("red")){
				MazeGameCreator red = new ReadMazeGameCreator();
				Maze maze = red.createMaze();
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
			}
		}
		
		else{
			
			MazeGameCreator MazeGameCreator = new MazeGameCreator();
			Maze maze = MazeGameCreator.createMaze();
		    MazeViewer viewer = new MazeViewer(maze);
		    viewer.run();
		}

	}
}
