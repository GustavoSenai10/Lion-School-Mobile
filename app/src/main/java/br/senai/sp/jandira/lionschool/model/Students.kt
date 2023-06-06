package br.senai.sp.jandira.lionschool.model

data class Students (

    val id: Long,
    val nome: String,
    val foto: String,
    val matricula: String,
    val sexo: String,
    val status: String,
    val curso: String,
    val dataConclusao:String
)