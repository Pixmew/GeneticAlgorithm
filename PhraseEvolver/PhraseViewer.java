import java.util.concurrent.*;
import java.util.*;

class PhraseViewer{
    public static void main(String[] args){

       String targetPhrase = "To_be_or_not_to_be,That_is_the_question!";
       int population = 1000;
       float mutationRate = 0.03f;
       int targetLength = targetPhrase.length();
       boolean isDone = false;


       Population pop = new Population(targetPhrase,targetLength,population,mutationRate);

       int Generations = 0;
       while (!isDone){
           pop.CalcualateFitness();
           pop.Reproduction();
           pop.mutation();
           isDone = pop.isDone();
           Generations++;
         }
         pop.showInfo(Generations);
     }
}






class Population{
    Dna[] population;
    int numOfPop;
    ArrayList<Dna> matingPool;
    int lengthOfPhrase;
    int bestfitness;
    int bestindex;
    Dna BestPhrase;
    float MutationRate;


    Population(String target,int length,int numberOfPopulation ,float mutationRate){
       this.numOfPop = numberOfPopulation;
       this.lengthOfPhrase = length;
       this.MutationRate = mutationRate;
       this.BestPhrase = new Dna(target, length);
       population = new Dna[numberOfPopulation];

       for (int i=0 ; i < this.population.length ; i++){
          this.population[i] = new Dna(target,length);
        }
    }


    void CalcualateFitness(){
        for (int i=0 ; i < this.population.length ; i++ ){
             this.population[i].CalcFitness();
         }
         ShowBest();
    }


    void GenerateMatingPool(){
        matingPool = new ArrayList<Dna>();
        matingPool.clear();

        for(int i =0 ; i< this.population.length ; i++){
             int num = this.population[i].fitness;
             for(int j=0 ; j < num ; j++){
                  matingPool.add(this.population[i]);
             }
         }
    }

    void Reproduction(){
        GenerateMatingPool();

        for(int i=0; i< this.population.length;  i++){
              int parentBindex = ThreadLocalRandom.current().nextInt(0,matingPool.size());
              int parentAindex = ThreadLocalRandom.current().nextInt(0,matingPool.size());
              this.population[i]  = matingPool.get(parentAindex).CrossOver(matingPool.get(parentBindex));
        }
    }


    void ShowBest(){
         bestfitness = 0;
         this.bestindex = 0;

         for (int i =0 ;i < this.population.length ; i++){
              if (bestfitness < this.population[i].fitness){
                    bestfitness = this.population[i].fitness;
                    this.bestindex = i;
                    this.BestPhrase.phrase = this.population[i].phrase;
              }
         }
         System.out.println(this.population[this.bestindex].phrase);
    }


    void mutation(){
         Random rand = new Random();

         for (int i=0  ;i < this.population.length ; i++){
              float mutationRate = rand.nextFloat();
              if (this.MutationRate >= mutationRate){
                   int randChar = ThreadLocalRandom.current().nextInt(0,this.lengthOfPhrase);
                   char randomCharacter = (char)ThreadLocalRandom.current().nextInt(32,127);
                   this.population[i].phrase[randChar] = randomCharacter;
              }
        }
    }


    boolean isDone(){
         if (this.bestfitness == this.lengthOfPhrase){
              return true;
          }
          return false;
     }


     void showInfo(int Gen){
           System.out.println("\n\n-------------------------------------------------------------------------------------");
           System.out.println("Number Of Generations : " + Gen);
           System.out.print("Generated Phrase : ");
           System.out.print(this.population[this.bestindex].phrase);
           System.out.println("\n\nEvolution is Over....");
           System.out.println("-----------------------------------------------------------------------------------------");
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
               phrase[i] = (char)ThreadLocalRandom.current().nextInt(32,127);
          }
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
