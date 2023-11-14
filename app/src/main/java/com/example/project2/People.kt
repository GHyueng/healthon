package com.example.project2

enum class Egender{
    MALE,
    FEMALE
}
data class People(val name:String,
                  val gender: Egender) {

}