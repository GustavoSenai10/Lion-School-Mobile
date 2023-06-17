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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.StudentPerformance
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Response


class PerformanceStudents : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var matricula = intent.getStringExtra("matricula")
            PerformanceScreen(matricula.toString())
        }
    }
}




@Preview
@Composable
fun PerformanceScreen(matricula:String){

    //Variavel de contexto
    val context = LocalContext.current
    //Fontes
    val fontFamily = FontFamily(
        Font(R.font.grenze_regular),
        Font(R.font.roboto_medium)
    )
    //alunos
    var aluno by remember {
        mutableStateOf(StudentPerformance(
            "",
            "",
            "",
            "",
            emptyList()
        ))



    }

    val  call = RetrofitFactory().getStudentsService().getAlunosByMatricula(matricula)

    call.enqueue(object : retrofit2.Callback<StudentPerformance> {
        override fun onResponse(
            call: Call<StudentPerformance>,
            response: Response<StudentPerformance>
        ) {
            if (response.isSuccessful){
                val studentResponse = response.body()
                if (studentResponse != null){
                    aluno = studentResponse
                }
            }else{
                Log.e("teste", "Erro na resposta da API: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<StudentPerformance>, t: Throwable) {
            Log.i("teste", "onFailure: ${t.message} ")
        }

    })


    Log.i("tag","PerformanceStudents:${aluno}")






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
            //Card do aluno
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.performance),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(weight = 400),
                    color = colorResource(id = R.color.primary_color)
                )}
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .width(600.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = colorResource(id = R.color.primary_color)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = aluno.foto,
                            contentDescription = "Foto do Aluno",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = aluno.nome,
                            fontSize = 25.sp,

                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )

                    }
                }
                Spacer(modifier = Modifier.height(25.dp))

                //Performace do Aluno
                Card(
                    modifier = Modifier
                        .height(400.dp)
                        .width(300.dp)
                ){
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().background(color = Color.Gray),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        items(aluno.disciplinas) {
                            var barra = 2.4 * it.media.toDouble()
                            var corBarra = colorResource(id = R.color.primary_color)

                            if (it.media.toDouble() > 60) {
                                corBarra = colorResource(id = R.color.primary_color)
                            } else if (it.media.toDouble() < 60 && it.media.toDouble() > 50) {
                                corBarra = colorResource(id = R.color.segundary_color)
                            } else {
                                corBarra = Color.Red
                            }
                            Column(modifier = Modifier
                                .width(240.dp)
                                .height(40.dp)
                            ) {
                                Text(
                                    text = it.nomeDisciplina,
                                    fontWeight = FontWeight(700),
                                    fontSize = 12.sp,
                                    color = colorResource(id = R.color.black)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Box(
                                    modifier = Modifier
                                        .height(17.5.dp)
                                        .width(240.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            colorResource(id = R.color.primary_color)
                                        )
                                ){
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(
                                                corBarra
                                            )
                                            .width(barra.dp)
                                            .padding(0.dp, 0.dp, 5.dp, 0.dp),
                                        contentAlignment = Alignment.CenterEnd
                                    ){
                                        Text(
                                            text = it.media + "%",
                                            fontWeight = FontWeight(700),
                                            fontSize = 12.sp,
                                            color = colorResource(id = R.color.white)
                                        )
                                    }
                                }
                            }


                        }
                    }
                }
            }
        }
    }

