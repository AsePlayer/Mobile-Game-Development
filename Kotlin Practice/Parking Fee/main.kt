// Code by Ryan Scott
fun main(args: Array<String>) {
    var hours = readLine()!!.toDouble()
    var total: Double = 0.0
    if(hours <= 5) {
        total = hours *1.0
    } 
    else if (hours > 5 && hours < 24) {
        total = 5.0 + (hours - 5) * 0.5
    }
    else {
        total = Math.floor(hours/24) * 15.0 + (hours%24) * 0.5
    }
    println(total)
    }