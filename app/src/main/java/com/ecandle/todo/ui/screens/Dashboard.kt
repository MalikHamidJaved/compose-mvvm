package com.ecandle.todo.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ecandle.todo.R
import com.ecandle.todo.component.CustomTopAppBar

data class PersonModel(val name:String)

private val personsList = mutableListOf<PersonModel>()

@Composable
fun Dashboard(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){
        DashboardPage(navController)
    }
}

@Composable
fun DashboardPage(navController: NavHostController){
    personsList.add(PersonModel("James"))
    personsList.add(PersonModel("John"))
    personsList.add(PersonModel("Robert"))
    personsList.add(PersonModel("Michael"))
    personsList.add(PersonModel("William"))
    personsList.add(PersonModel("David"))
    personsList.add(PersonModel("Richard"))
    personsList.add(PersonModel("Charles"))
    personsList.add(PersonModel("Joseph"))
    personsList.add(PersonModel("Steven"))
    personsList.add(PersonModel("Kenneth"))
    personsList.add(PersonModel("George"))
    personsList.add(PersonModel("Donald"))

    Scaffold(
        topBar = {

        }, content = {
            Box(modifier = Modifier.background(Color.White)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(10.dp)) {
                        items(personsList) { model ->
                            ListRow(model = model)
                        }
                    }
                }
            }
        })
}

@Composable
fun ListRow(model: PersonModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        val paddingModifier  = Modifier.padding(10.dp)
        Card(elevation = 10.dp, modifier = paddingModifier) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
                Text(
                    text = model.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }
        }
    }
}


@Preview
@Composable
fun previewListItem(){
    ListRow(model = PersonModel("alpha"))
}