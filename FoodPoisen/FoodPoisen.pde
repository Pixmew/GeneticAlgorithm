CreatureDna[] bird;
Eatable[] food;
void setup(){
  size(600,600);
  food = new Eatable[20];
  bird = new CreatureDna[4];
  for(int i = 0 ; i < food.length ; i++){
    food[i] = new Eatable();
  }
  for(int i =0 ; i < bird.length ; i++){
    bird[i] = new CreatureDna();
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
  for(int i = 0 ;  i < bird.length ; i++){
    
  bird[i].Display();
  bird[i].update();
  bird[i].eat(food);
}
}
