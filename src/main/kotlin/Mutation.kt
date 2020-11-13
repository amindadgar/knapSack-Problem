class Mutation(private val individuals:ArrayList<Individual>) {


    fun start():ArrayList<Individual>{
        val newPopulation:ArrayList<Individual> = arrayListOf()
        for ((index,individual) in individuals.withIndex()){

            val rand = (0..individual.itemsArray.size).random()
            val newIndividualItems = individual.itemsArray
            newIndividualItems[rand] = !newIndividualItems[rand]
            var childWeight = 0
            var childFitness = 0
            newIndividualItems.forEachIndexed { i, item ->
                if (item){
                    childWeight += ProblemProperties.Items[i].weight
                    childFitness += ProblemProperties.Items[i].fitness
                }
            }

            newPopulation.add(index,Individual(newIndividualItems,childWeight,childFitness))
        }
        return newPopulation
    }
}