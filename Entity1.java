public class Entity1 extends Entity {
    // Perform any necessary initialization in the constructor
    public Entity1(){
      for(int i = 0; i < distanceTable.length; i++){
        for(int j = 0; j < distanceTable[0].length; j++){
          distanceTable[i][j] = 999;
        }
      }

      distanceTable[0][0] = 1;
      distanceTable[1][1] = 0;
      distanceTable[2][2] = 1;
      distanceTable[3][3] = 999;

      int[] distanceVector = {1,0,1,999};

      for(int i =0; i < distanceTable.length; i++){
        if(i != 1 && i != 3){
          Packet p = new Packet(1, i, distanceVector);
          NetworkSimulator.toLayer2(p);
        }
      }
      // printDT();
    }

    // Handle updates when a packet is received.  Students will need to call
    // NetworkSimulator.toLayer2() with new packets based upon what they
    // send to update.  Be careful to construct the source and destination of
    // the packet correctly.  Read the warning in NetworkSimulator.java for more
    // details.
    public void update(Packet p){
      boolean updated = false;

      int[] distanceVector = new int[distanceTable.length];

      for(int i = 0; i < distanceTable.length; i++){
        int temp1 = Math.min(distanceTable[i][0], distanceTable[i][1]);
        int temp2 = Math.min(distanceTable[i][2], distanceTable[i][3]);
        distanceVector[i] = Math.min(temp1, temp2);
      }

      for(int i = 0; i < p.mincost.length; i++){
        if(p.getMincost(i)+distanceTable[p.getSource()][p.getSource()] < distanceTable[i][p.getSource()]){
          distanceTable[i][p.getSource()] = p.getMincost(i)+distanceTable[p.getSource()][p.getSource()];
          if(distanceTable[i][p.getSource()]<distanceVector[i]){
            distanceVector[i] = distanceTable[i][p.getSource()];
            updated = true;
          }
        }

      }
      // printDT();


      if(updated){
        for(int i =0; i < distanceTable.length; i++){
          if(i != 1 && i != 3){
            Packet newPacket = new Packet(1, i, distanceVector);
            NetworkSimulator.toLayer2(newPacket);
          }
        }
        // System.out.println(p.getDest() + " receiving distance vector from " + p.getSource());
        // System.out.print("source vector: ");
        // for(int i = 0; i < distanceVector.length; i++){
        //   System.out.print(p.getMincost(i));
        // }
        // printDT();
        // System.out.print("new vector: ");
        // for(int i = 0; i < distanceVector.length; i++){
        //   System.out.print(distanceVector[i]);
        // }
        // System.out.println();
      }
    }

    public void linkCostChangeHandler(int whichLink, int newCost){
    }

    public void printDT(){
        System.out.println();
        System.out.println("         via");
        System.out.println(" D1 |   0   2");
        System.out.println("----+--------");
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++){
            if (i == 1)
            {
                continue;
            }

            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j += 2)
            {

                if (distanceTable[i][j] < 10)
                {
                    System.out.print("   ");
                }
                else if (distanceTable[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else
                {
                    System.out.print(" ");
                }

                System.out.print(distanceTable[i][j]);
            }
            System.out.println();
        }
    }
}
