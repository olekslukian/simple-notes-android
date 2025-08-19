package com.olekslukian.simplenotes.data.models.notes.request

data class UpdateNoteDto(
    val id: Int,
    val title: String?,
    val body: String?,
)