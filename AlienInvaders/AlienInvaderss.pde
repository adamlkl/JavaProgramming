import processing.sound.*;

Aliens myAliens [];
Ship myship;
Bullets theBullet;
Bullets myBullet[];
Bullets bullet2;
Barrier myBarrier[];
Upgrades myUpgrades[];
Bombs myBombs [];
SoundFile soundWave;
SoundFile scream;
//Pulse pulse;
boolean gameOver1 = false;
boolean gameOver2 = false;
boolean upgraded1 = false;
boolean upgraded2 = false;
boolean fade = false;
boolean boom = false;
int ycoordinate = 0;
boolean fired = false;
boolean reloading = false;
boolean mousePress = false;
boolean exploded = false;
int bulletCount = 1;
String codeName2 = "alien4.png";
String codeName1 = "alien1.png";
String codeName3 = "explosion.png";
String upgrade = "upgrade.png";
String upgrade2 = "upgrade2.jpg";
String ammocode = "bullet.jpg";
String alienBomb = "bombs.png";

PImage normalAliens;
PImage superAliens;
PImage explosion;
PImage ammo;
PImage moreBullets;
PImage rage;
PImage alienBombs;
PImage b1;
PImage b2;
PImage b3;
PImage b4;
PImage b5;
PImage b6;
PFont font;

void setup ()
{ 
  soundWave = new SoundFile (this, "prometheus&pandora.mp3" );
  scream = new SoundFile ( this,"femalescream.wav");
  soundWave.play();
  soundWave.amp(1);
  //pulse=new Pulse(this);
  //pulse.play(soundWave);
  font = loadFont ("American48.vlw");
  myship = new Ship(); 
  myBombs = new Bombs [10];
  myAliens = new Aliens [10];
  normalAliens = loadImage ( codeName1 );
  superAliens = loadImage ( codeName2 );
  explosion = loadImage ( codeName3 );
  init_array ( myAliens, normalAliens, superAliens, explosion ); 
  ammo = loadImage ( ammocode );
  theBullet = new Bullets( ammo );
  bullet2 = new Bullets ( ammo );
  myUpgrades = new Upgrades[2];
  moreBullets = loadImage ( upgrade );
  rage = loadImage ( upgrade2 );
  alienBombs = loadImage ( alienBomb );
  init_bombs_array ( myBombs, alienBombs ); 
  b1 = loadImage ("steelwall.jpg");
  b2 = loadImage ("hexawall3.jpg");
  b3 = loadImage ("hexawall2.jpg");
  b4 = loadImage ("hexaWall.jpg");
  b5 = loadImage ("icywall.jpg");
  b6 = loadImage ("woodenwall.jpg");
  myBarrier = new Barrier [6];
  constructWall ( myBarrier, b6,b5,b4,b3,b2,b1 );
  size (1180,600);
}

