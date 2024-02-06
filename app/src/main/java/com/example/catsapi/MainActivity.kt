package com.example.catsapi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.catsapi.model.CatFacts
import com.example.catsapi.screens.HomeScreen
import com.example.catsapi.ui.theme.CatsApiTheme
import com.example.catsapi.utils.RetrofitInstance
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : ComponentActivity() {
    private var fact = mutableStateOf(CatFacts())
    @OptIn(DelicateCoroutinesApi::class)
    private fun makeRequest(){
        GlobalScope.launch(Dispatchers.IO){
            val response = try {
                RetrofitInstance.api.getRandomFact()
            }catch (e: HttpException){
                Toast.makeText(applicationContext,"Http Error: ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: HttpException){
                Toast.makeText(applicationContext,"An error ocourred: ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    fact.value = response.body()!!
                }
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsApiTheme {
                SetStatusBarColor(color = MaterialTheme.colorScheme.background)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val context = LocalContext.current
//                    var fact by remember{
//                        mutableStateOf(CatFacts())
//                    }
//
//                    val scope = rememberCoroutineScope()
//                    LaunchedEffect(key1 = true){
//                        scope.launch(Dispatchers.IO){
//                            val response = try {
//                                RetrofitInstance.api.getRandomFact()
//                            }catch (e: HttpException){
//                                Toast.makeText(context,"Http Error: ${e.message}",Toast.LENGTH_LONG).show()
//                                return@launch
//                            }catch (e: HttpException){
//                                Toast.makeText(context,"An error ocourred: ${e.message}",Toast.LENGTH_LONG).show()
//                                return@launch
//                            }
//                            if (response.isSuccessful && response.body() != null){
//                                withContext(Dispatchers.Main){
//                                    fact = response.body()!!
//                                }
//                            }
//                        }
//                    }
                    HomeScreen(fact = fact, makeRequest = ::makeRequest)
                }

            }

        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}
