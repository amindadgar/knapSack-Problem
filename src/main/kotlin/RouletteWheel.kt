class RouletteWheel(private val individuals:ArrayList<Individual>) {

    init {
        roll()
    }
    private fun roll():Individual{
        val rollArray = arrayListOf<Int>()

        for (index in 0 until ProblemProperties.individuals.size){
            // select how many times to add that item in array
            val repeatCount = (ProblemProperties.individuals[index].probability!! * 10000).toInt()
            for (i in 0..repeatCount){
                rollArray.add(index)
            }
        }
        val individualNo = (0..ProblemProperties.individuals.size).random()

        return ProblemProperties.individuals[rollArray[individualNo]]
    }
}