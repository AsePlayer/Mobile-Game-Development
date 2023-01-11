// Code by Ryan Scott
fun shippingCost(amount: Double, international: Boolean): Double {
    val fee: Double
    if(international) {
        // 15% shipping fee, with a maximum of $50
        fee = if(.15 * amount > 50) {50.0} else {.15 * amount}
    }
    else if(amount >= 75) {
        // US Orders $75+ don't have shipping
        fee = 0.0
    }
    else {
        // US Orders less than $75 have a 10% shipping fee
        fee = .10 * amount
    }
    return fee
}
fun main(args: Array<String>) {
    val total = readLine()!!.toDouble()
    val international = readLine()!!.toBoolean()    
    
    println(shippingCost(total, international))
}