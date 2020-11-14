import ProblemProperties.populationCount
import kotlin.random.Random.Default.nextBoolean

class InitPopulation(private val items: ArrayList<Item>) {



    init {
        create()
    }
    private fun create(){

        for (individualNo in 0 until populationCount) {
            val individual = Individual()
            var weightSum = 0
            var i = 0
            while ((weightSum + items[i].weight) <= ProblemProperties.weightCapacity &&
                i < items.size - 1) {

                if (nextBoolean()) {
                    weightSum += items[i].weight
                    individual.itemsArray[i] = true
                    individual.fitness += items[i].fitness
                }
                i++
            }
            individual.weightSum = weightSum
//            println("individual weight $weightSum")

            ProblemProperties.Individuals[ProblemProperties.NowGeneration].add(individualNo, individual)
        }
    }
}