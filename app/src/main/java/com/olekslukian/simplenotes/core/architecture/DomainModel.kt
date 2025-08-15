package com.olekslukian.simplenotes.core.architecture

abstract class DomainModel : IValidable {
   abstract val validationProperties: List<IValidable>

   override val isValid: Boolean
         get() = validationProperties.all { it.isValid }
}