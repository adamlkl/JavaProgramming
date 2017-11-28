class Bullets{
  
  PImage bullets;
  float x;
  float y;
  float yv = -17;
  float yvector = 2;
  boolean hit = false;
 
  Bullets( PImage missles)
  {
    bullets = missles;
  }
  
  void display (float bx , float by  )
  {   
    if (!hit)
    {
      x = bx;
      y = by;
      image ( bullets , x, y, 20, 40 );
    }
  }
  
  void move ( float bx, float by, Barrier theBarrier[] )
  {   
    for ( int index = 0; index<theBarrier.length; index++ )
    {
      if ( theBarrier[index].barrierLife!=0)
      {
        println("f");
        reset(bx, by);
      }
    }
    if (!hit)
    {
      image ( bullets, x, y, 20, 40);
      y += yv;
    
      if ( y<-40 )
      {
        reloading = false;
        bulletCount = 1;
      }
    
      if ( upgraded2 )
      {
        yv = -50;
      }
    }
  }
  
  void collided ( Aliens theAlien[] , Upgrades theUpgrades[], Barrier theBarrier[], PImage fervor, PImage extraCount )
  {
    for ( int index = 0; index<theAlien.length; index++ )
    {
      if ( x>=(theAlien[index].x) &&  x<= (theAlien[index].x + IMGSIZE) 
       && y < theAlien[index].y+IMGSIZE  && y > theAlien[index].y)
      {
        theAlien[index].explode();
        
        if ( index == 3 || index == 6 )
        { 
          if ( theUpgrades[0]==null )
          {
           theUpgrades[0] = new Upgrades ( extraCount ,x ,y );
          }
        }
        
        else if ( index == 2 || index == 8 )
        {
          theUpgrades[1] = new Upgrades ( fervor, x, y );
        }
      }
    }
    
    for ( int index2 = 0; index2<theBarrier.length; index2++ )
    {
      if ( theBarrier[index2].barrierLife != 0)
      {
      if (!hit)
      {
        if ( x >= theBarrier[index2].xpos && x+20<=(theBarrier[index2].xpos+100) 
        && y >= theBarrier[index2].ypos && y+20<= theBarrier[index2].ypos+40 )
        {
          theBarrier[index2].barrierLife --;
          hit = true;
          fired = false;
          reloading = false;
          bulletCount = 1;
        }
      }
      }
    }
  }
  
  void reset( float bx, float by)
  {
    if ( hit==true && fired == false)
      {
        hit=false;
        x=bx;
        y=by;
      }
  }
}