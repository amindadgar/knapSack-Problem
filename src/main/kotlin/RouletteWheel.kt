class RouletteWheel() {

    fun roll(individuals: ArrayList<Individual>):ArrayList<Individual>{
        val parentArray = arrayListOf<Individual>()
        // select an individual with probability
        val fitnessLineArray = arrayListOf<Int>()
        var fitnessSum = 0
        for ((index, individual) in individuals.withIndex()){
            fitnessSum += individual.fitness
            fitnessLineArray.add(index,fitnessSum)
        }
        // select an individual number
        // choose parents with count of ParentCount
        for (parentNo in 0 until ProblemProperties.populationCount) {
            val randomIndividual = (0 until fitnessSum).random()

            for ((index, fitnessLine) in fitnessLineArray.withIndex()) {
                if (randomIndividual <= fitnessLine) {
                    parentArray.add(individuals[index])
                    break
                }
            }
        }

        return parentArray
    }
}