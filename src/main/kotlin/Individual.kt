
// individualClass , the first item is showing items are taken or not
// the second parameter is individual weight
// and the third id fitness of the individual
class Individual(val itemsArray: Array<Boolean?> = Array(ProblemProperties.populationCount){false},
                 var weightSum:Int = 0,
                 var fitness:Int = 0){
    init {

    }
}