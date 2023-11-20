package models

data class Item (
    var itemID: Int = 0,
    var itemContents: String,
    var isItemComplete: Boolean = false
)