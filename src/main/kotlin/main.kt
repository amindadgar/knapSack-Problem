


fun main(args: Array<String>){

    val startTime = System.currentTimeMillis()
    // autoInitialize = false -> we will read from file
    // autoInitialize = true -> we will make items randomly
    Start(autoInitialize = false)
    val endTime = System.currentTimeMillis()
    println()
    println("Execution Time : ${endTime - startTime} ms")

}
