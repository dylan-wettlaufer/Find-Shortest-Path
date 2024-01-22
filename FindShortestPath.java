
/** this class find the shortest path out of the dungeon  */
public class FindShortestPath {
	public static void main(String[] args) {
		try {

			if (args.length < 1) throw new Exception("No input file specified");
			
			Hexagon exitChamber = null; // Variable that will be used to hold the Hexagon that represents the exit
			
			String dungeonFileName = args[0];  // reads the input file and uses it as the parameter for the Dungeon object
			Dungeon dungeon = new Dungeon(dungeonFileName); 
			DLPriorityQueue<Hexagon> prioQueue = new DLPriorityQueue<Hexagon>(); //creates an empty priority queue of type Hexagon
			Hexagon start = dungeon.getStart(); // gets the starting Hexagon 
			prioQueue.add(start, 0); // adds the starting Hexagon to the priority queue 
			start.markEnqueued(); // marks start as enqueued 
			
			boolean exit = false;
			
			while (!prioQueue.isEmpty() && !exit) {
				boolean dragonChamber = false;
				
				Hexagon current = prioQueue.removeMin(); // removes the Hexagon with the smallest priority
				current.markDequeued(); // marks this hexagon as dequeued 
			
				if (current.isExit()) { // if the current Hexagon is the exit, then the loop ends and the exitChamber variable is set to the current chamber
					exit = true; 
					exitChamber = current;
				}
				
				else {
					
					for (int i = 0; i < 6; i++) { // loops through each one of the current hexagon's neighbours to check if they are the dragon chamber
						Hexagon neighbour = current.getNeighbour(i);
						if (neighbour != null) {
							if (neighbour.isDragon()) dragonChamber = true; // if one of the neighbours is a dragon chmaber, the dragonChamber vairble is set to true and the following if statement won't run
						}
					}
					if (!dragonChamber) { // if none of the neighbours have a dragon chamber, the following code runs 
						for (int i = 0; i < 6; i++) { // loops through all six of a hexgon's neighbours
							Hexagon neighbour = current.getNeighbour(i);
							boolean modified = false;
							
							if (neighbour != null) { 
								if (!neighbour.isWall() && !neighbour.isMarkedDequeued()) { // The path cannot contain walls
									
									int d = 1 + current.getDistanceToStart();
									
									if (neighbour.getDistanceToStart() > d) { // if neighbour is greater than d than the distacne to the atrat is incorrect
										
										neighbour.setDistanceToStart(d); // fixes the distance to the start
										neighbour.setPredecessor(current); // sets current as the neighbours predecessor 
										modified = true;
										
									}
									
									if (modified && neighbour.isMarkedEnqueued()) {
										prioQueue.updatePriority(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
										
									}
									
									else if (!neighbour.isMarkedDequeued()) { // nighbour is added to the queue and marked as enqueued 
										prioQueue.add(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
										neighbour.markEnqueued();
									}
								}	
							}
					}
						}
				}
				}
	
			if (prioQueue.isEmpty()) System.out.println("No path found"); // if the priority queue is empty, then there is no path
				
			else {
				int pathLength = exitChamber.getDistanceToStart() + 1; // takes the distance from the extra chamber to the start and adds one 
				System.out.println("Path of length " + pathLength + " found");
			}
				
		}

		catch (InvalidDungeonCharacterException e) {
			System.out.println(e.getMessage());
		}
		catch (InvalidNeighbourIndexException e) {
			System.out.println(e.getMessage());
		}
		catch (InvalidElementException e) {
			System.out.println(e.getMessage());
		}
		catch (EmptyPriorityQueueException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

		
	


