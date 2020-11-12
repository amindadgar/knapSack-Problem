object ProblemProperties {
    var weightCapacity:Int = 165
    var Items:ArrayList<Item> = arrayListOf()
    var populationCount:Int = 30

    // individualArray , the first item is showing items are taken or not
    // the second parameter is fitness
    var Individuals:ArrayList<Individual> = arrayListOf()
    const val ParentCount:Int = 15
    val mutationPoints = Pair(2,5)
}