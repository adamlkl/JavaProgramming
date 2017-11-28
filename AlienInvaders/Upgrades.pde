class Upgrades{
  
  float x;
  float y;
  PImage U;
  boolean upgrader1 = true;
  boolean upgrader2 = true;
  
  Upgrades( PImage upgradeType,float ux, float uy )
  {
    x = ux;
    y = uy;
    U = upgradeType; 
  }
  
  void draw ( PImage upgradeType )
  {
    if ( !fade )
    {
      image ( upgradeType , x, y, 20,20 );
    }

  }
  
  void move1 ( Ship theShip )
  {
    y += 5;  
    
    if ( x>=theShip.xpos && x<=theShip.xpos+SHIP_SIZE && y<theShip.ypos + SHIP_SIZE 
    && y > theShip.ypos )
    {
      upgraded1 = true;
      fade = true;
    }
  }  
  
  void move2 ( Ship theShip )
  {
    y += 5;
    
    if ( x>=theShip.xpos && x<=theShip.xpos+SHIP_SIZE && y<theShip.ypos + SHIP_SIZE 
    && y > theShip.ypos )
    {
      upgraded2 = true;
      fade = true;
    }
  }  
  
}
}