package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user

// Throw this exception when a nickname has not a correct format.
class InvalidNicknameFormatException(invalidNickname: String):
        RuntimeException("$invalidNickname has not a valid nickname format!")