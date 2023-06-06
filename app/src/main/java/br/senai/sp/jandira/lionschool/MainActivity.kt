package br.senai.sp.jandira.lionschool


import android.content.Intent
import br.senai.sp.jandira.lionschool.R
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import kotlinx.coroutines.delay
import okhttp3.internal.wait

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            hallScreen()
        }
    }
}
@Preview
@Composable
fun hallScreen(){
    val navController = rememberNavController()
    var context = LocalContext.current
    var fontFamily = FontFamily(
        Font(R.font.grenze_regular),
        Font(R.font.roboto_medium)
    )
    NavHost(
        navController = navController ,
        startDestination = "Hall"
    ){
        composable("Hall"){
            SplashScreen(navController = navController)
        }
        composable("hallScreen"){
            Surface(
                modifier = Modifier.
                fillMaxSize(),
                color = colorResource(id =R.color.primary_color )
            ) {
                Column(
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .background(color = colorResource(id = R.color.primary_color)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_image),
                        modifier = Modifier
                            .height(75.dp)
                            .width(60.dp),
                        contentDescription = "Logo Lion School"
                    )
                    Text(
                        text = stringResource(id = R.string.welcome),
                        fontFamily = fontFamily,
                        fontSize = 24.sp,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = stringResource(id = R.string.description1_welcome),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(id = R.string.description2_welcome),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(id = R.string.description3_welcome),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(110.dp))

                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .width(200.dp)
                            .background(color = colorResource(id = R.color.primary_color)),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(

                            onClick = {

                                val openHomeActivity = Intent(context, HomeActivity::class.java)

                                context.startActivity(openHomeActivity)
                                      },
                            shape = RoundedCornerShape(16.dp),

                            ) {
                            Text(
                                text = stringResource(id =R.string.start_welcome )
                            )
                        }
                    }

                }

            }
        }
    }

}
@Composable
fun SplashScreen(navController: NavController){

    val offset = Offset(5.0f, 10.0f)


    val fontFamily = FontFamily(
        Font(R.font.grenze_regular),
        Font(R.font.roboto_medium)
    )

    LaunchedEffect(
        key1 = true

    ){
        delay(3000)
        navController.navigate("hallScreen")

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_color)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontFamily = fontFamily,
            color = Color.White,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                color = Color.Black,
                offset = offset,
                blurRadius = 3f
            )
        )



        )
    }

}