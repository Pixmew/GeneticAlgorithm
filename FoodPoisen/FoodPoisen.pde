CreatureDna bird;
PVector[] food;
void setup(){
  size(600,600);
  food = new PVector[20];
  bird = new CreatureDna();
  for(int i = 0 ; i < food.length ; i++){
    food[i] = new PVector(random(width),random(height));
  }
}

void draw(){
  background(0);
  
  for(int i=0  ;i < food.length ; i++){
    ellipse(food[i].x,food[i].y , 5,5);
    fill(0,255,0);
  }
  
  bird.display();
  bird.update();
  bird.eat(food);
}
