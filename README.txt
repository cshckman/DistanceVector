This is a project for the University of Memphis COMP 3825 class.
This is an implementation of a Distance Vector Algorithm and is an assignment
located in the Computer Networking: A Top Down Approach Programming Assignment
on page 429.

The book provided the vast majority of the code for the project the only files
that needed to be changed were Entity0, Entity1, Entity2, Entity3. It was the
actual implementation of the algorithm in each node. The rest of the code was
to set up the environment to run and test the code and was already supplied.

Inside each Entity I changed the update method and the constructor to fulfill
the needs of the project.

Inside each constructor I did the following:
  * initialized the 4x4 array 'distanceTable' to 999(which is the flag value.)
  * updated the known edges in the 'distanceTable'
  * creates the 'distanceVector'
  * delivers the 'distanceVector' to each neighbor via a 'packet'
    - each 'packet' contains both a 'source' and 'destination'

Then inside each update method I do the following:
  * set a boolean called 'updated' to false
    - this will be changed each time the vector is changed
    - this is to know whether to send new packets or not.
  * looks up the new 'distanceVector' using 'Math.min();'
  * It then uses the algorithm I implemented.
    - it gets the distanceVector from the packet.
    - it then checks if the vector plus path to node less than current 'distanceTable' value
      - initially I was using the shortest path instead of the direct path.
      - this caused the numbers to be off in some instances.
    - if the comparison is true it updates the 'distanceTable'
    - it then will check if the new 'distanceTable' value is less than the 'distanceVector'
    - if that is true it will flip updated to true and update the 'distanceVector'
  * finally if updated is true it will send out new packets.

Later I may implement the graduate assignment which handles changes to the links.
