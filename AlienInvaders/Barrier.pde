class Barrier{
  
  int barrierLife = 6;
  PImage firstBarrier;
  PImage secondBarrier;
  PImage thirdBarrier;
  PImage fourthBarrier;
  PImage fifthBarrier;
  PImage sixthBarrier;
  int xpos;
  int ypos;
  
  Barrier( int bx2, int by2, PImage b1, PImage b2,
  PImage b3,PImage b4, PImage b5, PImage b6 )
  {
    xpos=bx2;
    ypos=by2;
    firstBarrier = b1;
    secondBarrier = b2;
    thirdBarrier = b3;
    fourthBarrier = b4;
    fifthBarrier = b5;
    sixthBarrier = b6; 
  }
  
  void draw()
  {
    if ( barrierLife != 0 )
    {
      switch(barrierLife)
      {
        case 6:
        image ( sixthBarrier, xpos, ypos, 100, 40 );
        break;
        case 5:
        image ( fifthBarrier, xpos, ypos, 100, 40 );
        break;
        case 4:
        image ( fourthBarrier, xpos, ypos, 100, 40 );
        break;
        case 3:
        image ( thirdBarrier, xpos, ypos, 100, 40 );
        break;
        case 2:
        image ( secondBarrier, xpos, ypos, 100, 40 );
        break;
        case 1:
        image ( firstBarrier, xpos, ypos, 100, 40 );
        break;
      }
    }
  }
}