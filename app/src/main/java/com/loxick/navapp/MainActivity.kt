package com.loxick.navapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.loxick.navapp.ui.theme.NavAppTheme
import kotlinx.coroutines.flow.Flow

var loginGlobal = ""
var passwordGlobal = ""

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "loginScreen") {
            composable("loginScreen"){
            loginScreen {
                    if (loginGlobal == "admin" && passwordGlobal == "admin") {
                    navController.navigate("newsScreen")
                    }
                }
            }
            composable("newsScreen"){
                newsScreen {
                    navController.navigate("loginScreen")
                    }
                }
            }
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun loginScreen(onClick: () -> Unit) {
        var login = remember {
            mutableStateOf("")
        }
        var password = remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(modifier = Modifier.padding(20.dp),text = "PuppyNews", fontSize = 50.sp)
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(modifier = Modifier
                        .padding(10.dp),value = login.value, onValueChange = {login.value = it; loginGlobal = login.value} , label = { Text(
                        text = "Login"
                    )})
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(modifier = Modifier
                        .padding(10.dp),value = password.value, onValueChange = {password.value = it; passwordGlobal = password.value}, label = { Text(
                        text = "Password"
                    )} )
                }
            }

            Button(modifier = Modifier
                .padding(10.dp)
                .width(140.dp)
                .height(50.dp), onClick = { onClick() }) {
                Text(text = "Login")
            }
        }
    }


    @Composable
    private fun newsScreen(onClick: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan)
                .verticalScroll(ScrollState(0)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,

        ) {
            Text(text = "PuppyNews", fontSize = 40.sp)
            news(1)
            news(2)
            news(3)
            news(4)
            news(5)
            news(6)

        }
    }

@Composable
private fun news(number:Int){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp),
    ) {
        Row(modifier = Modifier,
            ) {
            Image(painter = painterResource(id = R.drawable.koala), contentDescription = "")
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                    ) {
                    Row(
                    ) {
                        Text(
                            text = "News ${number}", color = Color.Gray, fontSize = 30.sp
                        )
                    }
                    Text(
                        text = "11.03.2004", color = Color.Gray, fontSize = 30.sp
                    )
                    Box(){
                        Text(
                            text = "Чет случилось с коалами и теперь здесь новости про них", color = Color.Gray, fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun mainScreen(
    maintViewModel: MaintViewModel = viewModel(factory = MaintViewModel.factory)
){
    var itemList = maintViewModel.itemList.collectAsState(initial = emptyList())
}
