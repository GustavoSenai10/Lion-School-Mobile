package br.senai.sp.jandira.lionschool

import android.os.Bundle
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class PerformanceStudents : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var matricula = intent.getStringExtra("matricula")?: ""
            PerformanceScreen(matricula.toString())
        }
    }
}


@Preview
@Composable
fun PerformanceScreen(matricula:String){

    val fontFamily = FontFamily(
        Font(R.font.grenze_regular),
        Font(R.font.roboto_medium)
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
                    text = stringResource(id = R.string.performance),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(weight = 400),
                    color = colorResource(id = R.color.primary_color)
                )
                Card(
                    modifier = Modifier
                        .width(34.dp)
                        .height(34.dp).background(color = Color.Green)
                ) {

                }
                Spacer(modifier = Modifier.height(15.dp))
            }

            }

        }
    }



