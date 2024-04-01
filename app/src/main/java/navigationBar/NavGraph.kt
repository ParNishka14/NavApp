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
var isLoggined: Boolean = true
var receptIsSelected: Boolean = false
@Composable
fun NavGraph(navHostController: NavHostController, context: Context, userss: State<List<Users>>) {
    NavHost(navController = navHostController, startDestination = "loginScreen") {
        composable("loginScreen") {
            loginScreen(context) {
                for (i in 0..userss.value.size - 1)
                    if (loginGlobal == userss.value.get(i).name && passwordGlobal == userss.value.get(
                            i
                        ).password
                    ) {
                        navHostController.navigate("receptScreen")
                        isLoggined = true
                    } else {
                    }
            }
        }
        composable("receptScreen") {
            if (isLoggined) {
                receptScreen { name, description ->
                    run {
                        receptIsSelected = true
                        nameRecept = name
                        descriptionRecept = description
                        navHostController.navigate("receptInfo")
                    }
                }
            } else navHostController.navigate("nonLoginScreen")
        }
        composable("receptInfo") {
            if (isLoggined) {
                if (receptIsSelected) {
                    receptInfo(
                        navHostController = navHostController,
                        nameRecept = nameRecept,
                        description = descriptionRecept
                    ) {
                        navHostController.navigate("receptScreen")
                    }
                } else navHostController.navigate("nonSelectScreen")
            } else navHostController.navigate("nonLoginScreen")
        }

        composable("nonLoginScreen") {
            nonLoginScreen() {
                navHostController.navigate("LoginScreen")
            }
        }
        composable("nonSelectScreen") {
            nonSelectedScreen {
                navHostController.navigate("receptScreen")
            }
        }
        composable("favorScreen") {
            favorScreen{ name, description ->
                run {
                    receptIsSelected = true
                    nameRecept = name
                    descriptionRecept = description
                    navHostController.navigate("receptInfo")
                }
            }
        }
    }
}