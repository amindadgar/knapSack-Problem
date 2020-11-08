class Start(private val autoInitialize:Boolean = true,
            private val autoInitializerCount:Int = 30,
            private val weightCapacity:Int = 165) {
    init {
        if (autoInitialize)
            initPopulation()
        ProblemProperties.weightCapacity = this.weightCapacity
        evaluateFitness()
    }
    private fun initPopulation(){
        for (i in 0..autoInitializerCount)
            ProblemProperties.individuals.add(
                Individual(
                    No = i ,
                    fitness =  (0..100).random()
                )
            )
    }
    private fun evaluateFitness(){
        Evaluate(ProblemProperties.individuals)
    }
}