class Eatable{
  boolean food;
  PVector location;
 Eatable(){
    location = new PVector(random(width),random(height));
 }
 
 //Diaplay the food
 void foodDisplay(boolean food){
    this.food = food;
   //Change color of Food according to food and poisen
   if(food){
     fill(0,255,0);  
    }
    else{
     fill(255,0,0); 
    }
    noStroke();
    //Draw Circle on screen
    ellipse(location.x,location.y,5,5);
}

}
