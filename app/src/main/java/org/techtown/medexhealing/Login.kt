package org.techtown.medexhealing

data class Login(
    var code: Int,
    var msg: String,
    var name: String,
    var serialnum: String
)