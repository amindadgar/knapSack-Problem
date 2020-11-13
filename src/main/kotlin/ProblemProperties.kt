object ProblemProperties {
    var weightCapacity:Int = 200
    var Items:ArrayList<Item> = arrayListOf()
    var populationCount:Int = 30
    var genarationCount = 30
    // individualArray , the first item is showing items are taken or not
    // the second parameter is fitness
    var Individuals:Array<ArrayList<Individual>> = Array(populationCount){ arrayListOf<Individual>()}
    const val ParentCount:Int = 15
    val mutationPoints = Pair(2,5)
    var NowGeneration = 0
}