CreatureDna bird;
Eatable[] food;
void setup(){
  size(600,600);
  food = new Eatable[20];
  bird = new CreatureDna();
  for(int i = 0 ; i < food.length ; i++){
    food[i] = new Eatable();
  }
}

void draw(){
  background(0);
  
  for(int i=0  ;i < food.length ; i++){
    if(i < food.length/2){
    food[i].foodDisplay(true);
    }
    else{
     food[i].foodDisplay(false); 
    }
  }
  
  bird.Display();
  bird.update();
  bird.eat(food);
}
