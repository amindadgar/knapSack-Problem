import ProblemProperties.Individuals
import ProblemProperties.NowGeneration

class Start(private val autoInitialize:Boolean = true,
            private val autoInitializerCount:Int = 30,
            private val weightCapacity:Int = 200) {
    init {
        ProblemProperties.populationCount = autoInitializerCount
        if (autoInitialize)
            initItemsRandom()
        ProblemProperties.weightCapacity = this.weightCapacity
        InitPopulation(ProblemProperties.Items)

        var population = RouletteWheel().roll(Individuals[NowGeneration])
        Individuals[NowGeneration] = population

        for (i in 0..28) {
            population = Mutation(Individuals[NowGeneration])
                    .start()
            NowGeneration++
            population = RouletteWheel().roll(population)
            val calculateAverage = Calculate(population).average()
            println("Generation No$NowGeneration average: $calculateAverage")
            Individuals[NowGeneration] = population
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
        // TODO
    }

}