class ProduceChildren(parents:ArrayList<Individual>) {
    private val parents :ArrayList<Individual> = arrayListOf()
    init {
        // copy parents array so that would not be pass by reference
        this.parents.addAll(parents)
    }

    fun produceChild():ArrayList<Individual>{

        val newPopulation:ArrayList<Individual> = arrayListOf()



        val rolledParents = RouletteWheel().roll(parents)

        var index = 0
        while (index < rolledParents.size) {
            // select mutaion points randomly
            val firstPoint = (0 until rolledParents[index].itemsArray.size).random()
            val secondPoint = (firstPoint until rolledParents[index].itemsArray.size).random()

            val father = rolledParents[index++].itemsArray
            val mother = rolledParents[index++].itemsArray

            val firstChild =
                father.copyOfRange(0, firstPoint) +
                        mother.copyOfRange(firstPoint, secondPoint) +
                        father.copyOfRange(secondPoint, father.size)
            val secondChild =
                mother.copyOfRange(0, firstPoint) +
                        father.copyOfRange(firstPoint, secondPoint) +
                        mother.copyOfRange(secondPoint, mother.size)

            // with the probability of 0.3 we would have mutation
            var ran = (1..10).random()
            if (ran <= 3){
                val randomItemNumberMutation = (0 until firstChild.size).random()
                firstChild[randomItemNumberMutation] = !firstChild[randomItemNumberMutation]
            }

            // calculate fitness and weight of first child
            var firstChildWeight = 0
            var firstChildFitness = 0
            firstChild.forEachIndexed { index1, item ->
                if (item) {
                    firstChildWeight += ProblemProperties.Items[index1].weight
                    firstChildFitness += ProblemProperties.Items[index1].fitness
                }
            }

            // with the probability of 0.3 we would have mutation
            ran = (1..10).random()
            if (ran <= 3){
                val randomItemNumberMutation = (0 until secondChild.size).random()
                secondChild[randomItemNumberMutation] = !secondChild[randomItemNumberMutation]
            }

            // calculate fitness and weight of second child
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