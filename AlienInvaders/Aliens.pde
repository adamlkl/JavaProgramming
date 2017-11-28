
class Aliens {
  int lifeForce = 30;
  boolean aliendead = false;
  PImage aliens;
  float x; 
  float y;
  int xdirection = 1;
  int counter = 0;
  float dx = 5;
  float dy = 0;
  float amplitude = IMGSIZE/10;
  float frequency = 1/(8*TWO_PI);
  float sinemoving = 0;
  PImage a1;
  PImage e1;
  Bombs theBomb;
  PImage b1 = loadImage ("bombs.png");
 
  Aliens ( float xpos, float ypos, PImage name1, PImage exploded )
  {
    x=xpos;
    y=ypos;
    a1 = name1;
    e1 = exploded;
  }
 
 void draw ()
 {
   if ( lifeForce == ALIVE )
   {
     image ( a1,x,y,IMGSIZE,IMGSIZE );
   }
   else if ( lifeForce != DEAD )
   {
     image ( e1,x,y,IMGSIZE,IMGSIZE );
     lifeForce--;
   } 
 }
 
 void move ()
{ 
  if ( (x >= SCREEN_WIDTH - IMGSIZE && counter < IMGSIZE )  || (x <= 0 && counter < IMGSIZE) )
  {
    dy = 5;
    counter=counter+5;
    y+=dy;
  }
  else if (counter >= IMGSIZE ) 
  {
    dx *= -1.2;
    x+= dx;
    counter = 0;
    dy = 0;
  }
  else 
  {
    x =x + dx;
  }
}

    void sineMove ()
  {  
     sinemoving = amplitude*sin(2*PI*frequency*x);

  if ( (x >= SCREEN_WIDTH - IMGSIZE && counter < IMGSIZE )  || (x <= 0 && counter < IMGSIZE) )
  {
    dy = 5;
    counter=counter+5;
    sinemoving = 0;
    y=y+dy;
  }
  else if (counter >= IMGSIZE ) 
  {
    dx *= -1.2;
    x+=dx;
    counter = 0;
    dy = 0;
  }
 
  else 
  {
    x = x + dx;
    y += sinemoving;
  }
  }
 
 void explode ()
 {
  if ( lifeForce == ALIVE )
  {
    scream.play();
    scream.amp(2);
    lifeForce--;
    aliendead = true;
  }
 }
 
 void dropBomb( Barrier theBarrier[] )
 {   
    if ( theBomb == null || theBomb.offScreen())
    {
      if (!aliendead)
    {
      if ((int)random(1,100)==7)
      {
        theBomb = new Bombs ( x,y,b1 );
      }
    }
    }
    else if ( theBomb != null )
    {    
      theBomb.draw();
      if (theBomb.block(theBarrier))
      {
        theBomb.y = SCREEN_HEIGHT+10;
      }
    } 
 }
 
 Bombs getBomb()
 {
     return theBomb;
 }
 
  float ax()
  {
    return x;
  }
  
  float ay()
  {
    return y;
  }
  
  boolean dead()
  {
    return aliendead;
  }