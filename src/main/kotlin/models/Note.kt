package models
import utils.Utilities
data class Note(var noteId: Int = 0,
                var noteTitle: String,
                var notePriority: Int,
                var noteCategory: String,
                var isNoteArchived: Boolean = false,
                var items : MutableSet<Item> = mutableSetOf())
{
private var lastItemId = 0
    private fun getItemId() = lastItemId++

    fun addItem(item: Item) : Boolean{
        item.itemID = getItemId()
        return items.add(item)
    }

    fun numberOfItems() = items.size

    fun findOne(id: Int): Item? {
        return items.find{ item -> item.itemID == id }
    }

    fun delete(id: Int): Boolean {
        return items.removeIf { item -> item.itemID == id}

    }

    fun update(id: Int, newItem : Item): Boolean {
        val foundItems = findOne(id)

        if (foundItems != null){
            foundItems.itemContents = newItem.itemContents
            foundItems.isItemComplete = newItem.isItemComplete
            return true
        }
        return false
    }

    fun listItems() =
        if (items.isEmpty())  "\tNO ITEMS ADDED"
        else  Utilities.formatSetString(items)

    override fun toString(): String{
        val archived = if (isNoteArchived) 'Y' else 'N'
        return "$noteId: $noteTitle,Priority($notePriority), Category($noteCategory), Archived($archived) \n${listItems()}"
    }
    fun checkNoteCompletionStatus(): Boolean{
        if(items.isNotEmpty()){
            for (item in items){
                if(!item.isItemComplete){
                    return false
                }
            }
        }
        return true
    }
}