package ru.nnedition.kmenu.menu

data class Menu(
    val id: String,
    var title: String = "ExampleTitle",
    var type: InventoryType = InventoryType.CHEST,
    var size: Int = 27,
)

fun buildMenu(id: String, func: Menu.() -> Unit) = Menu(id).apply(func)