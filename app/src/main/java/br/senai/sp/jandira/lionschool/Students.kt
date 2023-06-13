package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Students : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var siglaCurso = intent.getStringExtra("sigla")?: ""
            studentsScreen(siglaCurso.toString())
        }
    }
}

@Preview
@Composable
fun studentsScreen(siglaCurso:String){

    //Fontes
    val fontFamily = FontFamily(
        Font(R.font.grenze_regular),
        Font(R.font.roboto_medium)
    )
    var listStudents = remember{
        mutableStateListOf<br.senai.sp.jandira.lionschool.model.Students>()
    }

    var call = RetrofitFactory().getStudentsService().getStudentsPerCoursesAndStatus(curso = "DS", status = "Cursando" )

    call.enqueue(object : Callback<StudentsList> {
        override fun onResponse(
            call: Call<StudentsList>,
            response: Response<StudentsList>
        ) {
             listStudents = response.body()!!.informacoes.toMutableStateList()
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {
            Log.i("ds2t", "onFailure: ${t.message}")
        }
    }
    )



    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.width(1000.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_image),
                    contentDescription = "Logo do lion School",
                    modifier = Modifier.size(width = 50.dp, height = 60.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    modifier = Modifier.width(50.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight(400),
                        fontSize = 20.sp
                    )

                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            //Linha amarela
            Row(
                modifier = Modifier.width(5000.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(5000.dp)
                        .height(2.dp)
                        .background(
                            Color(255, 194, 62),
                            shape = RoundedCornerShape(bottomStart = 16.dp)
                        )
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier.width(600.dp)
            ) {
                Row(
                    modifier = Modifier
                        .width(600.dp)
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.students),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(weight = 400),
                        color = colorResource(id = R.color.primary_color)
                    )

                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .width(600.dp)
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.subtitle),
                        fontSize = 15.sp,
                        fontWeight = FontWeight(weight = 400)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = stringResource(id = R.string.studying),
                        fontSize = 15.sp,
                        fontWeight = FontWeight(weight = 400)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = stringResource(id = R.string.year),
                        fontSize = 15.sp,
                        fontWeight = FontWeight(weight = 400)
                    )
                }
                
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                items(listStudents) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            AsyncImage(
                                model = it.foto,
                                contentDescription = "Foto do Aluno",
                                modifier = Modifier
                                    .size(80.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column() {
                                Text(
                                    text = it.nome,
                                    fontSize = 35.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
