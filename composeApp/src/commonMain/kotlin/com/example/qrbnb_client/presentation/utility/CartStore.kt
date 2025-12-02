package com.example.qrbnb_client.presentation.utility

object CartStore {
    var seatingId: String? = null

    val quantities = mutableMapOf<String, Int>()

    fun updateItem(
        itemId: String,
        quantity: Int,
    ) {
        println("Seating ID = $seatingId")
        if (quantity <= 0) {
            quantities.remove(itemId)
        } else {
            quantities[itemId] = quantity
        }

        println(" CART UPDATED $quantities")
    }
}
