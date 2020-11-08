class Evaluate(private val individuals: ArrayList<Individual>) {


    init {
        computeProbability()
    }
    private fun computeProbability(){
        // get every individuals fitness
        // make sum of individuals fitness and compute probability of each
        // the individual having more fitness is better for us
        var weightSum = 0
        for (individual in individuals){
            weightSum += individual.fitness
        }
        for (individual in individuals) {
            individual.probability = individual.fitness.toFloat() / weightSum
        }
        for (individual in individuals) {
            println(individual)
        }
    }
}