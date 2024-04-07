package navigationBar

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import com.loxick.navapp.MaintViewModel
import com.loxick.navapp.R
import com.loxick.navapp.Recept
import com.loxick.navapp.loginGlobal
import com.loxick.navapp.passwordGlobal
import kotlinx.coroutines.GlobalScope
import navigationBar.currentUser

var recepts = emptyArray<Recept>()

var initialized = mutableStateOf(false)
@Composable
 fun loginScreen(context: Context,onClick: () -> Unit) {
    var login = remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDFB069)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier.height(300.dp)){
            val composition by rememberLottieComposition(LottieCompositionSpec.Asset("annim.json"))
            LottieAnimation(composition)
        }
        Text(modifier = Modifier.padding(20.dp), text = "Книга рецептов", fontSize = 50.sp, color = Color.White)
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(modifier = Modifier
                    .padding(10.dp),
                    value = login.value,
                    onValueChange = { login.value = it; loginGlobal = login.value },
                    label = {
                        Text(
                            text = "Login"
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.White
                        ))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(modifier = Modifier
                    .padding(10.dp),
                    value = password.value,
                    onValueChange = { password.value = it; passwordGlobal = password.value },
                    label = {
                        Text(
                            text = "Password"
                        )
                    }, colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.White))
            }
        }

        Button(modifier = Modifier
            .padding(10.dp)
            .width(140.dp)
            .height(50.dp)
            ,colors = ButtonDefaults.buttonColors(Color.Black), onClick = { onClick()}) {
            Text(text = "Login")
        }
    }
}

@Composable
fun receptScreen(mvms:MaintViewModel, onClick: (name:String, description:String) -> Unit) {
    BottomAppBar {

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDFB069))
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,

        ) {
        Text(text = "Быстрый поиск", fontSize = 30.sp, fontFamily = FontFamily.Monospace)
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(ScrollState(0)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            horisontalCards("Еда")
            horisontalCards("Напитки")
            horisontalCards("Быстрые блюда")
            horisontalCards("Легкие")
            horisontalCards("Бюджетные")
        }
        Text(text = "Все рецепты", fontSize = 40.sp, fontFamily = FontFamily.Monospace)
        recept(false,"Карбонара",
            "1 Спагетти варить 7-10 минут в кипящей подсоленной воде и откинуть на дуршлаг. \n" +
                    "2 В сковороде разогрейте оливковое масло, положите чеснок и слегка подрумяньте. \n" +
                    "3 Ветчину/бекон мелко нарежьте, добавьте к чесноку и обжаривайте 5 минут. \n" +
                    "4 Сыр пармезан натрите на мелкой терке. Желтки взбить со сливками, немного подсолить. \n" +
                    "5 Спагетти переложить в сотейник с чесноком и ветчиной/беконом. \n" +
                    "6 Добавить взбитые желтки и тёртый сыр, перемешать. Держать на огне 3 минуты. \n" +
                    "7 Посыпать молотым перцем, украсить зеленью и подавать на стол.\n"
            ,4, mvm = mvms){name, description -> onClick(name, description)}
        recept(false,"Борщ","sasdasd",7, mvm = mvms ){name, description -> onClick(name, description)}
        recept(false,"Каша","sdasda",3,mvm = mvms){name, description -> onClick(name, description)}
        recept(false,"Турбо степан","1 Скителс \n" +
                "2 Самогон бабки \n" +
                "3 Боборовое мясо \n" +
                "4 Виски \n" +
                "5 Аспирин \n" +
                "6 Витамин C\n" +
                "7 Лава от Влада А4\n" +
                "8 Рассол \n" +
                "9 Взболтать но не смешивать",10,mvm = mvms){name, description -> onClick(name, description)}
        recept(false,"Прикол","sdada",3,mvm = mvms){name, description -> onClick(name, description)}
        recept(false,"Жаренная вода","sdasdad",1,mvm = mvms) { name, description -> onClick(name, description)}
    initialized.value = true
    }
}

//@Preview
@Composable
fun recept( liked:Boolean, name: String, description:String, stars:Int, mvm: MaintViewModel, onClick: (name:String, description:String) -> Unit) {
    var id = remember {
        mutableStateOf(0)
    }
    if(!initialized.value){
        var thisRecep = remember {
            mutableStateOf(Recept("","",4))
        }
        id.value = addElement(recepts.size)
        thisRecep.value = Recept(name, description, stars)
        var currentRecept = thisRecep.value
        recepts += currentRecept
    }
    for(i in 0.. recepts.size-1){
        if(recepts[i].name.equals(name)){
            id.value = i
        }
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick(name, description) },
    ) {
        Row(
            modifier = Modifier,
        ) {
            Image(painter = painterResource(id = R.drawable.food), contentDescription = "")
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
                            text = "${name}", color = Color.Gray, fontSize = 30.sp
                        )
                    }
                    Box() {
                        Text(
                            text = "Ням ням ням лакомство",
                            color = Color.Gray,
                            fontSize = 15.sp
                        )
                    }
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Text(text = "${stars}")
                        Image(painter = painterResource(id = R.drawable.star), contentDescription = "",
                            Modifier
                                .width(30.dp)
                                .height(30.dp))
                    }
                    Button(onClick = {
                        Log.d("INFO", "${id.value}")
                        if (!liked){
                            recepts[id.value].liked = true
                            currentUser.liked += recepts[id.value]
                            GlobalScope.launch {
                                mvm.addFavoriteToList()
                            }

                        } else {
                            recepts[id.value].liked = false
                            currentUser.liked -= recepts[id.value]
                            GlobalScope.launch {
                                mvm.addFavoriteToList()
                            }
                        }
                    }) {
                        if(!liked){
                            Text(text = "В избранное")
                        } else{
                            Text(text = "Удалить")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun receptInfo(navHostController: NavHostController, nameRecept:String, description:String, onClick: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Gray)
        .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Text(text = "$nameRecept", fontSize = 50.sp)
        Text(text = "Как приготовить:", fontSize = 30.sp )
        Card(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = "$description", fontSize = 20.sp)
        }
        Button(onClick = onClick) {
            Text(text = "Назад к рецептам")
        }
    }
}

//@Preview
@Composable
private fun horisontalCards(texti:String){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(120.dp)
            .height(160.dp),
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            Text(text = "$texti")
        }
    }
}

@Composable
fun nonLoginScreen(OnClick: () -> Unit){

    Column(Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Вы не вошли в приложуху!", fontSize = 30.sp)
        Button(onClick = { OnClick() }) {
            Text(text = "Войти")
        }
    }
}
@Composable
fun nonSelectedScreen(OnClick: () -> Unit){

    Column(Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Выберите рецепт", fontSize = 40.sp)
        Button(onClick = { OnClick() }) {
            Text(text = "Выбрать")
        }
    }
}

@Composable
fun favorScreen(mvms: MaintViewModel, onClick: (name:String, description:String) -> Unit){
    Column(Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Избранное:")
        for(i in 0 .. currentUser.liked.size-1){
            if(currentUser.liked[i].liked){
                recept(true,name = currentUser.liked[i].name, description = currentUser.liked[i].description, stars = currentUser.liked[i].stars, mvm = mvms){
                        name, description -> run {
                            onClick(name,description)
                        }
                }
            }
        }
    }
}

@Synchronized
private fun addElement(id:Int):Int{
    return id
}


