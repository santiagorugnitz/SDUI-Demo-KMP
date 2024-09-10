package com.sdui.dashboard.model.database

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID


object ScreenTable : StringIdTable() {
    val json = varchar("json", Int.MAX_VALUE)
}

class ScreenDAO(id: EntityID<String>) : Entity<String>(id) {

    companion object : StringEntityClass<ScreenDAO>(ScreenTable)

    var json by ScreenTable.json
}
