package com.example.studyengine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.studyengine.ui.StudyEngineApp
import com.example.studyengine.ui.theme.StudyEngineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyEngineTheme {
                StudyEngineApp()
            }
        }
    }
}
