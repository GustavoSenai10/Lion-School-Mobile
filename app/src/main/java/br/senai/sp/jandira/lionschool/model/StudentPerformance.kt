package br.senai.sp.jandira.lionschool.model

data class StudentPerformance (
    var nome:String,
    var foto:String,
    var matricula:String,
    var  status:String,
    var  disciplinas: List<subjects>
    )