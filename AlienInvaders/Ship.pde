class Ship
{
  float xpos;
  float ypos; 
  PImage ships;
  
  Ship()
  {
    ships = loadImage ("alien6.png");
  }
  
  void display (float sx , float sy)
  {
    xpos = sx;
    ypos = sy;
    image ( ships, sx , sy , SHIP_SIZE, SHIP_SIZE );
  }

  void move()
  { 
    if ( xpos > SCREEN_WIDTH - IMGSIZE )
    {
      xpos = SCREEN_WIDTH - IMGSIZE;
    }
    
     else if ( xpos < 0 )
     {
       xpos = 0;
     }
  }
}