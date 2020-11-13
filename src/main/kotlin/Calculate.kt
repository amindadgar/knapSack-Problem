class Calculate(private val Individuals: ArrayList<Individual>) {

    fun average():Int{
        var sumOfFitness = 0
        for (individual in Individuals){
            sumOfFitness += individual.fitness
        }
        return sumOfFitness / Individuals.size
    }

}