A program in Java to find a way to exit a dungeon. The dungeons consist of a set of hexagonal shaped chambers.
There is an inital chamber and en exit chamber. The goal is to find the shortest path from the entrance to the exit while avoiding the dragon in the dungeon. 
There are four types of chambers. Empty chmabers allow the character to safely walk across one of these chambers to move to an adjacent one. Wall chambers serve as walls and the character cannot traverse them.
The exit chamber allows the character to exit the dungeon. The dragon chamber is a chamber that the character cannot go through and they can't go through any chamber adjacent to a dragon chamber. The character can enter a
dragon chamber or a chamber adjacent to a dragon chamber, but when he realizes that he is in one of these chambers he has to leave it right away.
The algorithm will start at the initial chamber and as it traverses the dungeons,
it will keep in the priority queue the chambers that it might visit next. Each chamber has a priority
equal to the distance from the chamber to the initial chamber plus an estimation of the distance from
the chamber to the exit.
Because the algorithm visits chambers in increasing order of priority, it will always try to move to the
chamber that seems to be closest to the exit.
