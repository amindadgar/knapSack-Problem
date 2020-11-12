class Start(private val autoInitialize:Boolean = true,
            private val autoInitializerCount:Int = 30,
            private val weightCapacity:Int = 165) {
    init {
        ProblemProperties.populationCount = autoInitializerCount
        if (autoInitialize)
            initItems()
        ProblemProperties.weightCapacity = this.weightCapacity
        InitPopulation(ProblemProperties.Items)

        RouletteWheel().roll(ProblemProperties.Individuals)
        Mutation(ProblemProperties.Individuals)
    }
    private fun initItems(){
        for (i in 0..autoInitializerCount)
            ProblemProperties.Items.add(
                index = i,
                element = Item(
                    No = i ,
                    fitness =  (0 until 100).random()
                    ,weight = (0 until 100).random()
                )
            )
    }
    private fun initItemsFromFile(){
        // TODO
    }

}