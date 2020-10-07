import java.util.concurrent.*;
class PhaseViewer{
  public static void main(String[] args){
    String targetPhrase = "To be or not to be.That is the Question!";
    int targetLength = targetPhrase.length();
    int population = 5;
    Population pop = new Population(targetPhrase,targetLength,population);
  }
}


class Population{
  Dna[] population;


  Population(String target,int length,int numberOfPopulation){
    population = new Dna[length];
    for (int i=0 ; i < numberOfPopulation ; i++){
      population[i] = new Dna(target,length);
    }
  }


}


class Dna{
char[] phrase;
int fitness = 0;
String target;
  Dna(String target,int length){
    this.target = target;
    phrase = new char[length];
    for(int i=0 ; i< this.phrase.length ; i++){
      phrase[i] = (char)ThreadLocalRandom.current().nextInt(65,122);
    }
    System.out.println(phrase);
    this.CalcFitness();
    System.out.println(fitness);
  }

void CalcFitness(){
    int count = 0;
    for(int i =0 ; i < this.phrase.length ; i++){
      if(this.phrase[i] == this.target.charAt(i)){
        count++;
      }
    }
    this.fitness = count;
  }
}
