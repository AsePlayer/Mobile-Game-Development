package com.ryan.priceplanchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ryan.priceplanchallenge.ui.theme.PricePlanChallengeTheme
import androidx.compose.material.Button
import androidx.compose.ui.Alignment

data class PlanModel(
    val id: Int,
    var name: String,
    var bulletpoints: String,
    var description: String,
    var classes: Int,
    var services: Int,
    var selected: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PricePlanChallengeTheme() {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp() {
        var plans = remember {
            mutableStateListOf(
                PlanModel(
                    0,
                    "Freelancer",
                    "• Free as a bird!\n" +
                            "  • Be your own boss\n" +
                            "  • Whip and nae nae on the haters!",
                    "For individuals who work independently",
                    3,
                    10,
                    false
                ),
                PlanModel(
                    1,
                    "Startup",
                    "• Lightweight and robust!\n" +
                            "  • Perfect for newcomers!\n" +
                            "  • No reason you can't still whip and nae nae on the haters!",
                    "For small businesses with less than 5 employees",
                    5,
                    20,
                    false
                ),
                PlanModel(
                    2,
                    "Agency",
                    "• Sounds pretty cool for what it actually is.\n" +
                            "  • Got any secrets you want to share on our 24/7 support line?\n" +
                            "  • Gets updates faster",
                    "For large businesses with more than 5 employees",
                    1337,
                    69,
                    false
                ),
                PlanModel(
                    3,
                    "Enterprise",
                    "• Me me biiiiig boy! Pay up buttercup!!\n" +
                            "  • Not a whole lotta benefits, but you are Enterprise so we make more money!\n" +
                            "  • Pleasure doing business with you, bozo!",
                    "For large businesses with more than 5 employees",
                    10,
                    30,
                    false
                )
            )
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn() {
                items(plans.size) { index ->
                    Plan(plan = plans[index],
                        {
                            Log.d("Plan Tag", "MyApp: You selected plan number $it")
                            for (i in plans.indices) {
                                plans[i] = PlanModel(
                                    i, plans[i].name, plans[i].bulletpoints,
                                    plans[i].description, plans[i].classes, plans[i].services, false
                                )
                            }
                            plans[it] = PlanModel(
                                it, plans[it].name, plans[it].bulletpoints,
                                plans[it].description, plans[it].classes, plans[it].services, true
                            )
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun Plan(plan: PlanModel, selectPlan: (id: Int) -> Unit) {
        Column {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        selectPlan(plan.id)
                        Log.d("Plan Tag", "Plan: $plan")
                    },
                color = Color.Black,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                text = "${plan.name}"
            )
            if (plan.selected) {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = plan.description
                )
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.LightGray)
                        .fillMaxWidth(),
                    color = Color.DarkGray,
                    fontSize = 5.em,
                    text = "${plan.classes} classes and ${plan.services} services included"
                )
                Text(
                    text = "  ${plan.bulletpoints}"
                )

                Button(
                    onClick = {},
                    modifier = Modifier.align(Alignment.End),
                    contentPadding = PaddingValues(end = 20.dp),
                    content = {
                        Text("Subscribe")
                    }
                )
            }
            Divider()
        }
    }
}