void draw ()
{
  background (0);
  if ( !gameOver1 && !gameOver2 )
  {
    // displayWall(myShield);
    displayWall2(myBarrier);
    bomb_exploded ( myAliens, myship );
    myship.display (mouseX, SCREEN_HEIGHT - SHIP_SIZE);
    myship.move();
    move_array(myAliens);
    draw_array( myAliens );
    drop_all_bombs(myAliens,myBarrier); 
    check_dead (myAliens);
        
        if(myUpgrades[0] != null && myUpgrades[0].upgrader1 )
        {
          myUpgrades[0].draw(moreBullets);
          myUpgrades[0].move1(myship);
        }
  
        if (myUpgrades[1] != null && myUpgrades[1].upgrader1 )
        {
          myUpgrades[1].draw(rage);
          myUpgrades[1].move2(myship);
        }
      
        if ( !upgraded1 )
        {
          if ( fired )
          {
            theBullet.display ( myship.xpos+(10), 540);
            bulletCount = 0;
            reloading = true;
          }
    
          if ( reloading && bulletCount == 0)
          {
            theBullet.move(myship.xpos+(10),540,myBarrier);
            fired = false;
          }
          theBullet.collided(myAliens, myUpgrades,myBarrier, rage, moreBullets );  
        }
  
        else if ( upgraded1 )
        {
          if (fired)
          {
            theBullet.display ( myship.xpos+(20), 540);
            bullet2.display ( myship.xpos, 540 );
            bulletCount = 0;
            reloading = true; 
          }
    
          if ( reloading && bulletCount == 0 )
          {
            theBullet.move( myship.xpos+(20), 540, myBarrier);
            bullet2.move(myship.xpos, 540, myBarrier);
            fired = false;  
          }
          theBullet.collided(myAliens, myUpgrades,myBarrier, rage, moreBullets );
          bullet2.collided(myAliens, myUpgrades,myBarrier, rage, moreBullets );
          //crumblingWall(myShield, theBullet);
          //crumblingWall(myShield, bullet2);
        } 
  }
  
  else if (gameOver1)
  {
    soundWave.stop();
    textFont(font);
    fill(255);
    text("You LOST!!!", SCREEN_WIDTH/4, SCREEN_HEIGHT/4);
    text("NOOB!!!", SCREEN_WIDTH/2, 3*SCREEN_HEIGHT/4);
  }
  
  else if (gameOver2)
  {
    soundWave.stop();
    textFont(font);
    fill(255);
    text("You WIN!!!", SCREEN_WIDTH/3, SCREEN_HEIGHT/2);
  }
}  
    
void init_array ( Aliens theAliens[], PImage Alien1 , PImage Alien2, PImage supernova)
{
  for ( int index=0; index<theAliens.length; index++ )
  {
    if ((index == 4)|| (index == 9 ))
    {
      theAliens[index] = new Aliens ((index*IMGSIZE)+1, ycoordinate , Alien1, supernova );
    }
    else 
    {
    theAliens[index] = new Aliens ( (index*IMGSIZE)+1, ycoordinate, Alien2, supernova  );
    }
  }
}

void draw_array ( Aliens theAliens[] )
{
  for ( int index = 0; index<theAliens.length; index++ )
  {
   theAliens[index].draw(); 
  }
}

void move_array ( Aliens theAliens[] )
{
  for ( int index = 0; index<theAliens.length; index++ )
  {
    if ((index == 4)|| (index == 9 )) 
    {
      theAliens[index].sineMove();
    }
    else 
    {
     theAliens[index].move();
    }
  }
}

void init_bombs_array ( Bombs theBombs[], PImage alienBombz )
{
  for ( int index = 0; index<theBombs.length; index++ )
  {
    theBombs[index] = new Bombs ( 0,0, alienBombz );
  }
}

void drop_all_bombs ( Aliens theAliens[], Barrier theBarrier[])
{
  for (int index = 0; index<theAliens.length; index++ )
  {
    theAliens[index].dropBomb(theBarrier);
  }
  
}

void bomb_exploded (Aliens theAliens [] , Ship theship )
{
  for ( int index = 0; index<theAliens.length; index++ )
  {
    Bombs oneBomb = theAliens[index].getBomb();
    if ( oneBomb!=null )
    {
      if (oneBomb.collide(theship))
      {
        gameOver1 = true;
      }
    }
  }
}

void check_dead (Aliens theAliens [])
{
  int counter = 0;
  for ( int index = 0; index<theAliens.length; index++ )
  {
    if (theAliens[index].aliendead == true)
    {
      counter++;
    }
  }
  
  if (counter == theAliens.length)
  {
    gameOver2 = true;
  }
}

void constructWall ( Barrier theBarrier[], PImage p1, PImage p2,
PImage p3,PImage p4,PImage p5,PImage p6 )
{
  for ( int index = 0; index<theBarrier.length; index++ )
  {
    theBarrier[index]= new Barrier ( 50+index*(200) , 480, p1,p2,p3,p4,p5,p6 );
  }
}

void displayWall2( Barrier theBarrier[] )
{
  for ( int index = 0; index<theBarrier.length; index++ )
  {
    theBarrier[index].draw();
  }
}

void keyPressed()
{
    if ( key == ENTER && bulletCount>0 && !fired && !reloading )
    {
      fired = true;  
    }  
}



    