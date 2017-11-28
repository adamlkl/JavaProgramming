
class Bombs {
  
  float x;
  float y;
  float dy = 5;
  PImage b1;
  boolean blocked = false;
  boolean hit2 = false;
  
  Bombs ( float xpos, float ypos, PImage torpedos) 
  {
    x = xpos;
    y = ypos;
    b1 = torpedos;
  }
  
  void draw ()
  {
      image ( b1, x, y, BOMB_WIDTH, BOMB_HEIGHT );
      move();  
  }
  
  void move ()
  {
    y += dy;
  }
  
  boolean offScreen()
  {
    return ( y>SCREEN_HEIGHT );
  }
  
  boolean collide (Ship theShip)
  {
    return ( x+BOMB_WIDTH>=theShip.xpos && x<=theShip.xpos+SHIP_SIZE 
    && y+BOMB_HEIGHT>=theShip.ypos && y<=theShip.ypos+SHIP_SIZE );
  }

  boolean block (Barrier theBarrier[])
  {
   blocked = false;
     for ( int index = 0; index<theBarrier.length; index++ )
     {
       if ( theBarrier[index].barrierLife != 0)
       {
       
       if (x+BOMB_WIDTH>=theBarrier[index].xpos && x<=theBarrier[index].xpos+100 
        && y+BOMB_HEIGHT>=theBarrier[index].ypos && y<=theBarrier[index].ypos+40 )
        {
          theBarrier[index].barrierLife--;
          blocked = true;
         }
       
       }
     }
     return blocked;
  }
}