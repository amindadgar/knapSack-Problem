class Mutation(private val parents:ArrayList<Individual>) {

    fun start():ArrayList<Individual>{
        val newPopulation:ArrayList<Individual> = arrayListOf()


        val firstPoint = ProblemProperties.mutationPoints.first
        val secondPoint = ProblemProperties.mutationPoints.second
        var index = 0
        while (index < parents.size) {
            val father = parents[index++].itemsArray
            val mother = parents[index++].itemsArray

            val firstChild =
                father.copyOfRange(0, firstPoint) +
                        mother.copyOfRange(firstPoint, secondPoint) +
                        father.copyOfRange(secondPoint, father.size)
            val secondChild =
                mother.copyOfRange(0, firstPoint) +
                        father.copyOfRange(firstPoint, secondPoint) +
                        mother.copyOfRange(secondPoint, mother.size)

            var firstChildWeight = 0
            var firstChildFitness = 0
            firstChild.forEachIndexed { index1, item ->
                if (item) {
                    firstChildWeight += ProblemProperties.Items[index1].weight
                    firstChildFitness += ProblemProperties.Items[index1].fitness
                }
            }
            var secondChildWeight = 0
            var secondChildFitness = 0
            secondChild.forEachIndexed { index2, item ->
                if (item) {
                    secondChildWeight += ProblemProperties.Items[index2].weight
                    secondChildFitness += ProblemProperties.Items[index2].fitness

                }
            }

            // if children weight was more than out capacity we would set their fitness to zero
            if (firstChildWeight > ProblemProperties.weightCapacity) {
                firstChildFitness = 0
            }
            if (secondChildWeight > ProblemProperties.weightCapacity) {
                secondChildFitness = 0
            }
            newPopulation.add(index - 2, Individual(firstChild,firstChildWeight,firstChildFitness))
            newPopulation.add(index - 1, Individual(secondChild,secondChildWeight,secondChildFitness))
        }

        return newPopulation
    }
}