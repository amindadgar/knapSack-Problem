class Calculate() {

    fun average(Individuals: ArrayList<Individual>):Int{
        var sumOfFitness = 0
        var zeroFitnessCount = 0
        for (individual in Individuals){
            if (individual.fitness != 0)
                sumOfFitness += individual.fitness
            else
                zeroFitnessCount++
        }
        return sumOfFitness / Individuals.size
    }
    fun maxFitness(individuals: ArrayList<Individual>):Int{
        var max = 0
        for (individual in individuals){
            if (max < individual.fitness)
                max = individual.fitness
        }
        return max
    }

}