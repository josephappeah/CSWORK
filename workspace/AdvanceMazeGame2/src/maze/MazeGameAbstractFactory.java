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
public class MazeGameAbstractFactory
{
	/**
	 * Creates a small maze.
	 */

	public Maze loadMaze(final String path, MazeFactory fac) throws IOException
	{
		Maze maze = fac.MakeMaze();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String myLine;
		
		HashMap<Integer, Room> room_ = new HashMap<>();
		HashMap<String, Door> door_ = new HashMap<>();
		 
		while ((myLine = reader.readLine()) != null) {
			
			String[] array = myLine.split(" ");
			
		        if (array[0].equals("room")){
		        	
		        
		            Room room =  fac.MakeRoom(Integer.parseInt( array[1]));
		            room_.put(Integer.parseInt( array[1]), room);
		            maze.addRoom(room);
		            
		       } 
		       else if (array[0].equals("door")) {
		 
		            	Door theDoor =  fac.MakeDoor(maze.getRoom(Integer.parseInt( array[2])), maze.getRoom(Integer.parseInt( array[3])));
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
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North,  fac.MakeWall());
						}
						else if(array2[2].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North, door_.get(array2[2]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.North, room_.get(Integer.parseInt(array2[2])));
						}
			        	
				
			        	if(array2[3].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, fac.MakeWall());
						}
						else if(array2[3].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, door_.get(array2[3]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.South, room_.get(Integer.parseInt(array2[3])));
						}
			        	
	
						if(array2[4].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, fac.MakeWall());
						}
						else if(array2[4].charAt(0)=='d'){
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, door_.get(array2[4]));
						}
						else {
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.East, room_.get(Integer.parseInt(array2[4])));
						}
			        	
			       
						if(array2[5].equals("wall")){	
							room_.get(Integer.parseInt(array2[1])).setSide(Direction.West, fac.MakeWall());
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
			
			if(args[1].equals("blue")){	
			
				MazeGameAbstractFactory MazeGameCreator = new MazeGameAbstractFactory();
				BlueMazeFactory blue = new BlueMazeFactory();
				Maze maze = MazeGameCreator.loadMaze(args[0],blue);
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
			}
			else if(args[1].equals("red") ){
				MazeGameAbstractFactory MazeGameCreator = new MazeGameAbstractFactory();
				RedMazeFactory red = new RedMazeFactory();
				Maze maze = MazeGameCreator.loadMaze(args[0],red);
			    MazeViewer viewer = new MazeViewer(maze);
			    viewer.run();
			}

		}

		
		/*else{
			
			MazeGameAbstractFactory MazeGameCreator = new MazeGameAbstractFactory();
			Maze maze = MazeGameCreator.createMaze();
		    MazeViewer viewer = new MazeViewer(maze);
		    viewer.run();
		}*/

	}
}
