package com.treyherman.doordashlite.scene.discoverrestaurants.model

data class UIRestaurant(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val foodDescription: String,
    val status: String
) {
    // equals/hashCode methods generated by intellij
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UIRestaurant

        if (id != other.id) return false
        if (name != other.name) return false
        if (imageUrl != other.imageUrl) return false
        if (foodDescription != other.foodDescription) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + foodDescription.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }
}
