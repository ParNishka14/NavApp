package navigationBar
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loxick.navapp.Users
import com.loxick.navapp.loginGlobal
import com.loxick.navapp.passwordGlobal


var nameRecept:String = ""
var descriptionRecept:String = ""
@Composable
fun NavGraph(navHostController: NavHostController, context: Context, userss: State<List<Users>>) {
NavHost(navController = navHostController, startDestination = "loginScreen" ){
    composable("loginScreen"){
    loginScreen(context) {
        for (i in 0..userss.value.size - 1)
            if (loginGlobal == userss.value.get(i).name && passwordGlobal == userss.value.get(i).password
            ) {
                navHostController.navigate("receptScreen")
            } else{

            }
        }
    }
    composable("receptScreen"){
        receptScreen {
            name, description ->
            run {
                nameRecept = name
                descriptionRecept = description
                navHostController.navigate("receptInfo")
            }
        }
    }
    composable("receptInfo"){
        receptInfo(navHostController = navHostController, nameRecept = nameRecept , description = descriptionRecept ){
            navHostController.navigate("receptScreen")
        }
    }
}
}