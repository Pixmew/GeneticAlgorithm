//Import Statements for Random numbers Generation
import java.util.concurrent.*;
import java.util.*;

//This is the Main Class which handels the Population Class
class PhraseViewer{

    public static void main(String[] args){
       //This Stores the Phrase You want To Generate
       String targetPhrase = "To be or not to be,That is the question!";
       //This contains the number of Population of Evolutionary Phrases
       int population = 1000;
       //This stores The mutation Rate which should be Very small Number
       float mutationRate = 0.01f;
       //Initializeing targetLength Variable to be the Length(Number of Character) of Phrase
       int targetLength = targetPhrase.length();
       //Boolean Variable which keeps track That is to Evolve another Generation or Not
       boolean isDone = false;

       //Generation the Actual population objects Which internally Keeps Track of number of Phrases to Evolve
       Population pop = new Population(targetPhrase,targetLength,population,mutationRate);

       //This counts the number of Generations was Required to Evolve The Wanted Phrase
       int Generations = 0;
       //This Loop Runs untill the Target Phrase is Evolved
       while (!isDone){
           //This Function Calculates Fitness of All Available Population(Phrases)
           pop.CalcualateFitness();
           //This Function Generates new Phrase By combining 2 Random Phrases
           pop.Reproduction();
           //This Function Mutates The Newly Generated Phrases by very small amount to Maintain Varity and Not to Get Stuck with Same Phrases in the Whole population
           pop.mutation();
           //Checks if the Target Phrase is Evolved or not to Exit the Loop
           //returns True if Target Phrase is Evolved
           isDone = pop.isDone();
           //Increment the count od Generations by 1
           Generations++;
         }
         //When the Evolution of Phrases is completed then Show Result Summary
         pop.showInfo(Generations);
     }
}





//Class Population manages the Dna Objects(Phrases) and Performs Various Operation on it
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
