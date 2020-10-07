import java.util.concurrent.*;
class PhaseViewer{
  public static void main(String[] args){
    String targetPhrase = "hello";
    int targetLength = targetPhrase.length();
    int population = 10;
    Population pop = new Population(targetPhrase,targetLength,population);
  }
}


class Population{
  Dna[] population;

  Population(String target,int length,int numberOfPopulation){
    population = new Dna[numberOfPopulation];
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
    System.out.println(this.phrase);
    CalcFitness();
    System.out.println(this.fitness);
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


  Dna CrossOver(Dna Partner){
    int Partition = ThreadLocalRandom.current().nextInt(1,this.phrase.length);
    Dna Child = new Dna(this.target,this.phrase.length);
    for (int i=0 ; i < this.phrase.length ; i++){
      if (i < Partition){
        Child.phrase[i] = this.phrase[i];
      }
      else{
        Child.phrase[i] = Partner.phrase[i];
      }
    }
    return Child;
  }
}
