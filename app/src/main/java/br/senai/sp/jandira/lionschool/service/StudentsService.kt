package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.Students
import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentsService {

    @GET("alunos")
    fun getStudents(): Call<StudentsList>

    @GET("alunos/")
    fun getStudentsPerCoursesAndStatus(
        @Query("cursos") cursos: String,
        @Query("status") status: String
    ): Call<StudentsList>

    @GET("alunos/")
    fun getStudentsPerCourses(
        @Query("cursos") cursos: String
    ): Call<StudentsList>

    @GET("aluno/{matricula}")
    fun getStudentsPerRegistration(
        @Path("matricula") matricula:String
    ): Call<Students>


}