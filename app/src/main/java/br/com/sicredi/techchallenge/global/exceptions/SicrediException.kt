package br.com.sicredi.techchallenge.global.exceptions

open class SicrediException(message: String? = null) : Exception(message)

class InvalidEventIdException : SicrediException()
class InvalidEmailException : SicrediException()
class InvalidNameException : SicrediException()
class EventsConversionException : SicrediException()
class InvalidEventDetailsException : SicrediException()
class InvalidEventsException : SicrediException()