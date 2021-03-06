import ProblemProperties.Individuals
import ProblemProperties.NowGeneration
import java.io.File

class Start(private val autoInitialize:Boolean = true,
            private val autoInitializerCount:Int = 30,
            private val weightCapacity:Int = 165) {
    init {
        ProblemProperties.populationCount = autoInitializerCount
        if (autoInitialize) {
            initItemsRandom()
            ProblemProperties.weightCapacity = this.weightCapacity
        }
        else
            initItemsFromFile()

        val startTime = System.currentTimeMillis()
        InitPopulation(ProblemProperties.Items)
        // select parents
        var population = RouletteWheel().roll(Individuals[NowGeneration])
        Individuals[NowGeneration] = population

        val calculate = Calculate()
        var generationMax = 0
        for (i in 0 until ProblemProperties.genarationCount - 1) {

            val children  = ProduceChildren(Individuals[NowGeneration]).produceChild()
            println("Generation No$NowGeneration average: ${calculate.average(children)}")
            val max = calculate.maxFitness(Individuals[NowGeneration])
            if (generationMax < max){
                generationMax = max
            }
            
            val childrenAndParents = population
            childrenAndParents.addAll(children)
            population = RouletteWheel().roll(childrenAndParents)
            // add the new generation !
            Individuals[++NowGeneration] = population
        }
        println()
        println("max $generationMax")
        val endTime = System.currentTimeMillis()
        println()
        println("Execution Time : ${endTime - startTime} ms")
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
        val file = File("src/main/resources/items3.txt")
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