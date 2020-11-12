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
            while (weightSum <= populationCount && i < items.size) {

                if (nextBoolean()) {
                    weightSum += items[i].weight
                    individual.itemsArray[i] = true
                    individual.fitness += items[i].fitness
                }
                i++
            }
            individual.weightSum = weightSum

            ProblemProperties.Individuals.add(individualNo, individual)
        }
    }
}