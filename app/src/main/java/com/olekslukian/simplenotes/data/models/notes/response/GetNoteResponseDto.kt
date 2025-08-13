package com.olekslukian.simplenotes.data.models.notes.response

data class GetNoteResponseDto(
    val id: String,
    val title: String,
    val body: String,
    val createdAt: String,
    val updatedAt: String,
)
