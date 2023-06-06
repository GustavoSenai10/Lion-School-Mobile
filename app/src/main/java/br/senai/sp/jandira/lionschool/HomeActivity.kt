package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.colorspace.ColorSpaces
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
import br.senai.sp.jandira.lionschool.model.Curso
import br.senai.sp.jandira.lionschool.model.CursoList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.sign


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            homeScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun homeScreen(){


    //
    val context = LocalContext.current

    //Fontes
    val fontFamily = FontFamily(
        Font(R.font.grenze_regular),
        Font(R.font.roboto_medium)
    )
    //Vairavel de estados

    val texState = remember{
        mutableStateOf("")
    }

    var listCursos = remember{
        mutableStateListOf<br.senai.sp.jandira.lionschool.model.Curso>()
    }

    var call = RetrofitFactory().getCourseService().getCursos()

    call.enqueue(object : Callback<CursoList>{
        override fun onResponse(
            call: Call<CursoList>,
            response: Response<CursoList>
        ) {
            listCursos = response.body()!!.cursos.toMutableStateList()
        }

        override fun onFailure(call: Call<CursoList>, t: Throwable) {
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
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.courses),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(weight = 400),
                    color = colorResource(id = R.color.primary_color)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = texState.value,
                    onValueChange = {

                        texState.value = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    label = {
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "",
                        )
                    },
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn() {
                items(listCursos) {

                    Spacer(modifier = Modifier.height(30.dp))
                    Card(
                        onClick = {
                            var openStudents = Intent(context, Students::class.java)

                            openStudents.putExtra("sigla", it.sigla)
                            context.startActivity(openStudents)
                        },
                        modifier = Modifier
                            .height(100.dp)
                            .width(600.dp),
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = colorResource(id = R.color.primary_color)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = it.icone,
                                    contentDescription = "Curso Icone",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 10.dp)
                                )
                                Column() {
                                    Text(
                                        text = it.sigla,
                                        color = colorResource(id = R.color.white),
                                        modifier = Modifier.padding(start = 20.dp)
                                    )

                                    Text(
                                        text = it.nome,
                                        color = colorResource(id = R.color.white),
                                        modifier = Modifier.padding(start = 20.dp)
                                    )
                                    Text(
                                        text = it.carga,
                                        color = colorResource(id = R.color.white),
                                        modifier = Modifier.padding(start = 20.dp)
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
