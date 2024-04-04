package navigationBar

import android.graphics.drawable.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigations(navController: NavController ){
var listScreens = listOf(
    BottomItem.Screen1,
    BottomItem.Screen2,
    BottomItem.Screen3,
    BottomItem.Screen6
)
    NavigationBar() {
            val  backStackEntry by navController.currentBackStackEntryAsState()
            val currentRout = backStackEntry?.destination?.route
            listScreens.forEach {
                item ->
                NavigationBarItem(selected = currentRout == item.route, onClick = {
                                                                                  navController.navigate(item.route)
                }, icon = { androidx.compose.material3.Icon(
                    painter = painterResource(id = item.iconId),
                    contentDescription = "")}, 
                    label = {
                        Text(text = item.title, fontSize = 9.sp)
                        },
                    colors = androidx.compose.material3.NavigationBarItemDefaults
                        .colors(
                            selectedIconColor = Color.White,
                            indicatorColor = Color.Gray)
                )
            }
    }
}