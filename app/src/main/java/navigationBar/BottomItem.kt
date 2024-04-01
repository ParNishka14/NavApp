package navigationBar

import com.loxick.navapp.R


sealed class BottomItem(val title:String, val iconId: Int, val route: String) {
    object Screen1: BottomItem("", R.drawable.userlogin, "loginScreen")
    object Screen2: BottomItem("", R.drawable.foodico, "receptScreen")
    object Screen3: BottomItem("", R.drawable.information, "receptInfo")
    object Screen4: BottomItem("", R.drawable.baseline_accessible_forward_24, "nonLoginScreen")
    object Screen5: BottomItem("", R.drawable.baseline_accessible_forward_24, "nonSelectScreen")
    object Screen6: BottomItem("", R.drawable.heart, "favorScreen")
}