package com.test.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.test.assignment.MainViewModelFactory.MainFactory
import com.test.assignment.util.ListComopse
import com.test.assignment.ui.theme.TestAppComposalTheme
import com.test.assignment.util.MyApplication
import com.test.assignment.viewmodel.HolidayMainViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var mainFactory: MainFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).application.inject(this)
        val viewModel = ViewModelProvider(this, mainFactory)[HolidayMainViewModel::class.java]
        viewModel.getHolidayDetails()
        setContent {
            TestAppComposalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val comopse = ListComopse(viewModel)
                    comopse.showList()

                }

            }
        }


    }
}


