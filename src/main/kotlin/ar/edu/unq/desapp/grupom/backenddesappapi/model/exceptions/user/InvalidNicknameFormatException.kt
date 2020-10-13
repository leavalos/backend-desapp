package ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user

class InvalidNicknameFormatException(invalidNickname: String):
        RuntimeException("$invalidNickname has not a valid nickname format!")