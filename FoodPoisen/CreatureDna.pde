class CreatureDna{
  PVector location;
  PVector velocity;
  PVector acceleration;
  float r = 6;
  float maxforce;
  float maxspeed;
  
  float foodattract;
  float poisenattract;
  float health = 1000;
  
  CreatureDna(){
    location = new PVector(random(width),random(height));
    velocity = new PVector(0,-2);
    acceleration = new PVector(0,0);
    maxforce = 0.1;
    maxspeed = 5;
    foodattract = random(10);
    poisenattract = random(10);
  }
  
  void Display(){
    float theta = this.velocity.heading() + PI / 2;
    fill(127);
    stroke(200);
    strokeWeight(1);
    push();
    translate(this.location.x,this.location.y);
    rotate(theta);
    beginShape();
    vertex(0, -this.r * 2);
    vertex(-this.r, this.r * 2);
    vertex(this.r, this.r * 2);
    endShape(CLOSE);
    pop();
    health--;
    System.out.println(health);
  }
  
  void update(){ 
    velocity.add(acceleration);
    velocity.limit(maxspeed);
    location.add(velocity);
    acceleration.mult(0);
  }
  void seek(PVector target){
    PVector desired = PVector.sub(target,location);
    desired.setMag(maxspeed);  
    PVector steer = PVector.sub(desired,velocity);
    steer.limit(maxforce);
    applyForce(steer);
 }
  void applyForce(PVector force){
    acceleration.add(force);
 }
 
 
 void eat(Eatable[] food){
   float record = 99999999;
   int index = -1;
   for(int i=0 ; i< food.length ; i++){
     float dist = dist(food[i].location.x,food[i].location.y,this.location.x,this.location.y);
     if (dist < record){
       record = dist;
       index = i;
     }    
   }
   if(record < 5){
       food[index].location = new PVector(random(width),random(height));
       if (food[index].food){
         this.health += 100;
       }
       else{
        this.health -= 100; 
       }
     } 
     
  this.seek(food[index].location);
 }
  
}
