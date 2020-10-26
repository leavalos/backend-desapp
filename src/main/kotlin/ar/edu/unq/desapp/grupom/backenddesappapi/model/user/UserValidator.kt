package ar.edu.unq.desapp.grupom.backenddesappapi.model.user

import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.InvalidEmailFormatException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.InvalidNicknameFormatException
import ar.edu.unq.desapp.grupom.backenddesappapi.model.exceptions.user.InvalidPasswordFormatException

object UserValidator {

    /*
    A password is considered valid if all the following constraints are satisfied:
    Between 8 and 20 characters.
    At least one digit, one uppercase and one lowercase alphabet and one symbol (!@#$%&*()-+=^).
    It doesn't contain any white space.
    */
    private val passwordRegEx =
            """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?])(?=\S+$).{8,20}$"""
                    .toRegex()

    /*
    A nickname is considered valid if all the following constraints are satisfied:
    It contains at least 5 characters and at most 16 characters.
    Reject any whitespace or symbol.
    It contains at least one upper case alphabet.
    It contains at least one lower case alphabet.
    */
    private val nicknameRegEx: Regex =
            """^(?=.*[a-z])(?=.*[A-Z])(?=\S+$)(?=.{0}[^\W_]).{3,16}$"""
                    .toRegex()

    private val mailRegEx: Regex =
            """^((?!\.)[\w-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$"""
                    .toRegex()

    fun validateUser(mail: String, password: String, nickname: String) {
        this.validateMail(mail)
        this.validatePassword(password)
        this.validateNickname(nickname)
    }

    private fun validateMail(mail: String) {
        if (!this.mailRegEx.matches(mail)) {
            throw InvalidEmailFormatException(mail)
        }
    }

    private fun validatePassword(password: String) {
        if (!this.passwordRegEx.matches(password)) {
            throw InvalidPasswordFormatException()
        }
    }

    private fun validateNickname(nickname: String) {
        if (!this.nicknameRegEx.matches(nickname)) {
            throw InvalidNicknameFormatException(nickname)
        }
    }
}