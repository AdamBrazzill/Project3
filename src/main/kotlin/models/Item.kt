package models

data class Item (
    var itemID: Int = 0,
    var itemContents: String,
    var isItemComplete: Boolean = false
){
    override fun toString() =
        if (isItemComplete)
            "$itemID: $itemContents (Complete)"
    else
        "$itemID: $itemContents (TODO)"
}