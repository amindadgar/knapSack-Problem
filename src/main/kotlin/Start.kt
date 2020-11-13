import ProblemProperties.Individuals
import ProblemProperties.NowGeneration
import java.io.File

class Start(private val autoInitialize:Boolean = true,
            private val autoInitializerCount:Int = 30,
            private val weightCapacity:Int = 200) {
    init {
        ProblemProperties.populationCount = autoInitializerCount
        if (autoInitialize) {
            initItemsRandom()
            ProblemProperties.weightCapacity = this.weightCapacity
        }
        else
            initItemsFromFile()


        InitPopulation(ProblemProperties.Items)

        var population = RouletteWheel().roll(Individuals[NowGeneration])
        Individuals[NowGeneration] = population

        for (i in 0 until ProblemProperties.genarationCount - 1) {
            // if random number was more than 2 we would cross over ( 0.8 probability of cross over )
            // else we would mutate ( 0.2 probability of mutation )
            val random = (0..10).random()
            population = if (random > 2) {
                println("Cross over")
                Crossover(Individuals[NowGeneration])
                    .start()
            }
            else {
                println("Mutation")
                Mutation(population).start()
            }
            val calculateAverage = Calculate(population).average()
            population = RouletteWheel().roll(population)
            println("Generation No$NowGeneration average: $calculateAverage")

            // add the new generation !
            Individuals[++NowGeneration] = population
        }
    }
    private fun initItemsRandom(){
        for (i in 0..autoInitializerCount)
            ProblemProperties.Items.add(
                index = i,
                element = Item(
                    No = i ,
                    fitness =  (0 until 100).random()
                    ,weight = (0 until 100).random()
                )
            )
    }
    private fun initItemsFromFile(){
        val file = File("src/main/resources/Dataset.txt")
        var i = 0
        file.forEachLine {
            if (!it.contains(",")){
                // used for first line
                ProblemProperties.weightCapacity = it.toInt()
            }else {
                val stringData = it.replace(" ","")
                val data = stringData.split(",")

                // second line
                ProblemProperties.Items.add(
                    index = i,
                    element = Item(
                        No = i++ ,
                        fitness = data[1].toInt()
                        ,weight = data[0].toInt()
                    )
                )
            }
        }
        ProblemProperties.populationCount = i
    }

}