import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun simpleFlow()=flow{
    for( i in 1..3)
    {
        delay(100)
        emit(i+(i*i))
    }
}


fun main(args: Array<String>) = runBlocking{
    println("Hello World!")
    println(listOf("a","b","cccc").associate { it.length to it  })

    val stuff = listOf("a","b",null,null)
    println(stuff.last().orEmpty())

    val paymentsRegistry = mapOf("1111" to mutableMapOf(11 to 22.2));
    paymentsRegistry["1111"]?.putAll(mapOf(22 to 222.1,33 to 333.1))
    println(paymentsRegistry)

    launch{
        for(i in 1..50){
            print(" $i")
            delay(10)
        }
    }
    val sfres = simpleFlow()
    println("Collecting the flow")
    sfres.collect { println("\ncollected $it") }
}
