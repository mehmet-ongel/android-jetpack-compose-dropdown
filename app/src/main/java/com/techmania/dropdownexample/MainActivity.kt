package com.techmania.dropdownexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.techmania.dropdownexample.ui.theme.DropdownExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropdownExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DropdownExample()
                }
            }
        }
    }
}

@Composable
fun DropdownExample() {

    /*
    val selectedItem = remember {
        mutableStateOf("Germany")
    }*/
    val dropDownStatus = remember {
        mutableStateOf(false)
    }
    /*
    val countryList = listOf("Germany","England","Italy","Belgium","Finland","Turkey","Bulgaria","Greece","Netherlands","Russia","Brazil","Argentina",
        "Germany","England","Italy","Belgium","Finland","Turkey","Bulgaria","Greece","Netherlands","Russia","Brazil","Argentina")
    */
    
    val countryList = stringArrayResource(id = R.array.countryList)
    
    val itemPosition = remember {
     
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box() {

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    dropDownStatus.value = true
                }
            ) {
                Text(
                    text = countryList[itemPosition.value],
                    modifier = Modifier.clickable {
                        dropDownStatus.value = true
                    })
                Image(painter = painterResource(id = R.drawable.drop_down_icon), contentDescription = "")
            }

            DropdownMenu(
                expanded = dropDownStatus.value,
                onDismissRequest = {

                    dropDownStatus.value = false

                }) {

                countryList.forEachIndexed { index, country ->

                    DropdownMenuItem(
                        onClick = {

                            dropDownStatus.value = false
                            itemPosition.value = index

                        }) {

                        Text(text = country)

                    }

                }

            }

        }
    }
}