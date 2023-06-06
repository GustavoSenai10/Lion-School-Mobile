package br.senai.sp.jandira.lionschool.service

import retrofit2.Call
import br.senai.sp.jandira.lionschool.model.CursoList
import br.senai.sp.jandira.lionschool.model.Students
import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.http.GET
import retrofit2.http.Query


interface  CourseService{


    //https://projeto-lion-school.cyclic.app/v1/lion-school/

    @GET("cursos")
    fun getCursos(): Call<CursoList>

    @GET("informacoes")
    fun getStudents(@Query("cursos")students: String):Call<StudentsList>
}

