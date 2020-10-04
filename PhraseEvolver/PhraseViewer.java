import java.util.concurrent.*;
class PhaseViewer{

  public static void main(String[] args){
    Population pop = new Population(20,5);
  }
}


class Population{
  Dna[] population;


  Population(int length,int charlen){
    population = new Dna[length];
    for (int i=0 ; i < length ; i++){
      population[i] = new Dna(charlen);
    }
  }


}


class Dna{
char[] phrase;


  Dna(int length){
    phrase = new char[length];
    for(int i=0 ; i< phrase.length ; i++){
      phrase[i] = (char)ThreadLocalRandom.current().nextInt(65,122);
    }
    System.out.println(phrase);
  }


}
