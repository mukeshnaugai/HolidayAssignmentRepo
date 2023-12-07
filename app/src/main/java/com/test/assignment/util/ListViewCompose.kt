package com.test.assignment.util


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.assignment.viewmodel.HolidayMainViewModel

class ListComopse(val viewModel: HolidayMainViewModel) {

    @Composable
    fun showList() {
        val list = viewModel.liveData.observeAsState().value
        if (list != null) {
            LazyColumn {
                item {
                    list.data!!.map {
                        listItem(it.title!!, it.date)
                    }

                }

            }

        }
        else{
            showLoadingText()
        }
    }

    @Composable
    fun showLoadingText() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Loading...",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}




@Composable
fun listItem(string: String, date: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = string, fontSize = 15.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Text(
                text = date!!,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 3.dp)
            )
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }


}

