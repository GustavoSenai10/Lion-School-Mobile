package br.senai.sp.jandira.lionschool.service

import retrofit2.Call
import br.senai.sp.jandira.lionschool.model.CursoList
import retrofit2.http.GET


interface  CourseService{


    //https://projeto-lion-school.cyclic.app/v1/lion-school/

    @GET("cursos")
    fun getCursos(): Call<CursoList>

}

